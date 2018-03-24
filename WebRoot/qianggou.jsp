<%@page import="com.huyu.entity.Goods"%>
<%@page import="com.huyu.entity.Qianggou"%>
<%@page import="com.huyu.entity.Goods_type"%>
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
<link rel="stylesheet" href="/huyugo/css/List.css" />
<script>
		var num1 = -1;
		
			function getGoodsData(goodsId, bankuai){
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
						var imgid = "";
						var pid = "";
						var delid = "";
						if(bankuai == "qianggou"){
							imgid = "#img" + goodsId;
							pid = "#p" + goodsId;
							delid = "#del" + goodsId;
						}else if(bankuai == "temai"){
							imgid = "#imgtemai" + goodsId;
							pid = "#ptemai" + goodsId;
							delid = "#deltemai" + goodsId;
						}else if(bankuai == "miaosha"){
							imgid = "#imgmiaosha" + goodsId;
							pid = "#pmiaosha" + goodsId;
							delid = "#delmiaosha" + goodsId;
						}else if(bankuai == "cuxiao"){
							imgid = "#imgcuxiao" + goodsId;
							pid = "#pcuxiao" + goodsId;
							delid = "#delcuxiao" + goodsId;
						}else if(bankuai == "tuangou"){
							imgid = "#imgtuangou" + goodsId;
							pid = "#ptuangou" + goodsId;
							delid = "#deltuangou" + goodsId;
						}
						$(imgid).attr("src",json.picture_address);
						$(pid).html(json.name);
						$(delid).html("￥"+json.price);
						
						//全场秒杀
		            	
				    }
				});
			}
		
</script>
</head>

<body>
	<jsp:include page="header.jsp"></jsp:include>


	<!--商品分类列表-->
	<div class="publicBox ListBox">
		<!--面包屑路径-->
		<div class="actricNva clearfix">
			<a href="/huyugo/index.do">首页</a>
			<p>></p>
			<p class="type">抢购商品</p>
		</div>
		<!--分类商品列表-->
		<div class="ListDetails">
			<ul class="clearfix">
				<%
					List<Qianggou> list = (List<Qianggou>)request.getAttribute("list");
					for(Qianggou g : list){
				%>
				<li><a
					href="/huyugo/goods/showGoodsById.do?id=<%=g.getGoods_id() %>&status=qianggou">
						<img id="img<%=g.getGoods_id() %>" />
						<p id="p<%=g.getGoods_id() %>"></p>
						<dt>
							<span></span><span></span>
						</dt>
						<dd>
							<span>¥<%=g.getReal_price() %></span>
							<del id="del<%=g.getGoods_id() %>"></del>
							<em>马上抢</em>
						</dd> </a></li>
						<script>getGoodsData("<%=g.getGoods_id() %>","qianggou");</script>
				<%
					}
				 %>

			</ul>
		</div>
		<!--分类商品列表-->
	</div>
	<!--商品分类列表-->

	<jsp:include page="footer.jsp"></jsp:include>
</body>

</html>