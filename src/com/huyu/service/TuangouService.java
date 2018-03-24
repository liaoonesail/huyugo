package com.huyu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.TuangouDao;
import com.huyu.entity.Car;
import com.huyu.entity.Tuangou;
import com.huyu.util.OrderNum;

@Service
@Transactional
public class TuangouService {
	@Resource
	private TuangouDao dao;
	@Resource
	private CarService carService;

	/**
	 * 
	 * @param tuangou
	 *            添加团购商品
	 */
	public void add(Tuangou tuangou) {
		// TODO Auto-generated method stub
		dao.add(tuangou);
	}

	/**
	 * 
	 * @return 获取所有今日团购活动商品列表
	 */
	public List<Tuangou> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	/**
	 * 
	 * @return 获取当日今日团购活动商品列表
	 */
	public List<Tuangou> listBytoday() {
		List<Tuangou> list = dao.listBytoday();
		List<Tuangou> listArr = new ArrayList<Tuangou>();
		if (list != null) {
			for (Tuangou t : list) {
				if (OrderNum.largerTime1(
						new SimpleDateFormat("yyyy/MM/dd").format(new Date()),
						t.getActivity_time(), "yyyy/MM/dd")) {
					listArr.add(t);
				}
			}
		}
		return listArr;
	}

	/**
	 * 
	 * @param goods_id
	 *            根据商品ID查询团购活动商品
	 * @return
	 */
	public Tuangou getgoods_id(Integer goods_id) {
		return dao.getgoods_id(goods_id);
	}

	/**
	 * 
	 * @param tuangou
	 *            修改今日团购商品
	 */
	public void update(Tuangou tuangou) {
		// TODO Auto-generated method stub
		dao.update(tuangou);
	}

	/**
	 * 
	 * @param id
	 *            删除今日团购商品
	 */
	public void del(Integer id) {
		Tuangou g = dao.getid(id);
		// 删除团购活动时 对应把购物车内秒杀的商品一并删除
		List<Car> list = carService.listByGoodsAndPrice(g.getGoods_id(),
				g.getReal_price());
		if (list != null) {
			for (Car c : list) {
				carService.del(c.getId());
			}
		}
		dao.del(id);
	}

}
