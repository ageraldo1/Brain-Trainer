package com.itktechnologies.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import static android.view.View.INVISIBLE;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Integer> answersList = new ArrayList<>();
    private int correctAnswer;
    private int positionCorrectAnswer;
    private int incorrectAnswer;
    private int totalCorrect;
    private int totalIncorrect;


    private void generateQuestion() {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        TextView txt_sum = (TextView) findViewById(R.id.txt_sum);
        //TextView txt_result = (TextView) findViewById(R.id.txt_result);

        txt_sum.setText(String.format("%d + %d", a, b));
        //txt_result.setText("");

        positionCorrectAnswer = rand.nextInt(4); //tag button
        correctAnswer = a + b;

        for ( int i = 0; i < 4; i++) {
            if ( i == positionCorrectAnswer ) {
                answersList.add (correctAnswer);

            } else {
                do {
                    incorrectAnswer = rand.nextInt(41);

                } while ( incorrectAnswer == correctAnswer);

                answersList.add(incorrectAnswer);
            }
        }

        Button cmdAnswer1 = (Button)findViewById(R.id.cmdAnswer1);
        Button cmdAnswer2 = (Button)findViewById(R.id.cmdAnswer2);
        Button cmdAnswer3 = (Button)findViewById(R.id.cmdAnswer3);
        Button cmdAnswer4 = (Button)findViewById(R.id.cmdAnswer4);

        cmdAnswer1.setText(answersList.get(0).toString());
        cmdAnswer2.setText(answersList.get(1).toString());
        cmdAnswer3.setText(answersList.get(2).toString());
        cmdAnswer4.setText(answersList.get(3).toString());

        answersList.clear();

    }

    private void initChallenge() {
        TextView txt_result = (TextView) findViewById(R.id.txt_result);
        TextView txt_score = (TextView) findViewById(R.id.txt_score);

        totalCorrect = 0;
        totalIncorrect = 0;

        txt_result.setTextSize(50);
        txt_result.setText("");

        txt_score.setText(totalCorrect + "/" + totalIncorrect);
        generateQuestion();

        new CountDownTimer(30100,1000) {

            @Override
            public void onTick(long l) {
                TextView txt_secs_left = (TextView) findViewById(R.id.txt_secs_left);

                int secondsLeft = (int)(l / 1000);

                txt_secs_left.setText(Integer.toString(secondsLeft) + "s");
            }

            @Override
            public void onFinish() {
                TextView txt_secs_left = (TextView) findViewById(R.id.txt_secs_left);
                TextView txt_result = (TextView) findViewById(R.id.txt_result);
                Button cmdGo = (Button) findViewById(R.id.cmdGo);


                txt_result.setTextSize(20);
                txt_result.setText("Your final score is : " + totalCorrect + "/" + totalIncorrect);
                txt_secs_left.setText("0s");

                cmdGo.setText("Play Again");
                cmdGo.setVisibility(View.VISIBLE);
                cmdGo.setTag("play_again");

            }
        }.start();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateQuestion();
    }


    public void cmdGo (View v) {
        /* Way 1
            Button cmdGo = (Button) findViewById(R.id.cmdGo);
            cmdGo.setVisibility(View.INVISIBLE);
        */
        v.setVisibility(INVISIBLE);
        initChallenge();

        //if ( v.getTag().equals("play_again")) {

        //}

    }

    public void cmdAnswer (View v) {
        TextView txt_result = (TextView) findViewById(R.id.txt_result);
        TextView txt_score = (TextView) findViewById(R.id.txt_score);

        if ( Integer.parseInt(v.getTag().toString()) == positionCorrectAnswer)  {
           txt_result.setText("Correct!");
           totalCorrect++;

        } else {
            txt_result.setText("Incorrect!");
            totalIncorrect++;
        }

        txt_score.setText(totalCorrect + "/" + totalIncorrect);

        generateQuestion();

    }

}
