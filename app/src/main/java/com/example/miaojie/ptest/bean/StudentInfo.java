package com.example.miaojie.ptest.bean;

import java.io.Serializable;

/**
 * Created by miaojie on 2017/3/31.
 */

public class StudentInfo implements Serializable {
    private String StudentNum;
    private String StudentName;
    private String StudentClass;
    private String StudentSex;
    private String StudentMessage;
    private String StudentMail;
    private String StudentTel;
    private String StudentGroup;
    private String StudentState="未面试";

    public String getStudentState() {
        return StudentState;
    }

    public void setStudentState(String studentState) {
        StudentState = studentState;
    }

    public String getStudentNum() {
        return StudentNum;
    }

    public void setStudentNum(String studentNum) {
        StudentNum = studentNum;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getStudentSex() {
        return StudentSex;
    }

    public void setStudentSex(String studentSex) {
        StudentSex = studentSex;
    }

    public String getStudentClass() {
        return StudentClass;
    }

    public void setStudentClass(String studentClass) {
        StudentClass = studentClass;
    }

    public String getStudentMessage() {
        return StudentMessage;
    }

    public void setStudentMessage(String studentMessage) {
        StudentMessage = studentMessage;
    }

    public String getStudentTel() {
        return StudentTel;
    }

    public void setStudentTel(String studentTel) {
        StudentTel = studentTel;
    }

    public String getStudentMail() {
        return StudentMail;
    }

    public void setStudentMail(String studentMail) {
        StudentMail = studentMail;
    }

    public String getStudentGroup() {
        return StudentGroup;
    }

    public void setStudentGroup(String studentGroup) {
        StudentGroup = studentGroup;
    }
}
