/**
 * Created by Administrator on 2017/7/7.
 */

    var verifyCode ="";
    if(window.screen.width<1024){
        verifyCode= new GVerify("v_container2");//图片验证码-手机
    }else{
        verifyCode = new GVerify("v_container");//图片验证码
    }
//    注册按钮触发事件
$(".reg_Btn").on("click",function(){
    var regList=new Array();//输入框
    var form=$(this).parent().parent();
    var reg_phone=form.children().eq(0).children().eq(2);//1手机号
    var reg_psd=form.children().eq(2).children().eq(1);//2密码
    var reg_psd2=form.children().eq(4).children().eq(1);//3二次密码
    var reg_tpyzm=form.children().eq(6).children().eq(1);//4图形验证码
    var reg_dxyzm=form.children().eq(8).children().eq(1);//5短信验证码
    regList.push(reg_phone);regList.push(reg_psd);regList.push(reg_psd2);regList.push(reg_tpyzm);regList.push(reg_dxyzm);
    var surebtn=false;//6声明（flase-未阅读  true-已阅读）
//  是否阅读声明
    if($(".reg_tiptu").css("color")=="rgb(51, 51, 51)"){surebtn=false;}else if($(".reg_tiptu").css("color")=="rgb(62, 180, 158)"){surebtn=true;};
//  图片验证码
    var tpbool=false;//false-错误 true-正确
    var res = verifyCode.validate(form.children().eq(6).children().eq(1).val());
    if(res){tpbool=true;}else{form.children().eq(6).children().eq(1).parent().next().css("opacity",1);}
    var kongbool=kong(regList);//空
    var errbool=err(regList);//错误
    if(kongbool==false){
        layer.msg("输入框有空！")
    }else if(errbool==false){
        layer.msg("输入框内容有错！")
    }else if(surebtn==false){
        layer.msg("未阅读声明！")
    }else{
        $.post(ip+"/rrp/user/regist.do",{phone:reg_phone.val(),password:reg_psd.val(),code:reg_dxyzm.val()},function(data){
        	if(data.state==0){
//            	注册成功保存昵称到cookie
            	addCookie("userName",data.data.nickname,1,"/");  
            	addCookie("userId",data.data.id,1,"/"); 
            	addCookie("userPhone",data.data.phone,1,"/"); 
            	layer.msg(data.message,{
            		  icon: 1,
            		  time: 1000 
            	}, function(){
            		window.location.href="../../index.html";
            	}); 
            }else{
            	if(data.message=='验证码错误'||data.message=='验证码超时，请重新发送'){
            		layer.msg("短信"+data.message)
            	}else{
            	    layer.msg(data.message)
            	}
            }
        },'json')
    }
});
//短信验证码
$(".reg_dxyzmbtn").on("click",function(){
    var phone=$(this).parent().parent().parent().children().eq(0).children().eq(2).val();
    sends.send(phone,ip+"/rrp/user/sendRegCode.do")
});

//页面跳转
$("#to_logBtn span").on("click",function(){
    window.location.href="login.html"
})




