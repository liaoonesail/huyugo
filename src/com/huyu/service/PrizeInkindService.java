package com.huyu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.PrizeInkindDao;
import com.huyu.entity.PrizeInkind;

@Service
@Transactional
public class PrizeInkindService {
	@Resource
	private PrizeInkindDao dao;
	
	/**
	 * 添加奖品
	 * @param pk
	 */
	public void add(PrizeInkind pk) {
		dao.add(pk);
	}
	
	/**
	 * 获取数据
	 * @return
	 */
	public PrizeInkind getOne() {
		return dao.getOne();
	}
	
	/**
	 * 更新
	 * @param pk
	 */
	public void update(PrizeInkind pk) {
		dao.update(pk);
	}
}
