<%@page import="com.huyu.entity.Address"%>
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
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
	</head>

	<body>
	<%
	Address address = (Address)session.getAttribute("address");
	 %>
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>修改收货地址</span>

			</div>
			<form id="updateForm" method="post" action="/huyugo/address/updateAddress.do?status=update">
			<!--新增收货地址-->
			<div class="publicBox2 addressList clearfix">
				<!--收货人-->
				<div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>收货人</span>
					</div>
					<input class="useIputYou" type="text" name="name" value="<%=address.getName() %>" />
				</div>
				<!--收货人-->
				<!--身份证号-->
				<!-- <div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>身份证号</span>
					</div>
					<input class="useIputYou" type="text" value="392192123512092411" />
				</div> -->
				<!--身份证号-->
				<!--所在地区-->
				<div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>所在地区</span>
					</div>
					<div class="info">
						<div>
							<select id="s_province" name="province" ></select>
							<select id="s_city" name="city"></select>
							<select id="s_county" name="county"></select>
						</div>
						<div id="show"></div>
					</div>
				</div>
				<div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>详细地址</span>
					</div>
					<input class="useIputYou" name="address" type="text" value="<%=address.getAddress() %>" />
				</div>
				<!--所在地区-->
				<!--联系电话-->
				<div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>联系电话</span>
					</div>
					<input class="useIputYou" type="text" name="phone" value="<%=address.getPhone() %>" />
				</div>
				<!--联系电话-->
				<a class="btnsaddssse" onclick="updateAddress();">确认修改</a>
				<input type="hidden" name="id" value="<%=address.getId() %>" />
			</div>
			</form>
			<!--新增收货地址-->
		</div>
		<script class="resources library" src="/huyugo/js/area.js" type="text/javascript"></script>
		<script type="text/javascript">
			var opt0 = ["<%=address.getProvince() %>","<%=address.getCity() %>","<%=address.getCounty() %>"];//初始值
			_init_area();
			var Gid = document.getElementById;
			var showArea = function() {
                
				Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" +

					Gid('s_city').value + " - 县/区" +

					Gid('s_county').value + "</h3>"

			}

		 	function updateAddress(){
		 		$("#updateForm").submit();
		 	}
		</script>

	</body>

</html>