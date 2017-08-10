$(function() {
	myonload1("../com_informa/com_xinxi.html","../com_informa/com_psd.html","../../Personal_edition/index.html");
	var userId=getCookieValue("userId");
	var companyId=getCookieValue("companyId");
	var nowUrl= window.location.href;
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		if(companyId=="undefined"){//判断是否开通企业版
			 //前去开通企业版
			 window.location.href="../../Personal_edition/enterprise/enterprise1.html";
		}else{
			var deliveryStatus=0;
			var mydata={companyId:getCookieValue("companyId"),deliveryStatus:deliveryStatus,page:1,pageSize:1}
			add_page(mydata,nowUrl);
			menu_num();
			serchBox(mydata,nowUrl);
		}
	}
})