

(function($) {
	var dateArray = [];
	var isSign = false;
	function getSign(){
		$.ajax({
			url:"/huyugo/sign/showSign.do",
			data:{
			      
			},  
			type:'post',  
			cache:false,  
			dataType:'html',
			async: false,
			success:function(data) {
				var obj = JSON.parse(data);
				if(obj.status != null && obj.status != ""){
					if(obj.status != "notlogin"){
						//alert("-------");
						dateArray = obj.sign.split(",");
						if(obj.status == "noSign"){
							//没有签到，显示可签到按钮
						}else if(obj.status == "isSign"){
							//已签到，签到按钮变灰
							isSign = true;
						}
						
					}else{
						//dateArray = [];
					}
				}
		    }
		});
		return dateArray;
	}
	
	var dateArray = getSign();
				var Checkin = function(ele, options) {
					this.ele = ele;
					this.opt = options;
					this.defaults = {
						width: 160,
						height: 'auto',
						background: '#f90',
						radius: 10,
						color: '#000',
						padding: 10,
						dateArray: dateArray // 假设已签到的天数+1
					};
					this.obj = $.extend({}, this.defaults, this.opt);
				}
				
				
				var isChecked = false;
				var arrnuber =new Array();//已签到的天数
				Checkin.prototype.events = function() {
					var _self = this.ele;
					var $li = _self.find(".calendarList").find("li");
					/*$li.on('click', function(event) {
									event.preventDefault();
									 Act on the event 
									if($(this).hasClass('able-qiandao')) {
										$(this).addClass('checked');
										modal(_self);
										isChecked = true;
									}
						
					});*/
					var checkBtn = _self.find(".checkBtn");
					checkBtn.click(function(event) {
						$.ajax({
							url:"/huyugo/sign/addSign.do",
							data:{
							      
							},  
							type:'post',  
							cache:false,  
							dataType:'html',  
							success:function(data) {
								if(data == "ok"){
									modal(_self);
									_self.find('.able-qiandao').addClass('checked');
									 days=_self.find('.able-qiandao').text();
									 arrnuber.push(days.split(","));					
									isChecked = true;
									console.log(arrnuber);
								}
						    }
						});
						
					});
					
//                  this.obj.dateArray.push(arrnuber);
//                  console.log( this.obj.dateArray)
				}													
					
				Checkin.prototype.init = function() {
					var _self = this.ele,
						html = '',
						myDate = new Date(),
						year = myDate.getFullYear(),
						month = myDate.getMonth(),
						day = myDate.getDate(),
						weekText = ['日', '一', '二', '三', '四', '五', '六'];
					_self.css({
						width: this.obj.width + 'px',
						height: this.obj.height,
						background: this.obj.background,
						borderRadius: this.obj.radius,
						color: this.obj.color,
						padding: this.obj.padding
					}).append("<div class='title'><p>" + year + '年' + (month + 1) + '月' + day + '日' + "</p><a class=\'checkBtn\' href=\"javascript:;\">签到</a></div>");
					$("<ul class='week clearfix'></ul><ul class='calendarList clearfix'></ul>").appendTo(_self);
					for(var i = 0; i < 7; i++) {
						_self.find(".week").append("<li>" + weekText[i] + "</li>")
					};
					for(var i = 0; i < 42; i++) {
						html += "<li></li>"
					};
					_self.find(".calendarList").append(html);
					var $li = _self.find(".calendarList").find("li");
					_self.find(".week li").css({
						width: (_self.width() / 7) + 'px',
						height: 20 + 'px',
						borderRight: '1px solid #f90',
						boxSizing: 'border-box',
						background: '#b25d06'
					});
					$li.css({
						width: (_self.width() / 7) + 'px',
						height: 20 + 'px',
						borderRight: '1px solid #f90',
						borderBottom: '1px solid #f90',
						boxSizing: 'border-box',
						color: "#b25d06"
					});
					_self.find(".calendarList").find("li:nth-child(7n)").css('borderRight', 'none');
					_self.find(".week li:nth-child(7n)").css('borderRight', 'none');
					var monthFirst = new Date(year, month, 1).getDay();
					var d = new Date(year, (month + 1), 0)
					var totalDay = d.getDate(); //获取当前月的天数
					for(var i = 0; i < totalDay; i++) {
						$li.eq(i + monthFirst).html(i + 1);
						$li.eq(i + monthFirst).addClass('data' + (i + 1));

						if(isArray(this.obj.dateArray)) {
							for(var j = 0; j < this.obj.dateArray.length; j++) {
								if(i == this.obj.dateArray[j]) {
									// 假设已经签到的
									$li.eq(i + monthFirst -1).addClass('checked');
								}
							}
						}
					}
					_self.find($(".data" + day)).addClass('able-qiandao');
					if(isSign){
						modal2(_self);
					}
				}
				var modal = function(e) {
					var mask = e.parents().find(".mask");
					var close = e.parents().find(".closeBtn");
					if(mask && !isChecked) {
						mask.addClass('trf');
					} else {
						return;
					};
					close.click(function(event) {
						event.preventDefault();
						mask.removeClass('trf')
					});
					e.parents().find('.checkBtn').text("已签到");
				}
				var modal2 = function(e) {
					if(isSign){
						e.parents().find('.checkBtn').text("已签到");
					}
				}
				$.fn.Checkin = function(options) {
					var checkin = new Checkin(this, options);
					var obj = [checkin.init(), checkin.events()]
					return obj
				}
				var isArray = function(arg) {
					return Object.prototype.toString.call(arg) === '[object Array]';
				};
			})(jQuery);