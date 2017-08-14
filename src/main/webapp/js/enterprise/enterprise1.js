    var userId="";
	$(function() { 
		myonload2("../my/pers_infor.html","../index.html");
		userId=getCookieValue("userId");
		if(userId==""){//未登录，请先登录
		      window.location.href="../login.html";
		}else{
			var comStatus=getStatus();
			console.log(comStatus)
			if(comStatus==0){
				$("#enterp_phone").val(getCookieValue("userPhone"));
			}else{
				layer.msg("已经验证邮箱",{
		    	    icon: 7,
		    		time: 3000 
		      	}, function(){
		      		window.location.href="../../Enterprise_edition/com_informa/com_informa.html";
		      	});
			}
		}
	})
	$("#enterp_nextBtn").on("click",function(){
		        var szReg=/^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
		        var email=$("#enterp_email").val();
		        if(email==""){
		            layer.msg("请先填写邮箱地址")
		        }else{
		        	if(!szReg.test(email)){
	            		layer.msg("邮箱格式错误")
	                }else{
	                	var index=layer.load(1,{
                			content:'发送邮件中...',
                			success: function(layero){
                				layero.find('.layui-layer-content').css({
                				    'padding-left': '40px',
                			    	'width': '110px',
                			    	'line-height': '40px',
                			    	'font-size': '18px'
                				});
                			}
                		});
	                	$.post(ip+"/rrp/company/sendEmail.do",{userId:userId,email:email},function(data){
	                		if(data.state==0){
	                			layer.close(index);
	                			addCookie("email",email,1,"/");
	                    		window.location.href="enterprise2.html"
	                		}else{
	                			layer.close(index);
	                			layer.msg(data.message)
	                		}
	                	},"json");
	                }
		        }
		})	