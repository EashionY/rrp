$(function() {
	myonload1("com_informa/com_xinxi.html","com_informa/com_psd.html","../../index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//判断是否登录
	     window.location.href="../Personal_edition/login.html";
	}else{
		//加载消息
	}
})