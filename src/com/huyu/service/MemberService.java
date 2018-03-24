package com.huyu.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huyu.dao.MemberDao;
import com.huyu.entity.Member;
import com.huyu.entity.Pension;
@Service
@Transactional
public class MemberService {
	@Resource
	private MemberDao dao;
	public Member getMember() {
		// TODO Auto-generated method stub
		return dao.getMember();
	}
	public void update(Member member) {
		// TODO Auto-generated method stub
		dao.update(member);
	}
	public Pension getPension() {
		// TODO Auto-generated method stub
		return dao.getPension();
	}
	public void update(Pension pension) {
		// TODO Auto-generated method stub
		dao.updatePension(pension);
	}

}
