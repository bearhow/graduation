package com.example.zhang.filepersistencetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PersonActivity extends AppCompatActivity {
    Button btn1,btn2;
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        //mContext = this;
        //initView();
        btn1=(Button)findViewById(R.id.shezhi);
        btn2=(Button)findViewById(R.id.guanli);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1;
                intent1 = new Intent(PersonActivity.this,shezhi.class);
                startActivity(intent1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3;
                intent3 = new Intent(PersonActivity.this,guanli.class);
                startActivity(intent3);
            }
        });
    }
}
