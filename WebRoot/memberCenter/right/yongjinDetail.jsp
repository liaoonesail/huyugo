<%@page import="com.huyu.util.ServiceUtil"%>
<%@page import="com.huyu.entity.Receipt_Payment"%>
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
		<link rel="stylesheet" href="/huyugo/memberCenter/css/address.css" />
		<link rel="stylesheet" href="/huyugo/memberCenter/css/integralBox.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
	</head>

	<body>
	<%
	double huyubi = 0;
	User user = (User)request.getAttribute("user");
	if(user != null){
		huyubi = user.getHuyubi();
	}
	 %>
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>佣金明细</span>

			</div>
			
			<table width="100%" style="border-left: 1px solid #F5F5F5;border-right: 1px solid #F5F5F5;">
				<tr class="titleTable">
					<th>时间</th>
					<th>渠道</th>
					<th>获得</th>
				</tr>
				<%
				List<Receipt_Payment> paymentList = (List<Receipt_Payment>)request.getAttribute("paymentList");
				for(Receipt_Payment payment : paymentList){
					if(payment.getReceipt() > 0){
						%>
						<tr class="Trdf">
							<td><%=payment.getDate_time() %></td>
							<td><%=payment.getReceipt() %></td>
							<td style="color: #0c0;"><%=ServiceUtil.getPriceDetails(payment.getReceipt(), payment.getPayment()) %></td>
						</tr>
						<%
					}
				}
				 %>
			</table>
		</div>
		

	</body>

</html>