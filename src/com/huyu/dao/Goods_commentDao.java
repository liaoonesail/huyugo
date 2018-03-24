package com.huyu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Goods_comment;

@Repository
public class Goods_commentDao {
	@Resource
	private SqlSessionTemplate sm;

	public List<Goods_comment> listBygoods_id(Integer goods_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods_comment.listBygoods_id", goods_id);
	}

	public int add(Goods_comment goods_comment) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Goods_comment.add", goods_comment);
	}

	public int del(Integer id, Integer user_id) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("id", id);
		map.put("user_id", user_id);
		return sm.delete("com.huyu.entity.Goods_comment.del", map);
	}
}
