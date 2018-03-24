<%@page import="com.huyu.entity.Userinfo"%>
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
		<link rel="stylesheet" href="/huyugo/memberCenter/css/personal.css" />
		<link rel="stylesheet" href="/huyugo/css/templet2.css" />
		<script type="text/javascript" src="/huyugo/js/jquery-1.10.2.js"></script>
		<script src="/huyugo/js/jquery.form.js"></script>
		<script src="/huyugo/js/jquery.Validform.min.js"></script>
		<script>
		 //选择图片之后的上传函数
		    handleUploadImage = function() {
		            var options = {
		                url: "/huyugo/files/uploadImage.do",
		                type: 'post',
		                beforeSubmit: function (formData, jqForm, options) {
		                    var queryString = $.param(formData);
		                },
		                success: function (data) {
		
		                    if (data) {
		                        $('#pic').attr('src', data);
		                        $("#photo").val(data);
		                    }
		                }
		            };
		
		            $('#mForm').ajaxSubmit(options);
		    };
		</script>
	</head>

	<body>
	<%
	Userinfo ui = (Userinfo)request.getAttribute("userInfo");
	 %>
	 
	 <script type="text/javascript">
	     
	     /*文档加载*/
	     $(document).ready(function(){
	    	 
	    	 changeFormUrl();
	    	 
	     });
	 
	     /**
	      * 完善个人信息加积分 改变form提交路径
	      */
	     function changeFormUrl(){
	    	 if('<%=ui.getBankcard()%>'+"" != "null"&& '<%=ui.getBankname()%>'+""!="null"&&'<%=ui.getBankname()%>'+""!="null"&&'<%=ui.getIdcard()%>'+""!="null"){
	    		 $("#mForm").attr("action","/huyugo/person/updatePersonAgain.do");
	    		 $("input[name=bankcard]").attr("readonly","readonly");
	    		 $("input[name=bankname]").attr("readonly","readonly");
	    		 $("input[name=realName]").attr("readonly","readonly");
	    		 $("input[name=idCard]").attr("readonly","readonly");
	    	 }
	     }
	     
	     /**
	      * 提示用户信息
	      */
	     function yanzheng(){
	    	 if(confirm("真实姓名和银行卡信息提交之后不可修改,是否提交？")){
	    		 if($("input[name=bankcard]").val()==""&&$("input[name=bankname]").val()==""&&$("input[name=realName]").val()==""){
	    			 alert("请完善真实姓名及银行卡信息！！！");
	    			 return false;
	    		 }
	    	 }
	     }
	     
	 </script>
	 
		<div class="publicBox2">

			<div class="right1Title clearfix">
				<img src="/huyugo/memberCenter/jiantou/14.png" />
				<span>个人中心</span>
			</div>
			<style>
			     .touAdnList{
			         position: relative;
			     }
			     .touAdnList input {
				        outline: none;
				        padding: 4px 10px;
				        position: absolute;
				        top: 0;
				        left: 8%;
				}
			</style>
			<div class="personalBox clearfix">
				<ul id="xdmL" class="personalBoxNav">
					<li>个人资料 </li>
					<li>密码修改</li>
				</ul>
				<div class="clearfix"></div>
				<div class="personalBoxTouxiaNg">
					<form method="post" action="/huyugo/person/updatePerson.do" id="mForm" onsubmit="return yanzheng()" enctype="multipart/form-data">
						<div class="touAdnList clearfix">
							<div class="pull-left">当前头像：</div>
							<div class="pull-left">
								<% 
							    if(ui.getHead_picture()!=null && !"".equals(ui.getHead_picture())){
                                %>
							    	<img title="点击上传头像" id="pic" src="<%=ui.getHead_picture() %>" alt="点击上传头像"/>
							    <% }else{ %>
							      <img title="点击上传头像" id="pic" src="/huyugo/img/commodity/b.png" alt="点击上传头像"/>
							      <%} %>
								
								<input id="upload" name="images" accept="image/*" type="file" style="display: none" />
							</div>
						</div>
						<input type="hidden" name="photo" id="photo" value="<%if(ui.getHead_picture() != null){ %><%=ui.getHead_picture() %><%} %>" />
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">昵称：</div>
							<input type="text" class="pull-left" name="nickName" id="nickName" value="<%if(ui.getNickname() != null){ %><%=ui.getNickname() %><%} %>" />
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">邮箱：</div>
							<input type="text" class="pull-left" id="email" name="email" value="<%if(ui.getEmail() != null){ %><%=ui.getEmail() %><%} %>" />
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">微信：</div>
							<input type="text" class="pull-left" id="weixin" name="weixin" value="<%if(ui.getWechat() != null){ %><%=ui.getWechat() %><%} %>" />
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">Q Q：</div>
							<input type="text" class="pull-left" id="qq" name="qq" value="<%if(ui.getQq() != null){ %><%=ui.getQq() %><%} %>" />
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">银行卡：</div>
							<input type="text" class="pull-left" name="bankcard" id="bankcard" value="<%if(ui.getBankcard() != null){ %><%=ui.getBankcard() %><%} %>" />
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">开户行：</div>
							<input type="text" class="pull-left" name="bankname" id="bankname" value="<%if(ui.getBankname() != null){ %><%=ui.getBankname() %><%} %>" />
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">真实姓名：</div>
							<input type="text" class="pull-left" name="realName" id="realName" value="<%if(ui.getRealname() != null){ %><%=ui.getRealname() %><%} %>" />
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">身份证：</div>
							<input type="text" class="pull-left" name="idCard" name="idCard" value="<%if(ui.getIdcard() != null){ %><%=ui.getIdcard() %><%} %>" />
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">手机号码：</div>
							<input type="text" name="phone" id="phone" value="<%if(ui.getPhone() != null){ %><%=ui.getPhone() %><%} %>" />
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">性别：</div>
							<label style="margin-left: 20px;"><input type="radio" name="sex" style="margin-right: 10px;line-height: 19px;" value="男" <%if(ui.getSex() != null && ui.getSex().equals("男")){ %>checked="checked"<%} %> />男</label>
							<label style="margin-left: 20px;padding: 0 10px;"><input type="radio" style="margin-left: 50px;line-height: 19px;" name="sex" value="女" <%if(ui.getSex() != null && ui.getSex().equals("女")){ %>checked="checked"<%} %> />女</label>
						</div>
						<div class="touAdnList clearfix">
							<div class="pull-left dfhg">生日：</div>
							<select class="sel_year" rel="<%if(ui.getBirthday() != null && ui.getBirthday().indexOf("-") > -1){ %><%=ui.getBirthday().split("-")[0] %><%}else{ %>2017<%} %>" name="brithyear"></select>年
							<select class="sel_month" rel="<%if(ui.getBirthday() != null && ui.getBirthday().indexOf("-") > -1){ %><%=ui.getBirthday().split("-")[1] %><%}else{ %>01<%} %>" name="brithmonth"></select>月
							<select class="sel_day" rel="<%if(ui.getBirthday() != null && ui.getBirthday().indexOf("-") > -1){ %><%=ui.getBirthday().split("-")[2] %><%}else{ %>01<%} %>" name="brithday"></select>日
						</div>
						<div class="login_Membut">
							<input class="Okbtsdg" type="submit" name="submit" class="login_Email_but" value="确认修改" style="outline: none;border: none;" />
						</div>
					</form>
				</div>

				<div class="personalBoxTouxiaNg login_ConInput">
					<div><font color="red">${success }${error }</font></div>
					<div class="login_ConInput login_Ptxtf14">
						<form class="registerform" method="post" action="/huyugo/person/showPersonal.do">
							<dl class="touAdnList clearfix">
								<dt class="pull-left dfhg2">原密码：</dt>
								<dd class="pull-left"><input name="userpassword" plugin="passwordStrength" datatype="*6-20" nullmsg="密码不能为空！" errormsg="密码范围在6~20位之间！" type="password" class="login_input_text" maxlength="20"></dd>
								<dd class="pull-left" style="margin-left: 190px;">
									<div class="Validform_checktip">请输入原密码</div>
								</dd>
							</dl>
							<dl class="touAdnList clearfix">
								<dt class="pull-left dfhg2">新密码：</dt>
								<dd class="pull-left"><input name="userpassword1" plugin="passwordStrength" datatype="*6-20" nullmsg="密码不能为空！" errormsg="密码范围在6~20位之间！" type="password" class="login_input_text" maxlength="20"></dd>
								<dd class="pull-left" style="margin-left: 190px;">
									<div class="Validform_checktip">请输入新密码</div>
								</dd>
							</dl>

							<dl class="touAdnList clearfix">
								<dt class="pull-left">确认密码：</dt>
								<dd class="pull-left"><input name="userpassword2" recheck="userpassword1" sucmsg="" datatype="*6-20" nullmsg="请再输入一次密码！" errormsg="您两次输入的账号密码不一致！" id="NewPassAgain" type="password" class="login_input_text" maxlength="20"></dd>
								<dd class="pull-left" style="margin-left: 190px;">
									<div class="Validform_checktip">请再输入一次密码！</div>
								</dd>
							</dl>
							<input type="hidden" name="status" value="update" />
							<div class="login_Membut">
								<input class="Okbtsdg" type="submit" name="submit" class="login_Email_but" value="确认修改"  style="outline: none;border: none;" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<script src="/huyugo/memberCenter/js/birthday.js"></script>
		<script>
			<%
			String index2 = (String)request.getAttribute("index2");
			if(index2 == null || "".equals(index2)){
				index2 = "0";
			}
			%>
			//
			var ListNavServicew = document.getElementById("xdmL")
			var odivs = ListNavServicew.getElementsByTagName("li");
			var newlist1 = document.getElementsByClassName("personalBoxTouxiaNg");
			odivs[<%=index2 %>].className = 'Mmok';
			newlist1[<%=index2 %>].style.display = "block";
			for(var a = 0; a < odivs.length; a++) {
				odivs[a].index = a;
				odivs[a].onclick = function() {
					for(var b = 0; b < newlist1.length; b++) {
						odivs[b].className = ' ';
						newlist1[b].style.display = 'none';
					}
					this.className = 'Mmok';
					newlist1[this.index].style.display = "block";
				}
			};
			//
			$("#pic").click(function() {
				$("#upload").click(); //隐藏了input:file样式后，点击头像就可以本地上传
				/*  $("#upload").on("change", function() {
					var objUrl = getObjectURL(this.files[0]); //获取图片的路径，该路径不是图片在本地的路径
					if(objUrl) {
						$("#pic").attr("src", objUrl); //将图片路径存入src中，显示出图片

					}
				}); */
				$("#upload").on("change", handleUploadImage);

			});

			//建立一個可存取到該file的url
			function getObjectURL(file) {
				var url = null;
				if(window.createObjectURL != undefined) { // basic
					url = window.createObjectURL(file);
				} else if(window.URL != undefined) { // mozilla(firefox)
					url = window.URL.createObjectURL(file);
				} else if(window.webkitURL != undefined) { // webkit or chrome
					url = window.webkitURL.createObjectURL(file);
				}
				
				return url;
				
			}
			//
			$(function() {
				$.ms_DatePicker({
					YearSelector: ".sel_year",
					MonthSelector: ".sel_month",
					DaySelector: ".sel_day"
				});
				$.ms_DatePicker();
			});
			//
			$(function() {
				var demo = $(".registerform").Validform({
					tiptype: 2,
					usePlugin: {
						passwordstrength: {
							minLen: 6,
							maxLen: 20
						}
					}
				});
			});
			
		</script>
	</body>

</html>