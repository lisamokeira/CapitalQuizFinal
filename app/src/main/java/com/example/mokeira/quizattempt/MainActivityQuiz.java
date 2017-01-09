package com.example.mokeira.quizattempt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivityQuiz extends AppCompatActivity
{

    private boolean done;
    private int QuestionNo, count=1;

    public Button b1,bSubmit;
    public TextView t1;

    public EditText et;
    public static int score;
    public boolean cheated;






    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);
        score = 0;
        String[] questions = getResources().getStringArray(R.array.Questions);
        TextView t = (TextView) findViewById(R.id.tvQuestion);
        b1 = (Button) findViewById(R.id.nextbutton);
        bSubmit = (Button) findViewById(R.id.bSubmit);
        t1 = (TextView) findViewById(R.id.tvCorrect);


        et = (EditText) findViewById(R.id.tvUserAnswer);
        t.setText(questions[QuestionNo]);
        findViewById(R.id.tvCorrect).setVisibility(View.INVISIBLE);
        findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);

        /*bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivityQuiz.this, "clicked", Toast.LENGTH_SHORT).show();
            }
        });*/



    }
    public void onCheatClick(View view)
    {

        String[] hints = getResources().getStringArray(R.array.Hints);
        Toast toasty = Toast.makeText(getApplicationContext(), hints[count-1], Toast.LENGTH_SHORT);
        toasty.show();
        cheated = true;

    }

    public void onNextClick(View view)
    {

        //Toast.makeText(this, "clicked", Toast.LENGTH_SHORT).show();



            String[] questions = getResources().getStringArray(R.array.Questions);

        if (count-1==questions.length){
            Intent intent = new Intent(this,ScoreActivity.class);
            startActivity(intent);
            this.finish();

        }


            if (count<questions.length){
                cheated = false;
                TextView t = (TextView) findViewById(R.id.tvQuestion);
                t.setText(questions[count]);
                count++;

                et.setText("");

                findViewById(R.id.tvCorrect).setVisibility(View.INVISIBLE);
                findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);




            } else
            {
                Toast.makeText(this, String.valueOf(score), Toast.LENGTH_SHORT).show();
                finishGame();
            }


            /*if (QuestionNo < (questions.length - 1))
            {
                StringBuilder builder = new StringBuilder();
                for (String s : questions){
                    builder.append(s+" ");
                    TextView t = (TextView) findViewById(R.id.tvQuestion);
                    t.setText(builder.toString());

                }




                QuestionNo = QuestionNo++;
                TextView t = (TextView) findViewById(R.id.tvQuestion);
                t.setText(questions[QuestionNo]);



                done = false;
            }*/
        }


    public void onSkipClick(View view)
    {
        //done= true;
        onNextClick(view);
    }

    public void answersubmitted()
    {
        findViewById(R.id.tvCorrect).setVisibility(View.VISIBLE);
        findViewById(R.id.nextbutton).setVisibility(View.VISIBLE);
    }

    public void onSubmitClick(View view)
    {


            if(t1.getVisibility()== view.VISIBLE && b1.getVisibility()== view.VISIBLE){
                findViewById(R.id.tvCorrect).setVisibility(View.INVISIBLE);
                findViewById(R.id.nextbutton).setVisibility(View.INVISIBLE);
            } else {
                if(et.getText().toString().trim().equals("")){
                    Toast.makeText(this, "The answer is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    findViewById(R.id.tvCorrect).setVisibility(View.VISIBLE);
                    findViewById(R.id.nextbutton).setVisibility(View.VISIBLE);

                    /*done = true;*/
                }
            }

            String answer = ((EditText) findViewById(R.id.tvUserAnswer)).getText().toString();
            String[] answers = getResources().getStringArray(R.array.Answers);
            String correctanswer = answers[count-1];
            //gets the answer and correct answer from the edit text and strings.xml respectively
            correctanswer = correctanswer.toUpperCase();
            answer = answer.toUpperCase();

            answer.equalsIgnoreCase(answer);
            //makes sure that the strings are lower case


            if (answer.equals(correctanswer) && cheated == false)
            {
                TextView t = (TextView) findViewById(R.id.tvCorrect);
                t.setText("CORRECT!");
                answersubmitted();
                score ++;

            } else if(answer.equals(correctanswer))
                     {
                         TextView t = (TextView) findViewById(R.id.tvCorrect);
                         t.setText("CORRECT!");
                         answersubmitted();
            } else {
                TextView t = (TextView) findViewById(R.id.tvCorrect);
                t.setText("CORRECT ANSWER: " + correctanswer);
                answersubmitted();
            }

    }
    public void finishGame()
    {
        Intent showScore = new Intent(this, ScoreActivity.class);
        startActivity(showScore);
    }

}
