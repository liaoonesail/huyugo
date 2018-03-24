package com.huyu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Order;
import com.huyu.entity.Order_details;
import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.User;
import com.huyu.entity.Userinfo;
import com.huyu.service.Goods_commentService;
import com.huyu.service.OrderService;
import com.huyu.service.Order_detailsService;
import com.huyu.service.Receipt_PaymentService;
import com.huyu.service.UserService;
import com.huyu.service.UserinfoService;
import com.huyu.util.OrderNum;
import com.huyu.util.Utils;

@Controller
@RequestMapping("/person")
public class PersonCenterController {
	@Resource
	private Receipt_PaymentService recService;
	private UserService userService;
	private OrderService orderService;
	private Order_detailsService order_detailsService;
	private Goods_commentService goods_commentService;
	private UserinfoService userinfoService;

	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
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
	public void setOrder_detailsService(Order_detailsService order_detailsService) {
		this.order_detailsService = order_detailsService;
	}
	
	public Goods_commentService getGoods_commentService() {
		return goods_commentService;
	}
	@Resource
	public void setGoods_commentService(Goods_commentService goods_commentService) {
		this.goods_commentService = goods_commentService;
	}
	
	public UserinfoService getUserinfoService() {
		return userinfoService;
	}
	@Resource
	public void setUserinfoService(UserinfoService userinfoService) {
		this.userinfoService = userinfoService;
	}
	
	@RequestMapping("/showCenter")
	public String showCenter(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		//【养老金】会员进入会员中心
		if("".equals(user.getPensionTime())&&user.getPension()!=0){
			String time = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
			user.setPensionTime(time);
			userService.updatePensionTime(user);//更新养老金初次时间
		}
		
		String status = (String)request.getParameter("status");
		String url = null;
		if(status != null){
			//为1时表示跳转到收货地址显示页
			if("1".equals(status)){
				url = "/huyugo/address/showAddress.do";
			}
		}
		if(url != null && !"".equals(url)){
			request.setAttribute("url", url);
		}
		return "memberCenter/center";
	}
	
