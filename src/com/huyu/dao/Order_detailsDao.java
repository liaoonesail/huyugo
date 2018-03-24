package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Order_details;

@Repository
public class Order_detailsDao {
	@Resource
	private SqlSessionTemplate sm;

	public int add(Order_details order_details) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Order_details.add", order_details);
	}

	public List<Order_details> listByorder_id(int order_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Order_details.listByorder_id", order_id);
	}

	public Order_details getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Order_details.getid", id);
	}

	public int update(Order_details order_details) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Order_details.update", order_details);
	}

	public void delByorder_id(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Order_details.delByorder_id", id);
	}

}
