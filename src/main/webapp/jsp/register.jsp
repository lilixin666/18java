<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head></head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员注册</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #3164af;
    font-size: 18px;
    font-weight: normal;
    padding: 0 10px;
}
 </style>

	<%--
	  给 用户名控件 添加失去焦点事件   在失去焦点的时候  发ajax请求给后端, 携带用户名,   在回调函数中 ,  根据结果 展示用户名是否可用
	  同时 根据结果 设置 注册按钮是否可用



	--%>

	<script>

		$(function () {



		    $("#username").blur(function () {

		        //1.url
				var url = "${pageContext.request.contextPath}/userServlet"
				//2.params
				var params={
				    "method":"checkUserName",
					"username":$(this).val()
				}
				//3.callBack
				var callBack = function (rel) {

				    var message = rel.message
					// 给span添加信息
					$("#UsernameMessage").html(message)
					// 设置注册按钮
					if(rel.flag == 'true'){
                        $("#submitID").prop("disabled",false);

                    }else{
				        $("#submitID").prop("disabled",true);

                    }

                }
				//4.发射
		        $.post(url,params,callBack,"json");

            })

			//-- 校验验证码逻辑
			//  在 uyzm控件失去焦点之后  发送ajax请求 ,给后端 ,携带验证 进行校验
			// 在回调函数中 可以显示 验证码正确或者错误 ,
			// 如果验证码错误 可以提示验证码错误
			// 同时更新验证码图片 并 设置注册按钮不可用,
			// 如果验证码正确, 则提示验证码正确, 设置 注册按钮可用

			$("#uyzm").blur(function () {

			    //1.url
				var url = "${pageContext.request.contextPath}/userServlet"
				//2.params
				var params={
				    "method":"checkYzm",
					"uyzm":$(this).val()
				}
				//3.callBack
				var  callBack = function (rel) {
					if(rel){
					    //验证码正确
						$("#yzmMessage").html("验证码正确")
                            $("#submitID").prop("disabled",false);

                    }else{
                        $("#yzmMessage").html("验证码错误")

                        $("#submitID").prop("disabled",true);
                        // 用户这次输入验证码错误之后, 更新 验证码图片以便用户下一次再输入
                        $("#yzmImg").prop("src","${pageContext.request.contextPath}/userServlet?method=getYzm&time="+new Date().getTime())
                    }
                }
				//4.发射
				$.post(url,params,callBack,"json")

            })

        })



	</script>

</head>
<body>




<%@ include file="header.jsp"%>





<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
<div class="row"> 

	<div class="col-md-2"></div>
	
	


	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
		<font>会员注册</font>USER REGISTER
		<form action="${pageContext.request.contextPath}/userServlet"  method="post" class="form-horizontal" style="margin-top:5px;">

			<input type="hidden" name="method" value="register">


			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">用户名</label>
			    <div class="col-sm-6">
			      <input type="text"  name="username" class="form-control" id="username" placeholder="请输入用户名"><span id="UsernameMessage"></span>
			    </div>
			 </div>
			   <div class="form-group">
			    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
			    <div class="col-sm-6">
			      <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
			    </div>
			  </div>
			   <div class="form-group">
			    <label for="confirmpwd" class="col-sm-2 control-label">确认密码</label>
			    <div class="col-sm-6">
			      <input type="password" class="form-control" id="confirmpwd" placeholder="请输入确认密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-6">
			      <input type="email" name="email" class="form-control" id="inputEmail3" placeholder="Email">
			    </div>
			  </div>
			 <div class="form-group">
			    <label for="usercaption" class="col-sm-2 control-label">姓名</label>
			    <div class="col-sm-6">
			      <input type="text" name="name" class="form-control" id="usercaption" placeholder="请输入姓名">
			    </div>
			  </div>
			  <div class="form-group opt">  
			  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>  
			  <div class="col-sm-6">
			    <label class="radio-inline">
			  <input type="radio" name="sex" id="inlineRadio1" value="男"> 男
			</label>
			<label class="radio-inline">
			  <input type="radio" name="sex" id="inlineRadio2" value="女"> 女
			</label>
			</div>
			  </div>		
			  <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">出生日期</label>
			    <div class="col-sm-6">
			      <input type="date" name="birthday" class="form-control"  >
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="uyzm" class="col-sm-2 control-label">验证码</label>
			    <div class="col-sm-3">
			      <input type="text" name="uyzm" id="uyzm" class="form-control"  >
			      
			    </div>
			    <div class="col-sm-2">
			    <img id="yzmImg" src="${pageContext.request.contextPath}/userServlet?method=getYzm"/>
			    </div>
			    <span id="yzmMessage"></span>
			  </div>
			 
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <input type="submit"  id="submitID" width="100" value="注册" name="submit" border="0"
				    style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
			    </div>
			  </div>
			</form>
	</div>
	
	<div class="col-md-2"></div>
  
</div>
</div>

	  
	
	<div style="margin-top:50px;">
			<img src="${pageContext.request.contextPath}/img/footer.jpg" width="100%" height="78" alt="我们的优势" title="我们的优势" />
		</div>

		<div style="text-align: center;margin-top: 5px;">
			<ul class="list-inline">
				<li><a href="${pageContext.request.contextPath}/jsp/info.jsp">关于我们</a></li>
				<li><a>联系我们</a></li>
				<li><a>招贤纳士</a></li>
				<li><a>法律声明</a></li>
				<li><a>友情链接</a></li>
				<li><a target="_blank">支付方式</a></li>
				<li><a target="_blank">配送方式</a></li>
				<li><a>服务声明</a></li>
				<li><a>广告声明</a></li>
			</ul>
		</div>
		<div style="text-align: center;margin-top: 5px;margin-bottom:20px;">
			Copyright &copy; 2005-2016 潮铺商城 版权所有
		</div>

</body></html>




