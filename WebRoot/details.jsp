<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.huyu.entity.Cuxiao"%>
<%@page import="com.huyu.entity.Miaosha"%>
<%@page import="com.huyu.entity.Qianggou"%>
<%@page import="com.huyu.entity.Tuangou"%>
<%@page import="com.huyu.entity.Temai"%>
<%@page import="com.huyu.entity.Picture"%>
<%@page import="com.huyu.entity.Goods"%>
<%@page import="com.huyu.util.OrderNum"%>
<%@page import="com.huyu.entity.Integral_shop"%>
<%@page import="com.huyu.entity.Vip_shop"%>
<%@page import="com.huyu.entity.Ticket_shop"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

Goods goods = (Goods)request.getAttribute("goods");
List<Picture> picList = (List<Picture>)request.getAttribute("picList");
String msg = (String)request.getAttribute("activity");
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<link href="/huyugo/img/f.png" rel="shortcut icon" type="image/x-icon"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta name="keywords" content="互余，互余网、互余购、云购、返还购买、万州商城，直购商城，休闲娱乐" />
		<meta name="description" content="<%=goods.getName() %>" />
		<title><%=goods.getName() %></title>
		<link rel="stylesheet" href="/huyugo/css/bass.css" />
		<link rel="stylesheet" href="/huyugo/css/swiper.css" />
		<link rel="stylesheet" href="/huyugo/css/Comm.css" />
		<link rel="stylesheet" href="/huyugo/css/header.css" />
		<link rel="stylesheet" href="/huyugo/css/footer.css" />
		<link rel="stylesheet" href="/huyugo/css/details.css" />
		<link rel="stylesheet" href="/huyugo/css/tuijian.css" />
		<script type="text/javascript" src="/huyugo/js/swiper.min.js"></script>
		<style>
			.box img {
				vertical-align: top;
				border: 0;
			}
			/* box */
			
			.box {
				width: 400px;
				overflow: hidden;
				float: left;
			}
			
			.tb-pic a {
				display: table-cell;
				text-align: center;
				vertical-align: middle;
			}
			
			.tb-pic a img {
				display: block;
				width: 100%;
				height: 100%;
				vertical-align: middle;
			}
			
			.tb-pic a {
				*display: block;
				*font-family: Arial;
				*line-height: 1;
				cursor: pointer;
			}
			
			.tb-thumb {
				width: 45px;
				height: 300px;
				position: absolute;
				overflow: hidden;
				top: 20px;
			}
			
			.tb-thumb li {
				background: none repeat scroll 0 0 transparent;
				float: left;
				height: 42px;
				margin: 4px 0px;
				overflow: hidden;
				padding: 1px;
			}
			
			.tb-s310,
			.tb-s310 a {
				height: 310px;
				width: 400px;
				margin: 4px 0px;
			}
			
			.tb-s310,
			.tb-s310 img {
				max-height: 310px;
				max-width: 400px;
			}
			
			.tb-s310 a {
				*font-size: 271px;
			}
			
			.tb-s40 a {
				*font-size: 35px;
			}
			
			.tb-s40,
			.tb-s40 a {
				height: 40px;
				width: 40px;
			}
			
			.tb-booth {
				border: 1px solid #CDCDCD;
				position: relative;
				z-index: 1;
				margin-left: 45px;
			}
			
			.tb-thumb .tb-selected {
				background: none repeat scroll 0 0 #C30008;
				height: 42px;
				padding: 1px;
			}
			
			.tb-thumb .tb-selected div {
				background-color: #FFFFFF;
				border: medium none;
			}
			
			.tb-thumb li div {
				border: 1px solid #CDCDCD;
			}
			#shop{
				width: 65px;
				height: 25px;
				position: absolute;
				left: 117px;
				border-radius: 5px;
				bottom: -420px;
				background-color: #9E4EE4;
			}
			
			.zoomDiv {
				z-index: 999;
				position: absolute;
				top: 0px;
				left: 0px;
				width: 200px;
				height: 200px;
				background: #ffffff;
				border: 1px solid #CCCCCC;
				display: none;
				text-align: center;
				overflow: hidden;
			}
			
			.zoomMask {
				position: absolute;
				background: url("/huyugo/img/smallImg/mask.png") repeat scroll 0 0 transparent;
				width: 100px;
				height: 100px;
				cursor: move;
				z-index: 1;
			}
			.ListContsmsg  dl dd em{
				padding:0px !important;
			}
		</style>
		<script>
		var num1 = -1;
		</script>
	</head>

	<body>
		<%
			Goods g = (Goods)request.getAttribute("goods");
			if(g.getDisplay() == 1){
		 %>
		 <script type="text/javascript">
		 	alert("该商品已下架！");
		 	window.history.go(-1);
		 </script>
		<%
		} %>		 
		<%
			if(g.getAmount() < 1){
		 %>
		 <script type="text/javascript">
		 	alert("该商品暂缺货！");
		 	window.history.go(-1);
		 </script>
		<%
		} %>		 
		<jsp:include page="header.jsp"></jsp:include>
		<!--面包屑导航-->
		<div class="crumbs">
			<div class="publicBox">
				<ul class="clearfix">
					<li>
						<a href="/huyugo/index.do">首页</a>
					</li>
					<li>></li>
					<li>
						<a href="/huyugo/list/showList.do?typeId=${goodsType.id }">${goodsType.type }</a>
					</li>
					<li>></li>
					<li>
						<a href="/huyugo/list/goodsTypeList.do?typeId=${goodsType.id }&goods_type_id=${goodsTypeType.id }&sort=0">${goodsTypeType.type }</a>
					</li>
					<li>></li>
					<li>商品详情</li>
				</ul>
			</div>
		</div>
		<!--面包屑导航-->
		
		<!--商品详情介绍-->
		<div class="publicBox detailsList clearfix">
			<!--放大镜效果-->
			<div class="box">
				
				<!--大图-->
				<div class="tb-booth tb-pic tb-s310 pull-left">
					<a href="${goods.picture_address }">
						<img src="${goods.picture_address }" rel="${goods.picture_address }" class="jqzoom" />
					</a>
				</div>
				<!--大图-->
				<!--小图-->
				<ul class="tb-thumb" id="thumblist">
					<%
					int i = 0;
					for(Picture pic : picList){
						
						%>
						
						<li <%if(i == 0){ %>class="tb-selected"<%} %>>
							<div class="tb-pic tb-s40">
								<a>
									<img src="<%=pic.getPicture() %>" mid="<%=pic.getPicture() %>" big="<%=pic.getPicture() %>">
	
								</a>
							</div>
						</li>
						<%
						i++;
					}
					 %>
					
				</ul>
				<!--小图-->
				<!--分享-->
				<div class="fenxiang">
					<p class="bdshare-button-style0-16">
						<a><span style="color:red;padding-right: 6px;">分享送抵现金</span>分享到：</a>
						<!-- JiaThis Button BEGIN -->
						<div class="jiathis_style_24x24" style="padding-top: 10px;">
							<a class="jiathis_button_qzone songjif_" ></a>
							<a class="jiathis_button_tsina songjif_"></a>
							<a class="jiathis_button_weixin songjif_"></a>
							<a class="jiathis_button_cqq songjif_"></a>
							<!--<a href="http://www.jiathis.com/share" class="jiathis jiathis_txt jtico jtico_jiathis" target="_blank"></a>
							<a class="jiathis_counter_style"></a>-->
						</div>
						<script type="text/javascript" src="http://v3.jiathis.com/code/jia.js" charset="utf-8"></script>
						<!-- JiaThis Button END -->
					</p>
				</div>
				<!--分享-->
			</div>
			<!--放大镜效果-->
			<%
			String status_ = (String)request.getAttribute("status");
			String status_ch = "";
			double real_price = goods.getPrice();
			if(status_ != null && !"".equals(status_)){
				if(status_.equals("temai")){
					Temai temai = (Temai)request.getAttribute("temai");
					status_ch = "正在特卖";
					real_price = temai.getReal_price();
				}else if(status_.equals("tuangou")){
					Tuangou tuangou = (Tuangou)request.getAttribute("tuangou");
					status_ch = "正在团购";
					real_price = tuangou.getReal_price();
				}else if(status_.equals("qianggou")){
					Qianggou qianggou = (Qianggou)request.getAttribute("qianggou");
					status_ch = "正在抢购";
					//out.print(qianggou);
					real_price = qianggou.getReal_price();
				}else if(status_.equals("miaosha")){
					Miaosha miaosha = (Miaosha)request.getAttribute("miaosha");
					boolean flag =  OrderNum.largerTime(new SimpleDateFormat("yyyy/MM/dd HH:mm").format(new Date()),miaosha.getStart_time(), "yyyy/MM/dd HH:mm");
					if(flag){
						%>
					<script type="text/javascript">window.location.href="/huyugo/goods/showGoodsById.do?id=<%=miaosha.getGoods_id()%>";</script>
						<%
					}else{
						status_ch = "正在秒杀";
					}
					real_price = miaosha.getReal_price();
				}else if(status_.equals("cuxiao")){
					Cuxiao cuxiao = (Cuxiao)request.getAttribute("cuxiao");
					status_ch = "正在促销";
					real_price = cuxiao.getReal_price();
				}else if(status_.equals("integral")){
						Integral_shop integral = (Integral_shop) request.getAttribute("integral");
						status_ch = "积分兑换";
						real_price = integral.getIntegral();
					}else if(status_.equals("vipshop")){
						Vip_shop vipshop = (Vip_shop) request.getAttribute("vipshop");
						status_ch = "VIP专属";
						real_price = vipshop.getReal_price();
					}else if(status_.equals("ticketshop")){
						Ticket_shop cuxiao = (Ticket_shop) request.getAttribute("ticketshop");
						status_ch = "免费购买";
						real_price =0;
					}
			}else{
				status_ = "";
			}
			
			 %>
			<div id="scrollbox" class="ListContsmsg">
				<h3>${goods.name }</h3>
				<p>商品编号：${goods.id }<%if(msg != null){%>&nbsp;&nbsp;&nbsp;&nbsp;<span style="color:red;"><%=msg %></span><%}%></p>
				<input type="hidden" id="goodsId" value="${goods.id }" />
				<dl>
					<!--传值开始时间2017/06/25 16:00-->
					<input type="hidden" id="kaishim0" value="" />
					<!----传值截至时间2017/06/25 22:00-->
					<input type="hidden" id="hdfTm0" value="" />
					<dd>原价：¥${goods.price }<%if(!"".equals(status_ch)){ %><span id="qianggoujieshu" style="float: right;margin-right: 20px;"><%=status_ch %>&nbsp;&nbsp;<small>优惠价格：</small><%if(!"积分兑换".equals(status_ch)){%>¥ <%}%><%=real_price %><%if("积分兑换".equals(status_ch)){%>积分 <%}%></span><%} %></dd>
					<!--id=km0显示开始时间剩余时间-->
					<!--id=tm0显示截至剩余时间-->
					<dd>普通会员： 送 <b><%=goods.getCommon_integral() %></b>积分  &nbsp;<b><%=goods.getCommon_huyubi() %></b>互余币 <%if(!"积分兑换".equals(status_ch)&&!"VIP专属".equals(status_ch)&&!"免费购买".equals(status_ch)){%>&nbsp;此商品可用<b><%=goods.getCommon_dixianjin() %></b>元抵现金 <%}%> </dd>
					<dd>Vip会员： 送<b><%=goods.getMember_integral() %></b>积分  &nbsp;<b><%=goods.getMember_huyubi() %></b>互余币  <%if(!"积分兑换".equals(status_ch)&&!"VIP专属".equals(status_ch)&&!"免费购买".equals(status_ch)){%>&nbsp;此商品可用<b><%=goods.getMember_dixianjin() %></b>元抵现金<%}%> </dd>
					<dd>运费：<%=goods.getFreight() %>元</dd>
					<dd class="color">颜色：
					<%
					String colors = goods.getColor();
					if(colors == null || "".equals(colors)){
						%><em></em><%
					}else{
						if(colors.indexOf("#") > -1){
							i = 0;
							for(String color : colors.split("#")){
								%><em <%if(i == 0){ %>class="emok"<%} %>><%=color %></em><%
								i++;
								if(i%10 == 0){
									%>
										<br/>
										<br/>
									<%
								}
							}
						}else{
							%><em class="emok"><%=colors %></em><%
						}
					}
					 %>
					</dd>
					<dd class="guige">规格： 
					<%
					String guiges = goods.getNorms();
					if(guiges != null && !"".equals(guiges)){
						if(guiges.indexOf("#") > -1){
							for(String guige : guiges.split("#")){
								%><i><%=guige %></i><%
							}
						}else{
							%><i><%=guiges %></i><%
						}
					}
					 %>
					</dd>
					<dd>库存：${goods.amount }</dd>
					<dt class="message"></dt>
				</dl>
				<input type="hidden" id="color_" name="color_" value="" />
				<input type="hidden" id="guige_" name="guige_" value="" />
				<button class="addcar">加入购物车</button>
				<button class="lijizhifu" style="background: rgb(158,78,228);">立即购买</button>
			</div>
			<div style="position: relative;"><p style="position: absolute;left: 10px;bottom: -380px;">供货商：<span class='ghs'></span>&nbsp;&nbsp;&nbsp;联系方式：<span class="ghsphone"></span></p><a id="shop" target="_blank">进店逛逛</a></div>

			<script type="text/javascript">
				$(function(){
					$.post("/huyugo/adminshop/getid.do",{id:${goods.shopId}},function(res){
						$(".ghs").html(res[0].name);
						$("#shop").attr("href","/huyugo/adminshop/goodsList.do?id="+res[0].id);
						$(".ghsphone").html(res[0].phone);
					},"json");
				});
			</script>
			<div class="clearfix"></div>
			<div class="detailsAll clearfix">
				<div class="detailsAllLeft">
					<h3>最新商品推荐</h3>
					<ul>
						<%
						List<Goods> recommendList = (List<Goods>)request.getAttribute("recommendList");
						for(Goods recommendGoods : recommendList){
							%>
							<li>
								<a href="/huyugo/goods/showGoodsById.do?id=<%=recommendGoods.getId()%>">
									<img class="zoom" src="<%=recommendGoods.getPicture_address() %>" />
									<p><%=recommendGoods.getGoods_describe() %></p>
									<dd>¥<%=recommendGoods.getPrice() %></dd>
								</a>
							</li>
							<%
						}
						%>
						
					</ul>
				</div>
				<div class="detailsAllRight">
					<h3 class="detailsAllRight-title"><span class="active">商品详情介绍</span><span>评论</span></h3>
					<div class="detailsAllRight-box">${goods.details}</div>
					<!-- 商品评价 -->
					<div class="detailsAllRight-box details-plbox" style="display:none;">
					<ul class="pingjia">
						<!-- <li class="details-plbox-li clearfix">
						<span class="details-plbox-left">
							<img alt="" src="../img/head.jpg" class="details-plbox-head" />
							<span class="details-plbox-name">你的名字</span>
						</span><span class="details-plbox-right"></span></li> -->
					</ul>
					<script type="text/javascript">
					
						getpinjia();
						
						function getpinjia(){
							$.ajax({
								url:'/huyugo/goodsComment/getCommentByGoodsId.do',
								type:'post',
								data:{goodsId:${goods.id}},
								dataType:"json",
								success:function(res){
									console.log(res);
									var html = "";
									$(res.list).each(function(index,obj){
										var user = "";
										$.ajax({
											url:'/huyugo/user/getUserById.do',
											type:'post',
											data:{userId:obj.user_id},
											async:false,
											dataType:'json',success:function(res){
												user = res;
											}
										});
										var phone = user.phone.substring(0,3) +"***"+ user.phone.substring(8,user.phone.length);
										var img = "../img/head.jpg";
										html+="<li class='details-plbox-li clearfix'><span class='details-plbox-left'><img src='"+img+"' class='details-plbox-head' /><span class='details-plbox-name'>"+phone+"</span></span><span class='details-plbox-right'>"+obj.comment+"</span></li>";
									});
									console.log(html);
									$(".pingjia").html(html);
								}
							});
						}
					
					</script>
					
					<!-- 翻页 -->
					<!-- <div class="page-box mr10 clearfix">
					 	<span>上一页</span>
					 	<span class="page-num active">1</span>
					 	<span class="page-num">2</span>
					 	<span class="page-num">3</span>
					 	<span>下一页</span>
					 </div> -->
					</div>
				 </div>
			</div>
		</div>
	</div>

		<!--同类商品推荐-->
		<div class="publicBox tuijianboxTitle">
			<span>推荐</span><em>同类商品</em>
		</div>
		<div class="tuijianbox">

			<div class="swiper-container">
				<div class="swiper-wrapper">
					<%
					List<Goods> typeGoodsList = (List<Goods>)request.getAttribute("typeGoodsList");
					for(Goods typeGoods : typeGoodsList){
						%>
						<div class="swiper-slide">
							<a href="/huyugo/goods/showGoodsById.do?id=<%=typeGoods.getId()%>">
								<img class="zoom" src="<%=typeGoods.getPicture_address() %>" />
								<p><%=typeGoods.getGoods_describe() %></p>
								<div>¥<%=typeGoods.getPrice() %></div>
							</a>
						</div>
						<%
					}
					 %>
					
				</div>
				<!-- Add Arrows -->
				<div class="swiper-button-next"></div>
				<div class="swiper-button-prev"></div>
			</div>
		</div>
		<!--同类商品推荐-->
		<!--商品详情介绍-->

		<jsp:include page="footer.jsp"></jsp:include>
		<!--右边悬浮内容-->
		<div id="divRighTool" class="quickBack">
			<dl class="quick_But">
				<dd class="quick_cart" style="">
					<a href="/huyugo/car/showCarByUser.do" target="_blank" class="quick_cartA">
						<b>购物车</b>
						<s id="end"></s>
						<%
						String carNum = (String)request.getAttribute("carNum");
						if(carNum == null || "".equals(carNum)){
							carNum = "0";
						}
						 %>
						<em><%=carNum %></em>
					</a>
				</dd>
				<dd class="quick_service">
					<a id="btnRigQQ" href="http://wpa.qq.com/msgrd?v=3&uin=762136258&site=qq&menu=yes" target="_blank" class="quick_serviceA">
						<b>在线客服</b>
						<s></s>
					</a>
				</dd>
				<dd class="quick_Collection">
					<a id="btnFavorite" href="javascript:;" class="quick_CollectionA">
						<b>收藏本站</b>
						<s></s>
					</a>
				</dd>
				<dd class="quick_Return">
					<a id="btnGotoTop" href="javascript:;" class="quick_ReturnA">
						<b>返回顶部</b>
						<s></s>
					</a>
				</dd>
			</dl>
		</div>
		<!--右边悬浮内容-->

		<script type="text/javascript" src="/huyugo/js/right.js"></script>
		<script type="text/javascript" src="/huyugo/js/jquery.imagezoom.js"></script>
		<script type="text/javascript" src="/huyugo/js/fly.min.js"></script>
		<script>
			//放大镜
			$(document).ready(function() {
				//商品详情选项卡
				$(".detailsAllRight-title span").click(function() {
					$(".detailsAllRight-title span").removeClass("active");
					$(".detailsAllRight-box").hide();
					$(this).addClass("active");
					$(".detailsAllRight-box").eq($(this).index()).show();
				});
				$(".detailsAllRight-box")
				/*翻页*/
				$(".page-box .page-num").on('click', function() {$(".page-box .page-num").removeClass('active');$(this).addClass('active');});
				//商品默认颜色规格选择
				$("#color_").val($(".color em.emok").html());
				$("#guige_").val($(".guige i.emok").html());
				//商品默认颜色规格选择
			
				$(".jqzoom").imagezoom();
				$("#thumblist li a").click(function() {
					//增加点击的li的class:tb-selected，去掉其他的tb-selecte
					$(this).parents("li").addClass("tb-selected").siblings().removeClass("tb-selected");
					//赋值属性
					$(".jqzoom").attr('src', $(this).find("img").attr("mid"));
					$(".jqzoom").attr('rel', $(this).find("img").attr("big"));
				});
			});
			//分享送积分
			$(".songjif_").click(function(){
				var index_ =$(this).index();//下标
				$.ajax({
					url:'/huyugo/fx/fenxiang.do',// 跳转到 action  
					data:{
					      goodsId:$("#goodsId").val(),
					      leimu:index_
					},  
					type:'post',  
					cache:false,  
					dataType:'html',  
					success:function(data) {
					}
				});
			})
			//商品规格选择
			$("#scrollbox dd i").css({border: "1px solid #eee",marginRight: "3px",padding: "2px"});
			$("#scrollbox dd i").eq(0).addClass("emok");
			$("#scrollbox dd i").click(function(){
				$("#scrollbox dd i").removeClass("emok")
				var index_ =$(this).index();//下标
				var color_html =$(this).text();//选择的规格
				$(this).addClass("emok");
				//获取对应选择
				//alert(index_+"==选择的规格=="+color_html);
				$("#guige_").val(color_html);
			})
			//商品颜色选择
			var scrollbox = document.getElementById("scrollbox");
			var odiv = scrollbox.getElementsByTagName("em");
			for(var a = 0; a < odiv.length; a++) {
				odiv[a].index = a;
				odiv[a].onclick = function() {
					for(var b = 0; b < odiv.length; b++) {
						odiv[b].className = '';
					}
					this.className = 'emok';
					//alert(this.innerHTML)
					//alert($(this).text());
					$("#color_").val($(this).text());
				}
			};
			//购物车动画
			$(".addcar").click(function() {
				//添加到购物车动画
				var src = $('.jqzoom').attr('src');
				var $shadow = $('<img id="cart_dh" style="display: none; border:1px solid #aaa; z-index: 99999;" width="400" height="400" src="' + src + '" />').prependTo("body");
				var $img = $(".jqzoom").first("img");
				$shadow.css({
					'width': $img.css('width'),
					'height': $img.css('height'),
					'position': 'absolute',
					'top': $img.offset().top,
					'left': $img.offset().left,
					'opacity': 1
				}).show();
				var $cart = $("#end");
				$shadow.animate({
					width: 1,
					height: 1,
					top: $cart.offset().top,
					left: $cart.offset().left,
					opacity: 0
				}, 500);
				$.ajax({
					url:'/huyugo/car/addCar.do',// 跳转到 action  
					data:{
					      goodsId:$("#goodsId").val(),
					      status:"<%=status_ %>",
					      guige:$("#color_").val()+","+$("#guige_").val()
					},  
					type:'post',  
					cache:false,  
					dataType:'html',  
					success:function(data) {  
					    if(data == "ok"){
					    	//window.location.href="/huyugo/car/showCarByUser.do";
					    	$.ajax({
								url:'/huyugo/car/getCarNum.do',// 跳转到 action  
								data:{
								      
								},  
								type:'post',  
								cache:false,  
								dataType:'html',
								asyn:true, 
								success:function(data) {
								    getNum(data);
							    }
							});
					    }else if(data == "error"){
					    	alert("该商品已存在");
					    }else{
					    	window.location.href="/huyugo/user/login.do";
					    }
				    },
				    error:function(data){
				    	alert("服务器异常");
				    }
				});
			});
			
			$(".lijizhifu").click(function() {
				if("<%=g.getDisplay()%>" == 1){
					alert("该商品已下架,暂无法购买！");
					window.history.go(-1);
					return false;
				}
				if("<%=g.getAmount()%>" <= 0){
					alert("该商品暂时缺货,暂无法购买！");
					return false;
				}
				//添加到购物车动画
				var src = $('.jqzoom').attr('src');
				var $shadow = $('<img id="cart_dh" style="display: none; border:1px solid #aaa; z-index: 99999;" width="400" height="400" src="' + src + '" />').prependTo("body");
				var $img = $(".jqzoom").first("img");
				$shadow.css({
					'width': $img.css('width'),
					'height': $img.css('height'),
					'position': 'absolute',
					'top': $img.offset().top,
					'left': $img.offset().left,
					'opacity': 1
				}).show();
				var $cart = $("#end");
				$shadow.animate({
					width: 1,
					height: 1,
					top: $cart.offset().top,
					left: $cart.offset().left,
					opacity: 0
				}, 500);
				$.ajax({
					url:'/huyugo/car/addCar.do',// 跳转到 action  
					data:{
					      goodsId:$("#goodsId").val(),
					      status:"<%=status_ %>",
					      guige: $("#color_").val() + "," + $("#guige_").val()
					},  
					type:'post',  
					cache:false,  
					dataType:'html',  
					success:function(data) {  
					    if(data == "ok"){
					    	window.location.href="/huyugo/car/showCarByUser.do";
					    }else if(data == "error"){
					    	window.location.href="/huyugo/car/showCarByUser.do";
					    }else{
					    	window.location.href="/huyugo/user/login.do";
					    }
				    },
				    error:function(data){
				    	alert("服务器异常");
				    }
				});
			});

			//推荐
			var swiper = new Swiper('.swiper-container', {
				nextButton: '.swiper-button-next',
				prevButton: '.swiper-button-prev',
				slidesPerView: 5,
				paginationClickable: true,
				spaceBetween: 30,
				freeMode: true
			});

		 //全场秒杀
			//var timer;
			//starttimer();
			
		    function starttimer(){
		   		//定时器，每隔一秒获取一次
		    	timer = window.setInterval(showTime, 1000);
		    }
			//			秒杀功能函数
			function showTime() {

				//-获取当前时间（这个是客户端的时间）
				var nowdt = new Date();
				//使用一个循环来遍历， 这里的10表示最多有10个
				for(var i = 0; i < 8; i++) {

					//--这个判断很有必要， 
					//否则一旦产品没有10000个， js脚本就会出错，
					//加了这个判断， 无论产品有或没有， 
					//1 个或者10000个都不会有问题
					//判断获取页面隐藏输入框的开始时间的个数
					if(document.getElementById("kaishim" + i.toString()) != null) {	
						//获取页面隐藏输入框的开始时间
						var dt2 = new Date(document.getElementById("kaishim" + i.toString()).value.replace(/-/g, "/"));
						
						//判断开始时间是否==抢购到开始时间
						if(document.getElementById("km" + i.toString()).innerHTML == "抢购到开始时间") {
							//开始时间等于抢购到开始时间就隐藏对应的div
							document.getElementById("km" + i.toString()).style.display="none";
							//判断获取页面隐藏输入框的截至时间的个数
							if(document.getElementById("hdfTm" + i.toString()) != null) {
								//获取页面隐藏输入框的截至时间
								var dt = new Date(document.getElementById("hdfTm" + i.toString()).value.replace(/-/g, "/"));
								//判断截至时间是否==抢购时间结束
								if(document.getElementById("tm" + i.toString()).innerHTML != "抢购时间结束") {
									//将剩余截至时间显示在页面上
									document.getElementById("tm" + i.toString()).style.display="block";
									
										document.getElementById("tm" + i.toString()).innerHTML = getTimerString(dt.getTime() - nowdt.getTime());
									
									
								} else {
									//将剩余截至时间显示在页面上	
									document.getElementById("qianggoujieshu").style.display="none";
									if (document.getElementById("tm" + i.toString()).innerHTML =="抢购时间结束") {
										document.getElementById("qianggoujieshu").style.display="none";
										document.getElementById("tm" + i.toString()).style.display="none";
									} else{
										document.getElementById("tm" + i.toString()).innerHTML = getTimerString(dt.getTime() - nowdt.getTime());
									}
								}

							}

						} else if(document.getElementById("km" + i.toString()).innerHTML != "抢购到开始时间"){
							//将剩余开始时间显示在页面上
							document.getElementById("km" + i.toString()).style.display="block";
							document.getElementById("km" + i.toString()).innerHTML = getTimerString2(dt2.getTime() - nowdt.getTime());
						}

					}

					 
				}
			}
			//开始时间函数
			function getTimerString2(time2) {
				//time2开始时间的时间戳
				//天
				d = Math.floor(time2 / 86400000),
					//时
					h = Math.floor((time2 % 86400000) / 3600000),
					//分
					m = Math.floor(((time2 % 86400000) % 3600000) / 60000),
					//秒
					s = Math.floor(((time2 % 86400000) % 3600000 % 60000) / 1000);
				if(time2 > 0) {
					return "距开始：" + d + "天" + p(h) + "小时" + p(m) + "分" + p(s) + "秒";
				} else {
					return "抢购到开始时间";
				}
			};
			//结束时间函数
			function getTimerString(time) {
				//time结束时间的时间戳
				//天
				d = Math.floor(time / 86400000),
					//时
					h = Math.floor((time % 86400000) / 3600000),
					//分
					m = Math.floor(((time % 86400000) % 3600000) / 60000),
					//秒
					s = Math.floor(((time % 86400000) % 3600000 % 60000) / 1000);
				if(time > 0) {
					return "距结束：" + d + "天" + p(h) + "小时" + p(m) + "分" + p(s) + "秒";
				} else {
					return "抢购时间结束";
				}
			};
			//时间不足二位补0
			function p(s) {
				return s < 10 ? '0' + s : s;
			}
		</script>
	</body>

</html>