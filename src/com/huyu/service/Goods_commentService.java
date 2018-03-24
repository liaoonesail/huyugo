package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.Goods_commentDao;
import com.huyu.entity.Goods_comment;
import com.huyu.util.OrderNum;

@Service
@Transactional
public class Goods_commentService {
	@Resource
	private Goods_commentDao dao;
	/**
	 * 
	 * @param goods_comment 添加商品评价
	 * @return//0失败；1成功
	 */
	public int add(Goods_comment goods_comment){
		goods_comment.setTime(OrderNum.getSystemTime());
		int status=dao.add(goods_comment);
		return status;
	}
	/**
	 * 
	 * @param goods_id 查询商品评价
	 * @return
	 */
	public List<Goods_comment> listBygoods_id(Integer goods_id){
		return dao.listBygoods_id(goods_id);
	}
	/**
	 * 
	 * @param id：商品id
	 * @param user_id：用户id
	 * @return 0失败；1成功
	 */
	public int del(Integer id,Integer user_id){
		int status=dao.del(id,user_id);
		return status;
	}
}
