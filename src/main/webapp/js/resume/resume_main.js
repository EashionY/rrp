    function mydele(url,mydata){
		console.log(mydata)
		layer.confirm('确认删除?', {icon: 3, title:'提示'}, function(index){
			$.get(url,mydata,function(data){
				if(data.state==0){
					window.location.href="resume.html"
				}else{
					layer.msg("删除失败")
				}
			},"json")
		});
	}
	var resumeId;
	$(function() { 
		myonload2("../my/pers_infor.html","../index.html");
	    var userId = getCookieValue("userId"); 
	    if(userId==""){//未登录，请先登录
	       window.location.href="../login.html";
	    }else{
	    	
	    
	    	 $.post(ip+"/rrp/resume/findByUserId.do",{userId:userId},function(data){
	    		 console.log(data.data)
	    		 if(data.state==0){//有简历
	                	resumeId=data.data.id;
	                	//简历名称
	                	$("#resume_left_editbtn").html("&#xe635;");
	                	$(".resume_lname").html(data.data.resume_name);
	                	$(".resume_left_time").html("更新："+new Date(data.data.update_time).format("yyyy-MM-dd"));
	                	//头像
	                	if(data.data.head_img!=null){
	                		$("#re_headImg").attr("src","../../../../"+data.data.head_img)
	                	}
	                	//头像处理
						var options =
				        {
				            thumbBox: '.thumbBox',
				            spinner: '.spinner',
				            imgSrc: '../../../../'+data.data.head_img
				        };
				        var cropper = $('.imageBox').cropbox(options);
				        $('#upload-file').on('change', function(){
				            var reader = new FileReader();
				            reader.onload = function(e) {
				                options.imgSrc = e.target.result;
				                cropper = $('.imageBox').cropbox(options);
				            }
				            reader.readAsDataURL(this.files[0]);
				           // this.files = [];
				        })
				        var imgMain
				        $('#btnCrop').on('click', function(){
				            imgMain= cropper.getDataURL();
				            $('#imgDiv').html('');
				            $('#imgDiv').append('<img src="'+imgMain+'" align="absmiddle" style="width:100px;margin-top:30px;border-radius:50%;box-shadow:0px 0px 12px #7E7E7E;" ><p>100px*100px</p>');
				        })
				        $('#btnZoomIn').on('click', function(){
				            cropper.zoomIn();
				        })
				        $('#btnZoomOut').on('click', function(){
				            cropper.zoomOut();
				        })
				        
				        $("#imgbtn").click(function(){
				            if(imgMain==undefined){
				                layer.msg("没有剪切图片")
				            }else{
				                $.post(ip+"/rrp/resume/modifyHeadImg.do",{id:resumeId,userId:userId,headImg:imgMain},function(res){
				                	if(res.state==0){
				                		layer.msg("上传成功",{
				                    		  icon: 1,
				                    		  time: 1000 
				                      	}, function(){
				                      		window.location.href="resume.html";
				                      	});
				                	}else{
				                		layer.msg(res.message)
				                	}
				                })
				            }
				        })
				        
	                	//个人信息板块
	                	$("#uName").html(data.data.emp_name);
	                	$("#resume_modixinxi").css("display","inline-block")
	                	$("#resume_savexinxi").css("display","none");
	                	var str='<span>'+data.data.emp_region+'</span>'+
	                	'<span>'+data.data.work_exp+'工作经验</span>'+
	                	'<span>'+data.data.sex+'</span>'+
	                	'<span>'+data.data.top_degree+'</span>'+
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
	                			'<span style="display:none" class="skillID">'+skills[k].skill_id+'</span>'+
	                			'<span>'+skills[k].skill_name+'</span>'+
	                            '<span class="edit_font ">'+
	                                '<span class="myfont edit_fontpingjia skill_modi">&#xe635;</span>'+
	                                '<span class="myfont workjlbox_dele2 edit_fontpingjia skill_dele">&#xe60c;</span>'+
	                            '</span></div><div class="">';
	                            var str2='';
	                            if(skills[k].skill_level=='精通'){
	                            	str2='<span class="jineng jineng_jt"></span><span class="pingjia_text">精通</span></div></div>'
	                            }else if(skills[k].skill_level=='熟练'){
	                            	str2='<span class="jineng jineng_sl"></span><span class="pingjia_text">熟练</span></div></div>'
	                            }else if(skills[k].skill_level=='良好'){
	                            	str2='<span class="jineng jineng_lh"></span><span class="pingjia_text">良好</span></div></div>'
	                            }else if(skills[k].skill_level=='一般'){
	                            	str2='<span class="jineng jineng_yb"></span><span class="pingjia_text">一般</span></div></div>'
	                            };
	                            $("#pingjiabox").append(str1+str2);
	                		});
	                		$(".skill_dele").click(function(){//删除技能评价
	                			var skillId=$(this).parent().siblings(".skillID").html();
	                			mydele(ip+'/rrp/resume/deleteSkill.do',{userId:getCookieValue("userId"),skillId:skillId})
	                		});
	                		$(".skill_modi").click(function(){//修改技能评价
	                			var skillId=$(this).parent().siblings(".skillID").html();
	                			mydisplay($("#pingjiabox"),$("#pingjia_mask"));
	                			$("#resume_modipingjia").css("display","inline-block")
	     	                	$("#resume_savepingjia").css("display","none");
	                			$("#skillName").val($(this).parent().prev().html());
	                			$("#skillcd").html($(this).parent().parent().next().children(".pingjia_text").html());
	                			$("#resume_modipingjia").click(function(){
	                				 $.ajax({
	                						type: "post",
	                			            url: ip+"/rrp/resume/modifySkill.do",
	                			            data:{userId:getCookieValue("userId"),skillId:skillId,skillName:$("#skillName").val(),skillLevel:$("#skillcd").html()},
	                			            dataType: "json",
	                			            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
	                			            success: function(data){
	                			            	if(data.state==0){window.location.href="resume.html";}else{layer.msg("修改失败")}
	                			            }
	                					})
	                			 })
	                		})
	                	}
	                	//项目经验模块
	                	if(data.data.projectExp.length!=0){
	                		var projectExp=data.data.projectExp;
	                		$.each(projectExp,function(k,v){
	                			var str='<div class="resume_workbox"><div>'+
	                			'<span style="display:none" class="projectID">'+projectExp[k].projectexp_id+'</span>'+
	                            '<span>'+projectExp[k].project+'</span>'+
	                            '<span class="edit_font">'+
	                                '<span class="myfont project_modi">&#xe635;</span>'+
	                                '<span class="myfont workjlbox_dele project_dele">&#xe60c;</span>'+
	                            '</span></div>'+
	                            '<div class="resume_workbox_div2"><span>'+projectExp[k].project_time+'</span></div>'+
	                            '<div class="detail_div"><div>项目描述：</div>'+
	                            '<div class="detail_span">'+projectExp[k].project_description+'</div></div>'+
	                            '<div class="detail_div"><div>责任描述：</div>'+
	                            '<div class="detail_span">'+projectExp[k].duty+'</div></div></div>';
	                			$("#projectjlbox").append(str);
	                		})
	                		$(".project_modi").click(function(){//修改项目经验  
	                			mydisplay($("#projectjlbox"),$("#project_mask"));
	                			var projectID=parseInt($(this).parent().siblings(".projectID").html());
	                			var arr=$(this).parent().parent().next().children().html().split("至");
	                			$("#projectName").val($(this).parent().prev().html());
	                		    $("#xiangmutime1").val(arr[0]);
	                		    $("#xiangmutime2").val(arr[1]);
	                		    xiangmu.setContent($(this).parent().parent().next().next().children(".detail_span").html());
	                		    zeren.setContent($(this).parent().parent().next().next().next().children(".detail_span").html());
	                		    $("#resume_modiproject").css("display","inline-block");
	                			$("#resume_saveproject").css("display","none");
	                		    $("#resume_modiproject").click(function(){
	                				 $.ajax({
	                						type: "post",
	                			            url: ip+"/rrp/resume/modifyProjectExp.do",
	                			            data:{userId:getCookieValue("userId"),projectexpId:projectID,project:$("#projectName").val(),projectTime:$("#xiangmutime1").val()+'至'+$("#xiangmutime2").val(),projectDescription:xiangmu.getContent(),duty:zeren.getContent()},
	                			            dataType: "json",
	                			            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
	                			            success: function(data){
	                			            	if(data.state==0){window.location.href="resume.html";}else{layer.msg("修改失败")}
	                			            }
	                					})
	                			 })
	                		})
	                		$(".project_dele").click(function(){//删除项目经验   
	                			var projectID=parseInt($(this).parent().siblings(".projectID").html());
	                			mydele(ip+'/rrp/resume/deleteProjectExp.do',{userId:getCookieValue("userId"),projectexpId:projectID})
	                		})
	                	}
	                	//教育经验模块
	                	if(data.data.educationExp.length!=0){
	                		var educaExp=data.data.educationExp;
	                		$.each(educaExp,function(k,v){
	                			var str='<div class="resume_workbox"><div>'+
	                			'<span style="display:none" class="jiaoyuID">'+educaExp[k].educationexp_id+'</span>'+
	                            '<span>'+educaExp[k].school+'</span>'+
	                            '<span class="edit_font"><span class="myfont jiaoyu_modi">&#xe635;</span>'+
	                            '<span class="myfont workjlbox_dele jiaoyu_dele">&#xe60c;</span></span></div>'+
	                            '<div class="resume_jiaoyubox_div2">'+
	                            '<span>'+educaExp[k].major+'</span><span>'+educaExp[k].education+'</span>'+
	                            '<span>'+educaExp[k].school_time+'</span></div></div>'
	                        	$("#jiaoyubox").append(str) 
	                		})
	                		$(".jiaoyu_modi").click(function(){//修改教育经验
	                			var jiaoyuID=parseInt($(this).parent().siblings(".jiaoyuID").html());
	                			mydisplay($("#jiaoyubox"),$("#jiaoyu_mask"));
	                		    $("#resume_modijiaoyu").css("display","inline-block");
	                			$("#resume_savejiaoyu").css("display","none");
	                			$("#school").val($(this).parent().prev().html());
	                			$("#major").val($(this).parent().parent().next().children().eq(0).html());
	                			var jytime=$(this).parent().parent().next().children().eq(2).html().split('至')
	                			$("#jiaoyutime1").val(jytime[0])
	                		    $("#jiaoyutime2").val(jytime[1]);
	                			$("#education").html($(this).parent().parent().next().children().eq(1).html());
	                			$("#resume_modijiaoyu").click(function (){
	                				$.ajax({
                						type: "post",
                			            url: ip+"/rrp/resume/modifyEducationExp.do",
                			            data:{userId:getCookieValue("userId"),educationexpId:jiaoyuID,school:$("#school").val(),major:$("#major").val(),schoolTime:$("#jiaoyutime1").val()+"至"+$("#jiaoyutime2").val(),education:$("#education").html()},
                			            dataType: "json",
                			            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
                			            success: function(data){
                			            	if(data.state==0){window.location.href="resume.html";}else{layer.msg("修改失败")}
                			            }
                				    })
								})
	                		})
	                		$(".jiaoyu_dele").click(function(){//删除教育经验   
	                			var jiaoyuID=parseInt($(this).parent().siblings(".jiaoyuID").html());
	                			mydele(ip+'/rrp/resume/deleteEducationExp.do',{userId:getCookieValue("userId"),educationexpId:jiaoyuID})
	                		})
	                	}
	                	//工作经验模块  
	                	if(data.data.workExprience.length!=0){
	                		var workExp=data.data.workExprience;
	                        $.each(workExp,function(k,v){
	                        	var str='<div class="resume_workbox"><div>'+
	                        	'<span style="display:none" class="workID">'+workExp[k].workexp_id+'</span>'+
		                		'<span>'+workExp[k].company+'</span>'+
		                        '<span class="edit_font">'+
		                        '<span class="myfont work_modify">&#xe635;</span>'+
		                        '<span class="myfont workjlbox_dele work_dele">&#xe60c;</span></span></div>'+
		                        '<div class="resume_workbox_div2">'+
		                        '<span>'+workExp[k].work+'</span><span>'+workExp[k].work_time+'</span></div>'+
		                        '<div>'+workExp[k].work_description+'</div></div>'
	                        	$("#workjlbox").append(str);
	                        })
	                		$(".work_modify").click(function(){//修改工作经验
	                			 var workexpId=$(this).parent().siblings(".workID").html();
	                			 mydisplay($("#workjlbox"),$("#workjl_mask"));
	                			 $("#resume_modiwork").css("display","inline-block")
	     	                	 $("#resume_savework").css("display","none");
	                			 var arr=$(this).parent().parent().next().children().eq(1).html().split("至");
	                			 $("#re_company").val($(this).parent().prev().html());
	                			 $("#re_work").val($(this).parent().parent().next().children().eq(0).html());
	                			 $("#zizhitime1").val(arr[0]);
	                			 $("#zizhitime2").val(arr[1]);
	                			 workJy.setContent($(this).parent().parent().next().next().html());
	                			 $("#resume_modiwork").click(function(){
	                				 $.ajax({
	                						type: "post",
	                			            url: ip+"/rrp/resume/modifyWorkExp.do",
	                			            data:{userId:getCookieValue("userId"),workexpId:workexpId,company:$("#re_company").val(),work:$("#re_work").val(),workTime:$("#zizhitime1").val()+"至"+$("#zizhitime2").val(),workDescription:workJy.getContent()},
	                			            dataType: "json",
	                			            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
	                			            success: function(data){
	                			            	if(data.state==0){window.location.href="resume.html";}else{layer.msg("修改失败")}
	                			            }
	                				 })
	                			 })
	                		})
	                		$(".work_dele").click(function(){//删除工作经验   
	                			var workexpId=parseInt($(this).parent().siblings(".workID").html());
	                			mydele(ip+'/rrp/resume/deleteWorkExp.do',{userId:getCookieValue("userId"),workexpId:workexpId})
	                		})
	                	}
						// 预览简历
	                	$("#to_preview_resume").click(function(){
	                	    window.location.href="resume_preview.html"
	                	}) 	
	    		 }else{//无简历
	    			 $(".resume_lname").html("暂无简历");
	    			// 预览简历
	                $("#to_preview_resume").click(function(){
	                	layer.msg("暂无简历可预览，请先完善简历")
	                })
	    		 }
	    		 $("#imgbtn_quit").click(function(){
			            $("#img_mask").css("display","none");
			        })
	    	  },'json');
	    }
	});
	$("#resume_savejiaoyu").click(function(){//添加教育经历
		var school=$("#school").val();
		var major=$("#major").val();
		var schooltime=$("#jiaoyutime1").val()+" 至 "+$("#jiaoyutime2").val();
		var education=$("#education").html();
		$.ajax({
			type: "post",
            url: ip+"/rrp/resume/addEducationExp.do",
            data: {userId:getCookieValue("userId"),resumeId:resumeId,school:school,major:major,schoolTime:schooltime,education:education},
            dataType: "json",
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            success: function(data){
            	if(data.state==0){
            		window.location.href="resume.html";
                }else{
                	layer.msg(data.message)
                }
            }
		})
	})
	$("#resume_saveproject").click(function(){//添加项目经验
		var projectName=$("#projectName").val();
		var xiangmutime=$("#xiangmutime1").val()+" 至 "+$("#xiangmutime2").val();
		var xiangmu_content=xiangmu.getContent();
		var zeren_content=zeren.getContent();
		$.ajax({
			type: "post",
            url: ip+"/rrp/resume/addProjectExp.do",
            data: {userId:getCookieValue("userId"),resumeId:resumeId,project:projectName,projectTime:xiangmutime,projectDescription:xiangmu_content,duty:zeren_content},
            dataType: "json",
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            success: function(data){
            	if(data.state==0){
                	window.location.href="resume.html";
                }else{
                	layer.msg(data.message)
                }
            }
		})
	})
	$("#resume_savework").click(function(){//添加工作经验
		var re_company=$("#re_company").val();
		var re_work=$("#re_work").val();
		var zizhitime=$("#zizhitime1").val()+" 至 "+$("#zizhitime2").val();
		var work_content=workJy.getContent();
		$.ajax({
			type: "post",
            url: ip+"/rrp/resume/addWorkExp.do",
            data: {userId:getCookieValue("userId"),resumeId:resumeId,company:re_company,work:re_work,workTime:zizhitime,workDescription:work_content},
            dataType: "json",
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            success: function(data){
            	if(data.state==0){
                	window.location.href="resume.html";
                }else{
                	layer.msg(data.message)
                }
            }
		})
	})
	
	$("#resume_savepingjia").click(function(){//添加技能   
		console.log("保存")
		$.ajax({
			type: "post",
            url: ip+"/rrp/resume/addSkill.do",
            data: {userId:getCookieValue("userId"),resumeId:resumeId,skillName:$("#skillName").val(),skillLevel:$("#skillcd").html()},
            dataType: "json",
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            success: function(data){
            	if(data.state==0){
                	window.location.href="resume.html";
                }else{
                	layer.msg(data.message)
                }
            }
		})
	})
	$("#resume_saveziping").click(function(){//修改自我描述
		$.ajax({
			type: "post",
            url: ip+"/rrp/resume/modifySelfEvaluation.do",
            data: {id:resumeId,userId:getCookieValue("userId"),selfEvaluation:ziping.getContent()},
            dataType: "json",
            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
            success: function(data){
            	if(data.state==0){
                	window.location.href="resume.html";
                }else{
                	layer.msg(data.message)
                }
            }
		})
	})
	function is_null(arr){
		var bool=true;
		$.each(arr,function(k,v){
			if(arr[k]==""){
				bool=false;
			}
		})
		return bool;
	}
	$("#resume_saveyx").click(function(){//修改求职意向
		var sala=$("#qwsalary").html();
		var qwjob=$("#qwjob").val();
		var workType=$("#workType").html();
		var workArea=$("#workArea").val();
		var yxList=new Array();
		yxList.push(sala)
		yxList.push(qwjob)
		yxList.push(workType)
		if(is_null(yxList)){
			$.ajax({
				type: "post",
	            url: ip+"/rrp/resume/modifyJobIntention.do",
	            data: {id:resumeId,userId:getCookieValue("userId"),salary:sala,job:qwjob,workType:workType,workArea:workArea},
	            dataType: "json",
	            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
	            success: function(data){
	            	if(data.state==0){
	                	window.location.href="resume.html";
	                }else{
	                	layer.msg(data.message)
	                }
	            }
			})
		}else{
			layer.msg("未填写全面")
		}
	})
	
	$("#resume_modixinxi").click(function(){//修改信息
		 	var userId = getCookieValue("userId"); 
	        var empName=$("#uname").val();
	        var sex=$(".resume_mysex").html();
	        var birth=$("#birthday").val();
	        var workexp=$("#startwork").val();
	        var status=$("#ustate").html();
	        var phone=$("#uphone").val();
	        var email=$("#uemail").val();
	        var empRegion=$("#ucity").val();
	        var topDegree=$("#uhighxl").html();
	        $.ajax({
	            type: "post",
	            url: ip+"/rrp/resume/modifyBasicInfo.do",
	            data: {userId:userId,id:resumeId,empName:empName,sex:sex,birth:birth,workexp:workexp,status:status,phone:phone,email:email,empRegion:empRegion,topDegree:topDegree},
	            dataType: "json",
	            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
	            success: function(data){
	          	    console.log(data);
	                if(data.state==0){
	                	window.location.href="resume.html";
	                }else{
	                	layer.msg(data.message)
	                }
	            }
	        });
	})
	 $("#resume_savexinxi").click(function(){//添加信息
		    var id = getCookieValue("userId"); 
	        var empName=$("#uname").val();
	        var sex=$(".resume_mysex").html();
	        var birth=$("#birthday").val();
	        var workexp=$("#startwork").val();
	        var status=$("#ustate").html();
	        var phone=$("#uphone").val();
	        var email=$("#uemail").val();
	        var empRegion=$("#ucity").val();
	        var topDegree=$("#uhighxl").html();
	        //var xinxi=new Array();
	        //xinxi.push(empName),xinxi.push(sex),xinxi.push(birth),xinxi.push(workexp);
	        //xinxi.push(status),xinxi.push(phone),xinxi.push(email),xinxi.push(empRegion);
	        $.ajax({
	            type: "post",
	            url: ip+"/rrp/resume/addBasicInfo.do",
	            data: {userId:id,empName:empName,sex:sex,birth:birth,workexp:workexp,status:status,phone:phone,email:email,empRegion:empRegion,topDegree:topDegree},
	            dataType: "json",
	            contentType:'application/x-www-form-urlencoded; charset=UTF-8',
	            success: function(data){
	          	    console.log(data);
	                if(data.state==0){
	                	window.location.href="resume.html";
	                }else{
	                	layer.msg(data.message)
	                }
	            }
	        });
	   })
	   //修改简历名
	   $("#resume_left_mask_save").click(function(){
		   var uid = getCookieValue("userId"); 
		   var reName=$("#new_reName").val();
		   $.get(ip+"/rrp/resume/modifyResumeName.do",{id:resumeId,userId:uid,resumeName:reName},function(data){
			   if(data.state==0){
               		window.location.href="resume.html";
               }else{
               		layer.msg(data.message)
               }
		   },'json')
	   })
	   //修改头像
	   $(".resume_touxiang").click(function(){
		   $(".mymask").css("display","block")
	   });