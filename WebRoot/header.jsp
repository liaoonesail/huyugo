<%@page import="com.huyu.entity.Affiche"%>
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
		<link rel="stylesheet" href="/huyugo/css/bass.css" />
		<link rel="stylesheet" href="/huyugo/css/header.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		<script type="text/javascript" src="/huyugo/js/jquery.Validform.min.js"></script>
		<script type="text/javascript">
			function getAllType(){
				$.ajax({
					url:"/huyugo/type/getAllGoodsType.do",
					data:{
										      
					},  
					type:'post',  
					cache:false,  
					dataType:'html',  
					success:function(data) {
						if(data != "error"){
							var str = "";
							var json = JSON.parse(data);
							var i = 0;
							for(var o in json){
								if(i % 2 == 0){
									str += "<dd><a href='/huyugo/list/showList.do?typeId=" + json[o].id + "'>" + json[o].type + "</a>";
								}else{
									str += "<a href='/huyugo/list/showList.do?typeId=" + json[o].id + "'>" + json[o].type + "</a></dd>";
								}
								i++
							}
							if(i % 2 == 0){
								str += "</dd>";
							}
							$("#dlType").html(str);
						}else{
							
						}
					}
				});
			}
		</script>
		<%
		String index_ = (String)request.getAttribute("index_");
		if(index_ == null){
			index_ = "";
		}
		User user = (User)session.getAttribute("user");
		
		 %>
	</head>
	<body>
			<!--头部导航部分-->
			<header class="headerConts">
				<!--头部顶部内容-->
				<div class="headerTop">
					<div class="publicBox clearfix">
						<ul>
							<li><img src="/huyugo/img/smallImg/1.png" /><span>您好，欢迎来本网站！</span></li>
							<%
							if(user == null){
							 %>
							<!--登录前的样式-->
							<li class="logoinLx">
								<a href="/huyugo/user/login.do">[请登录]</a>
							</li>
							<li class="logoinLx">
								<a href="/huyugo/user/reg.do">[免费注册]</a>
							</li>
							<!--登录前的样式-->
							<%} %>
						</ul>
						
						<div class="pull-right">
							<ol>
								<%
							if(user != null){
							 %>
								<!--登录后的样式-->
								<li class="logoinLx2">
									<a href="">
										<img src="/huyugo/img/member.jpg" width="15"  style="margin-top: 4px;"/>
										<%
											String nick = user.getName();
											if("".equals(nick)){
												nick = user.getPhone();
											}
										 %>
										<span><%=nick %></span>
									</a>
								</li>
								<li class="logoinLx2">
									<a href="/huyugo/user/loginOut.do">[退出]</a>&nbsp;&nbsp;&nbsp;
								</li>
								<!--登录后的样式-->
								<%} %>
								<li>
									<a target="_blank" rel="nofollow" href="tencent://message/?uin=762136258&Site=&menu=yes">在线客服&nbsp;</a>|</li>
								<li>
									<a href="/huyugo/car/showCarByUser.do">我的购物车&nbsp;</a>|</li>
								<li>
									<a href="/huyugo/person/showCenter.do">我的积分&nbsp;</a>|</li>
								<li>
									<a href="/huyugo/person/showCenter.do">会员中心</a>
								</li>
							</ol>
						</div>

					</div>
				</div>
				<!--头部顶部内容-->
				<!--头部中间内容-->
				<div class="publicBox logoImg clearfix">
					<a class="btnIndex" href="/huyugo/index.do">
						<img src="/huyugo/img/logo/logo.jpg" />
					</a>
					<div class="zhongJIan">
						<div class="zhongJIanBox clearfix">
							<div class="starxiang"></div>
							<select>
								<option>商品</option>
								<option>儿童用品</option>
								<option>儿童用品儿童用品</option>
								<option>营养保健</option>
							</select>
							<input type="text" placeholder="请输入搜索商品" id="nameSearch" /><button onclick="submitSearch();">搜索</button>
						</div>
						<div class="searchMsi">
							<!--热门搜索：电子玩具  模型飞机  车模  航模  夏装  眼镜-->
							<ul class="clearfix">
								<li>热门搜索：</li>
								<!-- <li><a href="/huyugo/goods/showGoodsBySerch.do?names=电子玩具">电子玩具</a></li> -->
								<script>
							        /*获取热门搜索*/
						        	var url = "/huyugo/goods/showGoodsBySerch.do?names=";
						        	var html = "";
						        	$.post("/huyugo/hotsearch/listByDisplay.do",{"display":1},function(res){
						        		$(res).each(function(index,obj){
						        			html += "<li><a href='"+url+encodeURI(encodeURI(obj.name))+"'>"+obj.name+"</a></li>";
						        		});
                                        $("div.searchMsi ul.clearfix").append(html);
                                    },"json");
								</script>
							</ul>
						</div>
					</div>
					<div class="pull-right headerErweimai clearfix">
						<a href="/huyugo/car/showCarByUser.do" class="gowucheBtn clearfix">
							<img src="/huyugo/img/smallImg/gowuche.png" /> 去购物车结算
							<div class="Triangle"></div>
						</a>
						<div class="weixinbox">
							<img src="/huyugo/img/smallImg/footerimg.png" />
							<p>互余官方微信</p>
						</div>
					</div>
				</div>
				<!--头部中间内容-->
				<!--nav部分-->
				<div class="nav">
					<div class="publicBox">
						<ul class="clearfix">
							<li class="clearfix">
								<span>所有商品分类<img src="/huyugo/img/smallImg/duo.png"/></span>
								<!--二级导航-->
								<div class="navigation clearfix"  <%if(index_.equals("ok")){ %>style="display: block;"<%} %>>
									<dl id="dlType">
										<!-- <dd>
											<a href="/huyugo/list/showList.do">清洁日用</a>
											<a href="">美妆护理</a>
										</dd> -->
									</dl>
									<!--公告-->
									<div class="noticebox">
										<div class="navigationTitle clearfix">
											<!-- <a class="navigationTitleHover">促销</a>
											<a>|</a>-->
											<a class="navigationTitleHover">公告</a>
											<a href="/huyugo/notice/showNoticAll.do">更多</a>
										</div>
										<div class="navigationTitleList" id="noticeList">
											
										</div>
									</div>
									<!--公告-->
								</div>
								<!--二级导航-->
							</li>
							<!--如果是当前页面对应的a标签加navok的class样式-->
							<li>
								<a class="ullia" href="/huyugo/index.do">首页</a>
							</li>
							<li>
								<a class="ullia" href="/huyugo/luck/showLuck.do" target="_blank">互余乐园 </a>
							</li>
							<li>
								<a class="ullia" href="/huyugo/activity/showActivity.do" target="_blank">活动商城</a>
							</li>
						 <li>
								<a class="ullia" href="/huyugo/adminVip_shop/show.do" target="_blank">VIP商城</a>
							</li>
							<li>
								<a class="ullia" href="/huyugo/adminIntegral_shop/show.do" target="_blank">兑换商城</a>
							</li>
							<li>
								<a class="ullia" href="http://url.cn/5e0c6o4" target="_blank">峰氏头条</a>
							</li>
						</ul>
					</div>
					</div>
				</div>
				<!--nav部分-->
			</header>
			<!--头部导航部分-->
		<script type="text/javascript" src="/huyugo/js/header.js"></script>	
		<script type="text/javascript">
		$(function() {
			
			getAllType();		
		
			$.ajax({
				url:"/huyugo/notice/getNoticeAll.do",
				data:{
				      
				},  
				type:'post',  
				cache:false,  
				dataType:'html',  
				success:function(data) {
					var json = eval(data);
					var str = "";
					$.each(json, function (index, item) {
						str += "<div><a href='/huyugo/notice/showNoticeById.do?id=" + json[index].id + "'>" + json[index].title + "</a></div>";
					});
					$("#noticeList").html(str);
			    }
			});
			
			
		});
		function submitSearch(){
		//alert($("#nameSearch").val());
			var nameSearch = $("#nameSearch").val();
			window.location.href = "/huyugo/goods/showGoodsBySerch.do?names=" + encodeURI(encodeURI(nameSearch));
		}
		</script>
	</body>
</html>

