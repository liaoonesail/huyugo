package com.huyu.dao;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Prize;
@Repository
public class PrizeDao {
	@Resource
	private SqlSessionTemplate sm;
	public void add(Prize prize) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Prize.add", prize);
	}
	public void update(Prize prize) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Prize.update", prize);
	}
	public Prize getprize() {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Prize.getprize");
	}
	public void del() {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Prize.del");
	}

}
