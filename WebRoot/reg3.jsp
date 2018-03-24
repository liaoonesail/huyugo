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
		<link rel="stylesheet" href="/huyugo/css/Comm.css" />
		<link rel="stylesheet" href="/huyugo/css/templet2.css" />
		<link rel="stylesheet" href="/huyugo/css/header.css" />
		<link rel="stylesheet" href="/huyugo/css/bass.css" />
		<link rel="stylesheet" href="/huyugo/css/register.css" />
		<link href="/huyugo/css/templet2.css" rel="stylesheet" type="text/css" />

		<!--引入jquery库的jquery-1.10.2.js-->
		<style>
			.box {
				border: 5px solid #eee;
				width: 480px;
				background-color: #fff;
				margin: 160px auto;
				erflow: hidden;
			}
			
			.box-b {
				border: 1px solid #dfdbdb;
				width: 478px;
				erflow: hidden;
			}
			
			.box-title {
				background: #ca1b38;
				height: 30px;
				line-height: 33px;
				_line-height: 30px;
				font-size: 14px;
				color: #fff;
				padding: 0 10px;
			}
			
			.box-text {
				font: 12px/1.5 "微软雅黑", Arial, "宋体", Helvetica, sans-serif;
				font-size: 18px;
				color: #73787b;
				width: 438px;
				text-align: center;
				border: 0px solid #000;
				padding: 20px;
				height: auto;
				word-wrap: break-word;
			}
			
			.box-button {
				overflow: hidden;
				text-align: right;
			}
			
			.box-button a {
				display: inline-block;
				height: 25px;
				line-height: 23px;
				_line-height: 25px;
				text-align: center;
				font-family: "微软雅黑";
				font-size: 14px;
				text-decoration: none;
				padding: 0px 5px;
				margin: 0px 10px;
			}
			
			.a-1 {
				background-color: #eee;
				color: #666;
				border: 1px solid #dfdbdb;
			}
			
			.a-2 {
				background-color: #eee;
				color: #666;
				border: 1px solid #dfdbdb;
			}
		</style>
		<script>
		var num1 = -1;
		</script>
	</head>

	<body>
			
			<jsp:include page="header.jsp"></jsp:include>
			
			<div class="login_layout">    
	<div class="login_title">
		<h2>新用户注册</h2>
		<ul class="login_process">
			<li><b>1</b>填写注册信息</li>
			<li class="login_arrow"></li>
			<li><b>2</b>验证手机号码</li>
			<li class="login_arrow"></li>
			<li class="login_processCur"><b>3</b>完成注册</li>
		</ul>
		<span>已经是会员？<a id="hylinkLoginPage" class="blue Fb" href="/huyugo/user/login.do">登录</a></span>
	</div>
	<div class="box">
			<div class="box-b">
				<div class="box-title">消息提示</div>
				<div class="box-text">
					恭喜你注册成功! </div>
				<div class="box-button">
					<a class="a-2" href="javascript:;" onclick="locahost()">
						<font id="time" style="color:red;">3</font>秒后返回首页</a>
					<a class="a-1" href="/huyugo/index.do">返回首页</a>
				</div>
				<div style="height:10px; overflow:hidden; width:100%; clear:both"></div>
			</div>
		</div>
</div>

		<jsp:include page="footer.jsp"></jsp:include>
		
		<script>
			$(function() {
				$(function() {
					var demo = $(".login_ConInput").Validform({
						tiptype: 2,
						usePlugin: {
							passwordstrength: {
								minLen: 6,
								maxLen: 20,
								trigger: function(obj, error) {
									if(error) {
										obj.parent().next().find(".Validform_checktip").show();
										obj.parent().next().find(".passwordStrength").hide();
									} else {
										obj.parent().next().find(".Validform_checktip").hide();
										obj.parent().next().find(".passwordStrength").show();
									}
								}
							}
						}

					});
				})
			});
			//
			function locahost() {
				location.href = "/huyugo/index.do";
			}

			function closeWindow() {
				window.open('', '_self', '');
				window.close();
			}

			var i = 3;
			if(i != 0) {
				window.close_id = setInterval(function() {
					if(i > 0) {
						document.getElementById('time').innerHTML = i;
						i = i - 1;
					} else {
						locahost();
						clearInterval(window.close_id);
					}
				}, 1000);
			}
		</script>
	</body>

</html>