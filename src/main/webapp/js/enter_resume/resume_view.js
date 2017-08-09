$(function() {
	myonload1("../com_informa/com_xinxi.html","../com_informa/com_psd.html","../../Personal_edition/index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var Request = new Object(); 
		Request = GetRequest(); 
		var userId2=Request['userId']; //获取参数
		var deliveryId=Request['deliveryId']; //获取参数
		var prevUrl= Request['prevUrl'];
		var state= Request['state'];
		if(state==0){//设置当前状态
			$("#resu_state").html("新简历")
		}else if(state==1){
			$("#resu_state").html("已查看")
		}else if(state==2){
			$("#resu_state").html("有意向")
		}else if(state==3){
			$("#resu_state").html("邀请面试")
		}else if(state==4){
			$("#resu_state").html("不合适")
		}
		$("#uId").val(userId2);
		$.get(ip+'/rrp/resume/findByUserId.do',{userId:userId2},function(data){
			if(data.state==0){
				//console.log(data.data);
				$("#revi_job").html(data.data.job);
				$("#revi_head").attr("src","../../../../"+data.data.head_img);
				$(".resu_name").html(data.data.emp_name);
				$("#re_name").html(data.data.emp_name);
				var myDate = new Date();
			    var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy");
			    var birth=new Date(data.data.birth).format("yyyy");
			    var year=parseInt(nowDate)-parseInt(birth);//计算年龄
				$("#revi_sex").html('<span>'+data.data.sex+'/'+year+'岁</span><span>'+data.data.top_degree+'</span><span>'+data.data.work_exp+'</span>')
				$("#revi_tel").html(data.data.phone);
				$("#revi_email").html(data.data.email);
				
				$("#divselect_re ul li a").click(function(){
			    	var state=$(this).attr("selectid");
			    	if($(this).html()=="邀请面试"){
			    		layer.confirm('确定向'+data.data.emp_name+'发起面试邀请？', {icon: 3, title:'提示'}, function(index){
				    		$.get(ip+"/rrp/delivery/dealResume.do",{deliveryId:deliveryId,deliveryStatus:state},function(data){
				    			//console.log(data);
				    			if(data.state==0){
				    				layer.msg(data.message,{
				                	   icon: 1,
				                	   time: 1000 
				                  	}, function(){
				                  	   window.location.href=prevUrl;//返回之前页面
				                  	}); 
				    			}else{
				    				layer.msg(data.message)
				    			}
				    		},'json')
				    	});
			    	}else{
			    		layer.confirm('确认修改状态为'+$(this).html()+'？', {icon: 3, title:'提示'}, function(index){
			    			$.get(ip+"/rrp/delivery/dealResume.do",{deliveryId:deliveryId,deliveryStatus:state},function(data){
				    			//console.log(data);
				    			if(data.state==0){
				    				layer.msg(data.message,{
					                	   icon: 1,
					                	   time: 1000 
					                  	}, function(){
					                  	   window.location.href=prevUrl;//返回之前页面
					                  	}); 
				    			}else{
				    				layer.msg(data.message)
				    			}
				    		},'json')
				    	});
			    	}
			        
			    });
				
			}else{
				layer.msg(data.message)
			}
		},'json')
	}
})   






//    选择
    $(function(){
        $.divselect("#divselect_re","#inputselect_re");
    });
    

//    弹出层
    $(".reView_savebtn").click(function(){
        console.log("保存")
        $("#resuView_mask").css("display","none")
    });
    $(".reView_quitbtn").click(function(){
        $("#resuView_mask").css("display","none");
        $("#divselect_re cite").children(".input_text").html("")
        $("#inputselect_re").val("")
    });