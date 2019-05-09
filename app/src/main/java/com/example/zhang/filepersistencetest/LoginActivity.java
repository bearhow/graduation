package com.example.zhang.filepersistencetest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountEdit;
    private EditText passwordEdit;
    private Button login;//登录按钮
    private CheckBox rememberPass;
    private static final int REQUEST_CODE_GO_TO_REGIST = 100;


    private EditText mAccount;                        //用户名编辑
    private EditText mPwd;                            //密码编辑
    private EditText mPwdCheck;                       //密码编辑
    private EditText mMac;
    private Button register;                       //注册按钮
    private Button logError;                    //忘记密码按钮


    private String status="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        logError=(Button)findViewById(R.id.login_error);
        register=(Button)findViewById(R.id.register) ;
        pref=PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = (EditText) findViewById(R.id.username_edit);
        passwordEdit = (EditText) findViewById(R.id.userpassword_edit);
        //rememberPass=(CheckBox) findViewById(R.id.remember_pass);
        login = (Button) findViewById(R.id.login_button);
        //boolean isRemember=pref.getBoolean("remember_password",false);
        final Intent intent = getIntent();
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        logError.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.login_button){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //lists= DBOpenHelper.selectNumber(intent.getStringExtra("id"));
                     status = DBOpenHelper.login(accountEdit.getText().toString(),passwordEdit.getText().toString());


                }
            }).start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(status.equals("success")){
                Intent intent1=new Intent(LoginActivity.this,denglu.class);
                startActivity(intent1);
            }else{
                showNormalDialog("用户名密码错误！！！");
            }
        }
        if(v.getId()==R.id.login_error){
            Intent intent2;
            intent2 = new Intent(LoginActivity.this,miss_pass.class);
            startActivity(intent2);
        }
        if(v.getId()==R.id.register){
            Intent intent=new Intent(LoginActivity.this,zhuce.class);
            startActivity(intent);
//                startActivityForResult(intent, REQUEST_CODE_GO_TO_REGIST);
        }
    }
    private void showNormalDialog(String name){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(LoginActivity.this);

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
