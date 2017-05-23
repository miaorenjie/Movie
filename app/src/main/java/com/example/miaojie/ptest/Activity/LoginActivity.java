package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        userName= (EditText) findViewById(R.id.login_userName);
        passWord= (EditText) findViewById(R.id.login_passWord);
        loginButton= (Button) findViewById(R.id.login_login);
        registerButton= (Button) findViewById(R.id.login_register);
        userInfos=getUserInfos();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<userInfos.size();i++)
                {
                    if(userName.getText().toString().equals(userInfos.get(i).getUserName())&&
                            passWord.getText().toString().equals(userInfos.get(i).getUserPassWord()))
                    {
                        MainActivity.isLogin=true;
                        Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                        intent.putExtra("userInfo",userInfos.get(i));
                        startActivity(intent);
                    }
                }
            }
        });
    }
    private ArrayList<UserInfo>getUserInfos()
    {
        ArrayList<UserInfo>userInfos=new ArrayList<>();
        return userInfos;
    }
}
