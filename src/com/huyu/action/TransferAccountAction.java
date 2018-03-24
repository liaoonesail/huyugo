package com.huyu.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.TransferAccounts;
import com.huyu.entity.User;
import com.huyu.service.Receipt_PaymentService;
import com.huyu.service.TransferAccountsService;
import com.huyu.service.UserService;
import com.huyu.util.OrderNum;

@Controller
@RequestMapping("/transfer")
public class TransferAccountAction {

	@Resource
	private TransferAccountsService service;
	@Resource
	private UserService userService;
	@Resource
	private Receipt_PaymentService rpService;
	
	/**
	 * 会员之间转账
	 * @param accounts 
	 * @param phone 转入方手机号码
	 * @param response 
	 * 	0不能给自己转账
	 *  1不存在的手机号码
	 *  2账户余额不足
	 *  3积分不足
	 *  4抵现金不足
	 *  5互余币不足
	 *  6转账成功
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/add")
	public String add(TransferAccounts accounts,String phone,HttpServletResponse response,HttpServletRequest request) throws IOException{
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");//转出方
		User user1=userService.getid(user.getId());
		User user2 = userService.getByPhone(phone);//转入方
		if(user2 != null){
			if(user1.getId() == user2.getId()){
				response.getWriter().print("0");//不能给自己转账
				return null;
			}
			int type = accounts.getType();
			int money = (int) accounts.getMoney();//此处前台传入的是int类型
			if(money<=0){
				return null;
			}
			//转账类型 （1余额，2积分，3抵现金，4互余币）
			switch (type) {
			case 1:
				if(money>user1.getAccount()){
					response.getWriter().print("2");//账户余额不足
					return null;
				}
				break;
			case 2:
				if(money>user1.getIntegral()){
					response.getWriter().print("3");//积分不足
					return null;
				}
				break;
			case 3:
				if(money>user1.getDixianjin()){
					response.getWriter().print("4");//抵现金不足
					return null;
				}
				break;
			case 4:
				if(money>user1.getHuyubi()){
					response.getWriter().print("5");//互余币不足
					return null;
				}
				break;
			default:
				break;
			}
			String time = OrderNum.getregTime();//时间
			//先扣除转出方的余额，再向转入方加余额
			switch (type) { //转账类型 （1余额，2积分，3抵现金，4互余币）
			case 1:
				user1.setAccount(user1.getAccount()-accounts.getMoney());//扣
				user2.setAccount(user2.getAccount()+accounts.getMoney());//加
				userService.update(user1);
				userService.update(user2);
				accounts.setRollOut(user1.getId());
				accounts.setShiftTo(user2.getId());
				accounts.setTime(time);
				accounts.setMoney((double)money);
				service.add(accounts);//添加转账记录
				//添加收支记录
				Receipt_Payment rp1 = new Receipt_Payment();
				Receipt_Payment rp2 = new Receipt_Payment();
				rp1.setPayment(accounts.getMoney());//设置支出
				rp1.setDate_time(time);//设置时间
				rp1.setUser_id(user1.getId());
				rp1.setType(type);
				rp1.setWay(11);
				rp2.setReceipt(accounts.getMoney());//设置收入
				rp2.setDate_time(time);//设置时间
				rp2.setUser_id(user2.getId());
				rp2.setType(type);
				rp2.setWay(11);
				rpService.add(rp1);
				rpService.add(rp2);
				session.setAttribute("user", user1);
				response.getWriter().print("6");//转账成功
				break;
			case 2:
				user1.setIntegral(user1.getIntegral()-(int)accounts.getMoney());
				user2.setIntegral(user2.getIntegral()+(int)accounts.getMoney());
				userService.update(user1);
				userService.update(user2);
				//添加收支记录
				Receipt_Payment rp11 = new Receipt_Payment();
				Receipt_Payment rp21 = new Receipt_Payment();
				rp11.setPayment(accounts.getMoney());//设置支出
				rp11.setDate_time(time);//设置时间
				rp11.setUser_id(user1.getId());
				rp11.setType(4);
				rp11.setWay(11);
				rp21.setReceipt(accounts.getMoney());//设置收入
				rp21.setDate_time(time);//设置时间
				rp21.setUser_id(user2.getId());
				rp21.setType(4);
				rp21.setWay(11);
				rpService.add(rp11);
				rpService.add(rp21);
				session.setAttribute("user", user1);
				accounts.setRollOut(user1.getId());
				accounts.setShiftTo(user2.getId());
				accounts.setMoney((double)money);
				accounts.setTime(time);
				service.add(accounts);
				response.getWriter().print("6");//转账成功
				break;
			case 3:
				user1.setDixianjin(user1.getDixianjin()-(int)accounts.getMoney());
				user2.setDixianjin(user2.getDixianjin()+(int)accounts.getMoney());
				userService.update(user1);
				userService.update(user2);
				//添加收支记录
				Receipt_Payment rp111 = new Receipt_Payment();
				Receipt_Payment rp211 = new Receipt_Payment();
				rp111.setPayment(accounts.getMoney());//设置支出
				rp111.setDate_time(time);//设置时间
				rp111.setUser_id(user1.getId());
				rp111.setType(type);
				rp111.setWay(11);
				rp211.setReceipt(accounts.getMoney());//设置收入
				rp211.setDate_time(time);//设置时间
				rp211.setUser_id(user2.getId());
				rp211.setType(type);
				rp211.setWay(11);
				rpService.add(rp111);
				rpService.add(rp211);
				session.setAttribute("user", user1);
				accounts.setRollOut(user1.getId());
				accounts.setShiftTo(user2.getId());
				accounts.setTime(time);
				accounts.setMoney((double)money);
				service.add(accounts);
				response.getWriter().print("6");//转账成功
				break;
			case 4:
				user1.setHuyubi(user1.getHuyubi()-accounts.getMoney());
				user2.setHuyubi(user2.getHuyubi()+accounts.getMoney());
				userService.update(user1);
				userService.update(user2);
				//添加收支记录
				Receipt_Payment rp1111 = new Receipt_Payment();
				Receipt_Payment rp2111 = new Receipt_Payment();
				rp1111.setPayment(accounts.getMoney());//设置支出
				rp1111.setDate_time(time);//设置时间
				rp1111.setUser_id(user1.getId());
				rp1111.setType(2);
				rp1111.setWay(11);
				rp2111.setReceipt(accounts.getMoney());//设置收入
				rp2111.setDate_time(time);//设置时间
				rp2111.setUser_id(user2.getId());
				rp2111.setType(2);
				rp2111.setWay(11);
				rpService.add(rp1111);
				rpService.add(rp2111);
				session.setAttribute("user", user1);
				accounts.setRollOut(user1.getId());
				accounts.setShiftTo(user2.getId());
				accounts.setTime(time);
				accounts.setMoney((double)money);
				service.add(accounts);
				response.getWriter().print("6");//转账成功
				break;
			default:
				break;
			}
		}else{
			response.getWriter().print("1");//不存在的手机号码
			return null;
		}
		return null;
	}
	
	@RequestMapping("/page")
	public String page(HttpServletRequest request){
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		User user1=userService.getid(user.getId());
		request.setAttribute("user", user1);
		return "memberCenter/right/transfer";
	}
	
}
