package com.huyu.wechat.menu;

import com.huyu.wechat.entity.AccessToken;


public class Access_token {
	public void createMenu(AccessToken accessToken){
	//调用UTI执行创建菜单的动作
	int status = MenuUtil.createMenu(MenuUtil.getMenu(),accessToken);
	if(status==0){
	System.out.println("菜单创建成功！");
	}else{
	System.out.println("菜单创建失败！");
	}
	}

}

