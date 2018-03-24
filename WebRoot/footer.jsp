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
		<link rel="stylesheet" href="/huyugo/css/footer.css" />
	</head>
	<body>
			<!--底部内容-->
			<footer>
				<!--正品保障   8天无条件退换货    精美包装   正规发票-->
				<div class="packing">
					<div class="publicBox packingList clearfix">
						<ul class="clearfix">
							<li>
								<img src="/huyugo/img/smallImg/zhengpin1.png" />
								<p>正品保障</p>
								<dd>正品行货 购物无忧</dd>
							</li>
							<li>
								<img src="/huyugo/img/smallImg/zhengpin2.png" />
								<p>7天无条件退换货</p>
								<dd>提供售后无忧保障</dd>
							</li>
							<li>
								<img src="/huyugo/img/smallImg/zhengpin3.png" />
								<p>分享即创业</p>
								<dd>分享即创业、推广变财富</dd>
							</li>
							<li>
								<img src="/huyugo/img/smallImg/zhengpin4.png" />
								<p>天天有惊喜</p>
								<dd>实惠天天有、好礼兑不停</dd>
							</li>
						</ul>
					</div>
				</div>
				<!--正品保障   8天无条件退换货    精美包装   正规发票-->

				<!--帮助中心    售后服务    技术支持    关于商城      关注我们-->
				<div class="helpBox">
					<div class="publicBox helpBoxList clearfix">
						<ul>
							<div class="type5">新手指南</div>
						</ul>
						<ul>
							<div class="type4">互余购保障</div>
						</ul>
						<ul>
							<div class="type2">商品配送</div>
						</ul>
						<ul>
							<div class="type1">会员专区</div>
						</ul>
						<ul>
							<div class="type3">商务合作</div>
						</ul>
						 <script>
								/*新手指南*/
								
								$(document).ready(function(){
									var url = "/huyugo/adminarticle/getByType.do";
									getdata(url,5,".type5");
									getdata(url,4,".type4");
									getdata(url,2,".type2");
									getdata(url,1,".type1");
									getdata(url,3,".type3");
								});
								/* function getgg(url,classes){
									$.post(url,{},function(res){
										var html = "";
										$(res).each(function(index,obj){
											var title = obj.title;
											if(title.length > 13){
												title = title.substring(0,13);
											}
											html += "<li><a href='/huyugo/help/showHelp.do?type=2&status="+obj.id+"'>"+title+"</a></li>";
										});
										$(classes).append(html);
									},"json");
								} */
								
								function getdata(url,type,classes){
									$.post(url,{type:type},function(res){
										var html = "";
										$(res).each(function(index,obj){
											html += "<li><a href='/huyugo/help/showHelp.do?type=1&typeStatus="+obj.id+"'>"+obj.title+"</a></li>";
											if(index == 3){
												return false;
											}
										});
										$(classes).append(html);
									},"json");
								}
								
							</script>
					</div>
				</div>
				<!--帮助中心    售后服务    技术支持    关于商城      关注我们-->
				<!--关于我们  |  购物指南  |  支付方式  |  配送说明  |  售后服务  |  联系方式  |  投诉建议  |  诚聘英才  |  友情链接-->
				<div class="aboutus">
					<div class="publicBox aboutusList clearfix">
						<ul id="link">
							<!-- <li><a href="http://www.huyusc.com">峰氏头条</a>|</li>
							<li><a href="/huyugo/help/showHelp.do?status=1">互余购介绍</a>|</li>
							<li><a href="/huyugo/help/showHelp.do?status=9">隐私声明</a></li> -->
							<script>
							    /*友链*/
							    $.post("/huyugo/link/linkList.do",{},function(res){
							    	//console.log(res);
							    	var prefixHtml = "";
							    	//var suffixHtml = "<li><a href='http://www.huyusc.com'>峰氏头条</a>|</li><li><a href='/huyugo/help/showHelp.do?status=1'>互余购介绍</a>|</li><li><a href='/huyugo/help/showHelp.do?status=9'>隐私声明</a></li>";
							    	var suffixHtml = "";
							    	$(res).each(function(index,obj){
							    		prefixHtml += "<li><a target='_blank' href='http://"+obj.linkurl+"'>"+obj.linkname+"</a>  </li>";
							    	})
							    	$("#link").html(prefixHtml+suffixHtml);
							    },"json");
							</script>
						</ul>
						
					</div>
				</div>
				<!--关于我们  |  购物指南  |  支付方式  |  配送说明  |  售后服务  |  联系方式  |  投诉建议  |  诚聘英才  |  友情链接-->
				<div class="publicBox information">
					<div style="margin:0 auto; padding:0px 0;text-align: center;">						
				 		<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=50010102000464" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;"><img src="" style="float:left;"/><p style="float:left;height:20px;line-height:20px;margin: 0px 0px 0px 5px; color:#939393;"><img src="/huyugo/img/beian.png" style="vertical-align: bottom;"/>重庆互余电子商务有限责任公司 渝公网安备 50010102000464号</p></a>
				 	</div>
					<p>渝ICP备15006691号-2 全国订购及服务热线：023-58300357</p>
				</div>
			</footer>
			<!--底部内容-->
			
	</body>
</html>
<script src="https://s4.cnzz.com/z_stat.php?id=1256923212&web_id=1256923212" language="JavaScript"></script>
