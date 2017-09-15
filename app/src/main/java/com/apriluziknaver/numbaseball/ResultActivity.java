package com.apriluziknaver.numbaseball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ResultActivity extends AppCompatActivity {

    ImageView img;
    TextView name;

    TextView resultView;

    Intent reIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        img = (ImageView)findViewById(R.id.img);
        name = (TextView)findViewById(R.id.name);
        resultView = (TextView)findViewById(R.id.result);

        reIntent=getIntent();

        resultView.setText(reIntent.getStringExtra("Result"));
    }

    public void clickButton(View v){

        switch (v.getId()){

            case R.id.backBtn:

//                Intent reintent = getIntent();
//                setResult(RESULT_OK,reintent);

                finish();

                break;

            case R.id.okBtn:

                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);

                break;

        }

    }
}
