//����ָ��id��Ԫ��
// const id = function (id) {
//     return document.getElementById(id);
// };

function $(id) {
    return document.getElementById(id)
}

function setSelected(id) {
    for (let i = 1; i < 6; i++) {
        $('li_' + i).style.display = 'block';
        $('li_' + i).style.color = '';
        $('li_' + i).style.background = '';
    }
    $(id).style.display = 'block';
    $(id).style.color = '#fbffff';
    $(id).style.background = '#dddddd';

    $('contextIframe').src = ($(id).childNodes)[0].href;
}


function showTime() {
    const date = new Date();
    let year = date.getFullYear();
    let month = date.getMonth();
    let day = date.getDay();
    let hours = date.getHours();
    let minutes = date.getMinutes();
    let seconds = date.getSeconds();
    const timeStr = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
    return timeStr;
}

//����ҳ���»��� 20px ����"���ض���" ��ť
window.onscroll = function() {
    scrollFunction()
};
function scrollFunction() {
    if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
        document.getElementById("topBtn").style.display = "block";
    } else {
        document.getElementById("topBtn").style.display = "none";
    }
}

// �����ť�����ض���
function topFunction() {
    document.body.scrollTop = 0;
    document.documentElement.scrollTop = 0;
}