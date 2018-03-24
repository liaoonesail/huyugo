package com.huyu.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huyu.entity.*;
import com.huyu.service.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.util.ReadWriteTxt;
import com.huyu.util.Utils;

@Controller
@RequestMapping("/goods")
public class GoodsController {
	
	private GoodsService goodsService;
	private PictureService pictureService;
	private Goods_typeService goods_typeService;
	private Goods_type_typeService goods_type_typeService;
	private CarService carService;
	private TemaiService temaiService;
	private TuangouService tuangouService;
	private QianggouService qianggouService;
	private MiaoshaService miaoshaService;
	private CuxiaoService cuxiaoService;
	
	public GoodsService getGoodsService() {
		return goodsService;
	}
	@Resource
	public void setGoodsService(GoodsService goodsService) {
		this.goodsService = goodsService;
	}

	public PictureService getPictureService() {
		return pictureService;
	}
	@Resource
	public void setPictureService(PictureService pictureService) {
		this.pictureService = pictureService;
	}
	
	public Goods_typeService getGoods_typeService() {
		return goods_typeService;
	}
	@Resource
	public void setGoods_typeService(Goods_typeService goods_typeService) {
		this.goods_typeService = goods_typeService;
	}
	
	public Goods_type_typeService getGoods_type_typeService() {
		return goods_type_typeService;
	}
	@Resource
	public void setGoods_type_typeService(
			Goods_type_typeService goods_type_typeService) {
		this.goods_type_typeService = goods_type_typeService;
	}
	
	public CarService getCarService() {
		return carService;
	}
	@Resource
	public void setCarService(CarService carService) {
		this.carService = carService;
	}
	
	public TemaiService getTemaiService() {
		return temaiService;
	}
	@Resource
	public void setTemaiService(TemaiService temaiService) {
		this.temaiService = temaiService;
	}
	
	public TuangouService getTuangouService() {
		return tuangouService;
	}
	@Resource
	public void setTuangouService(TuangouService tuangouService) {
		this.tuangouService = tuangouService;
	}
	
	public QianggouService getQianggouService() {
		return qianggouService;
	}
	@Resource
	public void setQianggouService(QianggouService qianggouService) {
		this.qianggouService = qianggouService;
	}
	
	public MiaoshaService getMiaoshaService() {
		return miaoshaService;
	}
	@Resource
	public void setMiaoshaService(MiaoshaService miaoshaService) {
		this.miaoshaService = miaoshaService;
	}
	
