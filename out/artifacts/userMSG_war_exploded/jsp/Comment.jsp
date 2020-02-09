<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <style>
        .show_p p {
            min-height: 60px;
            display: -webkit-box;
            -webkit-box-orient: vertical;
            -webkit-line-clamp: 3;
            overflow: hidden;
        }
        #click_more{
            width: 100%;
            margin: 100px 0 0 50%;
            background-color: #dddddd;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
        }
        #click_more a{
            color: #5d5d5d;
        }
        #click_more a:hover{
            color: #DB5E5E;
        }
    </style>
</head>
<body style="margin:0;padding:0;overflow-x:hidden;overflow-y:auto;">
<div id="text_box">
    <div id="selected">
        <h3>评论<span>发布|删除</span></h3>
    </div>
    <hr/>
    <div id="context_text">
        <c:forEach items="${requestScope.dynamicPage.list}" var="pageBean" varStatus="vs">
            <div class="text">
                <h3>
                    <button class="dy_${vs.count}" style="display: none;"></button>
                    <a href="#" onclick="request_all_text();return false;"
                       title="点击阅读全文" target='contextIframe'>${pageBean.title}</a></h3>
                <form id="request_all_text_form" action="${pageContext.request.contextPath}/ShowDynamicMsgServlet" target="iframe_box" method="post">
                    <input type="hidden" name="currentPage" value="${requestScope.dynamicPage.currentPage}"/>
                    <input type="hidden" name="rows" value="${requestScope.dynamicPage.rows}"/>
                    <input type="hidden" name="dynamic" value="${pageBean.id}"/>
                    <input type="hidden" name="who" value="God"/>
                </form>
                <dl>
                    <dt class="dt_img">
                        <img class="person_Img" src="${applicationScope.basePath}/${pageBean.img_0}">
                        <img class="person_Img" src="${applicationScope.basePath}/${pageBean.img_1}">
                        <img class="person_Img" src="${applicationScope.basePath}/${pageBean.img_2}">
                        <img class="person_Img" src="${applicationScope.basePath}/${pageBean.img_3}">
                        <img class="person_Img" src="${applicationScope.basePath}/${pageBean.img_4}">
                        <img class="person_Img" src="${applicationScope.basePath}/${pageBean.img_5}">
                    </dt>
                    <dd>
                        <div class="show_p">
                            <p>${pageBean.text}</p>
                        </div>
                        <p class="dd_text_2">
                            <a id="a_${vs.count}" href="javascript:;" title="点赞"
                               onclick="save(${pageBean.id},'img_${vs.count}','a_${vs.count}');return false;"
                               style="margin-right: 0;padding: 0;">
                                <img id="img_${vs.count}" class="good_img"
                                     onclick="add_good_num('good_${vs.count}','img_${vs.count}');"
                                     src="${pageContext.request.contextPath}/images/good.png">
                            </a>
                            <a id="_a_${vs.count}" href="javascript:;" title="取消点赞"
                               onclick="save(${pageBean.id},'_img_${vs.count}','_a_${vs.count}');return false;"
                               style="margin-right: 0;padding: 0;">
                                <img id="_img_${vs.count}" class="good_img"
                                     onclick="cancel_good_num('good_${vs.count}','img_${vs.count}');"
                                     src="${pageContext.request.contextPath}/images/cancelGood.png">
                            </a>
                            <span class="good" style="margin-left: 0;padding: 0;" title="点赞"
                                  id="good_${vs.count}">0</span>
                            <span class="author">${pageBean.p_name}</span>
                            <span class="createTime">${pageBean.createTime}</span>
                            <span class="readText"><a
                                    href="#" onclick="request_all_text();return false;"
                                    title="发表评论">编辑评论</a></span>
                        </p>
                    </dd>
                </dl>
            </div>
        </c:forEach>
    </div>
    <div id="click_more">
        <a href="${pageContext.request.contextPath}/ShowDynamicMsgServlet?currentPage=${requestScope.dynamicPage.currentPage - 1 > 0 ? requestScope.dynamicPage.currentPage - 1 : 1}&rows=${requestScope.dynamicPage.rows}&dynamic=0&who=comment">&laquo;&laquo;</a>
        <a href="${pageContext.request.contextPath}/ShowDynamicMsgServlet?currentPage=${requestScope.dynamicPage.currentPage + 1 <= requestScope.dynamicPage.totalPage ? requestScope.dynamicPage.currentPage + 1 : requestScope.dynamicPage.totalPage}&rows=${requestScope.dynamicPage.rows}&dynamic=0&who=comment"
           id="click_loading">点击加载更多</a>
        <a href="${pageContext.request.contextPath}/ShowDynamicMsgServlet?currentPage=${requestScope.dynamicPage.currentPage + 1 <= requestScope.dynamicPage.totalPage ? requestScope.dynamicPage.currentPage + 1 : requestScope.dynamicPage.totalPage}&rows=${requestScope.dynamicPage.rows}&dynamic=0&who=comment">&raquo;&raquo;</a>
    </div>
