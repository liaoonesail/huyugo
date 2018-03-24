package com.huyu.dao;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Goods_type_type;
@Repository
public class Goods_type_typeDao {
	@Resource
	private SqlSessionTemplate sm;
	public List<Goods_type_type> listByid(Integer goods_type_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods_type_type.listByid", goods_type_id);
	}
	public void add(Goods_type_type type) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Goods_type_type.add", type);
	}
	public int count(String type) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Goods_type_type.getCount", type);
	}
	public List<Goods_type_type> list(String goods_type_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods_type_type.getList", goods_type_id);
	}
	public Goods_type_type getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Goods_type_type.getById", id);
	}
	public void update(Goods_type_type type) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Goods_type_type.update", type);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Goods_type_type.del", id);
	}

}
