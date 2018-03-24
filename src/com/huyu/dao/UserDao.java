package com.huyu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Member;
import com.huyu.entity.Pension;
import com.huyu.entity.User;
@Repository
public class UserDao {
@Resource
private SqlSessionTemplate sm;
	public void reg(User user) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.User.reg", user);
	}
	public User getByName(String name) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.User.getByName", name);
	}
	public User getByPhone(String phone) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.User.getByPhone", phone);
	}
	public int regmember(int id, String member_time) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("id", id);
		map.put("member_time", member_time);
		return sm.update("com.huyu.entity.User.regmember", map);
	}
	public User getid(Integer id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.User.getById", id);
	}
	public void addaccount(User suser) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.User.addAccount", suser);
	}
	public List<User> LowerLevelList(HashMap<String,Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.User.LowerLevelList", map);
	}
	public void updatemember_grade(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.User.updatemember_grade", map);
	}
	public int update_dixianjin(User user) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.User.update_dixianjin", user);
	}
	public int update_pension(User user) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.User.update_pension", user);
	}

	public int updatePensionTime(User user) {
		// TODO Auto-generated method stub
		int update = sm.update("com.huyu.entity.User.updatePensionTime", user);
		System.out.println("进入dao层...");
		return update;
	}
	public int update(User user) {
		// TODO Auto-generated method stub
		return sm.update("com.huyu.entity.User.update", user);
	}
	public List<User> viplist(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.User.viplist", map);
	}
	public List<User> noviplist(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.User.noviplist",map);
	}
	public void update_account(User user) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.User.update_account", user);
	}
	public void add(User user) {
		// TODO Auto-generated method stub
		sm.insert("com.huyu.entity.User.add", user);
	}
	public void del(Integer id) {
		// TODO Auto-generated method stub
		sm.delete("com.huyu.entity.User.del",id);
	}
	public int countBymember(String name, Integer member_grade) {
		// TODO Auto-generated method stub
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("name", name);
		map.put("member_grade", member_grade);
		return sm.selectOne("com.huyu.entity.User.countBymember", map);
	}
	public int countBynormal(String name) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.User.countBynormal", name);
	}
	public void updatePassword(User user) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.User.updatePassword", user);
	}
	public int supCount(int superior_id) {
		// TODO Auto-generated method stub
		return sm.selectOne("com.huyu.entity.User.supCount", superior_id);
	}
	public List<User> LowerLevelList2(int superior_id) {
		// TODO Auto-generated method stub
		return sm.selectList("com.huyu.entity.User.LowerLevelList2", superior_id);
	}
	public Member getMember() {
		// TODO Auto-generated method stub
		List<Member> list=sm.selectList("com.huyu.entity.User.getMember");
		return list.get(0);
	}
	public Pension getPension() {
		// TODO Auto-generated method stub
		List<Pension> list=sm.selectList("com.huyu.entity.User.getPension");
		return list.get(0);
	}


	public List<User> getByFazhanStatus() {
		return sm.selectList("com.huyu.entity.User.getByFazhanStatus");
	}

    public List<User> LowerLevelListF(int id) {
        return sm.selectList("com.huyu.entity.User.LowerLevelListF",id);
    }
}
