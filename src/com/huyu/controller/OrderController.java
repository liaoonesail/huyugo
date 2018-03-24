package com.huyu.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huyu.entity.*;
import com.huyu.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.util.OrderNum;
import com.huyu.util.Utils;

@Controller
@RequestMapping("/order")
public class OrderController {
@Resource
private FazhanOrderService fazhanOrderService;
@Resource
private PictureService pictureService;
	private OrderService orderService;
	private Order_detailsService order_detailsService;
	private CarService carService;
	private AddressService addressService;
	private GoodsService goodsService;
	private Dixianjin_detailsService dixianjin_detailsService;
	private UserService userService;

	@Resource
	private LogisticsService logisticsService;
	@Resource
	private TuangouService tuangouService;

	public OrderService getOrderService() {
		return orderService;
	}

	@Resource
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public Order_detailsService getOrder_detailsService() {
		return order_detailsService;
	}

	@Resource
	public void setOrder_detailsService(
			Order_detailsService order_detailsService) {
		this.order_detailsService = order_detailsService;
	}

	public CarService getCarService() {
		return carService;
	}

	@Resource
	public void setCarService(CarService carService) {
		this.carService = carService;
	}

	public AddressService getAddressService() {
		return addressService;
	}

	@Resource
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	public GoodsService getGoodsService() {
		return goodsService;
	}

	@Resource
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public Dixianjin_detailsService getDixianjin_detailsService() {
		return dixianjin_detailsService;
	}

	@Resource
	public void setDixianjin_detailsService(
			Dixianjin_detailsService dixianjin_detailsService) {
		this.dixianjin_detailsService = dixianjin_detailsService;
	}

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	private  int type=0;
	/**
	 * 购物车跳转到订单页面
	 * 
	 * @param
	 * @return
	 */
	private Order addOrder(int userId, int addressId, List<Goods> goodsList,
			String ids) {
		int i = 0;
		boolean flag = true;
		List<Order_details> orderDetailList = new ArrayList<Order_details>();
		// List<Car> carList = carService.list(userId);
		List<Car> carList = new ArrayList<Car>();
		String[] idArr = null;
		if (ids != "") {
			idArr = ids.split(",");
			System.out.println("选择的商品id：" + idArr);
			for (String id : idArr) {
				// carList.add(carService.getCarByUserIdAndGoodsId(userId,
				// Integer.parseInt(id)));
				carList.add(carService.getCarByUserIdAndGoodsId(userId,
						Integer.parseInt(id)));
			}
		}

		if (carList == null || carList.size() < 1) { // 购物车已被提交
			List<Order> orderList = orderService.list(userId, 0);
			Order order = null;
			if (orderList != null && orderList.size() > 0) {
				order = orderList.get(orderList.size() - 1);
				List<Order_details> detailsList = order.getList();
				Goods goods = null;
				for (Order_details details : detailsList) {
					goods = goodsService.getid(details.getGoods_id());
					goodsList.add(goods);
				}
			} else {
				order = new Order();
			}
			return order;
		}
		Order_details orderDetails = null;
		Order order = new Order();
		order.setAddress_id(addressId);
		order.setOrder_num(OrderNum.getOrderNum());
		order.setPayment("微信支付");
		order.setStatus(0);
		order.setTotal(0);
		order.setYu_total(0);
		order.setOrder_time(OrderNum.getSystemTime());
		order.setUser_id(userId);
		i = orderService.add(order);
		if (i < 1) {
			flag = false;
		}
		Goods goods = null;
		double sumPrice = 0;
		double sumIntegral=0;
		double yunfei = 0;
		for (Car car : carList) {
			orderDetails = new Order_details();
			orderDetails.setAmount(car.getAmount());
			orderDetails.setGoods_id(car.getGoods_id());
			orderDetails.setOrder_id(order.getId());
			orderDetails.setReal_price(car.getReal_price());
			orderDetails.setColor_norms(car.getColor_norms());
			orderDetails.setIntegral(car.getIntegral());
			if(car.getType()>=0){
				User user=userService.getid(userId);
				if(car.getType()==2&&user.getMember()!=1){
					type=car.getType();
					orderService.del(order.getId());
					return null;
				}
				if(car.getType()==3&&user.getFree()!=1){
					type=car.getType();
					orderService.del(order.getId());
					return null;
				}
				orderDetails.setType(car.getType());
			}
			i = order_detailsService.add(orderDetails);
			goods = goodsService.getid(car.getGoods_id());
			goods.setType(car.getType());
			goodsList.add(goods);
			sumPrice += orderDetails.getReal_price() * orderDetails.getAmount();
			if(orderDetails.getIntegral()>=0){
				sumIntegral+=orderDetails.getIntegral()*orderDetails.getAmount();
			}
			if (i < 1) {
				flag = false;
			}
			if (yunfei < goods.getFreight()) {
				yunfei = goods.getFreight();
			}
			orderDetailList.add(orderDetails);
			// 删除购物车
			carService.del(car.getId());
		}
		if (sumPrice > 99) {
			yunfei = 0;
		}
		// sumPrice += yunfei;
		order.setTotal(sumPrice);
		order.setIntegral(sumIntegral);
		order.setFreight(yunfei);
		orderService.updateorder(order);
		order.setList(orderDetailList);

		return order;
	}

