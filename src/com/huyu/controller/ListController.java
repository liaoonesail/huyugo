package com.huyu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Cuxiao;
import com.huyu.entity.Goods;
import com.huyu.entity.Goods_type;
import com.huyu.entity.Goods_type_type;
import com.huyu.entity.Miaosha;
import com.huyu.entity.Qianggou;
import com.huyu.entity.Temai;
import com.huyu.entity.Tuangou;
import com.huyu.service.CuxiaoService;
import com.huyu.service.GoodsService;
import com.huyu.service.Goods_typeService;
import com.huyu.service.Goods_type_typeService;
import com.huyu.service.MiaoshaService;
import com.huyu.service.QianggouService;
import com.huyu.service.TemaiService;
import com.huyu.service.TuangouService;

@Controller
@RequestMapping("/list")
public class ListController {
	
	private Goods_typeService goods_typeService;
	private GoodsService goodsService;
	@Resource
	private CuxiaoService cuxiaoService;
	@Resource
	private QianggouService qianggouService;
	@Resource
	private TemaiService temaiService;
	@Resource
	private TuangouService tuangouService;
	@Resource
	private MiaoshaService miaoshaService;
	
	public Goods_typeService getGoods_typeService() {
		return goods_typeService;
	}
	@Resource
	public void setGoods_typeService(Goods_typeService goods_typeService) {
		this.goods_typeService = goods_typeService;
	}
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	@Resource
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}
	@Resource
	private Goods_type_typeService type_typeService;
	/**
	 * 根据大类别查询商品列表
	 * @param typeId 大类别的ID
	 * @param sort 排序标识  0综合，表示不排序 1按最新的排序 2按便宜的排序 3按贵的排序
	 * @param request
	 * @return
	 */
	@RequestMapping("/showList")
	public String showList(String typeId, String sort, HttpServletRequest request){
		if(typeId == null || "".equals(typeId)){
			typeId = "0";
		}
		if(sort == null || "".equals(sort)){
			sort = "0";
		}
		int typeId_ = Integer.parseInt(typeId);
		int sort_ = Integer.parseInt(sort);
		if(sort_ > 3){
			sort_ = 0;
		}
		List<Goods_type> typeList = goods_typeService.list();
		List<Goods> goodsList = goodsService.listbytypeid(typeId_, sort_);
		request.setAttribute("typeId", typeId_);
		request.setAttribute("sort", sort_);
		request.setAttribute("typeList", typeList);
		request.setAttribute("goodsList", goodsList);
		return "list";
	}
	/**
	 * 返回goodsList，typeList
	 * @param goods_type_id
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/goodsTypeList")
	public String getNextGoodsType(Integer typeId,Integer sort,Integer goods_type_id,HttpServletRequest request){
		List<Goods_type> typeList = goods_typeService.list();
		List<Goods> goodslist=goodsService.listbytype_type_id(goods_type_id, sort);
		request.setAttribute("typeList", typeList);
		request.setAttribute("typeId", typeId);
		request.setAttribute("goodsList", goodslist);
		request.setAttribute("sort", sort);
		request.setAttribute("goodsTypeId", goods_type_id);
		return "list";
	}
	@RequestMapping("/goodsByTuiJian")
	public String goodsByTuiJian(Integer typeId,Integer sort,Integer goods_type_id,HttpServletRequest request){
		List<Goods_type> typeList = goods_typeService.list();
		List<Goods> goodslist=goodsService.goodsByTuiJian(goods_type_id, sort);
		request.setAttribute("typeList", typeList);
		request.setAttribute("typeId", typeId);
		request.setAttribute("goodsList", goodslist);
		request.setAttribute("sort", sort);
		request.setAttribute("goodsTypeId", goods_type_id);
		return "list";
	}
	
	@RequestMapping("/getGoodsByDiJia")
	public String getGoodsByDiJia(HttpServletRequest request){
		List<Cuxiao> list = cuxiaoService.list();
		request.setAttribute("cuxiao", list);
		return "cuxiaolist";
	}
	
	@RequestMapping("/getGoodsByQiangGou")
	public String getGoodsByQiangGou(HttpServletRequest request){
		List<Qianggou> list = qianggouService.listBytoday();
		request.setAttribute("list", list);
		return "qianggou";
	}
	
	@RequestMapping("/getGoodsByTeMai")
	public String getGoodsByTeMai(HttpServletRequest request){
		List<Temai> list = temaiService.listBytoday();
		request.setAttribute("list", list);
		return "temai";
	}
	
	@RequestMapping("/getGoodsByTuanGou")
	public String getGoodsByTuanGou(HttpServletRequest request){
		List<Tuangou> list = tuangouService.listBytoday();
		request.setAttribute("list", list);
		return "tuangou";
	}
	
	@RequestMapping("/getGoodsByMiaoSha")
	public String getGoodsByMiaoSha(HttpServletRequest request){
		List<Miaosha> list = miaoshaService.list();
		request.setAttribute("list", list);
		return "miaosha";
	}
	
}
