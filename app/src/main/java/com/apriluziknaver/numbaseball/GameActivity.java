package com.apriluziknaver.numbaseball;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {


    ArrayList<StateItem> states = new ArrayList<>();
    TextView myName;
    TextView someoneName;
    ImageView myImg;
    ImageView someoneImg;
    RecyclerView myRecycler;
    RecyclerView someoneRecycler;
    StateAdapter stateAdapter;
    TextView countRound;
    ImageView firstN;
    ImageView secondN;
    ImageView thirdN;
    ImageView timeBar;

    boolean isFirst = false;
    boolean isSecond = false;
    boolean isThird = false;

    Random rnd;
    int[] com = new int[3];
//    int comF;
//    int comS;
//    int comT;

    int[] user = new int[3];

    int f;
    int s;
    int t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myName = (TextView) findViewById(R.id.my_name);
        myImg = (ImageView) findViewById(R.id.my_img);
        myRecycler = (RecyclerView) findViewById(R.id.my_recycler);

        someoneName = (TextView) findViewById(R.id.someone_name);
        someoneImg = (ImageView) findViewById(R.id.someone_img);
        someoneRecycler = (RecyclerView) findViewById(R.id.someone_recycler);

        countRound = (TextView) findViewById(R.id.count);

        firstN = (ImageView) findViewById(R.id.first);
        secondN = (ImageView) findViewById(R.id.second);
        thirdN = (ImageView) findViewById(R.id.third);

//        timeBar = (ImageView)findViewById(R.id.time_bar);
//        editNum = (EditText)findViewById(R.id.edit_number)



    }
    public void makeRndNum(){

        rnd = new Random();



    }

    public String hitNumbers(){



        return null;
    }


    public void viewNumbers(int img,int num) {

        if(!isFirst){

            isFirst=true;
            firstN.setImageResource(img);
            user[0]=num;
            Log.d("viewNumbers",user[0]+"");


        }else if(!isSecond){

            if(f==num) return;

            isSecond=true;
            secondN.setImageResource(img);
            user[1]=num;
            Log.d("viewNumbers",user[1]+"");

        }else if(!isThird){

            if(s==num) return;

            isThird=true;
            thirdN.setImageResource(img);
            user[2]=num;
            Log.d("viewNumbers",user[2]+"");
        }



    }

    public void clickNumbers(View v) {

        int id = v.getId();

        switch (id) {

            case R.id.num_00:

                viewNumbers(R.drawable.num_00,0);
                break;
            case R.id.num_01:
                viewNumbers(R.drawable.num_01,1);
                break;
            case R.id.num_02:
                viewNumbers(R.drawable.num_02,2);
                break;
            case R.id.num_03:
                viewNumbers(R.drawable.num_03,3);
                break;
            case R.id.num_04:
                viewNumbers(R.drawable.num_04,4);
                break;
            case R.id.num_05:
                viewNumbers(R.drawable.num_05,5);
                break;
            case R.id.num_06:
                viewNumbers(R.drawable.num_06,6);
                break;
            case R.id.num_07:
                viewNumbers(R.drawable.num_07,7);
                break;
            case R.id.num_08:
                viewNumbers(R.drawable.num_08,8);
                break;
            case R.id.num_09:
                viewNumbers(R.drawable.num_09,9);
                break;

        }
    }


    public void clickButton(View v) {


        switch (v.getId()) {

            case R.id.submit_numbers:
                hitNumbers();
                break;

            case R.id.delete_numbers:

                break;

        }


    }

}
