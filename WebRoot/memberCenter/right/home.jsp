<%@page import="javax.persistence.criteria.CriteriaBuilder.In"%>
<%@page import="com.huyu.entity.User"%>
<%@page import="com.huyu.entity.Userinfo"%>
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
		<link rel="stylesheet" href="/huyugo/memberCenter/css/right.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
	</head>
	<body>
	<%
	User user = (User)request.getAttribute("user");
	String orderAll = (String)request.getAttribute("orderAll");
	String orderNoPay = (String)request.getAttribute("orderNoPay");
	String orderNoShouhuo = (String)request.getAttribute("orderNoShouhuo");
	String noPingjia = (String)request.getAttribute("noPingjia");
	String Pingjia = (String)request.getAttribute("Pingjia");
	String notuikuan = (String)request.getAttribute("notuikuan");
	String notuihuo = (String)request.getAttribute("notuihuo");
	String noSendGoods = (String)request.getAttribute("orderNoFahuo");
	Userinfo ui = (Userinfo)request.getAttribute("userinfo");

	String vipstr = "普通会员";
	String flag = "";
	if(user.getMember() == 1){
		flag = "已开通";
		if(user.getMember_grade() == 0){
			vipstr = "VIP0";
		}else if(user.getMember_grade() == 1){
			vipstr = "VIP1";
		}else if(user.getMember_grade() == 2){
			vipstr = "VIP2";
		}else if(user.getMember_grade() == 3){
			vipstr = "VIP3";
		}else if(user.getMember_grade() == 4){
			vipstr = "VIP4";
		}else if(user.getMember_grade() == 5){
			vipstr = "VIP5";
		}
	}
	
	 %>
		<div class="publicBox2">
			
			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>我的互余购</span>
				<!-- <span>交易管理</span>
				<span>></span>
				<span>充币记录</span> -->
			</div>
			
			<div class="publicBox2">
				<div class="right2BoxList clearfix">
					<!--编辑个人资料-->
					<div class="leftNavtouxiang">
						<div class="xiangxiangPic clearfix">
							<div class="xiangxiangPic11">
							<img src="<%if(ui.getHead_picture() != null && !"".equals(ui.getHead_picture())){ %><%=ui.getHead_picture() %><%}else{ %>/huyugo/img/commodity/b.png<%} %>"/>
							</div>
							<p><a href="/huyugo/person/showPersonal.do">编辑个人资料</a></p>
						</div>
						<div class="righyouLk">
							<p>您好~<%
								String name = user.getName();
								if("".equals(name)){
									name = user.getPhone();
								}
							 %>
								<span><%=name %></span>
								<span>(<img src="/huyugo/memberCenter/jiantou/rg_03.png"/>互余购
									<i><%=vipstr %></i>)
								</span>
							</p>
							<ul id="ListNavService" class="clearfix">
								<li>余额</li>
								<li>积分</li>
								<li>抵现金</li>
								<li>互余币</li>
								<li>养老金</li>
							</ul>
							<div class="meimeiYouLove clearfix">
								<p id="innhtml" style="height: 35px;line-height: 35px;"></p>
								<p>互余购VIP会员<%if(user.getMember() == 0){ %>：<span>未开通</span><a href="/huyugo/vip/kaitong.do">立即开通</a><%}else{ %>     <%=flag %><%} %><a href="/huyugo/adminTicket_shop/show.do" target="_blank">赠送商城</a></p>
							</div>
						</div>
					</div>
					<!--编辑个人资料-->
					<!--快捷菜单-->
					<div class="touxiangrightList clearfix">
						<h2>快捷菜单</h2>
						<ul class="clearfix">
							<li>
								<a href="/huyugo/fazhanOrder/showFazhanjin.do?curpage=1">发展金</a>
								<a href="/huyugo/car/showCarByUser.do" target="_blank">我的购物车</a>
								<a href="/huyugo/payment/showPriceDetail.do?curpage=1">我的资金详情</a>
							</li>
							<li>
								<a href="/huyugo/address/showAddress.do">我的收货地址</a>
								<a href="/huyugo/order/showOrder.do?zt=-1&curpage=1">我的订单</a>
							</li>
							<li>
								<a href="/huyugo/user/exchange.do?label=-1&money=-1">兑换功能</a>
								<a href="/huyugo/transfer/page.do">转账功能</a>
							</li>
						</ul>
					</div>
					<!--快捷菜单-->
				</div>
				<!--已买到的宝贝-->
				<div class="baobaokula clearfix">
					<h4>已买到的宝贝</h4>
					<ul>
						<!-- <li id="xdmL" onclick="javascript:window.location.href='/huyugo/order/showOrder.do?zt=-1'"> -->
						<li>
							<span><a href="/huyugo/order/showOrder.do?zt=-1&curpage=1">全部 <i><%=orderAll %></i></a></span>|
							<span><a href="/huyugo/order/showOrder.do?zt=0&curpage=1">待付款 <i><%=orderNoPay %></i></a></span>|
							<span><a href="/huyugo/order/showOrder.do?zt=1&curpage=1">待发货<i><%=noSendGoods %></i></a></span>|
							<span><a href="/huyugo/order/showOrder.do?zt=2&curpage=1">待确认收货 <i><%=orderNoShouhuo %></i></a></span>|
							<span><a href="/huyugo/order/showOrder.do?zt=3&curpage=1">待评价 <i><%=noPingjia %></i></a></span>|
							<span><a href="/huyugo/order/showOrder.do?zt=4&curpage=1">已完成 <i><%=Pingjia %></i></a></span>|
							<span><a href="/huyugo/order/showOrder.do?zt=5&curpage=1">待退款 <i><%=notuikuan %></i></a></span>|
							<span><a href="/huyugo/order/showOrder.do?zt=7&curpage=1">待退货 <i><%=notuihuo %></i></a></span>
						</li>
					</ul>
					<div class="clearfix"></div>
					
				<!--已买到的宝贝-->
			</div>
		</div>
		
		<script>
            //余额//积分//抵现金//互余币
            var ListNavService = document.getElementById("ListNavService")
		    var odiv = ListNavService.getElementsByTagName("li");
		    var innhtml =document.getElementById("innhtml");		    
		    odiv[0].className = 'ouLiy ';
		    conts="当前我的剩余余额:<span>[ <%=user.getAccount() %> ]</span><button onclick='window.location.href=\"/huyugo/memberCenter/right/account2.html\"'>提现</button>";
			innhtml.innerHTML=conts;
			for(var a = 0; a < odiv.length; a++) {
				odiv[a].index = a;
				odiv[a].onclick = function() {
					for(var b = 0; b < odiv.length; b++) {
						odiv[b].className = ' ';
					}
					this.className = 'ouLiy';
					if(this.index==0){
						conts="当前我的剩余余额:<span>[ <%=user.getAccount() %> ]</span><button onclick='window.location.href=\"/huyugo/memberCenter/right/account2.html\"'>提现</button>";
						innhtml.innerHTML=conts;
					}
					if(this.index==1){
						conts="当前我的剩余积分:<span>[ <%=user.getIntegral() %> ]</span>";
						innhtml.innerHTML=conts;
					}
					if(this.index==2){
						conts="当前我的剩余抵现金:<span>[ <%=user.getDixianjin() %> ]</span>";
						innhtml.innerHTML=conts;
					}
					if(this.index==3){
						conts="当前我的剩余互余币:<span>[ <%=user.getHuyubi() %> ]</span>";
						innhtml.innerHTML=conts;
					}
					if(this.index==4){
						conts="当前我的剩余养老金:<span>[ <%=user.getPension() %> ]</span>";
						innhtml.innerHTML=conts;
					}
				}
			};
           //已买到的宝贝
            var ListNavServicew = document.getElementById("xdmL")
			var odivs = ListNavServicew.getElementsByTagName("span");
			odivs[0].className = ' spannavLocalOk';
			for(var a = 0; a < odivs.length; a++) {
				odivs[a].index = a;
				odivs[a].onclick = function() {
					for(var b = 0; b < odivs.length; b++) {
						odivs[b].className = ' ';
					}
					this.className = 'spannavLocalOk';
				}
			};
			//更多信息
			$(".btnsdhover").css("display","none");
			var ddContsBox=$(".ddContsBox .ddContsBox1")
			for(var i=0;i<ddContsBox.length;i++) {
				if(i>=3){
					$(".btnsdhover").css("display","block");
					ddContsBox[i].style.display="none";
				}
			}
			$(".btnsdhover").click(function(){
				for(var i=0;i<ddContsBox.length;i++) {			 
					ddContsBox[i].style.display="block";				 
			     }
				$(".btnsdhover").empty();
			})
		</script>
	</body>
</html>
