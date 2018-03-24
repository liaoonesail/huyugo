package com.huyu.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.huyu.entity.Member;
import com.huyu.entity.Pension;
import com.huyu.entity.User;
/**
 * 会员系统
 * @author Administrator
 *
 */
@Component
public class MemberSystem {
	/**
	 * vip等级验证及修改，user为刚刚才被邀请注册的用户或者注册后才开通vip的用户
	 * 由于vip的等级除了vip1是直推10人外，vip2和vip3都只和直推的vip1多少有关，所有只用关联到上上级即可；
	 * @param user
	 */
	@Resource
	private UserService service;
	
	/**
	 * vip 等级
	 * @param user
	 */
	public void bemember(User user){
		int grade=0;
		int superior_id=user.getSuperior_id();
			List<User> suserlist=service.LowerLevelList2(superior_id);//获取上级的所有下级
			if(suserlist.size()>=10){//直推十人成为vip1（不管有没有成为会员）
				int sum = 0;
				for (User u : suserlist){
					if(u.getMember() == 1){
						sum++;
					}
				}
				if(sum >= 10){
					grade=1;
					service.updatemember_grade(grade,user.getSuperior_id());//修改上级vip等级vip1
				}
				
				User suser=service.getid(user.getSuperior_id());
				List<User> ssuserlist=service.LowerLevelList2(suser.getSuperior_id());//获取上上级的所有所有下级
				if(ssuserlist.size()>=10){
					sum=0;
					for (User user2 : ssuserlist) {
						if(user2.getMember_grade()>=1&&user2.getMember()==1){
							sum++;
						}
						if(sum>=5){//直推十人以上其中有至少5个vip1（开通了会员的）
							grade=2;
							service.updatemember_grade(grade,user.getSuperior_id());//修改vip等级vip2
						}
						if(sum>=12){//直推十人以上其中有至少12个vip1（开通了会员的）
							grade=3;
							service.updatemember_grade(grade,user.getSuperior_id());//修改vip等级vip3
						}
					}
				}
				sum=0;
				for (User user2 : suserlist) {
					if(user2.getMember_grade()>=1&&user2.getMember()==1){
						sum++;
					}
					if(sum>=5){//直推十人以上其中有至少5个vip1（开通了会员的）
						grade=2;
						service.updatemember_grade(grade,user.getSuperior_id());//修改vip等级vip2
					}
					if(sum>=12){//直推十人以上其中有至少12个vip1（开通了会员的）
						grade=3;
						service.updatemember_grade(grade,user.getSuperior_id());//修改vip等级vip3
					}
				}
			}
		
	}
	/**
	 *
	 * @param user 开通会员vip获利
	 * @param memberprice
	 */
	public void getprofit(User user,double memberprice){
		Member member=service.getMember();
		memberprice=500;//客户要求进入分销系统的是微信支付+余额支付=500元
		User suser;
		User ssuser=null;
		User sssuser=null;
		suser=service.getid(user.getSuperior_id());//用户上级
		if(suser!=null){
			 ssuser=service.getid(suser.getSuperior_id());//用户上上级
			if(ssuser!=null){
				 sssuser=service.getid(ssuser.getSuperior_id());//用户上上上级
			}
		}
		if(suser!=null){
			if(suser.getMember()==1){//上级是vip
				if(suser.getMember_grade()==0){
					suser.setAccount(suser.getAccount()+memberprice*member.getM_dai1());//上级为vip0
					service.addaccount(suser,1,memberprice*member.getM_dai1());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());//上级是会员vip0,上上级为vip0
								service.addaccount(ssuser,1,memberprice*member.getM_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip0,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v1());//上级为vip0,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());//上级为vip0,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());//上级为vip0,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//...........................没有
									}
									
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v1());//上级为vip0,上上级为vip1
								service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip0,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());//上级为vip0,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_2()+memberprice*member.getM_v2_service());//上级为vip0,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_2()+memberprice*member.getM_v2_service());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());//上级为vip0,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//..........................没有
									}
									
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());//上级为vip0,上上级为vip2
								service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip0,上上级为vip2,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip0,上上级为vip2,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());//上级为vip0,上上级为vip2,上上上级为vip2
											service.addaccount(sssuser,1,+memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());//上级为vip0,上上级为vip2,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//.........................没有
									}
									
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());//上级为vip0,上上级为vip3
								service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip0,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip0,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip0,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());//上级为vip0,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//...........................没有
									}
									
								}
							}
						}else if(ssuser.getMember()==0){//上级是会员vip0,上上级不是vip
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip0,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v1());//上级为vip0,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());//上级为vip0,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());//上级为vip0,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									//....................没有
								}
							}
						}
						
					}
				}else if(suser.getMember_grade()==1){
					suser.setAccount(suser.getAccount()+memberprice*member.getM_dai1()+memberprice*member.getM_v1());//上级为vip1
					service.addaccount(suser,1,memberprice*member.getM_dai1()+memberprice*member.getM_v1());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上级为vip1，上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());//上级为vip1,上上级为vip0
								service.addaccount(ssuser,1,memberprice*member.getM_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip1,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());//上级为vip1,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());//上级为vip1,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());//上级为vip1,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//.................没有
									}
									
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v1_1());//上级是vip1,上上级为vip1
								service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v1_1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级是vip1,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());//上级是vip1,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());//上级是vip1,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());//上级是vip1,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//.................没有
									}
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());//上级是vip1,上上级为vip2
								service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级是vip1,上上级为vip2,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级是vip1,上上级为vip2,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());//上级是vip1,上上级为vip2,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());//上级是vip1,上上级为vip2,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//.................没有
									}
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());//上级是vip1,上上级为vip3
								service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级是vip1,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级是vip1,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级是vip1,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());//上级是vip1,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//.................没有
									}
								}
							}
						}else if(ssuser.getMember()==0){//上级为vip1，上上级不是vip
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip1,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());//上级为vip1,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());//上级为vip1,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());//上级为vip1,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									//....................没有
								}
							}
						}
					}
				}else if(suser.getMember_grade()==2){
					suser.setAccount(suser.getAccount()+memberprice*member.getM_dai1()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());//上级为vip2
					service.addaccount(suser,1,memberprice*member.getM_dai1()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上级为vip2,上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());//上级为vip2,上上级为vip0
								service.addaccount(ssuser,1,memberprice*member.getM_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上级为vip0,上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());//上级为vip2,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());//上级为vip2,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());
										}
									}else if(sssuser.getMember()==0){//上上级为vip0,上上上级不是vip
										//....................没有
									}
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());//上级为vip2,上上级为vip1
								service.addaccount(ssuser,1,memberprice*member.getM_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上级为vip1,上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());//上级为vip2,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());//上级为vip2,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());
										}
									}else if(sssuser.getMember()==0){//上上级为vip1,上上上级不是vip
										//....................没有
									}
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());//上级为vip2,上上级为vip2
								service.addaccount(ssuser,1,memberprice*member.getM_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上级为vip2,上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上级为vip2,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上级为vip2,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());//上级为vip2,上上级为vip2,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());//上级为vip2,上上级为vip2,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());
										}
									}else if(sssuser.getMember()==0){//上上级为vip2,上上上级不是vip
										//....................没有
									}
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());//上级为vip2,上上级为vip3
								service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上级为vip3,上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());//上级为vip2,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());
										}
									}else if(sssuser.getMember()==0){//上级为vip3,上上上级不是vip
										//....................没有
									}
								}
							}
						}else if(ssuser.getMember()==0){//上级为vip2,上上级不是vip
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip2,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());//上级为vip2,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());//上级为vip2,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									//....................没有
								}
							}
						}
					}
				}else if(suser.getMember_grade()==3){
					suser.setAccount(suser.getAccount()+memberprice*member.getM_dai1()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());//上级为vip3
					service.addaccount(suser,1,memberprice*member.getM_dai1()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上级为vip3,上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());//上级为vip3,上上级为vip0
								service.addaccount(ssuser,1,memberprice*member.getM_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());//上级为vip3,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//....................没有
									}
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());//上级为vip3,上上级为vip1
								service.addaccount(ssuser,1,memberprice*member.getM_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());//上级为vip3,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//....................没有
									}
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());//上级为vip3,上上级为vip2
								service.addaccount(ssuser,1,memberprice*member.getM_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip2,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip2,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip2,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());//上级为vip3,上上级为vip2,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//....................没有
									}
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());//上级为vip3,上上级为vip3
								service.addaccount(ssuser,1,memberprice*member.getM_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,1,memberprice*member.getM_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());//上级为vip3,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										//....................没有
									}
								}
							}
						}else if(ssuser.getMember()==0){//上上级不是vip
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级为vip3,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());//上级为vip3,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									//....................没有
								}
							}
						}
						
					}
				}
			}else if(suser.getMember()==0){//上级不是vip
				if(ssuser!=null){
					if(ssuser.getMember()==1){//上上级是vip
						if(ssuser.getMember_grade()==0){//上级不是vip,上上级为vip0
							ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2());
							service.addaccount(ssuser,1,memberprice*member.getM_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip0,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级不是vip,上上为vip0,上上上级为vip0
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v1());//上级不是vip,上上为vip0,上上上级为vip1
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());//上级不是vip,上上为vip0,上上上级为vip2
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());//上级不是vip,上上为vip0,上上上级为vip3
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip0,上上上级不是vip
									//........................没有
								}
							}
						}else if(ssuser.getMember_grade()==1){//上级不是vip,上上级为vip1
							ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v1());
							service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v1());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip1,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级不是vip,上上为vip1,上上上级为vip0
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());//上级不是vip,上上为vip1,上上上级为vip1
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v1_1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());//上级不是vip,上上为vip1,上上上级为vip2
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_1()+memberprice*member.getM_v2_service());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());//上级不是vip,上上为vip1,上上上级为vip3
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_1()+memberprice*member.getM_v2_service());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip1,上上上级不是vip
									//........................没有
								}
							}
						}else if(ssuser.getMember_grade()==2){//上级不是vip,上上级为vip2
							ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());
							service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip2,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级不是vip,上上为vip2,上上上级为vip0
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级不是vip,上上为vip2,上上上级为vip1
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());//上级不是vip,上上为vip2,上上上级为vip2
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2_2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());//上级不是vip,上上为vip2,上上上级为vip3
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_2()+memberprice*member.getM_v2_service());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip2,上上上级不是vip
									//........................没有
								}
							}
						}else if(ssuser.getMember_grade()==3){//上级不是vip,上上级为vip3
							ssuser.setAccount(ssuser.getAccount()+memberprice*member.getM_dai2()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());
							service.addaccount(ssuser,1,memberprice*member.getM_dai2()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip3,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级不是vip,上上为vip3,上上上级为vip0
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级不是vip,上上为vip3,上上上级为vip1
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级不是vip,上上为vip3,上上上级为vip2
										service.addaccount(sssuser,1,memberprice*member.getM_dai3());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());//上级不是vip,上上为vip3,上上上级为vip3
										service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3_3());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip3,上上上级不是vip
									//........................没有
								}
							}
						}
					}else if(ssuser.getMember()==0){//上级不是vip,上上级不是vip
						if(sssuser!=null){
							if(sssuser.getMember()==1){//上级不是vip,上上级不是vip,上上上级是vip
								if(sssuser.getMember_grade()==0){
									sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3());//上级不是vip,上上不是vip,上上上级为vip0
									service.addaccount(sssuser,1,memberprice*member.getM_dai3());
								}else if(sssuser.getMember_grade()==1){
									sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v1());//上级不是vip,上上不是vip,上上上级为vip1
									service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v1());
								}else if(sssuser.getMember_grade()==2){
									sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());//上级不是vip,上上不是vip,上上上级为vip2
									service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v2()+memberprice*member.getM_v2_service());
								}else if(sssuser.getMember_grade()==3){
									sssuser.setAccount(sssuser.getAccount()+memberprice*member.getM_dai3()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());//上级不是vip,上上不是vip,上上上级为vip3
									service.addaccount(sssuser,1,memberprice*member.getM_dai3()+memberprice*member.getM_v3()+memberprice*member.getM_v2_service());
								}
							}else if(sssuser.getMember()==0){//上级不是vip,上上级不是vip,上上上级不是vip
								//..............完结撒花\(^o^)/
							}
						}
					}
				}
			}
		}
	}
	/*public void profitBuy(double price, int userId, int m, int n, String gread){
		if(m <= n){
			double price_ = 0;
			User user = service.getid(userId);
			if(user != null){
				String isMember = gread.split("_")[0];
				String vipdj = gread.split("_")[1];
				String result = "";
				if(user.getMember() == 0){
					price_ = price * 0.03;
					if("y".equals(isMember)){
						result = "y_" + vipdj;
					}else{
						result = "y_0";
					}
				}else{
					if(user.getMember_grade() == 0){
						price_ = price * 0.03;
						if(Integer.parseInt(vipdj) > 0){
							result = "y_" + vipdj;
						}else{
							result = "y_0";
						}
					}else if(user.getMember_grade() == 1){
						if("y".equals(isMember) && Integer.parseInt(vipdj) >= 1){
							price_ = price * 0.03;
						}else{
							price_ = price * 0.05;
						}
						if(Integer.parseInt(vipdj) > 1){
							result = "y_" + vipdj;
						}else{
							result = "y_1";
						}
					}else if(user.getMember_grade() == 2){
						if("y".equals(isMember) && Integer.parseInt(vipdj) >= 2){
							price_ = price * 0.03;
						}else if("y".equals(isMember) && Integer.parseInt(vipdj) == 1){
							price_ = price * 0.05;
						}else{
							price_ = price * 0.07;
						}
						if(Integer.parseInt(vipdj) > 2){
							result = "y_" + vipdj;
						}else{
							result = "y_2";
						}
					}else if(user.getMember_grade() == 3){
						if("y".equals(isMember) && Integer.parseInt(vipdj) >= 3){
							price_ = price * 0.03;
						}else if("y".equals(isMember) && Integer.parseInt(vipdj) == 2){
							price_ = price * 0.05;
						}else if("y".equals(isMember) && Integer.parseInt(vipdj) == 1){
							price_ = price * 0.07;
						}else{
							price_ = price * 0.09;
						}
						if(Integer.parseInt(vipdj) > 3){
							result = "y_" + vipdj;
						}else{
							result = "y_3";
						}
					}
				}
				//user.setAccount(user.getAccount() + price_);
				if(m == 2){
					price_ = price_ - price * 0.01;
				}
				service.addaccount(user,2,price_);
				m++;
				profitBuy(price, user.getSuperior_id(), m, n, result);
			}
		}
	}
	*/
	/**
	 * 购物获利
	 * @param price
	 * @param user
	 */

	public void getprofit_buy(double price,User user){
		Member member=service.getMember();
		User suser=null;
		User ssuser=null;
		User sssuser=null;
		suser=service.getid(user.getSuperior_id());//用户上级
		if(suser!=null){
			 ssuser=service.getid(suser.getSuperior_id());//用户上上级
			if(ssuser!=null){
				 sssuser=service.getid(ssuser.getSuperior_id());//用户上上上级
			}
		}
		if(suser!=null){
			if(suser.getMember()==1){//上级是vip
				if(suser.getMember_grade()==0){
					suser.setAccount(suser.getAccount()+price*member.getB_dai1());//上级为vip0
					service.addaccount(suser,2,price*member.getB_dai1());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());//上级是会员vip0,上上级为vip0
								service.addaccount(ssuser,2,price*member.getB_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip0,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v1());//上级为vip0,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2());//上级为vip0,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3());//上级为vip0,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
									
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v1());//上级为vip0,上上级为vip1
								service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip0,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v1_1());//上级为vip0,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v1_1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_1());//上级为vip0,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_1());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_1());//上级为vip0,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_1());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
									
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v2());//上级为vip0,上上级为vip2
								service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip0,上上级为vip2,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip0,上上级为vip2,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_2());//上级为vip0,上上级为vip2,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_2());//上级为vip0,上上级为vip2,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
									
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v3());//上级为vip0,上上级为vip3
								service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v3());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip0,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip0,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip0,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_3());//上级为vip0,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
									
								}
							}
						}else if(ssuser.getMember()==0){//上级是会员vip0,上上级不是vip
							ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());
							service.addaccount(ssuser,2,price*member.getB_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip0,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v1());//上级为vip0,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2());//上级为vip0,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3());//上级为vip0,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
									service.addaccount(sssuser,2,price*member.getB_dai3());
								}
							}
						}
						
					}
				}else if(suser.getMember_grade()==1){
					suser.setAccount(suser.getAccount()+price*member.getB_dai1()+price*member.getB_v1());//上级为vip1
					service.addaccount(suser,2,price*member.getB_dai1()+price*member.getB_v1());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上级为vip1，上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());//上级为vip1,上上级为vip0
								service.addaccount(ssuser,2,price*member.getB_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip1,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v1_1());//上级为vip1,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v1_1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_1());//上级为vip1,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_1());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_1());//上级为vip1,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_1());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
									
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v1_1());//上级是vip1,上上级为vip1
								service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v1_1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级是vip1,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v1_1());//上级是vip1,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v1_1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_1());//上级是vip1,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_1());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_1());//上级是vip1,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_1());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v2_1());//上级是vip1,上上级为vip2
								service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v2_1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级是vip1,上上级为vip2,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级是vip1,上上级为vip2,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_2());//上级是vip1,上上级为vip2,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_2());//上级是vip1,上上级为vip2,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v3_1());//上级是vip1,上上级为vip3
								service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v3_1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级是vip1,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级是vip1,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级是vip1,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_3());//上级是vip1,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}
						}else if(ssuser.getMember()==0){//上级为vip1，上上级不是vip
							ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());
							service.addaccount(ssuser,2,price*member.getB_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip1,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v1_1());//上级为vip1,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v1_1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_1());//上级为vip1,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_1());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_1());//上级为vip1,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_1());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
									service.addaccount(sssuser,2,price*member.getB_dai3());
								}
							}
						}
					}
				}else if(suser.getMember_grade()==2){
					suser.setAccount(suser.getAccount()+price*member.getB_dai1()+price*member.getB_v2());//上级为vip2
					service.addaccount(suser,2,price*member.getB_dai1()+price*member.getB_v2());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上级为vip2,上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());//上级为vip2,上上级为vip0
								service.addaccount(ssuser,2,price*member.getB_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_2());//上级为vip2,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_2());//上级为vip2,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());//上级为vip2,上上级为vip1
								service.addaccount(ssuser,2,price*member.getB_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_2());//上级为vip2,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_2());//上级为vip2,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v2_2());//上级为vip2,上上级为vip2
								service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v2_2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上级为vip2,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上级为vip2,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_2());//上级为vip2,上上级为vip2,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_2());//上级为vip2,上上级为vip2,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v3_2());//上级为vip2,上上级为vip3
								service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v3_2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_3());//上级为vip2,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}
						}else if(ssuser.getMember()==0){//上级为vip2,上上级不是vip
							ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());
							service.addaccount(ssuser,2,price*member.getB_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip2,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_2());//上级为vip2,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_2());//上级为vip2,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_2());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
									service.addaccount(sssuser,2,price*member.getB_dai3());
								}
							}
						}
					}
				}else if(suser.getMember_grade()==3){
					suser.setAccount(suser.getAccount()+price*member.getB_dai1()+price*member.getB_v3());//上级为vip3
					service.addaccount(suser,2,price*member.getB_dai1()+price*member.getB_v3());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上级为vip3,上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());//上级为vip3,上上级为vip0
								service.addaccount(ssuser,2,price*member.getB_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上级为vip0,上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_3());//上级为vip3,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上级为vip0,上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());//上级为vip3,上上级为vip1
								service.addaccount(ssuser,2,price*member.getB_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_3());//上级为vip3,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());//上级为vip3,上上级为vip2
								service.addaccount(ssuser,2,price*member.getB_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip2,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip2,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip2,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_3());//上级为vip3,上上级为vip2,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v3_3());//上级为vip3,上上级为vip3
								service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v3_3());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,2,price*member.getB_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_3());//上级为vip3,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}
								}
							}
						}else if(ssuser.getMember()==0){//上上级不是vip
							ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());
							service.addaccount(ssuser,2,price*member.getB_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级为vip3,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_3());//上级为vip3,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_3());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
									service.addaccount(sssuser,2,price*member.getB_dai3());
								}
							}
						}
						
					}
				}
			}else if(suser.getMember()==0){//上级不是vip
				suser.setAccount(suser.getAccount()+price*member.getB_dai1());
				service.addaccount(suser,2,price*member.getB_dai1());
				if(ssuser!=null){
					if(ssuser.getMember()==1){//上上级是vip
						if(ssuser.getMember_grade()==0){//上级不是vip,上上级为vip0
							ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());
							service.addaccount(ssuser,2,price*member.getB_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip0,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级不是vip,上上为vip0,上上上级为vip0
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v1());//上级不是vip,上上为vip0,上上上级为vip1
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2());//上级不是vip,上上为vip0,上上上级为vip2
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3());//上级不是vip,上上为vip0,上上上级为vip3
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip0,上上上级不是vip
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
									service.addaccount(sssuser,2,price*member.getB_dai3());
								}
							}
						}else if(ssuser.getMember_grade()==1){//上级不是vip,上上级为vip1
							ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v1());
							service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v1());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip1,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级不是vip,上上为vip1,上上上级为vip0
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v1_1());//上级不是vip,上上为vip1,上上上级为vip1
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v1_1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_1());//上级不是vip,上上为vip1,上上上级为vip2
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_1());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_1());//上级不是vip,上上为vip1,上上上级为vip3
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_1());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip1,上上上级不是vip
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
									service.addaccount(sssuser,2,price*member.getB_dai3());
								}
							}
						}else if(ssuser.getMember_grade()==2){//上级不是vip,上上级为vip2
							ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v2());
							service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip2,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级不是vip,上上为vip2,上上上级为vip0
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级不是vip,上上为vip2,上上上级为vip1
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2_2());//上级不是vip,上上为vip2,上上上级为vip2
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2_2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_2());//上级不是vip,上上为vip2,上上上级为vip3
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_2());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip2,上上上级不是vip
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
									service.addaccount(sssuser,2,price*member.getB_dai3());
								}
							}
						}else if(ssuser.getMember_grade()==3){//上级不是vip,上上级为vip3
							ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2()+price*member.getB_v3());
							service.addaccount(ssuser,2,price*member.getB_dai2()+price*member.getB_v3());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip3,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级不是vip,上上为vip3,上上上级为vip0
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级不是vip,上上为vip3,上上上级为vip1
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级不是vip,上上为vip3,上上上级为vip2
										service.addaccount(sssuser,2,price*member.getB_dai3());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3_3());//上级不是vip,上上为vip3,上上上级为vip3
										service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3_3());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip3,上上上级不是vip
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
									service.addaccount(sssuser,2,price*member.getB_dai3());
								}
							}
						}
					}else if(ssuser.getMember()==0){//上级不是vip,上上级不是vip
						ssuser.setAccount(ssuser.getAccount()+price*member.getB_dai2());
						service.addaccount(ssuser,2,price*member.getB_dai2());
						if(sssuser!=null){
							if(sssuser.getMember()==1){//上级不是vip,上上级不是vip,上上上级是vip
								if(sssuser.getMember_grade()==0){
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());//上级不是vip,上上不是vip,上上上级为vip0
									service.addaccount(sssuser,2,price*member.getB_dai3());
								}else if(sssuser.getMember_grade()==1){
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v1());//上级不是vip,上上不是vip,上上上级为vip1
									service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v1());
								}else if(sssuser.getMember_grade()==2){
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v2());//上级不是vip,上上不是vip,上上上级为vip2
									service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v2());
								}else if(sssuser.getMember_grade()==3){
									sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3()+price*member.getB_v3());//上级不是vip,上上不是vip,上上上级为vip3
									service.addaccount(sssuser,2,price*member.getB_dai3()+price*member.getB_v3());
								}
							}else if(sssuser.getMember()==0){//上级不是vip,上上级不是vip,上上上级不是vip
								sssuser.setAccount(sssuser.getAccount()+price*member.getB_dai3());
								service.addaccount(sssuser,2,price*member.getB_dai3());
								//..............完结撒花\(^o^)/
							}
						}
					}
				}
			}
		}
	}
	
	
	/**
	 * 养老金
	 * @param price
	 * @param user
	 */

	public void getprofit_pension(double price,User user){
		//自己获得的养老金部分
		service.ownPension(price, user);
		
		// 上级养老金计算部分除运费
		if (price < 109){
			price = price - 10;
		}
		
		//上下级获得的部分
		Pension pension=service.getPension();
		User suser=null;
		User ssuser=null;
		User sssuser=null;
		suser=service.getid(user.getSuperior_id());//用户上级
		if(suser!=null){
			 ssuser=service.getid(suser.getSuperior_id());//用户上上级
			if(ssuser!=null){
				 sssuser=service.getid(ssuser.getSuperior_id());//用户上上上级
			}
		}
		if(suser!=null){
			if(suser.getMember()==1){//上级是vip
				if(suser.getMember_grade()==0){
					suser.setPension(suser.getPension()+price*pension.getY_dai1());//上级为vip0
					service.addaccount(suser,3,price*pension.getY_dai1());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());//上级是会员vip0,上上级为vip0
								service.addaccount(ssuser,3,price*pension.getY_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip0,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v1());//上级为vip0,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2());//上级为vip0,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3());//上级为vip0,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
									
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v1());//上级为vip0,上上级为vip1
								service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip0,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v1_1());//上级为vip0,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v1_1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_1());//上级为vip0,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_1());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_1());//上级为vip0,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_1());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
									
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v2());//上级为vip0,上上级为vip2
								service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip0,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip0,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_2());//上级为vip0,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_2());//上级为vip0,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
									
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v3());//上级为vip0,上上级为vip3
								service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v3());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip0,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip0,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip0,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_3());//上级为vip0,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
									
								}
							}
						}else if(ssuser.getMember()==0){//上级是会员vip0,上上级不是vip
							ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());
							service.addaccount(ssuser,3,price*pension.getY_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip0,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v1());//上级为vip0,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2());//上级为vip0,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3());//上级为vip0,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
									service.addaccount(sssuser,3,price*pension.getY_dai3());
								}
							}
						}
						
					}
				}else if(suser.getMember_grade()==1){
					suser.setPension(suser.getPension()+price*pension.getY_dai1()+price*pension.getY_v1());//上级为vip1
					service.addaccount(suser,3,price*pension.getY_dai1()+price*pension.getY_v1());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上级为vip1，上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());//上级为vip1,上上级为vip0
								service.addaccount(ssuser,3,price*pension.getY_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip1,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v1_1());//上级为vip1,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v1_1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_1());//上级为vip1,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_1());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_1());//上级为vip1,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_1());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
									
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v1_1());//上级是vip1,上上级为vip1
								service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v1_1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级是vip1,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v1_1());//上级是vip1,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v1_1());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_1());//上级是vip1,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_1());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_1());//上级是vip1,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_1());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v2_1());//上级是vip1,上上级为vip2
								service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v2_1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级是vip1,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级是vip1,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_2());//上级是vip1,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_2());//上级是vip1,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v3_1());//上级是vip1,上上级为vip3
								service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v3_1());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级是vip1,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级是vip1,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级是vip1,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_3());//上级是vip1,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}
						}else if(ssuser.getMember()==0){//上级为vip1，上上级不是vip
							ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());
							service.addaccount(ssuser,3,price*pension.getY_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip1,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v1_1());//上级为vip1,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v1_1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_1());//上级为vip1,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_1());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_1());//上级为vip1,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_1());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
									service.addaccount(sssuser,3,price*pension.getY_dai3());
								}
							}
						}
					}
				}else if(suser.getMember_grade()==2){
					suser.setPension(suser.getPension()+price*pension.getY_dai1()+price*pension.getY_v2());//上级为vip2
					service.addaccount(suser,3,price*pension.getY_dai1()+price*pension.getY_v2());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上级为vip3,上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());//上级为vip3,上上级为vip0
								service.addaccount(ssuser,3,price*pension.getY_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_2());//上级为vip3,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_2());//上级为vip3,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());//上级为vip3,上上级为vip1
								service.addaccount(ssuser,3,price*pension.getY_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_2());//上级为vip3,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_2());//上级为vip3,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v2_2());//上级为vip3,上上级为vip2
								service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v2_2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_2());//上级为vip3,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_2());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_2());//上级为vip3,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_2());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v3_2());//上级为vip3,上上级为vip3
								service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v3_2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_3());//上级为vip3,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}
						}else if(ssuser.getMember()==0){//上级为vip3,上上级不是vip
							ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());
							service.addaccount(ssuser,3,price*pension.getY_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_2());//上级为vip3,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_2());//上级为vip3,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_2());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
									service.addaccount(sssuser,3,price*pension.getY_dai3());
								}
							}
						}
					}
				}else if(suser.getMember_grade()==3){
					suser.setPension(suser.getPension()+price*pension.getY_dai1()+price*pension.getY_v3());//上级为vip3
					service.addaccount(suser,3,price*pension.getY_dai1()+price*pension.getY_v3());
					if(ssuser!=null){
						if(ssuser.getMember()==1){//上级为vip3,上上级是vip
							if(ssuser.getMember_grade()==0){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());//上级为vip3,上上级为vip0
								service.addaccount(ssuser,3,price*pension.getY_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上级为vip0,上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip0,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip0,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip0,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_3());//上级为vip3,上上级为vip0,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上级为vip0,上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==1){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());//上级为vip3,上上级为vip1
								service.addaccount(ssuser,3,price*pension.getY_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip1,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip1,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip1,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_3());//上级为vip3,上上级为vip1,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==2){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());//上级为vip3,上上级为vip2
								service.addaccount(ssuser,3,price*pension.getY_dai2());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_3());//上级为vip3,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}else if(ssuser.getMember_grade()==3){
								ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v3_3());//上级为vip3,上上级为vip3
								service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v3_3());
								if(sssuser!=null){
									if(sssuser.getMember()==1){//上上上级是vip
										if(sssuser.getMember_grade()==0){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip0
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==1){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip1
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==2){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上级为vip3,上上上级为vip2
											service.addaccount(sssuser,3,price*pension.getY_dai3());
										}else if(sssuser.getMember_grade()==3){
											sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_3());//上级为vip3,上上级为vip3,上上上级为vip3
											service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_3());
										}
									}else if(sssuser.getMember()==0){//上上上级不是vip
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}
								}
							}
						}else if(ssuser.getMember()==0){//上上级不是vip
							ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());
							service.addaccount(ssuser,3,price*pension.getY_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上上级不是vip,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上不是vip,上上上级为vip0
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上不是vip,上上上级为vip1
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级为vip3,上上不是vip,上上上级为vip2
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_3());//上级为vip3,上上不是vip,上上上级为vip3
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_3());
									}
								}else if(sssuser.getMember()==0){//上上级不是vip,上上上级不是vip
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
									service.addaccount(sssuser,3,price*pension.getY_dai3());
								}
							}
						}
						
					}
				}
			}else if(suser.getMember()==0){//上级不是vip
				suser.setPension(suser.getPension()+price*pension.getY_dai1());
				service.addaccount(suser,3,price*pension.getY_dai1());
				if(ssuser!=null){
					if(ssuser.getMember()==1){//上上级是vip
						if(ssuser.getMember_grade()==0){//上级不是vip,上上级为vip0
							ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());
							service.addaccount(ssuser,3,price*pension.getY_dai2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip0,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级不是vip,上上为vip0,上上上级为vip0
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v1());//上级不是vip,上上为vip0,上上上级为vip1
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2());//上级不是vip,上上为vip0,上上上级为vip2
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3());//上级不是vip,上上为vip0,上上上级为vip3
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip0,上上上级不是vip
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
									service.addaccount(sssuser,3,price*pension.getY_dai3());
								}
							}
						}else if(ssuser.getMember_grade()==1){//上级不是vip,上上级为vip1
							ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v1());
							service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v1());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip1,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级不是vip,上上为vip1,上上上级为vip0
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v1_1());//上级不是vip,上上为vip1,上上上级为vip1
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v1_1());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_1());//上级不是vip,上上为vip1,上上上级为vip2
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_1());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_1());//上级不是vip,上上为vip1,上上上级为vip3
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_1());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip1,上上上级不是vip
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
									service.addaccount(sssuser,3,price*pension.getY_dai3());
								}
							}
						}else if(ssuser.getMember_grade()==2){//上级不是vip,上上级为vip2
							ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v2());
							service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v2());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip3,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级不是vip,上上为vip3,上上上级为vip0
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级不是vip,上上为vip3,上上上级为vip1
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2_2());//上级不是vip,上上为vip3,上上上级为vip2
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2_2());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_2());//上级不是vip,上上为vip3,上上上级为vip3
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_2());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip3,上上上级不是vip
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
									service.addaccount(sssuser,3,price*pension.getY_dai3());
								}
							}
						}else if(ssuser.getMember_grade()==3){//上级不是vip,上上级为vip3
							ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2()+price*pension.getY_v3());
							service.addaccount(ssuser,3,price*pension.getY_dai2()+price*pension.getY_v3());
							if(sssuser!=null){
								if(sssuser.getMember()==1){//上级不是vip,上上为vip3,上上上级是vip
									if(sssuser.getMember_grade()==0){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级不是vip,上上为vip3,上上上级为vip0
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==1){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级不是vip,上上为vip3,上上上级为vip1
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==2){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级不是vip,上上为vip3,上上上级为vip2
										service.addaccount(sssuser,3,price*pension.getY_dai3());
									}else if(sssuser.getMember_grade()==3){
										sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3_3());//上级不是vip,上上为vip3,上上上级为vip3
										service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3_3());
									}
								}else if(sssuser.getMember()==0){//上级不是vip,上上为vip3,上上上级不是vip
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
									service.addaccount(sssuser,3,price*pension.getY_dai3());
								}
							}
						}
					}else if(ssuser.getMember()==0){//上级不是vip,上上级不是vip
						ssuser.setPension(ssuser.getPension()+price*pension.getY_dai2());
						service.addaccount(ssuser,3,price*pension.getY_dai2());
						if(sssuser!=null){
							if(sssuser.getMember()==1){//上级不是vip,上上级不是vip,上上上级是vip
								if(sssuser.getMember_grade()==0){
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());//上级不是vip,上上不是vip,上上上级为vip0
									service.addaccount(sssuser,3,price*pension.getY_dai3());
								}else if(sssuser.getMember_grade()==1){
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v1());//上级不是vip,上上不是vip,上上上级为vip1
									service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v1());
								}else if(sssuser.getMember_grade()==2){
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v2());//上级不是vip,上上不是vip,上上上级为vip2
									service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v2());
								}else if(sssuser.getMember_grade()==3){
									sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3()+price*pension.getY_v3());//上级不是vip,上上不是vip,上上上级为vip3
									service.addaccount(sssuser,3,price*pension.getY_dai3()+price*pension.getY_v3());
								}
							}else if(sssuser.getMember()==0){//上级不是vip,上上级不是vip,上上上级不是vip
								sssuser.setPension(sssuser.getPension()+price*pension.getY_dai3());
								service.addaccount(sssuser,3,price*pension.getY_dai3());
								//..............完结撒花\(^o^)/
							}
						}
					}
				}
			}
		}
	}
}
