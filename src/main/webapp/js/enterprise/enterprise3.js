$(function() { 
		myonload2("../my/pers_infor.html","../../Enterprise_edition/talent_recom.html","../index.html");
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
        			layer.confirm('开通企业版成功，是否前往企业版完善资料？', {icon: 3, title:'提示'}, function(index){
        				window.location.href="../../Enterprise_edition/com_informa/com_informa.html"
        			},function(){
        				window.location.href="enterprise1.html"
        			});
        			
        			
        		}else{
        			layer.msg(data.message);
        		}
        	},'json')
        }
    })