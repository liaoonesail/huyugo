package com.huyu.entity;

public class Affiche {
	private int id;
	private String title;//公告标题
	private String content;//公告内容
	private String date_time;//时间
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	public Affiche(int id, String title, String content, String date_time) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.date_time = date_time;
	}
	public Affiche(String title, String content, String date_time) {
		super();
		this.title = title;
		this.content = content;
		this.date_time = date_time;
	}
	public Affiche() {
		super();
	}
	

}
