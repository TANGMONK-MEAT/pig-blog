//长度必须在6~20位之间
//开头不能为数字
//只能包含小写字母和数字
//数字：48~57
//小写字母：97~122
//innerHTML
function check_tip(){
    var check = document.getElementById("check");
    var check_Error = document.getElementById("check_input_error_box");
    if(check.value.length == 0){
        check_Error.innerHTML = "请输入验证码";
    }
}
function username_tip(){
    var oUname = document.getElementById("username");
    var username_Error = document.getElementById("username_input_error_box");
    if(oUname.value.length > 20 || oUname.value.length < 6){
        username_Error.innerHTML = "用户名长度必须在6~20位之间";

    }
    if(oUname.value.charCodeAt(0) >= 48 && oUname.value.charCodeAt(0) <= 57){
        username_Error.innerHTML = "用户名开头不能为数字";
    }
    else{
        for(var i=0; i<oUname.value.length; i++){
            if((oUname.value.charCodeAt(i) > 122 || oUname.value.charCodeAt(i) < 97) && (oUname.value.charCodeAt(i) > 57 || oUname.value.charCodeAt(i) < 48)){
                username_Error.innerHTML = "用户名只能包含小写字母和数字";
            }
        }
    }
}
function password_tip(){
    var oUpass = document.getElementById("password");
    var password_Error = document.getElementById("password_input_error_box");
    if(oUpass.value.length > 20 || oUpass.value.length < 6){
        password_Error.innerHTML = "密码长度必须在6~20位之间";
    }
}
