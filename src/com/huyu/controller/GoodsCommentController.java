package com.huyu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Goods;
import com.huyu.entity.Goods_comment;
import com.huyu.entity.Order;
import com.huyu.entity.Order_details;
import com.huyu.entity.User;
import com.huyu.service.GoodsService;
import com.huyu.service.Goods_commentService;
import com.huyu.service.OrderService;
import com.huyu.service.Order_detailsService;
import com.huyu.service.UserService;

@Controller
@RequestMapping("/goodsComment")
public class GoodsCommentController {
	@Resource
	private OrderService orderService;
	@Resource
	private Order_detailsService detailsService;
	@Resource
	private UserService userService;
	
	private Goods_commentService goods_commentService;
	private GoodsService goodsService;

	public Goods_commentService getGoods_commentService() {
		return goods_commentService;
	}
	@Resource
	public void setGoods_commentService(Goods_commentService goods_commentService) {
		this.goods_commentService = goods_commentService;
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	@Resource
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	/**
	 * 显示是否评价过
	 * @param goodsId
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/showGoodsCommentByGoodsId")
	public String showGoodsCommentByGoodsId(int goodsId, HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print("error");
		}else{
			List<Goods_comment> commentList = goods_commentService.listBygoods_id(goodsId);
			boolean flag = false;
			for(Goods_comment comment : commentList){
				if(comment.getUser_id() == user.getId()){
					flag = true;
				}
			}
			if(flag){
				response.getWriter().print("ok");
			}else{
				response.getWriter().print("no");
			}
		}
		return null;
				
	}
	
	/**
	 * 跳往评论页面
	 * @param orderId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getGoodsById")
	public String getGoodsById(String orderId, HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		/*Goods goods = goodsService.getid(goodsId);
		request.setAttribute("goods", goods);*/
		User getid = userService.getid(user.getId());
		request.setAttribute("user", getid);
		request.setAttribute("orderId",orderId);
		return "memberCenter/right/commentAdd";
	}
	/**
	 * 增加商品评价
	 * @param orderId
	 * @param comment
	 * @param request
	 * @return
	 */
	@RequestMapping("/addGoodsComment")
	public String addGoodsComment(String orderId,Goods_comment comment,String curpage,HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		List<Order_details> list=detailsService.listByorder_id(Integer.parseInt(orderId));
		for (Order_details order_details : list) {
			comment.setGoods_id(order_details.getGoods_id());
			goods_commentService.add(comment);
		}
		Order order = orderService.getid(Integer.parseInt(orderId));
		order.setStatus(4);
		orderService.updatestatus(order);
		//完成评价,赠送一个积分
		double integral = user.getIntegral()+1;
		user.setIntegral(integral);
		userService.update(user);
		request.setAttribute("curpage", curpage);
		return "redirect:/order/showOrder.do?zt=-1&curpage=1";
	}
	
	@RequestMapping("/getCommentByGoodsId")
	public String getCommentByGoodsId(Integer goodsId,HttpServletResponse response) throws IOException{
		List<Goods_comment> list = goods_commentService.listBygoods_id(goodsId);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		JSONObject json = JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
}
