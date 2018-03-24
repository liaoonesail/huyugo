<%@page import="com.huyu.entity.Goods"%>
<%@page import="com.huyu.entity.Cuxiao"%>
<%@page import="com.huyu.entity.Goods_type"%>
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
		<link rel="stylesheet" href="/huyugo/css/List.css" />
		<script>
		var num1 = -1;
		</script>
	</head>

	<body>
		<%
		int typeId = (Integer)request.getAttribute("typeId");
		int sort = (Integer)request.getAttribute("sort");
		Object goodsTypeId = request.getAttribute("goodsTypeId");
		List<Goods_type> typeList = (List<Goods_type>)request.getAttribute("typeList");
		List<Goods> goodsList = (List<Goods>)request.getAttribute("goodsList");
		 %>
		<jsp:include page="header.jsp"></jsp:include>
		<input type="hidden" id="typeId" value="<%=typeId %>" />
		<input type="hidden" id="sort" value="<%=sort %>" />
		<!--商品分类列表--> 
		<div class="publicBox ListBox">
			<!--面包屑路径-->
			<div class="actricNva clearfix">
				<a href="/huyugo/index.do">首页</a>
				<p>></p>
				<p data-id="<%=typeId %>" class="type"><!-- 清洁日用 --></p>
			</div>
			<!--面包屑路径-->
			<!--分类标题-->
			<div class="Listadd" style="width: 1233px;">
				<!--分类-->
				<dl class="clearfix">
					<dt>分类</dt>
					<dd>
						<ul id="ListAdd" class="clearfix">
							<li>
								<a <%if(0 == typeId){ %>class="AddClass"<%} %> id="type_0" >全部</a>
							</li>
							<%
							for(Goods_type goodsType : typeList){
								%>
								<li>
									<a <%if(goodsType.getId() == typeId){ %>class="AddClass"<%} %> id="type_<%=goodsType.getId() %>" ><%=goodsType.getType() %></a>
								</li>
								<%
							}
							 %>
						</ul>
					</dd>
				</dl>
				<ul class="list-type-crumbs" style="border-top: 1px solid #e1e1e1;padding-top: 10px;font-size: 12px;">
                    <li class="list-type-color first_type"><a>全部</a></li>
                    <script>
                        /*点击全部*/
                        $(".first_type").click(function(){
                        	changeList();
                        });
                    </script>
                    <!-- <li><a href="/huyugo/goods/">纸制品</a></li> -->
                </ul>
                <script type="text/javascript">
                /*获取二级分类*/
                var type_id = $("p.type").attr("data-id");
                //$.post("../type/getNextGoodsType.do",{"goods_type_id":type_id},function(res){
                	$.ajax({
                		url:'../type/getNextGoodsType.do',type:"post",async:false,data:{"goods_type_id":type_id},success:function(res){
                			//console.log(res);
                			res = eval("("+res+")");
                            var html = "";//二级分类拼串
                            $(res).each(function(index,obj){
                                html += "<li data-value='"+obj.id+"'><a href='javascript:;' data-value='"+obj.id+"'>"+obj.type+"</a></li>";
                            });
                            $("li.list-type-color").after(html);
                		}
                	});
                //},"json");
                </script>
                
				<!--排序-->
				<dl class="clearfix" style="border-top: 1px solid #E5E5E5;">
					<dt>排序</dt>
					<dd style="padding-bottom: 0px;">
						<ul id="ListAdd2" class="paixuList clearfix">
							<li <%if(sort == 0){ %>class="AddClass2"<%} %> id="sort_0">综合</li>
							<li <%if(sort == 1){ %>class="AddClass2"<%} %> id="sort_1">最新</li>
							<li <%if(sort == 2){ %>class="AddClass2"<%} %> id="sort_2">价格低</li>
							<li <%if(sort == 3){ %>class="AddClass2"<%} %> id="sort_3">价格高</li>
							<%--<li <%if(sort == 4){ %>class="AddClass2"<%} %> class="hot">最热</li> --%>
						</ul>
					</dd>
				</dl>
			</div>
			<!--分类标题-->
			<!--分类商品列表-->
			<div class="ListDetails">
				<ul class="clearfix">
					<%
					if(goodsList != null){
						for(Goods goods : goodsList){
							if(goods.getDisplay()!=1){
								%>
								<li>
									<a href="/huyugo/goods/showGoodsById.do?id=<%=goods.getId() %>">
										<img src="<%=goods.getPicture_address() %>" />
										<p><%=goods.getName() %></p>
										<dt><span></span><span></span></dt>
										<dd><span>价值：¥<%=goods.getPrice() %></span></dd>
									</a>
								</li>
								<%
							}
						}
					}
					 %>

				</ul>
			</div>
			<!--分类商品列表-->
		</div>
		<!--商品分类列表-->

		<jsp:include page="footer.jsp"></jsp:include>
		<script>
		
		   
		    $(document).ready(function(){
		    	/*面包屑分类显示*/
		    	$("ul#ListAdd li").each(function(index,obj){
		    		var aObj = $(obj).find("a");
		    		if($(aObj).hasClass("AddClass")){
		    			$("p.type").html($(obj).text());
		    		}
		    	});
		    	
		    	/*二级分类--全部*/
		    	if($("#type_0").hasClass("AddClass")){
		    		$(".list-type-crumbs").css("display","none");
		    	}
		    	
		    	/*二级类别hover效果*/
		    	if(<%=goodsTypeId%> != null){
		    		$("ul.list-type-crumbs li[data-value="+<%=goodsTypeId%>+"]").addClass("list-type-color");
	                $("ul.list-type-crumbs").find("li").first().removeClass("list-type-color");
		    	}
		    	
		    });
		    
		    /*二级分类的点击*/
		   // $("li a[data-value]").click(function(){
			$(document).on("click","li a[data-value]",function(){
		    	//getNextGoodsType
		    	var goods_type_id = $(this).attr("data-value");
		    	window.location.href="/huyugo/list/goodsTypeList.do?typeId=<%=typeId%>&goods_type_id="+goods_type_id+"&sort=<%=sort%>";
		    });
		
			//分类选中的样式js
			var ListNavService = document.getElementById("ListAdd")
			var odiv = ListNavService.getElementsByTagName("a");
			for(var a = 0; a < odiv.length; a++) {
				odiv[a].index = a;
				odiv[a].onclick = function() {
					for(var b = 0; b < odiv.length; b++) {
						odiv[b].className = ' ';
					}
					this.className = 'AddClass';
					$("#typeId").val(this.id.split("_")[1]);
					changeList();
				}
			};
			//排序选中的样式js
			var ListNavService1 = document.getElementById("ListAdd2")
			var odiv1 = ListNavService1.getElementsByTagName("li");
			//odiv.length  => 4
			for(var a = 0; a < 4; a++) {
				odiv1[a].index = a;
				odiv1[a].onclick = function() {
					for(var b = 0; b < odiv1.length; b++) {
						odiv1[b].className = ' ';
					}
					this.className = 'AddClass2';
					//alert(this.id);
					$("#sort").val(this.id.split("_")[1]);
					//alert($("#sort").val());
					changeList();
				}
				
			};
			function changeList(){
				var typeId = $("#typeId").val();
				var sort = $("#sort").val();
				window.location.href = "/huyugo/list/showList.do?typeId="+typeId+"&sort="+sort;
			}
		</script>

	</body>

</html>