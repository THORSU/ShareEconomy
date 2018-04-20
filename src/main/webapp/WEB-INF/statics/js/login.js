
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


    function login() {
        if (FLAG != false) {
            var username = $("#username").val();
            var password = $("#password").val();

            $.ajax({
                type: "post",
                url: "/cd/login.form?username=" + username.trim() + "&password=" + password.trim(),
                dataType: "html",
                success: function (msg) {
                    switch (msg) {
                        case "2":
                            alert("该用户已经登录");
                            break;
                        case "1":
                            window.location.href = 'FirstPage.html';
                            break;
                        case "0":
                            alert("用户名或者密码错误");
                            break;
                        default:
                            alert("输入有误");
                    }
                }
            })
        }
        else {

        }
    }
