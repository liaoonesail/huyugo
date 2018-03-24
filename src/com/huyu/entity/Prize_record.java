package com.huyu.entity;

public class Prize_record {
	private int id;
	private String prize;//奖项 1 一等奖  2 二等奖 3 三等奖 4 四等奖 5 五等奖 6 六等奖
	private String content;//奖项描述
	private String type;//奖项类别 1 转盘 2 砸金蛋
	private String date_time;//时间
	private int user_id;//用户ID
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPrize() {
		return prize;
	}
	public void setPrize(String prize) {
		this.prize = prize;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public Prize_record(int id, String prize, String content, String type,
			String date_time, int user_id) {
		super();
		this.id = id;
		this.prize = prize;
		this.content = content;
		this.type = type;
		this.date_time = date_time;
		this.user_id = user_id;
	}
	public Prize_record(String prize, String content, String type,
			String date_time, int user_id) {
		super();
		this.prize = prize;
		this.content = content;
		this.type = type;
		this.date_time = date_time;
		this.user_id = user_id;
	}
	public Prize_record() {
		super();
	}
	

}
