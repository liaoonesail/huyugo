package com.huyu.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.User;
import com.huyu.entity.Userinfo;
import com.huyu.service.MemberSystem;
import com.huyu.service.UserService;
import com.huyu.service.UserinfoService;
import com.huyu.util.OrderNum;
import com.huyu.util.Utils;
import com.huyu.util.page;

@Controller
@RequestMapping("/adminuser")
public class UserAction {
	@Resource
	private UserService service;
	@Resource
	private MemberSystem memberSystem;
	@Resource
	private UserinfoService uiService;

	/**
	 * 
	 * @param
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/reg")
	// 需要填写用户名、密码、邮箱、手机号码（短信验证）、上级id()
	public String reg(User user, HttpServletResponse response)
			throws IOException {
		User user1 = service.getByPhone(user.getPhone());
		if (user1 != null) {
			response.getWriter().print("用户名已经存在");
		} else {
			service.reg(user);
			memberSystem.bemember(user);// 级联向上修改vip等级
		}
		return "注册成功";
	}

	/**
	 * 
	 * @param
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/login")
	// 用户登录，手机号登录
	public String login(User user, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		User user1 = service.getByPhone(user.getPhone());
		if (user1 != null) {
			response.getWriter().print("用户名不存在");
		} else if (!user.getPassword().equals(user1.getPassword())) {
			response.getWriter().print("密码错误");
		} else {
			request.getSession().setAttribute("user", user1);
			response.getWriter().print("success");
		}
		return "登陆成功";
	}

	/**
	 * vip会员分页模糊查询
	 * 
	 * @param name
	 * @param curpage
	 * @param member_grade
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/viplist")
	public String viplist(String name, String curpage, Integer member_grade,
			HttpServletResponse response) throws IOException {
		name = name == null ? "" : name;
		int count = service.countBymember(name, member_grade);
		page page = new page(curpage, count, 10);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("name", name);
		map.put("member_grade", member_grade);
		List<User> list = service.viplist(map);
		map.put("page", page);
		map.put("list", list);
		map.put("count", count);
		JSONArray json = JSONArray.fromObject(map);
		response.getWriter().print(json);
		return null;
	}

	/**
	 * 普通会员分页模糊查询
	 * 
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/noviplist")
	public String noviplist(String name, String curpage,
			HttpServletResponse response) throws IOException {
		name = name == null ? "" : name;
		int count = service.countBynormal(name);
		page page = new page(curpage, count, 10);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("name", name);
		List<User> list = service.noviplist(map);
		map.put("page", page);
		map.put("list", list);
		map.put("count", count);
		JSONArray json = JSONArray.fromObject(map);
		response.getWriter().print(json);
		return null;
	}

	@RequestMapping("/getid")
	public String getid(Integer id, HttpServletResponse response)
			throws IOException {
		User user = service.getid(id);
		Userinfo ui = uiService.getByUser_id(id);
		user.setUserInfo(ui);
		JSONObject json = JSONObject.fromObject(user);
		response.getWriter().print(json);
		return null;
	}

	/**
	 * 后台手动添加会员
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/add")
	public String add(User user, HttpServletResponse response)
			throws IOException {
		User user1 = service.getByPhone(user.getPhone());
		if (user1 != null) {
			response.getWriter().print(false);
		} else {
			user.setPassword(Utils.MD5(user.getPassword()));
			if (user.getMember() == 1) {
				user.setMember_time(OrderNum.getregTime());
			}
			service.add(user);
			response.getWriter().print(true);
		}
		return null;
	}

	/**
	 * 修改会员参数
	 * 
	 * @param user
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/update")
	public String update(User user,Userinfo userInfo,Integer uiID, HttpServletResponse response)
			throws IOException {
		if (user.getMember() == 1) {
			user.setMember_time(OrderNum.getregTime());
		}
		userInfo.setId(uiID);
		System.out.println("用户id");
		System.out.println(user.getId()+"-------");
		int id = user.getId();
		userInfo.setUser_id(id);
		Userinfo byUser_id = uiService.getByUser_id(id);
		if(byUser_id!=null){
			userInfo.setHead_picture(byUser_id.getHead_picture());
		}
		uiService.update(userInfo);
		User user1=service.getid(user.getId());
		user.setPensionTime(user1.getPensionTime());
		service.update(user);
		response.getWriter().print(true);
		return null;
	}

	/**
	 * 后台删除会员
	 * 
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/del")
	public String del(Integer id, HttpServletResponse response)
			throws IOException {
		service.del(id);
		response.getWriter().print(true);
		return null;

	}

}
