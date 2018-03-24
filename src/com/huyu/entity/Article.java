package com.huyu.entity;

/**
 * 底部文章信息
 * 
 * @author zs
 * @time 2017年8月5日10:46:45
 */
public class Article {
	private int id;
	private int type;// 文章类别 1会员专区 2商品配送 3商务合作 4售后服务 5新手指南
	private String title;// 文章标题
	private String path;// 文章路径
	private String time;// 添加时间
	private int sort;// 排序

	public Article() {
		super();
	}

	public Article(int type, String title, String path, String time, int sort) {
		super();
		this.type = type;
		this.title = title;
		this.path = path;
		this.time = time;
		this.sort = sort;
	}

	public Article(int id, int type, String title, String path, String time,
			int sort) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.path = path;
		this.time = time;
		this.sort = sort;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", type=" + type + ", title=" + title
				+ ", path=" + path + ", time=" + time + ", sort=" + sort + "]";
	}

}
