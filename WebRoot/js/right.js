$(function(){
	$("#divRighTool").show(); 
	var wids=($(window).width()-1260)/2-70;
	if(wids>0){
		$("#divRighTool").css("right",wids);
	}else{
		$("#divRighTool").css("right",10);
	}
	$("#btnGotoTop").click(function(){
		$("html,body").animate({scrollTop:0},1500);
	});
	$("#btnFavorite").click(function(){
		var ctrl=(navigator.userAgent.toLowerCase()).indexOf('mac')!=-1?'Command/Cmd': 'CTRL';
		if(document.all){
			window.external.addFavorite('http://www.huyugo.com','互余购-综合网购商城、要网购就来互余购，消费变财富、分享即创业、正品低价、品质保障、享受不一样的购物乐趣');
		}
		else if(window.sidebar){
		   window.sidebar.addPanel('互余购-综合网购商城、要网购就来互余购，消费变财富、分享即创业、正品低价、品质保障、享受不一样的购物乐趣','http://www.huyugo.com', "");
		}else{ 
			alert('您可以通过快捷键' + ctrl + ' + D 加入到收藏夹');
		}
   });
   $("#divRighTool a").hover(		
		function(){
			$(this).addClass("Current");
		},
		function(){
			$(this).removeClass("Current");
		}
	)
});

function getNum(num){
	$("#divRighTool dl dd a em").html(num);
}