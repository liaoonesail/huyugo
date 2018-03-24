package com.huyu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.UserinfoDao;
import com.huyu.entity.Userinfo;

@Service
@Transactional
public class UserinfoService {
	@Resource
	private UserinfoDao dao;
	/**
	 * 添加用户信息
	 * @param userinfo
	 * @return
	 */
	public int add(Userinfo userinfo){
		return dao.add(userinfo);
	}
	/**
	 * 根据用户信息ID获取单个用户信息
	 * @param id
	 * @return
	 */
	public Userinfo getid(Integer id){
		return dao.getid(id);
	}
	/**
	 * 根据用户ID获取单个用户信息
	 * @param user_id
	 * @return
	 */
	public Userinfo getByUser_id(Integer user_id){
		return dao.getByUser_id(user_id);
	}
	/**
	 * 修改用户信息
	 * @param userinfo
	 * @return
	 */
	public int update(Userinfo userinfo){
		return dao.update(userinfo);
	}
	
}
