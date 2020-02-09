<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/css/login.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/login.js"></script>
    <script>
        //切换验证码图片
        function refreshCode() {
            //获取验证码对象
            var vcode = document.getElementById("vcode");
            //设置其src属性，加上上时间戳
            vcode.src = "${pageContext.request.contextPath}/CheckCodeServlet?time=" + new Date().getTime();
        }
    </script>
</head>
<body style="background-image: url('${pageContext.request.contextPath}/images/bg.png');">
<div>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
        <div class="box">
            <h1><strong>后台管理系统</strong></h1>
            <div id="username_input_error_box" class="error_box"></div>
            <div id="error_box"></div>
            <div class="input_box">
                <input style="background-image: url('${pageContext.request.contextPath}/images/username.gif');" type="text" placeholder="请输入账户名" id="uname" name="username"/>
            </div>
            <div id="password_input_error_box" class="error_box"></div>
            <div class="input_box">
                <input style="background-image: url('${pageContext.request.contextPath}/images/password.png');" type="password" placeholder="请输入密码" id="upass" name="password"/>
            </div>
            <div id="check_input_error_box" class="error_box"></div>
            <div class="input_check_box">
                <input style="background-image: url('${pageContext.request.contextPath}/images/checkCode.png');" type="text" placeholder="请输入验证码" id="check" name="verifycode"/>
                <a href="javascript:refreshCode()">
                    <img style="margin-left: 15px;" src="${pageContext.request.contextPath}/CheckCodeServlet" title="看不清点击刷新" id="vcode"/>
                </a>
            </div>
            <div class="input_box">
                <button type="submit">登录</button>
            </div>
            <div align="center">
                <c:if test="${not empty requestScope.login_msg}">
                    <!-- 出错显示的信息框 -->
                    <strong style="color: #c7254e;">${requestScope.login_msg}</strong>
                </c:if>
            </div>
            <div>
                <a href="${pageContext.request.contextPath}/jsp/forgotPassword.jsp"
                   style="float: left;margin-top: 10px;color: #c7254e;">忘记密码?</a>
                <a href="#"
                   style="float: right;margin-top: 10px;color: #c7254e;">立即注册</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
