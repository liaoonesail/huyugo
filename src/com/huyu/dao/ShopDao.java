package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import com.huyu.entity.Goods;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Shop;


@Repository
public class ShopDao {
	@Resource
	private SqlSessionTemplate sm;
	public int add(Shop Shop) {
		return sm.insert("com.huyu.entity.Shop.add", Shop);
	}
	public List<Shop> list() {
		return sm.selectList("com.huyu.entity.Shop.list");
	}
	public void del(Integer id) {
		sm.delete("com.huyu.entity.Shop.del", id);
	}
	public int update(Shop Shop) {
		return sm.update("com.huyu.entity.Shop.update", Shop);
	}
	public Shop getById(Integer id){
		return sm.selectOne("com.huyu.entity.Shop.getById", id);
	}
	public Shop getByName(String name){
		return sm.selectOne("com.huyu.entity.Shop.getByName", name);
	}


	public List<Goods> goodsList(Integer shopid) {
		return sm.selectList("com.huyu.entity.Goods.listByShopId",shopid);
	}
}
