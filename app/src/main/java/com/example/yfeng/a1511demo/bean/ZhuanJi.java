package com.example.yfeng.a1511demo.bean;

import java.io.Serializable;

/**
 * Created by yfeng on 2018/3/1.
 */

public class ZhuanJi implements Serializable{
    private int image;
    private String name;
    private String singer;
    private int num;

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
