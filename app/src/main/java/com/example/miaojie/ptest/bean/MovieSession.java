package com.example.miaojie.ptest.bean;

/**
 * Created by miaojie on 2017/5/20.
 */

public class MovieSession {
    private String startTime;
    private String endTime;
    private String studioId;

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStudioId() {
        return studioId;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStudioId(String studioId) {
        this.studioId = studioId;
    }
}
