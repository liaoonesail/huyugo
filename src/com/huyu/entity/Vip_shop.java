package com.huyu.entity;

public class Vip_shop {
    private int id;
    private int goods_id;
    private double real_price;

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

    public double getReal_price() {
        return real_price;
    }

    public void setReal_price(double real_price) {
        this.real_price = real_price;
    }

    public Vip_shop(int id, int goods_id, double real_price) {
        this.id = id;
        this.goods_id = goods_id;
        this.real_price = real_price;
    }

    public Vip_shop() {
    }
}
