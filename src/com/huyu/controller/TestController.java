package com.huyu.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Order;
import com.huyu.service.OrderService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Resource
	private OrderService orderService;
	
	@RequestMapping("/updateOrder")
	public String updateOrder(){
		String orderNum = "1500169450057248453";
		Order order = orderService.getorder_num(orderNum);
		order.setStatus(1);
		orderService.update(order);
		return null;
	}
}
