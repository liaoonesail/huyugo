package com.huyu.wechat.menu;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import net.sf.json.JSONObject;

import com.huyu.wechat.entity.AccessToken;
import com.huyu.wechat.menu.been.Button;
import com.huyu.wechat.menu.been.ComplexButton;
import com.huyu.wechat.menu.been.Menu;
import com.huyu.wechat.menu.been.TeletextButton;
import com.huyu.wechat.menu.been.ViewButton;




public class MenuUtil {
	/**
	* 封装菜单数据
	 * @param qRC 
	* */
	public static Menu getMenu(){
	//首先创建二级菜单
	
	TeletextButton cb_1_1=new TeletextButton();
	cb_1_1.setType("media_id");
	cb_1_1.setName("游戏介绍");
	cb_1_1.setMedia_id("lkXP6aQUPelNoZfMidVOnWMtD8dJOzgPVlioL8Nw0E8");


	TeletextButton cb_1_2 =new TeletextButton();
	cb_1_2.setType("media_id");
	cb_1_2.setName("玩法介绍");
	cb_1_2.setMedia_id("lkXP6aQUPelNoZfMidVOnYGv3g8Yh1UaR0uobnOcVmA");

	
	TeletextButton cb_1_3=new TeletextButton();
	cb_1_3.setType("media_id");
	cb_1_3.setName("客服中心");
	cb_1_3.setMedia_id("lkXP6aQUPelNoZfMidVOnY1NAL_1rugdASrXlwvlIPs");
	
	TeletextButton cb_1_4=new TeletextButton();
	cb_1_4.setType("media_id");
	cb_1_4.setName("游戏下载");
	cb_1_4.setMedia_id("lkXP6aQUPelNoZfMidVOnYER7QuXkSb-79Z08Sx3tuI");
	//创建第一个一级菜单
	ComplexButton cx_1 = new ComplexButton();
	cx_1.setName("游戏中心");
	cx_1.setSub_button(new Button[]{cb_1_1,cb_1_2,cb_1_3,cb_1_4});


	//继续创建二级菜单
	ViewButton cb_2_1= new ViewButton();
	cb_2_1.setName("充值");
	cb_2_1.setType("view");
	//需要使用网页授权获取微信用户的信息
	cb_2_1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx81eaf2b5a83df20b&redirect_uri=http://hkdoudoujob.cn/WeChat/user/rechageweb.do&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
	
	ViewButton cb_2_2 = new ViewButton();
	cb_2_2.setName("代理注册");
	cb_2_2.setType("view");
	//需要使用网页授权获取微信用户的信息
	cb_2_2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx81eaf2b5a83df20b&redirect_uri=http://hkdoudoujob.cn/WeChat/user/regagentweb.do&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");

	ViewButton cb_2_3 = new ViewButton();
	cb_2_3.setName("赠送金豆");
	cb_2_3.setType("view");
	//需要使用网页授权获取微信用户的信息
	cb_2_3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx81eaf2b5a83df20b&redirect_uri=http://hkdoudoujob.cn/WeChat/user/giftweb.do&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");

	//创建第二个一级菜单
	ComplexButton cx_2 = new ComplexButton();
	cx_2.setName("充值中心");
	cx_2.setSub_button(new Button[]{cb_2_1,cb_2_2,cb_2_3});
	//创建第三个一级菜单
	
	TeletextButton cb_3_1=new TeletextButton();
	cb_3_1.setType("media_id");
	cb_3_1.setName("推荐有礼");
	cb_3_1.setMedia_id("lkXP6aQUPelNoZfMidVOnR6ExD7WLR82vYU8iIf-LTc");
	
	ViewButton cb_3_2 =new ViewButton();
	cb_3_2.setName("代理开房");
	cb_3_2.setType("view");
	cb_3_2.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx81eaf2b5a83df20b&redirect_uri=http://hkdoudoujob.cn/WeChat/user/roomweb.do&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
	
	
	
	ViewButton cb_3_3 =new ViewButton();
	cb_3_3.setName("代理查看");
	cb_3_3.setType("view");
	cb_3_3.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx81eaf2b5a83df20b&redirect_uri=http://hkdoudoujob.cn/WeChat/user/Superior_subordinate_agent.do&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");
	
	ViewButton cb_3_4 =new ViewButton();
	cb_3_4.setName("我的二维码");
	cb_3_4.setType("view");
	cb_3_4.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx81eaf2b5a83df20b&redirect_uri=http://hkdoudoujob.cn/WeChat/user/qrcweb.do&response_type=code&scope=snsapi_userinfo&state=123#wechat_redirect");

	ComplexButton cx_3=new ComplexButton();
	cx_3.setName("管理中心");
	cx_3.setSub_button(new Button[]{cb_3_1,cb_3_2,cb_3_3,cb_3_4});
	//封装菜单数据
	Menu menu=new Menu();
	menu.setButton(new ComplexButton[]{cx_1,cx_2,cx_3});

	return menu;
	}


	/**
	* 创建自定义菜单(每天限制1000次)
	 * @param qRC 
	 * @param accessToken2 
	* */
	public static int createMenu(Menu menu, AccessToken accessToken){
	String jsonMenu=JSONObject.fromObject(menu).toString();

	int status=0;

	System.out.println("菜单："+jsonMenu);
	String path="https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+accessToken.getToken();
	try {
	URL url=new URL(path);
	HttpURLConnection http = (HttpURLConnection)url.openConnection();
	http.setDoOutput(true);
	http.setDoInput(true);
	http.setRequestMethod("POST");
	http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	http.connect();
	OutputStream os = http.getOutputStream();
	os.write(jsonMenu.getBytes("UTF-8"));
	os.close();

	InputStream is = http.getInputStream();
	int size = is.available();
	byte[] bt = new byte[size];
	is.read(bt);
	String message=new String(bt,"UTF-8");
	JSONObject jsonMsg = JSONObject.fromObject(message);
	status = Integer.parseInt(jsonMsg.getString("errcode"));
	System.out.println(jsonMsg.getString("errcode"));
	System.out.println(jsonMsg.getString("errmsg"));
	} catch (MalformedURLException e) {
	e.printStackTrace();
	} catch (IOException e) {
	e.printStackTrace();
	}
	return status;
	}
	}


	
	
