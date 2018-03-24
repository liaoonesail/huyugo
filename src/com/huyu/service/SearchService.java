package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.SearchDao;
import com.huyu.entity.Search;
@Service
@Transactional
public class SearchService {
	@Resource
	private SearchDao dao;
	/**
	 * 获取单个对象
	 * @param id
	 * @return
	 */
	public Search getid(Integer id) {
		// TODO Auto-generated method stub
		return dao.getid(id);
	}
	public List<Search> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	public void add(Search search) {
		// TODO Auto-generated method stub
		dao.add(search);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		dao.del(id);
	}
	/**
	 * 根据是否展示获取list
	 * @param display
	 * @return
	 */
	public List<Search> listByDisplay(Integer display){
		return dao.listByDisplay(display);
	}
	public void update(Search search) {
		// TODO Auto-generated method stub
		dao.update(search);
	}

}