</div>
</body>
<script>
    window.onload = function () {
        clear_img();
        getGoodNum();
    }

    //请求全文数据
    function request_all_text() {
        let form = document.getElementById("request_all_text_form");
        form.submit();
    }
    //隐藏没有src的img
    function clear_img() {
        const img = document.getElementsByClassName("person_Img");
        for (let i = 0; i < img.length; i++) {
            if (img.item(i).src === "${applicationScope.basePath}/") {
                img.item(i).style.display = 'none';
            }
        }
    }

    //点赞
    function add_good_num(span_id, img_id) {
        const good_span = document.getElementById(span_id);
        good_span.innerText = parseInt(good_span.innerText) + 1 + '';
    }

    //取消点赞
    function cancel_good_num(span_id, img_id) {
        const good_span = document.getElementById(span_id);
        good_span.innerText = parseInt(good_span.innerText) - 1 + '';
    }

    //向ShowGoodNumServlet发送请求，获取响应数据
    function getGoodNum() {
        const xml_request = new XMLHttpRequest();
        xml_request.open("post", "${pageContext.request.contextPath}/ShowGoodNumServlet", true);
        xml_request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;charset=utf-8');
        //设置post方式的请求头
        //xml_request处理事件,异步
        xml_request.onreadystatechange = function () {
            if (xml_request.readyState === 4 && xml_request.status === 200) {
                const kv = eval("(" + xml_request.responseText + ")");
                document.getElementById("good_1").innerText = kv.num_1;
                document.getElementById("good_2").innerText = kv.num_2;
                document.getElementById("good_3").innerText = kv.num_3;
                document.getElementById("good_4").innerText = kv.num_4;
            }
        }
        let ids = '';
        <c:forEach items="${requestScope.dynamicPage.list}" var="pageBean" varStatus="vs">
        ids += '&dy_${vs.count - 1}=${pageBean.id}';
        </c:forEach>
        let info = 'user_id=${sessionScope.activeUserID}' + ids;
        //对输入的特殊字符（& ，= 等）进行编码
        xml_request.send(info);
    }

    //储存点赞信息
    function save(dy_id, img_id, a_id) {
        const a = document.getElementById(a_id);
        let msg;
        msg = 'activeUserID=-1&dy_id=' + dy_id;
        if (a.title === "点赞") {
            sendMsg("${pageContext.request.contextPath}/SaveGoodNumServlet", msg);
        }
        if (a.title === "取消点赞") {
            sendMsg("${pageContext.request.contextPath}/delGoodNumServlet", msg);
        }
    }

    //发送请求
    function sendMsg(url, msg) {
        const xml_request = new XMLHttpRequest();
        xml_request.open("post", url, true);
        xml_request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded;charset=utf-8');
        xml_request.send(msg);
    }
</script>
</html>