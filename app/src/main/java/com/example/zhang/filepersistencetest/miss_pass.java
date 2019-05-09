package com.example.zhang.filepersistencetest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
public class miss_pass extends AppCompatActivity implements View.OnClickListener{
    private EditText mMac;                        //用户名编辑

    private EditText mPwd_new;                            //密码编辑
    private EditText mPwdCheck;                       //密码编辑
    private Button mSureButton;                       //确定按钮
    private Button mCancelButton;                     //取消按钮

    private String count="" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.miss_pass);
//        layout.setOrientation(RelativeLayout.VERTICAL).
        mMac= (EditText) findViewById(R.id.mac);
        //mPwd_old = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPwd_new = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
        mPwdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_check);

        mSureButton = (Button) findViewById(R.id.resetpwd_btn_sure);
        mSureButton.setOnClickListener(this);
        mCancelButton = (Button) findViewById(R.id.resetpwd_btn_cancel);
        mCancelButton.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.resetpwd_btn_sure){
            String ps=mPwd_new.getText().toString();
            String ps1=mPwdCheck.getText().toString();
            if(mPwd_new.getText().toString().equals(mPwdCheck.getText().toString())) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        count  = DBOpenHelper.updatePassword(mMac.getText().toString(), mPwd_new.getText().toString());
                    }
                }).start();

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (count.equals("success")) {
                    showNormalDialog("修改密码成功！！！");
                    Intent intent=new Intent(miss_pass.this,LoginActivity.class);
                    startActivity(intent);
                } else {
                    showNormalDialog("修改密码失败！！！");
                    Intent intent=new Intent(miss_pass.this,miss_pass.class);
                    startActivity(intent);
                }
            }else{
                showNormalDialog("两次密码输入不一致");
                Intent intent=new Intent(miss_pass.this,miss_pass.class);
                startActivity(intent);
            }
        }
        if(v.getId()==R.id.register_btn_cancel){

        }
    }

    private void showNormalDialog(String name){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(miss_pass.this);

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
