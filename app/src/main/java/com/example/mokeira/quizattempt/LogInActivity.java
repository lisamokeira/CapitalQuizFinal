package com.example.mokeira.quizattempt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LogInActivity extends AppCompatActivity
{
    private static EditText etUsername;
    private static EditText etPassword;
    private static Button bLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        LoginButton();

        final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);

        registerLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent userAreaIntent = new Intent(LogInActivity.this, RegisterActivity.class);
                LogInActivity.this.startActivity(userAreaIntent);
            }
        });

    }
    public void LoginButton()
    {
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        bLogIn = (Button)findViewById(R.id.bLogIn);


        bLogIn.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        if (etUsername.getText().toString().equals("user") &&
                                etPassword.getText().toString().equals("pass"))
                        {
                            Toast.makeText(LogInActivity.this,"Username and password is correct",
                                    Toast.LENGTH_SHORT).show();
                            Intent userAreaIntent = new Intent(LogInActivity.this, UserArea.class);
                            LogInActivity.this.startActivity(userAreaIntent);
                        }
                        else {
                            Toast.makeText(LogInActivity.this, "Username and password is NOT correct",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }

                }
        );
    }
}
