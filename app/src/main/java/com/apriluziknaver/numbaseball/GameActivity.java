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
    boolean isRnd=true;
    int[] com = new int[3];
    int strike;
    int ball;
    int roundCnt;

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

        makeNumber();



    }

    public void makeNumber(){

        while (isRnd){

            for(int i=0;i<com.length;i++){

                com[i]=(int)(Math.random()*10);
            }
            if (com[0]!=com[1]&&com[1]!=com[2]&&com[0]!=com[2]){

                isRnd=false;
            }


        }

        Log.d("hiting","com:"+com[0]+""+com[1]+""+com[2]+"");

    }

    boolean isOut=false;

    public void hitNumbers(){
        isRnd=true;

            int st=0;
            int ba=0;


            for(int i=0;i<com.length;i++){
                for(int j=0;j<user.length;j++){

                    if(com[i]==user[j]&&i==j){
                        st++;


                    }else if(com[i]==user[j]&&i!=j){
                        ba++;


                    }else if(com[i]!=user[j]){
                        isOut=true;

                    }

                }
            }

            if(strike==3){
                isOut=false;
                Log.d("hiting","End");
            }

            roundCnt++;
            Log.d("hiting",st+" S/"+ba+" B/"+isOut+" OUT/"+roundCnt+" round");





    }


    public void viewNumbers(int img,int num) {
        boolean isNum = false;
       while(!isNum){

           for(int i =0;i<user.length;i++){

               user[i]=num;

               for(int j= 0;j<i;j++){

                   
               }

           }


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
