package com.huyu.entity;

/**
 * 养老金
 * @author Administrator
 */
public class Pension {
	private int id;
	private double y_dai1;//购买一代奖3%
	private double y_dai2;//购买二代奖2%
	private double y_dai3;//购买三代奖3%
	
	private double y_v1;//v1直推10人享受团队业绩的2%
	private double y_v1_1;//v1下级团队的的0%
	
	private double y_v2;//v2直推10人并且培养5个v1享受团队业绩的4%
	private double y_v2_1;//v1下级团队的2%
	private double y_v2_2;//v2下级团队的0%
	
	private double y_v3;//v3直推10人并且培养12个v1享受团队业绩的6%
	private double y_v3_1;//v1下级团队的4%
	private double y_v3_2;//v2下级团队的2%
	private double y_v3_3;//v3下级团队的0%
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getY_dai1() {
		return y_dai1;
	}
	public void setY_dai1(double y_dai1) {
		this.y_dai1 = y_dai1;
	}
	public double getY_dai2() {
		return y_dai2;
	}
	public void setY_dai2(double y_dai2) {
		this.y_dai2 = y_dai2;
	}
	public double getY_dai3() {
		return y_dai3;
	}
	public void setY_dai3(double y_dai3) {
		this.y_dai3 = y_dai3;
	}
	public double getY_v1() {
		return y_v1;
	}
	public void setY_v1(double y_v1) {
		this.y_v1 = y_v1;
	}
	public double getY_v1_1() {
		return y_v1_1;
	}
	public void setY_v1_1(double y_v1_1) {
		this.y_v1_1 = y_v1_1;
	}
	public double getY_v2() {
		return y_v2;
	}
	public void setY_v2(double y_v2) {
		this.y_v2 = y_v2;
	}
	public double getY_v2_1() {
		return y_v2_1;
	}
	public void setY_v2_1(double y_v2_1) {
		this.y_v2_1 = y_v2_1;
	}
	public double getY_v2_2() {
		return y_v2_2;
	}
	public void setY_v2_2(double y_v2_2) {
		this.y_v2_2 = y_v2_2;
	}
	public double getY_v3() {
		return y_v3;
	}
	public void setY_v3(double y_v3) {
		this.y_v3 = y_v3;
	}
	public double getY_v3_1() {
		return y_v3_1;
	}
	public void setY_v3_1(double y_v3_1) {
		this.y_v3_1 = y_v3_1;
	}
	public double getY_v3_2() {
		return y_v3_2;
	}
	public void setY_v3_2(double y_v3_2) {
		this.y_v3_2 = y_v3_2;
	}
	public double getY_v3_3() {
		return y_v3_3;
	}
	public void setY_v3_3(double y_v3_3) {
		this.y_v3_3 = y_v3_3;
	}
	public Pension(int id, double y_dai1, double y_dai2, double y_dai3,
			double y_v1, double y_v1_1, double y_v2, double y_v2_1,
			double y_v2_2, double y_v3, double y_v3_1, double y_v3_2,
			double y_v3_3) {
		super();
		this.id = id;
		this.y_dai1 = y_dai1;
		this.y_dai2 = y_dai2;
		this.y_dai3 = y_dai3;
		this.y_v1 = y_v1;
		this.y_v1_1 = y_v1_1;
		this.y_v2 = y_v2;
		this.y_v2_1 = y_v2_1;
		this.y_v2_2 = y_v2_2;
		this.y_v3 = y_v3;
		this.y_v3_1 = y_v3_1;
		this.y_v3_2 = y_v3_2;
		this.y_v3_3 = y_v3_3;
	}
	public Pension() {
		super();
	}
	

}
