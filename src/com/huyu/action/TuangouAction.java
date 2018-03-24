package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Temai;
import com.huyu.entity.Tuangou;
import com.huyu.service.TuangouService;

@Controller
@RequestMapping("/admintuangou")
public class TuangouAction {
	@Resource
	private TuangouService service;
	@RequestMapping("/add")
	public String add(Tuangou tuangou){
		service.add(tuangou);
		return "redirect:../main/activity/tuangou_manage.html";
	}
	@RequestMapping("/list")
	public List<Tuangou> list(HttpServletResponse response) throws IOException{
		List<Tuangou> list=service.list();
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Tuangou tuangou){
		service.update(tuangou);
		return null;
	}
	@RequestMapping("/del")
	public String del(Integer id){
		service.del(id);
		return "redirect:../main/activity/tuangou_manage.html";
	}

}
