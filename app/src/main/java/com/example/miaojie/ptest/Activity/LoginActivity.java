package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.BBS;
import com.example.BridgeNative;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.bean.UserInfo;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/5/22.
 */

public class LoginActivity extends Activity {
    private EditText userName;
    private EditText passWord;
    private Button loginButton;
    private Button registerButton;
    private ArrayList<UserInfo>userInfos;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        userName= (EditText) findViewById(R.id.login_userName);
        passWord= (EditText) findViewById(R.id.login_passWord);
        loginButton= (Button) findViewById(R.id.login_login);
        registerButton= (Button) findViewById(R.id.login_register);

        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what)
                {
                    case 1:
                        Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                        passWord.setText("");
                        break;
                    case 2:
                        MainActivity.isLogin=true;
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("userInfo",(UserInfo)msg.obj);
                        Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        break;
                }
            }
        };

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        Message message=new Message();
                        String name=null;

                            name=  BBS.login(userName.getText().toString(),passWord.getText().toString())+"";

//                if(userName.getText().toString().equals(userInfos.get(i).getUserName())&&
//                            passWord.getText().toString().equals(userInfos.get(i).getUserPassWord()))
                        if(name==null||name.equals(""))
                        {
                            message.what=1;
                            handler.sendMessage(message);
                            return;
                        }
                        UserInfo currentUserInfo=new UserInfo();
                        currentUserInfo.setUserName(userName.getText().toString());
                        currentUserInfo.setUserNickName(name);
                        currentUserInfo.setUserPassWord(passWord.getText().toString());
                        message.what=2;
                        message.obj=currentUserInfo;
                    }
                }.start();

            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}