	/**
	 * 修改密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/showPersonal")
	public String showPersonal(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		String status = request.getParameter("status");
		if(status == null || "".equals(status)){
			Userinfo ui = userinfoService.getByUser_id(user.getId());
			if(ui == null){
				ui = new Userinfo();
				ui.setUser_id(user.getId());
				ui.setPhone(user.getPhone());
				userinfoService.add(ui);
			}
			request.setAttribute("userInfo", ui);
			return "memberCenter/right/personal";
		}else{
			if("update".equals(status)){
				String password = request.getParameter("userpassword");
				String password1 = request.getParameter("userpassword1");
				String password2 = request.getParameter("userpassword2");
				if(password == null || "".equals(password) || password1 == null || "".equals(password1) || password2 == null || "".equals(password2)){
					request.setAttribute("error", "不能为空");
					request.setAttribute("index2", "1");
					return "memberCenter/right/personal";
				}
				if(!password1.equals(password2)){
					request.setAttribute("error", "两次输入的密码不一致");
					request.setAttribute("index2", "1");
					return "memberCenter/right/personal";
				}
				if(!user.getPassword().equals(Utils.MD5(password))){
					request.setAttribute("error", "旧密码有错");
					request.setAttribute("index2", "1");
					return "memberCenter/right/personal";
				}
				user.setPassword(Utils.MD5(password1));
				userService.updatePassword(user);
				session.setAttribute("user", user);
				Userinfo ui = userinfoService.getByUser_id(user.getId());
				request.setAttribute("success", "修改成功");
				request.setAttribute("index2", "1");
				request.setAttribute("userInfo", ui);
				return "memberCenter/right/personal";
			}
		}
		return "memberCenter/right/personal";
	}
	
	@RequestMapping("/home")
	public String home(HttpServletRequest request){
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}else{
			Userinfo ui = userinfoService.getByUser_id(user.getId());
			if(ui == null){
				ui = new Userinfo();
				ui.setUser_id(user.getId());
				ui.setPhone(user.getPhone());
				userinfoService.add(ui);
			}
			request.setAttribute("userInfo", ui);	
		}
		int orderAll = 0;  //所有商品-1
		int orderNoPay = 0;  //未支付商品数量0
		int orderNoFahuo=0;   //待发货商品数量1
		int orderNoShouhuo = 0;  //未收货商品数量2
		int noPingjia = 0;   //未评价的商品3
		int Pingjia=0; //已评价商品4
		int notuikuan=0;//待退款5
		int notuihuo=0;//待退货7
		List<Order> orderList = orderService.list(user.getId(), -1);
		/*for(Order order : orderList){
			List<Order_details> detailsList = order_detailsService.listByorder_id(order.getId());
			orderAll += detailsList.size();
			for(Order_details detail : detailsList){
				List<Goods_comment> commentList = goods_commentService.listBygoods_id(detail.getGoods_id());
				int i = 0;
				for(Goods_comment comment : commentList){
					if(comment.getUser_id() == user.getId()){
						i++;
						break;
					}
				}
				if(i == 0){
					noPingjia++;
				}
			}
		}*/
		List<Order> orderList2 = orderService.list(user.getId(), 0);
		for(Order order : orderList2){
			List<Order_details> detailsList = order_detailsService.listByorder_id(order.getId());
			orderNoPay += detailsList.size();
		}
		List<Order> orderList3 = orderService.list(user.getId(), 1);
		for(Order order : orderList3){
			List<Order_details> detailsList = order_detailsService.listByorder_id(order.getId());
			orderNoFahuo += detailsList.size();
		}
		List<Order> orderList4 = orderService.list(user.getId(), 2);
		for(Order order : orderList4){
			List<Order_details> detailsList = order_detailsService.listByorder_id(order.getId());
			orderNoShouhuo += detailsList.size();
		}
		List<Order> orderList5 = orderService.list(user.getId(), 3);
		for(Order order : orderList5){
			List<Order_details> detailsList = order_detailsService.listByorder_id(order.getId());
			noPingjia += detailsList.size();
		}
		List<Order> orderList6 = orderService.list(user.getId(), 4);
		for(Order order : orderList6){
			List<Order_details> detailsList = order_detailsService.listByorder_id(order.getId());
			Pingjia += detailsList.size();
		}
		List<Order> orderList7 = orderService.list(user.getId(), 5);
		for(Order order : orderList7){
			List<Order_details> detailsList = order_detailsService.listByorder_id(order.getId());
			notuikuan += detailsList.size();
		}
		List<Order> orderList8 = orderService.list(user.getId(), 7);
		for(Order order : orderList8){
			List<Order_details> detailsList = order_detailsService.listByorder_id(order.getId());
			notuihuo += detailsList.size();
		}
		Userinfo userinfo = userinfoService.getByUser_id(user.getId());
		request.setAttribute("orderAll", orderList.size()+"");//-1
		request.setAttribute("orderNoPay", orderList2.size()+"");//0
		request.setAttribute("orderNoFahuo", orderList3.size()+"");//1
		request.setAttribute("orderNoShouhuo", orderList4.size()+"");//2
		request.setAttribute("noPingjia", orderList5.size()+"");//3
		request.setAttribute("Pingjia", orderList6.size()+"");//4
		request.setAttribute("notuikuan", orderList7.size()+"");//5
		request.setAttribute("notuihuo", orderList8.size()+"");//7
		request.setAttribute("userinfo", userinfo);
		User user1=userService.getid(user.getId());
		request.setAttribute("user", user1);
		return "memberCenter/right/home";
	}
	
	/**
	 * 修改用户个人资料
	 * @param request
	 * @param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/updatePerson")
	public String updatePerson(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user1 = (User)session.getAttribute("user");
		if(user1 == null){
			return "login";
		}
		User user=userService.getid(user1.getId());
		String phone = (String)request.getParameter("phone");
		String photoName = (String)request.getParameter("photo");
		String nickName = (String)request.getParameter("nickName");
		String email = (String)request.getParameter("email");
		String weixin = (String)request.getParameter("weixin");
		String qq = (String)request.getParameter("qq");
		String bankcard = (String)request.getParameter("bankcard");
		String kaihuhang = (String)request.getParameter("bankname");
		String realName = (String)request.getParameter("realName");
		String idCard = (String)request.getParameter("idCard");
		String sex = (String)request.getParameter("sex");
		String brithyear = (String)request.getParameter("brithyear");
		String brithmonth = (String)request.getParameter("brithmonth");
		String brithday = (String)request.getParameter("brithday");
		Userinfo ui = userinfoService.getByUser_id(user.getId());
		ui.setBankcard(bankcard);
		ui.setBankname(kaihuhang);
		ui.setBirthday(brithyear+"-"+brithmonth+"-"+brithday);
		ui.setEmail(email);
		ui.setHead_picture(photoName);
		ui.setIdcard(idCard);
		ui.setNickname(nickName);
		ui.setPhone(phone);
		ui.setQq(qq);
		ui.setRealname(realName);
		ui.setSex(sex);
		ui.setUser_id(user.getId());
		ui.setWechat(weixin);
		userinfoService.update(ui);
		//完善资料获得5积分
		user.setIntegral(user.getIntegral()+5);
		user.setName(nickName);
		userService.update(user);
		//增加收支明细
		Receipt_Payment receipt_Payment = new Receipt_Payment(5, 0, OrderNum.getregTime(), user.getId(), 4, 9,0);
		recService.add(receipt_Payment);
		session.setAttribute("user",user);
		return "redirect:/person/showPersonal.do";
	}
	/**
	 * 再次修改用户个人资料
	 * @param request
	 * @param
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/updatePersonAgain")
	public String updatePersonAgain(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user1 = (User)session.getAttribute("user");
		if(user1 == null){
			return "login";
		}
		User user=userService.getid(user1.getId());
		String phone = (String)request.getParameter("phone");
		String photoName = (String)request.getParameter("photo");
		String nickName = (String)request.getParameter("nickName");
		String email = (String)request.getParameter("email");
		String weixin = (String)request.getParameter("weixin");
		String qq = (String)request.getParameter("qq");
		String bankcard = (String)request.getParameter("bankcard");
		String kaihuhang = (String)request.getParameter("bankname");
		String realName = (String)request.getParameter("realName");
		String idCard = (String)request.getParameter("idCard");
		String sex = (String)request.getParameter("sex");
		String brithyear = (String)request.getParameter("brithyear");
		String brithmonth = (String)request.getParameter("brithmonth");
		String brithday = (String)request.getParameter("brithday");
		Userinfo ui = userinfoService.getByUser_id(user.getId());
		ui.setBankcard(bankcard);
		ui.setBankname(kaihuhang);
		ui.setBirthday(brithyear+"-"+brithmonth+"-"+brithday);
		ui.setEmail(email);
		ui.setHead_picture(photoName);
		ui.setIdcard(idCard);
		ui.setNickname(nickName);
		ui.setPhone(phone);
		ui.setQq(qq);
		ui.setRealname(realName);
		ui.setSex(sex);
		ui.setUser_id(user.getId());
		ui.setWechat(weixin);
		userinfoService.update(ui);
		user.setName(nickName);
		userService.update(user);
		return "redirect:/person/showPersonal.do";
	}
}
