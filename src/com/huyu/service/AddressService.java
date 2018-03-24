package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.AddressDao;
import com.huyu.entity.Address;

@Service
@Transactional
public class AddressService {
	@Resource
	private AddressDao dao;
	/**
	 * 
	 * @param address-添加收货地址
	 */
	public void add(Address address) {
		// TODO Auto-generated method stub
		updateall(address.getUser_id());
		address.setStatus(1);
		dao.add(address);
	}
	/**
	 * 
	 * @param user_id-根据用户id查询所有收货地址
	 * @return
	 */
	public List<Address> list(Integer user_id) {
		// TODO Auto-generated method stub
		return dao.list(user_id);
	}
	/**
	 * 
	 * @param id-获取单个收货地址详情
	 * @return
	 */
	public Address getid(Integer id) {
		// TODO Auto-generated method stub
		return dao.getid(id);
	}
	/**
	 * 
	 * @param address 修改收货地址
	 * @return
	 */
	public int update(Address address) {
		// TODO Auto-generated method stub
		updateall(address.getUser_id());
		address.setStatus(1);
		return dao.update(address);
	}
	/**
	 * 
	 * @param id 删除收货地址
	 * @return
	 */
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return dao.del(id);
	}
	/**
	 * 
	 * @param user_id 获取用户的默认地址
	 * @return
	 */
	public Address getaddress(Integer user_id){
		return dao.getaddress(user_id);
	}
	/**
	 * 
	 * @param user_id 用户最近修改或者添加的为默认地址
	 */
	public void updateall(int user_id) {
		// TODO Auto-generated method stub
		dao.updateall(user_id);
	}
}
