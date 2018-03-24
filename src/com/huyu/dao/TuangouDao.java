package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Tuangou;
@Repository
public class TuangouDao {
	@Resource
	private SqlSessionTemplate sm;
	public void add(Tuangou tuangou) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Tuangou.add", tuangou);
	}
	public List<Tuangou> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Tuangou.list");
	}
	public void update(Tuangou tuangou) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Tuangou.update", tuangou);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Tuangou.del", id);
	}
	public List<Tuangou> listBytoday() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Tuangou.listBytoday");
	}
	public Tuangou getgoods_id(Integer goods_id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Tuangou.getgoods_id", goods_id);
	}
	public Tuangou getid(Integer id){
		return sm.selectOne("com.huyu.entity.Tuangou.getid", id);
	}

}
