package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.LinkDao;
import com.huyu.entity.Link;
@Service
@Transactional
public class LinkService {
	@Resource
	private LinkDao dao;
	public void add(Link link) {
		// TODO Auto-generated method stub
		dao.add(link);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		dao.del(id);
	}
	public List<Link> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	public Link getid(Integer id) {
		// TODO Auto-generated method stub
		return dao.getid(id);
	}
	public void update(Link link) {
		// TODO Auto-generated method stub
		dao.update(link);
	}

}
