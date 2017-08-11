function getStatus(){//查找状态
	var status=''
	var email=getCookieValue("email");
	if(email=="undefined"){//未进入验证邮箱页面，未开通企业版
		status=0;
	}else{
		$.ajax({
			type:"get",
			url:ip+"/rrp/company/findCompanyInfo.do",
			data:{email:email},
			async: false,
			dataType:'json',
			success:function(data) {
				if(data.state==0){
					status=data.data.status;
				}
			}
		})
	}
	return status;
}


function to_open(comStatus){
	if(comStatus==0){//邮箱未验证
		layer.msg("还未开通企业版，先前往开通",{
    	    icon: 7,
    		time: 3000 
      	}, function(){
      		window.location.href="../Personal_edition/enterprise/enterprise1.html";
      	}); 
	}else if(comStatus==1){//前往完善资料
		layer.msg("还未完善公司资料",{
    	    icon: 7,
    		time: 3000 
      	}, function(){
      		window.location.href="com_informa/com_informa.html";
      	}); 
	}else{
		layer.msg("还未开通企业版",{//正在审核和未通过
    	    icon: 7,
    		time: 3000 
      	}, function(){
      		window.location.href="../Personal_edition/enterprise/enterprise_status.html";
      	}); 
	}
}
function to_open_inner(comStatus){
	if(comStatus==0){//邮箱未验证
		layer.msg("还未开通企业版，先前往开通",{
    	    icon: 7,
    		time: 3000 
      	}, function(){
      		window.location.href="../../Personal_edition/enterprise/enterprise1.html";
      	}); 
	}else if(comStatus==1){
		layer.msg("还未完善公司资料",{
    	    icon: 7,
    		time: 3000 
      	}, function(){
      		window.location.href="../com_informa/com_informa.html";
      	}); 
	}else{
		layer.msg("还未开通企业版",{
    	    icon: 7,
    		time: 3000 
      	}, function(){
      		window.location.href="../../Personal_edition/enterprise/enterprise_status.html";
      	}); 
	}
}
