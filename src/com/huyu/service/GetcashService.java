package com.huyu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.GetcashDao;
import com.huyu.entity.Getcash;
import com.huyu.entity.Receipt_Payment;
import com.huyu.util.page;

@Service
@Transactional
public class GetcashService {
	@Resource
	private GetcashDao dao;
	public int count(String name, Integer status) {
		// TODO Auto-generated method stub
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("status", status);
		return dao.count(map);
	}
	public List<Getcash> listpage(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.listpage(map);
	}
	public void add(Getcash getcash) {
		// TODO Auto-generated method stub
		dao.add(getcash);
	}
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return dao.del(id);
	}
	public Getcash getid(Integer id) {
		// TODO Auto-generated method stub
		return dao.getid(id);
	}
	public void updatestatus(Getcash getcash) {
		// TODO Auto-generated method stub
		dao.updatestatus(getcash);
	}
	public List<Getcash> cashList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.cashList(map);
	}
	
	public Map<String, Object> getByUserId(Integer userId, String curpage) {
		int count=dao.countByUid(userId);
		page page=new page(curpage, count, 20);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("userId", userId);
		List<Getcash> list = dao.getByUserId(map);
		map.put("page", page);
		map.put("countPage", page.getPageCount());
		map.put("list", list);
		map.put("count", count);
		map.put("curpage", curpage);
		return map;
	}

}
