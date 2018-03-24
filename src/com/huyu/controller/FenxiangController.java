package com.huyu.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.User;
import com.huyu.service.UserService;

@Controller
@RequestMapping("/fx")
public class FenxiangController {

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	/**
	 * 用户分享商品获得抵现金
	 * @param goodsId
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/fenxiang")
	public String fenxiang(int goodsId, int leimu, HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print("notLogin");
			return null;
		}
		
		boolean flag = userService.shareGoods(user.getId(), goodsId, leimu);
		if(flag){
			response.getWriter().print("ok");
		}else{
			response.getWriter().print("error");
		}
		return null;
	}
}
