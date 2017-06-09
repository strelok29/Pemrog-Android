package rifqi.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static android.R.id.message;

public class TripleDesCipher extends AppCompatActivity {

    Button encryptButton, decryptButton, deleteButton;
    EditText cipherEditText, keyEditText;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triple_des_cipher);


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

    private void decrypt_Button() {
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(TripleDesCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    decode_des();
                }

            }
        });

    }

    private void decode_des() {

    }

    private void encrypt_Button() {
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(TripleDesCipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    encode_des();
                }
            }
        });
    }

    private void encode_des() {

    }

}
