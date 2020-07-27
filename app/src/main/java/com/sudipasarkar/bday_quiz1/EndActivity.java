package com.sudipasarkar.bday_quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {
   //public TextView instrut;
   // private TextView score1;
  //  public String s;
   // public int s1;
    MediaPlayer pal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);
        Button play=findViewById(R.id.click);
        pal = MediaPlayer.create(this,R.raw.pal);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pal.start();

            }

        });
         // QuizActivity ob1 = new QuizActivity();
        /*instrut=findViewById(R.id.intrust);
        score1= (TextView) findViewById(R.id.view_score);
         s = score1.getText().toString();
         System.out.println(s);
         s1= Integer.valueOf(s);
        if(s1>15)
        {
          instrut.setText("You won a movie date...I will plan the day");
        }
        else if (s1<15 && s1>=10)
        {
            instrut.setText("You won a street food and hopping around the streets of calcutta date");
        }
        else
        {
          instrut.setText("You lost...bye!");
        }*/
    }
 protected void onPause()
 {
     super.onPause();
     pal.release();
 }
}
