package com.huyu.entity;

/**
 * 物流表
 * 
 * @author Administrator
 */
public class Logistics {
	private int id;
	private int order_id;// 订单id
	private int goods_id;// 订单绑定商品id
	private String logistics_name;// 物流名称
	private String logistics_num;// 物流号
	private String time;//发货时间

	public Logistics() {
		super();
	}

	public Logistics(int order_id, int goods_id, String logistics_name,
			String logistics_num, String time) {
		super();
		this.order_id = order_id;
		this.goods_id = goods_id;
		this.logistics_name = logistics_name;
		this.logistics_num = logistics_num;
		this.time = time;
	}

	public Logistics(int id, int order_id, int goods_id, String logistics_name,
			String logistics_num, String time) {
		super();
		this.id = id;
		this.order_id = order_id;
		this.goods_id = goods_id;
		this.logistics_name = logistics_name;
		this.logistics_num = logistics_num;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public String getLogistics_name() {
		return logistics_name;
	}

	public void setLogistics_name(String logistics_name) {
		this.logistics_name = logistics_name;
	}

	public String getLogistics_num() {
		return logistics_num;
	}

	public void setLogistics_num(String logistics_num) {
		this.logistics_num = logistics_num;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Logistics [id=" + id + ", order_id=" + order_id + ", goods_id="
				+ goods_id + ", logistics_name=" + logistics_name
				+ ", logistics_num=" + logistics_num + ", time=" + time + "]";
	}

}
