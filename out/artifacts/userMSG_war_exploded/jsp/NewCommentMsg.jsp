<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <style>
        #read_all_text_box{
            width: 100%;
            min-height: 741px;
        }
        #text {
            width: 100%;
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
            width: 100%;
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

        .hr {
            border: 0;
            border-top: 1px dashed #222;
        }

        #comment_box {
            width: 100%;
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
        <h3>最新评论</h3>
    </div>
    <div id="text">
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
            <c:forEach items="${requestScope.NewCommentList}" var="msgBean" varStatus="vs">
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
                    <hr class="hr"/>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script type="text/javascript">

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
            "                        <label class=\"fans_name\">唐僧肉（博主）</label>\n" +
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
</script>
</body>
</html>

