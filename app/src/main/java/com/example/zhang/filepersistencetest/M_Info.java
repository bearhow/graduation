package com.example.zhang.filepersistencetest;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class M_Info extends AppCompatActivity implements View.OnClickListener{
    Button btn1, btn2, btn3, btn4, btn5;
    EditText meeting_name;
    private String count="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m__info);
        meeting_name = (EditText) findViewById(R.id.meeting_name);
        btn1 = (Button) findViewById(R.id.select);
        btn2 = (Button) findViewById(R.id.update);
        btn3 = (Button) findViewById(R.id.delect);
        btn4 = (Button) findViewById(R.id.insert);
        btn5 = (Button) findViewById(R.id.insert_number);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        /**
         * 查询会议人员
         */
        if(v.getId()==R.id.select){
            Intent intent;
            intent = new Intent(M_Info.this, chaxun.class);
            String name= meeting_name.getText().toString();
            intent.putExtra("meeting_name", name);
            //String meeting_name = meeting_name.getText().toString();
            startActivity(intent);
        }
        /**
         * 修改会议
         */
        if(v.getId()==R.id.update){
            Intent intent;
            intent = new Intent(M_Info.this, xiugai.class);
            intent.putExtra("meetingName",meeting_name.getText().toString());
            startActivity(intent);
        }
        /**
         * 增加会议
         */
        if(v.getId()==R.id.insert){
            Intent intent2;
            intent2 = new Intent(M_Info.this, zengjia.class);
            startActivity(intent2);
        }
        /**
         * 删除会议
         */
        if(v.getId()==R.id.delect){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    count=DBOpenHelper.delectMeeting(meeting_name.getText().toString());
                }
            }).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count.equals("success")) {
                showNormalDialog("删除会议：" + meeting_name.getText().toString() + "成功！！！");
            } else {
                showNormalDialog("删除会议：" + meeting_name.getText().toString() + "失败！！！");
            }
        }


        if(v.getId()==R.id.insert_number){
            Intent intent;
            intent = new Intent(M_Info.this, Number.class);
            intent.putExtra("meeting_name", meeting_name.getText().toString());
            startActivity(intent);
        }
    }
    private void showNormalDialog(String name) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(M_Info.this);

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
