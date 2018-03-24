<%@page import="com.huyu.util.ServiceUtil"%>
<%@page import="com.huyu.entity.Receipt_Payment"%>
<%@page import="com.huyu.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		double integral = 0;
		User user = (User) request.getAttribute("user");
		if (user != null) {
			integral = user.getIntegral();
		}
	%>
	<div class="publicBox2">

		<div class="right1Title clearfix">
			<img src="/huyugo/memberCenter/jiantou/14.png" /> <span>我的积分</span>
		</div>
		<div class="integralBox">
			<div class="integralBoxListTile">
				<h4>
					可用积分：<span><%=integral%></span>
				</h4>
				<!-- <div class="jiefYU">可用金钱：	<span>14</span>积分 / <span>100积分</span><span style="font-size: 13px;">(1元=100积分)</span>	= <span>0</span>元(取整)</div> -->
			</div>
		</div>

		<table width="100%"
			style="border-left: 1px solid #F5F5F5;border-right: 1px solid #F5F5F5;">
			<tr class="titleTable">
				<th>时间</th>
				<th>渠道</th>
				<th>获得/支出</th>
			</tr>
			<%
				String curpage = (String)request.getAttribute("curpage");
				int curr = Integer.parseInt(curpage);
				Map<String, Object> paymentList = (Map<String, Object>) request
						.getAttribute("paymentList");
				for (Receipt_Payment payment : (List<Receipt_Payment>)paymentList.get("list")) {
			%>
			<tr class="Trdf">
				<td><%=payment.getDate_time()%></td>
				<td><%=ServiceUtil.getPaymentChannel(payment.getWay())%></td>
				<td style="color: #0c0;"><%=ServiceUtil.getPriceDetails(payment.getReceipt(),
						payment.getPayment())%></td>
			</tr>
			<%
				}
			%>
			<tr>
				<td></td>
				<td><a href="/huyugo/payment/showItegral.do?curpage=<% if (curr == 1) %><%=curpage %><% else %><%=curr-1%>">上一页</a></td>
				<td><a href="/huyugo/payment/showItegral.do?curpage=<% if (curr == (Integer)paymentList.get("countPage")) %><%=curpage %><% else %><%=curr+1%>">下一页</a></td>
				<td></td>
			</tr>
		</table>
		
	</div>


</body>

</html>