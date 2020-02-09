<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<!-- 网页使用的语言 -->--%>
<html lang="zh-CN">
<head>
    <%--        <!-- 指定字符集 -->--%>
    <meta charset="utf-8">
    <%--        <!-- 使用Edge最新的浏览器的渲染方式 -->--%>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <%--    &lt;%&ndash;    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。&ndash;%&gt;--%>
    <%--    &lt;%&ndash;    width: 默认宽度与设备的宽度相同&ndash;%&gt;--%>
    <%--    &lt;%&ndash;    initial-scale: 初始的缩放比，为1:1 -->&ndash;%&gt;--%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%--    &lt;%&ndash;    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->&ndash;%&gt;--%>
    <%--        <title>用户信息管理系统</title>--%>

    <%--    &lt;%&ndash;    <!-- 1. 导入CSS的全局样式 -->&ndash;%&gt;--%>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <%--    &lt;%&ndash;    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->&ndash;%&gt;--%>
    <script src="${pageContext.request.contextPath}/js/jquery-2.1.0.min.js"></script>
    <%--    <!-- 3. 导入bootstrap的js文件 -->--%>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <style type="text/css">
        <%--        设置表格的内容不会自动换行--%>
        th, td, th {
            text-align: center;
            word-break: keep-all; /* 不换行 */
            white-space: nowrap; /* 不换行 */
            overflow: hidden; /* 内容超出宽度时隐藏超出部分的内容 */
            text-overflow: ellipsis; /* 当对象内文本溢出时显示省略标记(...) ；需与overflow:hidden;一起使用。*/
            max-width: 100px;
        }
        th{
            background-color: #0ab4ff;
        }
    </style>
    <script type="text/javascript">
        function deleteUser(id) {
            //用户安全提示
            if (confirm("你确定要删除吗？")) {
                //访问路径
                location.href = "${pageContext.request.contextPath}/DeleteUserServlet?id=" + id + "&activeUserID=${sessionScope.activeUserID}";
            }
        }

        function findUser(id) {
            location.href = "${pageContext.request.contextPath}/FindUserServlet?id=" + id + "&activeUserID=${sessionScope.activeUserID}";
        }

        function findAll() {
            location.href = "${pageContext.request.contextPath}/FindUserByPageServlet?activeUserID=${sessionScope.activeUserID}";
        }

        window.onload = function () {
            //给删除选中添加单击事件
            document.getElementById("deleteSelected").onclick = function () {
                if (confirm("你确定要删除选中吗？")) {
                    //标志位
                    let flag = false;
                    //获取所有复选框对象
                    const checkboxes = document.getElementsByName("ids");
                    //遍历，有一个选中就跳出
                    for (let i = 0; i < checkboxes.length; i++) {
                        if (checkboxes[i].checked) {
                            //复选框被选中
                            flag = true;
                            break;
                        }
                    }
                    if (flag) {
                        //表单提交
                        document.getElementById("deleteForm").submit();
                    }
                }
            }
            //获取表格的中第一个checkBox,绑定单击事件
            document.getElementById("selectAll").onclick = function () {
                //获取下边列表中的所有的checkbox
                const checkboxes = document.getElementsByName("ids");
                //遍历
                for (let i = 0; i < checkboxes.length; i++) {
                    //设置这些checkbox的选中状态
                    checkboxes[i].checked = this.checked;
                }
            }
        }
    </script>
