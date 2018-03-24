package com.huyu.dao;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;


import com.huyu.entity.Logistics;

@Repository
public class LogisticsDao {
	@Resource
	private SqlSessionTemplate sm;

	/**
	 * 添加物流
	 * @param logistics
	 */
	public void add(Logistics logistics){
		sm.insert("com.huyu.entity.Logistics.add", logistics);
	}
	
	/**
	 * 删除物流信息
	 * @param id
	 */
	public void del(Integer id){
		sm.delete("com.huyu.entity.Logistics.del", id);
	}
	
	public void delByOrder(Integer orderId){
		sm.delete("com.huyu.entity.Logistics.delByOrder", orderId);
	}
	
	/**
	 * 获取单个物流信息
	 * @param logistics
	 * @return
	 */
	public Logistics getid(Map<String, Object> map){
		return sm.selectOne("com.huyu.entity.Logistics.getid", map);
	}
	
	public Logistics getByOrderIdAndGoodsId(Map<String,Object> map){
		return sm.selectOne("com.huyu.entity.Logistics.getByOrderId", map);
	}
	
	/**
	 * 更新物流信息
	 * @param logistics
	 */
	public void update(Logistics logistics){
		sm.update("com.huyu.entity.Logistics.update", logistics);
	}
	
	/*public int count(String name){
		return sm.selectOne("com.huyu.entity.Logistics.count", name);
	}*/
	
	/**
	 * 获取物流信息
	 * @param map
	 * @return
	 */
	/*public List<Logistics> getLogisticsByPage(HashMap<String, Object> map){
		return sm.selectList("com.huyu.entity.Logistics.getLogisticsByPage", map);
	}*/
}
