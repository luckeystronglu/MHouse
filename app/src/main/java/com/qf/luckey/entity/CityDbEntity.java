package com.qf.luckey.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * 城市实体类
 * Created by Administrator on 2016/8/7.

 */
@Table(name = "citydb",id = "_id")
public class CityDbEntity extends Model {
    @Column(name = "cid")
    private int cid;
    @Column(name = "cname")
    private String cname;
    @Column(name = "ctype")
    private int ctype = 1;//默认当前选择城市

    //有参构造方法
    public CityDbEntity(int cid,String cname, int ctype) {
        this.cid = cid;
        this.cname = cname;
        this.ctype = ctype;
    }

    public CityDbEntity() {
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getCtype() {
        return ctype;
    }

    public void setCtype(int ctype) {
        this.ctype = ctype;
    }

    @Override
    public String toString() {
        return "{" +
                "cname='" + cname + '\'' +
                '}';
    }
}
