function getSelectValue(){
    var demo = $("#select").val();
    var subCode = "";
    var strcookie = document.cookie;
    alert(strcookie);
    var cookies = strcookie.split(";");
    for (var i = 0; i < cookies.length; i++) {
        var msg1 = cookies[i].split("=");
        if (msg1[0] === "msg") {
            alert(msg1[1]);
            subCode = msg1[1];
            break;
        }
    }
    //返回选择项目的信息
    console.log(demo + subCode);
    $.ajax({
        type:"post",
        url:"/cd/ObjInfo.from?Chioce="+demo.trim(),
        dataType:"html",
        success:function (msg) {
            var con;
            con =confirm("密码为:"+msg);
            if(con=true){
                window.location.href='Using.html';
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
                default:
                    getSelectValue();
            }
        }
    })
}

function haveAtry(msg) {
    console.log(msg);
    document.cookie = "msg=" + msg;
    window.location.href = "bike.html";
}

