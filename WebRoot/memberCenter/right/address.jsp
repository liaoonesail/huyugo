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
	List<Address> addressList = (List<Address>)request.getAttribute("addressList");
	 %>
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>收货地址</span>
               
			</div>

			<div class="publicBox2 addressList clearfix">				
				<!--收货地址列表-->
				<!--新增收货地址-->
				<div onclick="javascript:window.location.href='/huyugo/address/addAddress.do'" class="addxinBtns">新增收货地址</div>
				<!--新增收货地址-->
				<ul id="ListNavService">
					<%
					for(Address address : addressList){
						%>
						<li>
							<div class="addressListconts clearfix">
								<h3 class="dgfde"><%=address.getName() %></h3>
								<%if(address.getStatus() == 1){ %><img class="moreng" src="/huyugo/memberCenter/jiantou/moreng.png" width="71" height="21" /><%} %>
								<button onclick="window.location.href='/huyugo/address/deleteAddress.do?id=<%=address.getId() %>'">删除</button>
								<button onclick="window.location.href='/huyugo/address/updateAddress.do?id=<%=address.getId() %>'">编辑</button>
								<%if(address.getStatus() == 0){ %><button onclick="window.location.href='/huyugo/address/moren.do?id=<%=address.getId() %>'">设置默认</button><%} %>
							</div>
							<div class="addressListconts22">
								<p>收货人：<span><%=address.getName() %></span></p>
								<!-- <p>身份证号：<span>392192****12092411</span></p> -->
								<!-- <p>所在地区：<span>上海市徐汇区漕河泾技术开发区</span></p> -->
								<p>详细地址：<span><%=address.getProvince() %><%=address.getCity() %><%=address.getCounty() %><%=address.getAddress() %></span></p>
								<p>联系电话：<span><%=address.getPhone() %></span></p>
							</div>
						</li>
						<%
					}
					 %>
					
					<a class="btnsdhover">更多信息</a>
				</ul>
				<!--收货地址列表-->
				
			</div>
		</div>

		<script>
			//设置默认地址
			var ListNavService = document.getElementById("ListNavService")
			var oli = ListNavService.getElementsByTagName("li")//所有的li
			var odiv = ListNavService.getElementsByClassName("dgfde");//姓名
			var newlist1 = document.getElementsByClassName("moreng");//默认地址
			var del =document.getElementsByClassName("del");//删除
			
			newlist1[0].style.display = 'block';
			for(var a = 0; a < odiv.length; a++) {
				odiv[a].index = a;
				del[a].index = a;
				oli[a].index =a;
				//默认地址
				odiv[a].onclick = function() {
					for(var b = 0; b < newlist1.length; b++) {
						newlist1[b].style.display = 'none';
					}
					newlist1[this.index].style.display = "block";

				};
				//删除地址
				del[a].onclick=function(){					
					oli[this.index].style.display = 'none';
					alert("删除成功!!!!");
				}
			};
			
			////更多信息
			$(".btnsdhover").css("display","none");
			var ddContsBox=$("#ListNavService li")
			for(var i=0;i<ddContsBox.length;i++) {
				if(i>=3){
					$(".btnsdhover").css("display","block");
					ddContsBox[i].style.display="none";
				}
			}
			$(".btnsdhover").click(function(){
				for(var i=0;i<ddContsBox.length;i++) {			 
					ddContsBox[i].style.display="block";				 
			     }
				$(".btnsdhover").empty();
			})
		</script>
	</body>

</html>