<%@page import="com.huyu.entity.Picture"%>
<%@page import="com.huyu.entity.Goods"%>
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
<meta name="description"
	content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
<title>互余购-综合网购商城</title>
<link rel="stylesheet" href="/huyugo/css/swiper.css" />
<!-- <link rel="stylesheet" href="css/bass.css" /> -->
<!--引入jquery库的jquery-1.10.2.js-->
<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
<!--引入的swiper.min.js特效组件-->
<script type="text/javascript" src="/huyugo/js/swiper.min.js"></script>
<style>
.swiper-pagination-bullet {
	width: 20px;
	height: 20px;
	text-align: center;
	line-height: 20px;
	font-size: 12px;
	color: #fff;
	opacity: 1;
	background: rgba(0, 0, 0, 0.8);
}

.swiper-pagination-bullet-active {
	color: #fff;
	background: #db3752;
}
</style>
<script>
		var num1 = 0;
		</script>
</head>

<body>

	<%
		User user = (User)session.getAttribute("user");
		String message=request.getAttribute("message")+"";
	%>

	<!--签到弹窗内容-->
	<div class="mask">
		<div class="modal">
			<a href="#" class="closeBtn">×</a>
			<h1 class="title_h1 clearfix">
				<span></span><em>已签到</em> <i>您已签到1天</i>
			</h1>
			<h2 class="title_h2">
				您获得抵现金<span>10元</span>
			</h2>
		</div>
	</div>
	<!--签到弹窗内容-->
	<!--主体部分-->
	<div class="mBodyContent">
		<!-- 头部引入 -->
		<jsp:include page="header.jsp"></jsp:include>
		<!-- 头部引入结束 -->
		<!--，轮播，签到部分-->

		<div class="publicBox banner clearfix">
			<!--签到-->
			<div class="signbox">
				<img class="signboxImg" src="/huyugo/img/smallImg/qitu.png" />
				<div class="checkin" style="margin-top: 35px"></div>
				<%
					if(user == null){
				%>
				<!--登录前-->
				<div class="Logigdd" style="display: block;">
					<form action="/huyugo/user/login.do?type=2" method="post">
						<!-- <div class="uesImg clearfix">
								<label>
								    <div class="ImgKi"><img src="/huyugo/img/smallImg/use.png"/></div>
								    <input class="useinput" name="phone" type="text" placeholder="请输入手机号" />
								</label>
							</div>
							<div class="uesPass clearfix">
								<label>
								     <div  class="ImgKi"><img src="/huyugo/img/smallImg/pass.png"/></div>
								     <input class="usepassword" type="password" name="password" placeholder="请输入登录密码" />
								</label>
							</div> -->
						<div class="useBtns">
							<input type="submit" value="立即登录"
								style="height: 35px;background: #f9de87;color: #6c0000;outline: none;border: none;padding: 4px;margin-left: 20px;margin-right: 10px;cursor: pointer;border-radius: 4px;" />
							<a href="/huyugo/user/reg.do">还没有注册？</a>
						</div>
					</form>
				</div>
				<!--登录前-->
				<%
					}else{
				%>
				<!--登录后-->
				<div class="dengluOk">
					<div class="UiOkList clearfix">
						<div class="renLoi">
							<img src="img/smallImg/re.png" />
						</div>
						<div class="renPoic">
							<p>hi,欢迎登录互余购</p>
							<!-- <a href="/huyugo/user/reg.do"><img src="img/smallImg/qiandao2.png" />注册会员</a> -->
							<a href="/huyugo/user/loginOut.do">[退出]</a>
							<%if(message.length()>5){%><p><%=message%></p> <%}%>
							<!-- <div class="fuliBOx">
										<a href="">新人福利</a>
									</div> -->
						</div>
					</div>
				</div>
				<!--登录后-->
				<%
					}
				%>

			</div>
			<!--签到-->
			<div class="carouselbox">
				<div class="swiper-container">
					<div class="swiper-wrapper">
						<%
							List<Picture> bannerList = (List<Picture>)request.getAttribute("bannerList");
										for(Picture picture : bannerList){
						%>
						<div class="swiper-slide">
							<a
								href="http://<%if(picture.getUrl() != null){out.print(picture.getUrl());}%>"><img
								src="<%=picture.getPicture()%>" /> </a>
						</div>
						<%
							}
						%>
					</div>
					<!-- Add Pagination -->
					<div class="swiper-pagination"></div>
				</div>
			</div>
		</div>
		<!--，轮播，签到部分-->
		<!--赚积分      积分大转盘       今日超值兑换-->
		<div class="publicBox jifenBox clearfix">

			<div class="jifenBoxContsUi clearfix">

				<div onclick="window.location.href='/huyugo/luck/showLuck.do'"
					class="jifenBoxContent"
					style="width:100%; height: 320px;margin-left: 0px;margin-right: 0px;">
					<img src="/huyugo/img/banner/luck.png" />
				</div>
			</div>
		</div>
		<!--赚积分      积分大转盘       今日超值兑换-->
		<!--淘  实惠  购  新潮  逛  品牌-->
		<div class="publicBox trendy clearfix">
			<div class="trendyTitle">
				<ul>
					<li>淘 <span>&bull;</span> 实惠</li>
					<li>购 <span>&bull;</span> 新潮</li>
					<li>逛 <span> &bull;</span> 品牌</li>
				</ul>
			</div>
			<div class="trendyTitleList">
				<!--循环的列表-->
				<div class="trendyList1">
					<ul class="clearfix">
						<li class="pos1"><a href=""> <img
								src="/huyugo/img/zuixin1.png" /> </a>
						</li>
						<%
							List<Goods> newGoodsList = (List<Goods>)request.getAttribute("newGoodsList");
											int i = 0;
											for(Goods goods : newGoodsList){
											if(i<4&&goods.getDisplay()!=1){
						%>
						<li><a
							href="/huyugo/goods/showGoodsById.do?id=<%=goods.getId()%>">
								<img src="<%=goods.getPicture_address()%>" />
								<p><%=goods.getName()%></p> <!-- <dt><span>7680积分抵扣+</span><span>18元</span></dt> -->
								<dd>
									<span>¥<%=goods.getPrice()%></span>
									<del>
										¥<%=goods.getCost_price()%></del>
								</dd> </a>
						</li>
						<%
							}
												i++;
											}
						%>
					</ul>
				</div>

				<div class="trendyList1">
					<ul class="clearfix">
						<li class="pos2"><a
							href="/huyugo/list/showList.do?typeId=0&sort=2"> <img
								src="/huyugo/img/zuixin2.png" /> </a>
						</li>
						<%
							i = 0;
											for(Goods goods : newGoodsList){
											if(i>3&&goods.getDisplay()!=1){
						%>
						<li><a
							href="/huyugo/goods/showGoodsById.do?id=<%=goods.getId()%>">
								<img src="<%=goods.getPicture_address()%>" />
								<p><%=goods.getName()%></p> <!-- <dt><span>7680积分抵扣+</span><span>18元</span></dt> -->
								<dd>
									<span>¥<%=goods.getPrice()%></span>
									<del>
										¥<%=goods.getCost_price()%></del>
								</dd> </a>
						</li>
						<%
							}
												i++;
											}
						%>
					</ul>
				</div>
			</div>
		</div>
		<!--淘  实惠  购  新潮  逛  品牌-->

		<div class="publicBox tupIc pos7">
			<a href=''><img src="img/commodity/d.png" /> </a>
		</div>

		<!--最热人气     更多-->
		<div class="publicBox hottestPopularity">
			<div class="hottestPopularityTitle clearfix">
				<h5 style="color: orangered;">最热人气</h5>
				<a
					href="/huyugo/list/goodsTypeList.do?typeId=0&sort=4&goods_type_id=0"
					style="color: orangered;">更多&gt;&gt;</a>
			</div>
			<div class="hottestPopularityList">
				<!--循环的列表-->
				<div class="trendyList1 orgred">
					<ul class="clearfix">
						<li class="pos3"><a
							href="/huyugo/list/goodsTypeList.do?typeId=0&sort=4&goods_type_id=0">
								<img src="/huyugo/img/renqi1.png" /> </a>
						</li>
						<%
							List<Goods> hotGoodsList = (List<Goods>)request.getAttribute("hotGoodsList");
											i = 0;
											for(Goods goods : hotGoodsList){
											if(i<4&&goods.getDisplay()!=1){
						%>
						<li><a
							href="/huyugo/goods/showGoodsById.do?id=<%=goods.getId()%>">
								<img src="<%=goods.getPicture_address()%>" />
								<p><%=goods.getName()%></p> <!-- <dt><span>7680积分抵扣+</span><span>18元</span></dt> -->
								<dd>
									<span>¥<%=goods.getPrice()%></span>
									<del>
										¥<%=goods.getCost_price()%></del>
								</dd> </a>
						</li>
						<%
							}
												i++;
											}
						%>
					</ul>
				</div>

				<div class="trendyList1 orgred">
					<ul class="clearfix">
						<li class="pos4"><a
							href="/huyugo/list/goodsTypeList.do?typeId=0&sort=4&goods_type_id=0">
								<img src="/huyugo/img/renqi2.png" /> </a>
						</li>
						<%
							i = 0;
											for(Goods goods : hotGoodsList){
											if(i>3&&goods.getDisplay()!=1){
						%>
						<li><a
							href="/huyugo/goods/showGoodsById.do?id=<%=goods.getId()%>">
								<img src="<%=goods.getPicture_address()%>" />
								<p><%=goods.getName()%></p> <!-- <dt><span>7680积分抵扣+</span><span>18元</span></dt> -->
								<dd>
									<span>¥<%=goods.getPrice()%></span>
									<del>
										¥<%=goods.getCost_price()%></del>
								</dd> </a>
						</li>
						<%
							}
												i++;
											}
						%>
					</ul>
				</div>
			</div>

		</div>
		<!--最热人气     更多-->

		<div class="publicBox tupIc pos8" style="overflow: hidden;">
			<a href=''><img src="img/commodity/d.png" /> </a>
		</div>

		<!--推荐商品   更多-->
		<div class="publicBox toyseries">
			<div class="hottestPopularityTitle clearfix">
				<h5 style="color: #75a900;">推荐商品</h5>
				<a
					href="/huyugo/list/goodsByTuiJian.do?typeId=0&sort=0&goods_type_id=0"
					style="color: #75a900;">更多&gt;&gt;</a>
			</div>
			<div class="hottestPopularityList">
				<!--循环的列表-->
				<div class="trendyList1">
					<ul class="clearfix">
						<li class="pos5"><a
							href="/huyugo/list/goodsByTuiJian.do?typeId=0&sort=0&goods_type_id=0">
								<img src="/huyugo/img/temai1.png" /> </a>
						</li>
						<%
							List<Goods> recommendGoodsList = (List<Goods>)request.getAttribute("recommendGoodsList");
											i = 0;
											for(Goods goods : recommendGoodsList){
											if(i<4&&goods.getDisplay()!=1){
						%>
						<li><a
							href="/huyugo/goods/showGoodsById.do?id=<%=goods.getId()%>">
								<img src="<%=goods.getPicture_address()%>" />
								<p><%=goods.getName()%></p> <!-- <dt><span>7680积分抵扣+</span><span>18元</span></dt> -->
								<dd>
									<span>¥<%=goods.getPrice()%></span>
									<del>
										¥<%=goods.getCost_price()%></del>
								</dd> </a>
						</li>
						<%
							}
												i++;
											}
						%>
					</ul>
				</div>

				<div class="trendyList1">
					<ul class="clearfix">
						<li class="pos6"><a
							href="/huyugo/list/goodsByTuiJian.do?typeId=0&sort=0&goods_type_id=0">
								<img src="/huyugo/img/temai2.png" /> </a>
						</li>
						<%
							i = 0;
											for(Goods goods : recommendGoodsList){
											if(i>3&&goods.getDisplay()!=1){
						%>
						<li><a
							href="/huyugo/goods/showGoodsById.do?id=<%=goods.getId()%>">
								<img src="<%=goods.getPicture_address()%>" />
								<p><%=goods.getName()%></p> <!-- <dt><span>7680积分抵扣+</span><span>18元</span></dt> -->
								<dd>
									<span>¥<%=goods.getPrice()%></span>
									<del>
										¥<%=goods.getCost_price()%></del>
								</dd> </a>
						</li>
						<%
							}
												i++;
											}
						%>
					</ul>
				</div>
			</div>

		</div>
		<!--推荐商品   更多-->
		<style>
