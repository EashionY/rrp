$(function() { 
	myonload2("../my/pers_infor.html","../index.html");
	var Request = new Object(); 
	Request = GetRequest(); 
	var postId=Request['postId']; //获取参数
	$.get(ip+"/rrp/post/jobDetail.do",{postId:postId},function(data){
		if(data.state==0){
			var result=data.data[0];
			$(".job_name").html(result.post_name);
			$(".job_companyname").html(result.name);
			$(".job_salary").html(result.salary);
			$("#job_ul").html('<li>'+result.region+'</li><li>'+result.work_exp+'</li><li>'+result.degree+'</li><li>'+result.work_type+'</li>')
			$("#job_ldul").html("");
			var ldStr='';
			var ldList=result.benefits.split('，');
			$.each(ldList,function(k,v){
				ldStr+='<li>'+ldList[k]+'</li>'
			})
			$("#job_ldul").html(ldStr);
			$("#job_yaoqiu").html(result.requirement);
			$("#job_duty").html(result.duty)
			$(".job_comdetail").html(result.info);
			$("#name").val(result.name);
			$("#phone").val(result.tel);
			$(".job_addressleft").html(result.address);
			$(".job_rightcomname").html(result.name);
			$(".job_rightcomlogo img").attr("src","../../../../"+result.logo);
			$("#job_gm").html(result.scale)
			$("#job_rz").html(result.financing)
			$("#job_hy").html(result.industry)
			$("#job_wz").html(result.website)
			$("#job_dz").html(result.address)
			$("#job_comid").val(result.company_id);
			
			$(".send").click(function(){//投递简历
				var nowUrl = window.location.href;//当前url地址
				//判断是否登录
				var userId = getCookieValue("userId"); 
			    if(userId==""){//未登录，请先登录
			    	layer.confirm('请先完成登录?', {icon: 3, title:'提示'}, function(index){
			    		window.location.href="../login.html?url="+nowUrl;
			    	});
			    }else{
			       //console.log(postId);//职位id
				   //console.log(result.id);//公司id
				   var resumeId='';
				   $.get(ip+"/rrp/resume/findByUserId.do",{userId:userId},function(data){
					   if(data.state==0){
						   resumeId=data.data.id;
						   //console.log(resumeId);//简历id
						   $.get(ip+'/rrp/delivery/deliveryResume.do',{resumeId:resumeId,companyId:result.id,postId:postId},function(data){
							   if(data.state==0){
								   layer.confirm(data.message+"，是否前往投递箱查看？", {icon: 3, title:'提示'}, function(index){
							    		window.location.href="../delivery_box/delivery_success.html";//前往投递箱页面
								   });
							   }else{
								   layer.msg(data.message)
							   }
						   },'json')
						   
					   }else{
						   layer.msg(data.message)
					   }
				   },'json');
			    }
					
			})
		}else{
			layer.msg(data.message)
		}
	},'json')
})

