package com.huyu.po;

/**
 * 养老金vo类
 * 
 * @author Administrator
 * 
 */
public class PensionVo {

	private String time;
	private String way;
	private String get;

	public PensionVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PensionVo(String time, String way, String get) {
		super();
		this.time = time;
		this.way = way;
		this.get = get;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getWay() {
		return way;
	}

	public void setWay(String way) {
		this.way = way;
	}

	public String getGet() {
		return get;
	}

	public void setGet(String get) {
		this.get = get;
	}

	@Override
	public String toString() {
		return "PensionVo [time=" + time + ", way=" + way + ", get=" + get
				+ "]";
	}

}
