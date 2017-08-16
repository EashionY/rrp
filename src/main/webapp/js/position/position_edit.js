
$(function() {
	myonload1("../com_informa/com_xinxi.html","../com_informa/com_psd.html","../../../index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		if(comStatus==3){//判断是否开通企业版
			var Request = new Object(); 
			Request = GetRequest(); 
			var postId=Request['postId']; //获取参数
			$.get(ip+"/rrp/post/jobDetail.do",{postId:postId},function(data){
				if(data.state==0){
					$("#po_name").val(data.data[0].post_name);//名称
					$("#po_city").val(data.data[0].region);//城市
					$("#po_workjy").html(data.data[0].work_exp)//工作经验
					$("#po_xl").html(data.data[0].degree)//学历
					$.each($("#divselect_xl ul li a"),function(k,v){//学历（数字）
						if($(v).html()==data.data[0].degree){
							$("#inputselect_xl").val($(v).attr("selectid"))
						}
					})
					$("#po_yx").html(data.data[0].salary)//职位月薪
					yaoqiu.addListener("ready", function () { 
						yaoqiu.setContent(data.data[0].requirement)//任职要求
					});
					zhize.addListener("ready", function () { 
						zhize.setContent(data.data[0].duty)//岗位职责
					});
					
					var type=$(".posiEdi_inputdiv_xz span");//职位性质
					$.each(type,function(k,v){
						if($(v).eq(0).html()==data.data[0].work_type){
							$(v).addClass("xz_active");
						}else{
							$(v).removeClass("xz_active")
						}
					})
					if(data.data[0].benefits!=""){
						var ldList=data.data[0].benefits.split("，");//职位亮点
						var ldstr='';
						$.each(ldList,function(k,v){
							ldstr+='<span><span class="ld_str">'+v+'</span><span class="ld_dele">X</span></span>'
						});
						$("#po_ldBox").html(ldstr);
					}
					ld_caozuo();
					
				}else{
					layer.msg(data.message)
				}
			},'json')
		}else{
			to_open_inner(comStatus);//判断跳转页面
		}
	}
})

$("#posiEdi_btn").click(function(){
	var Request = new Object(); 
	Request = GetRequest(); 
	var postId=Request['postId']; //获取参数
	var ld='';
	$.each($(".ld_str"),function(k,v){
		ld+=$(v).html()+'，';
	});
	ld = ld.substring(0,ld.length-1)
	$.ajax({
		type:"post",
		url:ip+'/rrp/post/editJob.do',
		data:{id:postId,name:$("#po_name").val(),salary:$("#po_yx").html(),region:$("#po_city").val(),workExp:$("#po_workjy").html(),degree:$("#po_xl").html(),workType:$(".xz_active").html(),benefits:ld,duty:zhize.getContent(),requirement:yaoqiu.getContent()},
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		dataType:'json',
		success:function(result){
			if(result.state==0){
				layer.msg("修改成功，请前往职位管理开启职位",{icon:1,time:2000},function(){
					window.location.href="position_manage.html";
				})
			}else{
				layer.msg(result.message)
			}
		}
	})
})


//    选择
    $(function(){
        $.divselect("#divselect_work","#inputselect_work");
        $.divselect("#divselect_xl","#inputselect_xl");
        $.divselect("#divselect_yx","#inputselect_yx");
    });





