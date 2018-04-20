function getSelectValue(){
    var demo=$("#select").val();//返回选择项目的信息
    console.log(demo);
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
                default:getSelectValue();
            }
        }
    })
}
