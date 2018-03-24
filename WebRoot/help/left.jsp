<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.huyu.entity.Article"%>
<%@page import="com.huyu.entity.Affiche"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta name="keywords" content="互余，互余网、互余购、云购、返还购买、万州商城，直购商城，休闲娱乐" />
		<meta name="description" content="互余购-综合网购商城、要网购就来互余购，消费变财富、分享即创业、正品低价、品质保障、新型的网购模式，购物模式多样化、直接购买、返还购买、一元购买等" />
		<title>互余购-综合网购商城、要网购就来互余购，消费变财富、分享即创业、正品低价、品质保障、享受不一样的购物乐趣</title>
		<link rel="stylesheet" href="/huyugo/css/bass.css" />
		<link rel="stylesheet" href="/huyugo/css/header.css" />
		<link rel="stylesheet" href="/huyugo/css/footer.css" />
		<link rel="stylesheet" href="lefthelp.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		<script>
		var num1 = -1;
		</script>
	</head>

	<body>
		<%
		String statusStr = (String)request.getAttribute("status");
		String typeStr = (String)request.getAttribute("type");
		/* String type = (String)request.getAttribute("type"); */
		List<Article> list1 = (List<Article>)request.getAttribute("list1");
		List<Article> list2 = (List<Article>)request.getAttribute("list2");
		List<Article> list3 = (List<Article>)request.getAttribute("list3");
		List<Article> list4 = (List<Article>)request.getAttribute("list4");
		List<Article> list5 = (List<Article>)request.getAttribute("list5");
		List<Affiche> aflist = (List<Affiche>)request.getAttribute("aflist");
		int status = Integer.parseInt(statusStr);
		int type = Integer.parseInt(typeStr);
		 %>
		<jsp:include page="../header.jsp"></jsp:include>

		<div class="publicBox clearfix">
			<div style="height: 10px;"></div>
			
			<!--左边菜单栏-->
			<div class="centerleftbox">
				<div><img src="Img.png" style="height: 50px;width: 200px;"/></div>
				<ul>
					<li>
						<a class="type5">新手指南</a>
						<dl class="div1">
							
							<%
								for(Article a :list5){
							 %>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/getid.do?type=1&id=<%=a.getId() %>" tid="<%=a.getId() %>" target="content"><%=a.getTitle() %></a>
							</dd>
							<%
								}
							 %>
							<!--<dd>
								<a class="hoverClass zcd" href="/huyugo/help/2.html" target="content">限时揭晓介绍</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/3.html" target="content">一元购买介绍及规则</a>
							</dd>
							 <dd>
								<a class="hoverClass zcd" href="/huyugo/help/4.html" target="content">常见问题</a>
							</dd> -->
						</dl>
					</li>
					
					<li>
						<!-- <a>互余购保障</a> -->
						<a class="type4">售后服务</a>
						 <dl class="div1">
						 	<%
								for(Article a :list4){
							 %>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/getid.do?type=1&id=<%=a.getId() %>" target="content"><%=a.getTitle() %></a>
							</dd>
							<%
								}
							 %>
							<!--<dd>
								<a class="hoverClass zcd" href="/huyugo/help/5.html" target="content">服务协议</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/6.html" target="content">购保障体系</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/7.html" target="content">正品保障</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/8.html" target="content">安全支付</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/9.html" target="content">隐私声明</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/10.html" target="content">互余购VIP会员服务协议</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/11.html" target="content">互余网平台服务协议</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/12.html" target="content">互余网隐私声明</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/13.html" target="content">互余网商品品质抽检规则</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/14.html" target="content">互余网平台规则</a>
							</dd>
						</dl> -->
					</li>
					
													
					<li>
						<a class="type2">商品配送</a>
							<dl class="div1"><%
								for(Article a :list2){
							 %>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/getid.do?type=1&id=<%=a.getId() %>" target="content"><%=a.getTitle() %></a>
							</dd>
							<%
								}
							 %>
							 </dl>
						<!-- <dl class="div1">
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/15.html" target="content">商品配送</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/16.html" target="content">配送费用</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/17.html" target="content">商品验货与签收</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/18.html" target="content">长时间未收到商品</a>
							</dd>							 
						</dl> -->
					</li>
					<li>
						<a class="type1">会员专区</a>
							<dl class="div1"><%
								for(Article a :list1){
							 %>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/getid.do?type=1&id=<%=a.getId() %>" target="content"><%=a.getTitle() %></a>
							</dd>
							<%
								}
							 %></dl>
					</li>
					<li>
						<a class="type3">商务合作</a>
							<dl class="div1"><%
								for(Article a :list3){
							 %>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/getid.do?type=1&id=<%=a.getId() %>" target="content"><%=a.getTitle() %></a>
							</dd>
							<%
								}
							 %></dl>
					</li>
					<li>
						<a class="gg">最新公告</a>
						<dl class="div1">
						
							<%
								for(Affiche af : aflist){
							 %>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/getid.do?type=2&id=<%=af.getId() %>" target="content"><%=af.getTitle() %></a>
							</dd>
							<%
								}
							 %>
							<!-- <dd>
								<a class="hoverClass zcd" href="/huyugo/help/20.html" target="content">互余购商城改版升级上线通知</a>
							</dd>
							<dd>
								<a class="hoverClass zcd" href="/huyugo/help/21.html" target="content">大病互助托管计划老年版详细规则</a>
							</dd> -->
							
						</dl>
					</li>
				</ul>
			</div>
			<!--左边菜单栏-->
			<!--右边内容显示-->
			<div class="centerrightbox">
					<!-- // type 1文章  2公告 -->
				 <iframe name="content" src="/huyugo/help/getid.do?type=<%=type %>&id=<%=status %>" frameborder="0" id="mainframe" scrolling="yes" marginheight="0" marginwidth="0" width="100%" height="700"></iframe>
			</div>
			<!--右边内容显示-->
		</div>

		<jsp:include page="../footer.jsp"></jsp:include>
		
		<script>
			//公告
			var navigationTitle = document.getElementsByClassName("navigationTitle")[0];
			var navigationTitleHover = navigationTitle.getElementsByClassName("navigationTitleHover");
			var navigationTitleList = document.getElementsByClassName("navigationTitleList");
			var num = 0;
			start();
			for(var i = 0; i < navigationTitleHover.length; i++) {
				navigationTitleHover[i].index = i;
				navigationTitleHover[i].onmouseover = function() {
					for(var i = 0; i < navigationTitleList.length; i++) {
						navigationTitleList[i].style.display = 'none';
						navigationTitleHover[num].style.cssText = 'color: #fff;';
					}
					num = this.index;
					start();
				}
			};

			function start() {
				navigationTitleList[num].style.display = 'block';
				navigationTitleHover[num].style.cssText = 'color:#ff4040;';
			};
			//左边导航栏
			$(".centerleftbox  ul li a.hoverClass").click(function() {
				$(".centerleftbox  ul li a.hoverClass").removeClass("removes"); //siblings是循环遍历
				
				$(this).addClass("removes");

			});
			$window = $(window);

			var $backTop = $('.centerleftbox  ul li a.hoverClass');
			$window.on('scroll', function() {
				var $this = $(this);
				var $scroll = $(this).scrollTop();

				if($scroll > 100) {

					$backTop.css('right', '0px');
				} else if($scroll < 100) {

					$backTop.css('right', '0px');
				}
			});
			$backTop.click(function() {
				$('body,html').animate({
					scrollTop: 0
				}, 300);
			});
			
			$("body").find("a[tid='<%=status%>']").addClass("removes");
		</script>

	</body>

</html>