package com.huyu.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Egg;
import com.huyu.entity.Prize;
import com.huyu.entity.PrizeInkind;
import com.huyu.service.EggService;
import com.huyu.service.PrizeInkindService;
import com.huyu.service.PrizeService;

@Controller
@RequestMapping("adminprize")
public class PrizeAction {
	@Resource
	private PrizeService service;
	@Resource
	private EggService eggService;
	@Resource
	private PrizeInkindService pkService;
	@RequestMapping("/add")
	public String add(Prize prize){
		service.del();
		service.add(prize);
		return "redirect:../main/prize/article_manage.html";
	}
	@RequestMapping("/addEgg")
	public String add(Egg e){
		eggService.add(e);
		return "redirect:../main/prize/article_manage.html";
	}
	@RequestMapping("/update")
	public String update(Prize prize){
		service.update(prize);
		return "redirect:../main/prize/article_manage.html";
	}
	@RequestMapping("/getprize")
	public String getprize(HttpServletResponse response) throws IOException{
		Prize prize=service.getprize();
		JSONObject json=JSONObject.fromObject(prize);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/getOne")
	public String getOne(HttpServletResponse response) throws IOException {
		PrizeInkind one = pkService.getOne();
		JSONObject json = JSONObject.fromObject(one);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/getOneEgg")
	public String getOneEgg(HttpServletResponse response) throws IOException {
		Egg one = eggService.getOne();
		JSONObject json = JSONObject.fromObject(one);
		response.getWriter().print(json);
		return null;
	}
	@RequestMapping("/updatepk")
	public String updatepk(PrizeInkind pk,HttpServletResponse response) throws IOException{
		pkService.update(pk);
		response.getWriter().print(true);
		return null;
	}
	@RequestMapping("/updateegg")
	public String updateegg(Egg e,HttpServletResponse response) throws IOException{
		eggService.update(e);
		response.getWriter().print(true);
		return null;
	}

}
