package com.huyu.dao;

import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.PrizeInkind;

@Repository
public class PrizeInkindDao {
	@Resource
	private SqlSessionTemplate sm;
	public int add(PrizeInkind pk) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.PrizeInkind.add", pk);
	}
	public int update(PrizeInkind pk) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.PrizeInkind.update", pk);
	}
	public PrizeInkind getOne(){
		return sm.selectOne("com.huyu.entity.PrizeInkind.getone");
	}
}
