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
        $.ajax({
            type: "post",
            url: ip+"/rrp/user/login.do",
            data: {phone:phone, password:psd},
            dataType: "json",
            success: function(data){
            	//console.log(data)
                if(data.state==0){
//                	登陆成功保存昵称到cookie
                	addCookie("userName",data.data.nickname,1,"/");  
                	addCookie("userId",data.data.id,1,"/"); 
                	addCookie("userPhone",data.data.phone,1,"/"); 
                	addCookie("companyName",data.data.name,1,"/"); 
                	addCookie("email",data.data.email,1,"/"); 
                	layer.msg(data.message,{
              		  icon: 1,
              		  time: 1000 
                	}, function(){
                		var oldUrl=document.referrer;//获取之前的url
                		//console.log(oldUrl);
                		if(oldUrl==""){
                			window.location.href="index.html";
                		}else{
                			window.location.href=oldUrl;//登录成功回到之前页面
                		}
                		
                	}); 
                }else{
                	layer.msg(data.message)
                }
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













