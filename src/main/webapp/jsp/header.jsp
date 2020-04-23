<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: tk
  Date: 2019/5/20
  Time: 11:57
  To change this template use File | Settings | File Templates.
--%>
<!--
描述：菜单栏
-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>

<script>

    <%--  页面加载成功之后

        发送ajax请求 给后端 获取所有的分类信息   遍历结果拼装到  categoryUL控件中
    --%>

    $(function () {

        //1.url
        var url = "${pageContext.request.contextPath}/categoryServlet"
        //2.params
        var params = {
            "method":"findAllCategoty"
        }
        //3.callBack
        //
        var callBack = function (rel) {
          //[{"cid":"1","cname":"手机数码"},{"cid":"172934bd636d485c98fd2d3d9cccd409","cname":"运动户外"},{"cid":"2","cname":"电脑办公"},{"cid":"3","cname":"家具家居"},{"cid":"4","cname":"鞋靴箱包"},{"cid":"5","cname":"图书音像"},{"cid":"59f56ba6ccb84cb591c66298766b83b5","cname":"aaaa"},{"cid":"6","cname":"母婴孕婴"},{"cid":"afdba41a139b4320a74904485bdb7719","cname":"汽车用品"}]
        // <li><a href="#">电脑办公</a></li>
            $(rel).each(function (index, ele) {

                $("#categoryUL").append("<li><a href='${pageContext.request.contextPath}/productServlet?method=findProductsByCid&cid="+ele.cid+"'>"+ele.cname+"</a></li>")

            })

        }
        //4.发射
        $.post(url,params,callBack,"json")

    })

</script>





<div class="container-fluid">
    <div class="col-md-4">
        <img src="${pageContext.request.contextPath}/img/her1.png" />
    </div>
    <div class="col-md-5">
        <img src="${pageContext.request.contextPath}/img/header.png" />
    </div>
    <div class="col-md-3" style="padding-top:20px">


        <c:if test="${not empty loginU}">

            <ol class="list-inline">
                欢迎您${loginU.name}
                <li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>
                <li><a href="${pageContext.request.contextPath}/orderServlet?method=findAllOrders">我的订单</a></li>
                <li><a href="${pageContext.request.contextPath}/userServlet?method=logOut">注销</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/updatePwd.jsp">修改密码</a></li>
            </ol>

        </c:if>

        <c:if test="${empty loginU}">

            <ol class="list-inline">

                <li><a href="${pageContext.request.contextPath}/jsp/login.jsp">登录</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/register.jsp">注册</a></li>
                <li><a href="${pageContext.request.contextPath}/jsp/cart.jsp">购物车</a></li>

            </ol>
        </c:if>


    </div>
</div>
<!--
描述：导航条
-->
<div class="container-fluid">
    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${pageContext.request.contextPath}/productServlet?method=findHotAndNewProducts">首页</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="categoryUL" class="nav navbar-nav">

                </ul>
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <input type="text" class="form-control" placeholder="Search">
                    </div>
                    <button type="submit" class="btn btn-default">Submit</button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container-fluid -->
    </nav>
</div>