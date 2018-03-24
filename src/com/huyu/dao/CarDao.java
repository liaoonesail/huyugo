package com.huyu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Car;
@Repository
public class CarDao {
	@Resource
	private SqlSessionTemplate sm;
	public int add(Car car) {
		// TODO Auto-generated method stub
		return sm.insert("com.huyu.entity.Car.add", car);
	}
	public List<Car> list(Integer user_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Car.getListBy_user_id", user_id);
	}
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return sm.delete("com.huyu.entity.Car.del", id);
	}
	public int update(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.Car.update", map);
	}
	public int updatePrice(Car car) {
		// TODO Auto-generated method stub
		System.out.println("updatePrice:"+car);
		return sm.update("com.huyu.entity.Car.updatePrice", car);
	}
	public Car getCarByUserIdAndGoodsId(Integer userId,Integer goodsId){
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("user_id", userId);
		map.put("goods_id", goodsId);
		return sm.selectOne("com.huyu.entity.Car.getCarByUserIdAndGoodsId", map);
	}
	public List<Car> listByGoodsAndPrice(Map<String,Object> map){
		return sm.selectList("com.huyu.entity.Car.listByGoodsAndPrice", map);
	}

}
