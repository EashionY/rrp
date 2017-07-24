/**
 * Created by Administrator on 2017/7/7.
 */
//    登录
$("#log_btn").on("click",function(){
    var phone=$("#log_phone").val();
    var psd=$("#log_psd").val();
    if(phone==""||psd==""){
        layer.msg("内容为空")
    }else{
        console.log("发送登录请求");
        console.log(phone);
        console.log(psd);
        $.ajax({
            type: "post",
            url: "http://192.168.0.20:8080/rrp/user/login",
            data: {phone:phone, password:psd},
            dataType: "json",
            success: function(data){
                console.log(data)
            }
        });
    }
});

//样式
$(".log_inputDiv input").on("focus",function(){
    $(this).parent().css("border","1px #3EB49E solid");
    $(this).parent().children(".log_tubiao").css("color", "#3EB49E")
});
$(".log_inputDiv input").on("blur",function(){
    $(this).parent().css("border","1px #E3E8EE solid");
    $(this).parent().children(".log_tubiao").css("color", "#dddddd")

});
//跳转
$("#to_regBtn span").on("click",function(){
    window.location.href="reg.html"
});
$("#to_forgotBtn").on("click",function(){
    window.location.href="forgot_psd.html"
});