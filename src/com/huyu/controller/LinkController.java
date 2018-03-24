package com.huyu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Link;
import com.huyu.service.LinkService;

@Controller
@RequestMapping("/link")
public class LinkController {
	@Resource
	private LinkService linkservice;
	/**
	 * 首页异步请求友情链接
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/linkList")
	public String linkList(HttpServletResponse response) throws IOException{
		List<Link> linkList=linkservice.list();
		JSONArray json=JSONArray.fromObject(linkList);
		response.getWriter().print(json);
		return null;
	}
}
