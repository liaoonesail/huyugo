package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Cuxiao;
import com.huyu.entity.Temai;
import com.huyu.service.CuxiaoService;

@Controller
@RequestMapping("/admincuxiao")
public class CuxiaoAction {
	@Resource
	private CuxiaoService service;
	@RequestMapping("/add")
	public String add(Cuxiao cuxiao){
		service.add(cuxiao);
		return "redirect:../main/activity/cuxiao_manage.html";
	}
	@RequestMapping("/list")
	public List<Cuxiao> list(HttpServletResponse response) throws IOException{
		List<Cuxiao> list=service.list();
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Cuxiao cuxiao){
		service.update(cuxiao);
		return null;
	}
	@RequestMapping("/del")
	public String del(Integer id){
		service.del(id);
		return "redirect:../main/activity/cuxiao_manage.html";
	}

}
