package com.huyu.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Logistics;
import com.huyu.entity.Order;
import com.huyu.entity.Order_details;
import com.huyu.service.LogisticsService;
import com.huyu.service.OrderService;
import com.huyu.service.Order_detailsService;
import com.huyu.util.OrderNum;
import com.huyu.util.page;

@Controller
@RequestMapping("/adminlogistics")
public class LogisticsAction {

	@Resource
	private LogisticsService service;
	@Resource
	private OrderService orderService;
	@Resource
	private Order_detailsService odService;

	@RequestMapping("/add")
	public String add(Logistics logistics, HttpServletResponse response)
			throws IOException {
		Order order1 = orderService.getid(logistics.getOrder_id());
		if(order1.getStatus() == 5){
			response.getWriter().print(false);
			return null;
		}
		logistics.setTime(OrderNum.getregTime());//设置发货时间
		service.addLogistics(logistics);
		// 更新订单状态(更改订单为已发货)
		List<Order_details> listByorder_id = odService.listByorder_id(logistics
				.getOrder_id());
		//获取订单详情，判断每个商品是否有物流信息，如果该笔订单的所有商品都有物流信息就将该笔订单的订单状态改为已发货
		int count = 0;
		for (Order_details od : listByorder_id) {
			Logistics byOrderId = service.getByOrderId(od.getOrder_id(),
					od.getGoods_id());
			if (byOrderId != null) {
				count++;
			}
		}
		if (count == listByorder_id.size()) {
			Order order = new Order();
			order.setId(logistics.getOrder_id());
			order.setStatus(2);
			orderService.updatestatus(order);
		}
		response.getWriter().print(true);
		return null;
	}

	/**
	 * 更新物流信息
	 * 
	 * @param logistics
	 *            根据 order_id && goods_id 更新
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/update")
	public String update(Logistics logistics, HttpServletResponse response)
			throws IOException {
		logistics.setTime(OrderNum.getregTime());//设置发货时间
		service.update(logistics);
		response.getWriter().print(true);
		return null;
	}

	/**
	 * 获取单个物流信息
	 * 
	 * @param orderId
	 *            订单id
	 * @param goodsId
	 *            商品id
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getByOrderId")
	public String getByOrderId(Integer orderId, Integer goodsId,
			HttpServletResponse response) throws IOException {
		Logistics logistics = service.getByOrderId(orderId, goodsId);
		JSONObject json = JSONObject.fromObject(logistics);
		response.getWriter().print(json);
		return null;
	}

	/**
	 * 获取物流信息
	 * 
	 * @param logistics
	 *            根据 order_id && goods_id 更新
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getid")
	public String getid(Logistics logistics, HttpServletResponse response)
			throws IOException {
		Logistics logistics1 = service.getid(logistics.getOrder_id(),
				logistics.getGoods_id());
		JSONObject json = JSONObject.fromObject(logistics1);
		response.getWriter().print(json);
		return null;
	}

	@RequestMapping("/del")
	public String del(Integer id, HttpServletResponse response)
			throws IOException {
		service.del(id);
		response.getWriter().print(true);
		return null;
	}

}
