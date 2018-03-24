package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Miaosha;
@Repository
public class MiaoshaDao {
	@Resource
	private SqlSessionTemplate sm;
	public void add(Miaosha miaosha) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Miaosha.add", miaosha);
	}
	public List<Miaosha> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Miaosha.list");
	}
	public void update(Miaosha miaosha) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Miaosha.update", miaosha);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Miaosha.del", id);
	}
	public Miaosha getgoods_id(Integer goods_id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Miaosha.getgoods_id", goods_id);
	}
	public Miaosha getid(Integer id){
		return sm.selectOne("com.huyu.entity.Miaosha.getid",id);
	}
}
