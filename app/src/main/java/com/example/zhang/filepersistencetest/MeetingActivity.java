package com.example.zhang.filepersistencetest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MeetingActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1,btn2;
    private EditText name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting);


        name=(EditText)findViewById(R.id.uname);
        btn1=(Button)findViewById(R.id.chaxun1);
        btn1.setOnClickListener(this);
        btn2=(Button)findViewById(R.id.go_back1);
        btn2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.chaxun1){
            Intent intent= new Intent(MeetingActivity.this,attendance.class);
            intent.putExtra("people_name",name.getText().toString());
            startActivity(intent);
        }
        if(v.getId()==R.id.go_back1){
            Intent intent= new Intent(MeetingActivity.this,denglu.class);
            startActivity(intent);
        }
    }
}


