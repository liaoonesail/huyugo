<%@page import="com.huyu.entity.Logistics"%>
<%@page import="com.huyu.entity.Order_details"%>
<%@page import="com.huyu.entity.Order"%>
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
		<link rel="stylesheet" href="/huyugo/memberCenter/css/right.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		<script type="text/javascript">
		function getGoodsData(id, goodsId, status, price, orderId){
				$.ajax({
					url:"/huyugo/goods/getGoodsById.do",
					data:{
					      id:goodsId
					},  
					type:'post',  
					cache:false,  
					dataType:'html',
					success:function(data) {
						var json = JSON.parse(data);
						var divid = "#goods" + id;
						var imgid = "#img" + id;
						var imgid2 = "#img2" + id;
						var pid = "#p" + id;
						var buttonid = "#button" + id;
						var buttona = "#buttona" + id;
						var str = json.name + " <span>￥  " + price + "</span>";
						if(json.name.length >= 26){
							str = "<a href='/huyugo/goods/showGoodsById.do?id="+json.id+"' target='_blank'>"+json.name.substring(0,26) + "...</a>" + " <span>￥  " + price + "</span>";
						}else{
							str = "<a href='/huyugo/goods/showGoodsById.do?id="+json.id+"' target='_blank'>"+json.name + "</a>" + " <span>￥  " + price + "</span>";
						}
						$(divid).html(str);
						$(imgid).attr("src",json.picture_address);
						
						if(Number(status) > 1){
							$.post("/huyugo/adminlogistics/getByOrderId.do",{orderId:orderId,goodsId:goodsId},function(res){
								$(".logistics"+id).html(res.logistics_name+":"+res.logistics_num);
							},"json");
						}
						
						if(status == 0){
							$(buttona).css("display","none");//换货
							$(imgid2).attr("src","/huyugo/memberCenter/jiantou/laonm.png");
							$(buttonid).attr("onclick","queren('"+orderId+"')");
							$(pid).html("订单处理中");
							$(buttonid).html("确认付款");
							var buttonid2 = "#button2"+id;
							$(buttonid2).html("撤销订单");
							$(buttonid2).css("display","block");
						}else if(status == 1){
							$(buttona).css("display","none");//换货
							$(imgid2).attr("src","/huyugo/memberCenter/jiantou/cahr.png");
							$(buttonid).attr("onclick","queren2('"+orderId+"')");
							$(pid).html("等待发货中");
							//$(buttonid).css("display","none");
							$(buttonid).attr("onclick","tuikuan('"+orderId+"','"+goodsId+"')");
							$(buttonid).html("申请退款");
						}else if(status == 2){
							$(buttona).css("display","none");//换货
							//$(imgid2).attr("src","/huyugo/memberCenter/jiantou/cahr.png");
							$(imgid2).attr("src","/huyugo/memberCenter/jiantou/cahr.png");
                            $(buttonid).attr("onclick","queren4('"+orderId+"')");
                            $(pid).html("等待收货中");
                            $(buttonid).html("确认收货");
                            
                            //发送一次同步ajax请求,执行自动确认收货
							$.ajax({
								url:'/huyugo/order/autoAffirm.do',
								type:'post',
								ascync:false,
								data:{orderId:orderId,goodsId:goodsId},
								success:function(){
								}
							});
						}else if(status == 3){
							$(pid).html("待评价");
							var buttonid2 = "#button2"+id;
							buttona = "#buttona" + id;
							$(buttonid2).css("display","block");
							$(buttonid2).html("申请退货");
							$(buttonid2).attr("onclick","tuihuo('"+orderId+"')");
							$(buttona).css("display","block");
							$(buttona).html("申请换货");
							$(buttona).attr("onclick","huanhuo('"+orderId+"')");
                            $.ajax({ 
                                url:"/huyugo/goodsComment/showGoodsCommentByGoodsId.do",
                                data:{
                                      goodsId:goodsId
                                },  
                                type:'post',
                                cache:false,
                                dataType:'html',
                                success:function(data) {
                                    $(buttonid).attr("onclick","queren3('"+orderId+"')");
                                    if(data == "no"){
                                        $(buttonid).html("前往评价");
                                    }else if(data == "ok"){
                                        $(buttonid).html("前往评价");
                                    }
                                }
                            });
						}else if(status == 4){
							$(buttona).css("display","none");//换货						
							$(pid).html("已完成");
							$(buttonid).html("删除订单");
							$(buttonid).attr("onclick","delOrder('"+orderId+"')");
							//$(buttonid).css("display","none");
						}else if(status == 5){
							$(buttona).css("display","none");//换货
							$(pid).html("申请退款中");
							//$(buttonid).css("display","none");
							$(buttonid).html("取消退款");
							$(buttonid).attr("onclick","cancelTuikuan('"+orderId+"')");
						}else if(status == 6){
							$(buttona).css("display","none");//换货
							$(pid).html("退款已完成");
							$(buttonid).html("删除订单");
							$(buttonid).attr("onclick","delOrder('"+orderId+"')");
						}else if(status == 7){
							//$(buttona).css("display","none");//换货
							$(pid).html("申请退货中");
							$(buttonid).html("删除订单");
							$(buttonid).attr("onclick","delOrder('"+orderId+"')");
							$(buttona).css("display","block");
							$(buttona).html("取消退货");
							$(buttona).attr("onclick","cancelTuihuo('"+orderId+"')");
						}else if(status == 8){
							$(buttona).css("display","none");//换货
							$(pid).html("退款成功！");
							$(buttonid).html("删除订单");
							$(buttonid).attr("onclick","delOrder('"+orderId+"')");
						}
				    }
				});
			}
			function queren(orderId){
				parent.location.href = "/huyugo/order/showOrderById.do?id="+orderId;
			}
			function queren2(orderId){
				window.location.href = "/huyugo/order/updateOrder.do?id="+orderId;
			}
			function queren3(orderId){
				window.location.href = "/huyugo/goodsComment/getGoodsById.do?curpage=1&orderId="+orderId;
			}
			function queren4(orderId){
				window.location.href = "/huyugo/order/updateStatus.do?curpage=1&id="+orderId;
			}
			function cancelTuikuan(orderId){
				$.post("../order/cancelTuikuan.do",{orderId:orderId},function(res){
					if(res == "1"){
						alert("取消成功！");
						window.location.reload();
					}else{
						alert("该订单已完成退款,取消失败！");
						window.location.reload();
					}
				});
			}
			function cancelTuihuo(orderId){
				$.post("../order/cancelTuihuo.do",{orderId:orderId},function(res){
					if(res == "1"){
						alert("取消成功！");
						window.location.reload();
					}else{
						alert("该订单已完成退货,取消失败！");
						window.location.reload();
					}
				});
			}
			function tuikuan(orderId,goodsId){
				$.ajax({
					url:'/huyugo/order/refundMoney.do',
					type:'post',
					data:{orderId:orderId,goodsId:goodsId},success:function(res){
						if(res == "1"){
							alert("申请退款成功");
							window.location.reload();
						}else if(res == "2"){
							alert("订单已发货,申请失败");
							window.location.reload();
						}
					}
				});
			}
			function tuihuo(orderId){
				if(confirm("您确定申请退货吗？")){
					$.ajax({
						url:'/huyugo/order/tuihuo.do',
						type:'post',
						data:{orderId:orderId},
						success:function(res){
							if(res == "1"){
								alert("申请退款成功！请联系客服获取退货地址,并按照地址邮寄");
								window.location.reload();
							}else{
								alert("申请退货失败！");
								window.location.reload();
							}
						}
					});
				}
			}
			
			function huanhuo(){
				alert("请联系在线客服");
				window.location.href='tencent://message/?uin=762136258&Site=&menu=yes';
			}
			
			function delOrder(orderId){
				if(confirm("此操作不可恢复,您确定删除订单？？？")){
					alert("删除成功！");
					window.location.href = "/huyugo/order/delOrder.do?curpage=1&id="+orderId;
				}
			}
		</script>
	</head>

	<body>
	<%
	//List<Order> orderList = (List<Order>)request.getAttribute("orderList"); 
	
	
	 %>
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<%
				

                Integer zt = (Integer)request.getAttribute("zt");
					String ztStr = "";
				    if(zt == -1){
				        ztStr = "所有订单";
				    }else if(zt == 0){
				        ztStr = "待付款订单";
				    }else if(zt == 1){
				        ztStr = "待发货订单";
				    }else if(zt == 2){
				        ztStr = "待确认收货订单";
				    }else if(zt == 3){
				        ztStr = "待评价订单";
				    }else if(zt == 4){
				        ztStr = "已完成订单";
				    }else if(zt == 5){
				    	ztStr = "待退款订单";
				    }else if(zt == 7){
				    	ztStr = "待退货订单";
				    }
				String orderAll = (String)request.getAttribute("orderAll");
				String orderNoPay = (String)request.getAttribute("orderNoPay");
				String orderNoShouhuo = (String)request.getAttribute("orderNoShouhuo");
				String noPingjia = (String)request.getAttribute("noPingjia");
				String Pingjia = (String)request.getAttribute("Pingjia");
				String notuikuan = (String)request.getAttribute("notuikuan");
				String notuihuo = (String)request.getAttribute("notuihuo");
				String noSendGoods = (String)request.getAttribute("orderNoFahuo");
				
				%>
				
				<span style="position: relative;"><div style="position: absolute;left: 10px;top: 0px;width: 130px;"><%=ztStr %></div><ul style="position: absolute;left: 140px;top: 0px;width: 700px;">
						<!-- <li id="xdmL" onclick="javascript:window.location.href='/huyugo/order/showOrder.do?zt=-1'"> -->
						<li>
							<span><a href="/huyugo/order/showOrder.do?zt=-1&amp;curpage=1">全部 <i><%=orderAll %></i></a>&nbsp;|&nbsp;</span>
							<span><a href="/huyugo/order/showOrder.do?zt=0&amp;curpage=1">待付款 <i><%=orderNoPay %></i></a>&nbsp;|&nbsp;</span>
							<span><a href="/huyugo/order/showOrder.do?zt=1&amp;curpage=1">待发货<i><%=noSendGoods %></i></a>&nbsp;|&nbsp;</span>
							<span><a href="/huyugo/order/showOrder.do?zt=2&amp;curpage=1">待确认收货 <i><%=orderNoShouhuo %></i></a>&nbsp;|&nbsp;</span>
							<span><a href="/huyugo/order/showOrder.do?zt=3&amp;curpage=1">待评价 <i><%=noPingjia %></i></a>&nbsp;|&nbsp;</span>
							<span><a href="/huyugo/order/showOrder.do?zt=4&amp;curpage=1">已完成 <i><%=Pingjia %></i></a>&nbsp;|&nbsp;</span>
							<span><a href="/huyugo/order/showOrder.do?zt=5&amp;curpage=1">待退款 <i><%=notuikuan %></i></a>&nbsp;|&nbsp;</span>
							<span><a href="/huyugo/order/showOrder.do?zt=7&amp;curpage=1">待退货 <i><%=notuihuo %></i></a></span>
						</li>
					</ul></span>

			</div>
			<!--已买到的宝贝列表-->
			<style>
				div.doutryLh{
					width: 70%;
				}
			</style>
			<dl class="ddContsBox">
				<%
				
				String curpage = (String)request.getAttribute("curpage");
				int curr = Integer.parseInt(curpage);
				Map<String, Object> orderList = (Map<String, Object>) request
						.getAttribute("orderList");
				int i = 0;
				/* out.print((Integer)orderList.get("count")+"     ");
				out.println((List<Order>)orderList.get("list")); */
				for(Order order : (List<Order>)orderList.get("list")){
					//out.print(order.getList().get(0).getAmount());
					List<Order_details> detailsList = order.getList();
					int j = 0;
					for(Order_details details : detailsList){
						%>
						<dd class="ddContsBox1 clearfix">
							<div class="Picdoutr">
								<img id="img<%=i %>_<%=j %>" />
							</div>
							<div class="Picdoutr2">
								<img id="img2<%=i %>_<%=j %>" />
								<p id="p<%=i %>_<%=j %>"></p>
							</div>
							<div class="doutryLh">
								<p>所属订单  <%=order.getOrder_num() %>  订单时间  <%=order.getOrder_time() %>  实付款 <%=order.getTotal()+order.getYu_total() %>元</p>
								<p id="goods<%=i %>_<%=j %>"></p>
								<p class="logistics<%=i %>_<%=j %>" style="color: #666666;font-size: 14px;margin-top: 20px;display: block;"></p>
								<button style="background: red;display:none;" id="button2<%=i %>_<%=j %>" onclick="delOrder('<%=order.getId() %>');"></button>
								<button class="quxaiofukuanBtns0" id="button<%=i %>_<%=j %>" ></button>
								<button class="quxaiofukuanBtns0" id="buttona<%=i %>_<%=j %>" ></button>
							</div>
						</dd>
						<script>getGoodsData("<%=i %>_<%=j %>", "<%=details.getGoods_id() %>", "<%=order.getStatus() %>", "<%=details.getReal_price() %>", "<%=order.getId() %>");</script>
						<%
						j++;
					}
					i++;
				}
				 %>
				

			</dl>
			<tr>
				<td></td>
				 <td><a href="/huyugo/order/showOrder.do?zt=<%=zt %>&curpage=<% if (curr == 1) %><%=curpage %><% else %><%=curr-1%>">上一页</a></td>
				<td><a href="/huyugo/order/showOrder.do?zt=<%=zt %>&curpage=<% if (curr == (Integer)orderList.get("countPage")) %><%=curpage %><% else %><%=curr+1%>">下一页</a></td>
				<td></td>
			</tr>
		</div>
		<!--已买到的宝贝列表-->
		<!--已买到的宝贝-->
		</div>
		<style>
			a > i{
				 color: #FE9900;
        		font-style: normal;
			}
			
			.daloginBox{
				display: none;
				position: absolute;
				top: 0px;
				left: 0px;
				width: 100%;
				height: 100%;				
				z-index: 258963;
			}
			.box {
				border: 5px solid #eee;
				width: 320px;
				background-color: #fff;
				margin: 20% auto;
				erflow: hidden;
			}
			
			.box-b {
				border: 1px solid #dfdbdb;
				width: 310px;
				erflow: hidden;
			}
			
			.box-title {
				background: #ca1b38;
				height: 30px;
				line-height: 33px;
				_line-height: 30px;
				font-size: 14px;
				color: #fff;
				padding: 0 10px;
			}
			
			.box-text {
				font: 12px/1.5 "微软雅黑", Arial, "宋体", Helvetica, sans-serif;
				font-size: 18px;
				color: #73787b;
				width: 320px;
				text-align: center;
				border: 0px solid #000;
				padding: 20px 0px;
				height: 130px;
				word-wrap: break-word;
			}
			.box-text p{
				font-size: 14px;
				width: 100%;
				overflow: hidden;
			}
			.box-button {
				overflow: hidden;
				text-align: right;
			}
			
			.box-button a {
				display: inline-block;
				height: 25px;
				line-height: 23px;
				_line-height: 25px;
				text-align: center;
				font-family: "微软雅黑";
				font-size: 14px;
				text-decoration: none;
				padding: 0px 5px;
				margin: 0px 10px;
			}
			
			.a-1 {
				background-color: #eee;
				color: #666;
				border: 1px solid #dfdbdb;
			}
			
			.a-2 {
				background-color: #eee;
				color: #666;
				border: 1px solid #dfdbdb;
			}
		</style>
		<!--取消付款/申请退货弹窗-->
		<div class="daloginBox">
			<div class="box">
				<div class="box-b">
					<div class="box-title">消息提示</div>
					<div class="box-text">
					   <p>申请退款成功！！！详情查看,请看我的退款</p>
					<div style="height:10px; overflow:hidden; width:100%; clear:both"></div>
				</div>
			</div>
		</div>
		<!--取消付款/申请退货弹窗-->
        <script>
        	$(function(){
				//取消付款
				$(".quxaiofukuanBtns").click(function() {
					 
					$(".daloginBox").css("display","block");
					var i = 4;
					if(i != 0) {
						setInterval(function() {
							if(i<=0){
								$(".daloginBox").css("display","none");
							}
						}, 1000);
					};
				})
				//申请退货
				/* $(".quxaiofukuanBtns0").click(function() {
					$(".daloginBox").css("display","block");
					var i = 4;
					if(i != 0) {
						setInterval(function() {
							i--;
							if(i<=0){
								$(".daloginBox").css("display","none");
							}
						}, 1000);
					}
				}) */
				
			});
			 //待评价
            $(".pinglunBtns").click(function() {
//									alert(1)
					window.location.href = 'pinglun0.html';
			});
			
			
        </script>
	</body>

</html>