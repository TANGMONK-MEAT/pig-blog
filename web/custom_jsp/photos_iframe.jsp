<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css"/>
    <style>
        #photos{
            width: 725px;
            min-height: 741px;
            background-color: white;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
        }
        #photos img{
            max-height:140px;
            max-width: 140px;
            vertical-align:middle;
            margin-top: 5px;
        }
        #now_location{
            width: 725px;
            border-style: solid;
            border-color: #dddddd;
            border-width: 1px;
            border-radius: 5px;
            background-color: #dddddd;
        }
        #now_location h3{
            margin: 8px 5px 8px 15px;
            display: inline-block;
        }
    </style>
</head>
<body style="margin:0;padding:0;overflow-x:hidden;overflow-y:auto;">
<div id="photos_box">
    <div id="now_location">
        <h3>首页>留言板</h3>
    </div>
    <hr/>
    <div id="photos">
        <c:forEach items="${requestScope.imgList}" var="imgURL" varStatus="vs">
            <img class="image" src="${applicationScope.basePath}/${imgURL}">
        </c:forEach>
    </div>
</div>
</body>
</html>
