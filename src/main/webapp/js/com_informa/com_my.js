$(function() {
	myonload1("com_xinxi.html","com_psd.html","../../../index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		if(comStatus==3){//判断是否开通企业版
			$.get(ip+"/rrp/company/findCompanyInfo.do",{email:getCookieValue("email")},function(data){
				 if(data.state==0){
					 $(".comMy_left img").attr("src","../../../../"+data.data.logo);
					 $("#commy_name").html(data.data.name);
					 $("#com_rz").html(data.data.financing)
					 $("#com_gm").html(data.data.scale)
					 $("#com_hy").html(data.data.industry)
				 }else{
					 layer.msg(data.message)
				 }
			 },'json')
		}else{
			to_open_inner(comStatus);//判断跳转页面
		}
	}
})