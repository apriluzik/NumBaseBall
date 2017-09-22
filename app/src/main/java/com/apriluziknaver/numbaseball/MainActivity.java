package com.apriluziknaver.numbaseball;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
//SignUp 가서 로그인하고 다시 돌아오면 SignUp버튼 사라져있고 내정보보임.

    TextView tvname;
    CardView start;
    CardView login;
    String name;
    int lose;
    int win;
    boolean isResist;

    MyRecord myRecord;

    ArrayList<MyRecord> records=new ArrayList<>();
    SQLiteDatabase db;
    String tablename="record";
    Cursor cursor;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvname=(TextView)findViewById(R.id.name);
        start = (CardView) findViewById(R.id.start);
        login = (CardView) findViewById(R.id.login);
        preferences = getSharedPreferences("Record",MODE_PRIVATE);



//
//        openDB();
//
//        cursor = db.rawQuery("SELECT * FROM " + tablename, null);
//        while (cursor.moveToNext()) {
//
//            MyRecord record = new MyRecord();
//            record.name = cursor.getString(cursor.getColumnIndex("name"));
//            record.win += cursor.getInt(cursor.getColumnIndex("win"));
//            record.lose += cursor.getInt(cursor.getColumnIndex("lose"));
//            records.add(record);
//            myRecord=record;
//            Log.d("로그1",record.name+"/"+record.win+"/"+record.lose);
//        }


    }

    @Override
    protected void onResume() {
        super.onResume();

        name = preferences.getString("Name","");
        win = preferences.getInt("Win",0);
        lose = preferences.getInt("Lose",0);

        if(name.equals("")){
            tvname.setText("로그인이 필요합니다");
            start.setVisibility(View.GONE);
        }else{
            String msg=" 님 / 승 "+win+" 패 "+lose;
            tvname.setText(name+msg);
            start.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);
        }

    }

    public void StartBtn(View view){
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("Name",name);
        startActivity(intent);

    }

    public void Login(View view){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


            switch (requestCode){

                case 1000:
                    if(resultCode==RESULT_OK){
                        isResist=true;
//                        records.clear();
//                        name=data.getStringExtra("Name");
//                        win=data.getIntExtra("Win",0);
//                        lose=data.getIntExtra("Lose",0);
//
//                        tvname.setText(name+"!");
//                        start.setVisibility(View.VISIBLE);
//                        login.setVisibility(View.GONE);
//
//                        db.execSQL("INSERT INTO " + tablename + "(name,win,lose)" +
//                                "values('" + name +"',"+win+","+lose+")");
//
//                        cursor = db.rawQuery("SELECT * FROM " + tablename, null);
//
//                        while (cursor.moveToNext()) {
//                            MyRecord record = new MyRecord();

//                            record.name = cursor.getString(cursor.getColumnIndex("name"));
//                            record.win += cursor.getInt(cursor.getColumnIndex("win"));
//                            record.lose += cursor.getInt(cursor.getColumnIndex("lose"));
//                            records.add(record);
//
//                            Log.d("로그",record.name+"/"+record.win+"/"+record.lose);
//
//                        }
//
//
                        name = data.getStringExtra("Name");
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("Name",name);
                        editor.commit();
                        start.setVisibility(View.VISIBLE);
                        login.setVisibility(View.GONE);


                    }else if(resultCode==RESULT_CANCELED){

                        isResist=false;
                    }

                    break;

            }


    }

    public void openDB() {

        db = openOrCreateDatabase("data.db", MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS " + tablename + "("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name TEXT, "
                + "win INTEGER, "
                + "lose INTEGER, " +
                "round INTEGER )");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}