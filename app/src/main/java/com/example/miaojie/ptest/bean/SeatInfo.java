package com.example.miaojie.ptest.bean;

/**
 * Created by miaojie on 2017/5/21.
 */

public class SeatInfo {
    private int seatX;
    private int seatY;

    public SeatInfo(int seatX, int seatY) {
        this.seatX = seatX;
        this.seatY = seatY;
    }

    public int getSeatX() {
        return seatX;
    }

    public void setSeatX(int seatX) {
        this.seatX = seatX;
    }

    public int getSeatY() {
        return seatY;
    }

    public void setSeatY(int seatY) {
        this.seatY = seatY;
    }
}
