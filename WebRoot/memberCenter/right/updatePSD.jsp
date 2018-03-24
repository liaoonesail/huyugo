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
		<link rel="stylesheet" href="/huyugo/memberCenter/css/personal.css" />
		<link rel="stylesheet" href="/huyugo/css/templet2.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
	</head>

	<body>
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>个人中心</span>
			</div>
			<div class="personalBox clearfix">
				<div class="clearfix"></div>
				
				<div class="personalBoxTouxiaNg login_ConInput" style="display:block">
					<div>${success }${error }</div>
					<div class="login_ConInput login_Ptxtf14">
						<form class="registerform" method="post" action="/huyugo/person/updatePSD.do">
							<dl class="touAdnList clearfix">
								<dt class="pull-left dfhg2">原密码：</dt>
								<dd class="pull-left"><input name="userpassword" plugin="passwordStrength" datatype="*6-20" nullmsg="密码不能为空！" errormsg="密码范围在6~20位之间！" type="password" class="login_input_text" maxlength="20"></dd>
								<dd class="pull-left">
									<div class="Validform_checktip">请输入密码</div>
								</dd>
							</dl>
							<dl class="touAdnList clearfix">
								<dt class="pull-left dfhg2">新密码：</dt>
								<dd class="pull-left"><input name="userpassword1" plugin="passwordStrength" datatype="*6-20" nullmsg="密码不能为空！" errormsg="密码范围在6~20位之间！" type="password" class="login_input_text" maxlength="20"></dd>
								<dd class="pull-left">
									<div class="Validform_checktip">再次输入新密码</div>
								</dd>
							</dl>

							<dl class="touAdnList clearfix">
								<dt class="pull-left">确认密码：</dt>
								<dd class="pull-left"><input name="userpassword2" recheck="userpassword" sucmsg="" datatype="*6-20" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！" id="NewPassAgain" type="password" class="login_input_text" maxlength="20"></dd>
								<dd class="pull-left">
									<div class="Validform_checktip">请再输入一次密码！</div>
								</dd>
							</dl>
							<input type="hidden" name="status" value="update" />
							<div class="login_Membut">
								<input class="Okbtsdg" type="submit" name="submit" class="login_Email_but" value="确认修改"  style="outline: none;border: none;" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script>
			
			/* 
			$(function() {
				var demo = $(".registerform").Validform({
					tiptype: 2,
					usePlugin: {
						passwordstrength: {
							minLen: 6,
							maxLen: 20
						}
					}
				});
			}); */
		</script>
	</body>

</html>