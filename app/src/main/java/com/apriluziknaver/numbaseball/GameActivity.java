package com.apriluziknaver.numbaseball;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
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
    Typeface typeface;

    Intent reIntent;
    ArrayList<StateItem> states;
    ArrayList<Integer> user = new ArrayList<>();



    LinearLayoutManager manager;
    StringBuffer buffer;
    TextView myName;

    ImageView myImg;
    RecyclerView myRecycler;

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
    int roundCnt ;

    boolean isRnd = true;
    int[] com = new int[3];

    int strike;
    int ball;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        typeface = Typeface.createFromAsset(getAssets(),"fonts/koverwatch.ttf");

        myName = (TextView) findViewById(R.id.my_name);
        myName.setText(getIntent().getStringExtra("Name")+" 님");
        myName.setTypeface(typeface);
//        myImg = (ImageView) findViewById(R.id.my_img);
        myRecycler = (RecyclerView) findViewById(R.id.my_recycler);

        countRound = (TextView) findViewById(R.id.count);
        countRound.setTypeface(typeface);


        firstN = (ImageView) findViewById(R.id.first);
        secondN = (ImageView) findViewById(R.id.second);
        thirdN = (ImageView) findViewById(R.id.third);

        initState();

        initViewNum();



    }

    public void initViewNum() {

        firstN.setImageResource(R.drawable.num_10);
        secondN.setImageResource(R.drawable.num_10);
        thirdN.setImageResource(R.drawable.num_10);
    }

    public void makeNumber() {
        int[] c = new int[3];

        isRnd=true;

        while (isRnd) {

            for (int i = 0; i < c.length; i++) {

                if (i == 0) {
                    c[i] = (int) (Math.random() * 9) + 1;
                } else {
                    c[i] = (int) (Math.random() * 10);
                }
            }
            if (c[0] != c[1] && c[1] != c[2] && c[0] != c[2]) {

                isRnd = false;
            }


        }
        com = c;
        Log.d("result", "com:" + com[0] + "" + com[1] + "" + com[2] + "");
        Log.d("result", "c:" + c[0] + "" + c[1] + "" + c[2] + "");

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
            buffer.append(user.get(i));

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

//        if (isHit) {
//            initViewNum();
//            user.clear();
//            isFirst = false;
//            isSecond = false;
//            isThird = false;
//
//        }
//        showStates();
        return;

    }

    public void clickNumbers(View v) {
        v.getTag();
        Log.d("Tagg", v.getTag().toString());

        int num = Integer.parseInt(v.getTag().toString());
        int img = R.drawable.num_00;

        viewNumbers(img + num, num);


    }

    //showStates()
    public void clickButton(View v) {

        switch (v.getId()) {

            case R.id.submit_numbers:

                if(user.size()==3){
                    hitNumbers();

                }else {
                    Toast.makeText(this,"숫자를 입력해 주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (isHit) {
                    initViewNum();
                    user.clear();
                    isFirst = false;
                    isSecond = false;
                    isThird = false;

                }
                roundCnt++;
                countRound.setText(roundCnt+1+"");
                showStates();
                break;

            case R.id.delete_numbers:
                deleteNumbers();
                break;

        }


    }

    //Result Activity
    public void showStates() {

        states.add(new StateItem(buffer.toString(), strike+ "S", ball + "B"));
        stateAdapter.notifyDataSetChanged();
        Log.d("쇼스탯",buffer.toString()+strike+ "S"+ ball + "B");

       if(strike==3 || roundCnt==9) {
           Intent intent = new Intent(this,ResultActivity.class);
           intent.putExtra("Round", roundCnt);
           intent.putExtra("Name",myName.getText());
           intent.putExtra("Result", strike == 3 ? "win" : "lose");

           startActivity(intent);
           finish();
       }

    }



    public void initState(){
        roundCnt=0;
        states =  new ArrayList<>();
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        stateAdapter = new StateAdapter(states, this);
        myRecycler.setLayoutManager(manager);
        myRecycler.setAdapter(stateAdapter);

        countRound.setText(roundCnt+1+"");
        stateAdapter.notifyDataSetChanged();

        makeNumber();

    }
}
