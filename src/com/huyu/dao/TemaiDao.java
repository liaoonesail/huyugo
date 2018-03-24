package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Temai;

@Repository
public class TemaiDao {
	@Resource
	private SqlSessionTemplate sm;

	public void add(Temai temai) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Temai.add", temai);
	}
	
	public Temai getid(Integer id){
		return sm.selectOne("com.huyu.entity.Temai.getid",id);
	}

	public List<Temai> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Temai.list");
	}

	public void update(Temai temai) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Temai.update", temai);
	}

	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Temai.del", id);
	}

	public List<Temai> listBytoday() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Temai.listBytoday");
	}

	public Temai getgoods_id(Integer goods_id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Temai.getgoods_id", goods_id);
	}
}
