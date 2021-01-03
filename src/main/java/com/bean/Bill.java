package com.bean;

import java.util.Date;

public class Bill {
    private Integer bill_id;
    private String email;
    private double money;
    private String detailed;
    private String flag;
    private Date time;
    private String week;

    public Bill() {

    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public Integer getBill_id() {
        return bill_id;
    }

    public void setBill_id(Integer bill_id) {
        this.bill_id = bill_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getDetailed() {
        return detailed;
    }

    public void setDetailed(String detailed) {
        this.detailed = detailed;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "bill_id=" + bill_id +
                ", email='" + email + '\'' +
                ", money=" + money +
                ", detailed='" + detailed + '\'' +
                ", flag='" + flag + '\'' +
                ", time=" + time +
                ", week='" + week + '\'' +
                '}';
    }

    public Bill(Integer bill_id, String email, double money, String detailed, String flag, Date time,String week) {
        this.bill_id = bill_id;
        this.email = email;
        this.money = money;
        this.detailed = detailed;
        this.flag = flag;
        this.time = time;
        this.week = week;
    }
}
