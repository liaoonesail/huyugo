package com.huyu.entity;

/**
 * 砸蛋奖品
 * 
 * @author Administrator
 * 
 */
public class Egg {
	private int id;
	private int prize1;
	private int prize2;
	private int prize3;
	private int zjl;

	public Egg() {
		super();
	}

	public Egg(int id, int prize1, int prize2, int prize3, int zjl) {
		super();
		this.id = id;
		this.prize1 = prize1;
		this.prize2 = prize2;
		this.prize3 = prize3;
		this.zjl = zjl;
	}

	public Egg(int prize1, int prize2, int prize3, int zjl) {
		super();
		this.prize1 = prize1;
		this.prize2 = prize2;
		this.prize3 = prize3;
		this.zjl = zjl;
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

	public int getZjl() {
		return zjl;
	}

	public void setZjl(int zjl) {
		this.zjl = zjl;
	}

	@Override
	public String toString() {
		return "Egg [id=" + id + ", prize1=" + prize1 + ", prize2=" + prize2
				+ ", prize3=" + prize3 + ", zjl=" + zjl + "]";
	}

}
