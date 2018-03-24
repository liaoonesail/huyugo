package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Car;
import com.huyu.service.CarService;

@Controller
@RequestMapping("/admincar")
public class CarAction {
	@Resource
	private CarService service;
	@RequestMapping("/add")
	public String add(Car car,HttpServletResponse response) throws IOException{
		int status=service.add(car);//status=0,添加失败;status=1，添加成功;
		response.getWriter().print(status);
		return null;
	}
	@RequestMapping("/list")
	public String list(Integer user_id,HttpServletResponse response) throws IOException{
		List<Car> list=service.list(user_id);
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/del")
	public String del(Integer id,HttpServletResponse response) throws IOException{
		int status=service.del(id);//status=0删除失败，status=1删除成功；
		response.getWriter().print(status);
		return null;
	}

}
