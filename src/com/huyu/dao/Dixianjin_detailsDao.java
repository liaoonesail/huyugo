package com.huyu.dao;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Dixianjin_details;

@Repository
public class Dixianjin_detailsDao {
	@Resource
	private SqlSessionTemplate sm;

	public int add(Dixianjin_details dixianjin_details) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Dixianjin_details.add", dixianjin_details);
	}

	public Dixianjin_details getid(Integer user_id, Integer order_id,
			Integer goods_id) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("user_id", user_id);
		map.put("order_id", order_id);
		map.put("goods_id", goods_id);
		return sm.selectOne("com.huyu.entity.Dixianjin_details.getid", map);
	}

	public int update(Dixianjin_details dixianjin_details) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Dixianjin_details.update", dixianjin_details);
	}
}
