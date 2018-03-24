package com.huyu.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Admin;
@Repository
public class AdminDao {
	@Resource
	private SqlSessionTemplate sm;
	public Admin getadmin(String admin_name) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Admin.getadmin", admin_name);
	}

}
