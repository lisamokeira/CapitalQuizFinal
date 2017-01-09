package com.example.mokeira.quizattempt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.mokeira.quizattempt.MainActivityQuiz.score;

public class ScoreActivity extends AppCompatActivity
{
    public TextView score_;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score_ = (TextView) findViewById(R.id.txtScore);

        score_.setText("Your Score Is : "+ String.valueOf(score) );

    }
}
