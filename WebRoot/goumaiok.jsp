<%@page import="com.huyu.entity.Order"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>

	<head>
		<link href="/huyugo/img/f.png" rel="shortcut icon" type="image/x-icon"/>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="keywords" content="互余，互余网、互余购、万州商城，直购商城，休闲娱乐" />
		<meta name="description" content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
		<title>互余购-综合网购商城</title>
		<link rel="stylesheet" type="text/css" href="/huyugo/css/Comm.css" />
		<link rel="stylesheet" href="/huyugo/css/bass.css" />
		<link rel="stylesheet" type="text/css" href="/huyugo/css/CartList.css" />
		<link rel="stylesheet" href="/huyugo/css/myCart.css" />
		<link rel="stylesheet" href="/huyugo/css/footer.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		
	</head>

	<body>
		<div class="logo">
			<div class="float">
				<span class="logo_pic">
					<a href="/huyugo/index.do" >
			          <img src="/huyugo/img/logo/logo.jpg" width="225" height="65"/>
		            </a>
				</span>
				<span class="tel">
					<a href="/huyugo/index.do" style="color:#999;">返回首页</a>
				</span>
			</div>
		</div>
	

			<div class="shop_payment">
				<ul class="payment">
					<li class="first_step">第一步：提交订单</li>
					<li class="arrow_2"></li>
					<li class="third_step">第二步：网银支付 </li>
					<li class="arrow_2"></li>					
					<li class="fourth_step">第三步：购买成功</li>
					<li class="arrow_1"></li>
					<li class="secend_step orange_Tech" style="width: 30.7%;">第四步：支付成功</li>
					 

				</ul>
			</div>
			<%
			Order order = (Order)request.getAttribute("order");
			String sumPrice = (String)request.getAttribute("sumPrice");
			String nowDate = (String)request.getAttribute("nowDate");
			 %>
			<div class="publicBox22">
				<div class="gowumiaoK">
					<img src="/huyugo/img/cgg.png" />
				</div>
				<div class="p10pl">
					<p>订单编号：<%=order.getOrder_num() %></p>
					<p>订单金额：<%=sumPrice %>元</p>
					<p>创建时间：<%=order.getOrder_time() %></p>
					<p>付款时间：<%=nowDate %></p>
				</div>
			</div>
			
			  
		<!--底部内容-->
		<jsp:include page="footer.jsp"></jsp:include>
		<!--底部内容-->
	</body>

</html>