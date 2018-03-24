package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Qianggou;
@Repository
public class QianggouDao {
	@Resource
	private SqlSessionTemplate sm;
	public void add(Qianggou qianggou) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Qianggou.add", qianggou);
	}
	public List<Qianggou> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Qianggou.list");
	}
	public void update(Qianggou qianggou) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Qianggou.update", qianggou);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Qianggou.del", id);
	}
	public List<Qianggou> listBytoday() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Qianggou.listBytoday");
	}
	public Qianggou getgoods_id(Integer goods_id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Qianggou.getgoods_id", goods_id);
	}
	public Qianggou getid(Integer id){
		return sm.selectOne("com.huyu.entity.Qianggou.getid", id);
	}

}
