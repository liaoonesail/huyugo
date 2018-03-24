package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.PictureDao;
import com.huyu.entity.Picture;
@Service
@Transactional
public class PictureService {
	@Resource
	private PictureDao dao;
	/**
	 * 
	 * @param picture 添加商品图片（轮播）
	 */
	public void add(Picture picture) {
		// TODO Auto-generated method stub
		dao.add(picture);
	}
	/**
	 * 
	 * @param goods_id 查询单个商品的轮播图片集合根据商品id,id=0时查询的是官网首页轮播图
	 * @return
	 */
	public List<Picture> list(int goods_id) {
		// TODO Auto-generated method stub
		return dao.list(goods_id);
	}
	/**
	 * 
	 * @param id 删除单个图片
	 */
	public void del(Integer id) {
		// TODO Auto-generated method stub
		dao.del(id);
	}
	public Picture getid(Integer id) {
		// TODO Auto-generated method stub
		return dao.getid(id);
	}
	

}
