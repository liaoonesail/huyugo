package com.huyu.entity;

public class Search {
	private int id;
	private int display;//是否展示 0否 1是
	private String name;//名称
	public int getId() {
		return id;
	}
	
	public int getDisplay() {
		return display;
	}

	public void setDisplay(int display) {
		this.display = display;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Search(int id, int display, String name) {
		super();
		this.id = id;
		this.display = display;
		this.name = name;
	}

	public Search(int display, String name) {
		super();
		this.display = display;
		this.name = name;
	}

	public Search() {
		super();
	}
	

}
