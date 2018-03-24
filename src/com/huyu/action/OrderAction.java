package com.huyu.action;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONFunction;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Order;
import com.huyu.entity.Order_details;
import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.User;
import com.huyu.service.OrderService;
import com.huyu.service.Order_detailsService;
import com.huyu.service.Receipt_PaymentService;
import com.huyu.service.UserService;
import com.huyu.util.OrderNum;
import com.huyu.util.page;

@Controller
@RequestMapping("/adminorder")
public class OrderAction {
	@Resource
	private OrderService service;
	@Resource
	private UserService userService;
	@Resource
	private Receipt_PaymentService rePaymentService;
	@Resource
	private Order_detailsService service1;
	@Resource
	private UserService service2;

	@RequestMapping("/add")
	public String add(Order order, HttpServletResponse response)
			throws IOException {
		order.setOrder_num(OrderNum.getOrderNum());
		order.setOrder_time(OrderNum.getSystemTime());
		order.setStatus(0);// 付款状态，0=未付款、1=已付款、2=待收货、3=已收货;
		int id = service.add(order);
		List<Order_details> list = order.getList();
		for (Order_details order_details : list) {
			order_details.setOrder_id(id);
			service1.add(order_details);
		}
		response.getWriter().print(id);// 返回order_id
		return null;
	}

	@RequestMapping("/update")
	// 支付成功调用此接口
	public String update(Order order, HttpServletResponse response)
			throws IOException {// 入参--id:int
		order.setStatus(1);
		service.update(order);
		response.getWriter().print(1);
		return null;
	}

	@RequestMapping("/updatetwo")
	public String updatetwo(Order order, HttpServletResponse response)
			throws IOException {// 入参--id:int
		Order order1 = service.getid(order.getId());
		order1.setStatus(order.getStatus());
		service.update(order1);
		response.getWriter().print(1);
		return null;
	}

	// 入参user_id:int,status:int
	// user_id为用户id；status为订单状态，status=0为未付款的订单，status=1为已付款的订单，status=2为已发货，待收货的订单,status=3为已收货已完成的订单；
	@RequestMapping("/list")
	public String list(Integer user_id, Integer status,
			HttpServletResponse response) throws IOException {
		List<Order> list = service.list(user_id, status);
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}