</head>
<body>
<span style="color: #2aabd2"> &#9658粉丝信息管理 </span>
<div class="container">
    <div style="width: 1200px;margin-top: 10px;">
        <c:if test="${requestScope.userPage != null}">
        <div style="float: left;width: 80%;">
            <form class="form-inline"
                  action="${pageContext.request.contextPath}/FindUserByPageServlet?find=1&activeUserID=${sessionScope.activeUserID}"
                  method="post">
                <div class="form-group">
                    <label for="exampleInputName2">昵称</label>
                    <input type="text" class="form-control" name="name" id="exampleInputName2"
                           value="${requestScope.condition.name[0]}" placeholder="">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail2">籍贯</label>
                    <input type="Address" class="form-control" name="address" id="exampleInputAddress2"
                           value="${requestScope.condition.address[0]}" placeholder="">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail2">邮箱</label>
                    <input type="text" class="form-control" name="email" id="exampleInputEmail2"
                           value="${requestScope.condition.email[0]}" placeholder="">
                </div>
                <button type="submit" class="btn btn-default">条件查询</button>
                <td><a class="btn btn-default" href="javascript:findAll()">查询所有</a>&nbsp;
            </form>
        </div>
        <div style="float: right;">
            <a class="btn btn-primary"
               href="${pageContext.request.contextPath}/jsp/add.jsp?activeUserID=${sessionScope.activeUserID}">添加粉丝</a>
            <a class="btn btn-primary" href="javascript:void(0);" id="deleteSelected">删除选中</a>
        </div>
        <div style="clear: both;"></div>
    </div>
    <form id="deleteForm"
          action="${pageContext.request.contextPath}/DeleteSelectedServlet?activeUserID=${sessionScope.activeUserID}"
          method="post" style="width: 1200px">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="selectAll"></th>
                <th>编号</th>
                <th>昵称</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>电话</th>
                <th>邮箱</th>
                <th>账号</th>
                <th>密码</th>
                <th>注册时间</th>
                <th>修改时间</th>
                <th>操作</th>
            </tr>
                <%--        循环获取list集合中的用户对象，将用户地信息展示到表格中--%>
            <c:forEach items="${requestScope.userPage.list}" var="user" varStatus="s">
                <tr>
                    <td><input type="checkbox" name="ids" value="${user.id}"></td>
                    <td title="${((requestScope.userPage.currentPage - 1) * requestScope.userPage.rows) + s.count}">${((requestScope.userPage.currentPage - 1) * requestScope.userPage.rows) + s.count}</td>
                    <td title="${user.name}">${user.name}</td>
                    <td title="${user.sex}">${user.sex}</td>
                    <td title="${user.age}">${user.age}</td>
                    <td title="${user.address}">${user.address}</td>
                    <td title="${user.phone}">${user.phone}</td>
                    <td title="${user.email}">${user.email}</td>
                    <td title="${user.username}">${user.username}</td>
                    <td title="${user.password}">${user.password}</td>
                    <td title="${user.createTime}">${user.createTime}</td>
                    <td title="${user.updateTime}">${user.updateTime}</td>
                    <td><a class="btn btn-default btn-sm" href="javascript:findUser(${user.id})">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a></td>
                </tr>
            </c:forEach>
        </table>
    </form>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${requestScope.userPage.currentPage == 1}">
                    <li class="disabled"><a href="javascript:return false;" style="opacity: 0.3;"><span
                            aria-hidden="true">&laquo;</span></a></li>
                </c:if>
                <c:if test="${requestScope.userPage.currentPage != 1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${requestScope.userPage.currentPage - 1 > 0 ? requestScope.userPage.currentPage - 1 : 1}&rows=${requestScope.userPage.rows}&activeUserID=${sessionScope.activeUserID}"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <c:forEach begin="1" end="${requestScope.userPage.totalPage}" var="i">
                    <c:if test="${requestScope.userPage.currentPage == i}">
                        <li class="active">
                            <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=${requestScope.userPage.rows}&activeUserID=${sessionScope.activeUserID}">${i}</a>
                        </li>
                    </c:if>
                    <c:if test="${requestScope.userPage.currentPage != i}">
                        <li>
                            <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=${requestScope.userPage.rows}&activeUserID=${sessionScope.activeUserID}">${i}</a>
                        </li>
                    </c:if>
                </c:forEach>

                <c:if test="${requestScope.userPage.currentPage == requestScope.userPage.totalPage}">
                    <li class="disabled"><a href="javascript:return false;" style="opacity: 0.3;"><span
                            aria-hidden="true">&raquo;</span></a></li>
                </c:if>
                <c:if test="${requestScope.userPage.currentPage != requestScope.userPage.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${requestScope.userPage.currentPage + 1 <= requestScope.userPage.totalPage ? requestScope.userPage.currentPage + 1 : requestScope.userPage.totalPage}&rows=${requestScope.userPage.rows}&activeUserID=${sessionScope.activeUserID}"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <span style="font-size: 20px;margin-left: 5px;">
                    有${requestScope.userPage.totalCount}条记录，共${requestScope.userPage.totalPage}页
                </span>
            </ul>
        </nav>
    </div>
    </c:if>
</div>
</body>
</html>

