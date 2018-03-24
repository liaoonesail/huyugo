package com.huyu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.PrizeDao;
import com.huyu.entity.Prize;
@Service
@Transactional
public class PrizeService {
	@Resource
	private PrizeDao dao;
	/**
	 * 
	 * @param prize 添加中奖概率
	 */
	public void add(Prize prize) {
		// TODO Auto-generated method stub
		dao.add(prize);
	}
	/**
	 * 
	 * @param prize 修改中奖概率
	 */
	public void update(Prize prize) {
		// TODO Auto-generated method stub
		dao.update(prize);
	}
	/**
	 * 
	 * @return 获取中奖概率
	 */
	public Prize getprize() {
		// TODO Auto-generated method stub
		return dao.getprize();
	}
	/**
	 * 删除中奖表中的数据 
	 */
	public void del() {
		// TODO Auto-generated method stub
		dao.del();
	}

}
