$(function() {
	myonload1("../com_informa/com_xinxi.html","../com_informa/com_psd.html","../../Personal_edition/index.html");
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
$(".posiEdi_btn").click(function(){
	//console.log($("#po_name").val());//名称
	//console.log($("#po_city").val());//城市
	//console.log($("#po_workjy").html())//工作经验
	//console.log($("#po_xl").html())//学历
	//console.log($("#po_yx").html())//职位月薪
	//console.log($("#work_content").val())//任职要求
	//console.log($("#work_content2").val())//岗位职责
	//console.log($(".xz_active").html())//工作性质
	var ld='';
	$.each($(".ld_str"),function(k,v){
		ld+=$(v).html()+'，';
	});
	ld = ld.substring(0,ld.length-1)
	//console.log(ld)//亮点
	//console.log(getCookieValue("companyId"));
	if($("#po_name").val()==""){
		layer.msg("职位名为空");
	}else{
		$.ajax({
			type:"post",
			url:ip+'/rrp/post/pushJob.do',
			data:{companyId:getCookieValue("companyId"),name:$("#po_name").val(),salary:$("#po_yx").html(),region:$("#po_city").val(),workExp:$("#po_workjy").html(),degree:$("#po_xl").html(),workType:$(".xz_active").html(),benefits:ld,duty:$("#work_content2").val(),requirement:$("#work_content").val()},
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			dataType:'json',
			success:function(result){
				console.log(result);
				if(result.state==0){
					layer.msg(result.message)
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
    
    
    