package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.Order_detailsDao;
import com.huyu.entity.Order_details;

@Service
@Transactional
public class Order_detailsService {
	@Resource
	private Order_detailsDao dao;
	public int add(Order_details order_details) {
		// TODO Auto-generated method stub
		return dao.add(order_details);
	}
	public List<Order_details> listByorder_id(int order_id) {
		// TODO Auto-generated method stub
		return dao.listByorder_id(order_id);
	}
	/**
	 * 
	 * @param id 获取单个
	 * @return
	 */
	public Order_details getid(Integer id){
		return dao.getid(id);
	}
	/**
	 * 
	 * @param order_details 修改real_price by id
	 * @return
	 */
	public int update(Order_details order_details){
		int status=dao.update(order_details);
		return status;
	}
	/**
	 * 
	 * @param id 根据order_id 删除订单详情
	 */
	public void delByorder_id(Integer id) {
		// TODO Auto-generated method stub
		dao.delByorder_id(id);
	}

}
