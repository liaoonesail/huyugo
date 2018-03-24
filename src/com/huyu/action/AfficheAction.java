package com.huyu.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Affiche;
import com.huyu.service.AfficheService;
import com.huyu.util.ReadWriteTxt;

@Controller
@RequestMapping("/adminaffiche")
public class AfficheAction {
	@Resource
	private AfficheService service;
	@RequestMapping("/add")
	public String add(Affiche affiche,HttpServletRequest request){
		String html=affiche.getContent();
		String time = new Date().getTime()+"";
		ReadWriteTxt.write(html, time, request);
		affiche.setContent("upload"+request.getContextPath()+"/html/"+time+".txt");
		service.add(affiche);
		return "redirect:../main/affiche/article_manage.html";
	}
	@RequestMapping("/list")
	public String list(HttpServletResponse response) throws IOException{
		List<Affiche> list=service.list();
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/getid")
	public String getid(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
		Affiche affiche=service.getid(id,request);
		JSONObject json=JSONObject.fromObject(affiche);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Affiche affiche,HttpServletRequest request){
		//获取公告以前信息
		Affiche affiche1 = service.getid(affiche.getId());
		//删除以前的公告详情.txt
		ReadWriteTxt.del(affiche1.getContent(), request);
		//添加公告修改的参数
		String html=affiche.getContent();
		String time = new Date().getTime()+"";
		ReadWriteTxt.write(html, time, request);
		affiche.setContent("upload"+request.getContextPath()+"/html/"+time+".txt");
		service.update(affiche);
		return "redirect:../main/affiche/article_manage.html";
	}
	@RequestMapping("/del")
	public String del(Integer id,HttpServletRequest request){
		//获取公告以前信息
		Affiche affiche1 = service.getid(id);
		//删除以前的公告详情.txt
		ReadWriteTxt.del(affiche1.getContent(), request);
		service.del(id);
		return "redirect:../main/affiche/article_manage.html";
	}
}
