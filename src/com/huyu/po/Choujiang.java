package com.huyu.po;

public class Choujiang {
	
	private int jiangId;  //抽奖号  一共6个 从1到6
	private int jiangType;   //奖品类型  1积分 2互余币
	private int jiangNum;    //奖品数量
	private String jiangDetails;  //奖品描述 如：获得20积分
	
	public int getJiangId() {
		return jiangId;
	}
	public void setJiangId(int jiangId) {
		this.jiangId = jiangId;
	}
	public int getJiangType() {
		return jiangType;
	}
	public void setJiangType(int jiangType) {
		this.jiangType = jiangType;
	}
	public int getJiangNum() {
		return jiangNum;
	}
	public void setJiangNum(int jiangNum) {
		this.jiangNum = jiangNum;
	}
	public String getJiangDetails() {
		return jiangDetails;
	}
	public void setJiangDetails(String jiangDetails) {
		this.jiangDetails = jiangDetails;
	}
	
	
}
