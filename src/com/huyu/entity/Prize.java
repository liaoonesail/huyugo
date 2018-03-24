package com.huyu.entity;

public class Prize {
	private int id;
	private double prize_1;//一等奖
	private double prize_2;//二等奖
	private double prize_3;//三等奖
	private double prize_4;//四等奖
	private double prize_5;//五等奖
	private double prize_6;//六等奖
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getPrize_1() {
		return prize_1;
	}
	public void setPrize_1(double prize_1) {
		this.prize_1 = prize_1;
	}
	public double getPrize_2() {
		return prize_2;
	}
	public void setPrize_2(double prize_2) {
		this.prize_2 = prize_2;
	}
	public double getPrize_3() {
		return prize_3;
	}
	public void setPrize_3(double prize_3) {
		this.prize_3 = prize_3;
	}
	public double getPrize_4() {
		return prize_4;
	}
	public void setPrize_4(double prize_4) {
		this.prize_4 = prize_4;
	}
	public double getPrize_5() {
		return prize_5;
	}
	public void setPrize_5(double prize_5) {
		this.prize_5 = prize_5;
	}
	public double getPrize_6() {
		return prize_6;
	}
	public void setPrize_6(double prize_6) {
		this.prize_6 = prize_6;
	}
	public Prize(int id, double prize_1, double prize_2, double prize_3,
			double prize_4, double prize_5, double prize_6) {
		super();
		this.id = id;
		this.prize_1 = prize_1;
		this.prize_2 = prize_2;
		this.prize_3 = prize_3;
		this.prize_4 = prize_4;
		this.prize_5 = prize_5;
		this.prize_6 = prize_6;
	}
	public Prize(double prize_1, double prize_2, double prize_3,
			double prize_4, double prize_5, double prize_6) {
		super();
		this.prize_1 = prize_1;
		this.prize_2 = prize_2;
		this.prize_3 = prize_3;
		this.prize_4 = prize_4;
		this.prize_5 = prize_5;
		this.prize_6 = prize_6;
	}
	public Prize() {
		super();
	}
}
