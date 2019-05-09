package com.example.zhang.filepersistencetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class xiugai extends AppCompatActivity implements View.OnClickListener{

    private EditText date;
    private EditText add;
    private EditText hoster;
    private EditText number;
    private Button btn;
    private String meetingName="";
    private String count="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiugai);

        date =(EditText)findViewById(R.id.mdate);
        add =(EditText)findViewById(R.id.madd);
        hoster=(EditText)findViewById(R.id.mhost);
        number=(EditText)findViewById(R.id.number);
        btn=(Button)findViewById(R.id.sure1);
        btn.setOnClickListener(this);
        Intent intent=getIntent();
        meetingName=intent.getStringExtra("meetingName");


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.sure1){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    count=DBOpenHelper.updateMeeting(meetingName,date.getText().toString(),add.getText().toString(),hoster.getText().toString(),number.getText().toString());
                }
            }).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count.equals("success")){
                showNormalDialog("修改会议："+meetingName+"成功！！！");
            }else{
                showNormalDialog("修改会议："+meetingName+"失败！！！");
            }
        }
    }

    private void showNormalDialog(String name){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(xiugai.this);

        normalDialog.setTitle("提示");
        normalDialog.setMessage(name);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });

        // 显示
        normalDialog.show();
    }
}
