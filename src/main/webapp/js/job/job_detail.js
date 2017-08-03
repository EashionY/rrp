$(function() { 
	myonload2("../my/pers_infor.html","../../Enterprise_edition/talent_recom.html","../index.html");
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
			$("#job_comid").val(result.company_id)
		}else{
			layer.msg(data.message)
		}
	},'json')
})