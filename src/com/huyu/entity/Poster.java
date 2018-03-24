package com.huyu.entity;

/**
 * @title 广告管理
 * @author Administrator
 * @time 2017-8-21 下午2:15:56
 * @describe
 */
public class Poster {
	
	private int id;
	private String url;
	private String path;

	public Poster() {
		super();
	}

	public Poster(int id, String url, String path) {
		super();
		this.id = id;
		this.url = url;
		this.path = path;
	}

	public Poster(String url, String path) {
		super();
		this.url = url;
		this.path = path;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Poster [id=" + id + ", url=" + url + ", path=" + path + "]";
	}
	
}
