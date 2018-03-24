package com.huyu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Search;
import com.huyu.service.SearchService;

@Controller
@RequestMapping("/hotsearch")
public class HotSearchController {
	@Resource
	private SearchService searchService;
	/**
	 * 根据状态获取热门搜索list 
	 * @param display
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/listByDisplay")
	public String listByDisplay(Integer display,HttpServletResponse response) throws IOException{
		List<Search> search = searchService.listByDisplay(display);
		JSONArray json=JSONArray.fromObject(search);
		response.getWriter().print(json);
		return null;
	}
}
