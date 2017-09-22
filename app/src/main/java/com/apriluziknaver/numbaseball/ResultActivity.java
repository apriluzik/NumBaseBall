package com.apriluziknaver.numbaseball;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    ImageView img;
    TextView name;
    TextView resultView;
    Intent reIntent;
    TextView score;
    int round;
    int win;
    int lose;
    String record;


    ArrayList<MyRecord> myRecords;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        img = (ImageView)findViewById(R.id.img);
        name = (TextView)findViewById(R.id.name);
        resultView = (TextView)findViewById(R.id.result);
        score = (TextView)findViewById(R.id.score);

        reIntent=getIntent();

        round = reIntent.getIntExtra("Round",0);
        record= reIntent.getStringExtra("Result");

        resultView.setText("You"+record);

        resultSave();


    }

    public void clickButton(View v){

        switch (v.getId()){

            case R.id.backBtn:

//                Intent reintent = getIntent();
//                setResult(RESULT_OK,reintent);

                finish();
                Intent intent=new Intent(this,GameActivity.class);

                startActivity(intent);

                break;

            case R.id.okBtn:

                finish();

                break;

        }

    }

    public void resultSave(){
        SharedPreferences preferences = getSharedPreferences("Record",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if(record.equals("win")){
            score.setText("플레이한 라운드: "+round);

            win = preferences.getInt("Win",0);
            win++;
            editor.remove("Win");
            editor.commit();

            editor.putInt("Win",win);
            editor.commit();
            Log.d("리절트","WIN:"+win+"");

        }else{


            lose = preferences.getInt("Lose",0);
            lose++;
            editor.remove("Lose");
            editor.commit();

            editor.putInt("Lose",lose);
            editor.commit();
            Log.d("리절트","LOSE:"+lose+"");

        }
    }

}
