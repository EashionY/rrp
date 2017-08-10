$(function() { 
		myonload2("../my/pers_infor.html","../index.html");
		var userId=getCookieValue("userId");
		if(userId==""){//未登录，请先登录
		     window.location.href="../login.html";
		}else{
			if(getCookieValue("email")==""){
				window.location.href="enterprise1.html"
			}else{
				$.get(ip+"/rrp/company/checkStatus.do",{email:getCookieValue("email")},function(data){
					if(data.data==false){
					 	window.location.href="enterprise1.html"
					}
				},"json");
			}
		}
	})
    
    $("#enterp_nextBtn2").on("click",function(){
        var compaName=$(".enterp_contentbox3_input input").val();
        if(compaName==""){
        	layer.msg("请先输入公司名称")
        }else{
        	$.get(ip+"/rrp/company/setCompanyName.do",{email:getCookieValue("email"),name:compaName},function(data){
        		if(data.state==0){
        			addCookie("companyName",compaName,1,"/");
        			layer.msg(data.message+",前往完善信息！",{
                		  icon: 1,
                		  time: 2000 
                  	}, function(){
                  		window.location.href="../../Enterprise_edition/com_informa/com_informa.html"
                  	}); 
        		}else{
        			layer.msg(data.message);
        		}
        	},'json')
        }
    })