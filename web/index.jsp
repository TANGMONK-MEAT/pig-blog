<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
</head>
<body>
<form id="login_msg_form" action="${pageContext.request.contextPath}/custom_jsp/login.jsp" method="post" target="_parent">
    <input type="hidden" name="login_msg" id="login_msg_input" value="${requestScope.login_msg}"/>
</form>
<form id="p_login_msg_form" action="${pageContext.request.contextPath}/jsp/login.jsp" method="post" target="_parent">
    <input type="hidden" name="login_msg" id="p_login_msg_input" value="${requestScope.login_msg}"/>
</form>
<a style="display: none;" id="to_index" onclick="to_index();"></a>
</body>
<script>
    window.onload = function () {
        <c:if test="${requestScope.login_msg == null}">
        to_index();
        </c:if>
        <c:if test="${requestScope.login_msg == \"账号异常，请重新登录\"}">
        document.getElementById("login_msg_form").submit();
        </c:if>
        <c:if test="${requestScope.login_msg == \"请先登录\"}">
        document.getElementById("p_login_msg_form").submit();
        </c:if>
    }
    function to_index() {
        let a = document.getElementById("to_index");
        a.href="${pageContext.request.contextPath}/custom_jsp/index.jsp";
        a.click();
    }
</script>
</html>

