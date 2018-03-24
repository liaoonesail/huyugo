package com.huyu.entity;

import java.util.List;

public class Order {
	private int id;
	private int user_id;//用户ID
	private String order_num;//订单号
	private int status;//订单状态//付款状态，0=未付款、1=已付款、2=待收货、3=已发货，待收货(确认收货，未评价)、4已完成、已收货（已完成评价）、5、申请退款、6、已退款、7、申请退货中、8、完成退货;
	private int address_id;//收货地址ID
	private double total;//订单价格
	private String payment;//支付方式
	private String order_time;//下单时间
	private double freight;//运费
	private double yu_total;//余额支付金额
	private double integral;//使用积分
	private User user;
	private List<Order_details> list;//订单详情
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public String getOrder_num() {
		return order_num;
	}


	public void setOrder_num(String order_num) {
		this.order_num = order_num;
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
	}


	public int getAddress_id() {
		return address_id;
	}


	public void setAddress_id(int address_id) {
		this.address_id = address_id;
	}


	public double getTotal() {
		return total;
	}


	public void setTotal(double total) {
		this.total = total;
	}


	public String getPayment() {
		return payment;
	}


	public void setPayment(String payment) {
		this.payment = payment;
	}


	public String getOrder_time() {
		return order_time;
	}


	public void setOrder_time(String order_time) {
		this.order_time = order_time;
	}


	public double getFreight() {
		return freight;
	}


	public void setFreight(double freight) {
		this.freight = freight;
	}
	
	public double getYu_total() {
		return yu_total;
	}


	public void setYu_total(double yu_total) {
		this.yu_total = yu_total;
	}


	public List<Order_details> getList() {
		return list;
	}


	public void setList(List<Order_details> list) {
		this.list = list;
	}

	public double getIntegral() {
		return integral;
	}

	public void setIntegral(double integral) {
		this.integral = integral;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Order(int id, int user_id, String order_num, int status, int address_id, double total, String payment, String order_time, double freight, double yu_total, double integral, List<Order_details> list) {
		this.id = id;
		this.user_id = user_id;
		this.order_num = order_num;
		this.status = status;
		this.address_id = address_id;
		this.total = total;
		this.payment = payment;
		this.order_time = order_time;
		this.freight = freight;
		this.yu_total = yu_total;
		this.integral = integral;
		this.list = list;
	}

	public Order(int user_id, String order_num, int status, int address_id, double total, String payment, String order_time, double freight, double yu_total, double integral, List<Order_details> list) {
		this.user_id = user_id;
		this.order_num = order_num;
		this.status = status;
		this.address_id = address_id;
		this.total = total;
		this.payment = payment;
		this.order_time = order_time;
		this.freight = freight;
		this.yu_total = yu_total;
		this.integral = integral;
		this.list = list;
	}

	public Order() {
		super();
	}
	
}
