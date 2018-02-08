package com.itktechnologies.braintrainer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        TextView txt_sum = (TextView) findViewById(R.id.txt_sum);
        txt_sum.setText(String.format("%d + %d", a, b));
    }



    public void cmdGo (View v) {
        Button cmdGo = (Button) findViewById(R.id.cmdGo);
        cmdGo.setVisibility(View.INVISIBLE);
    }

    public void cmdAnswer (View v) {
        //Button cmdAnswer = (Button) findViewById(R.id);
        //cmdGo.setVisibility(View.INVISIBLE);
    }

}
