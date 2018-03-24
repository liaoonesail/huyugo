<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.huyu.entity.Affiche"%>
<%@page import="com.huyu.entity.Affiche"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="righthelp.css" />
</head>
<body>
	<%
		Integer type = (Integer)request.getAttribute("type");
		if(type == 1){
			Affiche af = (Affiche)request.getAttribute("af");
		}else{
			
		}
		
	 %>
	<div class="publicBox2">
		<h3 class="helpTitle"><%=af.getTitle() %></h3>
		<div style="padding:0 35px;">
			<%=af.getContent() %>
		</div>
	</div>
</body>
</html>