.trendyList1 ul.like li:nth-of-type(1) img {
	display: block;
	width: 100%;
	height: 210px !important;
}

.trendyList1 ul li {
	float: left;
	width: 227px;
	/* margin-left: 30px; */
	/* height: 305px; */
	margin: 10px;
	border: 1px solid #cfcfcf;
	border-top: none;
	border-radius: 4px;
	overflow: hidden;
	padding: 4px 2px 17px -7px;
}
</style>
		<!--猜你喜欢   更多-->
		<div class="publicBox toyseries">
			<div class="hottestPopularityTitle clearfix">
				<h5 style="color: #75a900;">猜你喜欢</h5>
				<!-- <a
					href="/huyugo/list/goodsByTuiJian.do?typeId=0&sort=0&goods_type_id=0"
					style="color: #75a900;">更多&gt;&gt;</a> -->
			</div>
			<div class="hottestPopularityList">
				<!--循环的列表-->
				<div class="trendyList1">
					<ul class="clearfix like">
						<!-- <li class="pos5">
								<a href="/huyugo/list/goodsByTuiJian.do?typeId=0&sort=0&goods_type_id=0">
									<img src="/huyugo/img/temai1.png" />
								</a>
							</li> -->
						<%
							List<Goods> goodsByClickNum = (List<Goods>)request.getAttribute("goodsByClickNum");
											i = 0;
											for(Goods goods : goodsByClickNum){
											if(goods.getDisplay()!=1&&i<100){
						%>
						<li><a
							href="/huyugo/goods/showGoodsById.do?id=<%=goods.getId()%>">
								<img src="<%=goods.getPicture_address()%>" />
								<p><%=goods.getName()%></p> <!-- <dt><span>7680积分抵扣+</span><span>18元</span></dt> -->
								<dd>
									<span>¥<%=goods.getPrice()%></span>
									<del>
										¥<%=goods.getCost_price()%></del>
								</dd> </a>
						</li>
						<%
							}
												i++;
											}
						%>
					</ul>
				</div>

			</div>

		</div>
		<!--猜你喜欢    更多-->

		<!--底部导入开始-->
		<jsp:include page="footer.jsp"></jsp:include>
		<!--底部导入结束-->

		<!--主体部分-->
	</div>
	<script type="text/javascript">
		
			PosterHtml(".pos1",1);
			PosterHtml(".pos2",2);
			PosterHtml(".pos3",3);
			PosterHtml(".pos4",4);
			PosterHtml(".pos5",5);
			PosterHtml(".pos6",6);
			
			PostertHtml(".pos7",7);
			PostertHtml(".pos8",8);
			
			function PostertHtml(classes,id){
				$.post("/huyugo/adminposter/getid.do",{id:id},function(res){
					if (res[0]){
						var html = "<a href='http://"+res[0].url+"'><img src='"+res[0].path+"'></a>";
						$(classes).html(html);
					}
				},"json");
			}
		
			function PosterHtml(classes,id){
				$.post("/huyugo/adminposter/getid.do",{id:id},function(res){
					if (res[0]){
						var html = "<li><a href='http://"+res[0].url+"'><img src='"+res[0].path+"'></a></li>";
						$(classes).html(html);
					}
				},"json");
			}
		</script>
	<script type="text/javascript" src="/huyugo/js/rl.js"></script>
	<script>
			//轮播图
			var swiper = new Swiper('.swiper-container', {
				pagination: '.swiper-pagination',
				paginationClickable: true,
				spaceBetween: 30,
				centeredSlides: true,
				autoplay: 2500,
				autoplayDisableOnInteraction: false,
				paginationBulletRender: function(swiper, index, className) {
					return '<span class="' + className + '">' + (index + 1) + '</span>';
				}
			});
			//签到
			$(".checkin").Checkin();
			//登录前
			var phoned = /^1[34578]\d{9}$/; //联系电话正则
			$(".useBtns button").click(function(){
				if(phoned.test($(".useinput").val())== false){
					alert("手机号输入有错")
					return
				}
				if($(".usepassword").val()==null||$(".usepassword").val()==undefined||$(".usepassword").val()==""){
					alert("请输入登录密码")
					return
				}
				window.location.href="/huyugo/user/login.do";
			})
			
//			useBtns button
		</script>
</body>

</html>