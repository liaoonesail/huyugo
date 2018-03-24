package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.MiaoshaDao;
import com.huyu.entity.Car;
import com.huyu.entity.Miaosha;
import com.huyu.entity.Qianggou;
@Service
@Transactional
public class MiaoshaService {
	@Resource
	private MiaoshaDao dao;
	@Resource
	private GoodsService goodsService;
	@Resource
	private CarService carService;
	/**
	 * 
	 * @param miaosha 添加秒杀活动商品
	 */
	public void add(Miaosha miaosha) {
		// TODO Auto-generated method stub
		dao.add(miaosha);
	}
	/**
	 * 
	 * @return 获取秒杀活动商品列表
	 */
	public List<Miaosha> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	/**
	 * 
	 * @param goods_id 根据商品ID查询秒杀活动商品
	 * @return
	 */
	public Miaosha getgoods_id(Integer goods_id){
		return dao.getgoods_id(goods_id);
	}
	/**
	 * 
	 * @param miaosha 修改秒杀活动商品参数
	 */
	public void update(Miaosha miaosha) {
		// TODO Auto-generated method stub
		dao.update(miaosha);
	}
	public void del(Integer id) {
		Miaosha miaosha = dao.getid(id);
		//删除秒杀活动时 对应把购物车内秒杀的商品一并删除
		List<Car> list = carService.listByGoodsAndPrice(miaosha.getGoods_id(), miaosha.getReal_price());
		if(list != null){
			for(Car c :list){
				carService.del(c.getId());
			}
		}
		dao.del(id);
	}

}
