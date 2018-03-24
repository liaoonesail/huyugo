//头部导航
var ullia = document.getElementsByClassName("ullia");

startnav();
for(var i = 0; i < ullia.length; i++) {
	ullia[i].index = i;
	ullia[i].onclick = function() {
		for(var i = 0; i < ullia.length; i++) {
			ullia[num1].style.cssText = 'background:none;';
		}
		num1 = this.index;
		start();
	}
}

function startnav() {
	if(num1 > -1){
		ullia[num1].style.cssText = 'background: #6213ac;';
	}
}
//公告
var navigationTitle = document.getElementsByClassName("navigationTitle")[0];
var navigationTitleHover = navigationTitle.getElementsByClassName("navigationTitleHover");
var navigationTitleList = document.getElementsByClassName("navigationTitleList");
var num = 0;
start();
for(var i = 0; i < navigationTitleHover.length; i++) {
	navigationTitleHover[i].index = i;
	navigationTitleHover[i].onmouseover = function() {
		for(var i = 0; i < navigationTitleList.length; i++) {
			navigationTitleList[i].style.display = 'none';
			navigationTitleHover[num].style.cssText = 'color: #fff;';
		}
		num = this.index;
		start();
	}
}

function start() {
	navigationTitleList[num].style.display = 'block';
	navigationTitleHover[num].style.cssText = 'color:#ff4040;';
}