function fixObject() {
    var subObjectCode = $("#select").val();
    var objectId = "";
    var strcookie = document.cookie;
    // alert(strcookie);
    var cookies = strcookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var msg1 = cookies[i].split("=");
        console.log(msg1[1]);
        if (msg1[0] === " msg") {
            // alert(msg1[1]);
            objectId = msg1[1];
            break;
        }
    }
    $.ajax({
        type:"post",
        url:"/cd/fixObject.from?objectId=" + objectId.trim() + "&subObjectCode=" + subObjectCode.trim(),
        dataType:"html",
        success:function (msg) {
            if (msg == "1"){
                alert("感谢报修~");
            }
            else {
                alert("报修异常");
            }
        }

    })
}

function WaIsZero(){
    $.ajax({
        type:"post",
        url:"/ef/IsZero.from",
        datatype:"html",
        success:function (msg) {
            switch (msg) {
                // case 0:
                //     window.location.href = 'Using.html';
                //     break;
                case 0:
                    alert("余额不足");
                    break;
                default:getSelectValue();
            }
        }
    })
}

function getSelectValue(){
    var subObjectCode = $("#select").val();
    var objectId = "";
    var strcookie = document.cookie;
    // alert(strcookie);
    var cookies = strcookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var msg1 = cookies[i].split("=");
        console.log(msg1[1]);
        if (msg1[0] === " msg") {
            // alert(msg1[1]);
            objectId = msg1[1];
            break;
        }
    }
    $.ajax({
        type:"post",
        url:"/cd/ObjInfo.from?objectId=" + objectId.trim() + "&subObjectCode=" + subObjectCode.trim(),
        dataType:"html",
        //返回子商品实体类，此处取密码返回给用户
        success:function (msg) {
            if (msg === "null"){
                alert("该编号不存在！");
            }
            else if (msg == "badObject"){
                alert("该商品已坏，暂时无法使用");
            }
            else {
                var con;
                con =confirm("密码为:"+msg);
                if(con=true){
                    window.location.href='Using.html';
                }
            }
        }
    })
}

function haveAtry(msg) {
    console.log(msg);
    document.cookie = "msg=" + msg + ";path = /";
    // alert(document.cookie);
    window.location.href = "bike.html";
}


//获取登陆者昵称
document.ready = function getname() {
    $.ajax({
        type:"get",
        url:"/ef/getname.from",
        success:function (msg) {
            console.log(msg);
            $("#asd").text(msg);
        }
    })
};

//动态获取所有商品信息
window.onload=function getObjectInfo() {
        $.ajax({
            type:"",
            url:"/ef/getObjectInfo.from",
            success:function (msg) {
//                console.log(msg);
//                $("#asd").text(msg);
            }
        })
    };

function showInfo(){
    document.getElementById("div").style.display="";
//alert(document.getElementById("div").style.display)
}
function hiddenInfo(){
    document.getElementById("div").style.display="none";
//alert(document.getElementById("div").style.display)
}