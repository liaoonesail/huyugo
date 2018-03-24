package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.huyu.entity.Goods;
import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Shop;
import com.huyu.service.ShopService;

@Controller
@RequestMapping("/adminshop")
public class ShopAction {

	@Resource
	private ShopService service;

	@RequestMapping("/add")
	public String add(Shop shop) {
		Shop byName = service.getByName(shop.getName());
		if (byName == null) {
			service.add(shop);
			return "redirect:../main/shop/article_manage.html";
		} else {
			return "redirect:../main/shop/article_add.html";
		}

	}

	@RequestMapping("/list")
	public String list(Integer user_id, HttpServletResponse response)
			throws IOException {
		List<Shop> list = service.list();
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/goodsList")
	public String goodsList(Integer id,HttpServletRequest request){
		List<Goods> list=service.goodsList(id);
		request.setAttribute("goodsList",list);
		return "shop";
	}

	@RequestMapping("del")
	public String del(Integer id, HttpServletResponse response) {
		try {
			service.del(id);
			response.getWriter().print(true);
		} catch (Exception e) {
			System.out.println("异常，删除失败");
		}
		return null;
	}

	@RequestMapping("/getid")
	public String getid(Integer id, HttpServletResponse response)
			throws IOException {
		Shop Shop = service.getById(id);
		JSONArray json = JSONArray.fromObject(Shop);
		response.getWriter().print(json);
		return null;
	}

	@RequestMapping("/update")
	public String update(Shop shop) {
		service.update(shop);
		return "redirect:../main/shop/article_manage.html";
	}

}
