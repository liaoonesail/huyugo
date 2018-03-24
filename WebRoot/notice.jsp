<%@page import="com.huyu.entity.Affiche"%>
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
		<style>
			.notice_box_title {
				width: 100%;
				line-height: 35px;
				color: #232323;
				font-size: 16px;
				border-bottom: 1px solid #999999;
				margin-top: 20px;
			}
			
			.notice_box ul li {
				width: 100%;
				text-align: justify;
				font-size: 14px;
				color: #000;
			}
			
			.notice_box ul li a {
				display: block;
				width: 100%;
				
				border-bottom: 1px solid #999999;
			}
			
			.notice_box ul li a p {
				padding: 6px 0px;
				float: left;
				width: 88%;
				display: -webkit-box;
				text-align: justify;
				overflow: hidden;
				white-space: normal !important;
				text-overflow: ellipsis;
				word-wrap: break-word;
				-webkit-line-clamp: 2;
				-webkit-box-orient: vertical;
				line-height: 24px;
			}
			
			.notice_box ul li a span {
				float: right;
				width: 12%;
                line-height: 45px;
				overflow: hidden;
				text-align: center;
				color: #000 !important;
				white-space: nowrap;
				text-overflow: ellipsis;
			}
			
			.notice_box ul li:hover a p {
				color: #47176D !important;
				 
			}
			
			.notice_box ul li:hover a span {
				color: #47176D !important;
			 
			}
		</style>
		<script type="text/javascript">
		num1 = -1;
		</script>
	</head>

	<body>
		<!--头部导航部分-->
		<jsp:include page="header.jsp"></jsp:include>
		<!--头部导航部分-->

		<!--公告部分-->
		<div class="publicBox notice_box">
			<div class="notice_box_title">互余购公告</div>
			<ul>
				<%
				List<Affiche> afficheList = (List<Affiche>)request.getAttribute("afficheList");
				for(Affiche affiche : afficheList){
					%>
					<li>
						<a href="/huyugo/notice/showNoticeById.do?id=<%=affiche.getId() %>" class="clearfix">
							<p><%=affiche.getTitle() %> </p>
							<span> <%=affiche.getDate_time() %></span>
						</a>
					</li>
					<%
				}
				 %>
			</ul>
		</div>

		<!--公告部分-->
		<!--底部内容-->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>

</html>