	/**
	 * 显示订单
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/showOrder")
	public String showOrder(HttpServletRequest request, String curpage) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		int zt = -1;
		String status = request.getParameter("status");
		Order order = null;
		List<Goods> goodsList = new ArrayList<Goods>();
		if (status != null && "add".equals(status)) { // 购物车转订单
			Address address = addressService.getaddress(user.getId());
			int addressId = 0;
			if (address != null) {
				addressId = address.getId();
				request.setAttribute(
						"address",
						address.getProvince() + address.getCity()
								+ address.getCounty() + address.getAddress()
								+ " <a href='/huyugo/address/showAddress.do'>更改默认地址>></a>");
			} else {
				request.setAttribute("nullAddress", "null");
				request.setAttribute(
						"address",
						"还没有填写地址  <a href='/huyugo/person/showCenter.do?status=1'>去填写>></a>&nbsp;&nbsp;(<font color='red'>*没有默认地址不能前往支付</font>)");
			}
			String[] idArr = null;
			// 接收goodsid 字符串
			String ids = request.getParameter("ids");
			if (ids != "") {
				String[] split = ids.split(",");
				int userId = user.getId();
				for (String id : split) {
					Tuangou tuangou = tuangouService.getgoods_id(Integer
							.parseInt(id));
					Goods goods = goodsService.getid(Integer.parseInt(id));
					Car car = carService.getCarByUserIdAndGoodsId(userId,
							Integer.parseInt(id));
					if (tuangou != null
							&& car.getReal_price() <= goods.getPrice()
							&& car.getAmount() < tuangou.getPay_num()) {
						List<Car> carList = carService.list(user.getId());
						List<Goods> goodsList1 = new ArrayList<Goods>();
						Goods goods1 = null;
						for (Car c : carList) {
							goods1 = goodsService.getid(c.getGoods_id());
							goodsList1.add(goods1);
						}
						String msg = "团购商品【<span style='color:red;'>"
								+ goods.getName()
								+ "</span>】必须购买【<span style='color:red;'>"
								+ tuangou.getPay_num() + "</span>】件";
						System.out.println(msg);
						request.setAttribute("msg", msg);
						request.setAttribute("carList", carList);
						request.setAttribute("goodsList", goodsList1);
						return "carItem";
					}
				}
			}
			order = addOrder(user.getId(), addressId, goodsList, ids);
			if(order!=null){
				request.setAttribute("order", order);
				request.setAttribute("goodsList", goodsList);
				User getid = userService.getid(user.getId());
				request.setAttribute("user", getid);
				return "order";
			}else {
				//获取banner图片
				List<Picture> bannerList = pictureService.list(0);
				//获取最新商品集合
				List<Goods> newGoodsList = goodsService.listBynew();
				//获取推荐商品集合
				List<Goods> recommendGoodsList = goodsService.listByrecommend();
				//获取热销商品集合
				List<Goods> hotGoodsList = goodsService.listByhot();
				//获取猜你喜欢集合
				List<Goods> goodsByClickNum = goodsService.getGoodsByClickNum();
				request.setAttribute("bannerList", bannerList);
				request.setAttribute("index_", "ok");
				request.setAttribute("newGoodsList", newGoodsList);
				request.setAttribute("recommendGoodsList", recommendGoodsList);
				request.setAttribute("hotGoodsList", hotGoodsList);
				request.setAttribute("goodsByClickNum", goodsByClickNum);
				if(type==2){
					request.setAttribute("message","购买VIP商品需开通VIP");
				}else if(type==3){
					request.setAttribute("message","未开通会员或已经赠送过");
				}
				return "index";
			}

		} else { // 显示用户订单列表
			String ztstr = request.getParameter("zt");
			if (ztstr != null) {
				zt = Integer.parseInt(ztstr);
			}
			// List<Order> orderList = orderService.list(user.getId(), zt);
			HashMap<String, Object> orderList = orderService.orderListpage(
					user.getId(), zt, curpage);
			request.setAttribute("orderList", orderList);
			request.setAttribute("zt", zt);
			System.out.println("状态：" + zt);
			request.setAttribute("curpage", curpage);

			List<Order> orderList2 = orderService.list(user.getId(), 0);
			List<Order> orderList3 = orderService.list(user.getId(), 1);
			List<Order> orderList4 = orderService.list(user.getId(), 2);
			List<Order> orderList5 = orderService.list(user.getId(), 3);
			List<Order> orderList6 = orderService.list(user.getId(), 4);
			List<Order> orderList7 = orderService.list(user.getId(), 5);
			List<Order> orderList8 = orderService.list(user.getId(), 7);
			List<Order> orderList0 = orderService.list(user.getId(), -1);
			request.setAttribute("orderAll", orderList0.size() + "");// -1
			request.setAttribute("orderNoPay", orderList2.size() + "");// 0
			request.setAttribute("orderNoFahuo", orderList3.size() + "");// 1
			request.setAttribute("orderNoShouhuo", orderList4.size() + "");// 2
			request.setAttribute("noPingjia", orderList5.size() + "");// 3
			request.setAttribute("Pingjia", orderList6.size() + "");// 4
			request.setAttribute("notuikuan", orderList7.size() + "");// 5
			request.setAttribute("notuihuo", orderList8.size() + "");// 7

			// 这边要改为订单列表显示页
			return "memberCenter/right/orderByStatus";
		}
	}

	/**
	 * 显示单个未付款的订单
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/showOrderById")
	public String showOrderById(int id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		User user2 = userService.getid(user.getId());
		session.setAttribute("user", user2);
		Order order = orderService.getid(id);
		if (order.getStatus() == 0) {
			List<Order_details> detailsList = order_detailsService
					.listByorder_id(id);
			order.setList(detailsList);
			List<Goods> goodsList = new ArrayList<Goods>();
			Goods goods = null;
			for (Order_details details : detailsList) {
				goods = goodsService.getid(details.getGoods_id());
				goodsList.add(goods);
			}
			request.setAttribute("order", order);
			request.setAttribute("goodsList", goodsList);
			Address address = addressService.getaddress(order.getUser_id());
			if (address == null) {
				request.setAttribute("nullAddress", "null");
				request.setAttribute(
						"address",
						"还没有地址 <a href='/huyugo/person/showCenter.do?status=1'>添加默认地址>></a>&nbsp;&nbsp;(<font color='red'>*没有默认地址不能前往支付</font>)");
			} else {
				request.setAttribute(
						"address",
						address.getProvince()
								+ address.getCity()
								+ address.getCounty()
								+ address.getAddress()
								+ " <a href='/huyugo/person/showCenter.do?status=1'>更改默认地址>></a>");
			}
			request.setAttribute("user", user2);
			return "order";
		}
		return "order";
	}

	/**
	 * 修改订单
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateOrder")
	public String updateOrder(int id, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		Order order = orderService.getid(id);
		order.setStatus(2);
		orderService.updatestatus(order);
		return "redirect:/order/showOrder.do";
	}

	/**
	 * 后台确认评价订单
	 * 
	 * @param id
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/querenCommon")
	public String querenCommon(int id, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Order order = orderService.getid(id);
		order.setStatus(4);
		orderService.updatestatus(order);
		response.getWriter().print(1);
		return null;
	}

	/**
	 * 显示二维码支付页面
	 * 
	 * @param orderId
	 * @param request
	 * @return
	 */
	@RequestMapping("/showOrderPay")
	public String showOrderPay(int orderId, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		request.setAttribute("orderId", orderId + "");
		request.setAttribute("type",1);
		String[] dixianjins = request.getParameterValues("checkbox_dixianjin");
		Order order = orderService.getid(orderId);
		List<Order_details> detailsList = order_detailsService
				.listByorder_id(orderId);
		order.setList(detailsList);
		int sumPrice = 0;
		List<Goods> goodsList = new ArrayList<Goods>();
		Goods goods_ = null;
		double yunfei = 0;
		for (Order_details details : detailsList) {
			sumPrice += details.getReal_price() * details.getAmount();
			goods_ = goodsService.getid(details.getGoods_id());
			if (yunfei < goods_.getFreight()) {
				yunfei = goods_.getFreight();
			}
			goodsList.add(goods_);
		}
		if (order.getStatus() > 0) {
			// 该订单已经支付过，直接跳回成功页面
			request.setAttribute("order", order);
			request.setAttribute("sumPrice", sumPrice + order.getFreight() + "");
			request.setAttribute("nowDate", OrderNum.getregTime());
			return "goumaiok";
		}
		if (sumPrice >= 99) {
			yunfei = 0;
		}
		User user1=userService.getid(order.getUser_id());
		if(user1.getIntegral()<order.getIntegral()){
			User getid = userService.getid(user1.getId());
			request.setAttribute("user", getid);
			request.setAttribute("goodsList", goodsList);
			request.setAttribute("errorMsg", "没有足够的积分");
			request.setAttribute("order", order);
			return "order";
		}
		// request.getParameterValues("checkbox_dixianjin");
		// String[] dixianjins = checkbox_dixianjin;
		int dixianjin_ = 0;
		// String args = "";
		if (dixianjins != null && !"".equals(dixianjins)) {
			for (String dixianjin : dixianjins) {
				if (dixianjin != null && !"".equals(dixianjin)
						&& dixianjin.indexOf("_") > -1
						&& Utils.isNumeric(dixianjin.split("_")[1])) {
					dixianjin_ += Integer.parseInt(dixianjin.split("_")[1]);
					// args += "," + dixianjin.split("_")[0];
				}
			}

			// 判断抵现金是否可扣
			if (user.getDixianjin() < dixianjin_) {
				User getid = userService.getid(user.getId());
				request.setAttribute("user", getid);
				request.setAttribute("goodsList", goodsList);
				request.setAttribute("errorMsg", "没有足够的抵现金");
				request.setAttribute("order", order);
				return "order";
			}

		}
		// 判断余额是否可扣
		double yuezhifu = Double.parseDouble(request.getParameter("yuezhifu_"));
		if (yuezhifu > 0) {
			if (user.getAccount() < yuezhifu) {
				request.setAttribute("goodsList", goodsList);
				request.setAttribute("errorMsg", "没有足够的余额");
				request.setAttribute("order", order);
				return "order";
			} else {
				order.setYu_total(user.getAccount());
				//备注 -- 这里之前没有扣用户的账户余额，
				//修改 -- 扣除用户余额
				//user.setAccount(account)
				
				
			}
		}
		// 循环拿出每个商品

		for (Order_details details : detailsList) {

			if (dixianjins == null) {
				details.setState(0);
				order_detailsService.update(details);
			} else {
				boolean flag = false;
				for (String dixianjin : dixianjins) {
					if (details.getId() == Integer.parseInt(dixianjin
							.split("_")[0])) {
						flag = true;
					}
				}
				if (flag) {
					details.setState(1);
					order_detailsService.update(details);
				}
			}

			Goods goods = goodsService.getid(details.getGoods_id());
			Dixianjin_details dxjDetails = dixianjin_detailsService.getid(
					user.getId(), order.getId(), goods.getId());
			int xukou = 0; // 需要扣除的资金
			if (user.getMember() == 1) {
				xukou = goods.getMember_dixianjin() * details.getAmount();
				;
			} else {
				xukou = goods.getCommon_dixianjin() * details.getAmount();
				;
			}
			// 判断是否有抵现金
			if (dxjDetails == null) {
				if (details.getState() == 1) {
					dxjDetails = new Dixianjin_details();
					dxjDetails.setGoods_id(goods.getId());
					dxjDetails.setOrder_id(order.getId());
					dxjDetails.setStatus(1);
					dxjDetails.setUser_id(user.getId());
					dixianjin_detailsService.add(dxjDetails);

					user.setDixianjin(user.getDixianjin() - xukou);
					user.setD_dixianjin(user.getD_dixianjin() + xukou);
					userService.update(user);
					session.setAttribute("user", user);
				}
			} else {
				// 如果之前没有，现在设置成了用抵现金就扣除用户抵现金到冻结里面，没有设置使用抵现金就不管
				if (dxjDetails.getStatus() == 0) {
					if (details.getState() == 1) {
						dxjDetails.setStatus(1);
						dixianjin_detailsService.update(dxjDetails);

						user.setDixianjin(user.getDixianjin() - xukou);
						user.setD_dixianjin(user.getD_dixianjin() + xukou);
						userService.update(user);
						session.setAttribute("user", user);
					}
				} else {// 如果之前有，现在设置成了用抵现金就不管,没有设置用抵现金就要返回冻结的抵现金
					if (details.getState() == 0) {
						dxjDetails.setStatus(0);
						dixianjin_detailsService.update(dxjDetails);

						user.setDixianjin(user.getDixianjin() + xukou);
						user.setD_dixianjin(user.getD_dixianjin() - xukou);
						userService.update(user);
						session.setAttribute("user", user);
					}
				}
			}

		}
		double price2_ = sumPrice - dixianjin_ - yuezhifu + order.getFreight();
		// 修改订单金额
		order.setTotal(price2_);
		order.setYu_total(yuezhifu);
		if (order.getTotal() != price2_) {
			// 发生金额变动后要更换订单号，微信扫码支付需要同一订单号不能有两次金额
			order.setOrder_num(OrderNum.getOrderNum());
		}
		orderService.updateorder(order);
		if (price2_ > 0) {
			return "showCode";
		} else {
			// 全余额支付+积分使用
			order.setStatus(1);
			User user2 = orderService.update(order);
			session.setAttribute("user", user2);
			request.setAttribute("order", order);
			request.setAttribute("sumPrice", sumPrice + order.getFreight() + "");
			request.setAttribute("nowDate", OrderNum.getregTime());
			return "goumaiok";
		}
	}

