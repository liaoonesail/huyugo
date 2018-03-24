package com.huyu.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.huyu.entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.OrderDao;
import com.huyu.util.OrderNum;
import com.huyu.util.page;

@Service
@Transactional
public class OrderService {
	@Resource
	private OrderDao dao;
	@Resource
	private Order_detailsService service;
	@Resource
	private UserService service1;
	@Resource
	private GoodsService service2;
	@Resource
	private Receipt_PaymentService service3;
	@Resource
	private MemberSystem memberSystem;
	@Resource
	private MemberService memberService;
	@Resource
	private LogisticsService service4;
	@Resource
	private AddressService addressService;

	/**
	 * 
	 * @param order
	 *            添加订单by user_id
	 * @return
	 */
	public int add(Order order) {
		// TODO Auto-generated method stub
		return dao.add(order);
	}

	/**
	 * 
	 * @param order
	 *            //支付成功调用此接口；根据id修改单个订单by user_id order
	 *            所有参数都需要传进来(id,user_id,order_num
	 *            ,status,address_id,total,payment)
	 * @return //status==0失败，status==1成功；
	 */
	public User update(Order order) {
		// TODO Auto-generated method stub
		int integral = 0;
		double huyubi = 0;
		int dixianjin = 0;
		List<Order_details> list = service.listByorder_id(order.getId());
		User user = service1.getid(order.getUser_id());
		if (user.getMember() == 0) {
			for (Order_details order_details : list) {
				Goods goods = service2.getid(order_details.getGoods_id());
				if (order_details.getState() == 1) {
					dixianjin += goods.getCommon_dixianjin()
							* order_details.getAmount();

				}
				integral += goods.getCommon_integral()
						* order_details.getAmount();
				huyubi += goods.getCommon_huyubi() * order_details.getAmount();
				// 修改商品库存
				goods.setAmount(goods.getAmount() - order_details.getAmount());
				service2.update(goods);
			}
		} else {
			for (Order_details order_details : list) {
				Goods goods = service2.getid(order_details.getGoods_id());
				if (order_details.getState() == 1) {
					dixianjin += goods.getMember_dixianjin()
							* order_details.getAmount();
				}
				integral += goods.getMember_integral()
						* order_details.getAmount();
				huyubi += goods.getMember_huyubi() * order_details.getAmount();
				// 修改商品库存
				goods.setAmount(goods.getAmount() - order_details.getAmount());
				service2.update(goods);
			}
		}
		user.setIntegral(user.getIntegral() + integral);
		user.setHuyubi(user.getHuyubi() + huyubi);
		user.setD_dixianjin(user.getD_dixianjin() - dixianjin);
		// TODO 产品结算养老金 （购买者的养老金结算）
		double pension = (order.getTotal()+order.getYu_total());//所有会员自己消费赠送1%的养老金【按产品结算价格计算】
		if(pension<109){
			pension = (pension-10)*0.01;
		}else{
			pension = pension*0.01;
		}
		user.setPension(user.getPension()+pension);//购买者获得养老金

		// 修改上级账户余额获利
		User user1 = service1.getid(order.getUser_id());
		double wei_price = order.getTotal();// 微信支付
		double yu_price = order.getYu_total();// 余额支付
		double sumIntegral=order.getIntegral();//积分支付
		double price = wei_price + yu_price;// 客户要求进入分销系统的是微信支付+余额支付
		memberSystem.getprofit_buy(price, user1);//购物上下级获利
		memberSystem.getprofit_pension(price, user1);//购物上下级养老金获利
		//用户养老金开始时间判断
		if("".equals(user.getPensionTime()) || user.getPensionTime() == null){
			String time = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			user.setPensionTime(time);
			service1.updatePensionTime(user);//插入用户首次有养老金的时间
		}
		
		/*
		 * int m = 1; int n = 3; String gread = "n"; if(user.getMember() == 1){
		 * gread = "y"; } gread += "_" + user.getMember_grade();
		 * memberSystem.profitBuy(price, user.getSuperior_id(), m, n, gread);
		 */
		double yu_total = order.getYu_total();
		String date_time = OrderNum.getregTime();
		order.setOrder_time(date_time);
		order.setId(order.getId());
		dao.updateTime(order);
		// 增加自己购买商品明细
		if (wei_price != 0) {
			Receipt_Payment rp1 = new Receipt_Payment(0, wei_price, date_time,
					order.getUser_id(), 5, 2, order.getId());
			service3.add(rp1);// 添加微信支出明细
		}

		if (yu_total != 0) {
			user.setAccount(user.getAccount() - yu_total);
			Receipt_Payment yu_rp = new Receipt_Payment(0, yu_total, date_time,
					order.getUser_id(), 1, 2, order.getId());
			service3.add(yu_rp);// 添加余额支出明细
		}
		if(sumIntegral!=0){
			user.setIntegral(user.getIntegral()-sumIntegral);
			Receipt_Payment integral_rp = new Receipt_Payment(0, sumIntegral, date_time,
					order.getUser_id(), 4, 2, order.getId());
			service3.add(integral_rp);// 添加积分支出明细
		}
		if(user.getFree()==1&&price==0&&sumIntegral==0){//免费赠送商品
			user.setFree(0);
		}
		// 修改自己购买商品获得互余币和积分减少用户冻结抵现金里面的抵现金和扣除用余额支付的金额
		int status = service1.update(user);
		if (dixianjin > 0) {
			Receipt_Payment rp = new Receipt_Payment(0, dixianjin,
					OrderNum.getregTime(), order.getUser_id(), 3, 2,
					order.getId());
			service3.add(rp);// 增加支出抵现金明细
		}
		// 添加互余币和积分明细
		Receipt_Payment rp2 = new Receipt_Payment(huyubi, 0, date_time,
				order.getUser_id(), 2, 2, order.getId());
		service3.add(rp2);// 添加互余币明细
		Receipt_Payment rp3 = new Receipt_Payment(integral, 0, date_time,
				order.getUser_id(), 4, 2, order.getId());
		service3.add(rp3);// 添加积分明细

		// 修改订单付款状态
		if(order.getAddress_id()==0){
			Address getaddress = addressService.getaddress(order.getUser_id());
			order.setAddress_id(getaddress.getId());
		}
		dao.update(order);
		
		return user;
	}

