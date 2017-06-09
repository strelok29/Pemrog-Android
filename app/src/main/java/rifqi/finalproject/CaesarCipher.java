package rifqi.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CaesarCipher extends AppCompatActivity {

    EditText cipherEditText, keyEditText;
    Button encButton, decButton, deleteButton;
    TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caesar_cipher);

        cipherEditText = (EditText) findViewById(R.id.cipherEditText);
        keyEditText = (EditText) findViewById(R.id.keyEditText);
        encButton = (Button) findViewById(R.id.encButton);
        decButton = (Button) findViewById(R.id.decButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        myText = (TextView) findViewById(R.id.MyText);

        encrypt_Button();
        decrypt_Button();
        delete_Button();

    }

    private void decrypt_Button()
    {
        decButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(CaesarCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                {
                    decode_caesar_cipher();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        });
    }

    private void encrypt_Button() {
        encButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(CaesarCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    caesar_cipher_method();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        });
    }

    private void caesar_cipher_method() {
        String shiftText = keyEditText.getText().toString();
        int shift = Integer.valueOf(shiftText);
        Editable msg = cipherEditText.getText();
        String s = "";
        int len = msg.length();
        for (int x = 0; x < len; x++) {
            char c = (char) (msg.charAt(x) + shift);
            if (c > 'z' || (c > 'Z' && c < 'd'))
            {
                c -= 26;
            }

            s += c;
        }
        myText.setText(s);
    }

    private void decode_caesar_cipher() {
        String shiftText = keyEditText.getText().toString();
        int shift = Integer.valueOf(shiftText);
        Editable msg = cipherEditText.getText();
        String s = "";
        int len = msg.length();
        for (int x = 0; x < len; x++) {
            char c = (char) (msg.charAt(x) - shift);
            if (c < 'A' || (c < 'a' && c > 'W'))
                c += 26;
            s += c;


        }
        myText.setText(s);
    }


    private void delete_Button() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cipherEditText.setText("");
                keyEditText.setText("");
                myText.setText("");
            }
        });
    }
}
