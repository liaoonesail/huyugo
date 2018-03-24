<%@page import="com.huyu.entity.User"%>
<%@page import="com.huyu.entity.Goods"%>
<%@page import="com.huyu.entity.Order_details"%>
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
	<%
	User user = (User)request.getAttribute("user");
	double yue = 0;
	double dixianjin = 0;
	double integral=0;
	if(user != null){
		yue = user.getAccount();
		dixianjin = user.getDixianjin();
		integral=user.getIntegral();
	}else{
		%>
		<script>window.location.href = "/huyugo/user/login.do";</script>
		<%
	}
	String nullAddress = (String)request.getAttribute("nullAddress");
	 %>
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
		<form action="/huyugo/order/showOrderPay.do" method="post" onsubmit="return yanzheng();">
            <script>
                function yanzheng(){
                	var menoy = $("#weixinzhifu").html();
                	if(Number(menoy)<0){
                		alert("此商品当前不能使用抵现金！");
                		return false;
                	}
                }
            </script>
			<div class="shop_payment">
				<ul class="payment">
					<li class="first_step">第一步：提交订单</li>
					<li class="arrow_1"></li>
					<li class="secend_step orange_Tech">第二步：网银支付</li>
					<li class="arrow_3"></li>
					<li class="third_step">第三步：支付成功 </li>
					<li class="arrow_2"></li>
					<li class="fourth_step">第四步：购买成功</li>

				</ul>
				<div class="payment_Con">
					<ul class="order_list">
						<li class="top">
							<span class="name" style="width: 315px">商品名称</span>
							<span class="moneys" style="width: 140px;text-align: right;">价值</span>
							<span class="money" style="width: 200px;text-align: right;">购买价</span>
							<span class="num" style="width: 200px;">购买数量</span>
							<span class="num" style="width:100px;">是否使用抵现金</span>
							<span class="all" style="width: 160px;">小计</span>
						</li>
						<%
						Order order = (Order)request.getAttribute("order");
						List<Order_details> detailsList = order.getList();
						List<Goods> goodsList = (List<Goods>)request.getAttribute("goodsList");
						Goods goods = null;
						int i = 0;
						double allPrice = 0;
						for(Order_details details : detailsList){
							goods = goodsList.get(i);
						%>
							<li class="end">
								<span class="name">
				               		<a class="blue" href="/huyugo/goods/showGoodsById.do?id=<%=goods.getId() %>"><%=goods.getName() %></a>
				                </span>
								<span class="moneys"><%=goods.getPrice() %></span>
								<span class="money">
									<span class="color">￥<b><%=details.getReal_price() %></b></span>
								</span>
								<span class="num orange Fb" id="span_<%=i %>" style="width: 130px;text-align: left;"><%=details.getAmount() %></span>
								<input type="hidden" id="oneDixianjin<%=i %>" value="<%if(user.getMember() == 1){ %><%=goods.getMember_dixianjin() %><%}else{ %><%=goods.getCommon_dixianjin() %><%} %>" />
								<%if(details.getType()<1){%><span style="float: left;" class="usedixianjin"> <input type="checkbox" name="checkbox_dixianjin" id="checkbox<%=goods.getId() %>" onclick="changePrice();" value="<%if(user.getMember() == 1){ %><%=details.getId() + "_" +goods.getMember_dixianjin() %><%}else{ %><%=details.getId() + "_" +goods.getCommon_dixianjin() %><%} %>" /><%if(user.getMember() == 1){ %><%=goods.getMember_dixianjin() %><%}else{ %><%=goods.getCommon_dixianjin() %><%} %>元</span> <%}
							else{%>
								<span style="float: left;" class="usedixianjin"> <input style="display: none" type="checkbox" name="checkbox_dixianjin" id="checkbox<%=goods.getId() %>" onclick="changePrice();" value="<%if(user.getMember() == 1){ %><%=details.getId() + "_" +goods.getMember_dixianjin() %><%}else{ %><%=details.getId() + "_" +goods.getCommon_dixianjin() %><%} %>" /></span>
								<%}
							%>
								<%if(details.getType()==1){%><span class="all"><%=details.getIntegral() * details.getAmount() %>积分</span> <%}else{%><span class="all"><%=details.getReal_price() * details.getAmount() %></span><%}%>

							</li>
						<%
							allPrice += details.getReal_price() * details.getAmount();
							i++;
						}
						 %>

						<li class="payment_Total">
							<div class="payment_List_Lc">
								<!-- <a href="shoppingCart.html" class="form_ReturnBut">返回购物车修改订单</a> -->
							</div>
							<p class="payment_List_Rc">商品合计：<span class="orange F20" id="allPrice"><%=allPrice %></span> 元</p>
							<p class="payment_List_Rc"><font>可用抵现金:<%=dixianjin %></font></p>
							<p class="payment_List_Rc"><font>可用积分:<%=integral %></font></p>
						</li>
						<li class="payment_Total">
							<div></div>
							<p class="payment_List_Rc">运费：<span class="orange F20" id="yunfei"><%if(allPrice < 99){%><%=order.getFreight() %><%}else{ %>0<%} %></span>元</p>
						</li>
						<!-- 积分 -->
						<!-- <li  class="point_out point_gray">
							<div class="payment_List_Lc">
								<input type="checkbox" class="input_choice"  />使用积分抵扣现金：您的积分(0)本次消费最多可抵扣现金
								<span class="orange Fb">0.00</span>元)，我要使用
								<input type="text" maxlength="8" class="pay_input_text_gray" name="costPoint"  /> 积分
							</div>
							<p  class="pay_Value" style="display:none;"></p>
							<p class="payment_List_Rc"></p>
						</li> -->
						<!-- 积分 -->
						<!-- 余额支付 start-->
						<li class="point_in" >
							<div class="payment_List_Lc">
								<input type="checkbox" name="moneycheckbox" id="MoneyCheckbox" class="input_choice" value="shiyong" />使用账户余额支付，账户余额：
								<span class="green F18"><%=yue %></span>元
							</div>
							<!-- <p style="" class="pay_Value" id="pBalanceTip">
								<span>◆</span><em>◆</em>账户余额支付更快捷，
								<a class="blue" target="_blank" href="">立即充值</a>
							</p> -->
							<input type="hidden" name="yuezhifu_" id="yuezhifu_" value="0" />
							<p class="payment_List_Rc">余额支付：<span  class="orange F20" id="yuezhifu">0.00</span> 元</p>
							<p class="payment_List_Rc">抵现金使用：<span  class="orange F20" id="dixianjinId">0.00</span> 元</p>
							<p class="payment_List_Rc"><font color="red">${errorMsg }</font></p>
						</li>
						<!-- 余额支付 end-->

						<!-- 列出收货地址 -->

						<div style="padding-left:35px;">
						${address }
						</div>

						<input type="hidden" value="0" name="hd_dizhi" />

						<li class="point_in point_bank" style="display:list-item;">
							<div class="payment_List_Lc gary01 Fb" id="yuebuzu"></div>
							<p class="payment_List_Rc">微信支付：<span  class="orange F20" id="weixinzhifu"><%=allPrice + order.getFreight() %></span> 元</p>
						</li>

					</ul>
				</div>

				<div class="pay_bankC" style="display:block;">
					<div class="bank_arrow"><span>◆</span><em>◆</em></div>
					<h3 class="bor">支付平台支付：</h3>
					<ul class="click_img">
						<li>
							<input style="margin: 57px 5px 0 0;" checked="checked" type="radio" value="4" name="account" id="Tenpay">
							<img style="border:1px solid #eee; padding:1px" height="120px" width="120px" src="/huyugo/img/smallImg/payWexi.png">

						</li>
					</ul>
				</div>
				<div class="payment_but" style="margin:15px 0 0 0;">
					<span><font color="red">${errorMsg }</font></span>
					<input type="hidden" name="orderId" value="<%=order.getId() %>">
					<input  class="shop_pay_but" type="submit" name="submit" value="" <%if(nullAddress != null && "null".equals(nullAddress)){ %>disabled="disabled"<%} %> />
				</div>
		</form>
		<div class="answer">
			<h6><span></span>付款遇到问题</h6>
			<ul class="answer_list">
				<li>1、如您未开通网上银行，即可以使用储蓄卡快捷支付轻松完成付款；</li>
				<li>2、如果您没有网银，可以使用银联在线支付，银联有支持无需开通网银的快捷支付和储值卡支付；</li>
				<li>3、如果您有财付通或快钱、手机支付账户，可将款项先充入相应账户内，然后使用账户余额进行一次性支付；</li>
				<li>4、如果银行卡已经扣款，但您的账户中没有显示，有可能因为网络原因导致，将在第二个工作日恢复。</li>
			</ul>
		</div>

		</div>
		<jsp:include page="footer.jsp"></jsp:include>
		<script type="text/javascript">
		function changePrice(){
			var objs = document.getElementsByName("checkbox_dixianjin");
			var s = 0;  //使用抵现金之和
			for(var i=0; i<objs.length; i++){ 
				if(objs[i].checked){
					var price_ = parseFloat($("#oneDixianjin"+i).val()) * parseInt($("#span_"+i).html());
					objs[i].value = objs[i].value.split("_")[0] + "_" + price_;
					s += price_; //如果选中，将value添加到变量s中 
				}
			}
			var allPrice = parseFloat($("#allPrice").html());//商品总价
			//var yuezhifu = parseFloat($("#yuezhifu").html());
			var yuezhifu = 0.0;
			
			$("#dixianjinId").html(s);
			var yunfei = parseFloat($("#yunfei").html());
			var yueBox = $("#MoneyCheckbox");  //余额支付复选框
			if(yueBox.is(":checked")){
				yuezhifu = allPrice - s + yunfei;  //拿出余额支付最多应该支付的金额
				if(parseFloat('<%=yue %>') < yuezhifu){
					yuezhifu = parseFloat('<%=yue %>');
					$("#yuebuzu").html("您的账户余额不足，请选择以下方式完成支付");
				}else{
					$("#yuebuzu").html("");
				}
			}
			
			$("#yuezhifu").html(yuezhifu);
			$("#yuezhifu_").val(yuezhifu);
            $("#weixinzhifu").html((allPrice - s - yuezhifu + yunfei).toFixed(2));
		}
		
		function submitOrder(orderId){
			window.location.href = "/huyugo/order/showOrderPay.do?orderId="+orderId;
		}
		$(function(){
			$("#MoneyCheckbox").change(function(){
				changePrice();
			});
			var allPrice = parseFloat('<%=allPrice %>');
			var yunfei = parseFloat($("#yunfei").html());
			var yue = parseFloat('<%=yue %>');
			if(yue < (allPrice + yunfei)){
				$("#yuebuzu").html("您的账户余额不足，请选择以下方式完成支付");
			}
		}); 
		</script>
	</body>

</html>