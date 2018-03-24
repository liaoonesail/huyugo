<%@page import="com.huyu.entity.Member"%>
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
		Member m = (Member)request.getAttribute("member");
	 %>


	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<h3 class="text-center">互余购会员奖励指标</h3>
				<form class="form-horizontal" method="post" action="/huyugo/adminMember/update.do">
					<div class="control-group">
						<label class="control-label">会员一代奖</label>
						<div class="controls">
							<input type="text" name="m_dai1" value="<%=m.getM_dai1() %>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">会员二代奖</label>
						<div class="controls">
							<input type="text" name="m_dai2"  value="<%=m.getM_dai2()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">会员三代奖</label>
						<div class="controls">
							<input type="text" name="m_dai3" value="<%=m.getM_dai3()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v1直推10人享受团队业绩的4%</label>
						<div class="controls">
							<input type="text" name="m_v1" value="<%=m.getM_v1()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v1下级团队的的0%</label>
						<div class="controls">
							<input type="text" name="m_v1_1" value="<%=m.getM_v1_1()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v2直推10人并且培养5个v1享受团队业绩的6%</label>
						<div class="controls">
							<input type="text" name="m_v2" value="<%=m.getM_v2()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v1下级团队的2%</label>
						<div class="controls">
							<input type="text" name="m_v2_1" value="<%=m.getM_v2_1()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v2下级团队的<!-- 0% --></label>
						<div class="controls">
							<input type="text" name="m_v2_2" value="<%=m.getM_v2_2()%>" />
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">v3直推10人并且培养12个v1享受团队业绩<!-- 的8% --></label>
						<div class="controls">
							<input type="text" name="m_v3" value="<%=m.getM_v3()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v1下级团队的<!-- 4% --></label>
						<div class="controls">
							<input type="text" name="m_v3_1" value="<%=m.getM_v3_1()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v2下级团队的<!-- 2% --></label>
						<div class="controls">
							<input type="text" name="m_v3_2" value="<%=m.getM_v3_2()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v3下级团队的0%</label>
						<div class="controls">
							<input type="text" name="m_v3_3" value="<%=m.getM_v3_3()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">达到v2级别后享受补贴3%</label>
						<div class="controls">
							<input type="text" name="m_v2_service" value="<%=m.getM_v2_service()%>" />
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">购买一代奖</label>
						<div class="controls">
							<input type="text" name="b_dai1" value="<%=m.getB_dai1()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">购买二代奖</label>
						<div class="controls">
							<input type="text" name="b_dai2" value="<%=m.getB_dai2()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">购买三代奖</label>
						<div class="controls">
							<input type="text" name="b_dai3" value="<%=m.getB_dai3()%>" />
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">v1直推10人享受团队业绩的2%</label>
						<div class="controls">
							<input type="text" name="b_v1" value="<%=m.getB_v1()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v1下级团队的的0%</label>
						<div class="controls">
							<input type="text" name="b_v1_1" value="<%=m.getB_v1_1()%>" />
						</div>
					</div>
					
					<div class="control-group">
						<label class="control-label">v2直推10人并且培养5个v1享受团队业绩的4%</label>
						<div class="controls">
							<input type="text" name="b_v2" value="<%=m.getB_v2()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v1下级团队的2%</label>
						<div class="controls">
							<input type="text" name="b_v2_1" value="<%=m.getB_v2_1()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v2下级团队的0%</label>
						<div class="controls">
							<input type="text" name="b_v2_2" value="<%=m.getB_v2_2()%>" />
						</div>
					</div>
					
					
					<div class="control-group">
						<label class="control-label">v3直推10人并且培养12个v1享受团队业绩的6%</label>
						<div class="controls">
							<input type="text" name="b_v3" value="<%=m.getB_v3()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v1下级团队的4%</label>
						<div class="controls">
							<input type="text" name="b_v3_1" value="<%=m.getB_v3_1()%>" />
						</div>
					</div><div class="control-group">
						<label class="control-label">v2下级团队的2%</label>
						<div class="controls">
							<input type="text" name="b_v3_2" value="<%=m.getB_v3_2()%>" />
						</div>
					</div>
					<div class="control-group">
						<label class="control-label">v3下级团队的0%</label>
						<div class="controls">
							<input type="text" name="b_v3_3" value="<%=m.getB_v3_3()%>" />
						</div>
					</div>
					
					<input type="submit" value="提交">
		
				</form>
			</div>
		</div>
	</div>
</body>
</html>