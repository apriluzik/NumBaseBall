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
            intent.putExtra("Name",name);


            Toast.makeText(SignUpActivity.this, name+"", Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(SignUpActivity.this).setTitle("title").setMessage("").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("signup","확인"+name);
                    setResult(RESULT_OK,intent);
                    finish();
                }
            }).setNegativeButton("취소", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Log.d("signup","취소");
                }
            }).show();

        }

    };

}
