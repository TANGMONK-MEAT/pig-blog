<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core_1_1" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <style>
        #area {
            border-style: solid;
            border-color: white;
            border-width: 1px;
            border-radius: 5px;
            width: 725px;
            height: 477px;
            resize: none;
            background-color: white;
            font-size: 30px;
        }

        .now_location {
            width: 725px;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
            background-color: #dddddd;
        }

        .now_location h3 {
            margin: 7px 0 6px 15px;
            display: inline-block;
        }

        #div_button {
            margin: 8px 15px 8px 0;
            float: right;
        }

        #str_num {
            margin-left: 210px;
        }
        hr{
            background-color: #fff;
            border-top: 1px dashed #4ec5ff;
            margin-top: 0;
            margin-bottom: 0;
            width: 725px;
        }
        #_label{
            color: #db5e5e;
            margin-left: 100px;
        }
    </style>
</head>
<body style="margin:0;padding:0;overflow-x:hidden;overflow-y:auto;">
<div id="messageBoard_box">
    <c:if test="${requestScope.save_msg != null}">
    </c:if>
    <div class="now_location">
        <h3>首页>留言板</h3>
        <span id="str_num">530</span>/530
        <label id="_label">${requestScope.save_msg}</label>
        <div id="div_button">
            <input form="messageBoard_form" type="button" id="sub_btn" onclick="javascript:checkNull();" value="留言">
            <input form="messageBoard_form" onclick="renum('str_num');" type="reset" value="重置">
        </div>
    </div>
    <hr/>
    <div id="div_form">
        <form id="messageBoard_form" action="${pageContext.request.contextPath}/saveMessageBoardServlet" method="post">
            <textarea onkeyup='countChar("area","str_num");' form="messageBoard_form" id="area" name="text" autofocus cols="20"
                      rows="20" maxlength="530" wrap="hard" placeholder="想对博主说啥？"></textarea>
            <input type="text" name="activeUserID" value="${sessionScope.activeUserID}" style="display: none;"/>
        </form>
    </div>
</div>
<script>
    function checkNull() {
        let area = document.getElementById("area");
        <c:if test="${sessionScope.activeUserID == null}">
            alert("请先登录");
        </c:if>
        <c:if test="${sessionScope.activeUserID != null}">
            if(area.value == ''){
                alert("请先留言！");
                return false;
            }else{
                let sub_btn = document.getElementById("sub_btn");
                sub_btn.type = 'submit';
                return true;
            }
        </c:if>
    }
    function countChar(textareaName, spanName) {
        document.getElementById(spanName).innerHTML = 530 - document.getElementById(textareaName).value.length;
    }
    function renum(spanName) {
        document.getElementById(spanName).innerHTML = 530;
    }
</script>
</body>
</html>