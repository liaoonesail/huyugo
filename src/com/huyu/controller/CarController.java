package com.huyu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.huyu.entity.*;
import com.huyu.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/car")
public class CarController {
	
	private CarService carService;
	private GoodsService goodsService;
	private TemaiService temaiService;
	private TuangouService tuangouService;
	private QianggouService qianggouService;
	private MiaoshaService miaoshaService;
	private CuxiaoService cuxiaoService;
	
	public CarService getCarService() {
		return carService;
	}
	@Resource
	public void setCarService(CarService carService) {
		this.carService = carService;
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
	@Resource
	private Vip_shopService vipShopService;
	@Resource
	private Integral_shopService integralShopService;
	@Resource
	private Ticket_shopService ticketShopService;
	/**
	 * 向购物车里添加商品
	 * @param goodsId
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/addCar")
	public String addCar(int goodsId, HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String result = "";
		String status = request.getParameter("status");
		String guige = request.getParameter("guige");
		if(user == null){
			result = "not login";
		}else{
			//先判断商品是否存在
			List<Car> carList = carService.list(user.getId());
			int i = 0;
			for(Car car : carList){
				if(goodsId == car.getGoods_id()){
					i++;
				}
			}
			Goods goods = goodsService.getid(goodsId);
			if(i == 0){
				Car car = new Car();
				car.setGoods_id(goodsId);
				car.setUser_id(user.getId());
				car.setAmount(1);
				car.setColor_norms(guige);
				if(status != null && !"".equals(status)){
					if(status.equals("temai")){
						Temai temai = temaiService.getgoods_id(goodsId);
						car.setReal_price(temai.getReal_price());
						car.setType(4);
					}else if(status.equals("tuangou")){
						Tuangou tuangou = tuangouService.getgoods_id(goodsId);
						car.setReal_price(tuangou.getReal_price());
						car.setType(5);
					}else if(status.equals("qianggou")){
						Qianggou qianggou = qianggouService.getgoods_id(goodsId);
						car.setReal_price(qianggou.getReal_price());
						car.setType(6);
					}else if(status.equals("miaosha")){
						Miaosha miaosha = miaoshaService.getgoods_id(goodsId);
						car.setReal_price(miaosha.getReal_price());
						car.setType(7);
					}else if(status.equals("cuxiao")){
						Cuxiao cuxiao = cuxiaoService.getgoods_id(goodsId);
						car.setReal_price(cuxiao.getReal_price());
						car.setType(8);
					}else if(status.equals("vipshop")){
						Vip_shop vipShop = vipShopService.getgoods_id(goodsId);
						System.out.println("vip价格:"+vipShop.getReal_price());
						car.setReal_price(vipShop.getReal_price());
						car.setIntegral(0);
						car.setType(2);
					}else if(status.equals("integral")){
						Integral_shop integralShop = integralShopService.getgoods_id(goodsId);
						car.setIntegral(integralShop.getIntegral());
						car.setReal_price(0);
						car.setType(1);
					}else if(status.equals("ticketshop")){
						Ticket_shop ticketShop = ticketShopService.getgoods_id(goodsId);
						car.setReal_price(0);
						car.setIntegral(0);
						car.setType(3);
					}
				}else{
					car.setReal_price(goods.getPrice());
				}
				System.out.println(car);
				carService.add(car);
				result = "ok";
			}else{
				result = "error";
			}
		}
		response.getWriter().print(result);
		return null;
	}
	
	/**
	 * 查询用户的购物车
	 * @param request
	 * @return
	 */
	@RequestMapping("/showCarByUser")
	public String showCarByUser(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			return "login";
		}
		List<Car> carList = carService.list(user.getId());
		List<Goods> goodsList = new ArrayList<Goods>();
		Goods goods = null;
		for(Car car : carList){
			goods = goodsService.getid(car.getGoods_id());
			goodsList.add(goods);
		}
		request.setAttribute("msg", "");
		request.setAttribute("carList", carList);
		request.setAttribute("goodsList", goodsList);
		return "carItem";
	}
	
	/**
	 * 删除购物车里的商品
	 * @param id
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/deleteCar")
	public String deleteCar(int id, HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print("not login");
			return null;
		}
		carService.del(id);
		response.getWriter().print("ok");
		return null;
	}
	
	/**
	 * 修改购物车里某个商品的数量
	 * @param id
	 * @param count
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/updateCar")
	public String updateCar(int id, int count, HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print("not login");
			return null;
		}
		carService.update(id, count);
		response.getWriter().print("ok");
		System.out.println("id : " + id + " ,count : " + count);
		return null;
	}
	
	/**
	 * 获取登陆用户的购物车商品数量
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getCarNum")
	public String getCarNum(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print(0);
			return null;
		}
		List<Car> carList = carService.list(user.getId());
		if(carList != null){
			response.getWriter().print(carList.size());
		}else{
			response.getWriter().print(0);
		}
		return null;
	}
}
