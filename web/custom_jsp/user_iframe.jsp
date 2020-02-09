<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <style>
        #now_location{
            width: 725px;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
            background-color: #dddddd;
        }
        #now_location h3{
            margin: 8px 0 8px 15px;
            display: inline-block;
        }
        #me_msg{
            width: 725px;
            min-height: 745px;
            background-color: white;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
        }
        #me_msg ul li{
            list-style-type:none;
        }
        .person_Img{
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body style="margin:0;padding:0;overflow-x:hidden;overflow-y:auto;">
<div id="me_msg_box">
    <div id="now_location">
        <h3>首页>博主专题</h3>
    </div>
    <hr/>
    <div id="me_msg">
        <dl>
            <dt>
                <div>
                    <ul>
                        <li><a href="#"><img src="${applicationScope.basePath}/${requestScope.me.head_img}" class="person_Img"></a></li>
                        <li><label>姓名：${requestScope.me.name}</label></li>
                        <li><label>职业：${requestScope.me.job}</label></li>
                        <li><label>简介：</label></li>
                    </ul>
                </div>
            </dt>
            <dd>
                <p>
                    ${requestScope.me.introduction}
                </p>
            </dd>
        </dl>
    </div>
</div>
</body>
</html>
