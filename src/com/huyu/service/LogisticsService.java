package com.huyu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.LogisticsDao;
import com.huyu.entity.Logistics;

@Service
@Transactional
public class LogisticsService {
	@Resource
	private LogisticsDao dao;
	
	/**
	 * 创建物流信息
	 * @param logistics
	 */
	public void addLogistics(Logistics logistics){
		dao.add(logistics);
	}
	
	/**
	 * 更新物流信息
	 * 根据 order_id && goods_id 
	 * @param logistics
	 */
	public void update(Logistics logistics){
		dao.update(logistics);
	}
	
	/**
	 * 根据订单id获取物流信息
	 * @param id 订单id
	 * @return
	 */
	public Logistics getByOrderId(Integer orderId,Integer goodsId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_id", orderId);
		map.put("goods_id", goodsId);
		return dao.getByOrderIdAndGoodsId(map);
	}
	
	/**
	 * 获取单个物流信息
	 * @param logistics 根据 order_id && goods_id 获取
	 * @return
	 */
	public Logistics getid(Integer order_id,Integer goods_id){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("order_id", order_id);
		map.put("goods_id", goods_id);
		return dao.getid(map);
	}
	
	/**
	 * 根据订单id删除物流信息
	 * @param orderId 订单id
	 */
	public void delByOrder(Integer orderId){
		dao.delByOrder(orderId);
	}
	
	/**
	 * 删除物流信息
	 * @param id
	 */
	public void del(Integer id){
		dao.del(id);
	}
}
