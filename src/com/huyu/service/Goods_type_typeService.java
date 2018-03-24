package com.huyu.service;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.Goods_type_typeDao;
import com.huyu.entity.Goods_type_type;

@Service
@Transactional
public class Goods_type_typeService {
	@Resource
	private Goods_type_typeDao dao;
	/**
	 * 
	 * @param goods_type_id-查询此id下的所有下级类型
	 * @return
	 */
	public List<Goods_type_type> listByid(Integer goods_type_id) {
		// TODO Auto-generated method stub
		return dao.listByid(goods_type_id);
	}
	/**
	 * 
	 * @param type 对应goods_type_id添加下级类型
	 */
	public void add(Goods_type_type type) {
		// TODO Auto-generated method stub
		dao.add(type);
	}
	/**
	 * 
	 * @param type 查询对应goods_type_id下级类型的数量
	 * @return
	 */
	public int count(String type) {
		// TODO Auto-generated method stub
		return dao.count(type);
	}
	/**
	 * 
	 * @param map 查询对应goods_type_id下级类型对象集合
	 * @return
	 */
	public List<Goods_type_type> list(String goods_type_id) {
		// TODO Auto-generated method stub
		return dao.list(goods_type_id);
	}
	/**
	 * 
	 * @param id 根据id查询goods_type_id的单个下级类型
	 * @return
	 */
	public Goods_type_type getid(Integer id) {
		// TODO Auto-generated method stub
		return dao.getid(id);
	}
	/**
	 * 
	 * @param type 根据id修改goods_type_id的单个下级类型
	 */
	public void update(Goods_type_type type) {
		// TODO Auto-generated method stub
		dao.update(type);
	}
	/**
	 * 
	 * @param id 删除指定的goods_type_id的单个下级类型
	 */
	public void del(Integer id) {
		// TODO Auto-generated method stub
		dao.del(id);
	}
}
