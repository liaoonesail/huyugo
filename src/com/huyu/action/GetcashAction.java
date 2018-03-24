package com.huyu.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Getcash;
import com.huyu.entity.Userinfo;
import com.huyu.service.GetcashService;
import com.huyu.service.UserinfoService;
import com.huyu.util.page;

@Controller
@RequestMapping("/adminGetcash")
public class GetcashAction {
	@Resource
	private GetcashService service;
	@Resource
	private UserinfoService infoService;
	/**
	 * 模糊查询加分页
	 * @param name
	 * @param curpage
	 * @param user_id
	 * @param status
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/listpage")
	public String listpage(String name,String curpage,Integer status,HttpServletResponse response) throws IOException{
		name=name==null?"":name;
		int count=service.count(name,status);
		page page=new page(curpage, count, 10);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("name", name);
		map.put("status", status);
		List<Getcash> list=service.listpage(map);
		for (Getcash getcash : list) {
			Userinfo userinfo=infoService.getByUser_id(getcash.getUserId());
			getcash.setUserinfo(userinfo);
		}
		map.put("page", page);
		map.put("list", list);
		map.put("count", count);
		JSONArray json=JSONArray.fromObject(map);
		response.getWriter().print(json);
		return null;
	}
	//取消订单
	@RequestMapping("/del")
	public String del(Integer id,HttpServletResponse response) throws IOException{
		int status=service.del(id);
		response.getWriter().print(status);
		return null;
	}
	/**
	 * 获取单个对象
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/getid")
	public String getid(Integer id,HttpServletResponse response) throws IOException{
		Getcash getcash=service.getid(id);
		JSONObject json=JSONObject.fromObject(getcash);
		response.getWriter().print(json);
		return null;
	}
	/**
	 * 修个提现申请状态0,1(未提现，已经提现)
	 * @param status
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/updatestatus")
	public String updatestatus(Getcash getcash,HttpServletResponse response) throws IOException{
		getcash.setStatus(1);
		service.updatestatus(getcash);
		response.getWriter().print(true);
		return null;
	}
}
