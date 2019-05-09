package com.example.zhang.filepersistencetest;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AlertDialog;
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

public class shezhi extends AppCompatActivity implements View.OnClickListener {


    private EditText mName;                            //密码编辑
    private EditText mMima;                       //密码编辑

    private Button mSureButton;                       //确定按钮
    private Button mBackButton;
    private String count = "";
    private String name;
    private String mima;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shezhi);
        mSureButton = (Button) findViewById(R.id.save_person);
        mSureButton.setOnClickListener(this);

        mBackButton = (Button) findViewById(R.id.go_back);
        mBackButton.setOnClickListener(this);

        mName = (EditText) findViewById(R.id.name);
        mMima = (EditText) findViewById(R.id.mima);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.save_person) {
            name = mName.getText().toString();
            name=name.substring(3,name.length());
            mima = mMima.getText().toString();
            mima=mima.substring(3,mima.length());
            final String mac = tryGetWifiMac(shezhi.this);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    count = DBOpenHelper.updateUserInfo(mac, name, mima);
                }
            }).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count.equals("success")) {
                showNormalDialog("设置成功！！");
            } else {
                showNormalDialog("设置失败！！");
            }


//            if(!name.equals("")&&!mac.equals("")&&!mima.equals("")){
//                Intent intent=new Intent(shezhi.this,PersonActivity.class);
//                startActivity(intent);
//            }
        }
        if (v.getId() == R.id.go_back) {
            Intent intent = new Intent(shezhi.this, PersonActivity.class);
            startActivity(intent);
        }
    }

    private static String tryGetWifiMac(Context context) {
        WifiManager wm = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo wi = wm.getConnectionInfo();
        if (wi == null || wi.getMacAddress() == null) {
            return null;
        }
        if ("02:00:00:00:00:00".equals(wi.getMacAddress().trim())) {
            return null;
        } else {
            return wi.getMacAddress().trim();
        }
    }

    private void showNormalDialog(String name) {
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(shezhi.this);

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
