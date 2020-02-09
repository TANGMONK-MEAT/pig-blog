<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <style>
        #read_all_text_box {

        }

        #text {
            width: 725px;
            min-height: 741px;
            background-color: white;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
        }

        #text p {
            font-size: 18px;
            font-family: "楷体", "仿宋", sans-serif;
            font-style: normal;
            font-weight: bold;
        }

        #now_location {
            width: 725px;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
            background-color: #dddddd;
        }

        #now_location h3 {
            margin: 8px 5px 8px 0;
            display: inline-block;
        }

        .person_Img {
            width: 90px;
            height: 90px;
        }

        #div_right {
            margin: 8px 0 8px 0;
            float: right;
        }

        #div_right span {
            margin-right: 20px;
        }

        .answer_div {
            width: 90%;
            margin: 0 auto 20px;
            border: none;
            /*visibility: hidden;*/
            /*display: none;*/
        }

        ._textarea {
            border-style: solid;
            border-color: #dddddd;
            border-width: 2px;
            border-radius: 5px;
            width: 100%;
            min-height: 150px;
            resize: none;
            background-color: white;
            font-size: 15px;
        }

        .answer_btn {
            margin-left: 650px;
            text-align: center;
            cursor: pointer;
            border-color: #dddddd;
            border-width: 2px;
            border-radius: 5px;
            width: 50px;
            height: 30px;
            visibility: hidden;
            display: none;
        }

        .sub_btn {
            cursor: pointer;
            border-color: #dddddd;
            border-width: 2px;
            border-radius: 5px;
            width: 50px;
            height: 30px;
        }

        .cancel {
            cursor: pointer;
            border-color: #dddddd;
            border-width: 2px;
            border-radius: 5px;
            width: 50px;
            height: 30px;
            float: right;
        }

        hr {
            border: 0;
            border-top: 1px dashed #222;
        }

        #comment_box {
            max-width: 710px;
        }

        #comment_box p {
            font-size: 14px;
            font-family: "楷体", "仿宋", sans-serif;
            font-style: normal;
        }

        #num_div {
            /*margin-left: 210px;*/
            float: right;
            color: #db5e5e;
        }
    </style>
</head>
<body style="margin:0;padding:0;overflow-x:hidden;overflow-y:auto;">
<div id="read_all_text_box">
    <div id="now_location">
        <h3>首页>阅读全文>${requestScope.dynamicBean.title}</h3>
        <div id="div_right">
            <span class="author">作者:${requestScope.dynamicBean.p_name}</span>
            <span class="createTime">日期:${requestScope.dynamicBean.createTime}</span>
            <span class="readText"><a
                    href="${pageContext.request.contextPath}/ShowDynamicMsgServlet?currentPage=${requestScope.currentPage}&rows=${requestScope.rows}&dynamic=0"
                    target="contextIframe"
                    title="返回动态列表">返回</a></span>
        </div>
    </div>
    <hr/>
    <div id="text">
        <dl>
            <dt class="dt_img">
                <img class="person_Img" src="${applicationScope.basePath}/${requestScope.dynamicBean.img_0}">
                <img class="person_Img" src="${applicationScope.basePath}/${requestScope.dynamicBean.img_1}">
                <img class="person_Img" src="${applicationScope.basePath}/${requestScope.dynamicBean.img_2}">
                <img class="person_Img" src="${applicationScope.basePath}/${requestScope.dynamicBean.img_3}">
                <img class="person_Img" src="${applicationScope.basePath}/${requestScope.dynamicBean.img_4}">
                <img class="person_Img" src="${applicationScope.basePath}/${requestScope.dynamicBean.img_5}">
            </dt>
            <dd>
                <p>
                    ${requestScope.dynamicBean.text}
                </p>
            </dd>
        </dl>
        <!--   用于回复-->
        <div class="answer_div" id="answer_area">
            <div id="num_div"><span id="str_num">530</span>/530</div>
            <textarea onkeyup='countChar("_textarea","str_num");' class="_textarea" id="_textarea" wrap="hard"
                      placeholder="想对作者说点啥？" autofocus> </textarea>
            <div>
                <button type="button" class="sub_btn" id="sub_btn">评论</button>
                <button onclick="javascript:hide();" type="button" class="cancel">取消</button>
            </div>
        </div>
        <button class="answer_btn" id="answer_btn">评论</button>
        <div id="comment_box">
            <%-- 评论区 --%>
            <c:forEach items="${requestScope.comList}" var="msgBean" varStatus="vs">
                <div class=\"comment\">
                    <dl>
                        <dt>
                            <label class=\"fans_name\">${msgBean.name}</label>
                            <time style="margin-left: 20px;" class="send_time">${msgBean.createTime}</time>
                        </dt>
                        <dd class=\"comment_text\">
                            <p class=\"_text\">${msgBean.text}</p>
                        </dd>
                    </dl>
                    <hr/>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%-- 隐藏表单--%>
