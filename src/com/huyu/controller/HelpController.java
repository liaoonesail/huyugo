package com.huyu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Affiche;
import com.huyu.entity.Article;
import com.huyu.service.AfficheService;
import com.huyu.service.ArticleService;
import com.huyu.util.Utils;

@Controller
@RequestMapping("/help")
public class HelpController {

	@Resource
	private ArticleService service;
	@Resource
	private AfficheService afService;

	@RequestMapping("/showHelp")
	public String showHelp(String type,String typeStatus,HttpServletRequest request) {
		String status=typeStatus;
		/*String status = (String) request.getParameter("status");
		String type = (String)request.getParameter("type");// type 1文章  2公告
*/		System.out.println("=====================");
		System.out.println("id:"+status);
		System.out.println("=====================");
		List<Article> list1 = service.getByType(1);
		List<Article> list2 = service.getByType(2);
		List<Article> list3 = service.getByType(3);
		List<Article> list4 = service.getByType(4);
		List<Article> list5 = service.getByType(5);
		List<Affiche> aflist = afService.list();
		request.setAttribute("list1", list1);
		request.setAttribute("list2", list2);
		request.setAttribute("list3", list3);
		request.setAttribute("list4", list4);
		request.setAttribute("list5", list5);
		request.setAttribute("aflist", aflist);
		request.setAttribute("status", status);
		request.setAttribute("type", type);
		return "help/left";
	}

	@RequestMapping("/getid")
	public String getid(Integer id,Integer type,HttpServletRequest request) {
	
		
		//Integer id = (Integer) request.getAttribute("id");
		if(type == 1){
			Article article = service.getid(id, request);
			request.setAttribute("article", article);
		}else{
			Affiche af = afService.getid(id, request);
			request.setAttribute("affiche", af);
		}
		request.setAttribute("type", type);
		return "help/tp";
	}
	
	@RequestMapping("/gg")
	public String gg(Integer id,HttpServletRequest request){
		Affiche affiche = afService.getid(id, request);
		request.setAttribute("af", affiche);
		return "help/gg";
	}

}
