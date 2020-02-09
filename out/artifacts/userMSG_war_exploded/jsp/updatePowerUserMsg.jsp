<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<!-- 指定字符集 -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>修改用户</title>

<!-- 1. 导入CSS的全局样式 -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<!-- 2. jQuery导入，建议使用1.9以上的版本 -->
<script src="${pageContext.request.contextPath}/jquery-2.1.0.min.js"></script>
<!-- 3. 导入bootstrap的js文件 -->
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
    function update() {
        if (confirm("你确定要修改吗？")) {
            document.getElementById("updateForm").submit();
        }
    }
</script>
<head>
    <meta charset="UTF-8">
    <title>拖放本地图片到网页中</title>
    <style>
        #box {
            width: 500px;
            height: 300px;
            margin: 0 auto;
            border: 4px dashed #666;
        }

        #box img {
            max-width: 500px;
            max-height: 300px;
        }

        #tip_div {
            margin: auto;
            width: 640px;
            height: 480px;
        }

        #tip_div span {
            margin-left: 88px;
        }

        .btn {
            cursor: pointer;
            border-color: #dddddd;
            border-width: 2px;
            border-radius: 5px;
            width: 50px;
            height: 30px;
        }

        ._textarea {
            margin-left: 90px;
            maxlength: 1000;
            border-style: solid;
            border-color: #dddddd;
            border-width: 2px;
            border-radius: 5px;
            width: 71%;
            min-height: 150px;
            resize: none;
            background-color: white;
            font-size: 15px;
        }

        #title_input {
            margin-left: 130px;
            height: 30px;
            width: 60%;
            border-radius: 5px;
            margin-bottom: 15px;
        }

        #updateForm {
            max-width: 100%;
            margin-left: 50px;
        }
    </style>
</head>
<body>
<!--目标拖动进入的区域-->
<span style="color: #2aabd2"> &#9658 修改我的信息</span>
<div id="tip_div">
    <form class="form-horizontal" id="updateForm">
        <!--  隐藏域 提交id-->
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" name="name" value="${requestScope.user.name}"
                   placeholder="请输入姓名"/>
        </div>
        <div class="form-group">
            <label>性别：</label>
            <c:if test="${requestScope.user.sex=='男'}">
                <input type="radio" id="sex" value="男" checked/>男
                <input type="radio" id="sex" value="女"/>女
            </c:if>
            <c:if test="${requestScope.user.sex=='女'}">
                <input type="radio" id="sex" value="男"/>男
                <input type="radio" id="sex" value="女" checked/>女
            </c:if>
        </div>
        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" name="age" value="${requestScope.user.age}"
                   placeholder="请输入年龄"/>
        </div>
        <div class="form-group">
            <label for="phone">电话：</label>
            <input type="text" id="phone" class="form-control" name="phone" value="${requestScope.user.phone}"
                   placeholder="请输入手机号码"/>
        </div>
        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" id="email" class="form-control" name="email" value="${requestScope.user.email}"
                   placeholder="请输入邮箱地址"/>
        </div>
        <div class="form-group">
            <label for="job">job：</label>
            <input type="text" id="job" class="form-control" name="job" value="${requestScope.user.job}"
                   placeholder="请输入职业"/>
        </div>
        <div class="form-group">
            <label for="motto">motto：</label>
            <input type="text" id="motto" class="form-control" name="motto" value="${requestScope.user.motto}"
                   placeholder="请输入座右铭"/>
        </div>
        <div class="form-group">
            <label for="username">username：</label>
            <input type="text" id="username" class="form-control" name="username" value="${requestScope.user.username}"
                   placeholder="请输入账号"/>
        </div>
        <div class="form-group">
            <label for="password">password：</label>
            <input type="text" id="password" class="form-control" name="password" value="${requestScope.user.password}"
                   placeholder="请输入密码"/>
        </div>
    </form>
    <div id="box">
    </div>
    <span id="str_num" style="margin-left: 280px">1000</span>/1000
    <div id="text">
        <textarea class="_textarea" id="_textarea" onkeyup='countChar("_textarea","str_num");'
                  placeholder="动态文本"> </textarea>
    </div>
    <div>
        <button onclick="publish();" type="button" class="btn" style="margin-left: 90px">发布</button>
        <button type="button" class="btn" style="margin-left: 355px" onclick="cancel('_textarea','str_num');">取消
        </button>
    </div>
