<%@page import="javax.persistence.criteria.CriteriaBuilder.In"%>
<%@page import="com.huyu.entity.User"%>
<%@page import="com.huyu.entity.Userinfo"%>
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
		<link rel="stylesheet" href="/huyugo/memberCenter/css/right.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
	</head>
	<body>
	<%
	User user = (User)request.getAttribute("user");
	 %>
		<div class="publicBox2">
			
			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>兑换</span>
			</div>
			
			<div class="publicBox2">
				<div class="right2BoxList clearfix">
					<style>
						label {
	margin: 0 10px;
}
					</style>
					<div class="lab">
					<label><input type="radio" name="label" value="1">互余币兑换积分</label>
					<label><input type="radio" name="label" value="2">抵现金兑换积分</label>
					<label><input type="radio" name="label" value="3">积分兑换抵现金</label></div>
					<br>
					<br>
					<label>请输入兑换数量     <input type="text" name="money" placeholder="请输入兑换数量"></label>
					<button class="submit">提交</button>
					<script type="text/javascript">
						$(".submit").click(function(){
							var label = $(".lab").find("label input:checked").val();
							if(typeof(label) == "undefined"){
								alert("请选择兑换类型");
								return false;
							}
							var money = $("input[name=money]").val();
							if(money == ""){
								alert("请输入兑换数量");
								return false;
							}
							var reg = /^\+?[1-9][0-9]*$/;
							if(!reg.test(money)){
								alert("请正确输入数字！");
								return false;
							}
							if(label == "2" && money%5!=0){
								alert("5个抵现金兑换1个积分，请输入5的倍数");
								return false;
							}
							$.ajax({
								url:'/huyugo/user/exchange.do',
								type:'post',
								data:{money:money,label:label},
								success:function(res){
									alert(res);
									window.location.reload();
								}
							});
							return false;
						});
					</script>
				</div>
			</div>
		</div>
		
		<script>
			
		</script>
	</body>
</html>
