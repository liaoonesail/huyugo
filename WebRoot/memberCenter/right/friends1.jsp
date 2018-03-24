<%@page import="com.huyu.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<link href="/huyugo/img/f.png" rel="shortcut icon" type="image/x-icon"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta name="keywords" content="互余，互余网、互余购、万州商城，直购商城，休闲娱乐" />
		<meta name="description" content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
		<title>互余购-综合网购商城</title>
		<link rel="stylesheet" href="/huyugo/memberCenter/css/address.css" />
		<link rel="stylesheet" href="/huyugo/memberCenter/css/integralBox.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		<style>
			.success-invitation {
				padding: 7px 10px 12px 21px;
				border: 1px solid #F5F5F5;
				background: #FCFCFC;
				overflow: hidden;
				zoom: 1;
			}
			
			.gray02 {
				color: #999;
			}
			
			.success-invitation p {
				height: 28px;
			}
			
			.success-invitation span {
				display: inline-block;
				position: relative;
				margin-right: 5px;
				float: left;
			}
			
			.d_clip_copy {
				font-size: 14px;
				color: #000;
			}
			
			#d_clip_container {
				top: -45px;
				left: 508px;
			}
			
			#d_clip_container {
				margin: 0;
				width: 65px;
				height: 22px;
				text-align: center;
			}
			
			.bluebut {
				background: #49b8ff;
				border: 1px solid #1ba3fa;
				color: #fff;
			}
			
			.orangebut,
			.bluebut,
			.graybut,
			.cancelBtn,
			.greenbut {
				display: inline-block;
				font-size: 12px;
				border-radius: 2px;
				text-align: center;
				padding: 0 19px;
				cursor: pointer;
			}
			
			.blue {
				color: #666;
			}
			
			.get-tips {
				border: 1px solid #EED7D9;
				padding: 7px 0 6px 12px;
				background: #F0EBEC;
				margin: 15px auto;
			}
			
			.orange {
				color: #db3752;
			}
		</style>
	</head>

	<body>
		<%
		String curpage = (String)request.getAttribute("curpage");
		int curr = Integer.parseInt(curpage);
		String i = request.getAttribute("i")+"";
		Map<String, Object> userMap = (Map<String, Object>)request.getAttribute("userList");
		/* if((List<User>)userMap.get("list") != null){
			i = (List<User>)userMap.get("list").size();
		} */
		User user_ = (User)session.getAttribute("user");
		 %>
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>邀请好友</span>

			</div>
			<div id="divInvited" class="success-invitation gray02">
				<p>复制链接邀请好友，消费变财富，分享即创业！</p>
				<span>
					<b></b>
					<input id="txtInfo" value="分享即创业、推广变财富、互余购为消费者省钱、为互联网创业者导航、让商家销量利润倍增。【互余购省钱】：每天签到送10元，活动天天有、惊喜不间断，满额送现金红包、全场代金券、全场立减、全场折扣、好礼免费兑不停。【互余购创业】：分享佣金最高8%、招商佣金最高15% 一次推广永久享受。【互余购商家】：平台全力扶持，活动多，费用少，一次入驻终身享受。网上购物、创业、开店就到互余购商城，就是这么任性哦，赶快点击注册详细了解吧！ http://www.huyugo.com/user/reg.do?superId=<%=user_.getId() %>"  style="width:500px;height:20px; " disabled="disabled"  onpaste="return false" type="text">
					
				</span>
				<div class="d_clip_copy"></div>
				<div id="d_clip_container" style="position:relative;">
					<div id="d_clip_button" class="bluebut" style="text-align:center;margin-top:24px;height: 22px;line-height: 21px;">复制</div>
					<div style="position: absolute; left: 1px; top: 24px; width: 64px; height: 20px; z-index: 99;"></div>
				</div>
			</div>
			<div id="divInviteInfo" class="get-tips gray01" style="">
				成功邀请 <span class="orange"><%=i %></span>
			</div>
			
			<table width="100%" style="border-left: 1px solid #F5F5F5;border-right: 1px solid #F5F5F5;">
				<tr class="titleTable">
					<th>用户名</th>
					<th>时间</th>
					<th>会员等级</th>
					<th>是否VIP</th>
				</tr>
				<%
	if((List<User>)userMap.get("list") != null){
					
					for(User user : (List<User>)userMap.get("list")){
						String phone = user.getPhone();
						int dengji = user.getMember_grade();
						int member = user.getMember();
						String djstr = "普通会员";
						String sfVIP = "否";
						if(member == 1){
							sfVIP = "是";
							if(dengji == 0){
									djstr = "V0";
							}else if(dengji == 1){
								djstr = "V1";
							}else if(dengji == 2){
								djstr = "V2";
							}else if(dengji == 3){
								djstr = "V3";
							}else{
								djstr = "V0";
							}
						}
						%>
						<tr class="Trdf">
							<td><%=phone.substring(0, 3) %>****<%=phone.substring(phone.length()-4, phone.length()-1) %></td>
							<td><%=user.getReg_time() %></td>
							<td style="color: red;"><%=djstr %></td>
							<td><%=sfVIP %></td>
						</tr>
						<%
					}
					 
				}else{
					%><tr class="Trdf"><td colspan="4">暂无邀请成员</td></tr><%
				}
				%>
				<tr>
				<td></td>
				<td><a href="/huyugo/friend/showFriendView.do?curpage=<% if (curr == 1) %><%=curpage %><% else %><%=curr-1%>">上一页</a></td>
				<td><a href="/huyugo/friend/showFriendView.do?curpage=<% if (curr == (Integer)userMap.get("countPage")) %><%=curr %><%else %><%=curr+1%>">下一页</a></td>
				<td></td>
			</tr>
			</table>
		</div>
		<script type="text/javascript" src="/huyugo/memberCenter/js/ZeroClipboard.js" ></script>
		<script>
			var clip = null;

			function copy(id) {
				return document.getElementById(id);
			};

			function initx() {
				clip = new ZeroClipboard.Client();
				clip.setHandCursor(true);
				ZeroClipboard.setMoviePath("/huyugo/memberCenter/js/ZeroClipboard.swf");
				clip.addEventListener('mouseOver', function(client) {
					clip.setText(copy('txtInfo').value);
				});
				clip.addEventListener('complete', function(client, text) {
					alert("邀请复制成功");
				});
				clip.glue('d_clip_button', 'd_clip_container');
			};
			$(function() {
				initx();
			});

		</script>

	</body>

</html>