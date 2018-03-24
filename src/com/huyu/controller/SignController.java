package com.huyu.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Sign;
import com.huyu.entity.User;
import com.huyu.service.SignService;

@Controller
@RequestMapping("/sign")
public class SignController {

	private SignService signService;

	public SignService getSignService() {
		return signService;
	}
	@Resource
	public void setSignService(SignService signService) {
		this.signService = signService;
	}
	
	/**
	 * 签到方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/addSign")
	public String addSign(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print("notlogin");
			return null;
		}
		List<Sign> signList = signService.getlist(user.getId());
		SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		String day = df.format(new Date());
		for(Sign sign : signList){
			if(sign.getSigndate().equals(day)){
				response.getWriter().print("isSign");
				return null;
			}
		}
		int i = signService.add(user.getId());
		if(i > 0){
			response.getWriter().print("ok");
		}else{
			response.getWriter().print("error");
		}
		return null;
	}
	
	/**
	 * 查看今天签到情况
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/showSign")
	public String showSign(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){ //没有登陆
			response.getWriter().print("{\"status\":\"notlogin\"}");
			return null;
		}
		List<Sign> signList = signService.getlist(user.getId());
		SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		String day = df.format(new Date());
		boolean flag = true;
		String signs = "";
		for(Sign sign : signList){
			if(sign.getSigndate().equals(day)){
				flag = false;
			}
			signs += Integer.parseInt(sign.getSigndate().split("/")[2]) + ",";
		}
		if(signs.indexOf(",") > -1){
			signs = signs.substring(0, signs.length() - 1);
		}
		if(flag){ //没有签到，返回已签到内容
			response.getWriter().print("{\"status\":\"noSign\",\"sign\":\"" + signs + "\"}");
		}else{ //已签到，也要返回签到内容
			response.getWriter().print("{\"status\":\"isSign\",\"sign\":\"" + signs + "\"}");
		}
		return null;
	}
	
}
