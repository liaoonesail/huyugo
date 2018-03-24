package com.huyu.action;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Goods;
import com.huyu.entity.Picture;
import com.huyu.service.GoodsService;
import com.huyu.service.PictureService;
import com.huyu.util.ReadWriteTxt;
import com.huyu.util.page;


@Controller
@RequestMapping("/admingoods")
public class GoodsAction {
	@Resource
	private GoodsService service;
	@Resource
	private PictureService service1;
	@RequestMapping("/add")
	public String add(Goods goods,HttpServletRequest request){
		String html=goods.getDetails();
		String picture=goods.getPicture_address();
		String picture_address = picture.substring(picture.indexOf("src=")+5,picture.indexOf("alt=")-2);
		String time = new Date().getTime()+"";
		ReadWriteTxt.write(html, time, request);
		goods.setPicture_address(picture_address);
		goods.setDetails("upload"+request.getContextPath()+"/html/"+time+".txt");
		service.add(goods);
		return "redirect:../main/goods/article_manage.html";
	}
	@RequestMapping("/list")
	public String list(Integer goods_type_type_id,String name,String curpage,HttpServletResponse response) throws IOException{
		name=name==null?"":name;
		int count=service.count(name);
		page page=new page(curpage, count, 50);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("name", name);
		map.put("goods_type_type_id", goods_type_type_id);
		List<Goods> list=service.list(map);
		map.put("page", page);
		map.put("list", list);
		JSONObject json=JSONObject.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 下架商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/getGoodsByDisplay")
	public String getGoodsByDisplay(HttpServletRequest request){
		List<Goods> list = service.getGoodsByDisplay(1);
		request.setAttribute("list", list);
		return "main/goods/xiajia";
	}
	
	/**
	 * 缺货商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/getGoodsByStockout")
	public String getGoodsByStockout(HttpServletRequest request){
		List<Goods> list = service.getGoodsByStockout();
		request.setAttribute("list", list);
		return "main/goods/quehuo";
	}
	
	
	
	@RequestMapping("/getid")
	public String getid(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		Goods goods=service.getid(id);
		String read=ReadWriteTxt.read(goods.getDetails(),request);
		goods.setDetails(read);
		JSONObject json=JSONObject.fromObject(goods);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Goods goods,HttpServletRequest request){
		//获取此商品的以前信息
		Goods goods2=service.getid(goods.getId());
		//删除以前的商品详情.txt
		ReadWriteTxt.del(goods2.getDetails(), request);
		//添加修改的商品信息
		String html=goods.getDetails();
		String picture=goods.getPicture_address();
		String picture_address = picture.substring(picture.indexOf("src=")+5,picture.indexOf("alt=")-2);
		String time = new Date().getTime()+"";
		ReadWriteTxt.write(html, time, request);
		goods.setPicture_address(picture_address);
		goods.setDetails("upload"+request.getContextPath()+"/html/"+time+".txt");
		service.update(goods);
		return "redirect:../main/goods/article_manage.html";
	}
	@RequestMapping("/del")
	public String del(Integer id,HttpServletRequest request,HttpServletResponse response) throws IOException{
		//获取此商品的以前信息
		Goods goods2=service.getid(id);
		//删除商品详细图片
		List<Picture> list=service1.list(id);
		if(list.size()>0){
			for (Picture picture : list) {
				service1.del(picture.getId());
			}
		}
		//删除以前的图片和商品详情.txt
		ReadWriteTxt.del(goods2.getDetails(), request);
		String path=goods2.getPicture_address();
		ReadWriteTxt.del(path.substring(path.indexOf(request.getContextPath())+request.getContextPath().length()+1), request);
		
		int status=service.del(id);
		response.getWriter().print(status);
		return null;
		
	}
}
