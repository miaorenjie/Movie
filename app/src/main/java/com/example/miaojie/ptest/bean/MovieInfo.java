package com.example.miaojie.ptest.bean;

import java.io.Serializable;

/**
 * Created by miaojie on 2017/3/17.
 */

public class MovieInfo implements Serializable{
    public String Name;
    public String Director;
    public String introduce;
    public int CoverId;
    public String Protagonist;
    public int DetailedCover;

    public int getDetailedCover() {
        return DetailedCover;
    }

    public void setDetailedCover(int detailedCover) {
        DetailedCover = detailedCover;
    }

    public String getProtagonist() {
        return Protagonist;
    }

    public void setProtagonist(String protagonist) {
        Protagonist = "主演："+protagonist;
    }

    public int getCoverId() {
        return CoverId;
    }

    public void setCoverId(int coverId) {
        CoverId = coverId;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getDirector() {

        return Director;
    }

    public void setDirector(String director) {
        Director = "导演："+director;
    }

    public String getName() {

        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
