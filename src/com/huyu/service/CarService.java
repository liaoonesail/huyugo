package com.huyu.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huyu.dao.*;
import com.huyu.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.util.OrderNum;

@Service
@Transactional
public class CarService {
	@Resource
	private CarDao dao;
	@Resource
	private GoodsDao goodsDao;
	@Resource
	private MiaoshaDao miaoDao;
	@Resource
	private QianggouDao qiangDao;
	@Resource
	private TemaiDao teDao;
	@Resource
	private TuangouDao tuDao;
	@Resource
	private Vip_shopDao vipShopDao;
	@Resource
	private Integral_shopDao integralShopDao;
	@Resource
	private Ticket_shopDao ticketShopDao;

	/**
	 * 
	 * @param car
	 *            加入购物车
	 * @return
	 */
	public int add(Car car) {
		// TODO Auto-generated method stub
		return dao.add(car);
	}

	/**
	 * 根据用户id和商品id获取购物车
	 * 
	 * @param userId
	 * @param goodsId
	 * @return
	 */
	public Car getCarByUserIdAndGoodsId(Integer userId, Integer goodsId) {
		return dao.getCarByUserIdAndGoodsId(userId, goodsId);
	}

	/**
	 * 
	 * @param user_id
	 *            -根据用户id查询购物车商品
	 * @return
	 */
	public List<Car> list(Integer user_id) {
		List<Car> list = dao.list(user_id);
		if (list != null) {
			for (Car c : list) {
				int goods_id = c.getGoods_id();
				Goods goods = goodsDao.getid(goods_id);
				Miaosha miaosha = miaoDao.getgoods_id(goods_id);
				Qianggou qianggou = qiangDao.getgoods_id(goods_id);
				Temai temai = teDao.getgoods_id(goods_id);
				Tuangou tuangou = tuDao.getgoods_id(goods_id);
				Vip_shop vipShop = vipShopDao.getgoods_id(goods_id);
				Integral_shop integralShop = integralShopDao.getgoods_id(goods_id);
				Ticket_shop ticketShop = ticketShopDao.getgoods_id(goods_id);
				/**
				 * 如果存在活动(秒杀、抢购、特卖、团购)商品 并且 购物车里的商品价格小于商品价格(说明是从活动商城购买的商品)
				 * &nbsp;条件满足后就判断这个商品是否还在活动时间内<br/>
				 * &nbsp;&nbsp;如果过了活动时间==>更新购物车里的商品价格
				 */
				if (miaosha != null && c.getReal_price() <= goods.getPrice()&&vipShop==null&&integralShop==null&&ticketShop==null) {
					if (OrderNum.largerTime(miaosha.getEnd_time(),
							new SimpleDateFormat("yyyy-MM-dd HH:mm")
									.format(new Date()), "yyyy-MM-dd HH:mm")) {
						System.out.println("秒杀商品不在活动时间内 商品 ID 【"
								+ goods.getId() + "】");
						c.setReal_price(goods.getPrice());
						dao.updatePrice(c);// 更新实际价格
					}
				}
				if (qianggou != null && c.getReal_price() <= goods.getPrice()&&vipShop==null&&integralShop==null&&ticketShop==null) {
					if (OrderNum.largerTime(qianggou.getActivity_time(),
							new SimpleDateFormat("yyyy/MM/dd")
					.format(new Date()), "yyyy/MM/dd")) {
						System.out.println("抢购商品不在活动时间内 商品 ID 【"
								+ goods.getId() + "】");
						c.setReal_price(goods.getPrice());
						dao.updatePrice(c);// 更新实际价格
					}
				}
				if (temai != null && c.getReal_price() <= goods.getPrice()&&vipShop==null&&integralShop==null&&ticketShop==null) {
					if (OrderNum.largerTime(temai.getActivity_time(),
							new SimpleDateFormat("yyyy/MM/dd")
					.format(new Date()), "yyyy/MM/dd")) {
						System.out.println("特卖商品不在活动时间内 商品 ID 【"
								+ goods.getId() + "】");
						c.setReal_price(goods.getPrice());
						dao.updatePrice(c);// 更新实际价格
					}
				}
				if (tuangou != null && c.getReal_price() <= goods.getPrice()&&vipShop==null&&integralShop==null&&ticketShop==null) {
					if (OrderNum.largerTime(tuangou.getActivity_time(),
							new SimpleDateFormat("yyyy/MM/dd")
					.format(new Date()), "yyyy/MM/dd")) {
						System.out.println("团购商品不在活动时间内 商品 ID 【"
								+ goods.getId() + "】");
						c.setReal_price(goods.getPrice());
						dao.updatePrice(c);// 更新实际价格
					}
				}
				/*if(miaosha == null&&){
					c.setReal_price(goods.getPrice());
					dao.updatePrice(c);// 更新实际价格
				}
				if(qianggou == null){
					c.setReal_price(goods.getPrice());
					dao.updatePrice(c);// 更新实际价格
				}
				if(temai == null){
					c.setReal_price(goods.getPrice());
					dao.updatePrice(c);// 更新实际价格
				}
				if(tuangou == null){
					c.setReal_price(goods.getPrice());
					dao.updatePrice(c);// 更新实际价格
				}*/
			}
		}
		return dao.list(user_id);
	}

	/**
	 * 
	 * @param id
	 *            删除一个指定购物车中的商品
	 * @return
	 */
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return dao.del(id);
	}

	/**
	 * 
	 * @param id
	 *            修改购物车商品数量
	 * @param amount
	 * @return
	 */
	public int update(Integer id, int amount) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("id", id);
		map.put("amount", amount);
		return dao.update(map);
	}
	
	/**
	 * 根据商品id和价格获取购物车
	 * @param goodsId
	 * @param price
	 * @return
	 */
	public List<Car> listByGoodsAndPrice(int goodsId,double price){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("goods_id", goodsId);
		map.put("real_price", price);
		return dao.listByGoodsAndPrice(map);
	}
	
}
