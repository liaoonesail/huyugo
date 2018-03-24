package com.huyu.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.huyu.entity.User;
import com.huyu.entity.Vip_order;
import com.huyu.service.UserService;
import com.huyu.service.Vip_orderService;
import com.huyu.util.OrderNum;

@Controller
@RequestMapping("/vip")
public class VipController {
	
	private Vip_orderService vip_orderService;
	private UserService userService;

	public Vip_orderService getVip_orderService() {
		return vip_orderService;
	}
	@Resource
	public void setVip_orderService(Vip_orderService vip_orderService) {
		this.vip_orderService = vip_orderService;
	}

	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 开通VIP跳转
	 * @param request
	 * @return
	 */
	@RequestMapping("/kaitong")
	public String kaitong(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		//生成VIP开通订单
		Vip_order order = null;
		order = vip_orderService.getid(user.getId());
		if(order == null){
			order =	new Vip_order();
			String orderNum = OrderNum.getOrderNum();
			order.setOrder_num("vip"+orderNum.substring(3, orderNum.length()));
			//order.setOrder_num(orderNum);
			order.setOrder_time(OrderNum.getregTime());
			order.setPayment("微信扫码");
			order.setStatus(0);
			order.setTotal(500 - user.getAccount());
			order.setUser_id(user.getId());
			vip_orderService.add(order);
		}
		request.setAttribute("order", order);
		return "memberCenter/right/VipKaitong";
	}
	
	/**
	 * 获取会员购买二维码
	 * @param orderNum
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getCodeUrl")
	public String getCodeUrl(String orderNum, HttpServletRequest request, HttpServletResponse response) throws IOException{
		String result = "";
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			result = "notLogin";
		}else{
			double price = 500 - user.getAccount();
			Vip_order order = vip_orderService.getvip_order(orderNum);
			if(order.getTotal() != price){
				order.setTotal(price);
				String orderNum2 = OrderNum.getOrderNum();
				order.setOrder_num("vip"+orderNum2.substring(3, orderNum2.length()));
				vip_orderService.updateOrderNumTotal(order);
			}
			result = "attach=2&detail=vip购买&desc=vip购买&goodSn=120&orderSn=" + orderNum + "&amount=" + price;
			//result = "attach=1&detail=商品购买&desc=商品购买&goodSn=" + order.getId() + "&orderSn=" + order.getOrder_num() + "&amount=0.01";
		}
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 余额全款支付
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/accountPay")
	public String accountPay(String orderNum, HttpServletRequest request, HttpServletResponse response) throws IOException{
		String result = "";
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			result = "notLogin";
		}else{
			if(user.getAccount() < 500){
				result = "notFull";
			}else{
				Vip_order order = vip_orderService.getvip_order(orderNum);
				if(order.getTotal() != 0){
					order.setTotal(0);
					vip_orderService.updateOrderNumTotal(order);
				}
				order.setStatus(1);
				vip_orderService.update(order);
				result = "ok";
				
				User user_ = userService.getByPhone(user.getPhone());
				session.setAttribute("user", user_);
			}
		}
		response.getWriter().print(result);
		return null;
	}
	
}
