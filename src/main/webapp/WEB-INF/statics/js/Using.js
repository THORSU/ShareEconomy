function two_char(n) {
    return n >= 10 ? n : "0" + n;
}
var sec=0;
var int=self.setInterval("clock()",1000)
function clock(){
    sec++;
    var date = new Date(0, 0)
    date.setSeconds(sec);
    var h = date.getHours(), m = date.getMinutes(), s = date.getSeconds();
    document.getElementById("mytime").innerText = two_char(h) + ":" + two_char(m) + ":" + two_char(s);
}

var isUp=true;
function timeUp() {
    isUp=false;
    int=window.clearInterval(int);//internal the function clock();
    console.log(isUp);
}

function Deduct(msg) {
    console.log(msg);
    $.ajax({
        type:"post",
        url:"/ef/deduct.from?bill="+ msg.trim(),
        dataType:"html",
        success:function (msg1){
            if(msg1=="0"){
                window.location.href = 'SuccessPay.html';
            }else {
                alert("余额不足")
            }
        }
    })
}
function Paying() {
    if (isUp!=true) {
        var time = document.getElementById("mytime").innerText;
                   console.log(time);

        $.ajax({
            type: "post",
            url: "/ef/Paying.from?time=" + time.trim(),
            dataType: "html",
            success: function (msg) {
                var con;
                con = confirm("确定费用：" + msg);
                if (con == true) {
                    Deduct(msg);
                               window.location.href = 'SuccessPay.html';
                }
            }
        })
    }
    else {alert("请先停止计时")}
}