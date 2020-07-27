package com.sudipasarkar.bday_quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private Button button_next;
    private View view1;
    private ColorStateList textColorDefaultRb;
    private List<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;
    public int score;
    public int scorecp;
    private int count1,count2=0;
    private boolean answered;
    public int[] imagear={R.drawable.pallab,R.drawable.sumit,R.drawable.prat_row,R.drawable.prat_row,
            R.drawable.souro,R.drawable.anjali1,R.drawable.avi,R.drawable.adi,R.drawable.me};

    public int[] image={R.drawable.debu2,R.drawable.debu4,
            R.drawable.debu3,R.drawable.debu6,
    R.drawable.debu7,R.drawable.debu8,R.drawable.debu9,R.drawable.debu10,R.drawable.debu11}
          ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        view1 = findViewById(R.id.rela_lay);
        textViewQuestion = findViewById(R.id.view_question);
        textViewScore = findViewById(R.id.view_score);
        textViewQuestionCount  = findViewById(R.id.view_ques);
        rbGroup=findViewById(R.id.radio_group);
        rb1=findViewById(R.id.radio_button1);
        rb2=findViewById(R.id.radio_button2);
        rb3=findViewById(R.id.radio_button3);
        rb4=findViewById(R.id.radio_button4);
        button_next=findViewById(R.id.confirm_button);
        textColorDefaultRb = rb1.getTextColors();
        QuizDbHelper dbHelper= new QuizDbHelper(this);
        questionList= dbHelper.getAllQuestions();
        questionCountTotal=questionList.size();
        //Collections.shuffle(questionList);

        showNextQuestion();

        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered)
                {
                    if(rb1.isChecked() || rb2.isChecked() || rb3.isChecked() || rb4.isChecked())
                    {
                        checkAnswer();
                    }
                    else
                    {
                        Toast.makeText(QuizActivity.this,"Debanjan Please Don't skip answer",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    showNextQuestion();
                }
            }
        });
    }
    private void showNextQuestion()
    {
        view1.setBackgroundResource(image[count1++]);
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rb4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();
        if(questionCounter<=questionCountTotal)
        {
            currentQuestion=questionList.get(questionCounter);
            textViewQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());
            rb4.setText(currentQuestion.getOption4());
            questionCounter++;
            textViewQuestionCount.setText("Guess" + questionCounter + "/"+ questionCountTotal);
            answered=false;
            button_next.setText("Confirm");
        }
       /*else
        {
            finishQuiz();
        }*/
    }
    private void checkAnswer()
    {
        answered=true;
        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answer= rbGroup.indexOfChild(rbSelected) + 1;
        if(answer== currentQuestion.getAns())
        {
            score++;
            scorecp=score;
            textViewScore.setText("How well you know your Friends: " + score);
        }
        showSolution();
    }
    private void showSolution()
    {

        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        rb4.setTextColor(Color.RED);
        view1.setBackgroundResource(imagear[count2++]);
        //currentQuestion=questionList.get(questionCounter);
        switch (currentQuestion.getAns())
        {
            case 1:
                rb1.setTextColor(Color.GREEN);
                textViewQuestion.setText(currentQuestion.getOption1()+" --"+currentQuestion.getQuestion());
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText(currentQuestion.getOption2()+" --"+currentQuestion.getQuestion());
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                textViewQuestion.setText(currentQuestion.getOption3()+" --"+currentQuestion.getQuestion());
                break;
            case 4:
                rb4.setTextColor(Color.GREEN);
                textViewQuestion.setText(currentQuestion.getOption4()+" --"+currentQuestion.getQuestion());
                break;
        }
        if(questionCounter<questionCountTotal)
        {
            button_next.setText("Next");
        }
        else
        {
            button_next.setText("Finish");
            finishQuiz();
        }
    }
    private void finishQuiz()
    {
        Button buttonStartQuiz1=findViewById(R.id.confirm_button);
        buttonStartQuiz1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuizActivity.this,EndActivity.class);

                startActivity(intent);
            }
        });
    }
}

