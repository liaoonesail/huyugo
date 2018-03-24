package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Picture;
import com.huyu.service.PictureService;
import com.huyu.util.ReadWriteTxt;
@Controller
@RequestMapping("/adminpicture")
public class PictureAction {
	@Resource
	private PictureService service;
	@RequestMapping("/add")
	public String add(Picture picture){
		String pictures_address = picture.getPicture();
		String[] split = pictures_address.split("/>");
		for (String string : split) {
			String substring = string.substring(string.indexOf("src=")+5,string.indexOf("alt=")-2);
			picture.setPicture(substring);
			service.add(picture);
		}
		return "redirect:../main/detail_picture/article_manage.html?goods_id="+picture.getGoods_id();
	}
	@RequestMapping("/list")
	public String list(Integer goods_id,HttpServletResponse response) throws IOException{
		goods_id=goods_id==null?0:goods_id;
		List<Picture> list=service.list(goods_id);
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/del")
	public String del(Integer id,HttpServletRequest request){
		Picture picture=service.getid(id);
		String path = picture.getPicture();
		String substring = path.substring(path.indexOf(request.getContextPath())+15);
		ReadWriteTxt.del(substring, request);
		service.del(id);
		return "redirect:../main/detail_picture/article_manage.html?goods_id="+picture.getGoods_id();
	}
}
