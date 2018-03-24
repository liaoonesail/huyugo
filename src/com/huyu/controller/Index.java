package com.huyu.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Goods;
import com.huyu.entity.Picture;
import com.huyu.service.GoodsService;
import com.huyu.service.PictureService;

@Controller
public class Index {
	
	private PictureService pictureService;
	private GoodsService goodsService;

	public PictureService getPictureService() {
		return pictureService;
	}
	@Resource
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	@Resource
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	
	@RequestMapping("/index")
	public String showIndex(HttpServletRequest request){
		//获取banner图片
		List<Picture> bannerList = pictureService.list(0);
		//获取最新商品集合
		List<Goods> newGoodsList = goodsService.listBynew();
		//获取推荐商品集合
		List<Goods> recommendGoodsList = goodsService.listByrecommend();
		//获取热销商品集合
		List<Goods> hotGoodsList = goodsService.listByhot();
		//获取猜你喜欢集合
		List<Goods> goodsByClickNum = goodsService.getGoodsByClickNum();
		request.setAttribute("bannerList", bannerList);
		request.setAttribute("index_", "ok");
		request.setAttribute("newGoodsList", newGoodsList);
		request.setAttribute("recommendGoodsList", recommendGoodsList);
		request.setAttribute("hotGoodsList", hotGoodsList);
		request.setAttribute("goodsByClickNum", goodsByClickNum);
		return "index";
	}
}
