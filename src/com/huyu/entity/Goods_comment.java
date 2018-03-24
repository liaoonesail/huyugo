package com.huyu.entity;

public class Goods_comment {
	private int id;
	private String comment;//商品评价
	private int goods_id;//商品ID
	private int user_id;//用户ID
	private String time;//评价时间
	private int goods_grade;//商品评分
	private int service_grade;//服务评分
	private int deliver_grade;//快递评分
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	public int getGoods_grade() {
		return goods_grade;
	}

	public void setGoods_grade(int goods_grade) {
		this.goods_grade = goods_grade;
	}

	public int getService_grade() {
		return service_grade;
	}

	public void setService_grade(int service_grade) {
		this.service_grade = service_grade;
	}

	public int getDeliver_grade() {
		return deliver_grade;
	}

	public void setDeliver_grade(int deliver_grade) {
		this.deliver_grade = deliver_grade;
	}

	
	
	public Goods_comment(int id, String comment, int goods_id, int user_id,
			String time, int goods_grade, int service_grade, int deliver_grade) {
		super();
		this.id = id;
		this.comment = comment;
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.time = time;
		this.goods_grade = goods_grade;
		this.service_grade = service_grade;
		this.deliver_grade = deliver_grade;
	}
	

	public Goods_comment(String comment, int goods_id, int user_id,
			String time, int goods_grade, int service_grade, int deliver_grade) {
		super();
		this.comment = comment;
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.time = time;
		this.goods_grade = goods_grade;
		this.service_grade = service_grade;
		this.deliver_grade = deliver_grade;
	}
	public Goods_comment(String comment, int user_id, String time,
			int goods_grade, int service_grade, int deliver_grade) {
		super();
		this.comment = comment;
		this.user_id = user_id;
		this.time = time;
		this.goods_grade = goods_grade;
		this.service_grade = service_grade;
		this.deliver_grade = deliver_grade;
	}

	public Goods_comment() {
		super();
	}
	

}
