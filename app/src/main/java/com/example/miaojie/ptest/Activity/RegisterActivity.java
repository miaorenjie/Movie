package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.BBS;
import com.example.BridgeNative;
import com.example.miaojie.ptest.R;
import com.example.miaojie.ptest.bean.UserInfo;

/**
 * Created by miaojie on 2017/5/22.
 */

public class RegisterActivity extends Activity {
    private EditText userNickName;
    private EditText userName;
    private EditText passWord;
    private Button registerButton;
    private Button backButton;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        userName= (EditText) findViewById(R.id.registerUserName);
        userNickName= (EditText) findViewById(R.id.registerUserNickName);
        passWord= (EditText) findViewById(R.id.registerUserPassWord);
        registerButton= (Button) findViewById(R.id.registerRegister);
        backButton= (Button) findViewById(R.id.registerBack);
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what)
                {
                    case 1:
                        Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                        Log.e("asd","注册成功");
                        RegisterActivity.this.finish();
                        break;
                    case 2:

                        break;
                }
            }
        };
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.this.finish();
            }
        });
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String stringUserName=userName.getText().toString();
                String stringPassWord=passWord.getText().toString();
                String stringNickName=userNickName.getText().toString();
//                BBS.register()
                if(stringNickName.equals("")||stringPassWord.equals("")||stringUserName.equals(""))
                {
                    Toast.makeText(RegisterActivity.this,"三者不能为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                new Thread()
                {
                    @Override
                    public void run() {
                        super.run();
                        Message message=new Message();

                            BBS.register(stringUserName,stringPassWord,stringNickName);
                            message.what=1;
                            handler.sendMessage(message);


                    }
                }.start();

            }
        });
    }
}
