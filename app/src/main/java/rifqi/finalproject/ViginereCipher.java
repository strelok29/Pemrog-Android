package rifqi.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViginereCipher extends AppCompatActivity {

    Button encryptButton,decryptButton,deleteButton;
    EditText cipherEditText, keyEditText;
    TextView resultTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viginere_cipher);

        encryptButton = (Button) findViewById(R.id.encryptButton);
        decryptButton = (Button) findViewById(R.id.decryptButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        cipherEditText = (EditText) findViewById(R.id.cipherEditText);
        keyEditText = (EditText) findViewById(R.id.keyEditText);
        resultTextView = (TextView) findViewById(R.id.resultText);

        //caps lock semua di ET
        cipherEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});
        keyEditText.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

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
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(ViginereCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    decode_viginere();

                    //close keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }


        });

    }

    private void decode_viginere() {
        String res = "";
        Editable key = keyEditText.getText();
        Editable text = cipherEditText.getText();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c - key.charAt(j) + 26) % 26 + 'A');
            j = ++j % key.length();
        }
        resultTextView.setText(res);
    }


    private void encrypt_button() {
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(ViginereCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    encode_viginere();

                    //close keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        });

    }

    private void encode_viginere() {

        String res = "";
        Editable key = keyEditText.getText();

        Editable text = cipherEditText.getText();
        for (int i = 0, j = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c < 'A' || c > 'Z') continue;
            res += (char)((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
            j = ++j % key.length();
        }
        resultTextView.setText(res);
    }
}
