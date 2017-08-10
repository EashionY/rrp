/**
 * Created by Administrator on 2017/7/12.
 */
//样式
$(function(){
	console.log(getCookieValue("companyId"));
	var companyId=getCookieValue("companyId");
	if(companyId!='undefined'){//已经开通企业版，直接进入企业版
		window.location.href="../../Enterprise_edition/talent_recom.html";
	}
})

$(".enterp_contentbox3_input input").on("focus",function(){
    $(this).parent().css({
        "border": "1px #3EB49E solid"
    })
});
$(".enterp_contentbox3_input input").on("blur",function(){
    $(this).parent().css({
        "border": "1px #E3E8EE solid"
    })
})

$(".enterp_inputDiv input").on("focus",function(){
    $(this).parent().css({
        "border": "1px #3EB49E solid"
    })
    $(this).siblings(".enterp_tubiao").children(".enterp_font").css({
        "color": "#3EB49E"
    })
});
$(".enterp_inputDiv input").on("blur",function(){
    $(this).parent().css({
        "border": "1px #E3E8EE solid"
    })
    $(this).siblings(".enterp_tubiao").children(".enterp_font").css({
        "color": "#dddddd"
    })
})