package com.example.mokeira.quizattempt;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity
{
    private static EditText etUsername;
    private static EditText etPassword;
    private static Button bRegister;

    private TextView textViewSignin;

    private FirebaseAuth firebaseAuth;

    public static  String userEmail="",userPassword="";

    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        SharedPreferences editor = getApplicationContext().getSharedPreferences("credential", Context.MODE_PRIVATE);


        loggedIn = editor.getBoolean("login",false);

        if(loggedIn){
            startActivity(new Intent(getApplicationContext(), UserArea.class));
            finish();
        }


        //initializing firebase auth object
        firebaseAuth = FirebaseAuth.getInstance();

        LoginButton();

        //final TextView registerLink = (TextView) findViewById(R.id.tvRegisterHere);
        textViewSignin = (TextView) findViewById(R.id.textViewSignin);


        textViewSignin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent userAreaIntent = new Intent(RegistrationActivity.this, LogInActivity.class);
                startActivity(userAreaIntent);
            }
        });

    }
    public void LoginButton()
    {
        etUsername = (EditText)findViewById(R.id.etUsername);
        etPassword = (EditText)findViewById(R.id.etPassword);
        bRegister = (Button)findViewById(R.id.bLogIn);



        bRegister.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {

                        if (etUsername.getText().toString().trim().equals("")||etPassword.getText().toString().trim().equals("")){
                            Toast.makeText(RegistrationActivity.this,"Empty Fields",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            userPassword = etPassword.getText().toString();
                            userEmail= etUsername.getText().toString();
                            registerUser();
                        }
                        /*if (etUsername.getText().toString().equals("user") &&
                                etPassword.getText().toString().equals("pass"))
                        {
                            Toast.makeText(RegistrationActivity.this,"Username and password is correct",
                                    Toast.LENGTH_SHORT).show();
                            Intent userAreaIntent = new Intent(RegistrationActivity.this, UserArea.class);
                            RegistrationActivity.this.startActivity(userAreaIntent);
                        }
                        else {
                            Toast.makeText(RegistrationActivity.this, "Username and password is NOT correct",
                                    Toast.LENGTH_SHORT).show();
                        }*/
                    }

                }
        );
    }

    private void registerUser()
    {

        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(RegistrationActivity.this,"Successfully registered",Toast.LENGTH_LONG).show();

                            SharedPreferences.Editor editor = getSharedPreferences("credential", MODE_PRIVATE).edit();
                            editor.putString("username", userEmail);
                            editor.putBoolean("login", true);
                            editor.commit();


                            finish();
                            startActivity(new Intent(getApplicationContext(), UserArea.class));

                        }else{
                            //display some message here
                            Toast.makeText(RegistrationActivity.this,"Registration Error",Toast.LENGTH_LONG).show();
                        }

                    }
                });

    }





}
