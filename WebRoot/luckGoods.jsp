<%@page import="com.huyu.entity.Userinfo"%>
<%@page import="com.huyu.entity.User"%>
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
		<title>大转盘</title>
		<meta name="keywords" content="互余，互余网、互余购、万州商城，直购商城，休闲娱乐" />
		<meta name="description" content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
		<link rel="stylesheet" href="/huyugo/css/luckGoods.css" />
		<link rel="stylesheet" href="/huyugo/css/luckgoods1.css" />
		<link rel="stylesheet" href="/huyugo/css/Luck1_1.css" />
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
										///alert("未登录");
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
		<jsp:include page="header.jsp"></jsp:include>
		<!--头部导航部分-->
		<!--弹窗内容-->
		<div class="mask">
			<div class="modal">
				<a href="#" class="closeBtn">×</a>
				<h1 class="title_h1 clearfix"><span></span></h1>
				<h2 class="title_h2"><span></span></h2>
			</div>
		</div>
		<!--弹窗内容-->
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
		.zadanBox{
			text-align: justify;
			color: #fff;
			padding-top: 20px;
		}
		.zadanBox p{
			margin-left: 120px;
		}
		.playbtn{
			z-index: 888888;
		}
	</style>
		
		<div class="publicBox btnsstrbg">
			<a class="removes" href="/huyugo/luck/showLuck.do">幸运大转盘</a>
			<a href="/huyugo/luck/showZadan.do">砸金蛋</a>
		</div>
		<div class="publicBoxBackgroud">
			<div class="publicBox">
				<!-- <div class="zadanBox">
					<p>活动对象：互余购在线会员</p>
					<p>活动时间：2017年6月13日开始-7月13号结束为期25天</p>
					<p>活动内容：活动期间，每人每天拥有一次砸金蛋机会</p>
					<p>中奖奖品：话费5元、话费10元、积分10分、积分20分、优惠券50元、优惠券100元</p>
				</div> -->
				<div class="publicBox2 clearfix">
					<img class="guangoodsImg0" src="/huyugo/img/titlegoods.png" />
					<img class="guangoodsImg" src="/huyugo/img/guan.png" />
					<!--用户信息-->
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
					<div class="TxListBox">
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
					<!--用户信息-->
					<!--获奖公告-->
					<div class="huojiangBox">
						<div class="huojiangBoxTitle">恭喜以下达人中奖</div>
						<marquee behavior="scroll" loop="-1" width="300" height="300" direction="up">
							<ul id="buyList">
								<%
								int i = 0;
								List<Prize_record> prizeList = (List<Prize_record>)request.getAttribute("prizeList");
								if(prizeList != null && prizeList.size() > 0){
									for(Prize_record prize : prizeList){
										if(Integer.parseInt(prize.getPrize()) < 6){
											%>
											<li><%=prize.getPrize() %>等奖  <font id="font<%=i %>"></font> <%=prize.getContent() %></li>
											<script>getUser("<%=prize.getUser_id() %>","<%=i %>")</script>
											<%
										}
										i++;
									}
								}
								 %>
							</ul>
						</marquee>
					</div>
					<!--获奖公告-->
					<!--转盘-->
					<div class="LuckGood">
						<div class="g-lottery-box">
							<!-- <div class="Luck1_1">
								<p>一等奖</p>
								<p>100互余积分</p>
								<dt><img src="/huyugo/img/didi2.png"/></dt>
							</div>
							<div class="Luck1_2">
								<p>二等奖</p>
								<p>50积分</p>
								<dt><img src="/huyugo/img/didi1.png"/></dt>
							</div>
							<div class="Luck1_3">
								<dt><img src="/huyugo/img/didi2.png"/></dt>
								<p>三等奖</p>
								<p>30互余币</p>
							</div>
							<div class="Luck1_4">
								<dt><img src="/huyugo/img/didi1.png"/></dt>
								<p>四等奖</p>
								<p>20积分</p>
							</div>
							<div class="Luck1_5">
								<dt><img src="/huyugo/img/didi1.png"/></dt>
								<p>五等奖</p>
								<p>5互余币</p>
							</div> -->
							<div class="Luck1_1">
								<p>一等奖</p>
								<p><span></span>元红包</p>
								<dt><img src="/huyugo/img/didi2.png"/></dt>
							</div>
							<div class="Luck1_2">
								<p>二等奖</p>
								<p><span></span>元红包</p>
								<dt><img src="/huyugo/img/didi1.png"/></dt>
							</div>
							<div class="Luck1_3">
								<dt><img src="/huyugo/img/didi2.png"/></dt>
								<p>三等奖</p>
								<p><span></span>元红包</p>
							</div>
							<div class="Luck1_4">
								<dt><img src="/huyugo/img/didi1.png"/></dt>
								<p>四等奖</p>
								<p><span></span>元红包</p>
							</div>
							<div class="Luck1_5">
								<dt><img src="/huyugo/img/didi1.png"/></dt>
								<p>五等奖</p>
								<p><span></span>元红包</p>
							</div>
							<div class="Luck1_6">
								<p>继续</p>
								<p>加油吧!</p>
								<dt><img src="/huyugo/img/didi3.png"/></dt>
							</div>
							<div class="g-lottery-img">
								<a class="playbtn" href="javascript:;" title="开始抽奖"></a>
							</div>
						</div>
					</div>
					<!--转盘-->
					<script type="text/javascript">
					
							$.ajax({
								url:'/huyugo/adminprize/getOne.do',
								type:'post',
								async:false,
								dataType:'json',
								success:function(res){
									$(".Luck1_1").find("p").eq(1).find("span").html(res.prize1);
									$(".Luck1_2").find("p").eq(1).find("span").html(res.prize2);
									$(".Luck1_3").find("p").eq(1).find("span").html(res.prize3);
									$(".Luck1_4").find("p").eq(1).find("span").html(res.prize4);
									$(".Luck1_5").find("p").eq(1).find("span").html(res.prize5);
								}
							});
						
						
					</script>

					<div class="guizeBox">
						<div id="gradientLkk" class="guizeBox555">
							<h2>抽奖规则:</h2>
							<p>1.我可以参加抽奖吗？</p>
							<p>答：“享积分抽大奖”是互余购专属的活动，只要是互余购会员，有一定的积分，都可以参加抽奖。每次使用需2个积分</p>
							<p>2.奖品都有什么？</p>
							<p>答：活动设有6个奖项，1等奖为100元红包；2等奖为50元红包；3等奖为30元红包；4等奖为10元红包；5等奖为5元红包；6等奖为继续加油。</p>
							<p>3.抽奖有什么规则？</p>
							<p>答：每个会员抽奖次数不限</p>
							<p>4.我中奖了，怎么领取奖品？</p>
							<p>答：中奖后，系统自动发放到会员的互余购余额账户，查看 账户管理-账户明细</p>
							<p>5.活动时间多久？</p>
							<p>答：每天都有哦。</p>
							<p>6.可不可以提现</p>
							<p>答：累计一定数量都可以提现</p>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!--底部内容-->
		<jsp:include page="footer.jsp"></jsp:include>
		<!--底部内容-->
		<script type="text/javascript" src="/huyugo/js/jquery.rotate.min.js"></script>
		<script>
			 
			//大转盘
			var $btn = $('.playbtn');
			var playnum = 1000; //初始次数，由后台传入
			$('.playnum').html(playnum);
			var isture = 0;
			var clickfunc = function() {
				/*var data = [1 , 2, 3, 4, 5, 6];*/
			
				$.ajax({
					url:"/huyugo/luck/choujiang.do",
					data:{
					      
					},  
					type:'post',  
					cache:false,  
					dataType:'html',  
					success:function(data) {
						if(data == "notLogin"){
							alert("请先登录!");
						}else if(data == "notfullInteger"){
							alert("没有足够的积分！");
						}else{
							var json = JSON.parse(data);
							var pk = null;
							$.ajax({
								url:'/huyugo/adminprize/getOne.do',
								type:'post',
								async:false,
								dataType:'json',
								success:function(res){
									pk = res;
								}
							});
							switch(parseInt(json.jiangId)) {
								case 1:
									rotateFunc(1, 0, '获得'+pk.prize1+'元红包');
									break;
								case 2:
									rotateFunc(2, 60, '获得'+pk.prize2+'元红包');
									break;
								case 3:
									rotateFunc(3, 120, '获得'+pk.prize3+'元红包');
									break;
								case 4:
									rotateFunc(4, 180, '获得'+pk.prize4+'元红包');
									break;
								case 5:
									rotateFunc(5, 240, '获得'+pk.prize5+'元红包');
									break;
								case 6:
									rotateFunc(6, 300, '继续加油吧！');
									break;
								default:
									rotateFunc(6, 300, '继续加油吧！');
									break;
							}
							/*switch(parseInt(json.jiangId)) {
								case 1:
									rotateFunc(1, 0, '获得100互余币!');
									break;
								case 2:
									rotateFunc(2, 60, '获得50积分');
									break;
								case 3:
									rotateFunc(3, 120, '获得30互余币');
									break;
								case 4:
									rotateFunc(4, 180, '获得20积分');
									break;
								case 5:
									rotateFunc(5, 240, '获得5互余币');
									break;
								case 6:
									rotateFunc(6, 300, '继续加油吧！');
									break;
								default:
									rotateFunc(6, 300, '继续加油吧！');
									break;
							}*/
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
									$("#jifen").html("积分："+ json.integral);
							    }
							});
							
						}
				    }
				});
				
			}
			$btn.click(function() {
				if(isture) return; // 如果在执行就退出
				isture = true; // 标志为 在执行
				//先判断是否登录,未登录则执行下面的函数
				if(1 == 2) {
					$('.playnum').html('0');
					alert("请先登录");
					isture = false;
				} else { //登录了就执行下面
					if(playnum <= 0) { //当抽奖次数为0的时候执行
						$(".mask").css("display", "block");
						$(".mask .modal").css("transform", "translateY(0)");
						$(".mask .modal h2.title_h2 span").text("没有次数了");
						$(".closeBtn").click(function() {
							$(".mask .modal").css("transform", "translateY(-20000000)");
							$(".mask").css("display", "none");
						});
						$('.playnum').html(0);
						isture = false;

					} else { //还有次数就执行
						playnum = playnum - 1; //执行转盘了则次数减1
						if(playnum <= 0) {
							playnum = 0;
						}
						$('.playnum').html(playnum);
						clickfunc();
					}
				}
			});
			//对应的奖品顺序awards  对应的奖品角度angle 对应的奖品text
			var rotateFunc = function(awards, angle, text) {
				isture = true;
				$btn.stopRotate();
				$btn.rotate({
					angle: 0,
					duration: 5000, //旋转时间
					animateTo: angle + 1440, //让它根据得出来的结果加上1440度旋转
					callback: function() {
						isture = false; // 标志为 执行完毕
						//alert(text);
							$(".mask").css("display", "block");
							$(".mask .modal").css("transform", "translateY(0)");
							$(".mask .modal h2.title_h2 span").text(text);//弹窗显示对应的奖品
							$(".closeBtn").click(function() {
								$(".mask .modal").css("transform", "translateY(-20000000)");
								$(".mask").css("display", "none");
							});

					}
				});
			};
			
		</script>
	</body>

</html>