	/**
	 * 商品购买支付
	 * 
	 * @param orderId
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/orderPay")
	public String orderPay(int orderId, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String result = "";
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			result = "notLogin";
			response.getWriter().print(result);
			return null;
		} else {

			Order order = orderService.getid(orderId);
			double amount = order.getTotal();
			result = "attach=1&detail=商品购买&desc=商品购买&goodSn=" + "111"
					+ "&orderSn=" + order.getOrder_num() + "&amount=" + amount;
			// System.out.println(result);
			// result = "attach=1&detail=商品购买&desc=商品购买&goodSn=" + order.getId()
			// + "&orderSn=" + order.getOrder_num() + "&amount=0.01";
			response.getWriter().print(result);
			return null;
		}

	}

	/**
	 * 删除订单
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/delOrder")
	public String delOrder(int id, String curpage, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		Order order = orderService.getid(id);
		if (order != null && order.getUser_id() == user.getId()) {
			orderService.del(id);
			User user1 = userService.getid(user.getId());

			session.setAttribute("user", user1);
		}
		System.out.println("当前页码：" + curpage);
		request.setAttribute("curpage", curpage);
		return "redirect:/order/showOrder.do?curpage=1";
	}

	/**
	 * 扫码成功点击跳转调用此函数
	 * 
	 * @param id
	 *            订单ID
	 * @param request
	 * @param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/paySuccess")
	public String paySuccess(String id,String type, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		if (type.equals("2")){
			FazhanOrder getid = fazhanOrderService.getid(Integer.parseInt(id));
			if(getid.getStatus()==1){
				return "memberCenter/center";
			}else {
				request.setAttribute("orderId", id);
				request.setAttribute("type",2);
				return "showCode2";
			}
		}
		int sumPrice = 0;
		Order order = orderService.getid(Integer.parseInt(id));
		List<Order_details> listByorder_id = order_detailsService
				.listByorder_id(Integer.parseInt(id));
		for (Order_details details : listByorder_id) {
			sumPrice += details.getReal_price() * details.getAmount();
		}

		if (order.getStatus() == 1) {
			Order order2 = orderService.getid(Integer.parseInt(id));
			request.setAttribute("order", order2);
			request.setAttribute("sumPrice", sumPrice + order.getFreight() + "");
			request.setAttribute("nowDate", OrderNum.getregTime());
			return "goumaiok";
		} else {
			request.setAttribute("orderId", id);
			request.setAttribute("type",1);
			return "showCode";
		}

	}

	/**
	 * 确认收货调用
	 * 
	 * @param id
	 *            订单Id
	 * @param request
	 * @return
	 */
	@RequestMapping("/updateStatus")
	public String updateStatus(Integer id, String curpage,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		Order order = orderService.getid(id);
		order.setStatus(3);
		orderService.updatestatus(order);
		return "redirect:/order/showOrder.do?zt=-1&curpage=1";
	}

