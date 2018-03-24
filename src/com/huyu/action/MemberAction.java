package com.huyu.action;

import java.io.IOException;
import java.io.PrintStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Member;
import com.huyu.entity.Pension;
import com.huyu.service.MemberService;

@Controller
@RequestMapping("/adminMember")
public class MemberAction {
	@Resource
	private MemberService service;
	/**
	 * 获取会员制度
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("getMember")
	public String getMember(HttpServletResponse response) throws IOException{
		Member member=service.getMember();
		JSONObject json=JSONObject.fromObject(member);
		response.getWriter().print(json);
		return null;
	}
	/**
	 * 修改会员制度
	 * @param member
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("update")
	public String update(Member member,HttpServletResponse response) throws IOException{
		
		
		
		service.update(member);
		return "article_manage2";
	}
	
	@RequestMapping("/member")
	public String member(HttpServletRequest request){
		Member member=service.getMember();
		request.setAttribute("member", member);
		return "article_manage2";
	}
	/**
	 * 获取养老金制度
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getPension")
	public String getPension(HttpServletResponse response) throws IOException{
		Pension pension=service.getPension();
		JSONObject json=JSONObject.fromObject(pension);
		response.getWriter().print(json);
		return null;
	}
	
	
	/**
	 * 更新养老金
	 * @param p
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/updatePension")
	public String updatePension(Pension p,HttpServletResponse response) throws IOException{
		try{
			service.update(p);
		}catch(Exception e){
			System.out.println("更新养老金失败");
			e.printStackTrace();
		}
		response.getWriter().print(true);
		return null;
	}
}
