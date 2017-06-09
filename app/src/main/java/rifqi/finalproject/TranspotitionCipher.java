package rifqi.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TranspotitionCipher extends AppCompatActivity {

    private Button encryptButton,decryptButton,deleteButton;
    private EditText cipherEditText, keyEditText;
    private TextView resultTextView;

    private int key;
    private String plaintext;
    private ArrayList<String> cipherText;
    private ArrayList<String> plain;
    private String cipher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transpotition_cipher);

        encryptButton = (Button) findViewById(R.id.encryptButton);
        decryptButton = (Button) findViewById(R.id.decryptButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        cipherEditText = (EditText) findViewById(R.id.cipherEditText);
        keyEditText = (EditText) findViewById(R.id.keyEditText);
        resultTextView = (TextView) findViewById(R.id.resultText);

        encrypt_button();
        decrypt_button();
        delete_button();
    }

    private void delete_button() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cipherEditText.setText("");
                keyEditText.setText("");
                resultTextView.setText("");
            }
        });
    }

    private void decrypt_button() {
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cipherEditText.length() == 0 || keyEditText.length() == 0)
                {
                    Toast.makeText(TranspotitionCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    decode_trans();
                    //close keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }

            }
        });
        
    }
//beda di s dri encode
    private void decode_trans() {
        {
            key = Integer.parseInt(keyEditText.getText().toString());
            cipher = cipherEditText.getText().toString();
            plain  = new ArrayList<>();

            if (key < cipher.length())
            {
                if (cipher.length() % key == 0)
                {
                    int row = key;
                    int coloumn = cipher.length() / key;

                    String[] temps = cipher.split("");
                    int parseHelper = 1;

                    String[][] tempMatrixs = new String[row][coloumn];
                    for (int r = 0; r < tempMatrixs.length; r++) {
                        for (int c = 0; c < tempMatrixs[r].length; c++) {
                            tempMatrixs[r][c] = temps[parseHelper];
                            Log.i("Hasil", " " + r + "," + c + " " + tempMatrixs[r][c].toString());
                            parseHelper++;
                        }
                    }

                    String[][] hasils;
                    hasils = transposeMatrixs(tempMatrixs);

                    for (int r = 0; r < hasils.length; r++) {
                        for (int c = 0; c < hasils[r].length; c++) {
                            Log.i("Hasil", " " + r + "," + c + " " + hasils[r][c].toString());
                            plain.add(hasils[r][c]);
                        }
                    }

                    resultTextView.setText(plaintext.toString().replace("[", "").replace("]", "").replace(",", ""));
                }
                else
                {
                    Toast.makeText(TranspotitionCipher.this,"Cannot decrypt!", Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(TranspotitionCipher.this,"Cannot decrypt!", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private String[][] transposeMatrixs(String[][] s) {
        String[][] temps = new String[s[0].length][s.length];
        for (int i = 0; i < s.length; i++)
        {
            for (int j = 0; j < s[0].length; j++) {
                temps[j][i] = s[i][j];
            }
        }
        return temps;
    }


    private void encrypt_button() {
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(TranspotitionCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    encode_trans();
                    //close keyboard
                    InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        });
    }

    private void encode_trans() {
        key = Integer.parseInt(keyEditText.getText().toString());
        plaintext = cipherEditText.getText().toString().replace(" ", "");
        cipherText = new ArrayList<>();


        if (key < plaintext.length()) {
            if (plaintext.length() % key == 0) {
                int row = plaintext.length() / key;
                int coloumn = key;

                String[] temp = plaintext.split("");
                int parseHelper = 1;

                String[][] tempMatrix = new String[row][coloumn];
                for (int r = 0; r < tempMatrix.length; r++) {
                    for (int c = 0; c < tempMatrix[r].length; c++) {
                        tempMatrix[r][c] = temp[parseHelper];
                        Log.i("Hasil", " " + r + "," + c + " " + tempMatrix[r][c].toString());
                        parseHelper++;
                    }
                }
                String[][] hasil;
                hasil = transposeMatrix(tempMatrix);


                for (int r = 0; r < hasil.length; r++) {
                    for (int c = 0; c < hasil[r].length; c++) {
                        Log.i("Hasil", " " + r + "," + c + " " + hasil[r][c].toString());
                        cipherText.add(hasil[r][c]);
                    }
                }
                resultTextView.setText(cipherText.toString().replace("[", "").replace("]", "").replace(",", ""));
            } else {
                int insertAdder = key - (plaintext.length() % key);
                for (int i = 1; i <= insertAdder; i++) {
                    plaintext += "a";
                }
                Log.i("plaintext : ", plaintext);
                int row = plaintext.length() / key;
                int coloumn = key;

                String[] temp = plaintext.split("");
                int parseHelper = 1;

                String[][] tempMatrix = new String[row][coloumn];
                for (int r = 0; r < tempMatrix.length; r++) {
                    for (int c = 0; c < tempMatrix[r].length; c++) {
                        tempMatrix[r][c] = temp[parseHelper];
                        Log.i("Hasil", " " + r + "," + c + " " + tempMatrix[r][c].toString());
                        parseHelper++;
                    }
                }
                String[][] hasil;
                hasil = transposeMatrix(tempMatrix);

                for (int r = 0; r < hasil.length; r++) {
                    for (int c = 0; c < hasil[r].length; c++) {
                        Log.i("Hasil", " " + r + "," + c + " " + hasil[r][c].toString());
                        cipherText.add(hasil[r][c]);
                    }
                }
                resultTextView.setText(cipherText.toString().replace("[", "").replace("]", "").replace(",", ""));
            }
        }
        else
        {
            Toast.makeText(TranspotitionCipher.this, "Cannot encrypt!", Toast.LENGTH_SHORT).show();
        }
    }
    public static String[][] transposeMatrix(String [][] m) {
        String[][] temp = new String[m[0].length][m.length];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                temp[j][i] = m[i][j];
            }
        }
        return temp;
    }

}
