package com.huyu.entity;

/**
 * 发展金报表been
 */
public class Baobiao {
    private double todaypay;
    private double totalpay;
    private double todayreciptA;
    private double totalreciptA;
    private double todayreciptI;
    private double totalreciptI;
    private double yestodayreciptA;
    private double yestodayreciptI;

    public double getTodaypay() {
        return todaypay;
    }

    public void setTodaypay(double todaypay) {
        this.todaypay = todaypay;
    }

    public double getTotalpay() {
        return totalpay;
    }

    public void setTotalpay(double totalpay) {
        this.totalpay = totalpay;
    }

    public double getTodayreciptA() {
        return todayreciptA;
    }

    public void setTodayreciptA(double todayreciptA) {
        this.todayreciptA = todayreciptA;
    }

    public double getTotalreciptA() {
        return totalreciptA;
    }

    public void setTotalreciptA(double totalreciptA) {
        this.totalreciptA = totalreciptA;
    }

    public double getTodayreciptI() {
        return todayreciptI;
    }

    public void setTodayreciptI(double todayreciptI) {
        this.todayreciptI = todayreciptI;
    }

    public double getTotalreciptI() {
        return totalreciptI;
    }

    public void setTotalreciptI(double totalreciptI) {
        this.totalreciptI = totalreciptI;
    }

    public double getYestodayreciptA() {
        return yestodayreciptA;
    }

    public void setYestodayreciptA(double yestodayreciptA) {
        this.yestodayreciptA = yestodayreciptA;
    }

    public double getYestodayreciptI() {
        return yestodayreciptI;
    }

    public void setYestodayreciptI(double yestodayreciptI) {
        this.yestodayreciptI = yestodayreciptI;
    }

    public Baobiao(double todaypay, double totalpay, double todayreciptA, double totalreciptA, double todayreciptI, double totalreciptI, double yestodayreciptA, double yestodayreciptI) {
        this.todaypay = todaypay;
        this.totalpay = totalpay;
        this.todayreciptA = todayreciptA;
        this.totalreciptA = totalreciptA;
        this.todayreciptI = todayreciptI;
        this.totalreciptI = totalreciptI;
        this.yestodayreciptA = yestodayreciptA;
        this.yestodayreciptI = yestodayreciptI;
    }

    public Baobiao() {

    }
}
