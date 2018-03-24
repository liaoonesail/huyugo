package com.huyu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.Dixianjin_detailsDao;
import com.huyu.entity.Dixianjin_details;

@Service
@Transactional
public class Dixianjin_detailsService {
	@Resource
	private Dixianjin_detailsDao dao;
	/**
	 * 
	 * @param dixianjin_details 添加详情
	 * @return
	 */
	public int add(Dixianjin_details dixianjin_details){
		int status=dao.add(dixianjin_details);
		return status;
	}
	/**
	 * 
	 * @param user_id 根据这些参数查询 对象
	 * @param order_id
	 * @param goods_id
	 * @return
	 */
	public Dixianjin_details getid(Integer user_id,Integer order_id,Integer goods_id){
		return dao.getid(user_id,order_id,goods_id);
	}
	/**
	 * 
	 * @param dixianjin_details 修改状态
	 * @return
	 */
	public int update(Dixianjin_details dixianjin_details){
		int status=dao.update(dixianjin_details);
		return status;
	}

}
