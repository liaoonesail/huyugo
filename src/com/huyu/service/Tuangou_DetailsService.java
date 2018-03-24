package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.Tuangou_DetailsDao;
import com.huyu.entity.Tuangou_Details;

@Service
@Transactional
public class Tuangou_DetailsService {
	@Resource
	private Tuangou_DetailsDao dao;
	/**
	 * 
	 * @param details 添加团购明细
	 */
	public void add(Tuangou_Details details){
		dao.add(details);
	}
	/**
	 * 
	 * @param user_id 查询单个用户团购明细
	 * @return
	 */
	public List<Tuangou_Details> list(Integer user_id){
		return dao.list(user_id);
	}
	/**
	 * 
	 * @return 查询团购已经被购买的数量
	 */
	public int amount_total(){
		return dao.amount_total();
	}
	/**
	 * 
	 * @param tuangou_id 查询团购详细集合By团购ID
	 * @return
	 */
	public List<Tuangou_Details> listBytuangou_id(Integer tuangou_id){
		return dao.listBytuangou_id(tuangou_id);
	}
}
