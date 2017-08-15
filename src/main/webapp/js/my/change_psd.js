$(function() { 
	myonload2("pers_infor.html","../../../index.html");
	var userId=getCookieValue("userId");
	var uPhoneValue = getCookieValue("userPhone");
	if(userId==""){//未登录，请先登录
		window.location.href="../login.html";
	}else{
		$(".psd_div1 span").html(uPhoneValue)
	}
});
	$(".psd_btn").click(function () {
		var uid = getCookieValue("userId");
		var oldpsd=$("#oldpsd").val();
		var newpsd=$("#newpsd").val();
		var newpsd2=$("#newpsd2").val();
		if(oldpsd=="" || newpsd=="" || newpsd2==""){
			layer.msg("输入框不能为空！")
		}else if(newpsd!=newpsd2){
			layer.msg("两次密码不一致!")
		}else{
			var num=0;
		    var zm=false,sz=false,fh=false;
		    if(newpsd.search(/[A-Za-z]/)!=-1) {num+=1;}else{zm=true}
		    if(newpsd.search(/[0-9]/)!=-1) {num+=1;}else{sz=true}
		    if(newpsd.search(/[-+_!@#$%^&*()]/)!=-1) {num+=1;}else{fh=true}
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
			          	    console.log(data);
			                if(data.state==0){
			                	layer.msg('修改成功！请重新登录!', {
			                		  icon: 1,
			                		  time: 1000 //2秒关闭（如果不配置，默认是3秒）
			                		}, function(){
			                		  deleteCookie("userName","/");
			                		  window.location.href="../login.html";
			                		});   
			                }else{
			                	layer.msg(data.message)
			                }
			            }
			        });
		        }
		    }else{
		        layer.msg("请输入密码，6-16位字母/数字/符号组合")
		    }
		}
	})
	