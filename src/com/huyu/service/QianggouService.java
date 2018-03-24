package com.huyu.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.QianggouDao;
import com.huyu.entity.Car;
import com.huyu.entity.Qianggou;
import com.huyu.entity.Tuangou;
import com.huyu.util.OrderNum;

@Service
@Transactional
public class QianggouService {
	@Resource
	private QianggouDao dao;
	@Resource
	private CarService carService;

	/**
	 * 
	 * @param qianggou
	 *            添加今日抢购活动商品
	 */
	public void add(Qianggou qianggou) {
		// TODO Auto-generated method stub
		dao.add(qianggou);
	}

	public Qianggou getid(int id) {
		return dao.getid(id);
	}

	/**
	 * 
	 * @return 查询所有今日抢购活动商品列表
	 */
	public List<Qianggou> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}

	/**
	 * 
	 * @param goods_id
	 *            根据商品ID查询抢购活动商品
	 * @return
	 */
	public Qianggou getgoods_id(Integer goods_id) {
		return dao.getgoods_id(goods_id);
	}

	/**
	 * 
	 * @return 查询当天今日抢购活动商品列表
	 */
	public List<Qianggou> listBytoday() {
		List<Qianggou> list = dao.listBytoday();
		System.out.println("集合大小：" + list.size());
		List<Qianggou> listArr = new ArrayList<Qianggou>();
		if (list != null) {
			for (Qianggou qg : list) {
				if (OrderNum.largerTime1(
						new SimpleDateFormat("yyyy/MM/dd").format(new Date()),
						qg.getActivity_time(), "yyyy/MM/dd")) {
					listArr.add(qg);
				}

			}
		}
		return listArr;
	}

	/**
	 * 
	 * @param qianggou
	 *            修改今日抢购商品
	 */
	public void update(Qianggou qianggou) {
		// TODO Auto-generated method stub
		dao.update(qianggou);
	}

	/**
	 * 
	 * @param id
	 *            删除今日抢购商品
	 */
	public void del(Integer id) {
		Qianggou q = dao.getid(id);
		// 删除抢购活动时 对应把购物车内秒杀的商品一并删除
		List<Car> list = carService.listByGoodsAndPrice(q.getGoods_id(),
				q.getReal_price());
		if (list != null) {
			for (Car c : list) {
				carService.del(c.getId());
			}
		}
		dao.del(id);
	}

}
