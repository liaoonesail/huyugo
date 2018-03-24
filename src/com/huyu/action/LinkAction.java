package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Link;
import com.huyu.service.LinkService;

@Controller
@RequestMapping("/adminlink")
public class LinkAction {
	@Resource
	private LinkService service;
	@RequestMapping("/add")
	public String add(Link link,HttpServletResponse response){
		service.add(link);
		return "redirect:../main/link/article_manage.html";
	}
	@RequestMapping("/del")
	public String del(Integer id){
		service.del(id);
		return "redirect:../main/link/article_manage.html";
	}
	@RequestMapping("/list")
	public String list(HttpServletResponse response) throws IOException{
		List<Link> list=service.list();
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/getid")
	public String getid(Integer id,HttpServletResponse response) throws IOException{
		Link link=service.getid(id);
		JSONObject json=JSONObject.fromObject(link);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Link link){
		service.update(link);
		return "redirect:../main/link/article_manage.html";
	}

}
