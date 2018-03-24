package com.huyu.entity;

public class Integral_shop {
    private int id;
    private int goods_id;
    private double integral;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoods_id() {
        return goods_id;
    }

    public void setGoods_id(int goods_id) {
        this.goods_id = goods_id;
    }

    public double getIntegral() {
        return integral;
    }

    public void setIntegral(double integral) {
        this.integral = integral;
    }

    public Integral_shop(int id, int goods_id, double integral) {
        this.id = id;
        this.goods_id = goods_id;
        this.integral = integral;
    }

    public Integral_shop() {
    }
}
