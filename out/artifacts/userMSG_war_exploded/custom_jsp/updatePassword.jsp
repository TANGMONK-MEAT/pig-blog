<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改密码</title>
    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="${pageContext.request.contextPath}/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script>
        function update() {
            let p_1 = document.getElementById("p_1");
            let p_2 = document.getElementById("p_2");
            let old_p = document.getElementById('old_p');
            if(old_p.value.length === 0 || p_1.value.length === 0 || p_2.value.length === 0){
                alert("密码不可以为空");
            }else{
                if(p_1.value === p_2.value && isTrue('p_1','p_1_label') && isTrue('p_2','p_2_label')){
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
                }else{
                    alert("两次的密码不一致");
                }
            }
        }
        //判断密码框的密码格式是否正确
        function isTrue(input_id,label_id) {
            const regx = /^(?!([a-zA-Z]+|\d+)$)[a-zA-Z\d]{6,12}$/;
            let input = document.getElementById(input_id);
            let label = document.getElementById(label_id);
            if(input.value.match(regx)==null){
                label.innerText = "密码格式错误"
                return false;
            }else{
                label.innerText = '';
                return true;
            }
        }
    </script>
</head>
<body>
<div class="container" style="max-width: 400px;">
    <h3 style="text-align: center;">修改密码</h3>
    <form class="form-horizontal" id="updateForm" action="${pageContext.request.contextPath}/UpdatePersonPasswordServlet"
          method="post">
        <input type="hidden" name="user_id" value="${sessionScope.activeUserID}"/>
        <div class="form-group">
            <label for="old_p">旧密码：</label>
            <input type="text" class="form-control" id="old_p" name="oldPassword"
                   placeholder="请输入旧密码"/>
        </div>
        <div class="form-group">
            <label for="p_1">新密码：</label>
            <label id="p_1_label" style="color: red"></label>
            <input type="password" class="form-control" id="p_1" name="p_1" onkeyup="isTrue('p_1','p_1_label')"
                   placeholder="请输入6~12位有英文、数字的密码"/>
        </div>
        <div class="form-group">
            <label for="p_2">请确认：</label>
            <label id="p_2_label" style="color: red"></label>
            <input type="password" id="p_2" class="form-control" name="password" onkeyup="isTrue('p_2','p_2_label')"
                   placeholder="请输入6~12位有英文、数字的密码"/>
        </div>
        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="修改" onclick="update();"/>
            <input class="btn btn-primary" type="reset" value="重置" />
        </div>
    </form>
</div>
</body>
</html>
