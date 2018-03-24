package com.huyu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Receipt_Payment;

@Repository
public class Receipt_PaymentDao {
	@Resource
	private SqlSessionTemplate sm;

	public void add(Receipt_Payment receipt_Payment) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Receipt_Payment.add", receipt_Payment);
	}

	public List<Receipt_Payment> list(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Receipt_Payment.list", user_id);
	}

	public List<Receipt_Payment> listBytype(HashMap<String,Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Receipt_Payment.listBytype", map);
	}
	
	public List<Receipt_Payment> listByLayuiAndType(HashMap<String,Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Receipt_Payment.listByLayuiAndType", map);
	}

	public List<Receipt_Payment> listBytype_1_5(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Receipt_Payment.listBytype_1_5", user_id);
	}

	public List<Receipt_Payment> share(Integer user_id, Integer goods_id,
			Integer share_way, String date_time) {
		// TODO Auto-generated method stub
		Map<String, String> map=new HashMap<String, String>();
		map.put("user_id", user_id+"");
		map.put("goods_id", goods_id+"");
		map.put("share_way", share_way+"");
		map.put("date_time", date_time);
		return sm.selectList("com.huyu.entity.Receipt_Payment.share", map);
	}

	public int count(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Receipt_Payment.count", map);
	}
	
	public List<Receipt_Payment> listByOrderTimeAndUserId(Map<String, Object> map){
		return sm.selectList("com.huyu.entity.Receipt_Payment.listByOrderTimeAndUserId", map);
	}

	public int countf(String name) {
		return sm.selectOne("com.huyu.entity.Receipt_Payment.countf",name);
	}

	public List<Receipt_Payment> listPage(HashMap<String, Object> map) {
		return sm.selectList("com.huyu.entity.Receipt_Payment.listPage",map);
	}

	public String getRpdetails(Map<Object, Object> map) {
		return sm.selectOne("com.huyu.entity.Receipt_Payment.getRpdetails",map);
	}
}