	/**
	 * 模糊查询加分页
	 * 
	 * @param name
	 * @param curpage
	 * @param
	 * @param status
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/listpage")
	public String listpage(String name, String curpage, Integer status,
			HttpServletResponse response) throws IOException {
		name = name == null ? "" : name;
		int count = service.count(name, status);
		page page = new page(curpage, count, 10);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("name", name);
		map.put("status", status);
		List<Order> list = service.listpage(map);
		for (Order order:list) {
			order.setUser(userService.getid(order.getUser_id()));
		}
		map.put("page", page);
		map.put("list", list);
		map.put("count", count);
		JSONArray json = JSONArray.fromObject(map);
		response.getWriter().print(json);
		return null;
	}

	// 取消订单
	@RequestMapping("/del")
	public String del(Integer id, HttpServletResponse response)
			throws IOException {
		int status = service.del(id);
		response.getWriter().print(status);
		return null;
	}

	/**
	 * 确认收到退货商品后调动 将订单中的微信支付金额和余额支付金额 全部以余额方式返回给退货用户
	 * 
	 * @param id
	 * @param response
	 * @return 返回1 success
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping("/refundMoneyed")
	public String refundMoneyed(Integer id, HttpServletResponse response,
			HttpServletRequest request) throws IOException, ParseException {
		Order order = service.getid(id);
		User user = service2.getid(order.getUser_id());
		order.setStatus(6);
		int a = service.updateorder(order);
		double refundPay = order.getYu_total() + order.getTotal()-order.getFreight();//退货扣运费
		//user.setAccount(user.getAccount() + refundPay);
		//int b = service2.update(user);


		String time = OrderNum.getregTime();
		//Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(order.getOrder_time());
		/**
		 * 扣除上级获利
		 */
		//根据记录时间查询和退款用户的上级id查询是否有上级获利记录
		int supId = user.getSuperior_id();
		List<Receipt_Payment> supFenli = rePaymentService.listByOrderTimeAndUserId(supId, order.getOrder_time());
		if (supFenli.size() != 0) {
			
			/*Receipt_Payment rpSupYuE = supFenli.get(0);
			Receipt_Payment rpSub = new Receipt_Payment(rpSupYuE.getReceipt(),time,rpSupYuE.getUser_id(),1,3,-1);//订单id在上级获利记录里没有传入，再加上这里拿到订单id没有多大用处，so 就填-1
			rePaymentService.add(rpSub);//添加扣除上级的返利记录
			User supUser = userService.getid(user.getSuperior_id());//获取上级用户的账户
			supUser.setAccount(supUser.getAccount()-rpSupYuE.getReceipt());//扣除上级用户的返利金额
			userService.update(supUser);//更新上级用户的账户
*/			
			for(Receipt_Payment rp : supFenli){
				if(rp.getType() == 1){ //购物获利部分
					Receipt_Payment rpSub = new Receipt_Payment(rp.getReceipt(),time,rp.getUser_id(),1,3,-1);//订单id在上级获利记录里没有传入，再加上这里拿到订单id没有多大用处，so 扣除的余额 -1
					rePaymentService.add(rpSub);//添加扣除上级的返利记录
					User supUser = userService.getid(user.getSuperior_id());//获取上级用户的账户
					supUser.setAccount(supUser.getAccount()-rp.getReceipt());//扣除上级用户的返利金额
					userService.update(supUser);//更新上级用户的账户
				}else if(rp.getType() == 6){ // 养老金获利部分
					//Receipt_Payment rpSub = new Receipt_Payment(0-rp.getReceipt(),time,rp.getUser_id(),1,3,-2);//订单id在上级获利记录里没有传入，再加上这里拿到订单id没有多大用处，  养老金扣除的为-2 
					Receipt_Payment rpSub = new Receipt_Payment();
					rpSub.setReceipt(0-rp.getReceipt());// Because of back has layui pagable,so...
					rpSub.setDate_time(time);
					rpSub.setUser_id(rp.getUser_id());
					rpSub.setType(6);//养老金模块
					rpSub.setWay(3);
					rpSub.setOrderId(-2);
					rePaymentService.add(rpSub);//添加扣除上级的返利记录
					User supUser = userService.getid(user.getSuperior_id());//获取上级用户的账户
					supUser.setPension(user.getPension()-rp.getReceipt());//扣除上级用户的养老金
					userService.update(supUser);//更新上级用户的账户
				}
				
			}
		}
		// 扣除商品购买的积分和互余币的记录
		List<Receipt_Payment> list = rePaymentService.listByOrderTimeAndUserId(user.getId(), order.getOrder_time());
		if (list != null) {
			for (Receipt_Payment r : list) {
				
				if(r.getPayment() == 0){  //这里说明是用户购买商品获得的利润
					if(r.getType() == 4){ // 积分模块
						Receipt_Payment rpjifen = new Receipt_Payment(r.getReceipt(),time,r.getUser_id(),4,12,r.getOrderId());
						rePaymentService.add(rpjifen);//添加退换积分记录
						user.setIntegral(user.getIntegral()-(int)r.getReceipt());//扣除用户获得的积分
					}
					if(r.getType() == 2){// 互余币模块
						Receipt_Payment rphuyubi = new Receipt_Payment(r.getReceipt(),time,r.getUser_id(),2,12,r.getOrderId());
						rePaymentService.add(rphuyubi);//添加退货互余币记录
						user.setHuyubi(user.getHuyubi()-r.getReceipt());//扣除用户的互余币
					}
				}
				
			}
		}
		
