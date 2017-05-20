package com.example.miaojie.ptest.bean;

/**
 * Created by miaojie on 2017/3/22.
 */

public class CinemaInfo {
    private String Cinema_Name;
    private String Cinema_Introduce;
    private String cinemaAdress;
    private String recentlySession;

    public String getCinemaAdress() {
        return cinemaAdress;
    }

    public void setCinemaAdress(String cinemaAdress) {
        this.cinemaAdress = cinemaAdress;
    }

    public String getRecentlySession() {
        return recentlySession;
    }

    public void setRecentlySession(String recentlySession) {
        this.recentlySession = recentlySession;
    }

    public String getCinema_Name() {

        return Cinema_Name;
    }

    public void setCinema_Name(String cinema_Name) {
        Cinema_Name = cinema_Name;
    }

    public String getCinema_Introduce() {
        return Cinema_Introduce;
    }

    public void setCinema_Introduce(String cinema_Introduce) {
        Cinema_Introduce = cinema_Introduce;
    }
}
