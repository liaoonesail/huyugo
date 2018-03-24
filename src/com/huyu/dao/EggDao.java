package com.huyu.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Egg;

@Repository
public class EggDao {
	@Resource
	private SqlSessionTemplate sm;

	public int add(Egg pk) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Egg.add", pk);
	}

	public int update(Egg pk) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Egg.update", pk);
	}

	public Egg getOne() {
		return sm.selectOne("com.huyu.entity.Egg.getone");
	}
}