</div>
<div style="clear:both"></div>
</body>
<script language="JavaScript">
    let box;
    let fileArr = [];
    //窗体加载时函数执行
    window.onload = function () {
        box = document.getElementById("box");
        /*拖拽图片进入box触发的事件*/
        box.ondragenter = function (e) {
            // 拖拽目标进入时变色0
            this.style.borderColor = 'red';
        }
        /*拖拽图片离开box触发的事件*/
        box.ondragleave = function (e) {
            this.style.borderColor = '#666';
        }
        /*拖拽图片在box中移动时，触发的事件*/
        box.ondragover = function (e) {
            e.stopPropagation();
            e.preventDefault();
        }
        /*放置图片时*/
        box.ondrop = function (e) {
            e.stopPropagation();
            e.preventDefault();
            if(box.childElementCount >= 6){
                alert("图像文件个数不可以超过6个");
                return;
            }
            /*返回拖拽的文件数组*/
            let file = e.dataTransfer.files;
            if(file.length > 0 && file.length <= 6){
                for(let i = 0;i < 6;i++){
                    if(file[i].type.indexOf('image/') !== -1 && fileArr.length < 6){
                        fileArr[fileArr.length] = file[i];
                        let now_file = new FileReader();
                        now_file.readAsDataURL(file[i]);
                        now_file.onload = function (e) {
                            //转义字符
                            box.innerHTML += "<img src=\"" + now_file.result + "\" />";
                        }
                    }else{
                        alert("请确保文件为图像文件且个数不超过6个");
                        break;
                    }
                }
            }else{
                alert('请确保图片个数不超过6个');
            }
        }
    }

    /*记录文本域的剩余可输入字符数*/
    function countChar(textareaName, spanName) {
        document.getElementById(spanName).innerHTML = 1000 - document.getElementById(textareaName).value.length;
    }

    /*取消按钮的点击事件*/
    function cancel(textareaName, spanName) {
        document.getElementById(spanName).innerHTML = '1000';
        document.getElementById(textareaName).value = '';
        document.getElementById("title_input").value = '';
        while (box.hasChildNodes()) {//当div下还存在子节点时 循环继续
            box.removeChild(box.firstChild);
        }
        box.style.borderColor = '#666';
    }

    /*发布按钮的点击事件,文件上传*/
    function publish() {
        if(fileArr.length === 0){
            alert('请确保有图片');
            return false;
        }
        let texts = document.getElementById("_textarea").value;
        let name = document.getElementById("name").value;
        let sex = document.getElementById("sex").value;
        let age = document.getElementById("age").value;
        let phone = document.getElementById("phone").value;
        let email = document.getElementById("email").value;
        let username = document.getElementById("username").value;
        let password = document.getElementById("password").value;
        let job = document.getElementById("job").value;
        let motto = document.getElementById("motto").value;

        //创建FormData对象，进行上传
        let formData = new FormData();
        //img表示name，相当于form表单中的name属性，file[0]表示要上传的单个文件
        for(let i = 0; i < fileArr.length; i++){
            formData.append("img_" + i,fileArr[i]);
        }
        //其他的数据，也可以进行传输
        formData.append("introduction",texts);
        formData.append("id",'${requestScope.user.id}');
        formData.append("name",name);
        formData.append("sex",sex);
        formData.append("age",age);
        formData.append("phone",phone);
        formData.append("email",email);
        formData.append("username",username);
        formData.append("password",password);
        formData.append("job",job);
        formData.append("motto",motto);
        //文件上传必须通过正文方式进行传递，所以必须使用post请求
        //使用FormData时会自动对content-type进行设置，就不需要再进行手动设置
        let xml_request = new XMLHttpRequest();
        xml_request.open("post","${pageContext.request.contextPath}/SetPowerUserMsgServlet",true);
        xml_request.send(formData);
        cancel('_textarea','str_num');
        alert("上传成功");
    }
</script>
</html>