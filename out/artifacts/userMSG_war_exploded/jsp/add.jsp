<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">添加客户页面</h3>
    <form class="form-horizontal" action="${pageContext.request.contextPath}/AddUserServlet?activeUserID=${param.activeUserID}" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" value="${requestScope.fail_values.name[0]}" placeholder="请输入姓名">
        </div>

        <div class="form-group">
            <label>性别：</label>
            <input type="radio" name="sex" value="男" checked="checked"/>男
            <input type="radio" name="sex" value="女"/>女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" value="${requestScope.fail_values.sex[0]}" placeholder="请输入年龄">
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
<%--            <select name="address" class="form-control" id="address">--%>
<%--                <option value="陕西">陕西</option>--%>
<%--                <option value="北京">北京</option>--%>
<%--                <option value="上海">上海</option>--%>
<%--            </select>--%>
            <input type="text" class="form-control" id="address" name="address" value="${requestScope.fail_values.address[0]}" placeholder="请输入籍贯"/>
        </div>

        <div class="form-group">
            <label for="phone">电话：</label>
            <input type="text" class="form-control" id="phone" name="phone" value="${requestScope.fail_values.phone[0]}" placeholder="请输入手机号码"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" id="email" name="email" value="${requestScope.fail_values.email[0]}" placeholder="请输入邮箱地址"/>
        </div>
        <div class="form-group">
            <label for="username">账号：</label>
            <input type="text" class="form-control" id="username" name="username" placeholder="请输入账号"/>
        </div>
        <div class="form-group">
            <label for="password">密码：</label>
            <input type="text" class="form-control" id="password" name="password" placeholder="请输入密码"/>
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" id="submitButton" type="submit" value="提交" />
            <input class="btn btn-default" id="resetButton" type="reset" value="重置" />
<%--            <input class="btn btn-default" id="returnButton" type="button" value="返回" onclick="<jsp:forward page="/UserListServlet"></jsp:forward>"/>--%>
        </div>
    </form>
    <c:if test="${not empty requestScope.add_msg}">
        <!-- 出错显示的信息框 -->
        <div class="alert alert-warning alert-dismissible" role="alert" style="margin-top: 30px">
            <button type="button" class="close" data-dismiss="alert">
                <span>&times;</span>
            </button>
            <strong style="color: red;">${requestScope.add_msg}</strong>
        </div>
    </c:if>

</div>
</body>
</html>
