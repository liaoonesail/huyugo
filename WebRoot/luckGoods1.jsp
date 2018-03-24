<%@page import="com.huyu.entity.User"%>
<%@page import="com.huyu.entity.Userinfo"%>
<%@page import="com.huyu.entity.Prize_record"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="UTF-8">
		<link href="/huyugo/img/f.png" rel="shortcut icon" type="image/x-icon"/>
		<meta http-equiv="X-UA-Compatible" content="IE=9" />
		<title>砸蛋</title>
		<link rel="stylesheet" href="/huyugo/css/bass.css" />
		<link rel="stylesheet" href="/huyugo/css/luckGoods.css" />
		
		<script>
		num1 = 1;
		
		function getUser(userId,i){
				$.ajax({
								url:"/huyugo/user/getUserById.do",
								data:{
								      userId:userId
								},  
								type:'post',  
								cache:false,  
								dataType:'html',  
								success:function(data) {
									if(data == "notLogin"){
									}else{
										var json = JSON.parse(data);
										var fontId = "#font"+i;
										$(fontId).html(json.phone.substring(0,4)+"****");
									}
							    }
							});
			}
	</script>
	</head>
	<body>
		
		<!--头部导航部分-->
		<header class="headerConts">
			<!--头部顶部内容-->
			<jsp:include page="header.jsp"></jsp:include>
		<!--头部导航部分-->
		
		
		
		<style>
		.mask {
			position: fixed;
			width: 100%;
			height: 100%;
			background-color: rgba(0, 0, 0, 0.55);
			display: none;
			visibility: inherit;
			transition: all 0.25s ease;
			z-index: 9999999999999999999999999999999999555;
		}
		
		.modal {
			background: #fff;
			width: 450px;
			height: 400px;
			border-radius: 10px;
			border: 5px solid #47176D;
			box-sizing: border-box;
			margin: 100px auto;
			overflow: hidden;
			transform: translateY(-200%);
			transition: all 0.25s ease
		}
		
		.signbox a.closeBtn {
			display: block;
			position: absolute;
			right: 10px;
			top: 5px;
			font-family: 'simsun';
			font-size: 18px;
			text-decoration: none;
			font-weight: bolder;
			color: #333
		}
		
		.title_h1 {
			text-align: center;
			font-size: 40px;
			font-weight: normal;
			padding-top: 80px;
			display: block;
			width: 100%
		}
		
		.title_h1 span {
			display: inline-block;
			width: 40px;
			height: 40px;
			border-radius: 100%;
			background: #47176D;
			color: #fff;
			position: relative;
			float: left;
			margin-left: 44%;
			margin-top: 7px;
		}
		
		.title_h1 span::before {
			width: 10px;
			height: 2px;
			background: #fff;
			position: absolute;
			left: 8px;
			top: 23px;
			display: block;
			line-height: 0;
			font-size: 0;
			content: "";
			transform: rotate(52deg);
		}
		
		.title_h1 span::after {
			width: 24px;
			height: 2px;
			background: #fff;
			position: absolute;
			left: 12px;
			top: 20px;
			display: block;
			line-height: 0;
			font-size: 0;
			content: "";
			transform: rotate(-45deg);
		}
		
		.title_h1 em {
			display: inline-block;
			font-size: 30px;
			float: left;
			margin-left: 10px;
		}
		
		.title_h1 i {
			display: inline-block;
			font-size: 16px;
			float: left;
			margin: 14px 0 0 10px;
		}
		
		.title_h2 {
			text-align: center;
			font-size: 16px;
			display: block;
			padding-top: 20px;
		}
		
		.title_h2 span {
			font-size: 22px;
			color: #b25d06;
		}
		
		.closeBtn {
			position: absolute;
			right: 10px;
			top: 10px;
			font-size: 36px;
		}
		
		.egg {
			width: 660px;
			height: 400px;
			margin: 0px auto;
		}
		
		.egg ul li {
			z-index: 999;
		}
		
		.eggList {
			padding-top: 110px;
			position: relative;
			width: 660px;
		}
		
		.eggList li {
			float: left;
			background: url(/huyugo/img/egg_1.png) no-repeat bottom;
			width: 158px;
			height: 187px;
			cursor: pointer;
			position: relative;
			margin-left: 35px;
			list-style: none
		}
		
		.eggList li span {
			position: absolute;
			width: 30px;
			height: 60px;
			left: 68px;
			top: 64px;
			color: #ff0;
			font-size: 42px;
			font-weight: bold
		}
		
		.eggList li.curr {
			background: url(/huyugo/img/egg_2.png) no-repeat bottom;
			cursor: default;
			z-index: 300;
			list-style: none
		}
		
		.eggList li.curr sup {
			position: absolute;
			background: url(/huyugo/img/img-4.png) no-repeat;
			width: 232px;
			height: 181px;
			top: -36px;
			left: -34px;
			z-index: 800;
			list-style: none
		}
		
		.hammer {
			background: url(/huyugo/img/img-6.png) no-repeat;
			width: 74px;
			height: 87px;
			position: absolute;
			text-indent: -9999px;
			z-index: 150;
			left: 168px;
			top: 100px;
		}
		
		.resultTip {
			position: absolute;
			background: #ffc;
			width: 148px;
			padding: 6px;
			z-index: 500;
			top: 200px;
			left: 10px;
			color: #f60;
			text-align: center;
			overflow: hidden;
			display: none;
			z-index: 500;
		}
		
		.resultTip b {
			font-size: 14px;
			line-height: 24px;
		}
		
		.zadanBox {
			text-align: justify;
			color: #fff;
			padding-top: 120px;
		}
		
		.zadanBox p {
			margin-left: 120px;
		}
		
		.zadanGuizeBox {
			position: relative;
			width: 888px;
			height: 500px;
			margin: 10px auto;
		}
		
		.guizeBox555 {
			position: relative;
			width: 800px;
			height: 350px;
			background: #732c74;
			margin: 0 auto;
			padding: 20px;
			overflow: hidden;
		}
		
		.guizeBox555 h2 {
			color: #f2e2b4;
			line-height: 45px;
		}
		
		.guizeBox555 P {
			margin-bottom: 0px;
			line-height: 20px;
			text-indent: 0px;
			text-align: justify;
			color: #ffed3a;
			font-size: 12px;
		}
		
		.guizeBox555 P:nth-of-type(even) {
			color: #f2e2b4;
		}
		
		.huojiangBox {
			position: absolute;
			top: 20%;
			right: 15%;
			width: 300px;
			height: 388px;
			z-index: 88;
			overflow: hidden;
		}
		
		.huojiangBoxTitle {
			text-align: justify;
			color: #ffed3a !important;
			font-size: 22px;
		}
		
		#buyList {}
		
		#buyList li {
			color: #fff;
			font-size: 14px;
			text-align: justify;
			height: 30px;
			line-height: 30px;
			overflow: hidden;
		}
		
		.TxListBox {
			position: absolute;
			top: 25%;
			left: 8%;
			width: 200px;
			height: 300px;
			z-index: 886;
			overflow: hidden;
		}
		
		.TxListBox111 {
			position: relative;
			width: 100%;
			height: 100%;
			overflow: hidden;
		}
		
		.TxListBox222 img {
			float: left;
			width: 65px;
			height: 65px;
			border-radius: 50%;
			margin: 4px 6px;
		}
		
		.TxListBox222 span:nth-of-type(1) {
			display: block;
			float: left;
			width: 100px;
			height: 25px;
			color: #fff;
			overflow: hidden;
			margin-top: 15px;
		}
		
		.TxListBox222 span:nth-of-type(2) {
			display: block;
			float: left;
			width: 100px;
			height: 20px;
			color: #fff;
			font-size: 14px;
			overflow: hidden;
		}
		
		.guze222 {
			position: relative;
			width: 150px;
			height: 127px;
			margin: 20px auto;
			overflow: hidden;
			background: #8e368e;
		}
		
		.guze222 h3 {
			text-align: center;
			color: #fff;
			border-bottom: 1px solid #fff;
			line-height: 35px;
		}
		
		.guze222 p {
			position: relative;
			width: 90%;
			height: 30px;
			overflow: hidden;
			text-align: center;
			font-size: 14px;
			color: #fff76e;
			line-height: 30px;
			background: #6f1922;
			border-radius: 6px;
			margin: 10px auto;
		}
		
		.guze222 a {
			color: #fff76e;
			text-align: center;
			display: block;
			cursor: pointer;
			font-size: 14px;
		}
		
		.TxListBox1 {
        position: absolute;
        top: 19%;
        left: 17%;
        width: 200px;
        height: 300px;
        z-index: 886;
        overflow: hidden;
}
	</style>
		
		<!--弹窗内容-->
		<div class="mask">
			<div class="modal">
				<a href="#" class="closeBtn">×</a>
				<h1 class="title_h1 clearfix"><span></span></h1>
				<h2 class="title_h2"><span></span></h2>
			</div>
		</div>
		<!--弹窗内容-->
		<%
					User user = (User)session.getAttribute("user");
					Userinfo ui = (Userinfo)request.getAttribute("ui");
					String phone = "未登录";
					double jifen = 0;
					if(user != null){
						phone = user.getPhone().substring(0, 4) + "****" + user.getPhone().substring(user.getPhone().length() - 3, user.getPhone().length());
						jifen = user.getIntegral();
					}
					String headPath = "/huyugo/img/member.jpg";
					if(ui != null && !"".equals(ui.getHead_picture())){
						headPath = ui.getHead_picture();
					}
					 %>
		
		<div class="publicBox btnsstrbg">
			<a href="/huyugo/luck/showLuck.do">幸运大转盘</a>
			<a class="removes" href="/huyugo/luck/showZadan.do">砸金蛋</a>
		</div>
		<div class="publicBoxBackgroud">
			<div class="publicBox">
				<div class="TxListBox1">
						<div class="TxListBox111">
							<div class="TxListBox222 clearfix">
								<img src="<%=headPath %>" />
								<span><%=phone %></span>
								<span id="jifen">积分：<%=jifen %></span>
							</div>
							<div class="guze222">
								<h3>抽奖规则</h3>
								<p>2积分/次</p>
								<a href="#gradientLkk">查看详细规则 >></a>
							</div>
						</div>
					</div>
				<!--获奖公告-->
				<div class="huojiangBox">
					<div class="huojiangBoxTitle">恭喜以下达人中奖</div>
					<marquee behavior="scroll" loop="-1" width="300" height="300" direction="up">
						<ul id="buyList">
							<%
								List<Prize_record> prizeList = (List<Prize_record>)request.getAttribute("prizeList");
								if(prizeList != null && prizeList.size() > 0){
									int i = 0;
									for(Prize_record prize : prizeList){
											%>
											<li><%=prize.getPrize() %>等奖  <font id="font<%=i %>"></font> <%=prize.getContent() %></li>
											<script>getUser("<%=prize.getUser_id() %>","<%=i %>")</script>
											<%
										i++;
									}
								}
								 %>
						</ul>
					</marquee>
				</div>
				<%-- <div class="zadanBox">
					<p>活动对象：互余购在线会员</p>
					<p>活动时间：长期有效</p>
					<p>活动内容：活动期间，每砸蛋一次消耗10积分</p>
					<p>中奖奖品：30元抵现金</p>
					<p>我的积分：<font color="red" id="jifen"><%=jifen %></font></p>
				</div> --%>
				<div id="main">

					<div class="egg">

						<ul class="eggList">

							<p class="hammer" id="hammer">锤子</p>

							<p class="resultTip" id="resultTip"><b id="result"></b></p>

							<li><span>1</span><sup></sup></li>

							<li><span>2</span><sup></sup></li>

							<li><span>3</span><sup></sup></li>

						</ul>

					</div>

				</div>

				<!--抽奖规则-->
				<div class="zadanGuizeBox">
					<div id="gradientLkk" class="guizeBox555">
						<h2>抽奖规则:</h2>
						<p>1.我可以参加抽奖吗？</p>
						<p>答：“享积分抽大奖”是互余购专属的活动，只要是互余购会员，有一定的积分，都可以参加抽奖。每次使用需2个积分</p>
						<p>2.奖品都有什么？</p>
						<p>答：活动设有4个奖项，1等奖为30个积分；2等奖为10个积分；3等奖为5个积分；4等奖为继续加油。</p>
						<p>3.抽奖有什么规则？</p>
						<p>答：每个会员抽奖次数不限</p>
						<p>4.我中奖了，怎么领取奖品？</p>
						<p>答：中奖后，系统自动发放到会员的互余购积分账户，查看 我的积分</p>
						<p>5.活动时间多久？</p>
						<p>答：每天都有哦。</p>
					</div>
				</div>
			</div>
		</div>

		<!--底部内容-->
		<jsp:include page="footer.jsp"></jsp:include>
		<!--底部内容-->
		<script>
			//头部导航
			var ullia = document.getElementsByClassName("ullia");
			var num1 = 1;
			startnav();
			for(var i = 0; i < ullia.length; i++) {
				ullia[i].index = i;
				ullia[i].onclick = function() {
					for(var i = 0; i < ullia.length; i++) {
						ullia[num1].style.cssText = 'background:none;';
					}
					num1 = this.index;
					start();
				}
			}

			function startnav() {
				ullia[num1].style.cssText = 'background: #6213ac;';
			}
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
			}

			function start() {
				navigationTitleList[num].style.display = 'block';
				navigationTitleHover[num].style.cssText = 'color:#ff4040;';
			}
			//
			$(" a.hoverClass").click(function() {
				$("a.hoverClass").removeClass("removes"); //siblings是循环遍历
				$(this).addClass("removes");

			});
			//砸蛋
			function eggClick(obj) {

				var _this = obj;

				if(_this.hasClass("curr")) {
					$(".mask").css("display", "block");
					$(".mask .modal").css("transform", "translateY(0)");
					$(".mask .modal h2.title_h2 span").text("蛋都碎了，别砸了！");
					$(".closeBtn").click(function() {
						$(".mask .modal").css("transform", "translateY(-20000000)");
						$(".mask").css("display", "none");
					});

					return false;

				}

				//_this.unbind('click');

				$(".hammer").css({
					"top": _this.position().top - 55,
					"left": _this.position().left + 185
				});

				$(".hammer").animate({

						"top": _this.position().top - 25,

						"left": _this.position().left + 125

					}, 30, function() {

						_this.addClass("curr"); //蛋碎效果

						_this.find("sup").show(); //金花四溅

						$(".hammer").hide();

						$("#result").empty();

						$("#result").hide();

						$('.resultTip').css({
							display: 'none',
							top: '100px',
							left: _this.position().left + 45,
							opacity: 0
						}).animate({
							top: '50px',
							opacity: 1
						}, 300, function() {

							//中奖状态
							var award = [];
							$.ajax({
								url:"/huyugo/luck/zadan.do",
								data:{
								      
								},  
								type:'post',  
								cache:false,  
								dataType:'html', 
								async: false, 
								success:function(data) {
									var result = "";
									$.ajax({
										url:'/huyugo/adminprize/getOneEgg.do',
										type:'post',
										async:false,
										dataType:'json',
										success:function(res){
											result = res;
										}
									});
								
									if(data == "notLogin"){
										award = [
											["您还未登录！", 1],
											["您还未登录！", 2],
											["您还未登录！", 3]
										];
									}else if(data == "notfullInteger"){
										award = [
											["没有足够的积分！", 1],
											["没有足够的积分！", 2],
											["没有足够的积分！", 3]
										];
									}else if(data == "1"){
										award = [
											["恭喜您获得"+result.prize1+"积分！", 1],
											["恭喜您获得"+result.prize1+"积分！", 2],
											["恭喜您获得"+result.prize1+"积分！", 3]
										];
									}else if(data == "2"){
										award = [
											["恭喜您获得"+result.prize2+"积分！", 1],
											["恭喜您获得"+result.prize2+"积分！", 2],
											["恭喜您获得"+result.prize2+"积分！", 3]
										];
									}else if(data == "3"){
										award = [
											["恭喜您获得"+result.prize3+"积分！", 1],
											["恭喜您获得"+result.prize3+"积分！", 2],
											["恭喜您获得"+result.prize3+"积分！", 3]
										];
									}else if(data == "0"){
										award = [
											["继续加油吧！", 1],
											["继续加油吧！", 2],
											["继续加油吧！", 3]
										];
									}
									$.ajax({
										url:"/huyugo/user/getUserById.do",
										data:{
										      
										},  
										type:'post',  
										cache:false,  
										dataType:'html',  
										success:function(data) {
											if(data == "notLogin"){
												alert("未登录");
												return;
											}
											var json = JSON.parse(data);
											$("#jifen").html(json.integral);
									    }
									});
									
									
								}
							});

							var num = 0;

							//几率求和

							for(var i = 0; i < award.length; i++) {

								num += award[i][1];

							}

							var r = 0;

							var award_k = '';

							//概率

							for(var i = 0; i < award.length; i++) {

								r = randomNum(1, num); 

								if(r <= award[i][1]) {

									award_k = award[i];

									break;

								} else {

									num -= award[i][1];

								}

							}

							//							alert();  
							$(".mask").css("display", "block");
							$(".mask .modal").css("transform", "translateY(0)");
							$(".mask .modal h2.title_h2 span").text(award_k[0]);
							$(".closeBtn").click(function() {
								$(".mask .modal").css("transform", "translateY(-20000000)");
								$(".mask").css("display", "none");
								window.location.href = "/huyugo/luck/showZadan.do";
							});

						});

					}

				);

			}

			//指定范围的随机数

			function randomNum(Min, Max) {

				var Range = Max - Min;

				var Rand = Math.random();

				return(Min + Math.round(Rand * Range));

			}
			$(".eggList li").click(function() {
				<%
					if(user != null){
				%>
				$(this).children("span").hide();
				eggClick($(this));
				<%}else{ %>
					alert("请先登陆");
				<%} %>
			});

			$(".eggList li").hover(function() {

				var posL = $(this).position().left + $(this).width();

				$("#hammer").show().css('left', posL);

			})
		</script>
	</body>

</html>