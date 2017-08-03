$(function() { 
	myonload2("../my/pers_infor.html","../../Enterprise_edition/talent_recom.html","../index.html");
	var Request = new Object(); 
	Request = GetRequest(); 
	var companyId=Request['companyId']; //获取参数
	$.get(ip+"/rrp/company/companyDetail.do",{companyId:companyId},function(data){
		if(data.state==0){
			var result=data.data;
			$(".comtop_logbox img").attr("src","../../../../"+result.logo);
			$(".company_name").html(result.name);
			$("#com_ul").html('<li>'+result.financing+'</li><li>'+result.scale+'</li><li>'+result.industry+'</li>')
			$("#com_wz").html(result.website);
			$("#com_dz").html(result.address);
			$(".company_jianjie").html(result.intro)
			$(".company_addresstext").html(result.address);
			$("#addname").val(result.address)
			
		}else{
			layer.msg(data.message)
		}
	},'json')
	//TO-DO
	$.get(ip+"/rrp/post/listPostJob.do",{companyId:companyId,status:1,page:1,pageSize:4},function(data){
		if(data.state==0){
			var result=data.data;
			console.log(result);
			
			
		}else{
			layer.msg(data.message)
		}
	},'json')
})