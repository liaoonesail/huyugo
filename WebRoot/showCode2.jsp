<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>

<head>
    <link href="/huyugo/img/f.png" rel="shortcut icon" type="image/x-icon"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="keywords" content="互余，互余网、互余购、万州商城，直购商城，休闲娱乐" />
    <meta name="description" content="互余购-综合网购商城、要网购就来互余购，正品低价、品质保障、新型的网购模式，购物模式多样化等" />
    <title>互余购-综合网购商城</title>
    <link rel="stylesheet" type="text/css" href="/huyugo/css/Comm.css" />
    <link rel="stylesheet" href="/huyugo/css/bass.css" />
    <link rel="stylesheet" type="text/css" href="/huyugo/css/CartList.css" />
    <link rel="stylesheet" href="/huyugo/css/myCart.css" />
    <link rel="stylesheet" href="/huyugo/css/footer.css" />
    <script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>

</head>

<body>
<%
    int orderId = 0;
    String orderIdstr = request.getAttribute("orderId")+"";
    String type=request.getAttribute("type")+"";
    if(orderIdstr != null && !"".equals(orderIdstr)){
        orderId = Integer.parseInt(orderIdstr);
    }
%>
<div class="logo">
    <div class="float">
				<span class="logo_pic">
					<a href="/huyugo/index.do" >
			          <img src="/huyugo/img/logo/logo.jpg" width="225" height="65"/>
		            </a>
				</span>
        <span class="tel">
					<a href="/huyugo/index.do" style="color:#999;">返回首页</a>
				</span>
    </div>
</div>


<div class="shop_payment">
    <ul class="payment">
        <li class="first_step">第一步：提交订单</li>
        <li class="arrow_2"></li>
        <li class="third_step">第二步：网银支付 </li>

        <li class="arrow_1"></li>
        <li class="secend_step orange_Tech">第三步：微信支付</li>
        <li class="arrow_3"></li>
        <li class="fourth_step">第四步：购买成功</li>

    </ul>
</div>
<div class="publicBox" style="position: relative;">
    <div class="payoK clearfix">
        <div class="payok_ko">
            <p>支付完成前，请勿关闭页面！</p>
            <img id="img" />
        </div>
    </div>
    <a href="/huyugo/order/paySuccess.do?id=<%=orderIdstr%>&type=<%=type%>" style="position: absolute;top: 67%;left: 34%;">已完成支付？点击跳转</a>
</div>
<!--底部内容-->
<jsp:include page="footer.jsp"></jsp:include>
<!--底部内容-->
<script type="text/javascript">

    $(function(){
        if(parseInt(""+<%=orderId %>) < 1){
            return;
        }
        $.ajax({
            url:"/huyugo/fazhanOrder/orderPay.do",
            data:{
                orderId:<%=orderId %>
            },
            type:'post',
            cache:false,
            dataType:'html',
            success:function(data) {
                if(data == "notLogin"){
                    alert("请重新登录");
                }else{
                    var url = "/huyugo/wechatpay/pc_pay.do?" + data;
                    var xhr=new XMLHttpRequest();
                    xhr.open("GET",url,true);
                    xhr.responseType="blob";
                    xhr.onload=function(){
                        if(this.status==200){
                            var blob=this.response;
                            //var img=document.createElement("img");
                            //img.onload=function(e){
                            //	window.URL.revokeObjectURL(img.src);
                            //};
                            $("#img").attr("src",window.URL.createObjectURL(blob));
                            //img.src=window.URL.createObjectURL(blob);
                            //$("#img").html(img);
                        }
                    };xhr.send();
                }
            }
        });
    });
</script>
</body>

</html>