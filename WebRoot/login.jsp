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
		<link rel="stylesheet" type="text/css" href="/huyugo/css/Comm.css" />
		<link rel="stylesheet" type="text/css" href="/huyugo/css/login.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		<link href="/huyugo/css/templet2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="/huyugo/js/jquery.Validform.min.js"></script>
	</head>

	<body style="background: #fafafa;">

		<div class="login">

			<div class="login_top">
				<h1 style="width:250px;">
					<a rel="nofollow" href="/huyugo/index.do">
						<img src="/huyugo/img/logo/logo.jpg">
						
					</a>
				</h1>
				<p>
					<a rel="nofollow" href="/huyugo/index.do" class="back_home">返回首页</a>
					<a href="" target="_blank" class="help">帮助中心</a>
				</p>
			</div>
			<div class="login_bg">
				<div id="loadingPicBlock" class="login_banner">
					<img src="/huyugo/img/login.jpg" width="542" height="360">

				</div>
				<div class="login_box" id="LoginForm">
					<form class="registerform" method="post" action="/huyugo/user/login.do" id="registerform">
						<h3>互余购 - 用户登录</h3>
						<ul>
							<li class="click">
								<span>账号：</span>
								<input class="text_password" name="username" type="text" datatype="m | e" nullmsg="请填写帐号！" errormsg="手机号码！" />
							</li>
							<li class="ts">
								<div class="Validform_checktip">手机号码！</div>
							</li>
							<li>
								<span>密码：</span>
								<input class="text_password" name="password" type="password" datatype="*6-20" nullmsg="请设置密码！" errormsg="密码范围在6~20位之间！" />
								<span class="fog">
									<a href="/huyugo/user/forgetPsd.do">忘记密码？</a>
								</span>
							</li>
							<li class="ts" id="pwd_ts">
								<div class="Validform_checktip"><font style="color:red;">${errorMessage }</font></div>
							</li>
							<li>
								<span>验证码：</span>
								<input class="text_verify" name="verify" type="text" datatype="*4-6" nullmsg="请输入验证码！" errormsg="请输入正确的验证码！" />
								<span class="fog" style="cursor: pointer;">
			                    	<img  src="/huyugo/pcrimg.do" width="90" height="30" id="pcrimg" onclick="changeCode();" />
			                    </span>
							</li>
							<li class="ts" id="pwd_ts">
								<div class="Validform_checktip">&nbsp;&nbsp; 请输入验证码</div>
							</li>

							<li class="end">
								<span><input name="submit" type="submit" value="登录" class="login_init" ></span>
								<span>
			                    	<a id="hylinkRegisterPage" style="padding:0px 10px;" tabindex="4" class="reg" href="/huyugo/user/reg.do">立即注册</a>
			                    </span>
							</li>

						</ul>
						<input type="hidden" name="type" value="2" /> 
					</form>
				</div>
			</div>

			<!--关于我们  |  购物指南  |  支付方式  |  配送说明  |  售后服务  |  联系方式  |  投诉建议  |  诚聘英才  |  友情链接-->
			<!-- <div class="aboutus">
				<div class="publicBox aboutusList clearfix">
					<ul>
						    <li><a href="/huyugo/index.do">首页</a>|</li>
							<li><a href="">关于我们</a>|</li>
							<li><a href="">购物指南</a>|</li>
							<li><a href="">支付方式</a>|</li>
							<li><a href="">配送说明</a>|</li>
							<li><a href="">售后服务</a>|</li>
							<li><a href="">联系方式</a>|</li>
							<li><a href="">投诉建议</a>|</li>
							<li><a href="">诚聘英才</a>|</li>
							<li><a href="">友情链接</a></li>
						</ul>
				</div>
			</div> -->
			<!--关于我们  |  购物指南  |  支付方式  |  配送说明  |  售后服务  |  联系方式  |  投诉建议  |  诚聘英才  |  友情链接-->
			<!-- <div class="publicBox information">
				<div style="margin:0 auto; padding:0px 0;text-align: center;">						
				 		<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=50010102000464" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="" style="float:left;"><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;"><img src="/huyugo/img/beian.png" style="vertical-align: bottom;">重庆互余电子商务有限责任公司 渝公网安备 50010102000464号</p></a>
				 	</div>
				<p>渝ICP备15006691号-2 全国订购及服务热线：023-58300357</p>
			</div> -->
			
			<div class="aboutus">
					<div class="publicBox aboutusList clearfix">
						<ul id="link">
							<!-- <li><a href="http://www.huyusc.com">峰氏头条</a>|</li>
							<li><a href="/huyugo/help/showHelp.do?status=1">互余购介绍</a>|</li>
							<li><a href="/huyugo/help/showHelp.do?status=9">隐私声明</a></li> -->
							<script>
							    /*友链*/
							    $.post("/huyugo/link/linkList.do",{},function(res){
							    	//console.log(res);
							    	var prefixHtml = "";
							    	//var suffixHtml = "<li><a href='http://www.huyusc.com'>峰氏头条</a>|</li><li><a href='/huyugo/help/showHelp.do?status=1'>互余购介绍</a>|</li><li><a href='/huyugo/help/showHelp.do?status=9'>隐私声明</a></li>";
							    	var suffixHtml = "";
							    	$(res).each(function(index,obj){
							    		prefixHtml += "<li><a target='_blank' href='http://"+obj.linkurl+"'>"+obj.linkname+"</a>  </li>";
							    	})
							    	$("#link").html(prefixHtml+suffixHtml);
							    },"json");
							</script>
						</ul>
						
					</div>
				</div>
				<div class="publicBox information">
					<div style="margin:0 auto; padding:0px 0;text-align: center;">						
				 		<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=50010102000464" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;"><img src="/huyugo/img/beian.png" style="vertical-align: bottom;"/>重庆互余电子商务有限责任公司 渝公网安备 50010102000464号</p></a>
				 	</div>
					<p>渝ICP备15006691号-2 全国订购及服务热线：023-58300357</p>
				</div>
		</div>
		<!--login 结束-->
		<script type="text/javascript">
			function changeCode(){
				$("#pcrimg").attr("src","/huyugo/pcrimg.do");
			}
			$(function() {
				var demo = $(".registerform").Validform({
					tiptype: 2,
				});
			})
			//$(function(){
			//	$("#hylinkRegisterPage").onclick(function(){
			//		$("#registerform").submit();
			//	});
			//})
		</script>
	</body>

</html>








<!-- 三级分销   三级 -->