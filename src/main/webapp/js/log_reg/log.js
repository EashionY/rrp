/**
 * Created by Administrator on 2017/7/7.
 */
//    登录
$(".log_Btn").on("click",function(){
    var phone=$(this).parent().parent().children().eq(0).children().eq(2).val();
    var psd=$(this).parent().parent().children().eq(1).children().eq(1).val();
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
                	addCookie("companyId",data.data.companyId,1,"/"); 
                	addCookie("email",data.data.email,1,"/"); 
                	layer.msg(data.message,{
              		  icon: 1,
              		  time: 1000 
                	}, function(){
                		var oldUrl=document.referrer;//获取之前的url
                		var strs= new Array();
                		strs=oldUrl.split("/");
                		if(oldUrl=="" || strs[strs.length-1]=='reg.html'|| strs[strs.length-1]=='forgot_psd.html'){
                			window.location.href="../../index.html";
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


//跳转
$("#to_regBtn span").on("click",function(){
    window.location.href="reg.html"
});
$(".to_forgotBtn").on("click",function(){
    window.location.href="forgot_psd.html"
});













