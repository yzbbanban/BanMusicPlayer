package com.wangban.yzbbanban.banmusicplayer.entity;

import java.io.Serializable;

/**
 * Created by YZBbanban on 16/6/23.
 */
public class Billboard {
    private String billboard_no;
    private String billboard_songnum;
    private String billboard_type;
    private String havemore;
    private String comment;
    private String name;
    private String pic_s210;
    private String pic_s260;
    private String pic_s444;
    private String pic_s640;
    private String update_date;
    private String web_url;

    public Billboard() {
    }

    public Billboard(String billboard_no, String billboard_songnum, String billboard_type, String havemore, String comment, String name, String pic_s210, String pic_s260, String pic_s444, String pic_s640, String update_date, String web_url) {
        this.billboard_no = billboard_no;
        this.billboard_songnum = billboard_songnum;
        this.billboard_type = billboard_type;
        this.havemore = havemore;
        this.comment = comment;
        this.name = name;
        this.pic_s210 = pic_s210;
        this.pic_s260 = pic_s260;
        this.pic_s444 = pic_s444;
        this.pic_s640 = pic_s640;
        this.update_date = update_date;
        this.web_url = web_url;
    }

    public String getBillboard_no() {
        return billboard_no;
    }

    public void setBillboard_no(String billboard_no) {
        this.billboard_no = billboard_no;
    }

    public String getBillboard_songnum() {
        return billboard_songnum;
    }

    public void setBillboard_songnum(String billboard_songnum) {
        this.billboard_songnum = billboard_songnum;
    }

    public String getBillboard_type() {
        return billboard_type;
    }

    public void setBillboard_type(String billboard_type) {
        this.billboard_type = billboard_type;
    }

    public String getHavemore() {
        return havemore;
    }

    public void setHavemore(String havemore) {
        this.havemore = havemore;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic_s210() {
        return pic_s210;
    }

    public void setPic_s210(String pic_s210) {
        this.pic_s210 = pic_s210;
    }

    public String getPic_s260() {
        return pic_s260;
    }

    public void setPic_s260(String pic_s260) {
        this.pic_s260 = pic_s260;
    }

    public String getPic_s444() {
        return pic_s444;
    }

    public void setPic_s444(String pic_s444) {
        this.pic_s444 = pic_s444;
    }

    public String getPic_s640() {
        return pic_s640;
    }

    public void setPic_s640(String pic_s640) {
        this.pic_s640 = pic_s640;
    }

    public String getUpdate_date() {
        return update_date;
    }

    public void setUpdate_date(String update_date) {
        this.update_date = update_date;
    }

    public String getWeb_url() {
        return web_url;
    }

    public void setWeb_url(String web_url) {
        this.web_url = web_url;
    }
}
