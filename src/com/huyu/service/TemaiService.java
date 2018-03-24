package com.huyu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.TemaiDao;
import com.huyu.entity.Car;
import com.huyu.entity.Qianggou;
import com.huyu.entity.Temai;
import com.huyu.util.OrderNum;

@Service
@Transactional
public class TemaiService {
	@Resource
	private TemaiDao dao;
	@Resource
	private CarService carService;

	/**
	 * 
	 * @param temai
	 *            添加今日特卖活动商品
	 */
	public void add(Temai temai) {
		// TODO Auto-generated method stub
		dao.add(temai);
	}

	/**
	 * 
	 * @return 查询所有今日特卖活动商品列表
	 */
	public List<Temai> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	/**
	 * 
	 * @return 查询当日今日特卖活动商品列表
	 */
	public List<Temai> listBytoday() {
		List<Temai> list = dao.listBytoday();
		List<Temai> listArr = new ArrayList<Temai>();
		if (list != null) {
			for (Temai t : list) {
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
	 *            根据商品ID查询秒杀活动商品
	 * @return
	 */
	public Temai getgoods_id(Integer goods_id) {
		return dao.getgoods_id(goods_id);
	}

	/**
	 * 
	 * @param temai
	 *            修改今日特卖商品
	 */
	public void update(Temai temai) {
		// TODO Auto-generated method stub
		dao.update(temai);
	}

	/**
	 * 
	 * @param id
	 *            删除今日特卖商品
	 */
	public void del(Integer id) {
		Temai g = dao.getid(id);
		// 删除处活动时 对应把购物车内秒杀的商品一并删除
		List<Car> list = carService.listByGoodsAndPrice(g.getGoods_id(),g.getReal_price());
		if (list != null) {
			for (Car c : list) {
				carService.del(c.getId());
			}
		}
		dao.del(id);
	}

}
