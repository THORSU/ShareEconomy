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
                // window.location.href = 'SuccessPay.html';
                return "wellPay";
            }else {
                return "badPay";
            }
        }
    })
}
function Paying() {
    if (isUp!=true) {
        var time = document.getElementById("mytime").innerText;
        console.log(time);
        console.log(JSON.stringify(time));

        $.ajax({
            type: "post",
            url: "/ef/Paying.from?time=" + time.trim(),
            data: JSON.stringify(time),
            dataType: "html",
            success: function (msg) {
                console.log(msg);
                var con;
                con = confirm("确定费用：" + msg);
                var period = time;
                var bill = msg;
                console.log(period);
                if (con == true) {
                    $.ajax({
                        url: "/order/insertOrder.form?period=" + period.trim() + "&bill=" + bill.trim(),
                        type: "post",
                        dataType: "html",
                        success: function (msg) {
                            console.log(msg);
                           var result = Deduct(bill);
                            if (msg == "insert successful!") {

                                    alert("使用记录已生产，可到订单查看！");
                                    window.location.href = 'FirstPage.html';

                            }
                            else {
                                alert("insert fail!")
                            }
                        }
                    })
                }

            }
        })
    }
    else {alert("请先停止计时")}
}