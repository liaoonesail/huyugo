package com.huyu.entity;

public class Admin {
	private int id;
	private String admin_name;//管理名
	private String pwd;//密码
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Admin(int id, String admin_name, String pwd) {
		super();
		this.id = id;
		this.admin_name = admin_name;
		this.pwd = pwd;
	}
	public Admin(String admin_name, String pwd) {
		super();
		this.admin_name = admin_name;
		this.pwd = pwd;
	}
	public Admin() {
		super();
	}
	@Override
	public String toString() {
		return "Admin [id=" + id + ", admin_name=" + admin_name + ", pwd="
				+ pwd + "]";
	}
	

}
