package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import com.huyu.entity.Goods;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.ShopDao;
import com.huyu.entity.Shop;

@Service
@Transactional
public class ShopService {
	@Resource
	private ShopDao dao;
	
	/**
	 * 添加供应商图片
	 * @param e
	 */
	public void add(Shop e) {
		dao.add(e);
	}
	
	/**
	 * 删除方法
	 * @param id
	 */
	public void del(Integer id){
		dao.del(id);
	}
	
	/**
	 * 获取供应商集合
	 * @return
	 */
	public List<Shop> list(){
		return dao.list();
	}
	
	/**
	 * 更新
	 * @param e
	 */
	public void update(Shop e) {
		dao.update(e);
	}
	
	/**
	 * 获取单个供应商图片
	 * @param id
	 * @return
	 */
	public Shop getById(Integer id){
		return dao.getById(id);
	}
	/**
	 * 获取单个供应商图片
	 * @param
	 * @return
	 */
	public Shop getByName(String name){
		return dao.getByName(name);
	}

	public List<Goods> goodsList(Integer shopid) {
		return dao.goodsList(shopid);
	}
}
