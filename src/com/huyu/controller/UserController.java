package com.huyu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huyu.entity.Getcash;
import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.User;
import com.huyu.entity.Userinfo;
import com.huyu.service.GetcashService;
import com.huyu.service.Receipt_PaymentService;
import com.huyu.service.UserService;
import com.huyu.service.UserinfoService;
import com.huyu.util.OrderNum;
import com.huyu.util.SmsUtil;
import com.huyu.util.Utils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private GetcashService cashService;
	@Resource
	private Receipt_PaymentService paymentService;
	@Resource
	private UserinfoService uiService;

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	@Resource
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 前台用户登陆
	 * 
	 * @param
	 * @param
	 * @return
	 */
	@RequestMapping("/login")
	public String login(String type, HttpServletRequest request) {
		if (type == null || "".equals(type)) {
			type = "1";
		}
		switch (Integer.parseInt(type)) {
		case 1:

			return "login";

		case 2: // 进入到个人中心页面
			String phone = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String verify = (String) request.getParameter("verify");
			if (phone == null || "".equals(phone) || password == null
					|| "".equals(password) || verify == null
					|| "".equals(verify)) {
				return "login";
			}
			User user = userService.getByPhone(phone);
			if (user == null || !Utils.MD5(password).equals(user.getPassword())) {
				request.setAttribute("errorMessage", "用户名或密码错误");
				return "login";
			}

			HttpSession session = request.getSession();

			String code = (String) session.getAttribute("captchaToken");
			if (code == null) {
				request.setAttribute("errorMessage", "验证码失效，请刷新重新注册");
				return "login";
			}
			if (!code.equals(verify)) {
				request.setAttribute("errorMessage", "验证码错误，请重新填写");
				return "login";
			}
			Userinfo ui = uiService.getByUser_id(user.getId());
			if(ui != null && ui.getNickname() != null && !"".equals(ui.getNickname())){
				user.setName(ui.getNickname());
				userService.update(user);
			}
			session.setAttribute("user", user);
			return "redirect:/index.do";
		default:
			return "login";
		}

	}

	/**
	 * 注册方法
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/reg")
	public String reg(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String type = (String) request.getParameter("type");
		String superId = request.getParameter("superId");
		if (type == null || "".equals(type)) {
			type = "1";
		}
		switch (Integer.parseInt(type)) {
		case 1:
			// 注册第一步，跳转到注册页面
			if (superId != null && Utils.isNumeric(superId)) {
				request.setAttribute("superId", superId);
			}
			return "reg";
		case 2:
			// 注册第二步，接受第一步注册页面的参数，返回到reg2.jsp
			String phone = (String) request.getParameter("phone");
			String userPassword = (String) request.getParameter("userpassword");
			String userPassword2 = (String) request
					.getParameter("userpassword2");
			String yqr = (String) request.getParameter("yqr");
			String verify = (String) request.getParameter("verify");
			if (superId != null) {
				request.setAttribute("superId", superId);
			}
			if (phone == null || "".equals(phone)) {
				return "reg";
			} else {
				if (!Utils.pipei(phone)) {
					request.setAttribute("phone_error", "电话格式不正确");
					return "reg";
				}
			}
			if (userPassword == null || "".equals(userPassword)) {
				return "reg";
			}
			if (userPassword2 == null || "".equals(userPassword2)) {
				return "reg";
			}
			if (verify == null || "".equals(verify)) {
				request.setAttribute("verify_error", "验证码不能为空");
				return "reg";
			}
			if (!userPassword.equals(userPassword2)) {
				request.setAttribute("password2_error", "两次密码不一致");
				System.out.println();
				return "reg";
			}
			String code = (String) session.getAttribute("captchaToken");

			if (code == null) {
				request.setAttribute("verify_error", "验证码失效，请刷新重新注册");
				return "reg";
			}
			if (!code.equals(verify)) {
				request.setAttribute("verify_error", "验证码错误，请重新填写");
				return "reg";
			}

			if (yqr != null && !"".equals(yqr)) {
				User userYqr = userService.getByPhone(yqr);
				if (userYqr == null) {
					request.setAttribute("yqr_error", "邀请人不存在");
					return "reg";
				} else {
					superId = userYqr.getId() + "";
				}
			}

			String password = Utils.MD5(userPassword);
			User user = userService.getByPhone(phone);
			if (user != null) {
				request.setAttribute("phone_error", "该手机号已注册");
				return "reg";
			}
			String phoneCode_ = Utils.getRondom() + "";
			String phoneCode = SmsUtil.reg(phoneCode_);

			boolean flag = SmsUtil.send(phone, phoneCode);
			if (flag) {
				session.setAttribute("phoneCode", phoneCode_);
			}

			user = new User();
			// String superId_ = (String)session.getAttribute("superId");
			if (superId != null && Utils.isNumeric(superId)
					&& superId.length() > 0) {
				user.setSuperior_id(Integer.parseInt(superId));
			}
			user.setPhone(phone);
			user.setPassword(password);
			session.setAttribute("user_", user);
			return "reg2";

		case 3: // 接受第二步输入的短信验证码
			String smsCode = request.getParameter("smsCode");
			String phoneCode2 = (String) session.getAttribute("phoneCode");
			if (!smsCode.equals(phoneCode2)) {
				request.setAttribute("smsCodeError", "验证码错误！");
				return "reg2";
			}

			User user_ = (User) session.getAttribute("user_");
			// String superIdstr = (String)session2.getAttribute("superId");
			// if(superIdstr != null){
			// user_.setSuperior_id(Integer.parseInt(superIdstr));
			// }
			userService.reg(user_);
			User newUser = userService.getByPhone(user_.getPhone());
			session.setAttribute("user", newUser);
			return "reg3";
		default:
			return "reg";
		}
	}

	/**
	 * 忘记密码
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping("/forgetPsd")
	public String forgetPsd(String type, HttpServletRequest request) {
		if (type == null || "".equals(type) || !Utils.isNumeric(type)) {
			type = "1";
		}
		switch (Integer.parseInt(type)) {
		case 1:

			return "forgotPassword";

		case 2:// 提交电话号码
			String phone = (String) request.getParameter("phone");
			String verify = (String) request.getParameter("txtRegSN");
			if (phone == null || "".equals(phone) || verify == null
					|| "".equals(verify)) {
				request.setAttribute("errorMessage", "电话号码或验证码有误");
				return "forgotPassword";
			}
			User user = userService.getByPhone(phone);
			if (user == null) {
				request.setAttribute("errorMessage", "该手机号尚未注册");
				return "forgotPassword";
			}
			HttpSession session = request.getSession();
			String code = (String) session.getAttribute("captchaToken");

			if (code == null) {
				request.setAttribute("errorMessage", "验证码失效，请刷新重新注册");
				return "forgotPassword";
			}
			if (!code.equals(verify)) {
				request.setAttribute("errorMessage", "验证码错误，请重新填写");
				return "forgotPassword";
			}
			// 发送验证码
			String phoneCode = Utils.getRondom() + "";
			String phoneCode2 = SmsUtil.reg(phoneCode);
			boolean flag = SmsUtil.send(phone, phoneCode2);
			if (flag) {
				session.setAttribute("phoneCode", phoneCode);
				session.setAttribute("phone", phone);
			}
			return "forgotPsd2";
		case 3:
			String smsCode = (String) request.getParameter("checkcode");
			HttpSession session2 = request.getSession();
			String phoneCode3 = (String) session2.getAttribute("phoneCode");
			if (!smsCode.equals(phoneCode3)) {
				request.setAttribute("errorMessage", "短信验证码出错");
				return "forgotPsd2";
			}
			String phone2 = (String) session2.getAttribute("phone");
			User user2 = userService.getByPhone(phone2);
			user2.setPassword(Utils.MD5("888888"));
			userService.update(user2);
			request.setAttribute("errorMessage", "找回密码成功，已被重置为888888");
			return "forgotPsd2";
		default:
			return "forgotPassword";
		}
	}

	/**
	 * 异步返回用户对象
	 * 
	 * @param
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getUserById")
	public String getUserById(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		User user = null;
		String userId = (String) request.getParameter("userId");
		if (userId != null && !"".equals(userId) && Utils.isNumeric(userId)) {
			user = userService.getid(Integer.parseInt(userId));
		}
		if (user == null) {
			HttpSession session = request.getSession();
			user = (User) session.getAttribute("user");
			if (user == null) {
				response.getWriter().print("notLogin");
				return null;
			}
		}
		JSONObject json = JSONObject.fromObject(user);
		response.getWriter().print(json);
		return null;
	}

	/**
	 * 用户退出登陆
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginOut")
	public String loginOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		return "redirect:/user/login.do";
	}

	/**
	 * 重新发送验证码
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/reSendCode")
	public String reSendCode(String phone, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String phoneCode_ = Utils.getRondom() + "";
		String phoneCode = SmsUtil.reg(phoneCode_);
		HttpSession session = request.getSession();
		boolean flag = SmsUtil.send(phone, phoneCode);
		if (flag) {
			session.setAttribute("phoneCode", phoneCode_);
		}
		response.getWriter().print(true);
		return null;
	}

	/**
	 * 提现申请
	 * 
	 * @param getcash
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/add")
	public String add(Getcash getcash, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		getcash.setUserId(user.getId());
		User getid = userService.getid(getcash.getUserId());
		if (getcash.getPrice() >= 100
				&& getcash.getPrice() <= getid.getAccount()) {
			getcash.setTime(OrderNum.getSystemTime());
			cashService.add(getcash);
			getid.setAccount(getid.getAccount() - getcash.getPrice());
			userService.update(getid);
			session.setAttribute("user", getid);
			response.getWriter().print(true);
		} else {
			response.getWriter().print(false);
		}
		return null;
	}

	/**
	 * 根据提现状态获取用户提现申请
	 * 
	 * @param status
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/cashList")
	public String cashList(String status, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", user.getId());
		map.put("status", status);
		List<Getcash> list = cashService.cashList(map);
		JSONArray json = JSONArray.fromObject(list);
		response.getWriter().print(json);
		return null;
	}

	/**
	 * 
	 * @param money
	 *            兑换金额(互余币、抵现金、积分)互余币和积分必须是1的倍数，抵现金必须是5的倍数，且都不能带小数点！！！
	 * @param label
	 *            （1，2，3） 1：互余币兑换积分：1个互余币兑换20个积分 2：抵现金兑换积分：5个抵现金兑换1个积分
	 *            3：积分兑换抵现金：1个积分兑换5个抵现金
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/exchange")
	public String exchange(Integer money, Integer label,
			HttpServletResponse response, HttpServletRequest request)
			throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			if (label == -1 && money == -1) {
				return "memberCenter/right/exchange";
			}
			if (label == 1) {
				if (money <= user.getHuyubi()&&money>0) {
					user.setHuyubi(user.getHuyubi() - money);
					paymentService.add(new Receipt_Payment(0, money, OrderNum
							.getregTime(), user.getId(), 2, 10,0));
					user.setIntegral(user.getIntegral() + money * 20);
					paymentService.add(new Receipt_Payment(money * 20, 0,
							OrderNum.getregTime(), user.getId(), 4, 10,0));
				} else {
					response.getWriter().print("互余币不足");
					return null;
				}
			} else if (label == 2) {
				if (money <= user.getDixianjin()&&money>0) {
					user.setDixianjin(user.getDixianjin() - money);
					paymentService.add(new Receipt_Payment(0, money, OrderNum
							.getregTime(), user.getId(), 3, 10,0));
					user.setIntegral(user.getIntegral() + money / 5);
					paymentService.add(new Receipt_Payment(money / 5, 0,
							OrderNum.getregTime(), user.getId(), 4, 10,0));
				} else {
					response.getWriter().print("抵现金不足");
					return null;
				}
			} else if (label == 3) {
				if (money <= user.getIntegral()&&money>0) {
					user.setIntegral(user.getIntegral() - money);
					paymentService.add(new Receipt_Payment(0, money, OrderNum
							.getregTime(), user.getId(), 4, 10,0));
					user.setDixianjin(user.getDixianjin() + money * 5);
					paymentService.add(new Receipt_Payment(money * 5, 0,
							OrderNum.getregTime(), user.getId(), 3, 10,0));
				} else {
					response.getWriter().print("积分不足");
					return null;
				}
			}
			int update = userService.update(user);
			if (update > 0) {
				session.setAttribute("user", user);
				response.getWriter().print("已完成兑换");
			} else {
				response.getWriter().print("系统维护中,兑换失败");
			}
			return null;
		} else {
			return "login";
		}

	}

	/**
	 * 根据用户id获取提现记录
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getCashByUserId")
	public String getCashByUserId(String curpage, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Map<String, Object> map = cashService
				.getByUserId(user.getId(), curpage);
		JSONArray json = JSONArray.fromObject(map);
		response.getWriter().print(json);
		return null;
	}

	/**
	 * 用户删除提现记录
	 * 
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 * @RequestMapping("/delCash") public String delCash(Integer
	 *                             id,HttpServletResponse
	 *                             response,HttpServletRequest request) throws
	 *                             IOException{ Getcash getid =
	 *                             cashService.getid(id); if (getid != null) {
	 *                             cashService.del(id);
	 *                             response.getWriter().print(true); }else{
	 *                             response.getWriter().print(false); } return
	 *                             null; }
	 */

	/**
	 * 用户撤销提现
	 * 
	 * @param id
	 * @param response
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/cexiao")
	public String cexiao(Integer id,Integer userid, HttpServletResponse response,
			HttpServletRequest request) throws IOException {
		Getcash cash = cashService.getid(id);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (cash != null) {
			if(user!=null){
				double price = user.getAccount() + cash.getPrice();
				cashService.del(id);
				user.setAccount(price);
				userService.update(user);
				response.getWriter().print(true);
			}else{
				User user1 = userService.getid(userid);
				double price = user1.getAccount() + cash.getPrice();
				cashService.del(id);
				user1.setAccount(price);
				userService.update(user1);
				response.getWriter().print(true);
			}

		} else {
			response.getWriter().print(false);
		}
		return null;
	}

}
