package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Miaosha;
import com.huyu.entity.Qianggou;
import com.huyu.service.QianggouService;

@Controller
@RequestMapping("/adminqianggou")
public class QianggouAction {
	@Resource
	private QianggouService service;
	@RequestMapping("/add")
	public String add(Qianggou qianggou){
		service.add(qianggou);
		return "redirect:../main/activity/qianggou_manage.html";
	}
	@RequestMapping("/list")
	public List<Qianggou> list(HttpServletResponse response) throws IOException{
		List<Qianggou> list=service.list();
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/update")
	public String update(Qianggou qianggou){
		service.update(qianggou);
		return null;
	}
	@RequestMapping("/del")
	public String del(Integer id){
		service.del(id);
		return "redirect:../main/activity/qianggou_manage.html";
	}

}
