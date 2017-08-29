$(function() {
	var userId=getCookieValue("userId");
	var nowUrl= window.location.href;
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		if(comStatus==3){//判断是否开通企业版
			var deliveryStatus=3;
			var mydata={companyId:getCookieValue("companyId"),deliveryStatus:deliveryStatus,page:1,pageSize:5}
			add_page(mydata,nowUrl);
			menu_num();
			serchBox(mydata,nowUrl);
		}else{
			to_open_inner(comStatus);//判断跳转页面
		}
	}
})
$('#wrapper03').navbarscroll({
	defaultSelect:3,
	scrollerWidth:6,
	fingerClick:1,
	endClickScroll:function(obj){}
});