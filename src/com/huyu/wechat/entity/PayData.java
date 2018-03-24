package com.huyu.wechat.entity;

public class PayData {
	private String appid;
	private String mch_id;
	private String device_info;
	private String body;
	private String trade_type;
	private String nonce_str;
	private String notify_url;
	private String out_trade_no;
	private String total_fee;
	private String openid;
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTrade_type() {
		return trade_type;
	}
	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public PayData(String appid, String mch_id, String device_info,
			String body, String trade_type, String nonce_str,
			String notify_url, String out_trade_no, String total_fee,
			String openid) {
		super();
		this.appid = appid;
		this.mch_id = mch_id;
		this.device_info = device_info;
		this.body = body;
		this.trade_type = trade_type;
		this.nonce_str = nonce_str;
		this.notify_url = notify_url;
		this.out_trade_no = out_trade_no;
		this.total_fee = total_fee;
		this.openid = openid;
	}
	public PayData() {
	
	}

}
