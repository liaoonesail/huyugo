package com.huyu.entity;

public class Goods {
	private int id;
	private String name;// 商品名
	private String color;// 商品颜色
	private String picture_address;// 商品图片地址
	private String goods_describe;// 商品简介
	private String details;// 商品详情
	private double price;// 商品价格
	private double cost_price;// 商品原价
	private int amount;// 商品库存数量
	private int goods_type_type_id;// 商品类型ID
	private int hot;// 商品热度
	private int recommend;// 是否推荐，0=否，1=是；
	private int common_integral;// 普通用户获得积分
	private int common_dixianjin;// 普通用户可抵用的抵现金
	private double common_huyubi;// 普通用户获得互余币
	private int member_integral;// vip获得积分
	private int member_dixianjin;// vip可抵用的抵现金
	private double member_huyubi;// vip获得互余币
	private String norms;// 规格
	private double freight;// 运费
	private int display;// 是否上架 0上架 1下架
	private int shopId;// 供应商id
	private int clickNum;// 点击量
	private int type;//1,2,3 积分兑换，vip商城,vip券
	/*private double pension;//购买商品可获得的养老金

	public double getPension() {
		return pension;
	}

	public void setPension(double pension) {
		this.pension = pension;
	}*/	

	public Goods() {
		super();
	}

	public Goods(int id, String name, String color, String picture_address,
			String goods_describe, String details, double price,
			double cost_price, int amount, int goods_type_type_id, int hot,
			int recommend, int common_integral, int common_dixianjin,
			double common_huyubi, int member_integral, int member_dixianjin,
			double member_huyubi, String norms, double freight, int display,
			int shopId, int clickNum) {
		super();
		this.id = id;
		this.name = name;
		this.color = color;
		this.picture_address = picture_address;
		this.goods_describe = goods_describe;
		this.details = details;
		this.price = price;
		this.cost_price = cost_price;
		this.amount = amount;
		this.goods_type_type_id = goods_type_type_id;
		this.hot = hot;
		this.recommend = recommend;
		this.common_integral = common_integral;
		this.common_dixianjin = common_dixianjin;
		this.common_huyubi = common_huyubi;
		this.member_integral = member_integral;
		this.member_dixianjin = member_dixianjin;
		this.member_huyubi = member_huyubi;
		this.norms = norms;
		this.freight = freight;
		this.display = display;
		this.shopId = shopId;
		this.clickNum = clickNum;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPicture_address() {
		return picture_address;
	}

	public void setPicture_address(String picture_address) {
		this.picture_address = picture_address;
	}

	public String getGoods_describe() {
		return goods_describe;
	}

	public void setGoods_describe(String goods_describe) {
		this.goods_describe = goods_describe;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCost_price() {
		return cost_price;
	}

	public void setCost_price(double cost_price) {
		this.cost_price = cost_price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getGoods_type_type_id() {
		return goods_type_type_id;
	}

	public void setGoods_type_type_id(int goods_type_type_id) {
		this.goods_type_type_id = goods_type_type_id;
	}

	public int getHot() {
		return hot;
	}

	public void setHot(int hot) {
		this.hot = hot;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getCommon_integral() {
		return common_integral;
	}

	public void setCommon_integral(int common_integral) {
		this.common_integral = common_integral;
	}

	public int getCommon_dixianjin() {
		return common_dixianjin;
	}

	public void setCommon_dixianjin(int common_dixianjin) {
		this.common_dixianjin = common_dixianjin;
	}

	public double getCommon_huyubi() {
		return common_huyubi;
	}

	public void setCommon_huyubi(double common_huyubi) {
		this.common_huyubi = common_huyubi;
	}

	public int getMember_integral() {
		return member_integral;
	}

	public void setMember_integral(int member_integral) {
		this.member_integral = member_integral;
	}

	public int getMember_dixianjin() {
		return member_dixianjin;
	}

	public void setMember_dixianjin(int member_dixianjin) {
		this.member_dixianjin = member_dixianjin;
	}

	public double getMember_huyubi() {
		return member_huyubi;
	}

	public void setMember_huyubi(double member_huyubi) {
		this.member_huyubi = member_huyubi;
	}

	public String getNorms() {
		return norms;
	}

	public void setNorms(String norms) {
		this.norms = norms;
	}

	public double getFreight() {
		return freight;
	}

	public void setFreight(double freight) {
		this.freight = freight;
	}

	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public int getClickNum() {
		return clickNum;
	}

	public void setClickNum(int clickNum) {
		this.clickNum = clickNum;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", color=" + color
				+ ", picture_address=" + picture_address + ", goods_describe="
				+ goods_describe + ", details=" + details + ", price=" + price
				+ ", cost_price=" + cost_price + ", amount=" + amount
				+ ", goods_type_type_id=" + goods_type_type_id + ", hot=" + hot
				+ ", recommend=" + recommend + ", common_integral="
				+ common_integral + ", common_dixianjin=" + common_dixianjin
				+ ", common_huyubi=" + common_huyubi + ", member_integral="
				+ member_integral + ", member_dixianjin=" + member_dixianjin
				+ ", member_huyubi=" + member_huyubi + ", norms=" + norms
				+ ", freight=" + freight + ", display=" + display + ", shopId="
				+ shopId + ", clickNum=" + clickNum + "]";
	}

}
