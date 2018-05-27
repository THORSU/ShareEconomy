function getSelectValue(){
    // var demo=$("#select").val();//返回选择项目的信息
    // console.log(demo);
    var objectId = "";
    var subObjectCode = "";
    $.ajax({
        type:"post",
        url:"/cd/ObjInfo.from?objectId=" + objectId.trim() + "&subObjectCode=" + subObjectCode.trim(),
        dataType:"html",
        //返回子商品实体类，此处取密码返回给用户
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
