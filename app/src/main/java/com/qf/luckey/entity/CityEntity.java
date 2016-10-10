package com.qf.luckey.entity;

import java.io.Serializable;

/**
 * 城市实体类
 * Created by Administrator on 2016/8/7.

 */

public class CityEntity implements Serializable {

    private int cityid;

    private String cityname;
    private int type = 1;//默认当前选择城市

    //有参构造方法
    public CityEntity(String cityname, int type) {
        this.cityname = cityname;
        this.type = type;
    }

    //无参构造方法
    public CityEntity() {
    }

    public int getCityid() {
        return cityid;
    }

    public void setCityid(int cityid) {
        this.cityid = cityid;
    }

    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CityEntity{" +
                "cityid=" + cityid +
                ", cityname='" + cityname + '\'' +
                ", type=" + type +
                '}';
    }
}
