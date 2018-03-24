package com.huyu.entity;

public class Member {
	private int id;
	
	private double m_dai1;//会员一代奖15%
	private double m_dai2;//会员二代奖10%
	private double m_dai3;//会员三代奖15%
	
	private double m_v1;//v1直推10人享受团队业绩的4%
	private double m_v1_1;//v1下级团队的的0%
	
	private double m_v2;//v2直推10人并且培养5个v1享受团队业绩的6%
	private double m_v2_1;//v1下级团队的2%
	private double m_v2_2;//v2下级团队的0%
	
	private double m_v3;//v3直推10人并且培养12个v1享受团队业绩的8%
	private double m_v3_1;//v1下级团队的4%
	private double m_v3_2;//v2下级团队的2%
	private double m_v3_3;//v3下级团队的0%
	
	private double m_v2_service;//达到v2级别后享受补贴3%
	
	private double b_dai1;//购买一代奖3%
	private double b_dai2;//购买二代奖2%
	private double b_dai3;//购买三代奖3%
	
	private double b_v1;//v1直推10人享受团队业绩的2%
	private double b_v1_1;//v1下级团队的的0%
	
	private double b_v2;//v2直推10人并且培养5个v1享受团队业绩的4%
	private double b_v2_1;//v1下级团队的2%
	private double b_v2_2;//v2下级团队的0%
	
