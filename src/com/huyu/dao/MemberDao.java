package com.huyu.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.huyu.entity.Member;
import com.huyu.entity.Pension;
@Repository
public class MemberDao {
	@Resource
	private SqlSessionTemplate sm;
	public Member getMember() {
		// TODO Auto-generated method stub
		List<Member> list=sm.selectList("com.huyu.entity.Member.getMember");
		return list.get(0);
	}
	public void update(Member member) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Member.update", member);
	}
	public Pension getPension() {
		// TODO Auto-generated method stub
		List<Pension> list=sm.selectList("com.huyu.entity.Member.getPension");
		return list.get(0);
	}
	
	public void updatePension(Pension pension) {
		// TODO Auto-generated method stub
		sm.update("com.huyu.entity.Member.updatePension", pension);
	}

}
