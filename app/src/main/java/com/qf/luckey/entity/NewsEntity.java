package com.qf.luckey.entity;

/**
 * Created by Administrator on 2016/8/7.
 */
public class NewsEntity {

    /**
     * id : HBJ2016080700906302
     * type : 0
     * title : 买入4.68%股份 恒大加入“万科之争”
     * summary : 　　买入4.68%股份恒大加入“万科之争”　　恒大耗资91.1亿元买入5.17亿股万科A，万科股权之争增加新变数；万科A昨日涨停　　沉寂了一周的万科股权之争，因恒大的加入再次热闹起来。昨日恒大先否认再承认，耗资91.1亿元买入5.17亿股万科A，占万科总股本4.68%，逼近举牌线。在恒大买入万科消息刺激下，万科A股昨日涨停。　
     * thumbnail : http://inews.gtimg.com/newsapp_ls/0/468833689_640330/0
     * groupthumbnail : http://inews.gtimg.com/newsapp_ls/0/468833689_150120/0
     * commentcount : 0
     * imagecount : 0
     * commentid : 1493141761
     */

    private String id;
    private int type;
    private String title;
    private String summary;
    private String thumbnail;
    private String groupthumbnail;
    private int commentcount;
    private int imagecount;
    private String commentid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getGroupthumbnail() {
        return groupthumbnail;
    }

    public void setGroupthumbnail(String groupthumbnail) {
        this.groupthumbnail = groupthumbnail;
    }

    public int getCommentcount() {
        return commentcount;
    }

    public void setCommentcount(int commentcount) {
        this.commentcount = commentcount;
    }

    public int getImagecount() {
        return imagecount;
    }

    public void setImagecount(int imagecount) {
        this.imagecount = imagecount;
    }

    public String getCommentid() {
        return commentid;
    }

    public void setCommentid(String commentid) {
        this.commentid = commentid;
    }
}
