package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Qianggou;
import com.huyu.entity.Temai;
import com.huyu.service.TemaiService;

@Controller
@RequestMapping("/admintemai")
public class TemaiAction {
	@Resource
	private TemaiService service;
	@RequestMapping("/add")
	public String add(Temai temai){
		service.add(temai);
		return "redirect:../main/activity/temai_manage.html";
	}
	@RequestMapping("/list")
	public List<Temai> list(HttpServletResponse response) throws IOException{
		List<Temai> list=service.list();
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Temai temai){
		service.update(temai);
		return null;
	}
	@RequestMapping("/del")
	public String del(Integer id){
		service.del(id);
		return "redirect:../main/activity/temai_manage.html";
	}
}
