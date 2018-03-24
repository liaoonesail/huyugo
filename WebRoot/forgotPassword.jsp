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
		<title>找回密码</title>
		<meta name="keywords" content="互余，互余网、互余购、万州商城，直购商城，休闲娱乐" />
		<meta name="description" content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
		<link rel="stylesheet" href="/huyugo/css/Comm.css" />
		<link rel="stylesheet" href="/huyugo/css/register.css" />
		<link href="/huyugo/css/templet2.css" rel="stylesheet" type="text/css" />
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
		<h2>密码忘了？不要着急，通过以下方式即可帮您顺利的找回来！</h2>
	</div>
	<div class="login_Content">
		<form method="post" action="/huyugo/user/forgetPsd.do" enctype="application/x-www-form-urlencoded">
		<div class="login_CMobile_Complete login_CEmailPal">
			<p>您可以通过注册时所填写的手机号找回密码，请在下方输入您的帐号信息。</p>
			<dl>
				<dt></dt>
				<dd><font color="red">${errorMessage }</font></dd>
			</dl>
			<dl>
				<dt>注册手机号：</dt>
				<dd>
					<input datatype="m | e" sucmsg="帐号验证通过！" nullmsg="请填写帐号！" errormsg="手机号或邮箱！"  type="text" name="phone" class="login_CMobile_CodeW" value="">
					
				</dd>
				<dd>
					<div style="margin:7px 0 0 10px;" class="Validform_checktip"><font style="color:red;">${phoneError }</font></div>
				</dd>
			</dl>
			<dl>
				<dt>验证码：</dt>
				<dd><input  type="text" name="txtRegSN" class="login_input_text" value="" maxlength="6" style="width:60px;">
					<span>
						<img  src="/huyugo/pcrimg.do"  alt="看不清？换一张" onclick="changeCode();" id="pcrimg">
						
					</span>
				</dd>
			</dl>
			<input type="hidden" name="type" value="2" />
			<input type="submit" name="submit"  class="login_Email_but"  value="提交" />			
		</div> 
		
		</form>
		<div class="login_Explain">
			<p>1.您若忘记注册时所用的手机号或邮箱建议您重新注册账号， <a href="/huyugo/user/reg.do" class="blue Fb">立即注册</a></p>
			<p>2.若有任何疑问或需要帮助请您进入帮助中心，也可以点击在线客服进行咨询</p>
		</div>
	</div>
</div>

		<jsp:include page="footer.jsp"></jsp:include>
			
			<!--主体部分-->
		</div>
		<script>
			function changeCode(){
				$("#pcrimg").attr("src","/huyugo/pcrimg.do");
			}

		</script>
	</body>

</html>