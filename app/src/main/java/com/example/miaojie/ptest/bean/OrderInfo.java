package com.example.miaojie.ptest.bean;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/5/21.
 */

public class OrderInfo {
    private UserInfo userInfo;
    private String movieName;
    private String cinemaName;
    private String orderId;
    private int ticketNumber;
    private float price;
    private ArrayList<SeatInfo>seatInfos;
    private String startTime;
    private String endTime;
    private String bornTime;

    public String getBornTime() {
        return bornTime;
    }

    public void setBornTime(String bornTime) {
        this.bornTime = bornTime;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return movieName+"--"+cinemaName+"--"+orderId+"--"+ticketNumber+"--"+price+"--"+seatInfos.size()+"--"+startTime+"--"+endTime+"--"+bornTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public ArrayList<SeatInfo> getSeatInfos() {
        return seatInfos;
    }

    public void setSeatInfos(ArrayList<SeatInfo> seatInfos) {
        this.seatInfos = seatInfos;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(int ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
