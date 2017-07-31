$(function() { 
	myonload2("../my/pers_infor.html","../../Enterprise_edition/talent_recom.html","../index.html");
    var userId = getCookieValue("userId"); 
    if(userId==""){//未登录，请先登录
       window.location.href="../login.html";
    }else{
    	$.ajax({
            type: "post",
            url: ip+"/rrp/resume/findByUserId.do",
            data: {userId:userId},
            dataType: "json",
            success: function(data){
            	console.log(data.data)
            	//基本信息
            	$("#uName").html(data.data.emp_name);
            	var str='<span>'+data.data.emp_region+'</span>'+
            	'<span>'+data.data.work_exp+'工作经验</span>'+
            	'<span>'+data.data.sex+'</span>'+
            	'<span>'+data.data.status+'</span>'
            	$(".resume_rdiv1_addre").html(str);
            	$("#phone").html(data.data.phone);
            	$("#email").html(data.data.email);
            	//求职意向板块
            	if(data.data.salary!=""&&data.data.work_type!=null&&data.data.job!=""&&data.data.work_area!=null){
            		var yxstr1='<div class="resume_yixiang">'+
     	           				'<span>期望薪资：'+data.data.salary+'/月</span>'+
                    			'<span>工作类型：'+data.data.work_type+'</span></div>';
        			 var yxstr2='<div class="resume_yixiang">'+
     	           				'<span>期望职位：'+data.data.job+'</span>'+
                    			'<span>地点：'+data.data.work_area+'</span></div>'
     				$("#yixiangbox").append(yxstr1)
     				$("#yixiangbox").append(yxstr2)
            	}
            	//自我描述模块
            	if(data.data.self_evaluation!=null){
            		$("#zipingbox").append('<div class="resume_zhipingbox"><p>'+data.data.self_evaluation+'</p></div>')
            	}
            	//技能评价模块
            	if(data.data.skills.length!=0){
            		var skills=data.data.skills;
            		$.each(skills,function(k,v){
            			var str1='<div class="resume_pingjiabox"><div>'+
                        '<span>'+skills[k].skill_name+'</span></div><div class="">';
                        var str2='';
                        if(skills[k].skill_level=='精通'){
                        	str2='<span class="jinengpre jineng_jt"></span><span class="pingjia_text">精通</span></div></div>'
                        }else if(skills[k].skill_level=='熟练'){
                        	str2='<span class="jinengpre jineng_sl"></span><span class="pingjia_text">熟练</span></div></div>'
                        }else if(skills[k].skill_level=='良好'){
                        	str2='<span class="jinengpre jineng_lh"></span><span class="pingjia_text">良好</span></div></div>'
                        }else if(skills[k].skill_level=='一般'){
                        	str2='<span class="jinengpre jineng_yb"></span><span class="pingjia_text">一般</span></div></div>'
                        };
                        $("#pingjiabox").append(str1+str2);
            		});
            	}
            	//项目经验模块
            	if(data.data.projectExp.length!=0){
            		var projectExp=data.data.projectExp;
            		$.each(projectExp,function(k,v){
            			var str='<div class="resume_workbox"><div>'+
                        '<span>'+projectExp[k].project+'</span></div>'+
                        '<div class="resume_workbox_div2">'+
                        '<span>'+projectExp[k].project_time+'</span></div>'+
                        '<div class="detail_div"><div>项目描述：</div>'+
                        '<div class="detailpre_span">'+projectExp[k].project_description+'</div></div>'+
                        '<div class="detail_div"><div>责任描述：</div>'+
                        '<div class="detailpre_span">'+projectExp[k].duty+'</div></div></div>';
            			$("#projectjlbox").append(str);
            		})
            	}
            	//教育经验模块
            	if(data.data.educationExp.length!=0){
            		var educaExp=data.data.educationExp;
            		$.each(educaExp,function(k,v){
            			var str='<div class="resume_workbox"><div>'+
                        '<span>'+educaExp[k].school+'</span></div>'+
                        '<div class="resume_jiaoyubox_div2">'+
                        '<span>'+educaExp[k].major+'</span><span>'+educaExp[k].education+'</span><span>'+educaExp[k].school_time+'</span></div></div>'
                    	$("#jiaoyubox").append(str) 
            		})
            	}
            	//工作经验模块  
            	if(data.data.workExprience.length!=0){
            		var workExp=data.data.workExprience;
                    $.each(workExp,function(k,v){
                    	var str='<div class="resume_workbox">'+
                            '<div><span>'+workExp[k].company+'</span></div>'+
                            '<div class="resume_workbox_div2">'+
                            '<span>'+workExp[k].work+'</span><span>'+workExp[k].work_time+'</span></div><div>'+workExp[k].work_description+'</div></div>'
                    	$("#workjlbox").append(str);
                    })
            	}
            }
        });
    }
});