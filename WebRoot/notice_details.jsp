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
			.notice_box_detailed{
				padding: 10px 0px;
				min-height: 640px;
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

		<!--公告详情部分-->
		<div class="publicBox notice_box_detailed">
			${notice.content }
		</div>

		<!--公告详情部分-->
		<!--底部内容-->
		<jsp:include page="footer.jsp"></jsp:include>
	</body>

</html>