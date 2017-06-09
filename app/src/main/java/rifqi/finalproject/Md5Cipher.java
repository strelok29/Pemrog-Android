package rifqi.finalproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Cipher extends AppCompatActivity {

    Button encryptButton, decryptButton, deleteButton;
    EditText cipherEditText, keyEditText;
    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_md5_cipher);

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
                    Toast.makeText(Md5Cipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    decode_md5();
                }

            }
        });
    }

    private void decode_md5() {

    }

    private void encrypt_Button() {
        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cipherEditText.length() == 0 || keyEditText.length() == 0){
                    Toast.makeText(Md5Cipher.this, "Field input is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    encode_md5();
                }
            }
        });
    }

    private void encode_md5() {
        String s = cipherEditText.getText().toString();
        String key = keyEditText.getText().toString();
        int j = Integer.valueOf(key);


        try {

            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i=j; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
//            return hexString.toString();
            resultTextView.setText(hexString);

        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
