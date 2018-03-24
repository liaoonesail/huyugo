package com.huyu.entity;

public class Fazhanjin {
    private int id;
    private double yanglao;//1%

    private double chongzhi;//50%

    private double shifang;//1%
    private double shifangY;//90%
    private double shifangJ;//10%

    private double dai1;//10%
    private double dai2;//5%
    private double dai3;//3%

    private double guanli;//5%

    private double fuwu;//3%
    private double fuwujin;//500000

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getYanglao() {
        return yanglao;
    }

    public void setYanglao(double yanglao) {
        this.yanglao = yanglao;
    }

    public double getChongzhi() {
        return chongzhi;
    }

    public void setChongzhi(double chongzhi) {
        this.chongzhi = chongzhi;
    }

    public double getShifang() {
        return shifang;
    }

    public void setShifang(double shifang) {
        this.shifang = shifang;
    }

    public double getShifangY() {
        return shifangY;
    }

    public void setShifangY(double shifangY) {
        this.shifangY = shifangY;
    }

    public double getShifangJ() {
        return shifangJ;
    }

    public void setShifangJ(double shifangJ) {
        this.shifangJ = shifangJ;
    }

    public double getDai1() {
        return dai1;
    }

    public void setDai1(double dai1) {
        this.dai1 = dai1;
    }

    public double getDai2() {
        return dai2;
    }

    public void setDai2(double dai2) {
        this.dai2 = dai2;
    }

    public double getDai3() {
        return dai3;
    }

    public void setDai3(double dai3) {
        this.dai3 = dai3;
    }

    public double getGuanli() {
        return guanli;
    }

    public void setGuanli(double guanli) {
        this.guanli = guanli;
    }

    public double getFuwu() {
        return fuwu;
    }

    public void setFuwu(double fuwu) {
        this.fuwu = fuwu;
    }

    public double getFuwujin() {
        return fuwujin;
    }

    public void setFuwujin(double fuwujin) {
        this.fuwujin = fuwujin;
    }

    public Fazhanjin(int id, double yanglao, double chongzhi, double shifang, double shifangY, double shifangJ, double dai1, double dai2, double dai3, double guanli, double fuwu, double fuwujin) {
        this.id = id;
        this.yanglao = yanglao;
        this.chongzhi = chongzhi;
        this.shifang = shifang;
        this.shifangY = shifangY;
        this.shifangJ = shifangJ;
        this.dai1 = dai1;
        this.dai2 = dai2;
        this.dai3 = dai3;
        this.guanli = guanli;
        this.fuwu = fuwu;
        this.fuwujin = fuwujin;
    }

    public Fazhanjin() {
    }
}
