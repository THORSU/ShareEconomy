$("document").ready(function() {
  var nav_height = $("nav").height();
  //console.log(nav_height);
  $("#my_main").css("margin-top", nav_height);

  //改变窗口大小  当调整浏览器窗口的大小时，发生 resize 事件。
  $(window).resize(function() {
    var height_win = $(window).height();
    var width_win = $(window).width();
  //	var height_ifra = height_win - nav_height;
    $("#my_iframe").height(height_win);
    $("#my_iframe").width(width_win);
  });
  //load是动态加载一个页面的内容到指定的dom元素上。
  $("#my_iframe").load(function() {
    var height_win = $(window).height();
    var width_win = $(window).width();
    var height_ifra = height_win - nav_height;
    $(this).height(height_ifra);
    $(this).width(width_win);
  });
  //显示完模态框之后绑定的事件（与show做区分）
  $("#myModal").on("shown.bs.modal", function() {
    $("#input_a").focus();
  });
  //绑定键盘事件
  $(document).keydown(function(event) {
    if (event.keyCode == 13) {
      $("#make_sure").click();
    };
  });
$.post("describe.php",
{},
function(data){

}
)


});
//输入框的自动跳转
function auto(object, index) {
  if (object.value.length == 1 && index <= 6) {
    document.forms[0].elements[index + 1].focus();
  };
};

//向后台传送输入的密码
function mimaInput() {
  var a1 = $("#input_a").val();
  var a2 = $("#input_b").val();
  var a3 = $("#input_c").val();
  var a4 = $("#input_d").val();
  var a5 = $("#input_e").val();
  var a6 = $("#input_f").val();
  var string=a1+a2+a3+a4+a5+a6;

  // $.ajax({
  //     type:"post",
  //     url:"/ef/confirmPwd.from",
  //     data:string,
  //     datatype:"String",
  //     success:function () {
              Recharge();
  //     }
  // });

}
//清空input
function makeSure() {
  $("#make_sure").attr("data-dismiss", "modal");
  $("#input_a").val("");
  $("#input_b").val("");
  $("#input_c").val("");
  $("#input_d").val("");
  $("#input_e").val("");
  $("#input_f").val("");
  $("#input_g").val("");
  $("#input_h").val("");
}

