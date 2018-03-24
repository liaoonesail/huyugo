package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Tuangou_Details;
@Repository
public class Tuangou_DetailsDao {
	@Resource
	private SqlSessionTemplate sm;
	public void add(Tuangou_Details details) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Tuangou_Details.add", details);
	}
	public List<Tuangou_Details> list(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Tuangou_Details.list", user_id);
	}
	public int amount_total() {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Tuangou_Details.amount_total");
	}
	public List<Tuangou_Details> listBytuangou_id(Integer tuangou_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Tuangou_Details.listBytuangou_id", tuangou_id);
	}

}
