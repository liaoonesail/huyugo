package com.huyu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Affiche;
import com.huyu.service.AfficheService;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	private AfficheService afficheService;

	public AfficheService getAfficheService() {
		return afficheService;
	}
	@Resource
	public void setAfficheService(AfficheService afficheService) {
		this.afficheService = afficheService;
	}
	
	/**
	 * 异步获取公告列表
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getNoticeAll")
	public String getNoticeAll(HttpServletResponse response) throws IOException{
		List<Affiche> afficheList = afficheService.list();
		JSONArray json = JSONArray.fromObject(afficheList);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 显示公告列表
	 * @param request
	 * @return
	 */
	@RequestMapping("/showNoticAll")
	public String showNoticAll(HttpServletRequest request){
		List<Affiche> afficheList = afficheService.list();
		request.setAttribute("afficheList", afficheList);
		return "notice";
	}
	
	/**
	 * 显示单个公告
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/showNoticeById")
	public String showNoticeById(int id, HttpServletRequest request){
		Affiche notice = afficheService.getid(id, request);
		request.setAttribute("notice", notice);
		return "notice_details";
	}
}
