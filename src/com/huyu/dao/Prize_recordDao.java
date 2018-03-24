package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Prize_record;

@Repository
public class Prize_recordDao {
	@Resource
	private SqlSessionTemplate sm;

	public int add(Prize_record prize_record) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Prize_record.add", prize_record);
	}

	public List<Prize_record> listByuser_id(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Prize_record.listByuser_id", user_id);
	}

	public List<Prize_record> listBynew(String type) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Prize_record.listBynew",type);
	}

}
