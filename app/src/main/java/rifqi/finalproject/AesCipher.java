package rifqi.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import static android.R.attr.key;

public class AesCipher extends AppCompatActivity {

    Button encryptButton, decryptButton, deleteButton;
    EditText cipherEditText, keyEditText;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes_cipher);

        encryptButton = (Button) findViewById(R.id.encryptButton);
        decryptButton = (Button) findViewById(R.id.decryptButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        cipherEditText = (EditText) findViewById(R.id.cipherEditText);
        keyEditText = (EditText) findViewById(R.id.keyEditText);
        resultTextView = (TextView) findViewById(R.id.resultTextView);

        encrypt_Button();
        decrypt_Button();
        delete_Button();
    }

    private void encrypt_Button() {
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(AesCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    try {
                        encode_aes();
                        //close keyboard
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                    } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void encode_aes() throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        String text = cipherEditText.getText().toString();
        String key = keyEditText.getText().toString();

        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);
        byte[] encrypted = cipher.doFinal(text.getBytes());

//        System.err.println("Encrypted: " + new String(Base64.getEncoder().encodeToString(encrypted)));

        resultTextView.setText(new String(encrypted));
    }


    private void decrypt_Button() {
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(AesCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    decode_aes();
                    //close keyboard
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                }
            }
        });

    }

    private void decode_aes(){


//        String text = cipherEditText.getText().toString();
//        String encrypted = Base64.decode(text);
//        String key = keyEditText.getText().toString();
//
//        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
//        Cipher cipher = Cipher.getInstance("AES");
//
//        cipher.init(Cipher.DECRYPT_MODE, aesKey);
//        String decrypted = new String(cipher.doFinal(encrypted));
//        resultTextView.setText(decrypted);
    }




    private void delete_Button() {
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cipherEditText.setText("");
                keyEditText.setText("");
                resultTextView.setText("");
            }
        });
    }
}
