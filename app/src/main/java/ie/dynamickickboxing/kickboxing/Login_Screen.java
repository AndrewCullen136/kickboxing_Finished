package ie.dynamickickboxing.kickboxing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login_Screen extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Attempts;
    private Button Login;
    private int counter = 3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login__screen);

        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText) findViewById(R.id.etPassword);
        Attempts = (TextView) findViewById(R.id.tvAttempts);
        Login = (Button) findViewById(R.id.btLogIn);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                valdate(Name.getText().toString(), Password.getText().toString());
            }
        });
    }

    private void valdate(String userName, String userPassword) {

        if ((userName.equals("User")) && (userPassword.equals("abc1"))) {

            Intent hs = new Intent(Login_Screen.this, HomeScreen.class);
            startActivity(hs);
        } else {
            counter--;

            Attempts.setText("Number of attempts remaining: " + String.valueOf(counter));

            if (counter == 0) {

                Login.setEnabled(false);

            }
        }

    }
}
