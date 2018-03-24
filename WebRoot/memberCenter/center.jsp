<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<meta name="keywords" content="互余，互余网、互余购、万州商城，直购商城，休闲娱乐" />
		<meta name="description" content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
		<title>互余购-综合网购商城</title>
		<link rel="stylesheet" href="/huyugo/memberCenter/css/center.css" />
		<script>
			var num1 = -1;
		</script>
	</head>

	<body>
	<%
	String url = (String)request.getAttribute("url");
	if(url == null || "".equals(url)){
		url = "/huyugo/person/home.do";
	}
	 %>
		<jsp:include page="../header.jsp"></jsp:include>

		<div class="publicBox clearfix">
			<!--面包屑路径-->
			<div class="actricNva clearfix">
				<p>您现在的位置：</p>
				<a href="/huyugo/index.do">首页</a>
				<p>></p>
				<a href="/huyugo/person/showCenter.do">会员中心</a>
				<p>></p>
				<p>我的互余购</p>
			</div>
			<!--面包屑路径-->
			<!--左边菜单栏-->
			<div  class="centerleftbox menu_list">
				<div class="TitleCenter">会员中心</div>
				<ul>
					<li>
						<a class="hoverClass removes" href="/huyugo/person/home.do" target="content"><img src="/huyugo/img/smallImg/c1.png" />我的互余购</a>
					</li>
					<li>
						<a class="hoverClass" href="/huyugo/person/showPersonal.do" target="content"><img src="/huyugo/img/smallImg/c2.png" />个人中心</a>
					</li>
					<li>
						<a class="hoverClass" href="/huyugo/payment/showItegral.do?curpage=1" target="content"><img src="/huyugo/img/smallImg/c3.png" />我的积分</a>
					</li>
					<li>
						<a class="hoverClass" href="/huyugo/payment/showPension.do?curpage=1" target="content"><img src="/huyugo/img/smallImg/c4.png" />我的养老金</a>
					</li>
					<li>
						<a class="hoverClass" href="/huyugo/payment/showDixianjin.do?curpage=1" target="content"><img src="/huyugo/img/smallImg/c4.png" />我的抵现金</a>
					</li>
					<li>
						<a class="hoverClass" href="/huyugo/payment/showHuyubi.do?curpage=1" target="content"><img src="/huyugo/img/smallImg/c5.png" />我的互余币</a>
					</li>
					<li>
						<a class="hoverClass" href="/huyugo/fazhanOrder/showFazhanjin.do?curpage=1" target="content"><img src="/huyugo/img/smallImg/c5.png" />我的发展金</a>
					</li>
                    <li>
						<a class="hoverClass" href="/huyugo/order/showOrder.do?zt=-1&curpage=1" target="content"><img src="/huyugo/img/smallImg/c6.png" />我的订单</a>
						<!-- <img class="xiala" src="/huyugo/memberCenter/jiantou/jiantou.png" />
						<dl class="div1">
							<dd>
								<a class="hoverClass zcd" target="content" href="/huyugo/order/showOrder.do?zt=-1">我的订单</a>
							</dd>
							<dd>
								<a class="hoverClass zcd"   target="content">已购买的商品</a>
							</dd>
							<dd>
								<a class="hoverClass zcd"  target="content">评论</a>
							</dd>
						</dl> -->
					</li>
					<!-- <li>
						<a><img src="/huyugo/img/smallImg/c7.png" />我的优惠信息</a>
						<img class="xiala" src="/huyugo/memberCenter/jiantou/jiantou.png" />
						<dl class="div1">
							<dd>
								<a class="hoverClass zcd"  href="" target="content">我的赠送券</a>
							</dd>
							<dd>
								<a class="hoverClass zcd"   href="" target="content">我的优惠券</a>
							</dd>
							<dd>
								<a class="hoverClass zcd"  href="" target="content">我的现金券</a>
							</dd>
						</dl>
					</li>
					 -->
					<li>
						<a><img src="/huyugo/img/smallImg/c8.png" />邀请管理</a>
						<img class="xiala" src="/huyugo/memberCenter/jiantou/jiantou.png" />
						<dl class="div1">
							<dd>
								<a class="hoverClass zcd"  href="/huyugo/friend/showFriendView.do?curpage=1" target="content">邀请好友</a>
							</dd>
							<dd>
								<a class="hoverClass zcd"  href="/huyugo/payment/showYongjinDetail.do?curpage=1" target="content">佣金明细</a>
							</dd>
						</dl>
					</li>
					<li>
						<a><img src="/huyugo/img/smallImg/c9.png" />账户管理</a>
						<img class="xiala" src="/huyugo/memberCenter/jiantou/jiantou.png" />
						<dl class="div1">
							<dd>
								<a class="hoverClass zcd"  href="/huyugo/payment/showPriceDetail.do?curpage=1" target="content">账户明细</a>
							</dd>
							<!-- <dd>
								<a class="hoverClass zcd"  href="" target="content">账户充值</a>
							</dd> -->
							<dd>
								<a class="hoverClass zcd"  href="/huyugo/memberCenter/right/account2.html" target="content">申请提现</a>
							</dd>
							<!-- <dd>
								<a class="hoverClass zcd"  href="" target="content">提现记录</a>
							</dd> -->
						</dl>
					</li>
					
				</ul>
			</div>
			<!--左边菜单栏-->
			<!--右边内容显示-->
			<div class="centerrightbox">
				<iframe name="content" src="<%=url %>" frameborder="0" id="mainframe" scrolling="yes" marginheight="0" marginwidth="0" width="100%" height="820"></iframe>
			</div>
			<!--右边内容显示-->
		</div>

		<jsp:include page="../footer.jsp"></jsp:include>
		<!--底部内容-->
		<script type="text/javascript" src="/huyugo/memberCenter/js/left.js" ></script>
		<script>
			
			//左边导航栏
			$(".centerleftbox  ul li a.hoverClass").click(function(){
			         $(".centerleftbox  ul li a.hoverClass").removeClass("removes");//siblings是循环遍历
			        $(this).addClass("removes");
			        
			
			})
			
			$window = $(window);

			var $backTop = $('.centerleftbox  ul li a.hoverClass');
			$window.on('scroll', function() {
				var $this = $(this);
				var $scroll = $(this).scrollTop();

				if($scroll > 100) {

					$backTop.css('right', '25px');
				} else if($scroll < 100) {

					$backTop.css('right', '-50px');
				}
			});
			$backTop.click(function() {
				$('body,html').animate({
					scrollTop: 0
				}, 300);
			});
			
		</script>

	</body>

</html>