<%@page import="com.huyu.entity.Goods"%>
<%@page import="com.huyu.entity.Car"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta name="keywords" content="互余，互余网、互余购、万州商城，直购商城，休闲娱乐" />
		<meta name="description" content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
		<title>互余购-综合网购商城</title>
		<link rel="stylesheet" type="text/css" href="/huyugo/css/CartList.css" />
		<link rel="stylesheet" href="/huyugo/css/myCart.css" />
		<link href="/huyugo/img/f.png" rel="shortcut icon" type="image/x-icon"/>
		<script>
		var num1 = -1;
		</script>
	</head>

	<body>
		
		<jsp:include page="header.jsp"></jsp:include>
		
		<div class="shop_process">
			<ul class="process">
				<li class="first_step">第一步：提交订单</li>
				<li class="arrow_1"></li>
				<li class="secend_step">第二步：网银支付</li>
				<li class="arrow_2"></li>
				<li class="third_step">第三步：支付成功 </li>
				<li class="arrow_2"></li>
				<li class="fourth_step">第四步：购买成功</li>
			</ul>
			<div class="i_tips"></div>
			<div class="submitted">
				<div id="content">
					<table width="100%" border="0" cellspacing="0" cellpadding="0" id="shopping">
						<tr>
							<td class="title_2"><input type="checkbox" data="0" onclick="quanxuan(this)"></td>
							<td class="title_2">商品</td>
							<td class="title_2" style="text-align: left;text-indent: 30px;">名称</td>
							<td class="title_2">价值</td>
							<td class="title_3">获积分</td>							
							<td class="title_4">现价（元）</td>
							<td class="title_5">数量</td>
							<td class="title_6">小计（元）</td>
							<td class="title_7">操作</td>
						</tr>
						<tr>
							<td colspan="9" class="line"></td>
						</tr>
						<%
						String msg = (String)request.getAttribute("msg");
						List<Car> carList = (List<Car>)request.getAttribute("carList");
						List<Goods> goodsList = (List<Goods>)request.getAttribute("goodsList");
						Goods goods = null;
						int i = 1;
						for(Car car : carList){
							goods = goodsList.get(i-1);
							%>
							<tr id="product<%=i%>">
								<td class="cart_td_2"><input type="checkbox" name='goods' data-id='<%=goods.getId()%>'></td>
								<td class="cart_td_2">
									<a href="/huyugo/goods/showGoodsById.do?id=<%=car.getGoods_id()%>"><img src="<%=goods.getPicture_address() %>" alt="shopping" width="80" height="80" /></a>
								</td>
								<td class="cart_td_3">
									<a href="/huyugo/goods/showGoodsById.do?id=<%=car.getGoods_id()%>"><%=goods.getName() %></a>
									<%if(!car.getColor_norms().equals(",")) {%>颜色/尺码：<%=car.getColor_norms() %><%} else{%>颜色/尺码：默认   <%} %><br /> 原价：￥<%=goods.getCost_price() %>
									<br />保障：
									<img src="/huyugo/img/taobao_icon_01.jpg" alt="icon" />
									<img src="/huyugo/img/taobao_icon_02.jpg" alt="icon" />
								</td>
								<td class="cart_td_4">￥<%=goods.getPrice() %></td>
								<td class="cart_td_4 cart_td_41"><%=goods.getCommon_integral() %></td>
								<td class="cart_td_5"><%=car.getReal_price() %></td>
								<td class="cart_td_6">
									<img src="/huyugo/img/taobao_minus.jpg" alt="minus" onclick="changeNum('num_<%=i %>','minus','<%=car.getId() %>','<%=car.getReal_price()%>')" class="hand" />
									<input id="num_<%=i %>" type="text" value="<%=car.getAmount() %>" class="num_input" readonly="readonly" />
									<img src="/huyugo/img/taobao_adding.jpg" alt="add" onclick="changeNum('num_<%=i %>','add','<%=car.getId() %>','<%=car.getReal_price()%>')" class="hand" />
								</td>
								<td class="cart_td_7"></td>
								<td class="cart_td_8">
									<a href="javascript:deleteRow('product<%=i %>','<%=car.getId() %>');">删除</a>
								</td>
							</tr>
							<%
							i++;
						}
						 %>

						<tr>
							<td colspan="4"><%=msg %></td>
							<td colspan="1"></td>
							<td colspan="3" class="shopend">商品总价（不含运费）：
								<label id="total" class="yellow"></label> 元<br /> 可获积分 <label class="yellow" id="integral"></label> 点<br />
							</td>
						</tr>
					</table>
					<script type="text/javascript">
						
						 
					</script>

				</div>
				<h5>
					<a href="/huyugo/index.do" id="but_on"></a>
					<input id="but_ok" type="button" value="" name="submit" onclick="carSubmit('<%=i %>');">
				</h5>
			</div>

		</div>
		
		<jsp:include page="footer.jsp"></jsp:include>
		
        <script src="/huyugo/js/myCart.js"></script>
	</body>

</html>