package com.huyu.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Egg;
import com.huyu.entity.Prize;
import com.huyu.entity.PrizeInkind;
import com.huyu.entity.Prize_record;
import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.User;
import com.huyu.entity.Userinfo;
import com.huyu.po.Choujiang;
import com.huyu.service.EggService;
import com.huyu.service.PrizeInkindService;
import com.huyu.service.PrizeService;
import com.huyu.service.Prize_recordService;
import com.huyu.service.Receipt_PaymentService;
import com.huyu.service.UserService;
import com.huyu.service.UserinfoService;
import com.huyu.util.OrderNum;
import com.huyu.util.ServiceUtil;

@Controller
@RequestMapping("/luck")
public class LuckController {

	private PrizeService prizeService;
	private Prize_recordService prize_recordService;
	private Receipt_PaymentService receipt_PaymentService;
	private UserService userService;
	private PrizeInkindService pkService;
	@Resource
	private EggService eggService;
	@Resource
	private UserinfoService uiService;

	public PrizeService getPrizeService() {
		return prizeService;
	}
	@Resource
	public void setPrizeService(PrizeService prizeService) {
		this.prizeService = prizeService;
	}
	
	public Prize_recordService getPrize_recordService() {
		return prize_recordService;
	}
	@Resource
	public void setPrize_recordService(Prize_recordService prize_recordService) {
		this.prize_recordService = prize_recordService;
	}
	
	public Receipt_PaymentService getReceipt_PaymentService() {
		return receipt_PaymentService;
	}
	@Resource
	public void setReceipt_PaymentService(
			Receipt_PaymentService receipt_PaymentService) {
		this.receipt_PaymentService = receipt_PaymentService;
	}
	
