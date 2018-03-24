<%@page import="com.huyu.entity.Tuangou"%>
<%@page import="com.huyu.entity.Cuxiao"%>
<%@page import="com.huyu.entity.Miaosha"%>
<%@page import="com.huyu.entity.Temai"%>
<%@page import="com.huyu.entity.Qianggou"%>
<%@page import="com.huyu.entity.Picture"%>
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
		<meta name="description" content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
		<title>互余购-综合网购商城</title>
		<link rel="stylesheet" href="/huyugo/css/swiper.css" />		
		<link rel="stylesheet" href="/huyugo/css/bass.css" />
		<link rel="stylesheet" href="/huyugo/css/integralmall.css" />
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
		<script type="text/javascript">
		var num1 = 2;
		function showTime() {
                var nowdt = new Date();
				//-获取当前时间（这个是客户端的时间）
				//var nowdt = new Date();
				//使用一个循环来遍历， 这里的10表示最多有10个
				for(var i = 0; i < 10; i++) {

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
								var stint = 5000;
								if(document.getElementById("tm" + i.toString()).innerHTML != "抢购时间结束") {
									//将剩余截至时间显示在页面上
									document.getElementById("tm" + i.toString()).style.display="block";
									//alert("0 : "+stint);
									document.getElementById("tm" + i.toString()).innerHTML = getTimerString(dt.getTime()-nowdt.getTime());
									document.getElementById("removeCh"+ i.toString()).style.display="block"; 						
									
									//document.getElementById("miaoshaNav"+i.toString()).style.background="red";
								} else {
									//将剩余截至时间显示在页面上	
									document.getElementById("removeCh"+ i.toString()).style.display="none";
									document.getElementById("tm" + i.toString()).style.display="block";
									//alert("1 : "+stint);
									document.getElementById("tm" + i.toString()).innerHTML = getTimerString(dt.getTime()-nowdt.getTime());
									//对应的开始时间字体样式恢复默认样式
									
                                   //document.getElementById("delmiaosha").style.background="#ff9494";
								}

							}

						} else if(document.getElementById("km" + i.toString()).innerHTML != "抢购到开始时间"){
							//将剩余开始时间显示在页面上
							document.getElementById("km" + i.toString()).style.display="block";
							//var stint = paseInt(paseInt(dt2.getTime()) - nowdtInt);
							//alert("2 : "+stint);
							document.getElementById("km" + i.toString()).innerHTML = getTimerString2(dt2.getTime()-nowdt.getTime());
						}

					}
										
					
					//-延时1秒重复执行

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
		
		function getTuangouNum(tuangouId){
			$.ajax({
					url:"/huyugo/activity/showTuangouNum.do",
					data:{
					      tuangouId:tuangouId
					},  
					type:'post',  
					cache:false,  
					dataType:'html',
					success:function(data) {
						var emId = "#em"+tuangouId;
						var str = data + "<br/>团购";
						$(emId).html(str);
				    }
				});
		}
		</script>
	</head>

	<body>
	
	<%
	User user = (User)session.getAttribute("user");
	 %>
	<style>
		.trendyList1 ul li.d:nth-of-type(1) img{
			height: 210px !important;
		}
		.trendyList1 ul li:nth-of-type(1) img{
			height: 210px !important;
		}
		
		.trendyList1 ul li{
			margin: 10px 10px !important;
		}
	</style>
		<!--签到弹窗内容-->
			<div class="mask">
				<div class="modal">
				    <a href="#" class="closeBtn">×</a>
					<h1 class="title_h1 clearfix"><span></span><em>已签到</em> <i>您已签到1天</i></h1>
					<h2 class="title_h2">您获得抵现金<span>10元</span></h2>
			    </div>
			</div>
			<!--签到弹窗内容-->
		<!--主体部分-->
		<div class="mBodyContent">
			<jsp:include page="header.jsp"></jsp:include>
			
			<!--全场秒杀-->
			<div class="publicBox  clothing" id="miaosha">
				<div class="hottestPopularityTitle clearfix">
					<h5>全场秒杀</h5>
					<ul class="hottest-menu clearfix">
						<li class="hottest-menu-color"><a href="/huyugo/list/getGoodsByMiaoSha.do">全场秒杀</a></li>
						<li><a href="/huyugo/list/getGoodsByDiJia.do">低价促销</a></li>
						<li><a href="/huyugo/list/getGoodsByQiangGou.do">今日抢购</a></li>
						<li><a href="/huyugo/list/getGoodsByTeMai.do">今日特卖</a></li>
						<li><a href="/huyugo/list/getGoodsByTuanGou.do">今日团购</a></li>
					</ul>
					<a href='/huyugo/list/getGoodsByMiaoSha.do'>更多</a>
				</div>
				<div class="SeckillboxConts">
					<!--时间范围内的秒杀内容-->
					<div class="hottestPopularityList" style="display: block;">
						<!--循环的列表-->
						<div class="trendyList21">
							<ul class="clearfix">							
								<%
								List<Miaosha> miaoshaList = (List<Miaosha>)request.getAttribute("miaoshaList");
								int i = 0;
								for(Miaosha miaosha : miaoshaList){
									if(i < 10){
										%>
										<li id="removeCh<%=i %>">
											<a href="/huyugo/goods/showGoodsById.do?id=<%=miaosha.getGoods_id() %>&status=miaosha">
												<!--传值开始时间-->
												<input type="hidden" id="kaishim<%=i %>" value="<%=miaosha.getStart_time() %>" />
												<!----传值截至时间-->
												<input type="hidden" id="hdfTm<%=i %>" value="<%=miaosha.getEnd_time() %>" />
												<img id="imgmiaosha<%=miaosha.getGoods_id() %>" />
												<p id="pmiaosha<%=miaosha.getGoods_id() %>"></p>
												<!----显示开始时间剩余时间-->
												<dd id="km<%=i %>" class="ddKm0">距结束:1225585584545</dd>
												<!--显示截至剩余时间-->
												<dd id="tm<%=i %>" class="ddKm0">距结束:1225585584545</dd>
												<dd><span>¥<%=miaosha.getReal_price() %></span><del id="delmiaosha<%=miaosha.getGoods_id() %>"></del></dd>
											</a>
										</li>
										<script>getGoodsData("<%=miaosha.getGoods_id() %>","miaosha");</script>
										<%
										}
									i++;
								}
								 %>
							</ul>
						</div>

						 
					</div>
					<!--时间范围内的秒杀内容-->

				</div>
				 
			</div>
			<!--全场秒杀-->
			

	<!--低价促销-->
			<div class="publicBox Nutrition" id="cuxiao">
				<div class="hottestPopularityTitle clearfix">
					<h5>低价促销</h5>
					<a href='/huyugo/list/getGoodsByDiJia.do'>更多</a>
				</div>
				<div class="hottestPopularityList">
					<style>
						.NutritionList1 ul.clearfix li{
							margin: 10px 10px;
						}
					</style>
					<!--循环的列表-->
					<div class="trendyList1 NutritionList1">
						<ul class="clearfix">
							<!-- <li>
								<a href="">
									<img src="/huyugo/img/cuxiao1.png" />
								</a>
							</li> -->
							<%
							i = 0;
							List<Cuxiao> cuxiaoList = (List<Cuxiao>)request.getAttribute("cuxiaoList");
							for(Cuxiao cuxiao : cuxiaoList){
								if(i < 10){
									%>
									<li class='d'>
										<a href="/huyugo/goods/showGoodsById.do?id=<%=cuxiao.getGoods_id() %>&status=cuxiao">
											<img id="imgcuxiao<%=cuxiao.getGoods_id() %>" />
											<p id="pcuxiao<%=cuxiao.getGoods_id() %>"></p>
											<dt><span></span><span></span></dt>
											<dd><span>¥<%=cuxiao.getReal_price() %></span><del id="delcuxiao<%=cuxiao.getGoods_id() %>"></del><em>马上抢</em></dd>
										</a>
									</li>
									<script>getGoodsData("<%=cuxiao.getGoods_id() %>","cuxiao");</script>
									<%
									i++;
								}
							}
							 %>
						</ul>
					</div>

					<%-- <div class="trendyList1 NutritionList1">
						<ul class="clearfix">
							<li>
								<a href="">
									<img src="/huyugo/img/cuxiao2.png" />
								</a>
							</li>
							<%
							i = 0;
							for(Cuxiao cuxiao : cuxiaoList){
								if(i > 3){
									%>
									<li>
										<a href="/huyugo/goods/showGoodsById.do?id=<%=cuxiao.getGoods_id() %>&status=cuxiao">
											<img id="imgcuxiao<%=cuxiao.getGoods_id() %>" />
											<p id="pcuxiao<%=cuxiao.getGoods_id() %>"></p>
											<dt><span></span><span></span></dt>
											<dd><span>¥<%=cuxiao.getReal_price() %></span><del id="delcuxiao<%=cuxiao.getGoods_id() %>"></del><em>马上抢</em></dd>
										</a>
									</li>
									<script>getGoodsData("<%=cuxiao.getGoods_id() %>","cuxiao");</script>
									<%
								}
							}
							 %>
						</ul>
					</div> --%>
				</div>
			</div>
			<!--低价促销-->
			
			<!--今日抢购-->
			<div class="publicBox hottestPopularity" id="qianggou">
				<div class="hottestPopularityTitle clearfix">
					<h5>今日抢购</h5>
					<a href='/huyugo/list/getGoodsByQiangGou.do'>更多</a>
				</div>
				<div class="hottestPopularityList">
					<!--循环的列表-->
					<div class="trendyList1">
						<ul class="clearfix">
							<!-- <li>
								<a href="">
									<img src="/huyugo/img/qianggou1.png" />
								</a>
							</li> -->
							<%
							List<Qianggou> qianggouList = (List<Qianggou>)request.getAttribute("qianggouList");
							i = 0;
							for(Qianggou qg : qianggouList){
								if(i < 10){
								%>
								<li>
									<a href="/huyugo/goods/showGoodsById.do?id=<%=qg.getGoods_id() %>&status=qianggou">
										<img id="img<%=qg.getGoods_id() %>" />
										<p id="p<%=qg.getGoods_id() %>"></p>
										<dt><span></span><span></span></dt>
										<dd><span>¥<%=qg.getReal_price() %></span><del id="del<%=qg.getGoods_id() %>"></del><em>马上抢</em></dd>
									</a>
								</li>
								<script>getGoodsData("<%=qg.getGoods_id() %>","qianggou");</script>
								<%
								}else{
									break;
								} 
								i++;
							}
							 %>
							
						</ul>
					</div>

					<%-- <div class="trendyList1">
						<ul class="clearfix">
							<!-- <li>
								<a href="">
									<img src="/huyugo/img/qianggou2.png" />
								</a>
							</li> -->
							<%
							i = 0;
							for(Qianggou qg : qianggouList){
								/* if(i > 3){ */
								%>
								<li>
									<a href="/huyugo/goods/showGoodsById.do?id=<%=qg.getGoods_id() %>&status=qianggou">
										<img id="img<%=qg.getGoods_id() %>" />
										<p id="p<%=qg.getGoods_id() %>"></p>
										<dt><span></span><span></span></dt>
										<dd><span>¥<%=qg.getReal_price() %></span><del id="del<%=qg.getGoods_id() %>"></del><em>马上抢</em></dd>
									</a>
								</li>
								<script>getGoodsData("<%=qg.getGoods_id() %>","qianggou");</script>
								<%
								/* }
								i++; */
							}
							 %>
						</ul>
					</div> --%>
				</div>
			</div>
			<!--今日抢购-->
			<!--今日特卖-->
			<div class="publicBox toyseries" id="temai">
				<div class="hottestPopularityTitle clearfix">
					<h5>今日特卖</h5>
					<a href='/huyugo/list/getGoodsByTeMai.do'>更多</a>
				</div>
				<div class="hottestPopularityList">
					<!--循环的列表-->
					<div class="trendyList1">
						<ul class="clearfix">
							<!-- <li>
								<a href="">
									<img src="/huyugo/img/temai1.png" />
								</a>
							</li> -->
							<%
							i = 0;
							List<Temai> temaiList = (List<Temai>)request.getAttribute("temaiList");
							for(Temai temai : temaiList){
								if(i < 10){
									%>
									<li>
										<a href="/huyugo/goods/showGoodsById.do?id=<%=temai.getGoods_id() %>&status=temai">
											<img id="imgtemai<%=temai.getGoods_id() %>" />
											<p id="ptemai<%=temai.getGoods_id() %>"></p>
											<dt><span></span><span></span></dt>
											<dd><span>¥<%=temai.getReal_price() %></span><del id="deltemai<%=temai.getGoods_id() %>"></del></dd>
										</a>
									</li>
									<script>getGoodsData("<%=temai.getGoods_id() %>","temai");</script>
									<%
								}
								i++;
							}
							 %>
						</ul>
					</div>

					<%-- <div class="trendyList1">
						<ul class="clearfix">
							<li>
								<a href="">
									<img src="/huyugo/img/temai2.png" />
								</a>
							</li>
							<%
							i = 0;
							for(Temai temai : temaiList){
								if(i > 3){
									%>
									<li>
										<a href="/huyugo/goods/showGoodsById.do?id=<%=temai.getGoods_id() %>&status=temai">
											<img id="imgtemai<%=temai.getGoods_id() %>" />
											<p id="ptemai<%=temai.getGoods_id() %>"></p>
											<dt><span></span><span></span></dt>
											<dd><span>¥<%=temai.getReal_price() %></span><del id="deltemai<%=temai.getGoods_id() %>"></del></dd>
										</a>
									</li>
									<script>getGoodsData("<%=temai.getGoods_id() %>","temai");</script>
									<%
								}
								i++;
							}
							 %>
						</ul>
					</div> --%>
				</div>
			</div>
			<!--今日特卖-->

			
               
            <!--今日团购-->
            <div class="publicBox" id="tuangou">
            	<div class="hottestPopularityTitle clearfix">
					<h5>今日团购</h5>
					<a href='/huyugo/list/getGoodsByTuanGou.do'>更多</a>
				</div>
            	<div class="groupPurchase">
            		<div class="groupPurchaseList1">
						<ul class="clearfix">
							<%
							i = 0;
							List<Tuangou> tuangouList = (List<Tuangou>)request.getAttribute("tuangouList");
							for(Tuangou tuangou : tuangouList){
								if(i < 10){
								%>
								<li>
									<a href="/huyugo/goods/showGoodsById.do?id=<%=tuangou.getGoods_id() %>&status=tuangou">
										<img id="imgtuangou<%=tuangou.getGoods_id() %>"/>
										<p id="ptuangou<%=tuangou.getGoods_id() %>"></p>
										<dt><span>当前价格¥<%=tuangou.getReal_price() %></span></dt>
										<dd><span>总团购数&nbsp;&nbsp;<%=tuangou.getPay_num() %></span><em id="em<%=tuangou.getId() %>"></em></dd>
									</a>
								</li>
								<script>getGoodsData("<%=tuangou.getGoods_id() %>","tuangou");getTuangouNum("<%=tuangou.getId() %>");</script>
								<%
								}
								i++;
							}
							 %>
							
						</ul>
					</div>
            	</div>
            </div>
            <!--今日团购-->


			<!-- <div class="publicBox tupIc">
				<img src="/huyugo/img/commodity/d.png" />
			</div> -->
			<jsp:include page="footer.jsp"></jsp:include>
			
			<!--主体部分-->
		</div>
		<script type="text/javascript" src="/huyugo/js/rl.js" ></script>
		<script>
			
			
			/* function start() {
				navigationTitleList[num].style.display = 'block';
				navigationTitleHover[num].style.cssText = 'color:#ff4040;';
			} */
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
				window.location.href="index.html";
			});
			
			var timer;
			starttimer();
		    function starttimer(){
		   		//定时器，每隔一秒获取一次
		    	timer = window.setInterval(showTime, 1000);
		    }
//			useBtns button
		</script>
	</body>

</html>