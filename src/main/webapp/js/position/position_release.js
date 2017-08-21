$(function() {
	myonload1("../com_informa/com_xinxi.html","../com_informa/com_psd.html","../../../index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		if(comStatus==3){//判断是否开通企业版
			
		}else{
			to_open_inner(comStatus);//判断跳转页面
		}
	}
})
$(".posiEdi_Btn").click(function(){
	var ld='';
	$.each($(".ld_str"),function(k,v){
		ld+=$(v).html()+'，';
	});
	ld = ld.substring(0,ld.length-1)
	if($(".po_name").val()==""){
		layer.msg("职位名为空");
	}else{
		$.ajax({
			type:"post",
			url:ip+'/rrp/post/pushJob.do',
			data:{companyId:getCookieValue("companyId"),name:$(".po_name").val(),salary:$(".po_yx").html(),region:$(".po_city").val(),workExp:$(".po_workjy").html(),degree:$(".po_xl").html(),workType:$(".xz_active").html(),benefits:ld,duty:zhize.getContent(),requirement:yaoqiu.getContent()},
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			dataType:'json',
			success:function(result){
				if(result.state==0){
					layer.msg("发布成功，请前往职位管理开启职位",{icon:1,time:2000},function(){
						window.location.href="position_manage.html";
					})
				}else{
					layer.msg(result.message)
				}
			}
		})
	}
	
})








//    选择
    $(function(){
        $.divselect("#divselect_work","#inputselect_work");
        $.divselect("#divselect_xl","#inputselect_xl");
        $.divselect("#divselect_yx","#inputselect_yx");
    });
    
    
    