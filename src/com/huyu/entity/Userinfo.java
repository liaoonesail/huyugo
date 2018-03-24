package com.huyu.entity;

public class Userinfo {
	private int id;
	private String head_picture;//头像
	private String nickname;//昵称
	private String email;//邮箱
	private String phone;//电话
	private String qq;//QQ号
	private String wechat;//微信号
	private String idcard;//身份证号
	private String bankcard;//银行卡号
	private String bankname;//银行名称
	private String birthday;//生日
	private String sex;//性别   ：男  or 女
	private String realname;//真实姓名
	private int user_id;//用户ID
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHead_picture() {
		return head_picture;
	}

	public void setHead_picture(String head_picture) {
		this.head_picture = head_picture;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getBankcard() {
		return bankcard;
	}

	public void setBankcard(String bankcard) {
		this.bankcard = bankcard;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public Userinfo(int id, String head_picture, String nickname, String email,
			String phone, String qq, String wechat, String idcard,
			String bankcard, String bankname, String birthday, String sex,
			String realname, int user_id) {
		super();
		this.id = id;
		this.head_picture = head_picture;
		this.nickname = nickname;
		this.email = email;
		this.phone = phone;
		this.qq = qq;
		this.wechat = wechat;
		this.idcard = idcard;
		this.bankcard = bankcard;
		this.bankname = bankname;
		this.birthday = birthday;
		this.sex = sex;
		this.realname = realname;
		this.user_id = user_id;
	}
	
	public Userinfo(String head_picture, String nickname, String email,
			String phone, String qq, String wechat, String idcard,
			String bankcard, String bankname, String birthday, String sex,
			String realname, int user_id) {
		super();
		this.head_picture = head_picture;
		this.nickname = nickname;
		this.email = email;
		this.phone = phone;
		this.qq = qq;
		this.wechat = wechat;
		this.idcard = idcard;
		this.bankcard = bankcard;
		this.bankname = bankname;
		this.birthday = birthday;
		this.sex = sex;
		this.realname = realname;
		this.user_id = user_id;
	}

	public Userinfo() {
		super();
	}
	

}
