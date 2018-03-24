package com.huyu.dao;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.entity.Order;
@Transactional
@Repository
public class OrderDao {
	@Resource
	private SqlSessionTemplate sm;
	public int add(Order order) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Order.add", order);
	}
	public int update(Order order) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Order.update", order);
	}
	public int updateTime(Order order) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Order.updateTime", order);
	}
	public List<Order> list(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Order.getByUser_id", map);
	}
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return sm.delete("com.huyu.entity.Order.del", id);
	}
	public Order getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Order.getById", id);
	}
	public int updateorder(Order order) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Order.updateorder", order);
	}
	public Order getorder_num(String order_num) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Order.getorder_num", order_num);
	}
	public int count(String name, Integer status) {
		// TODO Auto-generated method stub
		Map<String, String> map=new HashMap<String, String>();
		map.put("name", name);
		map.put("status", status+"");
		return sm.selectOne("com.huyu.entity.Order.count", map);
	}
	public List<Order> listpage(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Order.listpage", map);
	}
	public int orderCount(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Order.orderCount", map);
	}
	public List<Order> orderListpage(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Order.orderListpage", map);
	}

}