	/**
	 * 修改订单状态 传入id status
	 * 
	 * @param order
	 */
	public void updatestatus(Order order) {
		dao.update(order);
	}

	/**
	 * 
	 * @return 修改订单不包含订单状态的修改
	 */
	public int updateorder(Order order) {
		int status = dao.updateorder(order);
		/*
		 * List<Order_details> list = service.listByorder_id(order.getId());
		 * User user = service1.getid(order.getUser_id()); int dixianjin=0;
		 * if(user.getMember()==0){ for (Order_details order_details : list) {
		 * Goods goods = service2.getid(order_details.getGoods_id());
		 * if(order_details.getState()==1){
		 * dixianjin+=goods.getCommon_dixianjin()*order_details.getAmount();
		 * 
		 * } } }else{ for (Order_details order_details : list) { Goods goods =
		 * service2.getid(order_details.getGoods_id());
		 * if(order_details.getState()==1){
		 * dixianjin+=goods.getMember_dixianjin()*order_details.getAmount(); } }
		 * } user.setD_dixianjin(user.getDixianjin()-dixianjin);
		 * user.setD_dixianjin(dixianjin);//减少用户冻结抵现金
		 * status=service1.update(user);
		 */
		return status;
	}

	/**
	 * 
	 * @param
	 *            //查询指定用户的订单，status=0为未付款的订单，status=1为已付款的订单、待发货,status=2已发货、
	 *            待收货,status=3已完成、已收货,status=-1为查询所有；
	 * @return
	 */
	public List<Order> list(int user_id, int status) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("user_id", user_id);
		map.put("status", status);
		List<Order> list = dao.list(map);
		for (Order order : list) {
			List<Order_details> list1 = service.listByorder_id(order.getId());// 查询单个订单下的所有商品详情
			order.setList(list1);
		}
		return list;
	}

	/**
	 * 
	 * @param
	 *           // 删除此订单 返回被冻结的抵现金
	 * @return
	 */
	public int del(Integer id) {
		// TODO Auto-generated method stub
		int dixianjin = 0;
		List<Order_details> list = service.listByorder_id(id);
		Order order = getid(id);
		if (order.getStatus() == 0) {
			User user = service1.getid(order.getUser_id());
			if (user.getMember() == 0) {
				for (Order_details order_details : list) {
					Goods goods = service2.getid(order_details.getGoods_id());
					if (order_details.getState() == 1) {
						dixianjin += goods.getCommon_dixianjin()
								* order_details.getAmount();
					}
				}

			} else {
				for (Order_details order_details : list) {
					Goods goods = service2.getid(order_details.getGoods_id());
					if (order_details.getState() == 1) {
						dixianjin += goods.getMember_dixianjin()
								* order_details.getAmount();
					}
				}

			}
			user.setDixianjin(user.getDixianjin() + dixianjin);
			user.setD_dixianjin(user.getD_dixianjin() - dixianjin);
			service1.update(user);
		}
		// 删除物流信息
		if (order.getStatus() == 2) {
			service4.delByOrder(order.getId());
		}
		service.delByorder_id(id);
		return dao.del(id);
	}

	/**
	 * 
	 * @param id
	 *            根据订单ID获取订单
	 * @return
	 */
	public Order getid(Integer id) {
		return dao.getid(id);
	}

	/**
	 * 
	 * @param order_num
	 *            查询订单By order_num
	 * @return
	 */
	public Order getorder_num(String order_num) {
		// TODO Auto-generated method stub
		return dao.getorder_num(order_num);
	}

	// 模糊查询
	public int count(String name, Integer status) {
		// TODO Auto-generated method stub
		return dao.count(name, status);
	}

	public List<Order> listpage(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.listpage(map);
	}

	public HashMap<String, Object> orderListpage(int user_id, int status,
			String curpage) {
		// TODO Auto-generated method stub
		int count = orderCount(user_id, status);
		page page = new page(curpage, count, 20);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("user_id", user_id);
		map.put("status", status);
		List<Order> list = dao.orderListpage(map);
		for (Order order : list) {
			List<Order_details> listByorder_id = service.listByorder_id(order
					.getId());
			order.setList(listByorder_id);
		}
		map.put("list", list);
		map.put("countPage", page.getPageCount());
		map.put("page", page);
		map.put("count", count);
		return map;
	}

	private int orderCount(int user_id, int status) {
		// TODO Auto-generated method stub
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("user_id", user_id);
		map.put("status", status);
		return dao.orderCount(map);
	}

}
