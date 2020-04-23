<%--
  Created by IntelliJ IDEA.
  User: tk
  Date: 2019/5/28
  Time: 14:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/qrcode.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"></script>

    <script>

        <%--页面加载成功之后  获取 qrcode控件 生成一个二维码 --%>
        $(function () {

            new QRCode(document.getElementById('qrcode'), 'hahahha');


        })

    </script>


</head>
<body>


<div id="qrcode"></div>

</body>
</html>
