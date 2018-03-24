package com.huyu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.User;
import com.huyu.po.Table;
import com.huyu.service.Receipt_PaymentService;
import com.huyu.service.UserService;

@Controller
@RequestMapping("/payment")
public class ReceiptPaymentController {

	private Receipt_PaymentService receipt_PaymentService;

	public Receipt_PaymentService getReceipt_PaymentService() {
		return receipt_PaymentService;
	}
	@Resource
	public void setReceipt_PaymentService(
			Receipt_PaymentService receipt_PaymentService) {
		this.receipt_PaymentService = receipt_PaymentService;
	}
	
	@Resource
	private UserService userService;
	
	/**
	 * 后台用户养老金详情记录
	 * @param page 当前页码
	 * @param limit 每页大小
	 * @param userId 用户id
	 * @param response 服务器响应
	 * @throws IOException 出错抛出IO异常
	 */
	@RequestMapping("/showPensionList")
	public void showPensionList(int page, int limit,int userId,HttpServletResponse response) throws IOException {
		HashMap<String, Object> map = receipt_PaymentService.listByLayuiAndType(userId, 6, page, limit);
		List list = (List) map.get("list");
		Table t = new Table(0, "success", (Integer)map.get("count"), list);
		JSONObject object = JSONObject.fromObject(t);
		response.getWriter().print(object);
	}
	
	/**
	 * 获取我的积分记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/showItegral")
	public String showItegral(String curpage,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		Map<String, Object> paymentList = receipt_PaymentService.listBytype(user.getId(), 4,curpage);
		User u = userService.getid(user.getId());
		request.setAttribute("user", u);
		request.setAttribute("curpage", curpage);
		request.setAttribute("paymentList", paymentList);
		return "memberCenter/right/integral";
	}
	/**
	 * 获取我的养老金记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/showPension")
	public String showPension(String curpage,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		Map<String, Object> paymentList = receipt_PaymentService.listBytype(user.getId(), 6,curpage);
		User u = userService.getid(user.getId());
		request.setAttribute("user", u);
		request.setAttribute("curpage", curpage);
		request.setAttribute("paymentList", paymentList);
		return "memberCenter/right/pension";
	}
	
	/**
	 * 获取我的互余币记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/showHuyubi")
	public String showHuyubi(String curpage,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		Map<String, Object> paymentList = receipt_PaymentService.listBytype(user.getId(), 2,curpage);
		User u = userService.getid(user.getId());
		request.setAttribute("user", u);
		request.setAttribute("curpage", curpage);
		request.setAttribute("paymentList", paymentList);
		return "memberCenter/right/huyubi";
	}
	
	/**
	 * 获取我的抵现金记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/showDixianjin")
	public String showDixianjin(String curpage,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		Map<String, Object> paymentList = receipt_PaymentService.listBytype(user.getId(), 3,curpage);
		User u = userService.getid(user.getId());
		request.setAttribute("user", u);
		request.setAttribute("curpage", curpage);
		request.setAttribute("paymentList", paymentList);
		return "memberCenter/right/dixianjin";
	}
	
	/**
	 * 获取我的资金详情记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/showPriceDetail")
	public String showPriceDetail(String curpage,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		Map<String, Object> paymentList = receipt_PaymentService.list(user.getId(),-1,curpage);
		User u = userService.getid(user.getId());
		request.setAttribute("user", u);
		request.setAttribute("curpage", curpage);
		request.setAttribute("paymentList", paymentList);
		request.setAttribute("status", -1);
		return "memberCenter/right/zhanghuMingxi";
	}
	
	/**
	 * 获取我的佣金详情记录
	 * @param request
	 * @return
	 */
	@RequestMapping("/showYongjinDetail")
	public String showYongjinDetail(String curpage,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		Map<String, Object> paymentList = receipt_PaymentService.listBytype(user.getId(), 1,curpage);
		User u = userService.getid(user.getId());
		request.setAttribute("user", u);
		request.setAttribute("curpage", curpage);
		request.setAttribute("status", 1);
		request.setAttribute("paymentList", paymentList);
		return "memberCenter/right/zhanghuMingxi";
	}
}
