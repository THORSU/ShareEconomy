function  check(v){
    if(v=="Cpwd"){
        if($("#Cpwd").val()==$("#Npwd").val()){
            console.log("true");
        }
        else {
            alert("密码输入错误");
        }
    }
}
function UpdateUInfo(){
    var Aname=$("#Aname").val();
    var NewUpwd=$("#Npwd").val();

    $.ajax({
        type:"post",
        url:"/cd/UpdateInfo.from?Aname="+Aname+"&NewUpassword="+NewUpwd,
        dataType:"html",
        success:function(msg){
            if(msg==1){

                var con;
                con = confirm("修改成功，请重新登录");
                if (con == true) {
                    window.location.href = 'logout.html';
                }
            }
            else{
                alert("修改失败");
            }
        }
    })

}