<%@page import="com.huyu.entity.Goods"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>åå°æ¬¢è¿é¡µ</title>
<link rel="stylesheet" href="/huyugo/main/css/reset.css" />
<link rel="stylesheet" href="/huyugo/main/css/content.css" />
<link rel="stylesheet" href="/huyugo/main/css/public.css">
<script src="/huyugo/main/js/jquery.min.js"></script>
</head>
<script>
	
	
	var goods_type_id="1";
	$(function(){
		/* 回车搜索事件*/
		$("#search").bind("keypress",function(e){
			if(e.keyCode == "13"){
				var name = $("#search").val();
				searchGoods(name);
			}
		});
	})
function searchGoods(name){
	var curpage=1;
	 $.post(
	"../../admingoods/list.do",
	{"goods_type_type_id":"0","curpage":1,"name":name},
	function(data){
	var li="";
	var order=0;
       $.each(data.list,function(){
       order+=1;
   		var item = this;
         var display = "";
   		if(item.display == "1"){
   			display = "下架中";
   		}else{
   			display = "上架中";
   		}
   		var shop = item.shopId;
   		if(item.shopId != ""){
   			$.ajax({
	   			url:'../../adminshop/getid.do',
	   			type:'post',
	   			data:{id:shop},
	   			async:false,
	   			dataType:'json',
	   			success:function(res){
	   				shop = res[0].name;
	   			}
	   		});
   		}
         li+="<tr><td><input type='checkbox' /></td><td>"+order+"</td><td><span style='color:#27828A'>"+item.name+"</span></td><td><img src="+item.picture_address+" height='75' width='150' /></td><td>"+item.price+"</td><td>"+item.amount+"</td><td>"+display+"</td><td>"+shop+"</td><td><div class='table-fun'><a href='../detail_picture/article_manage.html?id="+item.id+"' style='cursor:pointer' >详情图</a><a href='article_update.html?id="+item.id+"'>修改</a><a onclick='del("+item.id+")' style='cursor:pointer'>删除</a></td></div></tr>";
        }); 
        $("#head").nextAll().remove();
	$("#head").after(li); 
	},
	"json"
	); 
	}
	function del(id){
	if(confirm("确认要删除吗？")){
	$.post(
	"/huyugo/admingoods/del.do",
	{"id":id},
	function(data){
	if(data>0){
	alert("删除成功");
	window.location.reload();
	}
	},
	"text"
	);
	}
	}
	
	function shop(shop,i){
  		if(shop != ""){
  			$.ajax({
	   			url:'../adminshop/getid.do',
	   			type:'post',
	   			data:{id:shop},
	   			async:false,
	   			dataType:'json',
	   			success:function(res){
	   				shop = res[0].name;
	   				$(".a_"+i).html(shop);
	   			}
	   		});
  		}
	}
	
	</script>
<body marginwidth="0" marginheight="0">
	<div class="container">
		<div class="public-nav">
			您当前的位置：<a href="">管理首页</a>><a href="">商品类别</a>><a href="">商品管理</a>
		</div>
		<div class="public-content">
			<div class="public-content-header">
				<style>
.searchBox {
	position: absolute;
	top: -21px;
	right: 110px;
	width: 280px;
}

.form-input-txt {
	width: 100%;
	height: 23px;
	/* line-height: 6px; */
	border-radius: 8px;
	border: 1px solid #ccc;
	padding-left: 10px;
	outline: none;
}
</style>
				<h3 style="display: inline-block;">修改网站配置</h3>
				<div class="public-content-right add" style="position: relative;">
					<div class="searchBox form-group">
						<input style="width:100%;" type="text" name="name" id="search"
							placeholder="回车搜索商品" class="form-input-txt" />
					</div>
					<a href="article_add.html"
						style="height: 24px; width: 60px;border: 1px solid #ccc;font-size: 12px;text-align:center">添加信息</a>
				</div>
			</div>
			<div class="clearfix"></div>
			<div class="public-content-cont two-col">
				<div class="public-ifame mt20">

					<div class="clearfix"></div>
					<!-- 左侧导航栏 -->
					<div class="public-ifame-leftnav">
						<ul class="left-nav-list" id="title">

						</ul>
					</div>



				</div>
				<table class="public-cont-table col-2" id="table">
					<tr id="head">
						<th style="width:5%">选择</th>
						<th style="width:5%">ID</th>
						<th style="width:10%">商品名</th>
						<th style="width:35%">商品图片</th>
						<th style="width:5%">价格(元)</th>
						<th style="width:5%">库存(个)</th>
						<th>是否上架</th>
						<th>供应商</th>
						<th style="width:15%">操作</th>
					</tr>
					<%
						List<Goods> list = (List<Goods>)request.getAttribute("list");
						int i = 1;
						if(list!= null){
							for(Goods g : list){
					 %>
					<tr>
						<td><input type='checkbox' /></td>
						<td><%=i %></td>
						<td><span style='color:#27828A'<%=g.getName() %></span></td>
						<td><img src="<%=g.getPicture_address() %>" height='75' width='150' />
						</td>
						<td><%=g.getPrice() %></td>
						<td><%=g.getAmount() %></td>
						<td>下架中</td>
						<td class="a_<%=i%>">
						<%
							if(g.getShopId() != 0){
						 %>
						 <script type="text/javascript">shop(<%=g.getShopId()%>,<%=i%>)</script>
						 <% } else{ %>
						 <%=0 %>
						 <%} %>
						</td>
						<td><div class='table-fun'>
								<a href='/huyugo/main/detail_picture/article_manage.html?id=<%=g.getId()%>'
									style='cursor:pointer'>详情图</a><a
									href='/huyugo/main/goods/article_update.html?id=<%=g.getId()%>'>修改</a><a
									onclick='del(<%=g.getId() %>)' style='cursor:pointer'>删除</a>
						</td>
						</div>
					</tr>
					<%
								i++;
							}
						}
					 %>

				</table>
			</div>
		</div>
	</div>
</body>
</html>