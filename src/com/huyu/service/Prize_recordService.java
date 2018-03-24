package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.Prize_recordDao;
import com.huyu.entity.Prize_record;
import com.huyu.util.OrderNum;

@Service
@Transactional
public class Prize_recordService {
	@Resource
	private Prize_recordDao dao;
	/**
	 * 
	 * @param prize_record 添加中奖纪录
	 * @return
	 */
	public int add(Prize_record prize_record){
		prize_record.setDate_time(OrderNum.getregTime());
		int status=dao.add(prize_record);
		return status;//0 or 1(添加失败或成功)
	}
	/**
	 * 
	 * @param user_id 获取用户抽奖纪录
	 * @return
	 */
	public List<Prize_record> listByuser_id(Integer user_id){
		
		return dao.listByuser_id(user_id);
	}
	/**
	 * 
	 * @return 根据抽奖类型查询最新的十条中奖纪录
	 */
	public List<Prize_record> listBynew(String type){
		return dao.listBynew(type);
	}
	
}

