package com.huyu.entity;

public class FazhanOrder {
    private int id;
    private int userId;
    private String orderTime;
    private String orderNum;
    private double payMoney;//购买金额
    private int days;//剩余释放天数
    private double fazhanjin;//剩余发展金
    private int status;//订单状态
    private double yue;//使用余额
    private String name;
    private String phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public double getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(double payMoney) {
        this.payMoney = payMoney;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getFazhanjin() {
        return fazhanjin;
    }

    public void setFazhanjin(double fazhanjin) {
        this.fazhanjin = fazhanjin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getYue() {
        return yue;
    }

    public void setYue(double yue) {
        this.yue = yue;
    }

    public FazhanOrder(int id, int userId, String orderTime, String orderNum, double payMoney, int days, double fazhanjin, int status,double yue) {
        this.id = id;
        this.userId = userId;
        this.orderTime = orderTime;
        this.orderNum = orderNum;
        this.payMoney = payMoney;
        this.days = days;
        this.fazhanjin = fazhanjin;
        this.status = status;
        this.yue = yue;
    }

    public FazhanOrder() {
    }
}
