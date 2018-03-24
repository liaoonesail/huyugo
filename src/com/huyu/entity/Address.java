package com.huyu.entity;

public class Address {
	private int id;
	private String province;//省
	private String city;//市
	private String county;//区
	private String address;//街道地址
	private String name;//收货人姓名
	private String phone;//收货人手机号
	private int user_id;//用户ID
	private int status;//是否默认

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	

	public Address(int id, String province, String city, String county,
			String address, String name, String phone, int user_id, int status) {
		super();
		this.id = id;
		this.province = province;
		this.city = city;
		this.county = county;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.user_id = user_id;
		this.status = status;
	}

	

	public Address(String province, String city, String county, String address,
			String name, String phone, int user_id, int status) {
		super();
		this.province = province;
		this.city = city;
		this.county = county;
		this.address = address;
		this.name = name;
		this.phone = phone;
		this.user_id = user_id;
		this.status = status;
	}

	public Address() {
		super();
	}
	
	
}
