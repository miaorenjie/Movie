package com.example.miaojie.ptest.bean;

import java.util.ArrayList;

/**
 * Created by miaojie on 2017/5/21.
 */

public class OrderInfo {
    private String userName;
    private String movieName;
    private String cinemaName;
    private int orderId;
    private String ticketNumber;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
