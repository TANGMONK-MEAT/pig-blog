<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改信息</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script>
        function update() {
            if (confirm("你确定要修改吗？")) {
                document.getElementById("updateForm").submit();
                <c:if test="${empty requestScope.update_msg}">
                alert("修改成功");
                </c:if>
                <c:if test="${not empty requestScope.update_msg}">
                alert("网络繁忙，请稍后重试");
                </c:if>
                window.close();
            }
        }
    </script>
</head>
<body>
<div class="container" style="max-width: 400px;">
    <h3 style="text-align: center;">修改个人信息</h3>
    <form class="form-horizontal" id="updateForm" action="${pageContext.request.contextPath}/updatePersonMsgServlet"
          method="post">
        <input type="hidden" name="id" value="${requestScope.user.id}"/>
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" value="${requestScope.user.name}"
                   readonly="readonly" placeholder="请输入姓名"/>
        </div>

        <div class="form-group">
            <label>性别：</label>
            <c:if test="${requestScope.user.sex=='男'}">
                <input type="radio" name="sex" value="男" checked/>男
                <input type="radio" name="sex" value="女"/>女
            </c:if>
            <c:if test="${requestScope.user.sex=='女'}">
                <input type="radio" name="sex" value="男"/>男
                <input type="radio" name="sex" value="女" checked/>女
            </c:if>
        </div>
        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" value="${requestScope.user.age}"
                   placeholder="请输入年龄"/>
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <input type="text" id="address" class="form-control" name="address" value="${requestScope.user.address}"
                   placeholder="请输入籍贯"/>
        </div>

        <div class="form-group">
            <label for="phone">电话：</label>
            <input type="text" id="phone" class="form-control" name="phone" value="${requestScope.user.phone}"
                   placeholder="请输入手机号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" name="email" value="${requestScope.user.email}"
                   placeholder="请输入邮箱地址"/>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="修改" onclick="javascript:update();"/>
        </div>
    </form>
</div>
</body>
</html>
