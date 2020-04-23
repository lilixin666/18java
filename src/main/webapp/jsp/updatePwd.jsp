<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<base href="${pageContext.request.contextPath }/">
<meta content="application/xhtml+xml;charset=UTF-8"
	http-equiv="Content-Type">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<title>用户激活</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<link rel="stylesheet" href="css/updatePwd.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>



<script type="text/javascript">
        var clock = '';
        var nums = 60;
        var btn;

        function doLoop() {
			nums--;
			btn = $("#PhoneVercodeBtn");
			if (nums > 0) {
				$("#PhoneVercodeBtn").text(nums+ '秒后重新获取');
			} else {
				clearInterval(clock); //清除js定时器
				$("#PhoneVercodeBtn").attr("disabled",false);
				$("#PhoneVercodeBtn").html('重新获取验证码');
				nums = 60; //重置 时间
			}
	   }
        
        $(function() {
           //实现手机验证码的发送
           $("#PhoneVercodeBtn").click(function(){
        	 var regex = /^1(3|5|6|7|8)\d{9}$/;
             var telephone = $("#telephone").val();
   			 if(!regex.test(telephone)){
      				alert("手机输入有误!");
      				return false;
   			 }
   			 
   			 //发送手机验证码
   			 $.post("${pageContext.request.contextPath}/userServlet?method=sendPhoneCode&telephone="+telephone);
   			 
   			 $(this).attr("disabled",true);//按钮禁用
   			 $(this).html(nums + '秒后重新获取');
   			 clock = setInterval("doLoop()", 1000); //一秒执行一次

   			 return false;
           });
           
           //验证
           $("#phoneVercode").blur(function(){
        	   var phoneCode = $(this).val();
        	   var url = "${pageContext.request.contextPath}/userServlet";
        	   var data = {"method":"checkPhoneCode","phoneCode":phoneCode};
        	   $.post(url,data,function(result){
        		   if(!result){
        			   alert("验证码不正确！");
        			   $("#submit").attr("disabled",true);//按钮禁用
        		   }else{
        			   $("#submit").attr("disabled",false);//按钮禁用
        		   }
        	   },"json");
           });
           
        }); 
        </script>

<style>
.error {
	color: red;
}
</style>
</head>

<body>

	<!-- 引入头文件 -->
	<%@ include file="header.jsp"%>


	<!--signup-box-->
	<div class="container" >
		<div class="signup-box">
			<h4>密码修改</h4>
			<form action="${pageContext.request.contextPath }/userServlet" method="post" class="form-horizontal" id="signupForm">
				
				<input type="hidden" name="method" value="changePwd">
				
				<div class="form-group">
					<label for="telephone" class="col-md-2 control-label">手机号：</label>
					<div class="col-md-6">
						<input type="text" id="telephone" name="telephone"
							class="form-control" placeholder="输入手机号码" />
					</div>

				</div>
				
				<div class="form-group">
					<label for="phoneVercode" class="col-md-2 control-label">手机验证码：</label>
					<div class="col-md-4">
						<input type="text" id="phoneVercode" name="phoneCode"
							class="form-control" placeholder="输入手机验证码" />
					</div>
					<div class="col-md-2">
						<button id="PhoneVercodeBtn" class="btn btn-info btn-getcode">获取验证码</button>
					</div>
				</div>
				<div class="form-group">
					<label for="password" class="col-md-2 control-label">新密码：</label>
					<div class="col-md-4">
						<input type="password" id="password" name="newPwd" class="form-control" placeholder="输入新密码" />
					</div>
					
				</div>
				
				
				<div class="form-group">
					<div class="col-md-offset-2 col-md-4">
						<!-- <a class="btn btn-danger btn-block btn-signup" href="process-step2.html" target="_blank" >注&nbsp;&nbsp;&nbsp;&nbsp;册</a> -->
						<input class="btn btn-danger btn-block btn-signup" id="submit" type="submit"
							value="保&nbsp;&nbsp;&nbsp;&nbsp;存" />
					</div>
				</div>
			</form>
		</div>
	</div>
	<!--foot-->
	<footer>
		<section class="copyright size12">
			<div class="container">
				<p class="text-center">地址：北京市昌平区建材城西路金燕龙办公楼一层 邮编：100096
					电话：400-618-4000 传真：010-82935100</p>
				<p class="text-center">京ICP备08001421号京公网安备110108007702</p>
			</div>
		</section>
	</footer>

</body>

</html>