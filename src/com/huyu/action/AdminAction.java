package com.huyu.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Admin;
import com.huyu.service.AdminService;
import com.huyu.util.Utils;

@Controller
@RequestMapping("/admin")
public class AdminAction {
	@Resource
	private AdminService service;
	@RequestMapping("/login")
	public String login(Admin admin,HttpServletRequest request){
		Admin admin1=service.getadmin(admin.getAdmin_name());
		if(admin1==null){
			return "redirect:../main/login.html";
		}else if(admin1.getPwd().equals(Utils.MD5(Utils.MD5("想解密"+admin.getPwd()+"？不可能的兄弟")))){
			request.getSession().setAttribute("admin", admin1);
			return "redirect:../main/index.html";		
			}
		return "redirect:../main/login.html";
	}
}