	/**
	 * 申请退款调用
	 * 
	 * @param orderId
	 * @param goodsId
	 * @param request
	 * @param response
	 *            1可以申请退款 2订单已发货,申请退款失败(只能退货)
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/refundMoney")
	public String refundMoney(Integer orderId, Integer goodsId,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		Logistics logistics = logisticsService.getByOrderId(orderId, goodsId);
		if (logistics != null) {
			response.getWriter().print("2");// 订单已发货,申请退款失败(只能退货)
			return null;
		} else {
			Order order = orderService.getid(orderId);
			order.setStatus(5);
			orderService.updatestatus(order);
			response.getWriter().print("1");// 可以申请退款
			return null;
		}
	}

	/**
	 * 申请退货
	 * 
	 * @param orderId
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/tuihuo")
	public String tuihuo(Integer orderId, HttpServletResponse response)
			throws IOException {
		Order order = orderService.getid(orderId);
		System.out.println("订单状态：" + order.getStatus());
		if (order.getStatus() == 3) {
			order.setStatus(7);
			orderService.updatestatus(order);
			response.getWriter().print("1");// 申请退货成功！
		} else {
			response.getWriter().print("2");
		}
		return null;
	}

	/**
	 * 取消退款
	 * 
	 * @param orderId
	 * @param response
	 *            1取消成功 2取消失败
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/cancelTuikuan")
	public String cancelTuikuan(Integer orderId, HttpServletResponse response)
			throws IOException {
		Order order = orderService.getid(orderId);
		if (order.getStatus() == 5) {
			order.setStatus(1);// 设为待发货
			orderService.updatestatus(order);
			response.getWriter().print("1");
			return null;
		} else {
			response.getWriter().print("2");
			return null;
		}
	}

	/**
	 * 取消退货
	 * 
	 * @param orderId
	 * @param response
	 *            1取消成功 2取消失败
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/cancelTuihuo")
	public String cancelTuihuo(Integer orderId, HttpServletResponse response)
			throws IOException {
		Order order = orderService.getid(orderId);
		if (order.getStatus() == 7) {
			order.setStatus(3);// 设为待评价
			orderService.updatestatus(order);
			response.getWriter().print("1");
			return null;
		} else {
			response.getWriter().print("2");
			return null;
		}
	}

	/**
	 * 自动确认收货
	 * 
	 * @param orderId
	 *            订单id
	 * @param goodsId
	 *            商品id()
	 * @param request
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	@RequestMapping("/autoAffirm")
	public String autoAffirm(Integer orderId, Integer goodsId,
			HttpServletRequest request, HttpServletResponse response)
			throws ParseException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Logistics logistics = logisticsService.getByOrderId(orderId, goodsId);
		String format = "yyyy-MM-dd HH:mm:ss";
		long time = new SimpleDateFormat(format).parse(logistics.getTime())
				.getTime() + 1440000000L;// 加上20天后
		String time2 = new SimpleDateFormat(format).format(time);
		String nowTime = new SimpleDateFormat(format).format(new Date());
		Order order = orderService.getid(orderId);
		if (OrderNum.largerTime(time2, nowTime, format)
				&& order.getStatus() == 2) {// 如果true,更新订单状态
			order.setStatus(3);// 自动确认收货
			orderService.updatestatus(order);
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
		return null;
	}
	
	/**
	 * 后台自动确认收货
	 * @param orderId 订单id
	 * @param response 
	 * @return
	 * @throws ParseException 
	 * @throws IOException 
	 */
	@RequestMapping("/autoAffirmOrder")
	public String autoAffirmOrder(Integer orderId,HttpServletResponse response) throws ParseException, IOException{
		Order order = orderService.getid(orderId);
		List<Order_details> list = order_detailsService.listByorder_id(orderId);
		String format = "yyyy-MM-dd HH:mm:ss";
		for(Order_details details : list){
			Logistics logistics = logisticsService.getByOrderId(orderId, details.getGoods_id());
			String time2 = new SimpleDateFormat(format).format(new SimpleDateFormat(format).parse(logistics.getTime()).getTime()+1440000000L);
			String nowTime = new SimpleDateFormat(format).format(new Date());
			if (OrderNum.largerTime(time2, nowTime, format)
					&& order.getStatus() == 2) {// 如果true,更新订单状态
				order.setStatus(3);// 自动确认收货
				orderService.updatestatus(order);
				response.getWriter().print(true);
			}
		}
		return null;
	}
}
