package com.huyu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Cuxiao;
import com.huyu.entity.Miaosha;
import com.huyu.entity.Picture;
import com.huyu.entity.Qianggou;
import com.huyu.entity.Temai;
import com.huyu.entity.Tuangou;
import com.huyu.entity.Tuangou_Details;
import com.huyu.service.CuxiaoService;
import com.huyu.service.GoodsService;
import com.huyu.service.MiaoshaService;
import com.huyu.service.PictureService;
import com.huyu.service.QianggouService;
import com.huyu.service.TemaiService;
import com.huyu.service.TuangouService;
import com.huyu.service.Tuangou_DetailsService;

/**
 * 活动商城商品表
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/activity")
public class ActivityShopController {
	
	private PictureService pictureService;
	private GoodsService goodsService;
	private TemaiService temaiService;
	private TuangouService tuangouService;
	private QianggouService qianggouService;
	private MiaoshaService miaoshaService;
	private CuxiaoService cuxiaoService;
	private Tuangou_DetailsService tuangou_DetailsService;

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
	
	public Tuangou_DetailsService getTuangou_DetailsService() {
		return tuangou_DetailsService;
	}
	@Resource
	public void setTuangou_DetailsService(
			Tuangou_DetailsService tuangou_DetailsService) {
		this.tuangou_DetailsService = tuangou_DetailsService;
	}
	
	/**
	 * 活动商城显示入口
	 * @param request
	 * @return
	 */
	@RequestMapping("/showActivity")
	public String showActivity(HttpServletRequest request){
		//获取banner图片
		//List<Picture> bannerList = pictureService.list(0);
		
		List<Temai> temaiList = temaiService.listBytoday();
		List<Tuangou> tuangouList = tuangouService.listBytoday();
		List<Qianggou> qianggouList = qianggouService.listBytoday();
		List<Miaosha> miaoshaList = miaoshaService.list();
		List<Cuxiao> cuxiaoList = cuxiaoService.list();
		
		//System.out.println((Miaosha)miaoshaList.get(9));
		
		//request.setAttribute("bannerList", bannerList);
		//request.setAttribute("index_", "ok");
		request.setAttribute("temaiList", temaiList);
		request.setAttribute("tuangouList", tuangouList);
		request.setAttribute("qianggouList", qianggouList);
		request.setAttribute("miaoshaList", miaoshaList);
		request.setAttribute("cuxiaoList", cuxiaoList);
		return "activityShop";
	}
	
	/**
	 * 根据团购ID获取已购买个数
	 * @param tuangouId
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/showTuangouNum")
	public String showTuangouNum(int tuangouId, HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<Tuangou_Details> detailsList = tuangou_DetailsService.listBytuangou_id(tuangouId);
		int i = 0;
		if(detailsList != null){
			i = detailsList.size();
		}
		response.getWriter().print(i);
		return null;
	}

}
