package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Sign;

@Repository
public class SignDao {
	@Resource
	private SqlSessionTemplate sm;

	public List<Sign> getlist(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Sign.getList", user_id);
	}

	public int add(Sign sign) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Sign.add", sign);
	}
}
