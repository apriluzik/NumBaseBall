package com.apriluziknaver.numbaseball;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    Intent reIntent;

    ArrayList<StateItem> states = new ArrayList<>();
    StringBuffer buffer;
    TextView myName;

    ImageView myImg;
    RecyclerView myRecycler;

    TextView someoneName;
    ImageView someoneImg;
    RecyclerView someoneRecycler;
    StateAdapter stateAdapter;
    TextView countRound;

    ImageView firstN;
    ImageView secondN;
    ImageView thirdN;


    boolean isFirst = false;
    boolean isSecond = false;
    boolean isThird = false;
    boolean isOut = false;
    boolean isHit = false;
    int roundCnt = 1;

    boolean isRnd = true;
    int[] com = new int[3];

    int strike;
    int ball;
    StateItem stateItem;

    ArrayList<Integer> user = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        myName = (TextView) findViewById(R.id.my_name);
        myImg = (ImageView) findViewById(R.id.my_img);
        myRecycler = (RecyclerView) findViewById(R.id.my_recycler);

//        someoneName = (TextView) findViewById(R.id.someone_name);
//        someoneImg = (ImageView) findViewById(R.id.someone_img);
//        someoneRecycler = (RecyclerView) findViewById(R.id.someone_recycler);

        countRound = (TextView) findViewById(R.id.count);

        firstN = (ImageView) findViewById(R.id.first);
        secondN = (ImageView) findViewById(R.id.second);
        thirdN = (ImageView) findViewById(R.id.third);

        stateAdapter = new StateAdapter(states, this);
        myRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        myRecycler.setAdapter(stateAdapter);
        reIntent= new Intent(this,ResultActivity.class);

        initViewNum();
        makeNumber();




    }

    public void initViewNum() {

        firstN.setImageResource(R.drawable.num_10);
        secondN.setImageResource(R.drawable.num_10);
        thirdN.setImageResource(R.drawable.num_10);
    }

    public void makeNumber() {

        while (isRnd) {

            for (int i = 0; i < com.length; i++) {

                if (i == 0) {
                    com[i] = (int) (Math.random() * 9) + 1;
                } else {
                    com[i] = (int) (Math.random() * 10);
                }
            }
            if (com[0] != com[1] && com[1] != com[2] && com[0] != com[2]) {

                isRnd = false;
            }


        }

        Log.d("result", "com:" + com[0] + "" + com[1] + "" + com[2] + "");

    }

    public void deleteNumbers() {

        if (user.size() == 0) {
            return;
        } else if (user.size() == 1) {

            firstN.setImageResource(R.drawable.num_10);
            user.remove(0);
            isFirst = false;

        } else if (user.size() == 2) {

            secondN.setImageResource(R.drawable.num_10);
            user.remove(1);
            isSecond = false;

        } else if (user.size() == 3) {

            thirdN.setImageResource(R.drawable.num_10);
            user.remove(2);
            isThird = false;
        }


    }

    public void viewNumbers(int img, int num) {
//        boolean isNum = true;


        if (!isFirst) {

            user.add(num);
            firstN.setImageResource(img);
            isFirst = true;

        } else if (!isSecond) {

            if (user.get(0) != num) {

                user.add(num);
                secondN.setImageResource(img);
                isSecond = true;

            } else {
                return;
            }

        } else if (!isThird) {

            if (user.get(0) != num && user.get(1) != num) {

                user.add(num);
                thirdN.setImageResource(img);
                isThird = true;
            } else {
                return;
            }

        }


    }

    public void hitNumbers() {

        strike = 0;
        ball = 0;

        buffer = new StringBuffer();

        for (int i = 0; i < com.length; i++) {

            if (user.get(i) != 0) {
                buffer.append(user.get(i));
            }

            for (int j = 0; j < user.size(); j++) {

                if (com[i] == user.get(j) && i == j) {

                    strike++;

                    if (strike == 3) {
                        isOut = false;
                        Log.d("result", "3stkrike");
                        break;
                    }
                }
                if (com[i] == user.get(j) && i != j) {
                    ball++;
                }
                if (com[i] != user.get(j)) {
                    isOut = true;
                }

            }
            isHit = true;
        }


        Log.d("result", buffer + "\n" + strike + " S/ " + ball + " B/ " + isOut + " OUT/" + roundCnt + " round");

        if (isHit) {
            initViewNum();

            user.clear();
            isFirst = false;
            isSecond = false;
            isThird = false;


        }


        showStates();
        return;

    }

    public void clickNumbers(View v) {
        v.getTag();
        Log.d("Tagg", v.getTag().toString());

        int num = Integer.parseInt(v.getTag().toString());
        int img = R.drawable.num_00;

        viewNumbers(img + num, num);


    }

    public void clickButton(View v) {


        switch (v.getId()) {

            case R.id.submit_numbers:
                hitNumbers();
                roundCnt++;
                break;

            case R.id.delete_numbers:
                deleteNumbers();
                break;

        }


    }

    public void showStates() {

        states.add(new StateItem(buffer.toString(),strike + "S",ball + "B"));

//        if(strike==0){
//            states.add(new StateItem(buffer.toString(),strike + "s"));
//
//        }else if(ball==0){
//            states.add(new StateItem(buffer.toString(),ball + "B"));
//        }else{
//            states.add(new StateItem(buffer.toString(),strike + "S",ball + "B"));
//        }



        stateAdapter.notifyDataSetChanged();

        if(strike==3){
            Toast.makeText(this, "WINWIN", Toast.LENGTH_SHORT).show();


            reIntent.putExtra("Result","win");
            startActivity(reIntent);

        }else if(roundCnt==10){
            reIntent.putExtra("Result","lose");
            startActivity(reIntent);
        }

    }

}
