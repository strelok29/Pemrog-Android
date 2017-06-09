package rifqi.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button loginButton,exitButton;
    EditText passEditText, userEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        exitButton = (Button) findViewById(R.id.exitButton);
        loginButton = (Button) findViewById(R.id.loginButton);
        userEditText = (EditText)findViewById(R.id.userEditText);
        passEditText = (EditText)findViewById(R.id.passEditText);


        loginButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginButton){
            Log.d("FP","log in success");
            validateUser();
        }
        if (v.getId() == R.id.exitButton){
            Log.d("FP","App Terminated");
            exit();
        }

    }

    private void exit(){
        finish();
        System.exit(0);
    }


    private void validateUser() {
        String username = userEditText.getText().toString();
        String pass = passEditText.getText().toString();
        if(username.equals("admin")&&pass.equals("admin123")){
            Toast.makeText(LoginActivity.this,"Login Success", Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_SHORT).show();
        }
    }
}
