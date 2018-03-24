package com.huyu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.GoodsDao;
import com.huyu.entity.Goods;

@Service
@Transactional
public class GoodsService {
	@Resource
	private GoodsDao dao;

	/**
	 * 
	 * @param goods_type_type_id
	 *            根据goods_type_type_id查询此类型下的商品集合
	 * @return
	 */
	public List<Goods> listByid(Integer goods_type_type_id) {
		// TODO Auto-generated method stub
		return dao.listByid(goods_type_type_id);
	}

	/**
	 * 按人气查询最多8个商品
	 * 
	 * @return
	 */
	public List<Goods> listByhot() {
		return dao.listByhot();
	}

	/**
	 * 按最新查询最多8个商品
	 * 
	 * @return
	 */
	public List<Goods> listBynew() {
		return dao.listBynew();
	}

	/**
	 * 按是否推荐查询8个商品
	 * 
	 * @return
	 */
	public List<Goods> listByrecommend() {
		return dao.listByrecommend();
	}

	/**
	 * 
	 * @param goods
	 *            添加商品
	 */
	public void add(Goods goods) {
		// TODO Auto-generated method stub
		dao.add(goods);
	}

	/**
	 * 
	 * @param name
	 *            根据商品名称模糊查询类似商品的数量
	 * @return
	 */
	public int count(String goods_name) {
		// TODO Auto-generated method stub
		return dao.count(goods_name);
	}

	/**
	 * 
	 * @param map
	 *            查询所有商品
	 * @return
	 */
	public List<Goods> list(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.list(map);
	}

	/**
	 * 
	 * @param id
	 *            获取单个商品对象 需要获取商品详情，请调用GoodsAction中的getid(int id)方法
	 * @return
	 */
	public Goods getid(int id) {
		// TODO Auto-generated method stub
		Goods goods = dao.getid(id);
		int clickNum = goods.getClickNum() + 1;
		goods.setClickNum(clickNum);
		dao.updateClickNum(goods);
		return dao.getid(id);
	}

	/**
	 * 
	 * @param goods
	 *            根据id修改单个商品
	 */
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		dao.update(goods);
	}

	/**
	 * 
	 * @param id
	 *            删除单个商品
	 * @return
	 */
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return dao.del(id);
	}

	/**
	 * 
	 * @param goods_type_id
	 *            根据goods_type_查询此类型下的商品集合
	 * @param status
	 *            0 、1 、2 、3、4 分别代表综合 、商品价格高 、商品价格低 、最新、热度
	 * @return
	 */
	public List<Goods> listbytypeid(Integer id, Integer status) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		map.put("status", status);
		return dao.listbytypeid(map);
	}

	/**
	 * 
	 * @param name
	 *            按商品名字模糊查询
	 * @return
	 */
	public List<Goods> listByname(String goods_name) {
		return dao.listByname(goods_name);
	}

	/**
	 * 
	 * @param type_type_id
	 * @param status
	 *            status 0 、1 、2 、3 、4分别代表综合 、商品价格高 、商品价格低 、最新、热度
	 * @return
	 */
	public List<Goods> listbytype_type_id(Integer type_type_id, Integer status) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("type_type_id", type_type_id);
		map.put("status", status);
		return dao.listbytype_type_id(map);
	}

	public List<Goods> goodsByTuiJian(Integer type_type_id, Integer status) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("type_type_id", type_type_id);
		map.put("status", status);
		return dao.goodsByTuiJian(map);
	}

	/**
	 * 获取是否上架商品
	 * 
	 * @param display
	 *            是否上架 0上架 1下架
	 * @return
	 */
	public List<Goods> getGoodsByDisplay(Integer display) {
		return dao.getGoodsByDisplay(display);
	}

	/**
	 * 获取缺货产品
	 * 
	 * @return
	 */
	public List<Goods> getGoodsByStockout() {
		return dao.getGoodsByStockout();
	}

	/**
	 * 根据点击量获取商品
	 * 
	 * @return
	 */
	public List<Goods> getGoodsByClickNum() {
		return dao.getGoodsByClickNum();
	}

}