	public CuxiaoService getCuxiaoService() {
		return cuxiaoService;
	}
	@Resource
	public void setCuxiaoService(CuxiaoService cuxiaoService) {
		this.cuxiaoService = cuxiaoService;
	}
	@Resource
	private Integral_shopService integralShopService;
	@Resource
	private Vip_shopService vipShopService;
	@Resource
	private Ticket_shopService ticketShopService;
	/**
	 * 根据ID显示单个
	 * @param
	 * @param request
	 * @return
	 */
	@RequestMapping("/showGoodsById")
	public String showGoodsById(HttpServletRequest request){
		String idstr = (String)request.getParameter("id");
		if(idstr == null || "".equals(idstr) || !Utils.isNumeric(idstr) || Integer.parseInt(idstr) < 1){
			return "redirect:index.do";
		}
		int id = Integer.parseInt(idstr);
		Goods goods = goodsService.getid(id);
		System.out.println("活动商品id："+goods.getId());
		String read=ReadWriteTxt.read(goods.getDetails(),request);
		goods.setDetails(read);
		List<Picture> picList = pictureService.list(id);
		Goods_type_type goodsTypeType = goods_type_typeService.getid(goods.getGoods_type_type_id());
		Goods_type goodsType = goods_typeService.getid(goodsTypeType.getGoods_type_id());
		//推荐商品列表
		List<Goods> recommendList = goodsService.listByrecommend();
		//小类别商品
		List<Goods> typeGoodsList = goodsService.listByid(goodsTypeType.getId());
		
		//查询该商品是否在活动中
		//活动类型
		String hdlx = (String)request.getParameter("status");
		if(hdlx != null && !"".equals(hdlx)){
			if(hdlx.equals("temai")){
				request.setAttribute("status", "temai");
				Temai temai = temaiService.getgoods_id(goods.getId());
				request.setAttribute("temai", temai);
			}else if(hdlx.equals("tuangou")){
				request.setAttribute("status", "tuangou");
				Tuangou tuangou = tuangouService.getgoods_id(goods.getId());
				request.setAttribute("tuangou", tuangou);
			}else if(hdlx.equals("qianggou")){
				request.setAttribute("status", "qianggou");
				int goodsId = goods.getId();
				Qianggou qianggou = qianggouService.getgoods_id(goodsId);
				System.out.println(qianggou);
				request.setAttribute("qianggou", qianggou);
			}else if(hdlx.equals("miaosha")){
				request.setAttribute("status", "miaosha");
				Miaosha miaosha = miaoshaService.getgoods_id(goods.getId());
				request.setAttribute("miaosha", miaosha);
			}else if(hdlx.equals("cuxiao")){
				request.setAttribute("status", "cuxiao");
				Cuxiao cuxiao = cuxiaoService.getgoods_id(goods.getId());
				request.setAttribute("cuxiao", cuxiao);
			}
			request.setAttribute("activity", "温馨提示：本次活动商品，每人限购一件！");
			 if(hdlx.equals("integral")){
				request.setAttribute("status", "integral");
				Integral_shop integral = integralShopService.getgoods_id(goods.getId());
				request.setAttribute("integral", integral);
				 request.setAttribute("activity", "");
			}else if(hdlx.equals("vipshop")){
				request.setAttribute("status", "vipshop");
				Vip_shop vipshop = vipShopService.getgoods_id(goods.getId());
				request.setAttribute("vipshop", vipshop);
				 request.setAttribute("activity", "");
			}else if(hdlx.equals("ticketshop")){
				request.setAttribute("status", "ticketshop");
				Ticket_shop ticketshop = ticketShopService.getgoods_id(goods.getId());
				request.setAttribute("ticketshop", ticketshop);
				 request.setAttribute("activity", "");
			}else if(hdlx.equals("shop")){
				 request.setAttribute("activity", "");
			 }
		}
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user != null){
			List<Car> carList = carService.list(user.getId());
			request.setAttribute("carNum", carList.size()+"");
		}
		request.setAttribute("goods", goods);
		request.setAttribute("picList", picList);
		request.setAttribute("goodsTypeType", goodsTypeType);
		request.setAttribute("goodsType", goodsType);
		request.setAttribute("recommendList", recommendList);
		request.setAttribute("typeGoodsList", typeGoodsList);
		return "details";
	}
	
	/**
	 * 异步返回单个商品
	 * @param id
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getGoodsById")
	public String getGoodsById(int id, HttpServletResponse response) throws IOException{
		Goods goods = goodsService.getid(id);
		JSONObject json = JSONObject.fromObject(goods);
		response.getWriter().print(json);
		return null;
	}
	
	/**
	 * 根据下级类别ID异步返回商品列表
	 * @param type_type_id
	 * @param
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getGoodsNextTypeId")
	public String getGoodsNextTypeId(Integer type_type_id,Integer sort,HttpServletResponse response) throws IOException{
		List<Goods> goodsList=goodsService.listbytype_type_id(type_type_id,sort);
		if(goodsList != null && goodsList.size() > 0){
			JSONArray json = JSONArray.fromObject(goodsList);
			response.getWriter().print(json);
		}else{
			response.getWriter().print("error");
		}
		return null;
	}
	
	/**
	 * 搜索商品
	 * @param request
	 * @return
	 */
	@RequestMapping("/showGoodsBySerch")
	public String showGoodsBySerch(String names, HttpServletRequest request){
		//String names = (String)request.getParameter("names");
		try {
			names=URLDecoder.decode(names, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		List<Goods> goodsList = goodsService.listByname(names);
		request.setAttribute("goodsList", goodsList);
		return "search";
	}

}
