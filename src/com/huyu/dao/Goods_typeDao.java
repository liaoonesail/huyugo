package com.huyu.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Goods_type;
@Repository
public class Goods_typeDao {
	@Resource
	private SqlSessionTemplate sm;
	public void add(Goods_type type) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Goods_type.add", type);
	}
	public int count(String type) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Goods_type.getCount", type);
	}
	public List<Goods_type> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods_type.getList");
	}
	 public Goods_type getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Goods_type.getById", id);
	}
	public void update(Goods_type type) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Goods_type.update", type);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Goods_type.del", id);
	}

}