	public UserService getUserService() {
		return userService;
	}
	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public PrizeInkindService getPkService() {
		return pkService;
	}
	@Resource
	public void setPkService(PrizeInkindService pkService) {
		this.pkService = pkService;
	}
	/**
	 * 跳转抽奖界面
	 * @param request
	 * @return
	 */
	@RequestMapping("/showLuck")
	public String showLuck(HttpServletRequest request){
		List<Prize_record> prizeList = prize_recordService.listBynew("1");
		request.setAttribute("prizeList", prizeList);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null){
			User user2 = userService.getid(user.getId());
			session.setAttribute("user", user2);
			Userinfo ui = uiService.getByUser_id(user2.getId());
			request.setAttribute("ui", ui);
		}
		return "luckGoods";
	}
	
	/**
	 * 跳转到砸金蛋界面
	 * @return
	 */
	@RequestMapping("/showZadan")
	public String showZadan(HttpServletRequest request){
		List<Prize_record> prizeList = prize_recordService.listBynew("2");
		request.setAttribute("prizeList", prizeList);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user != null){
			User user2 = userService.getid(user.getId());
			session.setAttribute("user", user2);
			Userinfo ui = uiService.getByUser_id(user2.getId());
			request.setAttribute("ui", ui);
		}
		return "luckGoods1";
	}
	
	/**
	 * 抽奖方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/choujiang")
	public String choujiang(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//String result = "";
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print("notLogin");
			return null;
		}else{
			if(user.getIntegral() < 2){
				response.getWriter().print("notfullInteger");
				return null;
			}
			Prize prize = prizeService.getprize();
			double prize_1 = prize.getPrize_1();
			double prize_2 = prize.getPrize_2();
			double prize_3 = prize.getPrize_3();
			double prize_4 = prize.getPrize_4();
			double prize_5 = prize.getPrize_5();
			//double prize_6 = prize.getPrize_6();
			int num = (int)prize_1;
			int rom = (int)(Math.random()*1000);
			int data = 0;
			if(0 < rom && rom <= num){
			 	data=1;
			}else if(num < rom && rom <= (num = (int)(num + prize_2))){
			 	data=2;
			}else if(num < rom && rom <= (num = (int)(num + prize_3))){
			 	data=3;
			}else if(num < rom && rom <= (num = (int)(num + prize_4))){
			 	data=4;
			}else if(num < rom && rom <= (num = (int)(num + prize_5))){
			 	data=5;
			}else{
			 	data=6;
			}
			PrizeInkind pk = pkService.getOne();
			Choujiang cj = (Choujiang)ServiceUtil.getChoujiang(data,pk);
			if(data < 6){
				//添加抽奖记录
				Prize_record prize_record = new Prize_record();
				prize_record.setContent(cj.getJiangDetails());//
				prize_record.setPrize(""+data);//奖项 1 一等奖  2 二等奖 3 三等奖 4 四等奖 5 五等奖 6 六等奖
				prize_record.setType("1");//奖项类别 1 转盘 2 砸金蛋
				prize_record.setUser_id(user.getId());
				prize_recordService.add(prize_record);
				
				//添加得到奖品的资金明细
				Receipt_Payment payment2 = new Receipt_Payment();
				payment2.setDate_time(OrderNum.getregTime());
				payment2.setPayment(0);//支出
				payment2.setReceipt(cj.getJiangNum());//获得
				/*if(cj.getJiangType() == 1){
					payment2.setType(4);//模块 1余额 2互余币 3抵现金 4积分 5第三方支付
					user.setIntegral(user.getIntegral() + cj.getJiangNum());
				}else if(cj.getJiangType() == 2){
					payment2.setType(2);
					user.setHuyubi(user.getHuyubi() + cj.getJiangNum());
				}*/
				payment2.setType(1);//添加到余额中
				user.setAccount(user.getAccount()+cj.getJiangNum());
				payment2.setUser_id(user.getId());
				payment2.setWay(5);//渠道 1会员开通 2商品购买 3下级分利 4抽奖花费 5抽奖获得 6提现 7签到 8分享9完善资料
				receipt_PaymentService.add(payment2);
				
			}
			//添加扣除2积分的资金明细
			Receipt_Payment payment = new Receipt_Payment();
			payment.setDate_time(OrderNum.getregTime());
			payment.setPayment(2);//支出
			payment.setReceipt(0);
			payment.setType(4);//模块 1余额 2互余币 3抵现金 4积分 5第三方支付
			payment.setUser_id(user.getId());
			payment.setWay(4);//渠道 1会员开通 2商品购买 3下级分利 4抽奖花费 5抽奖获得 6提现 7签到 8分享9完善资料
			receipt_PaymentService.add(payment);
			
			//扣除用户2积分
			user.setIntegral(user.getIntegral() - 2);
			userService.update(user);
			session.setAttribute("user", user);
			JSONObject json = JSONObject.fromObject(cj);
			response.getWriter().print(json);
			return null;
		}
	}
	
	/**
	 * 抽奖方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	@RequestMapping("/choujiang")
	public String choujiang(HttpServletRequest request, HttpServletResponse response) throws IOException{
		//String result = "";
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print("notLogin");
			return null;
		}else{
			if(user.getIntegral() < 20){
				response.getWriter().print("notfullInteger");
				return null;
			}
			Prize prize = prizeService.getprize();
			double prize_1 = prize.getPrize_1();
			double prize_2 = prize.getPrize_2();
			double prize_3 = prize.getPrize_3();
			double prize_4 = prize.getPrize_4();
			double prize_5 = prize.getPrize_5();
			//double prize_6 = prize.getPrize_6();
			int num = (int)prize_1;
			int rom = (int)(Math.random()*1000);
			int data = 0;
			if(0 < rom && rom <= num){
			 	data=1;
			}else if(num < rom && rom <= (num = (int)(num + prize_2))){
			 	data=2;
			}else if(num < rom && rom <= (num = (int)(num + prize_3))){
			 	data=3;
			}else if(num < rom && rom <= (num = (int)(num + prize_4))){
			 	data=4;
			}else if(num < rom && rom <= (num = (int)(num + prize_5))){
			 	data=5;
			}else{
			 	data=6;
			}
			Choujiang cj = (Choujiang)ServiceUtil.getChoujiang(data);
			if(data < 6){
				//添加抽奖记录
				Prize_record prize_record = new Prize_record();
				prize_record.setContent(cj.getJiangDetails());
				prize_record.setPrize(""+data);
				prize_record.setType("1");
				prize_record.setUser_id(user.getId());
				prize_recordService.add(prize_record);
				
				//添加得到奖品的资金明细
				Receipt_Payment payment2 = new Receipt_Payment();
				payment2.setDate_time(OrderNum.getregTime());
				payment2.setPayment(0);
				payment2.setReceipt(cj.getJiangNum());
				if(cj.getJiangType() == 1){
					payment2.setType(4);
					user.setIntegral(user.getIntegral() + cj.getJiangNum());
				}else if(cj.getJiangType() == 2){
					payment2.setType(2);
					user.setHuyubi(user.getHuyubi() + cj.getJiangNum());
				}
				payment2.setUser_id(user.getId());
				payment2.setWay(5);
				receipt_PaymentService.add(payment2);
				
			}
			//添加扣除20积分的资金明细
			Receipt_Payment payment = new Receipt_Payment();
			payment.setDate_time(OrderNum.getregTime());
			payment.setPayment(20);
			payment.setReceipt(0);
			payment.setType(4);
			payment.setUser_id(user.getId());
			payment.setWay(4);
			receipt_PaymentService.add(payment);
			
			//扣除用户积分
			user.setIntegral(user.getIntegral() - 20);
			userService.update(user);
			session.setAttribute("user", user);
			JSONObject json = JSONObject.fromObject(cj);
			response.getWriter().print(json);
			return null;
		}
	}*/
	
	@RequestMapping("/zadan")
	public String zadan(HttpServletRequest request,HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print("notLogin");
			return null;
		}else{
			if(user.getIntegral() < 10){
				response.getWriter().print("notfullInteger");
				return null;
			}
			int rom = (int)(Math.random()*100);
			Egg one = eggService.getOne();
			int data = one.getZjl();
			int type = 0;//中奖项
			int i = 0;//是否中将标识
			if(rom <= data){
			 	i=1;
			}
			if(i == 1){
				int prize = 0;//奖项
				switch (rom%3) {
				case 0:
					type = 1;
					prize = eggService.getOne().getPrize1();
					break;
				case 1:
					type = 2;
					prize = eggService.getOne().getPrize2();
					break;
				case 2:
					type = 3;
					prize = eggService.getOne().getPrize3();
					break;
				default:
					type = 0;
					prize = 0;
					break;
				}
				
				//为用户充值砸蛋砸到的红包
				user.setIntegral(user.getIntegral()+prize);
				userService.update(user);
				
				//添加砸蛋记录
				Prize_record prize_record = new Prize_record();
				prize_record.setContent("获得"+prize+"积分");
				prize_record.setPrize(""+type);
				prize_record.setType("2");
				prize_record.setUser_id(user.getId());
				prize_recordService.add(prize_record);
				
				//添加得到奖品的资金明细
				Receipt_Payment payment2 = new Receipt_Payment();
				payment2.setDate_time(OrderNum.getregTime());
				payment2.setPayment(0);
				payment2.setReceipt(prize);//添加获得金额
				payment2.setType(4);//积分
				//user.setAccount(user.getAccount() + 30);
				//user.setIntegral(user.getIntegral()+prize);
				payment2.setUser_id(user.getId());
				payment2.setWay(5);
				receipt_PaymentService.add(payment2);
				
			}
			//添加用户扣除10积分的资金明细
			Receipt_Payment payment = new Receipt_Payment();
			payment.setDate_time(OrderNum.getregTime());
			payment.setPayment(2);
			payment.setReceipt(0);
			payment.setType(4);
			payment.setUser_id(user.getId());
			payment.setWay(4);
			receipt_PaymentService.add(payment);
			//扣除用户积分
			user.setIntegral(user.getIntegral() - 2);
			userService.update(user);
			session.setAttribute("user", user);
			response.getWriter().print(type);//中奖等级  0未中奖  1 2 3
			return null;
		}
	}
	
	/**
	 * 砸蛋方法
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	/*@RequestMapping("/zadan")
	public String zadan(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user == null){
			response.getWriter().print("notLogin");
			return null;
		}else{
			if(user.getIntegral() < 10){
				response.getWriter().print("notfullInteger");
				return null;
			}
			int rom = (int)(Math.random()*100);
			int data = ServiceUtil.ZDGL;
			int i = 0;
			if(rom <= data){
			 	i=1;
			}
			if(i == 1){
				//添加砸蛋记录
				Prize_record prize_record = new Prize_record();
				prize_record.setContent("获得30抵现金");
				prize_record.setPrize(""+i);
				prize_record.setType("2");
				prize_record.setUser_id(user.getId());
				prize_recordService.add(prize_record);
				
				//添加得到奖品的资金明细
				Receipt_Payment payment2 = new Receipt_Payment();
				payment2.setDate_time(OrderNum.getregTime());
				payment2.setPayment(0);
				payment2.setReceipt(30);
				payment2.setType(3);
				user.setDixianjin(user.getDixianjin() + 30);
				payment2.setUser_id(user.getId());
				payment2.setWay(5);
				receipt_PaymentService.add(payment2);
				
			}
			//添加用户扣除10积分的资金明细
			Receipt_Payment payment = new Receipt_Payment();
			payment.setDate_time(OrderNum.getregTime());
			payment.setPayment(10);
			payment.setReceipt(0);
			payment.setType(4);
			payment.setUser_id(user.getId());
			payment.setWay(4);
			receipt_PaymentService.add(payment);
			//扣除用户积分
			user.setIntegral(user.getIntegral() - 10);
			userService.update(user);
			session.setAttribute("user", user);
			response.getWriter().print(i);
			return null;
		}
	}*/
}
