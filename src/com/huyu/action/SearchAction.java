package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Search;
import com.huyu.service.SearchService;

@Controller
@RequestMapping("/adminsearch")
public class SearchAction {
	@Resource
	private SearchService service;
	@RequestMapping("/add")
	public String add(Search search,HttpServletResponse response){
		service.add(search);
		return "redirect:../main/search/article_manage.html";
	}
	@RequestMapping("/del")
	public String del(Integer id){
		service.del(id);
		return "redirect:../main/search/article_manage.html";
	}
	@RequestMapping("/list")
	public String list(HttpServletResponse response) throws IOException{
		List<Search> list=service.list();
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/getid")
	public String getid(Integer id,HttpServletResponse response) throws IOException{
		Search search=service.getid(id);
		JSONObject json=JSONObject.fromObject(search);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Search search){
		service.update(search);
		return "redirect:../main/search/article_manage.html";
	}

}
