package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.CuxiaoDao;
import com.huyu.entity.Cuxiao;
import com.huyu.entity.Miaosha;

@Service
@Transactional
public class CuxiaoService {
	@Resource
	private CuxiaoDao dao;
	/**
	 * 
	 * @param cuxiao 低价促销商品添加
	 */
	public void add(Cuxiao cuxiao) {
		// TODO Auto-generated method stub
		dao.add(cuxiao);
	}
	/**
	 * 
	 * @return 低价促销所有商品列表
	 */
	public List<Cuxiao> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	/**
	 * 
	 * @param goods_id 根据商品ID查询促销活动商品
	 * @return
	 */
	public Cuxiao getgoods_id(Integer goods_id){
		return dao.getgoods_id(goods_id);
	}
	/**
	 * 
	 * @param cuxiao 低价促销商品修改
	 */
	public void update(Cuxiao cuxiao) {
		// TODO Auto-generated method stub
		dao.update(cuxiao);
	}
	/**
	 * 
	 * @param id 删除低价促销商品下的单个商品
	 */
	public void del(Integer id) {
		// TODO Auto-generated method stub
		dao.del(id);
	}
	
}
