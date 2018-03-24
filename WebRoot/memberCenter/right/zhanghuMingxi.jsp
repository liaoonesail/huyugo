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
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>账户明细</span>

			</div>
			<table width="100%" style="border-left: 1px solid #F5F5F5;border-right: 1px solid #F5F5F5;">
				<tr class="titleTable">
					<th>时间</th>
					<th>资金渠道</th>
					<th>获得/支出</th>
				</tr>
				<%
				int status = (Integer)request.getAttribute("status");
				String curpage = (String)request.getAttribute("curpage");
				int curr = Integer.parseInt(curpage);
				Map<String, Object> paymentList = (Map<String, Object>) request
						.getAttribute("paymentList");
				for(Receipt_Payment payment : (List<Receipt_Payment>)paymentList.get("list")){
					%>
					<tr class="Trdf">
						<td><%=payment.getDate_time() %></td>
						<td><%=ServiceUtil.getPaymentChannel(payment.getWay()) %><%if(payment.getWay()==11){%><%=ServiceUtil.getPaymentChannelType(payment.getType())%><%=payment.getPhone()%> <%} %></td>
						<td style="color: #0c0;"><%=ServiceUtil.getPriceDetails(payment.getReceipt(), payment.getPayment()) %></td>
					</tr>
					<%
				}
				 %>
				<tr>
				<td></td>
				<% if(status == 1) {%>
				<td><a href="/huyugo/payment/showYongjinDetail.do?curpage=<% if (curr == 1) %><%=curpage %><% else %><%=curr-1%>">上一页</a></td>
				<td><a href="/huyugo/payment/showYongjinDetail.do?curpage=<% if (curr == (Integer)paymentList.get("countPage")) %><%=curpage %><% else %><%=curr+1%>">下一页</a></td>
				<%}else{ %>
				<td><a href="/huyugo/payment/showPriceDetail.do?curpage=<% if (curr == 1) %><%=curpage %><% else %><%=curr-1%>">上一页</a></td>
				<td><a href="/huyugo/payment/showPriceDetail.do?curpage=<% if (curr == (Integer)paymentList.get("countPage")) %><%=curpage %><% else %><%=curr+1%>">下一页</a></td>
				<%} %>
				<td></td>
			</tr>
			</table>
		</div>
		

	</body>

</html>