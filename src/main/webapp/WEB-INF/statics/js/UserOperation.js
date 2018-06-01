function Recharge() {
    var account=$("#select2").val();
    console.log(account);
    $.ajax({
        type:"post",
        url:"/ef/recharge.from?account="+account.trim(),
        dataType:"html",
        success:function () {
            var con;
            con = confirm("充值成功");
            if (con == true) {
                window.location.href='FirstPage.html';
            }
        }
    })
}

// function logout(){
//     alert("asd")
    // $.ajax({
    //     type:"post",
    //     url:"/cd/logout.from",
    //     datatype:"html",
    //     success:function (msg) {
    //         if(msg==1){
    //             window.location.href='login.html';
    //         }
    //     }
    // })
// }

function Balance() {
    $.ajax({
        type:"post",
        url:"/ef/IsZero.from",
        dataType:"html",
        success:function (msg) {
            alert("您的账户余额为："+msg);
        }
    })
}