package com.huyu.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.AopProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.UserDao;
import com.huyu.entity.Member;
import com.huyu.entity.Pension;
import com.huyu.entity.Receipt_Payment;
import com.huyu.entity.User;
import com.huyu.util.OrderNum;
import com.huyu.util.page;
@Service("userService")
@Transactional
public class UserService{
@Resource
private UserDao dao;
@Resource
private Receipt_PaymentService service;

	@Resource
	private MemberSystem memberSystem;
	/**
	 * 
	 * @param user 用户注册
	 */
	public void reg(User user) {
		// TODO Auto-generated method stub
		user.setReg_time(OrderNum.getregTime());
		dao.reg(user);
	}
	/**
	 * 
	 * @param name 用户使用用户名登录
	 * @return 返回用户输入的用户名的对象
	 */
	public User getByName(String name) {
		// TODO Auto-generated method stub
		return dao.getByName(name);
	}
	/**
	 * @param phone 根据电话号码获取用户对象
	 * @return
	 */
	public User getByPhone(String phone){
		return dao.getByPhone(phone);
	}
	/**
	 * 
	 * @param //userid 充值会员成功调用，改变用户会员member的状态0=》1和级联向上修改获利金额
	 * @return
	 */
	public int regmember(Integer id,double memberprice) {
		// TODO Auto-generated method stub
		User user=getid(id);//用户
		if(user.getSuperior_id()!=0){
			memberSystem.bemember(user);//级联向上修改vip等级
			memberSystem.getprofit(user,memberprice);//vip获利 
			
			Receipt_Payment rp1=new Receipt_Payment(5, 0, OrderNum.getregTime(), user.getId(), 6, 1,0);//开通会员的养老金
			service.add(rp1);//添加收支明细
			user.setPension(user.getPension()+5);//充值用户开通会员的养老金部分
			//设置用户的养老金开始时间
			if("".equals(user.getPensionTime()) || user.getPensionTime() == null){
				user.setPensionTime(new SimpleDateFormat("yyyy/MM/dd").format(new Date()));
				dao.updatePensionTime(user);
			}
		}
		
		//添加开通会员支出500元明细
		double payment=memberprice;
		double receipt=500;
		String time=OrderNum.getregTime();
		
		if(payment!=500){
			Receipt_Payment rp3=new Receipt_Payment(0, 500-payment, time, id, 1, 1,0);
			service.add(rp3);//添加收支明细
		}
		if(payment!=0){
			Receipt_Payment rp=new Receipt_Payment(0, payment, time, id, 5, 1,0);
			service.add(rp);//添加收支明细
		}
		update_account(user,500-payment);
		//开通会员获得500抵现金
		user.setDixianjin(user.getDixianjin()+500);
		user.setFree(1);//开通会员获得一次免费购物机会
		update(user);
		Receipt_Payment rp2=new Receipt_Payment(receipt, 0, time, id, 3, 1,0);
		service.add(rp2);//添加收支明细
		String member_time=OrderNum.getregTime();
		return dao.regmember(id,member_time);
	}
	private void update_account(User user, double payment) {
		// TODO Auto-generated method stub
		user.setAccount(user.getAccount()-payment);
		dao.update_account(user);
	}
	/**
	 * 更新用户养老金
	 * @param user
	 */
	public void update_pension(User user){
		dao.update_pension(user);
	}
	public void updatePensionTime(User user) {
		dao.updatePensionTime(user);
	}
	/**
	 * 
	 * @param id 根据id获得user对象
	 * @return
	 */
	public User getid(Integer id) {
		// TODO Auto-generated method stub
		return dao.getid(id);
	}
	/**
	 * 分享商品获得1抵现金
	 * @param user_id
	 * @param goods_id
	 */
	public boolean shareGoods(Integer user_id,Integer goods_id,Integer share_way){
		 List<Receipt_Payment> recList=service.share(user_id,goods_id,share_way,OrderNum.getregTime());
		if(recList.size()==0){
			User user = getid(user_id);
			user.setDixianjin(user.getDixianjin()+1);
			Receipt_Payment rp=new Receipt_Payment(1, 0, OrderNum.getregTime(), user_id, 3, 8, goods_id,share_way);
			service.add(rp);//添加收支明细
			update(user);
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 
	 * @param //suser 分销获利 修改账户 state=1下级开通会员 state=2 下级购买商品
	 */
	/*public void addaccount(User suser,int state,double receipt) {
		// TODO Auto-generated method stub
		//double receipt=suser.getAccount()-getid(suser.getId()).getAccount();
		suser.setAccount(suser.getAccount() + receipt);
		Receipt_Payment rp=new Receipt_Payment(receipt, 0, OrderNum.getregTime(), suser.getId(), 1, 3);
		service.add(rp);//添加收支明细
		dao.addaccount(suser);//修改账户
	}*/
	public void addaccount(User user,int state,double receipt) {
		// TODO Auto-generated method stub
		// 购物获利
		if (state==1){
			Receipt_Payment rp=new Receipt_Payment(receipt, 0, OrderNum.getregTime(), user.getId(), 1, 3,0);
			service.add(rp);//添加收支明细
			dao.addaccount(user);//修改账户
		}
		if (state == 2) {
			Receipt_Payment rp=new Receipt_Payment(receipt, 0, OrderNum.getregTime(), user.getId(), 1, 3,0);
			service.add(rp);//添加收支明细
			dao.addaccount(user);//修改账户
		}
		
		/**
		 * 判断state类型，state==2没有判断，state=3为购物返养老金
		 */
		if(state == 3){
			// TODO：下级养老金获得情况
			//下级返利 养老金获得情况
			Receipt_Payment rp1=new Receipt_Payment(receipt, 0, OrderNum.getregTime(), user.getId(), 6, 3,0);
			service.add(rp1);//添加收支明细
			dao.update_pension(user);//更新用户的养老金
		}
	}
	
	/**
	 * 用户购买商品获得养老金
	 * @param price 商品总价
	 * @param user 当前用户
	 */
	public void ownPension(double price, User user){
		double p = 0;//获得的养老金
		if(price<109){
			//支付总价小于109的就要扣除运费
			p = (price-10)*0.01;
		}else{
			// 否则就不用扣除运费
			p = price*0.01;
		}
		
		user.setPension(user.getPension()+p);
		update(user);
		//添加记录
		Receipt_Payment rp1=new Receipt_Payment(p, 0, OrderNum.getregTime(), user.getId(), 6, 2,0);
		service.add(rp1);//添加收支明细
		
	}
	
	
	/**
	 * 
	 * @param user 修改用户信息（在调用之前确保入参对象里的数据是从数据库获取）
	 * @return
	 */
	public int update(User user){
		if(user.getPensionTime()==null||"".equals(user.getPensionTime()))
		{
			user.setPensionTime(OrderNum.getdate());
		}
		return dao.update(user);
	}
	/**
	 * 
	 * @param superior_id 根据用户的上级id查询此上级id的所有下级用户
	 * @param curpage 
	 * @return
	 */
	public HashMap<String, Object> LowerLevelList(int superior_id, String curpage) {
		// TODO Auto-generated method stub
		int count=dao.supCount(superior_id);
		page page=new page(curpage, count, 20);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("superior_id", superior_id);
		List<User> list = dao.LowerLevelList(map);
		map.put("page", page);
		map.put("countPage", page.getPageCount());
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	/**
	 * 
	 * @param grade 修改上级用户的vip等级
	 * @param id
	 */
	public void updatemember_grade(int grade, int id) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("grade", grade);
		map.put("id", id);
		dao.updatemember_grade(map);
	}
	//修改用户抵现金
	public int update_dixianjin(int user_id, int receipt){
			//增加获得抵现金明细
			Receipt_Payment rp=new Receipt_Payment(receipt, 0, OrderNum.getregTime(), user_id, 3, 7,0);
			service.add(rp);
		User user = dao.getid(user_id);
		user.setDixianjin(user.getDixianjin()+receipt);
		int status=dao.update_dixianjin(user);
		return status;
	}
	
	
	/**
	 * 手动添加会员
	 * @param user
	 */
	public void add(User user) {
		// TODO Auto-generated method stub
		dao.add(user);
	}
	/**
	 * 删除会员
	 * @param id
	 */
	public void del(Integer id) {
		// TODO Auto-generated method stub
		dao.del(id);
	}
	/**
	 * vip分页模糊
	 * @param name
	 * @param member_grade
	 * @return
	 */
	public int countBymember(String name, Integer member_grade) {
		// TODO Auto-generated method stub
		return dao.countBymember(name,member_grade);
	}
	public List<User> viplist(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.viplist(map);
	}
	/**
	 * 普通会员分页模糊
	 * @param name
	 * @return
	 */
	public int countBynormal(String name) {
		// TODO Auto-generated method stub
		return dao.countBynormal(name);
	}
	public List<User> noviplist(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return dao.noviplist(map);
	}
	/**
	 * 修改用户密码
	 * @param user
	 */
	public void updatePassword(User user) {
		// TODO Auto-generated method stub
		dao.updatePassword(user);
	}
	public List<User> LowerLevelList2(int superior_id) {
		// TODO Auto-generated method stub
		return dao.LowerLevelList2(superior_id);
	}
	public Member getMember() {
		// TODO Auto-generated method stub
		return dao.getMember();
	}
	public Pension getPension() {
		// TODO Auto-generated method stub
		return dao.getPension();
	}

	public List<User> getByFazhanStatus() {
		return dao.getByFazhanStatus();
	}

	public List<User> LowerLevelListF(int id) {
		return dao.LowerLevelListF(id);
	}
}
