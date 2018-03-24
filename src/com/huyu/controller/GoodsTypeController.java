package com.huyu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Goods_type;
import com.huyu.entity.Goods_type_type;
import com.huyu.service.Goods_typeService;
import com.huyu.service.Goods_type_typeService;

@Controller
@RequestMapping("/type")
public class GoodsTypeController {
	
	private Goods_typeService goods_typeService;

	public Goods_typeService getGoods_typeService() {
		return goods_typeService;
	}
	@Resource
	public void setGoods_typeService(Goods_typeService goods_typeService) {
		this.goods_typeService = goods_typeService;
	}
	@Resource
	private Goods_type_typeService type_typeService;
	/**
	 * 前台异步获取所有大类别
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getAllGoodsType")
	public String getAllGoodsType(HttpServletResponse response) throws IOException{
		List<Goods_type> typeList = goods_typeService.list();
		if(typeList != null && typeList.size() > 0){
			JSONArray json = JSONArray.fromObject(typeList);
			response.getWriter().print(json);
		}else{
			response.getWriter().print("error");
		}
		return null;
	}
	/**
	 * 前台根据大类ID异步获取下级类别
	 * @param goods_type_id
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getNextGoodsType")
	public String getNextGoodsType(String goods_type_id,HttpServletResponse response) throws IOException{
		List<Goods_type_type> typeList=type_typeService.list(goods_type_id);
		if(typeList != null && typeList.size() > 0){
			JSONArray json = JSONArray.fromObject(typeList);
			response.getWriter().print(json);
		}else{
			response.getWriter().print("error");
		}
		return null;
	}
}
