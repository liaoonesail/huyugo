package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Affiche;

@Repository
public class AfficheDao {
	@Resource
	private SqlSessionTemplate sm;

	public void add(Affiche affiche) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Affiche.add", affiche);
	}

	public List<Affiche> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Affiche.list");
	}

	public Affiche getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Affiche.getid", id);
	}

	public void update(Affiche affiche) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Affiche.update", affiche);
	}

	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Affiche.del",id);
	}
}
