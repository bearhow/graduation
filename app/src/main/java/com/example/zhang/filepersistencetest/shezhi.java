package com.example.zhang.filepersistencetest;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import util.RoundImageView;

import java.time.chrono.Chronology;
import java.util.List;

public class shezhi extends AppCompatActivity {

    private EditText mUmac;                        //用户名编辑
    private EditText mName;                            //密码编辑
    private EditText mMima;                       //密码编辑

    private Button mSureButton;                       //确定按钮
    private Button mBackButton;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        mSureButton = (Button) findViewById(R.id.save_person);
        mBackButton=(Button)findViewById(R.id.go_back);
        mUmac = (EditText) findViewById(R.id.umac);
        mName=(EditText)findViewById(R.id.name);
        mMima=(EditText)findViewById(R.id.mima);
        mSureButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String mac = mUmac.getText().toString();
            String name = mName.getText().toString();
            String mima = mMima.getText().toString();

            if(!name.equals("")&&!mac.equals("")&&!mima.equals("")){
                Intent intent=new Intent(shezhi.this,PersonActivity.class);
                startActivity(intent);
            }
        }
        });

        mBackButton.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View v){
                Intent intent = new Intent(shezhi.this, PersonActivity.class);
                startActivity(intent);
//
            }

    });
    }




}
