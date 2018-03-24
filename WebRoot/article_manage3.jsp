<%@page import="com.huyu.entity.Fazhanjin"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>后台欢迎页</title>

<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body marginwidth="0" marginheight="0">
	<%
		Fazhanjin m = (Fazhanjin) request.getAttribute("fazhanjin");
	 %>


	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">互余购发展金指标</h3>
				<form class="form-horizontal" method="post" action="/huyugo/adminFazhanjin/update.do">
					<div class="control-group">
						<label class="control-label">一代奖</label>
						<div class="controls">
							<input type="text" name="dai1" value="<%=m.getDai1() %>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">二代奖</label>
						<div class="controls">
							<input type="text" name="dai2"  value="<%=m.getDai2()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">三代奖</label>
						<div class="controls">
							<input type="text" name="dai3" value="<%=m.getDai3()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">每加盟一次送加盟额度50%的发展金</label>
						<div class="controls">
							<input type="text" name="chongzhi" value="<%=m.getChongzhi()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">每次加盟获得加盟金1%的养老金</label>
						<div class="controls">
							<input type="text" name="yanglao" value="<%=m.getYanglao()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">静态收益：每天1%释放分红</label>
						<div class="controls">
							<input type="text" name="shifang" value="<%=m.getShifang()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">把每天释放的分红这个分到余额账户90%</label>
						<div class="controls">
							<input type="text" name="shifangY" value="<%=m.getShifangY()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">把每天释放的分红这个分到积分账户10%</label>
						<div class="controls">
							<input type="text" name="shifangJ" value="<%=m.getShifangJ()%>" />
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">管理奖：享受每天直推收入的5%（要计算2笔，第1代代奖收入和第1代服务奖收入）</label>
						<div class="controls">
							<input type="text" name="guanli" value="<%=m.getGuanli()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">服务奖，新加入会员每加盟一单奖励3%（只有一次）（下级在拿奖励了，上级就没有了）</label>
						<div class="controls">
							<input type="text" name="fuwu" value="<%=m.getFuwu()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">服务奖：直推10人、业绩累计达到50万，开通服务奖</label>
						<div class="controls">
							<input type="text" name="fuwujin" value="<%=m.getFuwujin()%>" />
							<input type="text" name="id" value="<%=m.getId()%>" style="display: none" />
						</div>
					</div>
					
					<input type="submit" value="提交">
		
				</form>
			</div>
		</div>
	</div>
</body>
</html>