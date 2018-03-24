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
		<script>
		var num1 = -1;
		</script>
	</head>

	<body>
	
	<%
	User user_ = (User)session.getAttribute("user_");
	if(user_ == null){
		%><script>window.location.href = "/huyugo/user/reg.do";</script><%
	}
	 %>
	
			<jsp:include page="header.jsp"></jsp:include>
			
			<div class="login_layout">    
	<div class="login_title">
		<h2>新用户注册</h2>
		<ul class="login_process">
			<li><b>1</b>填写注册信息</li>
			<li class="login_arrow"></li>
			<li class="login_processCur"><b>2</b>验证手机号码</li>
			<li class="login_arrow"></li>
			<li><b>3</b>完成注册</li>
		</ul>
		<span>已经是会员？<a id="hylinkLoginPage" class="blue Fb" href="/huyugo/user/login.do">登录</a></span>
	</div>
	<div class="login_Content">
		<form action="/huyugo/user/reg.do" enctype="application/x-www-form-urlencoded" method="post">
		<div class="login_CMobile_Complete">
			<p>互余购-综合网购商城、要网购就来互余购，消费变财富、分享即创业、正品低价、品质保障、享受不一样的购物乐趣已向您的手机 <span class="orange">${user_.phone }</span> 免费发送了一条验证短信，请查看您的手机短信！</p>
			<dl>
				<dt style="width:200px">请输入手机短信收到的验证码：</dt>
				<dd><input  name="smsCode" class="login_CMobile_Code" type="text"></dd>
				<dd><font color="red">${smsCodeError }</font></dd>
			</dl>
			<input type="submit" name="submit"  href="javascript:void(0);" class="login_Email_but" value="提交验证">
		</div>
		<input type="hidden" name="type" value="3" />
        </form>
		<div class="login_Explain">
			<h2>没收到验证短信？</h2>
			<p>1.请查看手机的垃圾短信，信息有可能被误认为是垃圾信息。</p>
			<p>2.如果在2分钟后仍未收到验证短信，请点击<button id="retrySend" onclick="javascript:sendmobile();" disabled="1" class="login_SendoutbutClick">重新发送120</button> </p>
			<p>3.如果手机号码不小心输错了或者想换个号码？请点击 <a  class="blue Fb" href="/huyugo/user/reg.do">重新注册</a></p>
		</div>
		
		
	</div>
</div>


		<jsp:include page="footer.jsp"></jsp:include>

		<script>
			//
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
			var i = 120;
			var senda=document.getElementById('retrySend');
			setInterval(function(){if(i>0){
				senda.innerHTML = '重新发送'+i;
				i--;
			}else{
				senda.innerHTML = '重新发送';
				senda.disabled=0;
			}
			},1000);
	
			function sendmobile(){
				$.ajax({
					url:'/huyugo/user/reSendCode.do',
					type:'post',
					data:{phone:$("span.orange").text()},
					success:function(res){
						$("#retrySend").html("重新发送120");
						var i = 120;
			            var senda=document.getElementById('retrySend');
			            setInterval(function(){if(i>0){
			                senda.innerHTML = '重新发送'+i;
			                i--;
			            }else{
			                senda.innerHTML = '重新发送';
			                senda.disabled=0;
			            }
			            },1000);
					}
				});
			}

		</script>
	</body>

</html>