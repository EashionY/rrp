$(function() { 
		myonload2("pers_infor.html","../../Enterprise_edition/talent_recom.html","../index.html");
		var userIdValue = getCookieValue("userId"); 
		//console.log(userIdValue)
		if(userIdValue==""){//未登录，请先登录
		    window.location.href="../login.html";
		}else{
		    $.get(ip+"/rrp/user/findUserInfo.do",{userId:userIdValue},function(data){
		    	if(data.state==0){
		    		//console.log(data.data);
		    		$("#my_headimg").attr("src",'../../../../'+data.data.headImg)
		    		$("#uname1").html(data.data.nickname)
		    		$(".my_zhiye").html(data.data.job)
		    		$("#usex1").html(data.data.sex)
		    		$("#udegree1").html(data.data.degree)
		    		$("#uselfIntro1").html(data.data.selfIntro)
		    		//头像处理
					var options =
			        {
			            thumbBox: '.thumbBox',
			            spinner: '.spinner',
			            imgSrc: '../../../../'+data.data.headImg
			        };
			        var cropper = $('.imageBox').cropbox(options);
			        $('#upload-file').on('change', function(){
			            var reader = new FileReader();
			            reader.onload = function(e) {
			                options.imgSrc = e.target.result;
			                cropper = $('.imageBox').cropbox(options);
			            }
			            reader.readAsDataURL(this.files[0]);
			          //  this.files = [];
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
			                $.post(ip+"/rrp/user/modifyHeadImg.do",{userId:userIdValue,base64:imgMain},function(res){
			                	if(res.state==0){
			                		layer.msg("上传成功")
			                		$("#my_headImg2").attr("src",'../../../../'+res.data.headImg);
			                		$("#img_mask").css("display","none");
			                	}else{
			                		layer.msg(res.message)
			                	}
			                })
			            }
			        })
			        $("#imgbtn_quit").click(function(){
			            $("#img_mask").css("display","none");
			        })
			        
		    	}else{
		    		layer.msg(data.message)
		    	}
		    },'json')	
		}
		
		
		
	});
	
	$(".myedit_savebtn").click(function () {//修改个人信息
		var uid = getCookieValue("userId");
		var uname=$("#uname").val();
		var usex=$(".mysex").html();
		var ujob=$("#ujob").val();
		var udegree=$("#udegree").val();
		var uselfIntro=$("#work_content").val();
		console.log(uid,uname,usex,ujob,udegree,uselfIntro);
		$.ajax({
            type: "post",
            url: ip+"/rrp/user/modifyUserInfo.do",
            data: {userId:uid,nickname:uname,sex:usex,job:ujob,degree:udegree,selfIntro:uselfIntro},
            dataType: "json",
            success: function(data){
          	    console.log(data);
                if(data.state==0){
                	layer.msg(data.message,{
                		  icon: 1,
                		  time: 1000 
                  	}, function(){
                  		deleteCookie("userName","/");
                  	    addCookie("userName",uname,1,"/"); 
                    	window.location.href="pers_infor.html";
                  	}); 
                }else{
                	layer.msg(data.message)
                }
            }
        });
	})
	$(".myedit_touxiang").click(function(){
        $("#img_mask").css("display","block")
    })
	$(".myedit_quitbtn").click(function () {
		window.location.href="pers_infor.html";
		$(".my_rightmain1").css("display","block");
	    $(".my_rightmain2").css("display","none")
	})
	