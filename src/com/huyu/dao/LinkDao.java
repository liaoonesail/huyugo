package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Link;
@Repository
public class LinkDao {
	@Resource
	private SqlSessionTemplate sm;
	public void add(Link link) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Link.add", link);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Link.del",id);
	}
	public List<Link> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Link.list");
	}
	public Link getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Link.getById", id);
	}
	public void update(Link link) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Link.update", link);
	}

}
