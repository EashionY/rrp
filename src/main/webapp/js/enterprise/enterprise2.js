	var email=""
	$(function() { 
		myonload2("../my/pers_infor.html","../index.html");
		var userId=getCookieValue("userId");
		if(userId==""){//未登录，请先登录
		     window.location.href="../login.html";
		}else{
			if(getCookieValue("email")==""){
				window.location.href="enterprise1.html"
			}else{
				email=getCookieValue("email"); 
				$("#enterp_contentbox2_email").html(email)
			}
		}
	})
	$("#email_nextBtn").click(function(){//继续下一步
		if(email!=""){
			$.get(ip+"/rrp/company/checkStatus.do",{email:email},function(data){
				if(data.state==0){
					if(data.data==true){
						window.location.href="enterprise3.html"
					}else{
						layer.msg("请先前往邮箱验证")
					}	
				}else{
					layer.msg(data.message)
				}
				
			},"json");
		}else{
			layer.msg("没有邮箱")
		}
		
	});
	$("#prev_btn").click(function(){//返回上一步
		window.location.href="enterprise1.html"
	})
	$("#again_btn").click(function(){//重新发送邮件验证码
		var index=layer.load(1,{
			content:'发送邮件中...',
			success: function(layero){
				layero.find('.layui-layer-content').css({
				    'padding-left': '40px',
			    	'width': '110px',
			    	'line-height': '40px',
			    	'font-size': '18px'
				});
				$.post(ip+"/rrp/company/sendEmail.do",{userId:getCookieValue("userId"),email:email},function(data){
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
		});
	})