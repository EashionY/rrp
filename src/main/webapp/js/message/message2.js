$(function() { 
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
		window.location.href="../login.html";
	}else{
		//加载消息
	}
})