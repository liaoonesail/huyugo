package com.huyu.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.Goods_typeDao;
import com.huyu.entity.Goods_type;
@Service
@Transactional
public class Goods_typeService {
	@Resource
	private Goods_typeDao dao;
	/**
	 * 
	 * @param type 添加分类
	 */
	public void add(Goods_type type) {
		// TODO Auto-generated method stub
		dao.add(type);
	}
	/**
	 * 
	 * @param type 查现分类数量
	 * @return
	 */
	public int count(String type) {
		// TODO Auto-generated method stub
		return dao.count(type);
	}
	/**
	 * 
	 * @param map 查询分类集合
	 * @return
	 */
	public List<Goods_type> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	/**
	 * 
	 * @param id 获取单个分类的对象
	 * @return
	 */
	public Goods_type getid(Integer id) {
		// TODO Auto-generated method stub
		return dao.getid(id);
	}
	/**
	 * 
	 * @param type 根据id修给单个分类
	 */
	public void update(Goods_type type) {
		// TODO Auto-generated method stub
		dao.update(type);
	}
	/**
	 * 
	 * @param id 删除单个分类
	 */
	public void del(Integer id) {
		// TODO Auto-generated method stub
		dao.del(id);
	}

}
