// JavaScript Document



/*改变所购商品的数量*/
function changeNum(numId,flag,goodsId,realprice){/*numId表示对应商品数量的文本框ID，flag表示是增加还是减少商品数量*/
	var numId=document.getElementById(numId);
	//var count = numId.value;
	if(flag=="minus"){/*减少商品数量*/
		if(numId.value<=1){
			alert("宝贝数量必须是大于0");
			return false;
			}
		else{
			numId.value=parseInt(numId.value)-1;
			productCount4();
			//productCount2()
			}
	}
	else{/*flag为add，增加商品数量*/
		if(realprice!=0){
            numId.value=parseInt(numId.value)+1;
            productCount4();
            //productCount2()
            console.log(11212)
		}

	}
	$.ajax({
		url:"/huyugo/car/updateCar.do",
		data:{
		      id:goodsId,
		      count:numId.value
		},  
		type:'post',  
		cache:false,  
		dataType:'html',  
		success:function(data) {
			
	    }
	});
}
	
/*自动计算商品的总金额、总共节省的金额和积分*/
function productCount(){
	var total=0;      //商品金额总计
	var integral=0;   //可获商品积分
	
	var point;      //每一行商品的单品积分
	var price;     //每一行商品的单价
	var number;    //每一行商品的数量
	var subtotal;  //每一行商品的小计

     /*访问ID为shopping表格中所有的行数*/
	var myTableTr=document.getElementById("shopping").getElementsByTagName("tr"); 
	if(myTableTr.length>0){
		if($(this).find("input[type=checkbox]").is(":checked")){
	for(var i=1;i<myTableTr.length;i++){/*从1开始，第一行的标题不计算*/
	    if(myTableTr[i].getElementsByTagName("td").length>2){ //最后一行不计算
		point=myTableTr[i].getElementsByTagName("td")[4].innerHTML; 
		price=myTableTr[i].getElementsByTagName("td")[5].innerHTML;
		number=myTableTr[i].getElementsByTagName("td")[6].getElementsByTagName("input")[0].value;
		integral+=point*number;
		total+=price*number;
		myTableTr[i].getElementsByTagName("td")[7].innerHTML=price*number;
		}
	}
	/*document.getElementById("total").innerHTML=total;*/
	document.getElementById("integral").innerHTML=integral;
		}
	}
}
/*自动计算商品的总金额、总共节省的金额和积分*/
function productCount2(){
	var total=0;      //商品金额总计
	var integral=0;   //可获商品积分
	
	var point=0;      //每一行商品的单品积分
	var price=0;     //每一行商品的单价
	var number=0;    //每一行商品的数量
	var subtotal=0;  //每一行商品的小计

     /*访问ID为shopping表格中所有的行数*/
	var myTableTr=document.getElementById("shopping").getElementsByTagName("tr"); 
	if(myTableTr.length>0){
	for(var i=1;i<myTableTr.length;i++){/*从1开始，第一行的标题不计算*/
	    if(myTableTr[i].getElementsByTagName("td").length>2){ //最后一行不计算
		point=myTableTr[i].getElementsByTagName("td")[4].innerHTML; 
		price=myTableTr[i].getElementsByTagName("td")[5].innerHTML;
		number=myTableTr[i].getElementsByTagName("td")[6].getElementsByTagName("input")[0].value;
		integral+=point*number;
		total+=price*number;
		myTableTr[i].getElementsByTagName("td")[7].innerHTML=price*number;
		}
	}
	document.getElementById("total").innerHTML=total;
	document.getElementById("integral").innerHTML=integral;
	
	}
}
/*自动计算商品的总金额、总共节省的金额和积分*/
function productCount3(){    
	var integral=0;   //可获商品积分
	var point=0;      //每一行商品的单品积分   
	var jiage=0;//每一行商品的单价
	var shulang=0;//每一行商品的数量
	var mm=0;//商品金额总计
	var mlll=0;//每一行商品的小计
	var allnum;
	//循环每一个选中商品，并计算价格
	$("#shopping tr").each(function() {
		if($(this).find("input[type=checkbox]").is(":checked")) {
			point = $(this).find("td.cart_td_41").text();
			jiage=$(this).find("td.cart_td_5").text();
			shulang =$(this).find("td.cart_td_6 input").val();
			mlll +=jiage*shulang;
			 mm +=jiage*shulang;
		     
			//total = parseFloat($(this).find("td.cart_td_5").text()) *parseFloat($(this).find("td.cart_td_6 input").val().replace(/\D/gi,''));
			//price = price + total;cart_td_7
			integral +=point *shulang;
			console.log(integral);
			$(this).find("td.cart_td_7").text(mlll)
			document.getElementById("total").innerHTML =mm;
			document.getElementById("integral").innerHTML=integral;
		}
	})
	 
}
/*自动计算商品的总金额、总共节省的金额和积分*/
function productCount4(){    
	var integral=0;   //可获商品积分
	var point=0;      //每一行商品的单品积分   
	var jiage=0;//每一行商品的单价
	var shulang=0;//每一行商品的数量
	var mm=0;//商品金额总计
	var mlll=0;//每一行商品的小计
	var allnum;
	//循环每一个选中商品，并计算价格
	$("#shopping tr").each(function() {
		if($(this).find("input[type=checkbox]").is(":checked")) {
			point = $(this).find("td.cart_td_41").text();
			jiage=$(this).find("td.cart_td_5").text();
			shulang =$(this).find("td.cart_td_6 input").val();
			if(point !=undefined && jiage !=undefined && shulang !=undefined){
				
			
			mlll =jiage*shulang;
			 mm +=jiage*shulang;
			integral +=point *shulang;
			console.log(point);
			console.log(jiage);
			console.log(shulang);
			}
			$(this).find("td.cart_td_7").text(mlll)
			document.getElementById("total").innerHTML =mm;
			document.getElementById("integral").innerHTML=integral;
		}
	})
	 
}
//window.onload=productCount;
/*删除单行商品*/
function deleteRow(rowId,goodsId){
	$.ajax({
		url:"/huyugo/car/deleteCar.do",
		data:{
		      id:goodsId
		},  
		type:'post',  
		cache:false,  
		dataType:'html',  
		success:function(data) {
			if(data == "ok"){
				alert("删除成功");
			}else{
				alert("删除失败");
				return;
			}
	    }
	});
	var Index=document.getElementById(rowId).rowIndex; //获取当前行的索引号
	document.getElementById("shopping").deleteRow(Index);
	productCount();
}
$(document).ready(function(){
	//默认 tr 下面的 首个td input为checkbox选中
	$("table tr").each(function(index,obj){
		$(obj).find("td").eq(0).find("input[type=checkbox]").attr("checked","checked");
		if($(obj).find("td").eq(0).find("input[type=checkbox]").attr("checked","checked")){
			productCount2()
		}
		
	});
	
	
});

