package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Poster;
import com.huyu.service.PosterService;

@Controller
@RequestMapping("/adminposter")
public class PosterAction {

	@Resource
	private PosterService service;

	@RequestMapping("/add")
	public String add(Poster poster) {
		List<Poster> list = service.list();
		if (list.size() > 7) { // 8张图片 最后两张为长条图
			return "redirect:../main/poster/article_add.html";
		} else {
			String path = poster.getPath();
			path = path.substring(path.indexOf("src=") + 5,
					path.indexOf("alt=") - 2);
			poster.setPath(path);
			service.add(poster);
			return "redirect:../main/poster/article_manage.html";
		}
	}

	@RequestMapping("/list")
	public String list(Integer user_id, HttpServletResponse response)
			throws IOException {
		List<Poster> list = service.list();
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}

	@RequestMapping("/getid")
	public String getid(Integer id, HttpServletResponse response)
			throws IOException {
		Poster poster = service.getById(id);
		JSONArray json = JSONArray.fromObject(poster);
		response.getWriter().print(json);
		return null;
	}

	@RequestMapping("/update")
	public String update(Poster poster) {
		String path = poster.getPath();
		path = path.substring(path.indexOf("src=") + 5,
				path.indexOf("alt=") - 2);
		poster.setPath(path);
		service.update(poster);
		return "redirect:../main/poster/article_manage.html";
	}

}
