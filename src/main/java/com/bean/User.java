package com.bean;

import java.util.Date;

public class User {
    private Integer t_id;
    private String t_email;
    private String t_pwd;
    private Date t_createdtime;
    private Date t_modifiedtime;

    public User(String t_email) {
        this.t_email = t_email;
    }

    public Integer getT_id() {
        return t_id;
    }

    public void setT_id(Integer t_id) {
        this.t_id = t_id;
    }

    public User(){

    }

    public Date getT_modifiedtime() {
        return t_modifiedtime;
    }

    public void setT_modifiedtime(Date t_modifiedtime) {
        this.t_modifiedtime = t_modifiedtime;
    }

    public User(Integer t_id,String t_email, String t_pwd, Date t_createdtime,Date t_modifiedtime) {
        this.t_id = t_id;
        this.t_email = t_email;
        this.t_pwd = t_pwd;
        this.t_createdtime = t_createdtime;
        this.t_modifiedtime = t_createdtime;
    }

    @Override
    public String toString() {
        return "User{" +
                "t_id=" + t_id +
                ", t_email='" + t_email + '\'' +
                ", t_pwd='" + t_pwd + '\'' +
                ", t_createdtime=" + t_createdtime +
                ", t_modifiedtime=" + t_modifiedtime +
                '}';
    }

    public String getT_email() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email = t_email;
    }

    public String getT_pwd() {
        return t_pwd;
    }

    public void setT_pwd(String t_pwd) {
        this.t_pwd = t_pwd;
    }

    public Date getT_createdtime() {
        return t_createdtime;
    }

    public void setT_createdtime(Date t_date) {
        this.t_createdtime = t_date;
    }



}
