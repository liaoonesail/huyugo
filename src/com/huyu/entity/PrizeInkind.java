package com.huyu.entity;

/**
 * 大转盘奖项
 * 
 * @author zs
 * @time 2017年8月4日13:56:57
 */
public class PrizeInkind {
	private int id;
	private int prize1;
	private int prize2;
	private int prize3;
	private int prize4;
	private int prize5;

	public PrizeInkind() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PrizeInkind(int prize1, int prize2, int prize3, int prize4,
			int prize5) {
		super();
		this.prize1 = prize1;
		this.prize2 = prize2;
		this.prize3 = prize3;
		this.prize4 = prize4;
		this.prize5 = prize5;
	}

	public PrizeInkind(int id, int prize1, int prize2, int prize3, int prize4,
			int prize5) {
		super();
		this.id = id;
		this.prize1 = prize1;
		this.prize2 = prize2;
		this.prize3 = prize3;
		this.prize4 = prize4;
		this.prize5 = prize5;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrize1() {
		return prize1;
	}

	public void setPrize1(int prize1) {
		this.prize1 = prize1;
	}

	public int getPrize2() {
		return prize2;
	}

	public void setPrize2(int prize2) {
		this.prize2 = prize2;
	}

	public int getPrize3() {
		return prize3;
	}

	public void setPrize3(int prize3) {
		this.prize3 = prize3;
	}

	public int getPrize4() {
		return prize4;
	}

	public void setPrize4(int prize4) {
		this.prize4 = prize4;
	}

	public int getPrize5() {
		return prize5;
	}

	public void setPrize5(int prize5) {
		this.prize5 = prize5;
	}

	@Override
	public String toString() {
		return "PrizeInkind [id=" + id + ", prize1=" + prize1 + ", prize2="
				+ prize2 + ", prize3=" + prize3 + ", prize4=" + prize4
				+ ", prize5=" + prize5 + "]";
	}

}
