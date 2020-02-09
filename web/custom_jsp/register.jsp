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
    <title>用户注册</title>
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

        //验证用户名
        function check_username(input_id,td_id) {
            const input = document.getElementById(input_id);
            const td = document.getElementById(td_id);
            //必须以汉字 英文字母 数字 下划线组成：3~8位
            if (input.value.match(/^[\u4E00-\u9FA5a-zA-Z0-9_]{3,8}$/)==null) {
                td.innerText = "用户名格式错误";
                return false;
            }else{
                td.innerText = '';
                return true;
            }
        }
        //验证密码  6~12位由英文、数字组成的密码
        function check_password(input_id,td_id){
            const regx = /^(?!([a-zA-Z]+|\d+)$)[a-zA-Z\d]{6,12}$/;
            let input = document.getElementById(input_id);
            let td = document.getElementById(td_id);
            if(input.value.match(regx)==null){
                td.innerText = "密码格式错误"
                return false;
            }else{
                td.innerText = '';
                return true;
            }
        }
        //验证名字
        function check_name(input_id,td_id) {
            const input = document.getElementById(input_id);
            const td = document.getElementById(td_id);
            //必须以汉字 英文字母 数字 下划线组成：3~8位
            if (input.value.match(/^[\u4E00-\u9FA5a-zA-Z0-9_]{1,8}$/)==null) {
                td.innerText = "名字格式错误";
                return false;
            }else{
                td.innerText = '';
                return true;
            }
        }
        function check_age(input_id,td_id) {
            const input = document.getElementById(input_id);
            const td = document.getElementById(td_id);
            if(input.value <= 0 || input.value > 100){
                td.innerText = "年龄错误";
                return false;
            }else{
                td.innerText = '';
                return true;
            }
        }
        function check_address(input_id,td_id){
            const input = document.getElementById(input_id);
            const td = document.getElementById(td_id);
            //汉字 英文字母 数字 下划线组成：0~20位
            if (input.value.match(/^[\u4E00-\u9FA5a-zA-Z0-9_]{0,20}$/)==null) {
                td.innerText = "籍贯格式错误";
                return false;
            }else{
                td.innerText = '';
                return true;
            }
        }
        function check_phone(input_id,td_id){
            const input = document.getElementById(input_id);
            const td = document.getElementById(td_id);
            if (input.value.match(/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/)==null) {
                td.innerText = "号码格式错误";
                return false;
            }else{
                td.innerText = '';
                return true;
            }
        }
        function check_empty() {
            if(!check_username('usernameID','username_tip') || !check_password('passwordID','password_tip') || !check_name('nameID','name_tip')){
                alert("姓名、用户名和密码不可以为空");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="box">
    <div class="box_top_left">
        <p>用户注册</p>
    </div>
    <div class="box_top_right">
        <p>已有账号?<a href="${pageContext.request.contextPath}/custom_jsp/login.jsp">立即登录</a></p>
    </div>
    <div class="box_center">
        <form action="${pageContext.request.contextPath}/FansRegistServlet" method="post">
            <table class="table_center">
                <tr>
                    <td class="td_left"><label for="usernameID">用户名</label></td>
                    <td class="td_right"><input type="text" id="usernameID" name="username" placeholder="汉字英文数字下划线随意组成：3~8位" onkeyup="check_username('usernameID','username_tip');"></td>
                    <td style="color: red;font-size: 14px;" id="username_tip"></td>
                </tr>
                <tr>
                    <td class="td_left"><label for="passwordID">密码</label></td>
                    <td class="td_right"><input type="text" id="passwordID" name="password" placeholder="必须由英文、数字共同组成：6~12位" onkeyup="check_password('passwordID','password_tip');"></td>
                    <td style="color: red;font-size: 14px;" id="password_tip"></td>
                </tr>
                <tr>
                    <td class="td_left"><label for="nameID">姓名</label></td>
                    <td class="td_right"><input type="text" id="nameID" name="name" placeholder="汉字英文数字下划线随意组成：1~8位" onkeyup="check_name('nameID','name_tip');"></td>
                    <td style="color: red;font-size: 14px;" id="name_tip"></td>
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
                    <td class="td_right"><input type="number" id="ageID" name="age" placeholder="请输入年龄" onkeyup="check_age('ageID','age_tip');"></td>
                    <td style="color: red;font-size: 14px;" id="age_tip"></td>
                </tr>
                <tr>
                    <td class="td_left"><label for="emailID">邮箱</label></td>
                    <td class="td_right"><input type="email" id="emailID" name="email" placeholder="请输入邮箱"></td>
                    <td id="email_tip"></td>
                </tr>
                <tr>
                    <td class="td_left"><label for="addressID">籍贯</label></td>
                    <td class="td_right"><input type="text" id="addressID" name="address" placeholder="请输入籍贯" onkeyup="check_address('addressID','address_tip');"></td>
                    <td style="color: red;font-size: 14px;" id="address_tip"></td>
                </tr>
                <tr>
                    <td class="td_left"><label for="phoneID">手机号</label></td>
                    <td class="td_right"><input type="text" id="phoneID" name="phone" placeholder="请输入手机号" onkeyup="check_phone('phoneID','phone_tip');"></td>
                    <td style="color: red;font-size: 14px;" id="phone_tip"></td>
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
                        <input type="submit" onclick="check_empty();" id="btn_sub" value="注册">
                        <button type="reset" id="btn_reset" onclick="refreshCode();">重置</button>
                        <a href="${pageContext.request.contextPath}/custom_jsp/index.jsp">
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