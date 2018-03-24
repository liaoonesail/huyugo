package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Poster;


@Repository
public class PosterDao {
	@Resource
	private SqlSessionTemplate sm;
	public int add(Poster poster) {
		return sm.insert("com.huyu.entity.Poster.add", poster);
	}
	public List<Poster> list() {
		return sm.selectList("com.huyu.entity.Poster.list");
	}
	public int del(Integer id) {
		return sm.delete("com.huyu.entity.Poster.del", id);
	}
	public int update(Poster poster) {
		return sm.update("com.huyu.entity.Poster.update", poster);
	}
	public Poster getById(Integer id){
		return sm.selectOne("com.huyu.entity.Poster.getById", id);
	}
	
	
}
