package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Cuxiao;

@Repository
public class CuxiaoDao {
	@Resource
	private SqlSessionTemplate sm;

	public void add(Cuxiao cuxiao) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Cuxiao.add", cuxiao);
	}

	public List<Cuxiao> list() {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Cuxiao.list");
	}

	public void update(Cuxiao cuxiao) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Cuxiao.update", cuxiao);
	}

	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Cuxiao.del", id);
	}

	public Cuxiao getgoods_id(Integer goods_id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Cuxiao.getgoods_id", goods_id);
	}
}
