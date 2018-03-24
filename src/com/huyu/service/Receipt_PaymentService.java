package com.huyu.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.Receipt_PaymentDao;
import com.huyu.entity.Receipt_Payment;
import com.huyu.util.page;

@Service("rpservice")
@Transactional
public class Receipt_PaymentService {
	@Resource
	private Receipt_PaymentDao dao;
	/**
	 * 
	 * @param receipt_Payment 添加收支明细
	 */
	public void add(Receipt_Payment receipt_Payment){
		dao.add(receipt_Payment);
	}
	/**
	 * 
	 * @param user_id 查询用户收支明细
	 * @return
	 */
	public HashMap<String, Object> list(Integer user_id,Integer type,String curpage){
		int count=count(user_id,type);
		page page=new page(curpage, count, 20);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("user_id", user_id);
		map.put("type", type);
		List<Receipt_Payment> list = dao.listBytype(map);
		for (Receipt_Payment rp:list) {
			if(rp.getWay()==11){
				String getphone = ((Receipt_PaymentService) AopContext.currentProxy()).getphone(rp.getUser_id(), rp.getDate_time(), rp.getType(), rp.getWay());
				rp.setPhone(getphone);
			}
		}
		map.put("page", page);
		map.put("countPage", page.getPageCount());
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	public String getphone(int user_id,String date_time,int type,int way){
		Map<Object,Object> map=new HashMap<>();
		map.put("user_id",user_id);
		map.put("date_time",date_time);
		map.put("type",type);
		map.put("way",way);
		return dao.getRpdetails(map);
	}
	/**
	 * 
	 * @param user_id
	 * @param type 查询用户收支明细根据模块类型
	 * @return
	 */
	public HashMap<String, Object> listBytype(Integer user_id,Integer type,String curpage){
		int count=count(user_id,type);
		page page=new page(curpage, count, 20);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("startRecord", page.getStartRecord());
		map.put("pageSize", page.getPageSize());
		map.put("user_id", user_id);
		map.put("type", type);
		List<Receipt_Payment> list = dao.listBytype(map);
		map.put("page", page);
		map.put("countPage", page.getPageCount());
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	public HashMap<String,Object> listByLayuiAndType(Integer userId,Integer type,Integer curpage,Integer limit){
		int count=count(userId,type);
		HashMap<String,Object> map = new HashMap<String, Object>();
		map.put("start", (curpage-1)*limit);
		map.put("limit", limit);
		map.put("user_id", userId);
		map.put("type",type);
		List<Receipt_Payment> list = dao.listByLayuiAndType(map);
		map.put("list", list);
		map.put("count", count);
		return map;
	}
	private int count(Integer user_id, Integer type) {
		// TODO Auto-generated method stub
		Map<String, Integer> map=new HashMap<String, Integer>();
		map.put("user_id", user_id);
		map.put("type", type);
		return dao.count(map);
	}
	/**
	 * 
	 * @param user_id查询用户收支明细根据模块类型为 1余额 5第三方支付
	 * @return
	 */
	public List<Receipt_Payment> listBytype_1_5(Integer user_id){
		return dao.listBytype_1_5(user_id);
	}
	public List<Receipt_Payment> share(Integer user_id, Integer goods_id,
			Integer share_way, String date_time) {
		// TODO Auto-generated method stub
		return dao.share(user_id,goods_id,share_way,date_time);
	}
	
	/**
	 * 返回互余币和积分、余额的记录
	 * @param userId
	 * @param time
	 * @return
	 */
	public List<Receipt_Payment> listByOrderTimeAndUserId(Integer userId,String time){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", userId);
		map.put("date_time", time);
		/*List<Receipt_Payment> list = dao.listByOrderTimeAndUserId(map);
		List<Receipt_Payment> list2 = new ArrayList<Receipt_Payment>();*/
		/*if(list.size() == 4){
			for(Receipt_Payment rp :list){
				if(rp.getType() == 2 || rp.getType() == 4){
					list2.add(rp);
				}
			}
		}*/
		return dao.listByOrderTimeAndUserId(map);
	}

	public int countf(String name) {
		return dao.countf(name);
	}

	public List<Receipt_Payment> listPage(HashMap<String, Object> map) {
		return dao.listPage(map);
	}
}
