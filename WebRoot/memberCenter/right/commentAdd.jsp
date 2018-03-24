<%@page import="com.huyu.entity.Goods"%>
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
		<link rel="stylesheet" href="/huyugo/memberCenter/css/address.css" />
		<link rel="stylesheet" href="/huyugo/memberCenter/css/pinglun0.css" />
		<link href="/huyugo/memberCenter/css/IMGUP.css" rel="stylesheet" />		
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		<script src="/huyugo/memberCenter/js/jquery-migrate-1.2.1.min.js"></script>
	</head>

	<body>
		<%
		User user = (User)request.getAttribute("user");
		String orderId = (String)request.getAttribute("orderId");
		 %>
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>商品评论  <%--=goods.getName() --%></span>

			</div>
			<div class="pimLunBox clearfix">
				<!--卖家的服务态度打分-->
				<div id="star">
					<span>商品打分</span>
					<ul>
						<li>
							<a href="javascript:;">1</a>
						</li>
						<li>
							<a href="javascript:;">2</a>
						</li>
						<li>
							<a href="javascript:;">3</a>
						</li>
						<li>
							<a href="javascript:;">4</a>
						</li>
						<li>
							<a href="javascript:;">5</a>
						</li>
					</ul>
					<span></span>
					<p></p>
				</div>
				<!--卖家的服务态度打分-->
				<!--卖家的发货速度打分-->
				<div id="star1">
					<span>卖家的服务态度打分</span>
					<ul>
						<li>
							<a href="javascript:;">1</a>
						</li>
						<li>
							<a href="javascript:;">2</a>
						</li>
						<li>
							<a href="javascript:;">3</a>
						</li>
						<li>
							<a href="javascript:;">4</a>
						</li>
						<li>
							<a href="javascript:;">5</a>
						</li>
					</ul>
					<span></span>
					<p></p>
				</div>
				<!--卖家的发货速度打分-->
				<div id="star2">
					<span>快递态度打分</span>
					<ul>
						<li>
							<a href="javascript:;">1</a>
						</li>
						<li>
							<a href="javascript:;">2</a>
						</li>
						<li>
							<a href="javascript:;">3</a>
						</li>
						<li>
							<a href="javascript:;">4</a>
						</li>
						<li>
							<a href="javascript:;">5</a>
						</li>
					</ul>
					<span></span>
					<p></p>
				</div>
				<!--卖家的发货速度打分-->
				<!--评论内容-->
				<p>请输入评论内容:</p>
				<textarea name="content" placeholder="请输入评论内容"></textarea>
				<!--评论内容-->
				<!--图片选择对话框-->
				<!-- <div id="div_imgfile">选择图片</div> -->
				<!--图片预览容器-->
				<!-- <div id="div_imglook">
					<div style="clear: both;"></div>
				</div> -->
				<!--确定上传按钮
				<input type="button" value="确定上传" id="btn_ImgUpStart" />-->
				<form action="/huyugo/goodsComment/addGoodsComment.do" id="comment" method="post">
				    <input type="hidden" name="comment" value="">
				    <input type="hidden" name="user_id" value="<%=user.getId()%>">
				    <input type="hidden" name="orderId" value="<%=orderId%>">
				    <input type="hidden" name="goods_grade">
				    <input type="hidden" name="service_grade">
				    <input type="hidden" name="deliver_grade">
				    <!-- <a class="pingLBTnsdd">发表评论</a> -->
				    <input type="button" class="pingLBTnsdd" value="发表评论" />
				</form>
			</div>
       
		</div>
		
         <script src="/huyugo/memberCenter/js/IMGUP.js"></script>
		<script>
			//卖家的服务态度打分
			window.onload = function() {
				var oStar = document.getElementById("star");
				var aLi = oStar.getElementsByTagName("li");
				var oUl = oStar.getElementsByTagName("ul")[0];
				var oSpan = oStar.getElementsByTagName("span")[1];
				var oP = oStar.getElementsByTagName("p")[0];
				var i = iScore = iStar = 0;
				var aMsg = [
					"很不满意|差得太离谱，与卖家描述的严重不符，非常不满",
					"不满意|部分有破损，与卖家描述的不符，不满意",
					"一般|质量一般，没有卖家描述的那么好",
					"满意|质量不错，与卖家描述的基本一致，还是挺满意的",
					"非常满意|质量非常好，与卖家描述的完全一致，非常满意"
				]
				for(i = 1; i <= aLi.length; i++) {
					aLi[i - 1].index = i;
					//鼠标移过显示分数
					aLi[i - 1].onmouseover = function() {
						fnPoint(this.index);
						//浮动层显示
						oP.style.display = "block";
						//计算浮动层位置
						oP.style.left = oUl.offsetLeft + this.index * this.offsetWidth - 104 + "px";
						//匹配浮动层文字内容
						oP.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg[this.index - 1].match(/\|(.+)/)[1]
					};
					//鼠标离开后恢复上次评分
					aLi[i - 1].onmouseout = function() {
						fnPoint();
						//关闭浮动层
						oP.style.display = "none"
					};
					//点击后进行评分处理
					aLi[i - 1].onclick = function() {
						iStar = this.index;
						oP.style.display = "none";
						oSpan.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg[this.index - 1].match(/\|(.+)/)[1] + ")"
					}
				}
				//评分处理
				function fnPoint(iArg) {
					//分数赋值
					iScore = iArg || iStar;
					for(i = 0; i < aLi.length; i++) aLi[i].className = i < iScore ? "on" : "";
				}

				//卖家的发货速度打分			
				var oStar1 = document.getElementById("star1");
				var aLi1 = oStar1.getElementsByTagName("li");
				var oUl1 = oStar1.getElementsByTagName("ul")[0];
				var oSpan1 = oStar1.getElementsByTagName("span")[1];
				var oP1 = oStar1.getElementsByTagName("p")[0];
				var i1 = iScore1 = iStar1 = 0;
				var aMsg1 = [
					"很不满意|差得太离谱，与卖家描述的严重不符，非常不满",
					"不满意|部分有破损，与卖家描述的不符，不满意",
					"一般|质量一般，没有卖家描述的那么好",
					"满意|质量不错，与卖家描述的基本一致，还是挺满意的",
					"非常满意|质量非常好，与卖家描述的完全一致，非常满意"
				]
				for(i = 1; i <= aLi1.length; i++) {
					aLi1[i - 1].index = i;
					//鼠标移过显示分数
					aLi1[i - 1].onmouseover = function() {
						fnPoint1(this.index);
						//浮动层显示
						oP1.style.display = "block";
						//计算浮动层位置
						oP1.style.left = oUl1.offsetLeft + this.index * this.offsetWidth - 104 + "px";
						//匹配浮动层文字内容
						oP1.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg1[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg1[this.index - 1].match(/\|(.+)/)[1]
					};
					//鼠标离开后恢复上次评分
					aLi1[i - 1].onmouseout = function() {
						fnPoint1();
						//关闭浮动层
						oP1.style.display = "none"
					};
					//点击后进行评分处理
					aLi1[i - 1].onclick = function() {
						iStar1 = this.index;
						oP1.style.display = "none";
						oSpan1.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg1[this.index - 1].match(/\|(.+)/)[1] + ")"
					}
				}
				//评分处理
				function fnPoint1(iArg1) {
					//分数赋值
					iScore1 = iArg1 || iStar1;
					for(i = 0; i < aLi1.length; i++) aLi1[i].className = i < iScore1 ? "on" : "";
				}
				
				//卖家的发货速度打分         
                var oStar2 = document.getElementById("star2");
                var aLi2 = oStar2.getElementsByTagName("li");
                var oUl2 = oStar2.getElementsByTagName("ul")[0];
                var oSpan2 = oStar2.getElementsByTagName("span")[1];
                var oP2 = oStar2.getElementsByTagName("p")[0];
                var i2 = iScore2 = iStar2 = 0;
                var aMsg2 = [
                    "很不满意|差得太离谱，与卖家描述的严重不符，非常不满",
                    "不满意|部分有破损，与卖家描述的不符，不满意",
                    "一般|质量一般，没有卖家描述的那么好",
                    "满意|质量不错，与卖家描述的基本一致，还是挺满意的",
                    "非常满意|质量非常好，与卖家描述的完全一致，非常满意"
                ]
                for(i = 1; i <= aLi2.length; i++) {
                    aLi2[i - 1].index = i;
                    //鼠标移过显示分数
                    aLi2[i - 1].onmouseover = function() {
                        fnPoint2(this.index);
                        //浮动层显示
                        oP2.style.display = "block";
                        //计算浮动层位置
                        oP2.style.left = oUl1.offsetLeft + this.index * this.offsetWidth - 104 + "px";
                        //匹配浮动层文字内容
                        oP2.innerHTML = "<em><b>" + this.index + "</b> 分 " + aMsg2[this.index - 1].match(/(.+)\|/)[1] + "</em>" + aMsg2[this.index - 1].match(/\|(.+)/)[1]
                    };
                    //鼠标离开后恢复上次评分
                    aLi2[i - 1].onmouseout = function() {
                        fnPoint2();
                        //关闭浮动层
                        oP2.style.display = "none"
                    };
                    //点击后进行评分处理
                    aLi2[i - 1].onclick = function() {
                        iStar2 = this.index;
                        oP2.style.display = "none";
                        oSpan2.innerHTML = "<strong>" + (this.index) + " 分</strong> (" + aMsg2[this.index - 1].match(/\|(.+)/)[1] + ")"
                    }
                }
                //评分处理
                function fnPoint2(iArg2) {
                    //分数赋值
                    iScore2 = iArg2 || iStar2;
                    for(i = 0; i < aLi2.length; i++) aLi2[i].className = i < iScore2 ? "on" : "";
                }
			};
		</script>
		<script src="/huyugo/main/kingediter/kindeditor-all-min.js"></script>
		<script src="/huyugo/main/js/laydate.js"></script>
		 <script>
		     var editor = null;
             KindEditor.ready(function(K) {
                 editor = K.create('textarea[name="content"]', {
                     resizeType : 1,
                     allowPreviewEmoticons : false,
                     items : [
                     'source','undo','redo','|','formatblock','fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline','strikethrough','removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist','insertunorderedlist','insertorderedlist','insertunorderedlist', '|', 'emoticons', 'image', 'link','table','hr','preview'],
                     });
                });
             
            $(".pingLBTnsdd").click(function(){
                var goods_grade = $("#star").find("strong").html();
                if(typeof(goods_grade) == "undefined"){
                	alert("请为商品评价打星！");
                	return false;
                }
                goods_grade = goods_grade.substring(0,1);
                var service_grade = $("#star1").find("strong").html();
                if(typeof(service_grade) == "undefined"){
                	alert("请为商家服务打星！");
                	return false;
                }
                service_grade = service_grade.substring(0,1);
                var deliver_grade = $("#star2").find("strong").html();
                if(typeof(deliver_grade) == "undefined"){
                	alert("请为快递打星！");
                	return false;
                }
                deliver_grade = deliver_grade.substring(0,1);
                var comment = editor.html();
                $("input[name=comment]").val(comment);
                $("input[name=goods_grade]").val(goods_grade);
                $("input[name=service_grade]").val(service_grade);
                $("input[name=deliver_grade]").val(deliver_grade);
                $("#comment").submit();
                return false;
               // window.location.href="/huyugo/goodsComment/addGoodsComment.do?";
            });
             
        </script>
	</body>

</html>