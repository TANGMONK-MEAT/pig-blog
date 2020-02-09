<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/register.css">
    <title>管理员注册</title>
    <script type="text/javascript">
        /*
         *切换验证码图片
         */
        function refreshCode() {
            //获取验证码对象
            var img_check = document.getElementById("img_check");
            //设置其src属性，加上上时间戳
            img_check.src = "${pageContext.request.contextPath}/CheckCodeServlet?time=" + new Date().getTime();
        }
    </script>
</head>
<body>
<div class="box">
    <div class="box_top_left">
        <p>管理员注册</p>
    </div>
    <div class="box_top_right">
        <p>已有账号?<a href="${pageContext.request.contextPath}/jsp/login.jsp">立即登录</a></p>
    </div>
    <div class="box_center">
        <form action="${pageContext.request.contextPath}/RegistServlet" method="post">
            <table class="table_center">
                <tr>
                    <td class="td_left"><label for="usernameID">用户名</label></td>
                    <td class="td_right"><input type="text" id="usernameID" name="username" placeholder="请输入用户明"></td>
                    <td>3</td>
                </tr>
                <tr>
                    <td class="td_left"><label for="passwordID">密码</label></td>
                    <td class="td_right"><input type="text" id="passwordID" name="password" placeholder="请输入密码"></td>
                    <td>3</td>
                </tr>
                <tr>
                    <td class="td_left"><label for="nameID">姓名</label></td>
                    <td class="td_right"><input type="text" id="nameID" name="name" placeholder="请输入名字"></td>
                    <td>3</td>
                </tr>
                <tr>
                    <td class="td_left"><label>性别</label></td>
                    <td class="td_right">
                        <input type="radio" name="sex" value="男" checked>男
                        <input type="radio" name="sex" value="女">女
                    </td>
                </tr>
                <tr>
                    <td class="td_left"><label for="ageID">年龄</label></td>
                    <td class="td_right"><input type="number" id="ageID" name="age" placeholder="请输入年龄"></td>
                    <td>3</td>
                </tr>
                <tr>
                    <td class="td_left"><label for="emailID">邮箱</label></td>
                    <td class="td_right"><input type="email" id="emailID" name="email" placeholder="请输入邮箱"></td>
                    <td>3</td>
                </tr>
                <tr>
                    <td class="td_left"><label for="addressID">籍贯</label></td>
                    <td class="td_right"><input type="text" id="addressID" name="address" placeholder="请输入籍贯"></td>
                    <td>3</td>
                </tr>
                <tr>
                    <td class="td_left"><label for="phoneID">手机号</label></td>
                    <td class="td_right"><input type="text" id="phoneID" name="phone" placeholder="请输入手机号"></td>
                    <td>3</td>
                </tr>
                <tr>
                    <td class="td_left"><label for="checkCodeID">验证码</label></td>
                    <td class="td_right"><input type="text" id="checkCodeID" name="checkCode" placeholder="请输入验证码">
                        <a href="javascript:refreshCode()"><img id="img_check" title="看不清点击刷新" src="${pageContext.request.contextPath}/CheckCodeServlet"></a>
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td align="center">
                        <input type="submit" id="btn_sub" value="注册">
                        <button type="reset" id="btn_reset" onclick="refreshCode();">重置</button>
                        <a href="${pageContext.request.contextPath}/jsp/login.jsp">
                            <button id="btn_return" type="button">返回</button>
                        </a>
                    </td>
                    <td></td>
                </tr>
            </table>
        </form>
        <c:if test="${not empty requestScope.register_msg}">
            <!-- 出错显示的信息 -->
            <div class="" style="margin-left: 300px;">
                <strong style="color: red;">${requestScope.register_msg}</strong>
            </div>
        </c:if>
    </div>
</div>
</body>
</html>
