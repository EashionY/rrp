$(function() { 
	myonload2("../my/pers_infor.html","../../../index.html");
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
			searchByStationName(result.address,result.name,result.tel);//加载地图
			$(".job_addressleft").html(result.address);
			$(".job_rightcomname").html(result.name);
			$(".job_rightcomlogo img").attr("src","../../../../"+result.logo);
			$("#job_gm").html(result.scale)
			$("#job_rz").html(result.financing)
			$("#job_hy").html(result.industry)
			$("#job_wz").html(result.website)
			$("#job_dz").html(result.address)
			$("#job_comid").val(result.company_id);
			//相似职位
			$.get(ip+'/rrp/post/searchPostCompany.do',{keyword:result.post_name,page:1,pageSize:2},function(data){
				if(data.state==0){
					var str="";
					$.each(data.data,function(k,v){
						str+='<div class="job_xsmain"><input value="'+v.post_id+'" class="myinput"/>'+
		                     '<div class="xs_zhiwei">'+v.post_name+'</div>'+
		                     '<div class="xs_salary">'+v.salary+'</div>'+
		                     '<div class="xs_comname">'+v.name+'</div></div>';
					});
					$("#job_xszwBox").html(str);
					$(".job_xsmain").click(function() {
						window.location.href="job_detail.html?postId="+$(this).children().eq(0).val();
					});
					if(data.data[0].totalnum>2){
						$(".job_morezhiwei").css("display","block");
						$(".job_morezhiwei").on("click",function(){
					        window.location.href="recom_position.html?keyword="+result.post_name;
					    })
					}
				}else{
					
				}
			},'json')
			
			//投递简历
			$(".send").click(function(){
				var nowUrl = window.location.href;//当前url地址
				//判断是否登录
				var userId = getCookieValue("userId"); 
			    if(userId==""){//未登录，请先登录
			    	layer.confirm('请先完成登录?', {icon: 3, title:'提示'}, function(index){
			    		window.location.href="../login.html?url="+nowUrl;
			    	});
			    }else{
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
	},'json');
	
})

    
    $(".job_companyBox").on("click",function(){
        var comId=$("#job_comid").val()
        window.location.href="../company/company.html?companyId="+comId;
    });

    $(".job_addressright").on("click",function(){
        var map=$(this).parent().next();
        if(map.css("display")=="block"){
            map.css({
                "display":"none"
            });
            $(this).html("查看地图")
        }else{
            map.css({
                "display":"block"
            });
            $(this).html("关闭地图")
        }
    });

