<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.huyu.entity.Article"%>
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
		Article a = (Article)request.getAttribute("article");
		Affiche af = (Affiche)request.getAttribute("affiche");
		Integer type = (Integer)request.getAttribute("type");
	 %>
	<div class="publicBox2">
		<h3 class="helpTitle">
			<%
				if(type == 1){
			 %>
			<%=a.getTitle() %>
			<%
				}else{
			 %>
			 <%=af.getTitle() %>
			 <%
			 	}
			  %>
		</h3>
		<div style="padding:0 35px;">
			<% 
				if(type == 1){
			 %>
				<%=a.getPath() %>
			<%
				}else{
			 %>
			 	<%=af.getContent() %>
		 	<%
		 		}
		 	 %>
		</div>
	</div>
</body>
</html>