
var FLAG=false;//密码验证通过与否标志位
$(document).ready(function() {
    //前台验证账号
    $("#username").focus(function() {
        $("#loginTip").text("*账号由11位手机号组成");
        $("#loginTip").css("color", "red");
    })
    $("#username").blur(function() {
        var username = $("#username").val();
        if (username == "" || username == null) {
            $("#loginTip").text("*输入的用户名不能为空");
            $("#loginTip").css("color", "red");

            return false;
        } else if (!isPoneAvailable(username)) {
            $("#loginTip").text("*请输入正确的用户名");
            $("#loginTip").css("color", "red");
            return false;
        } else{
            $("#loginTip").html("<br>");
            FLAG=true;

        }
    })
    //前台验证密码
    $("#password").focus(function() {
        $("#passwordTip").text("*密码由不少于6位或大于16位数字和字母组成");
        $("#passwordTip").css("color", "red");
    })
    $("#password").blur(function() {
        // console.log("111")
        var password = $("#password").val();
        if (password == "" || password == null) {
            // console.log("222")
            $("#passwordTip").text("*输入的密码不能为空");
            $("#passwordTip").css("color", "red");
            return false;
        } else if (!CheckPassWord(password)) {
            // console.log("333")
            $("#passwordTip").text("*请输入正确的密码");
            $("#passwordTip").css("color", "red");
            return false;
        } else{
            // console.log("444")
            $("#passwordTip").html("<br>");
            FLAG=true;

        }

    })


});
//验证是否为11位有效手机号
function isPoneAvailable(str) {
    var myreg = /^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(str)) {
        return false;
    } else {
        return true;
    }
}
//验证密码不能包括特殊字符，由数字和字母组成且长度不小于6
function CheckPassWord(password) {
    var str = password;
    if (str == null || str.length < 6) {
        return false;
    }
    var reg1 = new RegExp(/^[0-9A-Za-z]+$/);
    if (!reg1.test(str)) {
        return false;
    }
    var reg = new RegExp(/[A-Za-z].*[0-9]|[0-9].*[A-Za-z]/);
    if (reg.test(str)) {
        return true;
    } else {
        return true;
    }
}

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
    if (FLAG!=false){
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
                    url: "/cd/SignUp.from?username=" + username + "&password=" + password,
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
                        else if (msg == 2){
                            alert("该用户名已被注册");
                        }
                    }
                })
            }
        }
        else {alert("验证码错误")}
    }else {}

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

