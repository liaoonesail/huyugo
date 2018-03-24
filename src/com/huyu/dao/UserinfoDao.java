package com.huyu.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Userinfo;
@Repository
public class UserinfoDao {
	@Resource
	private SqlSessionTemplate sm;
	public int add(Userinfo userinfo) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Userinfo.add", userinfo);
	}
	public Userinfo getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Userinfo.getById", id);
	}
	public Userinfo getByUser_id(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Userinfo.getByUser_id", user_id);
	}
	public int update(Userinfo userinfo) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Userinfo.update", userinfo);
	}

}
