package com.huyu.entity;

/**
 * 会员之间转账
 * 
 * @author Administrator
 * 
 */
public class TransferAccounts {

	private int id;
	private int shiftTo;// 转入方用户id
	private int rollOut;// 转出方用户id
	private int type;// 转账类型 （1余额，2积分，3抵现金，4互余币）
	private String time;// 转账时间
	private double money;// 转账金额

	public TransferAccounts() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransferAccounts(int shiftTo, int rollOut, int type, String time,
			double money) {
		super();
		this.shiftTo = shiftTo;
		this.rollOut = rollOut;
		this.type = type;
		this.time = time;
		this.money = money;
	}

	public TransferAccounts(int id, int shiftTo, int rollOut, int type,
			String time, double money) {
		super();
		this.id = id;
		this.shiftTo = shiftTo;
		this.rollOut = rollOut;
		this.type = type;
		this.time = time;
		this.money = money;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getShiftTo() {
		return shiftTo;
	}

	public void setShiftTo(int shiftTo) {
		this.shiftTo = shiftTo;
	}

	public int getRollOut() {
		return rollOut;
	}

	public void setRollOut(int rollOut) {
		this.rollOut = rollOut;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "TransferAccounts [id=" + id + ", shiftTo=" + shiftTo
				+ ", rollOut=" + rollOut + ", type=" + type + ", time=" + time
				+ ", money=" + money + "]";
	}

}
