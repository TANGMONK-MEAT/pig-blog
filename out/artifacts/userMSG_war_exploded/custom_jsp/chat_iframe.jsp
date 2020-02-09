<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <script src="${pageContext.request.contextPath}/js/index.js"></script>
    <style>
        #chat_container {
            width: 730px;
            background-color: #F5F5F5;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
        }

        .now_location {
            width: 730px;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
            background-color: #dddddd;
        }

        .now_location h3 {
            margin: 8px 0 6px 15px;
            display: inline-block;
        }

        .chat_div {
            width: 90%;
            margin: 0 auto 20px;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
        }

        .chat_header {
            background-color: #dddddd;
        }

        .chat_header time {
            float: right;
        }

        .chat_header a {
            color: #4ec5ff;
        }

        #div_box{
            min-height: 727px;
        }
        .chat_header a:hover {
            color: darkred;
            margin-left: 20px;
            transition: all 0.5s;
            -webkit-transition: all 0.5s;
            -moz-transition: all 0.5s;
            -o-transition: all 0.5s;
        }



        .answer_div {
            width: 90%;
            margin: 0 auto 20px;
            border: none;
            visibility: hidden;
            display: none;
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
            margin-left: 645px;
            text-align: center;
            cursor: pointer;
            border-color: #dddddd;
            border-width: 2px;
            border-radius: 5px;
            width: 50px;
            height: 30px;
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
        #reset_btn{
            float: right;
            margin-right: 15px;
        }
    </style>
</head>
<body style="margin:0;padding:0;overflow-x:hidden;overflow-y:auto;">
<div id="chat_container">
    <div class="now_location">
        <h3>首页>聊天室</h3>
        <button id="reset_btn" class="sub_btn" onclick="reset();">清空</button>
    </div>
    <hr/>
    <div id="div_box">
        <ul>
            <div id="chat_box">
                <!--    用于查看-->
            </div>
            <!--   用于回复-->
            <div class="answer_div" id="answer_area">
                <textarea class="_textarea" id="_textarea" ></textarea>
                <div>
                    <button onclick="javascript:createDIV();" type="button" class="sub_btn">回复</button>
                    <button onclick="javascript:hide();" type="button" class="cancel">取消</button>
                </div>
            </div>
        </ul>
        <button class="answer_btn" id="answer_btn">回复</button>
    </div>
</div>
</body>
<script>
    document.getElementById("answer_btn").onclick = function () {
        const btn = document.getElementById("answer_btn").style.visibility = 'hidden';
        const area = document.getElementById('answer_area');
        area.style.display = 'block';
        area.style.visibility = 'visible';
    }

    function hide() {
        const area = document.getElementById('answer_area');
        area.style.display = 'none';
        area.style.visibility = 'hidden';
        document.getElementById("answer_btn").style.visibility = 'visible';
        document.getElementById("_textarea").value = '';

    }

    function createDIV(name, texts) {
        const parentDIV = document.getElementById("chat_box");
        const str = "          <div class=\"chat_div\" id=\"chat_div\">\n" +
            "                <li class=\"chat_li\" id=\"chat_li\">\n" +
            "                    <div class=\"chat_header\" id=\"chat_header\">\n" +
            "                        <a class=\"fans_msg\" href=\"#\" id=\"fans_msg\">${sessionScope.activeUserName}</a>\n" +
            "                        <time  title=\"2014年8月31日 下午3:20\" datetime=\"2014-08-31T03:54:20\" class=\"sendTime\">\n" +
            "                            \n" +
            "                        </time>\n" +
            "                    </div>\n" +
            "                    <div class=\"send_text\" id=\"send_text\">\n" +
            "                        <p class=\"main_text\">\n" +
            "                            \n" +
            "                        </p>\n" +
            "                    </div>\n" +
            "                </li>\n" +
            "            </div>";
        parentDIV.innerHTML += str;
        let value = document.getElementById("_textarea").value;
        document.getElementsByClassName("main_text").item(document.getElementsByClassName("main_text").length - 1).innerHTML = value;
        let timeElem = document.getElementsByClassName("sendTime").item(document.getElementsByClassName("sendTime").length - 1);
        timeElem.innerText = showTime();
        timeElem.dateTime = showTime();
        timeElem.title = showTime();
    }

    // function showTime() {
    //     const date = new Date();
    //     let year = date.getFullYear();
    //     let month = date.getMonth();
    //     let day = date.getDay();
    //     let hours = date.getHours();
    //     let minutes = date.getMinutes();
    //     let seconds = date.getSeconds();
    //     const timeStr = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
    //     return timeStr;
    // }
    function reset() {
        let box = document.getElementById("chat_box");
        box.innerHTML = '';
        box.value = '';
        box.empty();
    }
</script>
</html>
