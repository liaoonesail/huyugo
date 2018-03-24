package com.huyu.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.SignDao;
import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.Sign;
import com.huyu.util.OrderNum;

@Service
@Transactional
public class SignService {
	@Resource
	private SignDao dao;
	@Resource
	private UserService service;
	
	/**
	 * 
	 * @param user_id 用户签到时调用/用户的抵现金增加10
	 * @return
	 */
	public int add(Integer user_id){
		SimpleDateFormat df=new SimpleDateFormat("yyyy/MM/dd");
		Sign sign=new Sign(user_id, df.format(new Date()).toString());
		int status=dao.add(sign);
		int receipt=10;
		if(status>0){
			//自己用户抵现金（签到时获得10）
			status=service.update_dixianjin(user_id,receipt);
		}
		return status;//status=0添加失败；status=1添加成功
	}
	/**
	 * 
	 * @param user_id 获取用户当月签到对象集合
	 * @return
	 */
	public List<Sign> getlist(Integer user_id){
		return dao.getlist(user_id);
	}
}
