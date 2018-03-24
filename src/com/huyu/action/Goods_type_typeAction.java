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

import com.huyu.entity.Goods;
import com.huyu.entity.Goods_type_type;
import com.huyu.service.GoodsService;
import com.huyu.service.Goods_type_typeService;
import com.huyu.util.page;

@Controller
@RequestMapping("/admingoods_type_type")
public class Goods_type_typeAction {
	@Resource
	private Goods_type_typeService service;
	@Resource
	private GoodsService service1;
	@RequestMapping("/add")
	public String add(Goods_type_type type){
		service.add(type);
		return "redirect:../main/goods_type_type/article_manage.html";
	}
	@RequestMapping("/list")
	public String list(String goods_type_id,HttpServletResponse response) throws IOException{
		List<Goods_type_type> list=service.list(goods_type_id);
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/listByid")
	public String listBygoods_type_id(Integer goods_type_id,HttpServletResponse response) throws IOException{
		List<Goods_type_type> list=service.listByid(goods_type_id);
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/getid")
	public String getid(Integer id,HttpServletResponse response) throws IOException{
		Goods_type_type type=service.getid(id);
		JSONObject json=JSONObject.fromObject(type);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Goods_type_type type){
		service.update(type);
		return "redirect:../main/goods_type_type/article_manage.html";
	}
	@RequestMapping("/del")
	public String del(Integer id,HttpServletResponse response) throws IOException{
		List<Goods> list=service1.listByid(id);
		if(list.size()>0){
			response.getWriter().print("此类别下还有商品，无法删除！");
			return null;
		}else{
			service.del(id);
			response.getWriter().print("删除成功！");
			return null;
		}
	}

}
