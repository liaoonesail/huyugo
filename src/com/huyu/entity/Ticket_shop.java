package com.huyu.entity;

public class Ticket_shop {
    private int id;
    private int goods_id;

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

    public Ticket_shop(int id, int goods_id) {
        this.id = id;
        this.goods_id = goods_id;
    }

    public Ticket_shop() {

    }
}
