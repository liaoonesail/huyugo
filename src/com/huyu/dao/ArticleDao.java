package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Article;

@Repository
public class ArticleDao {
	@Resource
	private SqlSessionTemplate sm;

	public void add(Article article) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Article.add", article);
	}

	public List<Article> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Article.list");
	}
	
	public List<Article> getByType(int type) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Article.getByType",type);
	}

	public Article getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Article.getid", id);
	}

	public void update(Article article) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Article.update", article);
	}

	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Article.del",id);
	}
}
