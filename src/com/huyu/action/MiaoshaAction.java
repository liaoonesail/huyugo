package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Miaosha;
import com.huyu.service.MiaoshaService;

@Controller
@RequestMapping("/adminmiaosha")
public class MiaoshaAction {
	@Resource
	private MiaoshaService service;
	@RequestMapping("/add")
	public String add(Miaosha miaosha){
		service.add(miaosha);
		return "redirect:../main/activity/miaosha_manage.html";
	}
	@RequestMapping("/list")
	public List<Miaosha> list(HttpServletResponse response) throws IOException{
		List<Miaosha> list=service.list();
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Miaosha miaosha){
		service.update(miaosha);
		return null;
	}
	@RequestMapping("/del")
	public String del(Integer id){
		service.del(id);
		return "redirect:../main/activity/miaosha_manage.html";
	}

}
