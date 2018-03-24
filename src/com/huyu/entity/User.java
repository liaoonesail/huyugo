package com.huyu.entity;

public class User {
	private int id;
	private String name;// 用户名
	private String phone;// 用户电话
	private String email;// 用户邮箱
	private String password;// 用户密码
	private int member;// 是否为会员0=否，1=是
	private double  integral;// 积分
	private int member_grade;// 会员等级0,1,2,3
	private int dixianjin;// 抵现金
	private int d_dixianjin;// 冻结的抵现金
	private double huyubi;// 互余币
	private double account;// 账户
	private int superior_id;// 上级id
	private String reg_time;// 注册时间
	private String member_time;// 会员开通时间
	private double pension;// 养老金
	private String pensionTime;// 初次有养老金的时间
	private double fazhanjin;//发展金总数
	private int fazhanStatus;//是否购买过发展金
	private int isfuwu;//0默认1关闭服务2开通服务
	private int free;//0,1 已经使用，未使用
	private Userinfo userInfo;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String name, String phone, String email, String password, int member, double integral, int member_grade, int dixianjin, int d_dixianjin, double huyubi, double account, int superior_id, String reg_time, String member_time, double pension, String pensionTime, double fazhanjin, int fazhanStatus, Userinfo userInfo) {
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.member = member;
		this.integral = integral;
		this.member_grade = member_grade;
		this.dixianjin = dixianjin;
		this.d_dixianjin = d_dixianjin;
		this.huyubi = huyubi;
		this.account = account;
		this.superior_id = superior_id;
		this.reg_time = reg_time;
		this.member_time = member_time;
		this.pension = pension;
		this.pensionTime = pensionTime;
		this.fazhanjin = fazhanjin;
		this.fazhanStatus = fazhanStatus;
		this.userInfo = userInfo;
	}

	public User(String name, String phone, String email, String password, int member, double integral, int member_grade, int dixianjin, int d_dixianjin, double huyubi, double account, int superior_id, String reg_time, String member_time, double pension, String pensionTime, double fazhanjin, int fazhanStatus, Userinfo userInfo) {
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.member = member;
		this.integral = integral;
		this.member_grade = member_grade;
		this.dixianjin = dixianjin;
		this.d_dixianjin = d_dixianjin;
		this.huyubi = huyubi;
		this.account = account;
		this.superior_id = superior_id;
		this.reg_time = reg_time;
		this.member_time = member_time;
		this.pension = pension;
		this.pensionTime = pensionTime;
		this.fazhanjin = fazhanjin;
		this.fazhanStatus = fazhanStatus;
		this.userInfo = userInfo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMember() {
		return member;
	}

	public void setMember(int member) {
		this.member = member;
	}

	public double  getIntegral() {
		return integral;
	}

	public void setIntegral(double integral) {
		this.integral = integral;
	}

	public int getMember_grade() {
		return member_grade;
	}

	public void setMember_grade(int member_grade) {
		this.member_grade = member_grade;
	}

	public int getDixianjin() {
		return dixianjin;
	}

	public void setDixianjin(int dixianjin) {
		this.dixianjin = dixianjin;
	}

	public int getD_dixianjin() {
		return d_dixianjin;
	}

	public void setD_dixianjin(int d_dixianjin) {
		this.d_dixianjin = d_dixianjin;
	}

	public double getHuyubi() {
		return huyubi;
	}

	public void setHuyubi(double huyubi) {
		this.huyubi = huyubi;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public int getSuperior_id() {
		return superior_id;
	}

	public void setSuperior_id(int superior_id) {
		this.superior_id = superior_id;
	}

	public String getReg_time() {
		return reg_time;
	}

	public void setReg_time(String reg_time) {
		this.reg_time = reg_time;
	}

	public String getMember_time() {
		return member_time;
	}

	public void setMember_time(String member_time) {
		this.member_time = member_time;
	}

	public double getPension() {
		return pension;
	}

	public void setPension(double pension) {
		this.pension = pension;
	}

	public String getPensionTime() {
		return pensionTime;
	}

	public void setPensionTime(String pensionTime) {
		this.pensionTime = pensionTime;
	}

	public Userinfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(Userinfo userInfo) {
		this.userInfo = userInfo;
	}

	public double getFazhanjin() {
		return fazhanjin;
	}

	public void setFazhanjin(double fazhanjin) {
		this.fazhanjin = fazhanjin;
	}

	public int getFazhanStatus() {
		return fazhanStatus;
	}

	public void setFazhanStatus(int fazhanStatus) {
		this.fazhanStatus = fazhanStatus;
	}

	public int getIsfuwu() {
		return isfuwu;
	}

	public void setIsfuwu(int isfuwu) {
		this.isfuwu = isfuwu;
	}

	public int getFree() {
		return free;
	}

	public void setFree(int free) {
		this.free = free;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", member=" + member +
				", integral=" + integral +
				", member_grade=" + member_grade +
				", dixianjin=" + dixianjin +
				", d_dixianjin=" + d_dixianjin +
				", huyubi=" + huyubi +
				", account=" + account +
				", superior_id=" + superior_id +
				", reg_time='" + reg_time + '\'' +
				", member_time='" + member_time + '\'' +
				", pension=" + pension +
				", pensionTime='" + pensionTime + '\'' +
				", fazhanjin=" + fazhanjin +
				", fazhanStatus=" + fazhanStatus +
				", isfuwu=" + isfuwu +
				", free=" + free +
				", userInfo=" + userInfo +
				'}';
	}
}