		//添加退款用户的退款记录（返还用户支付金额-10元运费）
		Receipt_Payment rpTuikuan = new Receipt_Payment(refundPay,0,time,user.getId(),1,13,0,0,-1);
		rePaymentService.add(rpTuikuan);//添加退款返回记录
		user.setAccount(user.getAccount()+refundPay);//为用户重置退换的金额
		userService.update(user);//更新用户的账户
		// 设置订单状态
		order.setStatus(8);//设置订单状态为完成退款
		service.updatestatus(order);
		request.getSession().setAttribute("user", user);
		response.getWriter().print(1);
		return null;
	}
	
	@RequestMapping("/json")
	public String json(HttpServletResponse response) throws IOException{
		List<Receipt_Payment> list = rePaymentService.listByOrderTimeAndUserId(886, "2017-09-04 11:45:25");
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 退货申请中的退款 退款 返还用户支付金额-10元运费
	 * 
	 * @param orderId
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ParseException 
	 */
	@RequestMapping("/tuikuan")
	public String tuikuan(Integer orderId, HttpServletResponse response)
			throws IOException, ParseException {
		Order order = service.getid(orderId);
		double price = order.getYu_total() + order.getTotal();// 退款不 扣运费
		User user = userService.getid(order.getUser_id());
		/*user.setAccount(user.getAccount() + price);
		userService.update(user);*/

		String time = OrderNum.getregTime();
		//Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm").parse(order.getOrder_time());
		/**
		 * 扣除上级获利
		 */
		//根据记录时间查询和退款用户的上级id查询是否有上级获利记录
		int supId = user.getSuperior_id();
		List<Receipt_Payment> supFenli = rePaymentService.listByOrderTimeAndUserId(supId, order.getOrder_time());
		if (supFenli.size() != 0) {
			/*Receipt_Payment rpSupYuE = supFenli.get(0);
			Receipt_Payment rpSub = new Receipt_Payment(rpSupYuE.getReceipt(),time,rpSupYuE.getUser_id(),1,3,-1);//订单id在上级获利记录里没有传入，再加上这里拿到订单id没有多大用处，so 就填-1
			rePaymentService.add(rpSub);//添加扣除上级的返利记录
			User supUser = userService.getid(user.getSuperior_id());//获取上级用户的账户
			supUser.setAccount(supUser.getAccount()-rpSupYuE.getReceipt());//扣除上级用户的返利金额
			userService.update(supUser);//更新上级用户的账户
*/			
			for(Receipt_Payment rp : supFenli){
				if(rp.getType() == 1){ //购物获利部分
					Receipt_Payment rpSub = new Receipt_Payment(rp.getReceipt(),time,rp.getUser_id(),1,3,-1);//订单id在上级获利记录里没有传入，再加上这里拿到订单id没有多大用处，so 扣除的余额 -1
					rePaymentService.add(rpSub);//添加扣除上级的返利记录
					User supUser = userService.getid(user.getSuperior_id());//获取上级用户的账户
					supUser.setAccount(supUser.getAccount()-rp.getReceipt());//扣除上级用户的返利金额
					userService.update(supUser);//更新上级用户的账户
				}else if(rp.getType() == 6){ // 养老金获利部分
					//Receipt_Payment rpSub = new Receipt_Payment(0-rp.getReceipt(),time,rp.getUser_id(),1,3,-2);//订单id在上级获利记录里没有传入，再加上这里拿到订单id没有多大用处，  养老金扣除的为-2 
					Receipt_Payment rpSub = new Receipt_Payment();
					rpSub.setReceipt(0-rp.getReceipt());// Because of back has layui pagable,so...
					rpSub.setDate_time(time);
					rpSub.setUser_id(rp.getUser_id());
					rpSub.setType(6);//养老金模块
					rpSub.setWay(3);
					rpSub.setOrderId(-2);
					rePaymentService.add(rpSub);//添加扣除上级的返利记录
					User supUser = userService.getid(user.getSuperior_id());//获取上级用户的账户
					supUser.setPension(user.getPension()-rp.getReceipt());//扣除上级用户的养老金
					userService.update(supUser);//更新上级用户的账户
				}
				
			}
			
			
		}
		// 扣除商品购买的积分和互余币的记录
		List<Receipt_Payment> list = rePaymentService.listByOrderTimeAndUserId(user.getId(), order.getOrder_time());
		if (list != null) {
			for (Receipt_Payment r : list) {
				
				if(r.getPayment() == 0){  //这里说明是用户购买商品获得的利润
					if(r.getType() == 4){ // 积分模块
						Receipt_Payment rpjifen = new Receipt_Payment(r.getReceipt(),time,r.getUser_id(),4,12,r.getOrderId());
						rePaymentService.add(rpjifen);//添加退换积分记录
						user.setIntegral(user.getIntegral()-(int)r.getReceipt());//扣除用户获得的积分
					}
					if(r.getType() == 2){// 互余币模块
						Receipt_Payment rphuyubi = new Receipt_Payment(r.getReceipt(),time,r.getUser_id(),2,12,r.getOrderId());
						rePaymentService.add(rphuyubi);//添加退货互余币记录
						user.setHuyubi(user.getHuyubi()-r.getReceipt());//扣除用户的互余币
					}
					if(r.getType() == 6){ //养老金模块
						Receipt_Payment rpPension = new Receipt_Payment();
						rpPension.setReceipt(0-r.getReceipt());
						rpPension.setDate_time(time);
						rpPension.setUser_id(r.getUser_id());
						rpPension.setType(6);//养老金模块
						rpPension.setWay(12);//退款模块
						rePaymentService.add(rpPension);
						user.setPension(user.getPension()-r.getReceipt());//扣除用户购买商品的养老金
					}
				}
				
			}
		}
		
		//添加退款用户的退款记录（返还用户支付金额-10元运费）
		Receipt_Payment rpTuikuan = new Receipt_Payment(price,0,time,user.getId(),1,12,0,0,-1);
		rePaymentService.add(rpTuikuan);//添加退款返回记录
		user.setAccount(user.getAccount()+price);//为用户重置退换的金额
		userService.update(user);//更新用户的账户
		// 设置订单状态
		order.setStatus(6);//设置订单状态为完成退款
		service.updatestatus(order);
		response.getWriter().print("1");
		return null;
	}
	
	

}
