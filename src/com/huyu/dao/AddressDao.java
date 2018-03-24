package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Address;
@Repository
public class AddressDao {
	@Resource
	private SqlSessionTemplate sm;
	public void add(Address address) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Address.add", address);
	}
	public List<Address> list(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Address.getListBy_user_id", user_id);
	}
	public Address getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Address.getById", id);
	}
	public int update(Address address) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Address.update", address);
	}
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return sm.delete("com.huyu.entity.Address.del", id);
	}
	public Address getaddress(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Address.getAddress", user_id);
	}
	public void updateall(int user_id) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Address.updateAll",user_id);
	}

}
