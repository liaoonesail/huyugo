package com.huyu.entity;

public class Picture {
	private int id;
	private String picture;//图片地址
	private int goods_id;//图片对应的商品ID
	private String url;//图片跳转地址
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public int getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Picture(int id, String picture, int goods_id, String url) {
		super();
		this.id = id;
		this.picture = picture;
		this.goods_id = goods_id;
		this.url = url;
	}
	public Picture(String picture, int goods_id, String url) {
		super();
		this.picture = picture;
		this.goods_id = goods_id;
		this.url = url;
	}
	public Picture() {
		super();
	}
	

}
