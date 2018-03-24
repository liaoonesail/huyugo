package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Picture;
@Repository
public class PictureDao {
	@Resource
	private SqlSessionTemplate sm;
	public void add(Picture picture) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.Picture.add", picture);
	}
	public List<Picture> list(Integer goods_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.Picture.listByid", goods_id);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.Picture.del", id);
	}
	public Picture getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.Picture.getById", id);
	}

}
