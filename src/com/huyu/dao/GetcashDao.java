package com.huyu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Getcash;
@Repository
public class GetcashDao {
	@Resource
	private SqlSessionTemplate sm;
	
	public int count(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Getcash.count", map);
	}

	public List<Getcash> listpage(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Getcash.listpage", map);
	}

	public void add(Getcash getcash) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Getcash.add", getcash);
	}

	public int del(Integer id) {
		// TODO Auto-generated method stub
		return sm.delete("com.huyu.entity.Getcash.del", id);
	}

	public Getcash getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Getcash.getById", id);
	}

	public void updatestatus(Getcash getcash) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Getcash.update", getcash);
	}

	public List<Getcash> cashList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Getcash.cashList", map);
	}
	
	public List<Getcash> getByUserId(HashMap<String, Object> map) {
		return sm.selectList("com.huyu.entity.Getcash.getByUserId", map);
	}

	public int countByUid(Integer userId) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Getcash.countByUid", userId);
	}

}
