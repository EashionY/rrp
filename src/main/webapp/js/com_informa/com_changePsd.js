$(function() {
	myonload1("com_xinxi.html","com_psd.html","../../../index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		if(comStatus==3){//判断是否开通企业版
			$("#com_account").html(getCookieValue("userPhone"))
		}else{
			to_open_inner(comStatus);//判断跳转页面
		}
		
	}
})
$(".psd_btn").click(function(){
	var uid = getCookieValue("userId");
	var oldpsd=$("#old_psd").val();
	var newpsd=$("#new_psd").val();
	var newpsd2=$("#new_psd2").val();
	if(oldpsd=="" || newpsd=="" || newpsd2==""){
		layer.msg("输入框不能为空！")
	}else if(newpsd!=newpsd2){
		layer.msg("两次密码不一致!")
	}else{
		var num=0;
	    var zm=false,sz=false,fh=false;
	    if(newpsd.search(/[A-Za-z]/)!=-1) {num+=1;}else{zm=true}
	    if(newpsd.search(/[0-9]/)!=-1) {num+=1;}else{sz=true}
	    if(newpsd.search(/[-+_!@#$%^&*().]/)!=-1) {num+=1;}else{fh=true}
	    if(newpsd.length>=6 && newpsd.length<=16){
	        if(num<3){
	            if(zm==true){layer.msg("新密码至少有一个字母")}
	            else if(sz==true){layer.msg("新密码至少有一个数字")}
	            else if(fh==true){layer.msg("新密码至少有一个符号")}
	        }else{
	        	$.ajax({
		            type: "post",
		            url: ip+"/rrp/user/modifyPsd.do",
		            data: {userId:uid,oldPsd:oldpsd,newPsd:newpsd},
		            dataType: "json",
		            success: function(data){
		                if(data.state==0){
		                	layer.msg('修改成功！请重新登录!', {
		                		  icon: 1,
		                		  time: 1000 //2秒关闭（如果不配置，默认是3秒）
		                		}, function(){
		                		  deleteCookie("userName","/");
		                		  window.location.href="../../Personal_edition/login.html";
		                		});   
		                }else{
		                	layer.msg(data.message)
		                }
		            }
		        });
	        }
	    }
	}
})