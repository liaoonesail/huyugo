package com.huyu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.Vip_orderDao;
import com.huyu.entity.Vip_order;

@Service
@Transactional
public class Vip_orderService {
	@Resource
	private UserService service;
	@Resource
	private Vip_orderDao dao;
	/**
	 * 
	 * @param vip_order 添加开通会员的订单
	 * @return
	 */
	public int add(Vip_order vip_order){
		vip_order.setStatus(0);
		return dao.add(vip_order);
	}
	/**
	 * 
	 * @param vip_order 修改订单状态
	 * @return
	 */
	public int update(Vip_order vip_order){
		service.regmember(vip_order.getUser_id(),vip_order.getTotal());
		return dao.update(vip_order);
	}
	/**
	 * 修改订单号和金额
	 * @param vip_order
	 * @return
	 */
	public int updateOrderNumTotal(Vip_order vip_order){
		return dao.updateOrderNumTotal(vip_order);
	}
	/**
	 * 
	 * @param order_num 根据订单号获取订单
	 * @return
	 */
	public Vip_order getvip_order(String order_num){
		return dao.getvip_order(order_num);
	}
	/**
	 * 
	 * @param id 删除订单
	 * @return
	 */
	public int del(Integer id){
		return dao.del(id);
	}
	/**
	 * 
	 * @param user_id 根据user_id 获取订单
	 * @return
	 */
	public Vip_order getid(Integer user_id){
		return dao.getid(user_id);
		}
}
