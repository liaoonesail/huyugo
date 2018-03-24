package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.TransferAccounts;

@Repository
public class TransferAccountsDao {

	@Resource
	private SqlSessionTemplate sm;
	
	public void add(TransferAccounts accounts){
		sm.insert("com.huyu.entity.TransferAccounts.add", accounts);
	}
	
	public void del(int id){
		sm.delete("com.huyu.entity.TransferAccounts.del", id);
	}
	
	public TransferAccounts getid(int id){
		return sm.selectOne("com.huyu.entity.TransferAccounts.getid", id);
	}
	
	public List<TransferAccounts> getByShiftTo(int shiftTo){
		return sm.selectList("com.huyu.entity.TransferAccounts.getByShiftTo", shiftTo);
	}
	
	
}
