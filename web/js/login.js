//���ȱ�����6~20λ֮��
//��ͷ����Ϊ����
//ֻ�ܰ���Сд��ĸ������
//���֣�48~57
//Сд��ĸ��97~122
//innerHTML
function check_tip(){
    var check = document.getElementById("check");
    var check_Error = document.getElementById("check_input_error_box");
    if(check.value.length == 0){
        check_Error.innerHTML = "��������֤��";
    }
}
function username_tip(){
    var oUname = document.getElementById("username");
    var username_Error = document.getElementById("username_input_error_box");
    if(oUname.value.length > 20 || oUname.value.length < 6){
        username_Error.innerHTML = "�û������ȱ�����6~20λ֮��";

    }
    if(oUname.value.charCodeAt(0) >= 48 && oUname.value.charCodeAt(0) <= 57){
        username_Error.innerHTML = "�û�����ͷ����Ϊ����";
    }
    else{
        for(var i=0; i<oUname.value.length; i++){
            if((oUname.value.charCodeAt(i) > 122 || oUname.value.charCodeAt(i) < 97) && (oUname.value.charCodeAt(i) > 57 || oUname.value.charCodeAt(i) < 48)){
                username_Error.innerHTML = "�û���ֻ�ܰ���Сд��ĸ������";
            }
        }
    }
}
function password_tip(){
    var oUpass = document.getElementById("password");
    var password_Error = document.getElementById("password_input_error_box");
    if(oUpass.value.length > 20 || oUpass.value.length < 6){
        password_Error.innerHTML = "���볤�ȱ�����6~20λ֮��";
    }
}
