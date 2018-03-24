/**
 * 提现相关js
 * @author zs
 * @time 2017年8月4日9:57:37
 */
$(function(){
	$(".head_title ul li").click(function(){
		$(this).addClass("li_bg");
		$(this).siblings("li").removeClass("li_bg");
		$(".head_title ul li").each(function(index,obj){
			if($(obj).hasClass("li_bg")){
				$("ul.content li").eq(index).css("display","block");
			}else{
				$("ul.content li").eq(index).css("display","none");
			}
		});
		return false;
	});
});