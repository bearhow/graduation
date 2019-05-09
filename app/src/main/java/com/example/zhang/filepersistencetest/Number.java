package com.example.zhang.filepersistencetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Number extends AppCompatActivity implements View.OnClickListener{
    private EditText name;
    private EditText MAC;
    private Button btn;
    private String meetingName;
    private String count="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number);

        final Intent intent = getIntent();
        meetingName=intent.getStringExtra("meeting_name");

        name=(EditText)findViewById(R.id.number_name);
        MAC=(EditText)findViewById(R.id.number_mac);
        btn=(Button)findViewById(R.id.resetpwd_btn_sure);
        btn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.resetpwd_btn_sure){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    count=DBOpenHelper.insertNumber(meetingName,name.getText().toString(),MAC.getText().toString());
                }
            }).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(count.equals("success")){
                showNormalDialog("增加参会人员成功！！！");
            }else{
                showNormalDialog("增加参会人员失败！！！");
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
                new AlertDialog.Builder(Number.this);

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
