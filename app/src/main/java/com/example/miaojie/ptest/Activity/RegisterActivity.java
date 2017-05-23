package com.example.miaojie.ptest.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.miaojie.ptest.R;

/**
 * Created by miaojie on 2017/5/22.
 */

public class RegisterActivity extends Activity {
    private EditText userNickName;
    private EditText userName;
    private EditText passWord;
    private Button registerButton;
    private Button backButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        userName= (EditText) findViewById(R.id.registerUserName);
        userNickName= (EditText) findViewById(R.id.registerUserNickName);
        passWord= (EditText) findViewById(R.id.registerUserPassWord);
        registerButton= (Button) findViewById(R.id.registerRegister);
        backButton= (Button) findViewById(R.id.registerBack);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
