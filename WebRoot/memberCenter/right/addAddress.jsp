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
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>新增收货地址</span>

			</div>
			<!--新增收货地址-->
			<form id="addForm" method="post" action="/huyugo/address/addAddress.do">
			<div class="publicBox2 addressList clearfix">
				<!--收货人-->
				<div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>收货人</span>
					</div>
					<input class="useIputYou" type="text" name="name" placeholder="请输入收货人姓名" />
				</div>
				<!--收货人-->
				<!--联系电话-->
				<div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>联系电话</span>
					</div>
					<input class="useIputYou" type="text" name="phone" placeholder="请输入联系电话" />
				</div>
				<!--联系电话-->
				<!--身份证号-->
				<!-- <div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>身份证号</span>
					</div>
					<input class="useIputYou" type="text"  placeholder="请输入身份证号" />
				</div> -->
				<!--身份证号-->
				<!--所在地区-->
				<div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>所在地区</span>
					</div>
					<div class="info">
						<div>
							<select id="s_province" name="province"></select>
							<select id="s_city" name="city"></select>
							<select id="s_county" name="county"></select>
						</div>
						<div id="show"></div>
					</div>
				</div>
				<!--所在地区-->
				<!--联系电话-->
				<div class="clearfix">
					<div class="addxinWang">
						<span>*</span><span>详细地址</span>
					</div>
					<input class="useIputYou" type="text" name="address" placeholder="请输入详细地址" />
				</div>
				<!--联系电话-->
				<a class="btnsaddssse" onclick="addressSubmit();">确认添加</a>
			</div>
			<input type="hidden" name="status" value="add" />
			</form>
			<!--新增收货地址-->
		</div>
		<script class="resources library" src="/huyugo/js/area.js" type="text/javascript"></script>

		<script type="text/javascript">
			_init_area();
		</script>

		<script type="text/javascript">
			var Gid = document.getElementById;

			var showArea = function() {

				Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" +

					Gid('s_city').value + " - 县/区" +

					Gid('s_county').value + "</h3>"

			}
			
			
			function addressSubmit(){
				$("#addForm").submit();
			}

		 
		</script>

	</body>

</html>