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
			<img src="/huyugo/memberCenter/jiantou/14.png" /> <span>转账</span>
		</div>

		<div class="publicBox2">
			<div class="right2BoxList clearfix">
				<style>
label {
	margin: 0 10px;
}
</style>
				<div class="lab">
					<label>当前可操作账户余额 余额：<span style="color:red;"
						class="account"><%=user.getAccount() %></span> 互余币：<span
						class="huyubi" style="color:red;"><%=user.getHuyubi() %></span>
						抵现金：<span class="dixianjin" style="color:red;"><%=user.getDixianjin() %></span>
						积分：<span class="jifen" style="color:red;"><%=user.getIntegral() %></span>
					</label> <br> <br> <label>请选择转账类型</label> <label><input
						type="radio" name="type" value="1">余额</label> <label><input
						type="radio" name="type" value="2">积分</label> <label><input
						type="radio" name="type" value="3">抵现金</label> <label><input
						type="radio" name="type" value="4">互余币</label>
				</div>
				<br> <label>请输入对方手机号 <input type="text" name="phone"
					placeholder="请输入对方手机号">
				</label><br>
				<br> <label>请输入转账金额 <input type="text" name="money"
					placeholder="请输入转账金额">
				</label>
				<button class="submit">提交</button>
				<script type="text/javascript">
						$(".submit").click(function(){
							//转账类型 （1余额，2积分，3抵现金，4互余币）
							var account = $("account").text();
							var huyubi = $("huyubi").text();
							var dixianjin = $("dixianjin").text();
							var jifen = $("jifen").text();
							var label = $(".lab").find("label input:checked").val();
							if(typeof(label) == "undefined"){
								alert("请选择转账类型");
								return false;
							}
							var phone = $("input[name=phone]").val();
							var money = $("input[name=money]").val();
							var phoneReg = /^1[3|4|5|8][0-9]\d{4,8}$/;
							var numberReg = /^\+?[1-9][0-9]*$/;
							if(!phoneReg.test(phone)){
								alert("请输入正确的手机号码");
								return false;
							}
							if(!numberReg.test(money)){
								alert("请输入正确的转账金额");
								return false;
							}
							if(money<0){
								alert("请正确输入余额");
								return false;
							}
							money = Number(money);
							label = Number(label);
							var msg = "";
							switch(label){
								case 1:
									msg = "余额";
									break;
								case 2:
									msg = "积分";
									break;
								case 3:
									msg = "抵现金";
									break;
								case 4:
									msg = "互余币";
									break;
								default:
									msg = "";
									break;
							}
							$.ajax({
								url:'/huyugo/transfer/add.do',type:'post',data:{phone:phone,type:label,money:money},success:function(res){
									switch(res){
										case "0":
											alert("您不能给自己转账");
											break;
										case "1":
											alert("您输入的手机号码暂未注册本平台");
											break;
										case "2":
											alert("账户余额不足");
											break;
										case "3":
											alert("积分不足");
											break;
										case "4":
											alert("抵现金不足");
											break;
										case "5":
											alert("互余币不足");
											break;
										case "6":
											alert("转账成功！");
											window.location.reload();
											break;
									}
								}
							});
							msg = "";
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
