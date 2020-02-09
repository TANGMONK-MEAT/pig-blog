<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
<%--    <!-- 使用Edge最新的浏览器的渲染方式 -->--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
<%--    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。--%>
<%--    width: 默认宽度与设备的宽度相同--%>
<%--    initial-scale: 初始的缩放比，为1:1 -->--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/index.js"></script>
</head>
<body>
<div class="header">
    <div class="header03"></div>
    <div class="header01"></div>
    <div class="header02">后台管理</div>
</div>
<div class="left" id="LeftBox">
    <div class="left01">
        <div class="left01_right"></div>
        <div class="left01_left"></div>
        <%--        显示管理员姓名--%>
        <div class="left01_c">
        <strong>管理员：${sessionScope.activeUserName}</strong>
        </div>
    </div>
    <div class="left02">
        <div class="left02top">
            <div class="left02top_right"></div>
            <div class="left02top_left"></div>
            <div class="left02top_c"><b>用户管理</b></div>
        </div>
        <div class="left02down">
            <div class="left02down01">
                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?activeUserID=${sessionScope.activeUserID}"
                       target="iframe_box"> &#9658 用户信息管理</a>
            </div>
            <div class="left02down01" >
                <a href="#" onclick="request_me_msg();return false;" target="iframe_box">&#9658 我的信息管理</a>
            </div>
            <form id="request_msg_form" action="${pageContext.request.contextPath}/ShowPowerUserMsgServlet" method="post" target="iframe_box">
                <input type="hidden" name="activeUserID" value="${sessionScope.activeUserID}"/>
            </form>
        </div>
    </div>
    <div class="left02">
        <div class="left02top">
            <div class="left02top_right"></div>
            <div class="left02top_left"></div>
            <div class="left02top_c">数据统计</div>
        </div>
        <div class="left02down">
            <div class="left02down01" >
                <a href="#" onclick="return false;" target="iframe_box">&#9658 用户在线统计</a>
            </div>
            <div class="left02down01">
                <a href="#" onclick="return false;" target="iframe_box">&#9658 用户点赞统计</a>
            </div>
        </div>
    </div>
    <div class="left02">
        <div class="left02top">
            <div class="left02top_right"></div>
            <div class="left02top_left"></div>
            <div class="left02top_c">聊天室管理</div>
        </div>
        <div class="left02down">
            <div class="left02down01">
                <a href="#">&#9658 群聊</a>
            </div>
            <div class="left02down01">
                <a href="${pageContext.request.contextPath}/GetMessageBoardServlet" target="iframe_box">&#9658 私信</a>
                <label style="float: right;margin-right: 20px;color: red;" id="newMsg">0</label>
            </div>
        </div>
    </div>
    <div class="left02">
        <div class="left02top">
            <div class="left02top_right"></div>
            <div class="left02top_left"></div>
            <div class="left02top_c">动态管理</div>
        </div>
        <div class="left02down">
            <div class="left02down01">
                <a href="${pageContext.request.contextPath}/jsp/dynamic_publication.jsp" target="iframe_box">&#9658 动态发布</a>
            </div>
            <div class="left02down01">
                <a href="#" onclick="request_dynamic('God');return false;" target="iframe_box">&#9658 动态删除</a>
            </div>
            <form id="showDynamic_form" action="${pageContext.request.contextPath}/ShowDynamicMsgServlet" target="iframe_box" method="post">
                <input type="hidden" name="dynamic" value="0"/>
                <input id="who" type="hidden" name="who" value=""/>
            </form>
        </div>
    </div>
    <div class="left02">
        <div class="left02top">
            <div class="left02top_right"></div>
            <div class="left02top_left"></div>
            <div class="left02top_c">评论管理</div>
        </div>
        <div class="left02down">
            <div class="left02down01">
                <a href="${pageContext.request.contextPath}/ShowNewCommentMsgServlet" target="iframe_box">&#9658 最新评论</a>
                <label style="float: right;margin-right: 20px;color: red;" id="newComment">0</label>
            </div>
            <div class="left02down01">
                <a href="#" onclick="request_dynamic('comment');return false;" target="iframe_box">&#9658 发布|删除</a>
            </div>
        </div>
    </div>
    <div class="left01">
        <div class="left03_right"></div>
        <div class="left01_left"></div>
        <div class="left03_c">
            <a href="${pageContext.request.contextPath}/custom_jsp/index.jsp" target="_blank">客户端</a>
        </div>
    </div>
    <div class="left01">
        <div class="left03_right"></div>
        <div class="left01_left"></div>
        <div class="left03_c">
            <button title="安全退出" onclick="quit_system();">安全退出</button>
        </div>
        <form id="quit_form" action="${pageContext.request.contextPath}/QuitLoginServlet" target="_self" method="post">
            <input type="hidden" name="who" value="God"/>
            <input type="hidden" name="id" value="${sessionScope.activeUserID}"/>
        </form>
    </div>
</div>


<div class="right_mainBox" id="RightBox">
            <iframe style="width: 100%;max-height: 630px;min-height:630px;border: 0" name="iframe_box"></iframe>
</div>
<script>
    window.onload = function () {
        setNewCommentNum();
        setNewMessageNum();
    }
    //请求用户数据
    function request_me_msg() {
        let form = document.getElementById("request_msg_form");
        form.submit();
    }
    //删除动态的a标签的点击事件
    function request_dynamic(msg) {
        let form = document.getElementById("showDynamic_form");
        let input = document.getElementById("who");
        input.value = msg;
        form.submit();//提交请求信息
    }
    //退出系统
    function quit_system() {
        if(confirm("你确定退出吗？")){
            let form = document.getElementById("quit_form");
            form.submit();
        }
    }
    //设置新评论的个数通知
    function setNewCommentNum(){
        let label = document.getElementById("newComment");
        let xml_request = new XMLHttpRequest();
        xml_request.open('post', "${pageContext.request.contextPath}/GetNewCommentNumServlet", true);
        xml_request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;charset=utf-8');
        xml_request.onreadystatechange = function(){
            if (xml_request.readyState === 4 && xml_request.status === 200) {
                const kv = eval("(" + xml_request.responseText + ")");
                label.innerText = kv.num;
            }
        };
        xml_request.send();
    }
    function setNewMessageNum(){
        let label = document.getElementById("newMsg");
        let xml_request = new XMLHttpRequest();
        xml_request.open('post', "${pageContext.request.contextPath}/GetNewMessageNumServlet", true);
        xml_request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;charset=utf-8');
        xml_request.onreadystatechange = function(){
            if (xml_request.readyState === 4 && xml_request.status === 200) {
                const kv = eval("(" + xml_request.responseText + ")");
                label.innerText = kv.num;
            }
        };
        xml_request.send();
    }
    //轮询 ，每3秒
    // self.setInterval(function () {
    //     setNewCommentNum();
    //     setNewMessageNum();
    // },3000);
</script>
</body>
</html>
