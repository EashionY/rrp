$(function() {
	myonload1("com_xinxi.html","com_psd.html","../../Personal_edition/index.html");
	var userId=getCookieValue("userId");
	var companyId=getCookieValue("companyId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		if(companyId=="undefined"){//判断是否开通企业版
			 //前去开通企业版
			 window.location.href="../../Personal_edition/enterprise/enterprise1.html";
		}else{
			$.get(ip+"/rrp/company/findCompanyInfo.do",{email:getCookieValue("email")},function(data){
				 if(data.state==0){
					 $("#scom_logo").attr("src","../../../../"+data.data.logo);
					 $(".comxx_name").html(data.data.name);
					 $("#com_rz").html(data.data.financing)
					 $("#com_gm").html(data.data.scale)
					 $("#com_hy").html(data.data.industry)
					 $("#com_wz").html(data.data.website);
					 $("#com_dz").html(data.data.address);
					 $(".comxx_detail").html('<p>'+data.data.intro+'</p>')
				 }else{
					 layer.msg(data.message)
				 }
			 },'json')
		}
		 
	}
})