	private double b_v3;//v3直推10人并且培养12个v1享受团队业绩的6%
	private double b_v3_1;//v1下级团队的4%
	private double b_v3_2;//v2下级团队的2%
	private double b_v3_3;//v3下级团队的0%
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getM_dai1() {
		return m_dai1;
	}
	public void setM_dai1(double m_dai1) {
		this.m_dai1 = m_dai1;
	}
	public double getM_dai2() {
		return m_dai2;
	}
	public void setM_dai2(double m_dai2) {
		this.m_dai2 = m_dai2;
	}
	public double getM_dai3() {
		return m_dai3;
	}
	public void setM_dai3(double m_dai3) {
		this.m_dai3 = m_dai3;
	}
	public double getM_v1() {
		return m_v1;
	}
	public void setM_v1(double m_v1) {
		this.m_v1 = m_v1;
	}
	public double getM_v1_1() {
		return m_v1_1;
	}
	public void setM_v1_1(double m_v1_1) {
		this.m_v1_1 = m_v1_1;
	}
	public double getM_v2() {
		return m_v2;
	}
	public void setM_v2(double m_v2) {
		this.m_v2 = m_v2;
	}
	public double getM_v2_1() {
		return m_v2_1;
	}
	public void setM_v2_1(double m_v2_1) {
		this.m_v2_1 = m_v2_1;
	}
	public double getM_v2_2() {
		return m_v2_2;
	}
	public void setM_v2_2(double m_v2_2) {
		this.m_v2_2 = m_v2_2;
	}
	public double getM_v3() {
		return m_v3;
	}
	public void setM_v3(double m_v3) {
		this.m_v3 = m_v3;
	}
	public double getM_v3_1() {
		return m_v3_1;
	}
	public void setM_v3_1(double m_v3_1) {
		this.m_v3_1 = m_v3_1;
	}
	public double getM_v3_2() {
		return m_v3_2;
	}
	public void setM_v3_2(double m_v3_2) {
		this.m_v3_2 = m_v3_2;
	}
	public double getM_v3_3() {
		return m_v3_3;
	}
	public void setM_v3_3(double m_v3_3) {
		this.m_v3_3 = m_v3_3;
	}
	public double getM_v2_service() {
		return m_v2_service;
	}
	public void setM_v2_service(double m_v2_service) {
		this.m_v2_service = m_v2_service;
	}
	public double getB_dai1() {
		return b_dai1;
	}
	public void setB_dai1(double b_dai1) {
		this.b_dai1 = b_dai1;
	}
	public double getB_dai2() {
		return b_dai2;
	}
	public void setB_dai2(double b_dai2) {
		this.b_dai2 = b_dai2;
	}
	public double getB_dai3() {
		return b_dai3;
	}
	public void setB_dai3(double b_dai3) {
		this.b_dai3 = b_dai3;
	}
	public double getB_v1() {
		return b_v1;
	}
	public void setB_v1(double b_v1) {
		this.b_v1 = b_v1;
	}
	public double getB_v1_1() {
		return b_v1_1;
	}
	public void setB_v1_1(double b_v1_1) {
		this.b_v1_1 = b_v1_1;
	}
	public double getB_v2() {
		return b_v2;
	}
	public void setB_v2(double b_v2) {
		this.b_v2 = b_v2;
	}
	public double getB_v2_1() {
		return b_v2_1;
	}
	public void setB_v2_1(double b_v2_1) {
		this.b_v2_1 = b_v2_1;
	}
	public double getB_v2_2() {
		return b_v2_2;
	}
	public void setB_v2_2(double b_v2_2) {
		this.b_v2_2 = b_v2_2;
	}
	public double getB_v3() {
		return b_v3;
	}
	public void setB_v3(double b_v3) {
		this.b_v3 = b_v3;
	}
	public double getB_v3_1() {
		return b_v3_1;
	}
	public void setB_v3_1(double b_v3_1) {
		this.b_v3_1 = b_v3_1;
	}
	public double getB_v3_2() {
		return b_v3_2;
	}
	public void setB_v3_2(double b_v3_2) {
		this.b_v3_2 = b_v3_2;
	}
	public double getB_v3_3() {
		return b_v3_3;
	}
	public void setB_v3_3(double b_v3_3) {
		this.b_v3_3 = b_v3_3;
	}
	public Member(int id, double m_dai1, double m_dai2, double m_dai3,
			double m_v1, double m_v1_1, double m_v2, double m_v2_1,
			double m_v2_2, double m_v3, double m_v3_1, double m_v3_2,
			double m_v3_3, double m_v2_service, double b_dai1, double b_dai2,
			double b_dai3, double b_v1, double b_v1_1, double b_v2,
			double b_v2_1, double b_v2_2, double b_v3, double b_v3_1,
			double b_v3_2, double b_v3_3) {
		super();
		this.id = id;
		this.m_dai1 = m_dai1;
		this.m_dai2 = m_dai2;
		this.m_dai3 = m_dai3;
		this.m_v1 = m_v1;
		this.m_v1_1 = m_v1_1;
		this.m_v2 = m_v2;
		this.m_v2_1 = m_v2_1;
		this.m_v2_2 = m_v2_2;
		this.m_v3 = m_v3;
		this.m_v3_1 = m_v3_1;
		this.m_v3_2 = m_v3_2;
		this.m_v3_3 = m_v3_3;
		this.m_v2_service = m_v2_service;
		this.b_dai1 = b_dai1;
		this.b_dai2 = b_dai2;
		this.b_dai3 = b_dai3;
		this.b_v1 = b_v1;
		this.b_v1_1 = b_v1_1;
		this.b_v2 = b_v2;
		this.b_v2_1 = b_v2_1;
		this.b_v2_2 = b_v2_2;
		this.b_v3 = b_v3;
		this.b_v3_1 = b_v3_1;
		this.b_v3_2 = b_v3_2;
		this.b_v3_3 = b_v3_3;
	}
	public Member(double m_dai1, double m_dai2, double m_dai3, double m_v1,
			double m_v1_1, double m_v2, double m_v2_1, double m_v2_2,
			double m_v3, double m_v3_1, double m_v3_2, double m_v3_3,
			double m_v2_service, double b_dai1, double b_dai2, double b_dai3,
			double b_v1, double b_v1_1, double b_v2, double b_v2_1,
			double b_v2_2, double b_v3, double b_v3_1, double b_v3_2,
			double b_v3_3) {
		super();
		this.m_dai1 = m_dai1;
		this.m_dai2 = m_dai2;
		this.m_dai3 = m_dai3;
		this.m_v1 = m_v1;
		this.m_v1_1 = m_v1_1;
		this.m_v2 = m_v2;
		this.m_v2_1 = m_v2_1;
		this.m_v2_2 = m_v2_2;
		this.m_v3 = m_v3;
		this.m_v3_1 = m_v3_1;
		this.m_v3_2 = m_v3_2;
		this.m_v3_3 = m_v3_3;
		this.m_v2_service = m_v2_service;
		this.b_dai1 = b_dai1;
		this.b_dai2 = b_dai2;
		this.b_dai3 = b_dai3;
		this.b_v1 = b_v1;
		this.b_v1_1 = b_v1_1;
		this.b_v2 = b_v2;
		this.b_v2_1 = b_v2_1;
		this.b_v2_2 = b_v2_2;
		this.b_v3 = b_v3;
		this.b_v3_1 = b_v3_1;
		this.b_v3_2 = b_v3_2;
		this.b_v3_3 = b_v3_3;
	}
	public Member() {
	}

	@Override
	public String toString() {
		return "Member{" +
				"id=" + id +
				", m_dai1=" + m_dai1 +
				", m_dai2=" + m_dai2 +
				", m_dai3=" + m_dai3 +
				", m_v1=" + m_v1 +
				", m_v1_1=" + m_v1_1 +
				", m_v2=" + m_v2 +
				", m_v2_1=" + m_v2_1 +
				", m_v2_2=" + m_v2_2 +
				", m_v3=" + m_v3 +
				", m_v3_1=" + m_v3_1 +
				", m_v3_2=" + m_v3_2 +
				", m_v3_3=" + m_v3_3 +
				", m_v2_service=" + m_v2_service +
				", b_dai1=" + b_dai1 +
				", b_dai2=" + b_dai2 +
				", b_dai3=" + b_dai3 +
				", b_v1=" + b_v1 +
				", b_v1_1=" + b_v1_1 +
				", b_v2=" + b_v2 +
				", b_v2_1=" + b_v2_1 +
				", b_v2_2=" + b_v2_2 +
				", b_v3=" + b_v3 +
				", b_v3_1=" + b_v3_1 +
				", b_v3_2=" + b_v3_2 +
				", b_v3_3=" + b_v3_3 +
				'}';
	}
}
