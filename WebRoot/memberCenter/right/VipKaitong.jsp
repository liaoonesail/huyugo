<%@page import="com.huyu.entity.User"%>
<%@page import="com.huyu.entity.Vip_order"%>
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
			.zhuce_middle_p1{
				font-size: 14px;
				color: #999;
				margin: 20px 20px 0px 20px;
				line-height: 35px;
			}
			
			.nameLout2{
				font-size: 14px;
				margin: 0px 20px 0px 20px;
				line-height: 35px;
			}
			.huiyuanBtns{
				display: block;
				width: 130px;
				height: 35px;
				line-height: 35px;
				text-align: center;
				font-size: 16px;
				color: #fff;
				border-radius: 6px;
				background: #7418C6;
				margin: 10px 20px ;
				cursor: pointer;
			}
			.tixiangLa{
				color: #323232;
				font-size: 14px;
				text-align: justify;
				line-height: 35px;
				margin: 20px 20px 0px 20px;
			}
			.erwmKLog{
				width: 145px;
				height: 145px;
				margin: 5px 20px;
			}
			.erwmKLog img{
				display: block;
				width: 100%;
				height: 100%;
			}
			.fukuanOk{
				display: none;
			}
		</style>
		<script type="text/javascript">
		function weixinPay(){
			$(".huiyuanon").css("display","none");
         	$(".fukuanOk").css("display","block");
			$.ajax({
						url:"/huyugo/vip/getCodeUrl.do",
						data:{
						      orderNum:$("#hiddenCode").val()
						},  
						type:'post',  
						cache:false,  
						dataType:'html',  
						success:function(data) {
							if(data == "notLogin"){
								alert("请重新登录");
							}else{
								var url = "/huyugo/wechatpay/pc_pay.do?" + data;
								var xhr=new XMLHttpRequest();
								 xhr.open("GET",url,true);
								 xhr.responseType="blob";
								 xhr.onload=function(){
									if(this.status==200){
										var blob=this.response;
											$("#img").attr("src",window.URL.createObjectURL(blob));
									} 
								 };xhr.send();
							}
					    }
					});
		}
		
		function yuePay(){
			
			$.ajax({
						url:"/huyugo/vip/accountPay.do",
						data:{
						      orderNum:$("#hiddenCode").val()
						},  
						type:'post',  
						cache:false,  
						dataType:'html',  
						success:function(data) {
							if(data == "notLogin"){
								alert("请重新登录");
							}else if(data == "ok"){
								alert("开通会员成功");
								window.location.href = "/huyugo/person/home.do";
							}else if(data == "notFull"){
								alert("余额不足");
							}
					    }
					});
		}
		
		function kaitong(account){
			if(parseFloat(account) >= 500){
				yuePay();
			}else{
				weixinPay();
			}
		}
		</script>
	</head>

	<body>
		<%
		Vip_order vipOrder = (Vip_order)request.getAttribute("order");
		User user = (User)session.getAttribute("user");
		 %>
		<div class="publicBox2">
			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>开通互余购VIP会员</span>
			</div>
			<div class="huiyuanon">
				<p class="zhuce_middle_p1">互余购VIP会员是互余为用户提供的一项增值服务，为广大创业者提供的创业平台。</p>
				<div class="nameLout2">
					服务费用：<font color="red">500</font>元
				</div>
				<div class="nameLout2">
					账户余额：<font color="red"><%=user.getAccount() %></font>元
				</div>
				<div class="nameLout2">
					还需支付：<font color="red"><%if((500 - user.getAccount()) < 0){%>0.0<%}else{%><%=500 - user.getAccount() %><%} %></font>元
				</div>
				<div class="huiyuanBtns" onclick="kaitong('<%=user.getAccount() %>');">确认付款</div>
				<p class="zhuce_middle_p1">点击"确认付款"，即表示您同意并愿意遵守<a href="/huyugo/help/getid.do?type=1&id=38" style="color: #47176D;text-decoration: underline;">互余购VIP会员协议</a></p>
				<input type="hidden" id="hiddenCode" value="<%=vipOrder.getOrder_num() %>" />
			</div>
			
			<!--付款扫码-->
			<div class="fukuanOk">
				<p class="tixiangLa">请使用微信支付，支付完成前，请勿关闭页面！</p>
				<div class="erwmKLog">
					<img id="img" />
				</div>
			</div>
			<!--付款扫码-->
		</div>
		
         <script>
         	/* $(".huiyuanBtns").click(function(){
         		$(".huiyuanon").css("display","none");
         		$(".fukuanOk").css("display","block");
         	}) */
         </script>
	</body>

</html>