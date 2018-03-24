<%@page import="com.huyu.util.ServiceUtil"%>
<%@page import="com.huyu.entity.Receipt_Payment"%>
<%@page import="com.huyu.entity.User"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.huyu.entity.FazhanOrder" %>
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
		<link rel="stylesheet" href="/huyugo/memberCenter/css/right.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="/huyugo/js/fazhanjin.js"></script>
		<style>
			.fazhanjin{
				display: inline;width: 100px;height: 45px;background-color: #1E9FFF;border-radius: 10px;font-size: 150%;cursor:pointer;margin-left: 20px;
			}
			.jiameng{
				display: inline;background-color: #00CC5D;border-radius: 10px;font-size: 165%;cursor:pointer;margin-left: 35px;font-family:"Arial Black";font-style:italic;
			}
			.zhuce_middle_p1{
				font-size: 14px;
				color: #999;
				margin: 20px 20px 0px 20px;
				line-height: 35px;
				margin-left: 500px;
			}
			#yue{
				border: 1px solid #ccc;
				padding: 7px 0px;
				border-radius: 3px;
				padding-left:2px;
				width: 110px;
				margin-left: 35px;
			}
		</style>

	</head>

	<body>
	<%
	double account = 0;
	User user = (User)request.getAttribute("user");
	if(user != null){
		account = user.getAccount();
	}

	 %>
	<div class="publicBox2">
		<div class="right1Title clearfix">
			<span style="color: #6f1922">可用余额：</span><span style="color: #00cc00" id="account"><%=account%></span>
		</div>
	</div>
		<div class="publicBox2">
		<div class="right1Title clearfix">
			<div id="fazhanhead" value="0">
				<form target="_blank" action="/huyugo/fazhanOrder/pay.do" method="post" onsubmit="return yanzheng2()">
					<div class="fazhanjin" myturn="1">100元</div>
					<div class="fazhanjin" myturn="2">500元</div>
					<div class="fazhanjin" myturn="3">1000元</div>
					<div class="fazhanjin" myturn="4">5000元</div>
					<div class="fazhanjin" myturn="5">10000元</div>
					<div class="fazhanjin" myturn="6">50000元</div>
					<div class="fazhanjin" myturn="7">100000元</div>
					<input style="color: #00cc00" id="yue" name="yue" placeholder="填写要使用的余额" >
					<input style="display: none" id="title" name="title" value="" type="number"/>
					<button type="submit" class="jiameng" style="border-bottom:solid;border-bottom-color: #7d8289">加盟</button>
				</form>
			</div>
		</div>
		</div>

		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>发展金加盟明细</span>
				<p class="zhuce_middle_p1">点击"加盟"，即表示您同意并愿意遵守<a href="/huyugo/help/getid.do?type=1&id=57" style="color: #47176D;text-decoration: underline;">发展基金加盟协议及风险提示</a></p>
			</div>
			
			<table width="100%" style="border-left: 1px solid #F5F5F5;border-right: 1px solid #F5F5F5;">
				<tr class="titleTable">
					<th>加盟时间</th>
					<th>加盟金额</th>
					<th>剩余天数</th>
					<th>发展金数</th>
				</tr>
				<%
					String curpage = (String)request.getAttribute("curpage");
					if(curpage==null||"".equals(curpage)){
					    curpage="1";
					}
					int curr = Integer.parseInt(curpage);
					Map<String, Object> paymentList = (Map<String, Object>) request.getAttribute("orderList");
					for (FazhanOrder payment : (List<FazhanOrder>)paymentList.get("list")) {
						%>
						<tr class="Trdf">
							<td><%=payment.getOrderTime() %></td>
							<td style="color: #0c0;"><%=payment.getPayMoney() %></td>
							<td ><%=payment.getDays() %></td>
							<td ><%=payment.getFazhanjin() %></td>
						</tr>
						<%
				}
				 %>
				<tr>
					<td></td>
					<td><a href="/huyugo/fazhanOrder/showFazhanjin.do?curpage=<% if (curr == 1) %><%=curpage %><% else %><%=curr-1%>">上一页</a></td>
					<td><a href="/huyugo/fazhanOrder/showFazhanjin.do?curpage=<% if (curr == (Integer)paymentList.get("countPage")) %><%=curpage %><% else %><%=curr+1%>">下一页</a></td>
					<td></td>
				</tr>
			</table>
		</div>
	</body>

</html>