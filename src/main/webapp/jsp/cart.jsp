<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>购物车</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			.container .row div {
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
	</head>

	<body>


	<%@ include file="header.jsp"%>

	<c:if test="${not empty cart }">
		<div class="container">
			<div class="row">

				<div style="margin:0 auto; margin-top:10px;width:950px;">
					<strong style="font-size:16px;margin:5px 0;">购物车详情</strong>
					<table class="table table-bordered">



				<c:forEach items="${cart.map}" var="entry">

					<tbody>
					<tr class="warning">
						<th>图片</th>
						<th>商品</th>
						<th>价格</th>
						<th>数量</th>
						<th>小计</th>
						<th>操作</th>
					</tr>
					<tr class="active">
						<td width="60" width="40%">
							<input type="hidden" name="id" value="22">
							<img src="${pageContext.request.contextPath}/${entry.value.product.pimage}" width="70" height="60">
						</td>
						<td width="30%">
							<a target="_blank"> ${entry.value.product.pname}</a>
						</td>
						<td width="20%">
							￥${entry.value.product.shop_price}
						</td>
						<td width="10%">
							<input type="text" name="count" value="${entry.value.count}" maxlength="4" size="10">
						</td>
						<td width="15%">
							<span class="subtotal">￥${entry.value.subTotal}</span>
						</td>
						<td>
							<a href="${pageContext.request.contextPath}/cartServlet?method=removeCartItemByPid&pid=${entry.value.product.pid}" class="delete">删除</a>
						</td>
					</tr>
					</tbody>


				</c:forEach>






					</table>
				</div>
			</div>

			<div style="margin-right:130px;">
				<div style="text-align:right;">
					<em style="color:#ff6600;">
				登录后确认是否享有优惠&nbsp;&nbsp;
			</em> 赠送积分: <em style="color:#ff6600;">${cart.totalMoney/10}</em>&nbsp; 商品金额: <strong style="color:#ff6600;">￥${cart.totalMoney}元</strong>
				</div>
				<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
					<a href="${pageContext.request.contextPath}/cartServlet?method=clearCart" id="clear" class="clear">清空购物车</a>
					<a href="${pageContext.request.contextPath}/orderServlet?method=createOrder">
						<%--提交表单 --%>

						<c:if test="${empty loginU}">
							<%--未登录状态--%>

							<h1><a href="${pageContext.request.contextPath}/jsp/login.jsp">请登录后 提交订单</a></h1>

						</c:if>

						<c:if test="${not empty loginU}">
							<%-- 登录状态 --%>
							<input type="submit" width="100" value="提交订单" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
									height:35px;width:100px;color:white;">
						</c:if>


					</a>
				</div>
			</div>

		</div>

	</c:if>

	<c:if test="${empty cart}">

		<h1 align="center"> 您的购物车 是空的   请去 购物   </h1>

	</c:if>

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

	</body>

</html>