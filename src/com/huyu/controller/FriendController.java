package com.huyu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.User;
import com.huyu.service.UserService;

/**
 * 我的邀请
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/friend")
public class FriendController {
	
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 根据当前用户ID查看下级用户
	 * @param request
	 * @return
	 */
	@RequestMapping("/showFriendView")
	public String showFriendView(HttpServletRequest request,String curpage){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		Map<String, Object> userList = userService.LowerLevelList(user.getId(),curpage);
		List<User> list = (List<User>) userList.get("list");
		int i=0;
		for (User a:list) {
			i++;
		}
		request.setAttribute("userList", userList);
		request.setAttribute("curpage", curpage);
		request.setAttribute("i", i);
		return "memberCenter/right/friends1";
	}
	
	public String showFriendData(){
		
		return "";
	}
}
