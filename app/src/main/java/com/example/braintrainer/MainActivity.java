package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button buttonStart,buttonEnd;
    Button button0,button1,button2,button3;
    TextView textViewScore,textViewTimer,textViewQuestion,textViewResult;
    int num1,num2,res,opt,quesNo,correct,t,i;
    ArrayList<Integer> answers = new ArrayList<Integer>();

    Random rand = new Random();

    void generate(){
        num1=rand.nextInt(1000);
        num2=rand.nextInt(1000);

        textViewQuestion.setText(Integer.toString(num1)+" + "+Integer.toString(num2));
        textViewScore.setText(Integer.toString(correct)+"/"+Integer.toString(quesNo));
        res=num1+num2;
        opt=rand.nextInt(4);
        answers.clear();
        for(i=0;i<4;i++){
            if(i==opt){
                answers.add(res);
            }
            else{
                t=rand.nextInt(2000);
                while(t==res){
                    t=rand.nextInt(2000);
                }
                answers.add(t);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view){
        buttonStart.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        buttonEnd.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        textViewResult.setText("");

        textViewTimer.setVisibility(View.VISIBLE);
        textViewQuestion.setVisibility(View.VISIBLE);
        textViewScore.setVisibility(View.VISIBLE);

        quesNo=0;
        correct=0;
        generate();
        quesNo++;
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                textViewTimer.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                textViewResult.setText("Time's Up");
                buttonEnd.setVisibility(View.VISIBLE);
                buttonEnd.setEnabled(true);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

            }
        }.start();

    }
    public void next(View view){
        String s =view.getTag().toString();
        int selected=Integer.parseInt(s);
        textViewResult.setVisibility(View.VISIBLE);
        if(selected==opt){
            correct++;
            textViewResult.setText("Correct :)");
        }
        else{
            textViewResult.setText("Wrong :(");

        }
        generate();
        quesNo++;


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonStart= findViewById(R.id.buttonStart);
        buttonEnd=findViewById(R.id.buttonEnd);
        button0= findViewById(R.id.button0);
        button1= findViewById(R.id.button1);
        button2= findViewById(R.id.button2);
        button3= findViewById(R.id.button3);

        textViewScore= findViewById(R.id.textViewScore);
        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewTimer=findViewById(R.id.textViewTimer);
        textViewResult=findViewById(R.id.textViewResult);

        buttonStart.setVisibility(View.VISIBLE);
        buttonEnd.setVisibility(View.INVISIBLE);
        button0.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);

        textViewTimer.setVisibility(View.INVISIBLE);
        textViewQuestion.setVisibility(View.INVISIBLE);
        textViewScore.setVisibility(View.INVISIBLE);
        textViewResult.setVisibility(View.INVISIBLE);



    }
}