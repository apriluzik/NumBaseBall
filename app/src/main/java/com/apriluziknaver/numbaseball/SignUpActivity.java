package com.apriluziknaver.numbaseball;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    TextView loginBtn;
    EditText editname;
    String name;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        intent=getIntent();

        editname=(EditText) findViewById(R.id.edit_name);
        loginBtn = (TextView) findViewById(R.id.loginBtn);


        loginBtn.setOnClickListener(listener);


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            name = editname.getText().toString();

            if (editname.getText().toString().equals("")) {

                new AlertDialog.Builder(SignUpActivity.this)
                        .setMessage("이름을 입력해주세요.")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();

            }else {

            new AlertDialog.Builder(SignUpActivity.this).setTitle("").setMessage(" ' "+name+" ' 으로 하시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    setResult(RESULT_OK, intent);
                    Log.d("signup", "확인" + name);
                    intent.putExtra("Name", name);
                    finish();

                }
            }).show();
        }

        }

    };



    @Override
    public void onBackPressed() {
        super.onBackPressed();

        setResult(RESULT_CANCELED, intent);
        finish();
    }
}
