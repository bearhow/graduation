package com.example.zhang.filepersistencetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class guanli extends AppCompatActivity {
    private EditText mMiyao;                        //用户名编辑
    private Button mSure;
    private Button mGoBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guanli);
        mSure=(Button)findViewById(R.id.sure);
        mGoBack=(Button)findViewById(R.id.go_back1) ;
        mMiyao= (EditText) findViewById(R.id.miyao);
        mSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Miyao = mMiyao.getText().toString();
                if (Miyao.equals("123456") ) {
                    Intent intent=new Intent(guanli.this,M_Info.class);
                    startActivity(intent);
                } else {

                   // Toast.makeText(this,"您输入的密钥不正确", Toast.LENGTH_SHORT).show();

                }
            }

            });
        mGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(guanli.this,PersonActivity.class);
                startActivity(intent);
//                startActivityForResult(intent, REQUEST_CODE_GO_TO_REGIST);
            }
        });


    }
}
