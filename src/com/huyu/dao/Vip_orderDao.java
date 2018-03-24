package com.huyu.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Vip_order;
@Repository
public class Vip_orderDao {
	@Resource
	private SqlSessionTemplate sm;
	public int add(Vip_order vip_order) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Vip_order.add", vip_order);
	}
	public int update(Vip_order vip_order) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Vip_order.update", vip_order);
	}
	public Vip_order getvip_order(String order_num) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Vip_order.getvip_order", order_num);
	}
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return sm.delete("com.huyu.entity.Vip_order.del", id);
	}
	public Vip_order getid(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Vip_order.getid", user_id);
	}
	public int updateOrderNumTotal(Vip_order vip_order) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Vip_order.updateOrderNumTotal", vip_order);
	}

}
