package com.apriluziknaver.numbaseball;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
//SignUp 가서 로그인하고 다시 돌아오면 SignUp버튼 사라져있고 내정보보임.

    TextView tvname;
    Button start;
    Button login;
    String name;


    ArrayList<MyRecord> records=new ArrayList<>();
    SQLiteDatabase db;
    String tablename="record";
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvname=(TextView)findViewById(R.id.name);
        start = (Button) findViewById(R.id.start);
        login = (Button) findViewById(R.id.login);

        openDB();

        cursor = db.rawQuery("SELECT * FROM " + tablename, null);

        while (cursor.moveToNext()) {
            MyRecord record = new MyRecord();
            record.name = cursor.getString(cursor.getColumnIndex("name"));
        

        }



    }

    public void StartBtn(View view){
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);

    }

    public void Login(View view){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        start.setVisibility(View.VISIBLE);
        login.setVisibility(View.GONE);
        name = data.getStringExtra("Name");
        tvname.setText(name);

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

}