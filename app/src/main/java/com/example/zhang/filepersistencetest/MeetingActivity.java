package com.example.zhang.filepersistencetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MeetingActivity extends AppCompatActivity {
    Button btn1,btn2;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);
        final Intent intent = getIntent();
        final String people_name=intent.getStringExtra("people_name");

        name=(EditText)findViewById(R.id.uname);
        btn1=(Button)findViewById(R.id.chaxun1);
        btn2=(Button)findViewById(R.id.go_back1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1;
                intent1 = new Intent(MeetingActivity.this,attendance.class);
                startActivity(intent1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2;
                intent2 = new Intent(MeetingActivity.this,denglu.class);
                startActivity(intent2);
            }
        });
    }
}


