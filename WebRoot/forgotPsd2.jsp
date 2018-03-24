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
		<title>找回密码</title>
		<meta name="keywords" content="互余，互余网、互余购、万州商城，直购商城，休闲娱乐" />
		<meta name="description" content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
		<link rel="stylesheet" href="/huyugo/css/swiper.css" />
		<link rel="stylesheet" href="/huyugo/css/Comm.css" />
		<link rel="stylesheet" href="/huyugo/css/templet2.css" />
		<link rel="stylesheet" href="/huyugo/css/header.css" />
		<link rel="stylesheet" href="/huyugo/css/bass.css" />
		<link rel="stylesheet" href="/huyugo/css/register.css" />
		<link rel="stylesheet" href="/huyugo/css/footer.css" />
		<link href="/huyugo/css/templet2.css" rel="stylesheet" type="text/css" />

		<!--引入jquery库的jquery-1.10.2.js-->
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="/huyugo/js/jquery.Validform.min.js"></script>
		<!--引入的swiper.min.js特效组件-->
		<script type="text/javascript" src="/huyugo/js/swiper.min.js"></script>
		<style>
			.swiper-pagination-bullet {
				width: 20px;
				height: 20px;
				text-align: center;
				line-height: 20px;
				font-size: 12px;
				color: #fff;
				opacity: 1;
				background: rgba(0, 0, 0, 0.8);
			}
			
			.swiper-pagination-bullet-active {
				color: #fff;
				background: #db3752;
			}
		</style>
		<script>
		var num1 = -1;
		</script>
	</head>

	<body>
		<!--主体部分-->
		<div class="mBodyContent">
			
			<jsp:include page="header.jsp"></jsp:include>
			
			<div class="login_layout">
				<div class="login_title">
					<h2>手机找回密码验证</h2>
				</div>
				<div class="login_Content">
					
					<form action="/huyugo/user/forgetPsd.do" enctype="" method="post">
						<div class="login_CMobile_Complete">
							<p>互余购-综合网购商城、要网购就来互余购，消费变财富、分享即创业、正品低价、品质保障、享受不一样的购物乐趣已向您的手机 <span class="orange">${user_.phone }</span> 免费发送了一条验证短信，请查看您的手机短信！</p>
							<dl>
								<dt></dt>
								<dd></dd>
							</dl>
							<dl>
								<dt></dt>
								<dd><font color="red">${errorMessage }</font></dd>
							</dl>
							<dl>
								<dt style="width:200px">请输入手机短信收到的验证码：</dt>
								<dd><input name="checkcode" class="login_CMobile_Code" type="text"></dd>
								<dd></dd>
							</dl>
							<input type="hidden" name="type" value="3" />
							<input type="button" onclick="upnex();" class="login_Email_but" value="上一步" />
							<input type="submit" name="submit" href="javascript:void(0);" class="login_Email_but" value="提交验证" />
						</div>
						
					</form>
					<div class="login_Explain">
						<h2>没收到验证短信？</h2>
						<p>1.请查看手机的垃圾短信，信息有可能被误认为是垃圾信息。</p>
						<p>2.如果在2分钟后仍未收到验证短信，请点击<button id="retrySend" onclick="javascript:sendmobile();" disabled="1" class="login_SendoutbutClick">重新发送120</button> </p>
						<p>3.如果手机号码不小心输错了或者想换个号码？请点击
							<a class="blue Fb" href="/huyugo/user/reg.do">重新注册</a>
						</p>
					</div>

				</div>
			</div>
			<form id="formgoto" method="post" action="/huyugo/user/forgetPsd.do">
				<input type="hidden" name="type" value="2" />
			</form>
			<jsp:include page="footer.jsp"></jsp:include>

			<!--主体部分-->
		</div>
		<script>
			var i = 120;
			var senda = document.getElementById('retrySend');
			setInterval(function() {
				if(i > 0) {
					senda.innerHTML = '重新发送' + i;
					i--;
				} else {
					senda.innerHTML = '重新发送';
					senda.disabled = 0;
				}
			}, 1000);

			function sendmobile() {
				window.location.href = "fasong.html";
			}
			
			function upnex(){
				$("#formgoto").submit();
			}
		</script>
	</body>

</html>