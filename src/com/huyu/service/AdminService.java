package com.huyu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.AdminDao;
import com.huyu.entity.Admin;
@Service
@Transactional
public class AdminService {
	@Resource
	private AdminDao dao;
	public Admin getadmin(String admin_name) {
		// TODO Auto-generated method stub
		return dao.getadmin(admin_name);
	}

}
