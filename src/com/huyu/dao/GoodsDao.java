package com.huyu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Goods;
@Repository
public class GoodsDao {
	@Resource
	private SqlSessionTemplate sm;
	public List<Goods> listByid(Integer goods_type_type_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods.listByid", goods_type_type_id);
	}
	public void add(Goods goods) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Goods.add", goods);
	}
	public int count(String goods_name) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Goods.getCount", goods_name);
	}
	public List<Goods> list(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods.getList", map);
	}
	public Goods getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Goods.getById", id);
	}
	public void update(Goods goods) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Goods.update", goods);
	}
	public int del(Integer id) {
		// TODO Auto-generated method stub
		return sm.delete("com.huyu.entity.Goods.del", id);
	}
	public List<Goods> listByhot() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods.listByhot");
	}
	public List<Goods> listBynew() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods.listBynew");
	}
	public List<Goods> listByrecommend() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods.listByrecommend");
	}
	public List<Goods> listbytypeid(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods.listbytypeid",map);
	}
	public List<Goods> listByname(String goods_name) {
		// TODO Auto-generated method stub
		
		return sm.selectList("com.huyu.entity.Goods.listByname",goods_name);
	}
	public List<Goods> listbytype_type_id(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods.listbytype_type_id", map);
	}
	public List<Goods> goodsByTuiJian(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Goods.goodsByTuiJian", map);
	}
	public List<Goods> getGoodsByDisplay(Integer display){
		return sm.selectList("com.huyu.entity.Goods.getGoodsByDisplay", display);
	}
	public List<Goods> getGoodsByStockout(){
		return sm.selectList("com.huyu.entity.Goods.getGoodsByStockout");
	}
	public void updateClickNum(Goods g){
		sm.update("com.huyu.entity.Goods.updateClickNum", g);
	}
	public List<Goods> getGoodsByClickNum(){
		return sm.selectList("com.huyu.entity.Goods.getGoodsByClickNum");
	}
}
