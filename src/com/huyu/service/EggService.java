package com.huyu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.EggDao;
import com.huyu.entity.Egg;

@Service
@Transactional
public class EggService {
	@Resource
	private EggDao dao;
	
	/**
	 * 添加奖品
	 * @param e
	 */
	public void add(Egg e) {
		dao.add(e);
	}
	
	/**
	 * 获取数据
	 * @return
	 */
	public Egg getOne() {
		return dao.getOne();
	}
	
	/**
	 * 更新
	 * @param e
	 */
	public void update(Egg e) {
		dao.update(e);
	}
}
