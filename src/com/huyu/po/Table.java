package com.huyu.po;

import java.util.List;

/**
 * @title 封装Layui的table实体类
 * @author Administrator
 * @time 2017-8-23 下午1:18:49
 * @describe
 */
public class Table {

	private int code = 0;// 状态码，0代表成功，其它失败
	private String msg;// 状态信息，一般可为空
	private int count;// 数据总量
	private List data;// 数据，字段是任意的。如：[{"id":1,"username":"贤心"},
								// {"id":2,"username":"佟丽娅"}]

	public Table() {
		super();
	}

	public Table(int code, String msg, int count, List data) {
		super();
		this.code = code;
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

}
