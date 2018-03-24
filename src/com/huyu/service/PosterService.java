package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.EggDao;
import com.huyu.dao.PosterDao;
import com.huyu.entity.Egg;
import com.huyu.entity.Poster;

@Service
@Transactional
public class PosterService {
	@Resource
	private PosterDao dao;
	
	/**
	 * 添加广告图片
	 * @param e
	 */
	public void add(Poster e) {
		dao.add(e);
	}
	
	/**
	 * 获取广告集合
	 * @return
	 */
	public List<Poster> list(){
		return dao.list();
	}
	
	/**
	 * 更新
	 * @param e
	 */
	public void update(Poster e) {
		dao.update(e);
	}
	
	/**
	 * 获取单个广告图片
	 * @param id
	 * @return
	 */
	public Poster getById(Integer id){
		return dao.getById(id);
	}
}
