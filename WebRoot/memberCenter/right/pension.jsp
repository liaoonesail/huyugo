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
			integral = user.getPension();
		}
	%>
	
	<div class="publicBox2">

		<div class="right1Title clearfix">
			<img src="/huyugo/memberCenter/jiantou/14.png" /> <span>我的养老金</span>
		</div>
		<div class="integralBox">
			<div class="integralBoxListTile">
				<h4 style="position: relative;">
					可用养老金：<span><%=integral%></span>
					<p style="position: absolute; top:0; left: 250px;line-height: 56px;">倒计时<span id="pension_daojishi" time="<% if(user.getPensionTime()==null || "".equals(user.getPensionTime())){ %><%=1%><%}else{%><%=user.getPensionTime()%><%}%>"></span></p>
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
				<td style="color: #0c0;"><% if((payment.getReceipt()+"").indexOf("-") == -1) {%> +<%}%> <%=payment.getReceipt() %></td>
				<!-- 养老金改成同一个字段getReceipt  带有减号标识支出，没有则表示获得 -->
				<%-- <td style="color: #0c0;"><%=ServiceUtil.getPriceDetails(payment.getReceipt(),
						payment.getPayment())%></td> --%>
			</tr>
			<%
				}
			%>
			<tr>
				<td></td>
				<td><a href="/huyugo/payment/showPension.do?curpage=<% if (curr == 1) %><%=curpage %><% else %><%=curr-1%>">上一页</a></td>
				<td><a href="/huyugo/payment/showPension.do?curpage=<% if (curr == (Integer)paymentList.get("countPage")) %><%=curpage %><% else %><%=curr+1%>">下一页</a></td>
				<td></td>
			</tr>
		</table>
		
	</div>
<script type="text/javascript">
	var day = 1000 * 60 * 60 * 24;//一天的毫秒值
	var sumDay = day*365*10;//十年的毫秒值
	var pension_time = $("#pension_daojishi").attr("time");//开始有
	console.log(pension_time);
	if(pension_time!=1){
		pension_time = new Date(pension_time);//格式化成日期对象
		var s1 = (pension_time.getTime()+sumDay)*1;//终点时间
		var s2 = new Date().getTime()*1;//当前时间的毫秒值
		var juli  = s1*1 - s2*1;//两时间的距离（毫秒值）
		var days = parseInt(juli / day)*1;
		$("#pension_daojishi").html(days+"天");
	}else{
		console.log("dd");
        $("#pension_daojishi").html(3649+"天");
	}
	
	
</script>

</body>

</html>