package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.ArticleDao;
import com.huyu.entity.Article;
import com.huyu.util.ReadWriteTxt;

@Service
@Transactional
public class ArticleService {
	@Resource
	private ArticleDao dao;

	public void add(Article Article) {
		// TODO Auto-generated method stub
		dao.add(Article);
	}
	/**
	 * 
	 * @return 查询文章列表 不包含文章详情
	 */
	public List<Article> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	
	/**
	 * 根据类别获取文章
	 * @param type
	 * @return
	 */
	public List<Article> getByType(int type) {
		// TODO Auto-generated method stub
		return dao.getByType(type);
	}
	/**
	 * 
	 * @param id 获取单个文章 包含详情
 	 * @return
	 */
	public Article getid(Integer id,HttpServletRequest request) {
		// TODO Auto-generated method stub
		System.out.println("--------------");
		System.out.println(id);
		System.out.println("--------------");
		Article article=dao.getid(id);
		String read = ReadWriteTxt.read(article.getPath(), request);
		article.setPath(read);
		return article;
	}
	
	/**
	 * 
	 * @param id 获取单个文章 不包含详情
	 * @return
	 */
	public Article getid(Integer id){
		return dao.getid(id);
	}
	/**
	 * 
	 * @param Article 修改文章
	 */
	public void update(Article article) {
		// TODO Auto-generated method stub
		dao.update(article);
	}
	/**
	 * 
	 * @param id 删除文章
	 */
	public void del(Integer id) {
		// TODO Auto-generated method stub
		dao.del(id);
	}
}
