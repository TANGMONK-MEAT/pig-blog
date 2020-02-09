<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>BLOG</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <script>
    </script>
    <style>
        #music_div {
            position: fixed;
            bottom: 20px;
            left: 5px;
            z-index: 99;
        }
        #music_div audio{
            max-width: 240px;
            border: none;
            outline: none;
            color: white; /* 文本颜色 */
            cursor: pointer;
            padding: 0;
            border-radius: 30px; /* 圆角 */
        }
        /* 下拉按钮样式 */
        .drop-btn {
            background:transparent;	/*按钮背景透明*/
            outline:none;	/*点击后没边框*/
            color: #0ab4ff;
            padding: 8px 20px 8px 20px;
            font-size: 16px;
            border: none;
            cursor: pointer;
        }

        /* 容器 <div> - 需要定位下拉内容 */
        .dropdown {
            float: right;
            margin-top: 20px;
            position: relative;
            display: inline-block;
        }

        /* 下拉内容 (默认隐藏) */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #f9f9f9;
            min-width: 100px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
        }

        /* 下拉菜单的链接 */
        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
        }

        /* 鼠标移上去后修改下拉菜单链接颜色 */
        .dropdown-content a:hover {background-color: #f1f1f1}

        /* 在鼠标移上去后显示下拉菜单 */
        .dropdown:hover .dropdown-content {
            display: block;
        }

        /* 当下拉内容显示后修改下拉按钮的背景颜色 */
        .dropdown:hover .drop-btn {
            background-color: #f1f1f1;
        }
    </style>
    <script>
        function show_me_msg() {
            const xml_request = new XMLHttpRequest();
            xml_request.open("get","${pageContext.request.contextPath}/AboutMeServlet?me=0",true);
            //xml_request处理事件,异步
            xml_request.onreadystatechange = function() {
                if (xml_request.readyState === 4 && xml_request.status === 200) {
                    //在这里进行相关处理,通过xhr.responseText获取后台反馈的文本
                    const kv = eval("("+xml_request.responseText+")");
                    document.getElementById("job_label").innerText += kv.job;
                    document.getElementById("person_img").src = "${applicationScope.basePath}/"+kv.head_img;
                    document.getElementById("introduction_p").innerText += kv.introduction;
                    document.getElementById("motto").innerText = kv.motto ;
                    document.getElementById("name_label").innerText += kv.name;
                }
            };
            xml_request.send();
        }
        function show_new_msg() {
            const xml_request = new XMLHttpRequest();
            xml_request.open("get","${pageContext.request.contextPath}/FindNewDynamicMsgServlet",true);
            //xml_request处理事件,异步
            xml_request.onreadystatechange = function() {
                if (xml_request.readyState == 4 && xml_request.status == 200) {
                    const kv = eval("("+xml_request.responseText+")");
                    document.getElementById("new_0").innerText = (kv.title_0 === undefined ? '' : kv.title_0);
                    document.getElementById("new_0").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic=" + kv.id_0;
                    document.getElementById("new_0").title = kv.title_0;
                    document.getElementById("new_1").innerText = (kv.title_1 === undefined ? '' : kv.title_1);
                    document.getElementById("new_1").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_1;
                    document.getElementById("new_1").title = kv.title_1;
                    document.getElementById("new_2").innerText = (kv.title_2 === undefined ? '' : kv.title_2);
                    document.getElementById("new_2").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_2;
                    document.getElementById("new_2").title = kv.title_2;
                    document.getElementById("new_3").innerText = (kv.title_3 === undefined ? '' : kv.title_3);
                    document.getElementById("new_3").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_3;
                    document.getElementById("new_3").title = kv.title_3;
                    document.getElementById("new_4").innerText = (kv.title_4 === undefined ? '' : kv.title_4);
                    document.getElementById("new_4").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_4;
                    document.getElementById("new_4").title = kv.title_4;
                    document.getElementById("new_5").innerText = (kv.title_5 === undefined ? '' : kv.title_5);
                    document.getElementById("new_5").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_5;
                    document.getElementById("new_5").title = kv.title_5;
                }
            }
            xml_request.send();
        }
        function show_order_msg() {
            const xml_request = new XMLHttpRequest();
            xml_request.open("get","${pageContext.request.contextPath}/FindOrderDynamicMsgServlet",true);
            //xml_request处理事件,异步
            xml_request.onreadystatechange = function() {
                if (xml_request.readyState == 4 && xml_request.status == 200) {
                    const kv = eval("("+xml_request.responseText+")");
                    document.getElementById("order_0").innerText = (kv.title_0 === undefined ? '': kv.title_0);
                    document.getElementById("order_0").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_0;
                    document.getElementById("order_0").title = kv.title_0;
                    document.getElementById("order_1").innerText = (kv.title_1 === undefined ? '': kv.title_1);
                    document.getElementById("order_1").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_1;
                    document.getElementById("order_1").title = kv.title_1;
                    document.getElementById("order_2").innerText = (kv.title_2 === undefined ? '': kv.title_2);
                    document.getElementById("order_2").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_2;
                    document.getElementById("order_2").title = kv.title_2;
                    document.getElementById("order_3").innerText = (kv.title_3 === undefined ? '': kv.title_3);
                    document.getElementById("order_3").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_3;
                    document.getElementById("order_3").title = kv.title_3;
                    document.getElementById("order_4").innerText = (kv.title_4 === undefined ? '': kv.title_4);
                    document.getElementById("order_4").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_4;
                    document.getElementById("order_4").title = kv.title_4;
                    document.getElementById("order_5").innerText = (kv.title_5 === undefined ? '': kv.title_5);
                    document.getElementById("order_5").href = "${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic="+kv.id_5;
                    document.getElementById("order_5").title = kv.title_5;
                }
            }
            xml_request.send();
        }
    </script>
</head>
<body>
<div class="container">
<%--    音乐播放器--%>
    <div id="music_div">
        <audio id="audio" controls="controls" loop="-1" title="若关闭,请刷新"></audio>
    </div>
    <div class="main_box">
        <div id="header">
            <h1>个人博客</h1>
            <c:if test="${sessionScope.activeUserID == null}">
                <div id="login_register">
                    <a href="${pageContext.request.contextPath}/custom_jsp/login.jsp"><span>登录</span></a>
                    <a href="${pageContext.request.contextPath}/custom_jsp/register.jsp"><span>注册</span></a>
                </div>
            </c:if>
            <c:if test="${sessionScope.activeUserID != null}">
                <div class="dropdown" id="dropdown">
                    <button id="_user" class="drop-btn" value="${sessionScope.activeUserID}">
                        ${sessionScope.activeUserName}
                    </button>
                    <div class="dropdown-content">
                        <a id="me_msg" href="#" onclick="javascript:to_showMSgServlet();return false;" target="_blank">个人信息</a>
                        <a id="update_pw" href="#" onclick="javascript:to_updatePassword();return false;" target="_blank">修改密码</a>
                        <a id="quit_login" href="#" onclick="javascript:to_quitSystemServlet();return false;" target="_self">退出登录</a>
                    </div>
                </div>
            </c:if>
            <p id="motto">青春是打开了,就合不上的书。人生是踏上了，就回不了头的路，爱情是扔出了，就收不回的赌注。</p>
        </div>
        <div class="titles">
            <ul>
                <li id="li_1" onclick="setSelected('li_1')"><a id="li_1_a" href="${pageContext.request.contextPath}/ShowDynamicMsgServlet?dynamic=0" target="contextIframe">首页</a>
                </li>
                <li id="li_2" onclick="setSelected('li_2')"><a href="${pageContext.request.contextPath}/AboutMeServlet?me=1" target="contextIframe">博主专题</a>
                </li>
                <li id="li_3" onclick="setSelected('li_3')"><a href="${pageContext.request.contextPath}/PhotosServlet" target="contextIframe">相册展示</a>
                </li>
                <li id="li_4" onclick="setSelected('li_4')"><a href="${pageContext.request.contextPath}/custom_jsp/messageBoard_iframe.jsp" target="contextIframe">留言板</a>
                </li>
                <li id="li_5" onclick="setSelected('li_5')"><a href="${pageContext.request.contextPath}/custom_jsp/chat_iframe.jsp" target="contextIframe">聊天室</a>
                </li>
            </ul>
        </div>
        <!--        left-->
        <iframe
                id="contextIframe" name="contextIframe" src="${pageContext.request.contextPath}/custom_jsp/index_iframe.jsp"
                style="overflow-x:hidden;width:725px;height:1400px;" frameborder="0" scrolling="auto"></iframe>
        <!--        right-->

        <div id="find_box">
            <!--            关于博主-->
            <div id="about_me">
                <div class="find_title">
                    <h3>关于博主</h3>
                </div>
                <hr/>
                <div class="about_li">
                    <ol>
                        <li><a id="me_a" href="${pageContext.request.contextPath}/AboutMeServlet?me=1" title="点击查看详情" target="contextIframe"><img id="person_img" src="#" class="person_Img"></a></li>
                        <li><label id="name_label">姓名：</label></li>
                        <li><label id="job_label">职业：</label></li>
                        <li>
                            <p id="introduction_p"><label>简介：</label></p>
                        </li>
                    </ol>
                </div>
            </div>
            <!--        文章排行-->
            <div id="order_by">
                <div class="find_title">
                    <h3>文章排行</h3>
                </div>
                <hr/>
                <div class="li_a">
                    <ol>
                        <li><a id="order_0" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="order_1" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="order_2" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="order_3" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="order_4" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="order_5" href="#" title="" target="contextIframe"></a></li>
                    </ol>
                </div>
            </div>
            <!--        最新文章-->
            <div id="new_text">
                <div class="find_title">
                    <h3>最新文章</h3>
                </div>
                <hr/>
                <div class="li_a">
                    <ol>
                        <li><a id="new_0" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="new_1" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="new_2" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="new_3" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="new_4" href="#" title="" target="contextIframe"></a></li>
                        <li><a id="new_5" href="#" title="" target="contextIframe"></a></li>
                    </ol>
                </div>
            </div>
        </div>
    </div>
    <button onclick="topFunction()" id="topBtn">返回顶部</button>
<%--    音乐--%>
</div>
<form id="updatePw_form" action="${pageContext.request.contextPath}/custom_jsp/updatePassword.jsp" method="post" target="_blank">
    <input type="hidden" name="user_id" value="${sessionScope.activeUserID}"/>
</form>
<form id="updateMsg_form" action="${pageContext.request.contextPath}/showPersonMsgServlet" method="post" target="_blank">
    <input type="hidden" name="id" value="${sessionScope.activeUserID}"/>
</form>

<form id="QuitSystem_form" action="${pageContext.request.contextPath}/QuitLoginServlet" method="post" target="_self">
    <input type="hidden" name="id" value="${sessionScope.activeUserID}"/>
</form>
</body>
<iframe src="${applicationScope.basePath}/images/music/null.mp3" allow="autoplay" style="display:none" id="iframeAudio"></iframe>
<script>
    window.onload = function () {
        show_me_msg();
        show_new_msg();
        show_order_msg();
        document.getElementById("li_1_a").click();
    }
    //实现多曲循环
    document.getElementById("iframeAudio").onload = function(){
        _loop();
    };
    function _loop(){
        const arr = ["${applicationScope.basePath}/images/music/null.mp3", "${applicationScope.basePath}/images/music/video/1.mp3","${applicationScope.basePath}/images/music/3.mp3","${applicationScope.basePath}/images/music/2.mp3","${applicationScope.basePath}/images/music/4.mp3","${applicationScope.basePath}/images/music/5.mp3"];//把需要播放的歌曲从后往前排，这里已添加两首音乐，可继续添加多个音乐
        let myAudio = document.getElementById("audio");
        myAudio.preload = true;
        myAudio.controls = true;
        myAudio.src = arr[Math.floor(Math.random() * 6 +1)];   //每次读数组最后一个元素
        myAudio.addEventListener('ended', playEndedHandler, false);
        myAudio.play();
        document.getElementById("music_div").appendChild(myAudio);
        myAudio.loop = false;//禁止循环，否则无法触发ended事件
        function playEndedHandler(){
            myAudio.src = arr[Math.floor(Math.random() * 6 +1)];
            myAudio.play();
            !arr.length && myAudio.removeEventListener('ended',playEndedHandler,false);//只有一个元素时解除绑定
        }
    }
    //跳转到修改密码的jsp
    function to_updatePassword() {
        <c:if test="${sessionScope.activeUserID != null}">
        document.getElementById("updatePw_form").submit();//提交表单
        </c:if>
    }
    //跳转到获取个人信息的servlet
    function to_showMSgServlet() {
        <c:if test="${sessionScope.activeUserID != null}">
        document.getElementById("updateMsg_form").submit();//提交表单
        </c:if>
    }
    //跳转到退出登录的servlet
    function to_quitSystemServlet() {
        <c:if test="${sessionScope.activeUserID != null}">
        document.getElementById("QuitSystem_form").submit();//提交表单
        </c:if>
    }
</script>
</html>
