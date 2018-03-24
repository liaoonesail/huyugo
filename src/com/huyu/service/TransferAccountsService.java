package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.TransferAccountsDao;
import com.huyu.entity.TransferAccounts;

@Service
@Transactional
public class TransferAccountsService {

	@Resource
	private TransferAccountsDao dao;
	
	/**
	 * 添加转账记录
	 * @param accounts
	 */
	public void add(TransferAccounts accounts){
		dao.add(accounts);
	}
	
	/**
	 * 根据id删除单个记录
	 * @param id
	 */
	public void del(int id){
		dao.del(id);
	}
	
	/**
	 * 根据id获取单个转账记录
	 * @param id 转账记录id
	 * @return
	 */
	public TransferAccounts getid(int id){
		return dao.getid(id);
	}
	
	/**
	 * 根据转入方查询转账记录
	 * @param shiftTo 转入方用户id
	 * @return
	 */
	public List<TransferAccounts> getByShiftTo(int shiftTo){
		return dao.getByShiftTo(shiftTo);
	}
	
	
	
}
