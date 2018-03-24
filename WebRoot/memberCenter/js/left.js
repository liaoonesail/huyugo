$(document).ready(function() {
//绑定元素点击事件
$(".menu_list ul li").click(function() {
	//判断对象是显示还是隐藏
	if($(this).children(".div1").is(":hidden")){
		//表示隐藏
		if(!$(this).children(".div1").is(":animated")) {
			$(this).children(".xiala").css({'transform':'rotate(180deg)'}); 
			//如果当前没有进行动画，则添加新动画
			$(this).children(".div1").animate({
					height: 'show'
				}, 1000)
				//siblings遍历div1的元素
				.end().siblings().find(".div1").hide(1000);
		}
	} else {
		//表示显示
		if(!$(this).children(".div1").is(":animated")) {
			$(this).children(".xiala").css({'transform':'rotate(360deg)'});  
			$(this).children(".div1").animate({
					height: 'hide'
				}, 1000)
				.end().siblings().find(".div1").hide(1000);
		}
	}
});

//阻止事件冒泡，子元素不再继承父元素的点击事件
$('.div1').click(function(e){
	e.stopPropagation();
});
});
