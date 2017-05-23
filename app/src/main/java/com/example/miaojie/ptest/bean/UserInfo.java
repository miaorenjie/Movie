package com.example.miaojie.ptest.bean;

import java.io.Serializable;

/**
 * Created by miaojie on 2017/5/22.
 */

public class UserInfo implements Serializable{
    private String userNickName;
    private String userName;
    private String userPassWord;

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassWord() {
        return userPassWord;
    }

    public void setUserPassWord(String userPassWord) {
        this.userPassWord = userPassWord;
    }
}
