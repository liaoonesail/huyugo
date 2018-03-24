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

import com.huyu.entity.Article;
import com.huyu.service.ArticleService;
import com.huyu.util.ReadWriteTxt;

@Controller
@RequestMapping("/adminarticle")
public class ArticleAction {
	@Resource
	private ArticleService service;
	@RequestMapping("/add")
	public String add(Article Article,HttpServletRequest request){
		String html=Article.getPath();
		String time = new Date().getTime()+"";
		ReadWriteTxt.write(html, time, request);
		Article.setPath("upload"+request.getContextPath()+"/html/"+time+".txt");
		service.add(Article);
		return "redirect:../main/help/article_manage.html";
	}
	@RequestMapping("/list")
	public String list(Integer type,HttpServletResponse response) throws IOException{
		List<Article> list=service.list();
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/getid")
	public String getid(Integer id,HttpServletResponse response,HttpServletRequest request) throws IOException{
		Article Article=service.getid(id,request);
		JSONObject json=JSONObject.fromObject(Article);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Article Article,HttpServletRequest request){
		//获取公告以前信息
		Article Article1 = service.getid(Article.getId());
		//删除以前的公告详情.txt
		ReadWriteTxt.del(Article1.getPath(), request);
		//添加公告修改的参数
		String html=Article.getPath();
		String time = new Date().getTime()+"";
		ReadWriteTxt.write(html, time, request);
		Article.setPath("upload"+request.getContextPath()+"/html/"+time+".txt");
		service.update(Article);
		return "redirect:../main/help/article_manage.html";
	}
	@RequestMapping("/del")
	public String del(Integer id,HttpServletRequest request){
		//获取公告以前信息
		Article Article1 = service.getid(id);
		//删除以前的公告详情.txt
		ReadWriteTxt.del(Article1.getPath(), request);
		service.del(id);
		return "redirect:../main/help/article_manage.html";
	}
	@RequestMapping("/getByType")
	public String getByType(Integer type,HttpServletResponse response) throws IOException{
		List<Article> list=service.getByType(type);
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
}
