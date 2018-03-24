package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Address;
import com.huyu.service.AddressService;

@Controller
@RequestMapping("/adminaddress")
public class AddressAction {
	@Resource
	private AddressService service;
	@RequestMapping("/add")
	public String add(Address address,HttpServletRequest reqeust){
		/*User user=(User) reqeust.getSession().getAttribute("user");*/
		service.add(address);
		return "redirect:../main/address/article_manage.html";
	}
	@RequestMapping("/list")
	public String list(Integer user_id,HttpServletResponse response) throws IOException{
		List<Address> list=service.list(user_id);
		JSONArray json=JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/getid")
	public String getid(Integer id,HttpServletResponse response) throws IOException{
		Address address=service.getid(id);
		if(address!=null){
			JSONObject json=JSONObject.fromObject(address);
			response.getWriter().print(json);
		}else{
			address=new Address(0, "", "", "", "", "", "", 0, 0);
			JSONObject json=JSONObject.fromObject(address);
			response.getWriter().print(json);
		}
		
		return null;
	}
	@RequestMapping("/update")
	public String update(Address address,HttpServletResponse response) throws IOException{
		int status=service.update(address);//status=0修改失败，status=1修改成功；
		response.getWriter().print(status);
		return null;
	}
	@RequestMapping("/del")
	public String del(Integer id,HttpServletResponse response) throws IOException{
		int status=service.del(id);//status=0删除失败，status=1删除成功；
		response.getWriter().print(status);
		return null;
	}

}
