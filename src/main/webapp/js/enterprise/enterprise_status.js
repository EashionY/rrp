$(function() { 
	myonload2("../my/pers_infor.html","../../../index.html");
	var userId=getCookieValue("userId");
	var comStatus=getCookieValue("comStatus");
	if(userId==""){//未登录，请先登录
		window.location.href="../login.html";
	}else{
		var comStatus=getStatus();
		console.log(comStatus);
		if(comStatus==2){
			$("#status_Box").html("资料审核中......");
			$("#btn_box").html('<span id="to_check">查看资料</span>');
			$("#to_check").click(function(){
				window.location.href="../../Enterprise_edition/com_informa/com_informa_check.html";;//查看资料
			})
		}else if(comStatus==-1){
			$("#status_Box").html("资料未审核通过");
			$("#btn_box").html('<span id="to_modify">重新修改资料</span>');
			$("#to_modify").click(function(){
				window.location.href="../../Enterprise_edition/com_informa/com_informa_modify.html";
			})
		}else{
			if(comStatus==0){//邮箱未验证
				layer.msg("还未开通企业版，先前往开通",{
		    	    icon: 7,
		    		time: 3000 
		      	}, function(){
		      		window.location.href="enterprise1.html";
		      	}); 
			}else if(comStatus==1){
				layer.msg("还未完善公司资料",{
		    	    icon: 7,
		    		time: 3000 
		      	}, function(){
		      		window.location.href="../../Enterprise_edition/com_informa/com_informa.html";
		      	}); 
			}else{
				window.location.href="../../Enterprise_edition/talent_recom.html";
			}
		}
	}
})
