package com.apriluziknaver.numbaseball;

import android.content.Intent;
import android.inputmethodservice.Keyboard;
import android.inputmethodservice.KeyboardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;

import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
//SignUp 가서 로그인하고 다시 돌아오면 SignUp버튼 사라져있고 내정보보임.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    public void StartBtn(View view){
        Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);


    }

    public void SignUp(View view){
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivityForResult(intent,1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



    }
}