<form id="_request" action="${pageContext.request.contextPath}/ShowDynamicMsgServlet" method="post">
    <input type="hidden" name="dynamic" value="${requestScope.dynamicBean.id}"/>
    <input type="hidden" name="currentPage" value="${requestScope.currentPage}"/>
    <input type="hidden" name="rows" value="${requestScope.rows}"/>
</form>
<script type="text/javascript">
    //页面加载触发
    window.onload = function () {
        hide_img();
    }
    //隐藏没有src的img
    function hide_img() {
        const img = document.getElementsByClassName("person_Img");
        for (let i = 0; i < img.length; i++) {
            if (img.item(i).src === "${applicationScope.basePath}/") {
                img.item(i).style.display = 'none';
            }
        }
    }

    //显示文本域，隐藏“评论”按钮
    document.getElementById("answer_btn").onclick = function () {
        const btn = document.getElementById("answer_btn").style.visibility = 'hidden';
        const area = document.getElementById('answer_area');
        area.style.display = 'block';
        area.style.visibility = 'visible';
    }
    //评论按钮的点击事件
    document.getElementById("sub_btn").onclick = function () {
        <c:if test="${sessionScope.activeUserID != null}">
            createCommentDiv();
            submit_comment();
        </c:if>
        <c:if test="${sessionScope.activeUserID == null}">
             alert("请先登录");
        </c:if>
    }

    //隐藏文本域，显示“评论”按钮
    function hide() {
        const area = document.getElementById('answer_area');
        area.style.display = 'none';
        area.style.visibility = 'hidden';
        document.getElementById("answer_btn").style.visibility = 'visible';
        document.getElementById("answer_btn").style.display = 'block';
        document.getElementById("_textarea").value = '';
        document.getElementById("str_num").innerHTML = 530;
    }

    //动态添加评论信息
    function createCommentDiv() {
        const parentDIV = document.getElementById("comment_box");
        let str = "            <div class=\"comment\">\n" +
            "                <dl>\n" +
            "                    <dt>\n" +
            "                        <label class=\"fans_name\">name</label>\n" +
            "                        <time style=\"margin-left: 20px;\" class=\"send_time\">time</time>\n" +
            "                    </dt>\n" +
            "                    <dd class=\"comment_text\">\n" +
            "                        <p class=\"_text\">\n" +
            "                        text</p>\n" +
            "                    </dd>\n" +
            "                </dl>\n" +
            "                <hr/>\n" +
            "            </div>";

        parentDIV.innerHTML = str + parentDIV.innerHTML;
        let _text = document.getElementById("_textarea").value;//获取文本域内容
        document.getElementsByClassName("_text").item(0).innerHTML = _text;//将内容填充到评论区
        let _time = document.getElementsByClassName("send_time").item(0);//获取time标签
        _time.innerText = showTime();
        _time.dateTime = showTime();
        _time.title = showTime();
    }

    //用户限制评论字数
    function countChar(textareaName, spanName) {
        document.getElementById(spanName).innerHTML = 530 - document.getElementById(textareaName).value.length;
    }

    function renum(spanName) {
        document.getElementById(spanName).innerHTML = 530;
    }

    //利用ajax，上传评论信息
    function submit_comment() {
        let xml_request = new XMLHttpRequest();
        xml_request.open('post', "${pageContext.request.contextPath}/SaveCommentServlet", true);
        xml_request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;charset=utf-8');
        let text = document.getElementById("_textarea").value;
        let msg = "dy_id=${requestScope.dynamicBean.id}&user_id=${sessionScope.activeUserID}&text=" + text;
        xml_request.send(msg);
    }
    //发送请求
    function _request() {
        document.getElementById("_request").submit();//提交表单
    }
    //定时请求获取最新评论
    self.setInterval(function () {
        _request()
    },40000);
</script>
</body>
</html>
