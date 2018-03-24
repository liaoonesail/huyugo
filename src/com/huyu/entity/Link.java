package com.huyu.entity;

public class Link {
	private int id;
	private String linkname;//友情链接名
	private String linkurl;//友情链接地址
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLinkname() {
		return linkname;
	}
	public void setLinkname(String linkname) {
		this.linkname = linkname;
	}
	public String getLinkurl() {
		return linkurl;
	}
	public void setLinkurl(String linkurl) {
		this.linkurl = linkurl;
	}
	public Link(int id, String linkname, String linkurl) {
		super();
		this.id = id;
		this.linkname = linkname;
		this.linkurl = linkurl;
	}
	public Link(String linkname, String linkurl) {
		super();
		this.linkname = linkname;
		this.linkurl = linkurl;
	}
	public Link() {
		super();
	}
	

}
