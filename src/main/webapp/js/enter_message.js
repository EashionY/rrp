$(function() {
	myonload1("com_informa/com_xinxi.html","com_informa/com_psd.html","../../index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//判断是否登录
	     window.location.href="../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		if(comStatus==3){//判断是否开通企业版(3--开通成功);
			//加载消息
		}else{
			to_open_inner(comStatus);//判断跳转页面
		}
	}
})