function quanxuan(obj){
	var data = $(obj).attr("data");
	if(data == "0"){
		$(obj).attr("data","1");
		//取消全选
		$("table tr").each(function(index,obj){
			$(obj).find("td").eq(0).find("input").removeAttr("checked");
		});
		document.getElementById("total").innerHTML=0;
        document.getElementById("integral").innerHTML=0;
        //productCount()
	}else if(data == "1"){
		$(obj).attr("data","0");
		//添加全选
		$("table tr").each(function(index,obj){
			$(obj).find("td").eq(0).find("input[type=checkbox]").prop("checked",true);
			productCount2()
		});
	
		
	}
	
}
var index=$("input[name='goods']").index();
$("input[name='goods']").click(function () {
	
    $(this).prop("checked", this.checked);
    if($(this).prop("checked", this.checked)){
    	productCount4();
    }
 
   });
function carSubmit(i){
	/**
	 * 获取选中的复选框的goods id,再提交生成订单
	 */
	var idStr = "";
	var trObj = $("table tr");
	var trLength = trObj.length;
	if(trLength > 3){
		$(trObj).each(function(index,obj){
			if(index > 1 && index < trLength-1){
				var checked = $(obj).find("td").eq(0).find("input").is(":checked");
				if($(obj).find("td").eq(0).find("input").is(":checked")){
					var id = $(obj).find("td").eq(0).find("input").attr("data-id");
					idStr += id+",";
				}
			}
		});
	}
	if(idStr != ""){
		idStr = idStr.substring(0,idStr.length-1);
	}
	console.log(idStr);
	if(idStr == ""){
		alert("请选择您要支付的商品！");
		return false;
	}
	
	if(i > 1){
		window.location.href = "/huyugo/order/showOrder.do?status=add&ids="+idStr;
	}
}