package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.AfficheDao;
import com.huyu.entity.Affiche;
import com.huyu.util.ReadWriteTxt;

@Service
@Transactional
public class AfficheService {
	@Resource
	private AfficheDao dao;

	public void add(Affiche affiche) {
		// TODO Auto-generated method stub
		dao.add(affiche);
	}
	/**
	 * 
	 * @return 查询公告列表 不包含公告详情
	 */
	public List<Affiche> list() {
		// TODO Auto-generated method stub
		return dao.list();
	}
	/**
	 * 
	 * @param id 获取单个公告 包含详情
 	 * @return
	 */
	public Affiche getid(Integer id,HttpServletRequest request) {
		// TODO Auto-generated method stub
		Affiche affiche=dao.getid(id);
		String read = ReadWriteTxt.read(affiche.getContent(), request);
		affiche.setContent(read);
		return affiche;
	}
	/**
	 * 
	 * @param id 获取单个公告 不包含详情
	 * @return
	 */
	public Affiche getid(Integer id){
		return dao.getid(id);
	}
	/**
	 * 
	 * @param affiche 修改公告
	 */
	public void update(Affiche affiche) {
		// TODO Auto-generated method stub
		dao.update(affiche);
	}
	/**
	 * 
	 * @param id 删除公告
	 */
	public void del(Integer id) {
		// TODO Auto-generated method stub
		dao.del(id);
	}
}
