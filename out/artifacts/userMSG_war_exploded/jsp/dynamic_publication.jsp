<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>拖放本地图片到网页中</title>
    <style>
        #box {
            width: 450px;
            height: 300px;
            margin: 0 auto;
            border: 4px dashed #666;
        }
        #box img {
            width: 150px;
            height: 150px;
        }
        #tip_div{
            width: 640px;
            height: 480px;
            margin: 0 auto;
        }
        #tip_div span{
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
        #title_input{
           margin-left: 130px;
            height: 30px;
            width: 60%;
            border-radius: 5px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<!--目标拖动进入的区域-->
<span style="color: #2aabd2"> &#9658 动态发布</span>
<div id="tip_div">
    <input id="title_input" type="text" placeholder="标题"/><br />
    <div id="box">
    </div>
    <span id="str_num" style="margin-left: 280px">1000</span>/1000
    <div id="text">
        <textarea class="_textarea" id="_textarea" onkeyup='countChar("_textarea","str_num");' placeholder="动态文本"> </textarea>
    </div>
    <div>
        <button onclick="publish();" type="button" class="btn" style="margin-left: 90px">发布</button>
        <button type="button" class="btn" style="margin-left: 355px" onclick="cancel('_textarea','str_num');">取消</button>
    </div>
</div>
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
                    if(file[i].type.indexOf('image/') != -1 && fileArr.length < 6){
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
    function cancel(textareaName,spanName) {
        document.getElementById(spanName).innerHTML = '1000';
        document.getElementById(textareaName).value = '';
        document.getElementById("title_input").value = '';
        fileArr = [];
        while(box.hasChildNodes()) {//当div下还存在子节点时 循环继续
            box.removeChild(box.firstChild);
        }
        box.style.borderColor = '#666';
    }
    /*发布按钮的点击事件,文件上传*/
    function publish() {
        let texts = document.getElementById("_textarea").value;
        let title = document.getElementById("title_input").value;
        //创建FormData对象，进行上传
        let formData = new FormData();
        //img表示name，相当于form表单中的name属性，file[0]表示要上传的单个文件
        for(let i = 0; i < fileArr.length; i++){
            formData.append("img_" + i,fileArr[i]);
        }
        //其他的数据，也可以进行传输
        formData.append("text",texts);
        formData.append("title",title);
        //文件上传必须通过正文方式进行传递，所以必须使用post请求
        //使用FormData时会自动对content-type进行设置，就不需要再进行手动设置
        let xml_request = new XMLHttpRequest();
        xml_request.open("post","${pageContext.request.contextPath}/SaveDynamicMsgServlet",true);
        // xml_request.onload = function(){
        //     console.log(xml_request.responseText);
        // };
        xml_request.send(formData);
        cancel('_textarea','str_num');
        alert("上传成功");
    }
</script>
</html>