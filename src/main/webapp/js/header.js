/**
 * Created by Administrator on 2017/7/20.
 */
//登录注册跳转
$("#head_logbtn").click(function(){
    window.location.href="login.html"
});
$("#head_regbtn").click(function(){
    window.location.href="reg.html"
});
//样式
$("#head_person").mouseover(function(){
    $("#head_person_ul").css("display","block")
});
$("#head_person").mouseout(function(){
    $("#head_person_ul").css("display","none")
});

//个人中心跳转
$("#head_zh").click(function(){
    window.location.href="my/pers_infor.html"
});
$("#head_qy").click(function(){
    window.location.href="../Enterprise_edition/talent_recom.html"
});
$("#head_exit").click(function(){
    alert("退出！")
});

