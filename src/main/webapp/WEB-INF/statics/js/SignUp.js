function check(v){
    if(v=="username") {
        var username = $("#username").val();
        $.ajax({
            type: "post",
            url: "/cd/CheckUname.from?username=" + username.trim(),
            dataType: "html",
            success: function (msg) {
                if (msg == 1) {
                    alert("用户名已被使用！");
                }
                else {
                    // $("#res").html("");
                }
            }
        })
    }
}
var flag=true;
function SignUp() {
    var username = $("#username").val();
    var password = $("#password").val();
    var Aname = $("#Aname").val();
    var usermobile = $("#usermobile").val();
    var IDnumber = $("#IDnumber").val();
    if(flag!=true) {
        if (username == "" || password == "") {
            alert("内容不能为空");
        }
        else {
            $.ajax({
                type: "post",
                url: "/cd/SignUp.from?username=" + username + "&password=" + password + "&Aname=" + Aname +
                "&usermobile=" + usermobile + "&IDnumber=" + IDnumber,
                dataType: "html",
                success: function (msg) {
                    if (msg == 1) {
                        var con;
                        con = confirm("注册成功，请登录");
                        if (con == true) {

                            window.location.href = 'login.html';
                        }
                        else {
                            alert("注册失败");
                        }
                    }
                }
            })
        }
    }
    else {alert("验证码错误")}
}

window.onload = function() {
    var _yanzhengma = document.getElementById("yanzhengma");
    getRandomMath(_yanzhengma);
    var _random = null;
    var loginform = document.getElementById("loginform");
    var indexVlaue = null;

}
//随机颜色函数
function getRandomColor() {
    return '#' + (function(h) {
            return new Array(7 - h.length).join("0") + h
        })((Math.random() * 0x1000000 << 0).toString(16));
}
//随机数
var _random;
function getRandomMath(obj) {
    _random = Math.floor(Math.random() * (99999 - 10000) + 10000);
    var _arryanzhengma = (String)(_random).split("");
    obj.innerHTML = "";
    for (var i = 0; i < _arryanzhengma.length; i++) {
        //追加的形式添加
        var _nodeChild = document.createElement("i");
        _nodeChild.style.color = getRandomColor();
        _nodeChild.innerHTML = _arryanzhengma[i] + "&nbsp;";
        obj.appendChild(_nodeChild);
    }
    // console.log(_random)
}

function validateCode() {
    var code=$("#vcode").val();
    if(code==_random){
        flag=false;
    }

}

