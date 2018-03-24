package com.huyu.entity;

/**
 * @title 供应商管理
 * @author Administrator
 * @time 2017-8-21 下午3:54:31
 * @describe
 */
public class Shop {

	private int id;
	private String name;
	private String phone;

	public Shop() {
		super();
	}

	public Shop(int id, String name, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	public Shop(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", phone=" + phone + "]";
	}

}
