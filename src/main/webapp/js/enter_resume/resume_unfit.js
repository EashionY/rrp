$(function() {
	myonload1("../com_informa/com_xinxi.html","../com_informa/com_psd.html","../../Personal_edition/index.html");
	var userId=getCookieValue("userId");
	var nowUrl= window.location.href;
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var mydata={companyId:getCookieValue("companyId"),deliveryStatus:4,page:1,pageSize:1}
		add_page(mydata,nowUrl);
		menu_num();
	}
})