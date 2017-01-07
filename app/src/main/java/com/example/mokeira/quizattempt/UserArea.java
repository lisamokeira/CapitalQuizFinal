package com.example.mokeira.quizattempt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UserArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final TextView tvWelcomeMessage = (TextView) findViewById(R.id.tvWelcomeMessage);
        final TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final Button bHighScores = (Button) findViewById(R.id.bHighScores);
        final Button bSignOut = (Button) findViewById(R.id.bSignOut);
        final Button bQuit = (Button) findViewById(R.id.bQuit);
    }

    public void onNewQuizClick(View view)
    {
        Intent newQuiz = new Intent(this, MainActivityQuiz.class);
        startActivity(newQuiz);
    }
}
