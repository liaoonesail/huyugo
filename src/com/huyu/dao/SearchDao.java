package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Search;
@Repository
public class SearchDao {
	@Resource
	private SqlSessionTemplate sm;
	public Search getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Search.getById", id);
	}
	public List<Search> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Search.list");
	}
	public void add(Search search) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Search.add", search);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Search.del", id);
	}
	public List<Search> listByDisplay(Integer display) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Search.listByDisplay", display);
	}
	public void update(Search search) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Search.update", search);
	}
	

}
