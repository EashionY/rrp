/**
 * Created by Administrator on 2017/7/7.
 */
//    输入框验证
function err_tips_false(obj){
    $(obj).parent().next().css("opacity",0)
}
var reg_focus=$(".reg_focus");
$.each(reg_focus,function(key,val){
    $(val).on("focus",function(){
        err_tips_false(this)
    });
});

$("#reg_phone").on("blur",function(){
    var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
    if(!myreg.test($(this).val())) {
        $(this).parent().next().css("opacity",1);
    }
});
$("#reg_psd").on("blur",function(){
    var psd=$(this).val();
    var num=0;
    var zm=false,sz=false,fh=false;
    if(psd.search(/[A-Za-z]/)!=-1) {num+=1;}else{zm=true}
    if(psd.search(/[0-9]/)!=-1) {num+=1;}else{sz=true}
    if(psd.search(/[-+_!@#$%^&*()]/)!=-1) {num+=1;}else{fh=true}
    if(psd.length>=6 && psd.length<=16){
        if(num<3){
            $(this).parent().next().css("opacity",1);
            if(zm==true){
                $(this).parent().next().html("*至少有一个字母")}
            if(sz==true){
                $(this).parent().next().html("*至少有一个数字")}
            if(fh==true){
                $(this).parent().next().html("*至少有一个符号")}
        }
    }else{
        $(this).parent().next().css("opacity",1);
    }
});
$("#reg_psd2").on("blur",function(){
    var psd1=$("#reg_psd").val();
    var psd2=$(this).val();
    if(psd2!=psd1){
        $(this).parent().next().css("opacity",1);
    }
});

//短信验证码
$("#reg_dxyzmbtn").on("click",function(){
    sends.send()
});
var verifyCode = new GVerify("v_container");//图片验证码
//阅读声明-样式
$("#reg_tip").on("click",function(){
    if($(this).children().css("color")=="rgb(51, 51, 51)"){
        $(this).children().css("color","#3EB49E")
    }else{
        $(this).children().css("color","rgb(51, 51, 51)")
    }
})


//    注册按钮触发事件
$("#reg_Btn").on("click",function(){
    var regList=new Array();//输入框
    var reg_phone=$("#reg_phone");//1手机号
    var reg_psd=$("#reg_psd");//2密码
    var reg_psd2=$("#reg_psd2");//3二次密码
    var reg_tpyzm=$("#reg_tpyzm");//4图形验证码
    var reg_dxyzm=$("#reg_dxyzm");//5短信验证码
    regList.push(reg_phone);regList.push(reg_psd);regList.push(reg_psd2);regList.push(reg_tpyzm);regList.push(reg_dxyzm);
    var surebtn=false;//6声明（flase-未阅读  true-已阅读）
//  是否阅读声明
    if($("#reg_last").css("color")=="rgb(51, 51, 51)"){surebtn=false;}else if($("#reg_last").css("color")=="rgb(62, 180, 158)"){surebtn=true;};
//  图片验证码
    var tpbool=false;//false-错误 true-正确
    var res = verifyCode.validate($("#reg_tpyzm").val());
    if(res){tpbool=true;}else{$("#reg_tpyzm").parent().next().css("opacity",1);}
    var kongbool=false;//空
    $.each(regList,function(key,val){
        if(val.val()==""){
            kongbool=false;
            return false;
        }else{
            kongbool=true;
        }
    });
    var errbool=false;//错误
    $.each(regList,function(key,val){
        if(val.parent().next().css("opacity")==1){
            errbool=false;
            return false;
        }else{
            errbool=true;
        }
    });
    if(kongbool==false){
        layer.msg("输入框有空！")
    }else if(errbool==false){
        layer.msg("输入框内容有错！")
    }else if(surebtn==false){
        layer.msg("未阅读声明！")
    }else{
        console.log("发送注册请求")
        $.each(regList,function(key,val){
           console.log($(val).val())
        });
    }
});



//页面跳转
$("#to_logBtn").on("click",function(){
    window.location.href="login.html"
})




