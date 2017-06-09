package rifqi.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button caesarButton, viginereButton, transpotitionButton,aesButton, md5Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        caesarButton = (Button) findViewById(R.id.caesarButton);
        viginereButton = (Button) findViewById(R.id.viginereButton);
        transpotitionButton = (Button) findViewById(R.id.transpositionButton);
        aesButton = (Button) findViewById(R.id.aesButton);
        md5Button = (Button) findViewById(R.id.md5Button);

        md5Button.setOnClickListener(this);
        aesButton.setOnClickListener(this);
        caesarButton.setOnClickListener(this);
        viginereButton.setOnClickListener(this);
        transpotitionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.caesarButton) {
            Log.d("FP", "caesar cipher  execute");
            caesarCipher();
        }
        if (v.getId() == R.id.viginereButton) {
            Log.d("FP", "viginere cipher execute");
            viginereCipher();
        }
        if (v.getId() == R.id.transpositionButton) {
            Log.d("FP", "Transpotition cipher execute");
            transCipher();
        }
        if (v.getId() == R.id.aesButton) {
            Log.d("FP", "AES cipher execute");
            aesCipher();

        }
        if (v.getId() == R.id.md5Button){
            Log.d("FP","MD5 cipher execute");
            md5Cipher();
        }
    }

    private void md5Cipher() {
        Intent i = new Intent(getApplicationContext(),Md5Cipher.class);
        startActivity(i);
    }

    private void aesCipher() {
        Intent i = new Intent(getApplicationContext(),AesCipher.class);
        startActivity(i);
    }

    private void transCipher() {
        Intent i = new Intent(getApplicationContext(),TranspotitionCipher.class);
        startActivity(i);
    }

    private void viginereCipher() {
        Intent i = new Intent(getApplicationContext(),ViginereCipher.class);
        startActivity(i);
    }

    private void caesarCipher() {
        Intent i = new Intent(getApplicationContext(),CaesarCipher.class);
        startActivity(i);
    }
}
