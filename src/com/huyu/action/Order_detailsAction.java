package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Order_details;
import com.huyu.service.Order_detailsService;

@Controller
@RequestMapping("/adminorder_details")
public class Order_detailsAction {
	@Resource
	private Order_detailsService service;
	@RequestMapping("/list")
	public List<Order_details> list(Integer order_id,HttpServletResponse response) throws IOException{
		List<Order_details> list = service.listByorder_id(order_id);
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
}
