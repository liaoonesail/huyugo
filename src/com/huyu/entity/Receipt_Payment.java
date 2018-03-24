package com.huyu.entity;

public class Receipt_Payment {
	private int id;
	private double receipt;// 获得
	private double payment;// 支出
	private String date_time;// 时间
	private int user_id;// 用户ID
	private int type;// 模块 1余额 2互余币 3抵现金 4积分 5第三方支付 6养老金
	private int way;// 渠道 1会员开通 2商品购买 3下级分利 4抽奖花费 5抽奖获得 6提现 7签到 8分享9完善资料10兑换
					// 11会员转账 12商品退款 13商品退货 14发展金收益 18加盟
	private int goods_id;// 商品id
	private int share_way;// 1 2 3 4
	private int orderId;
	private String name;
	private String phone;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getReceipt() {
		return receipt;
	}

	public void setReceipt(double receipt) {
		this.receipt = receipt;
	}

	public double getPayment() {
		return payment;
	}

	public void setPayment(double payment) {
		this.payment = payment;
	}

	public String getDate_time() {
		return date_time;
	}

	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getWay() {
		return way;
	}

	public void setWay(int way) {
		this.way = way;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getShare_way() {
		return share_way;
	}

	public void setShare_way(int share_way) {
		this.share_way = share_way;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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

	//退款退货返回构造
	public Receipt_Payment(double payment, String date_time, int user_id,
			int type, int way, int orderId) {
		super();
		this.payment = payment;
		this.date_time = date_time;
		this.user_id = user_id;
		this.type = type;
		this.way = way;
		this.orderId = orderId;
	}

	public Receipt_Payment(int id, double receipt, double payment,
			String date_time, int user_id, int type, int way, int goods_id,
			int share_way, int orderId) {
		super();
		this.id = id;
		this.receipt = receipt;
		this.payment = payment;
		this.date_time = date_time;
		this.user_id = user_id;
		this.type = type;
		this.way = way;
		this.goods_id = goods_id;
		this.share_way = share_way;
		this.orderId = orderId;
	}

	public Receipt_Payment(double receipt, double payment, String date_time,
			int user_id, int type, int way, int goods_id, int share_way,
			int orderId) {
		super();
		this.receipt = receipt;
		this.payment = payment;
		this.date_time = date_time;
		this.user_id = user_id;
		this.type = type;
		this.way = way;
		this.goods_id = goods_id;
		this.share_way = share_way;
		this.orderId = orderId;
	}

	public Receipt_Payment(int id, double receipt, double payment,
			String date_time, int user_id, int type, int way, int goods_id,
			int share_way) {
		super();
		this.id = id;
		this.receipt = receipt;
		this.payment = payment;
		this.date_time = date_time;
		this.user_id = user_id;
		this.type = type;
		this.way = way;
		this.goods_id = goods_id;
		this.share_way = share_way;
	}

	public Receipt_Payment(double receipt, double payment, String date_time,
			int user_id, int type, int way, int orderId) {
		super();
		this.receipt = receipt;
		this.payment = payment;
		this.date_time = date_time;
		this.user_id = user_id;
		this.type = type;
		this.way = way;
		this.orderId = orderId;
	}

	public Receipt_Payment(double receipt, double payment, String date_time,
			int user_id, int type, int way, int goods_id, int share_way) {
		super();
		this.receipt = receipt;
		this.payment = payment;
		this.date_time = date_time;
		this.user_id = user_id;
		this.type = type;
		this.way = way;
		this.goods_id = goods_id;
		this.share_way = share_way;
	}

	public Receipt_Payment() {
		super();
	}




}
