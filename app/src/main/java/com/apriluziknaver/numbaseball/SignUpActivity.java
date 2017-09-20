package com.apriluziknaver.numbaseball;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    ImageView loginBtn;
    EditText editname;
    String name;


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        intent=getIntent();



//        loginBtn.setOnClickListener(listener);



    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            name = editname.getText().toString();

            Toast.makeText(SignUpActivity.this, name+"", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK);
            intent.putExtra("Name",name);
            finish();
        }

    };

}
