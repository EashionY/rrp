$(function() { 
	myonload2("../my/pers_infor.html","../../../index.html");
	var Request = new Object(); 
	Request = GetRequest(); 
	var companyId=Request['companyId']; //获取参数
	$.get(ip+"/rrp/company/companyDetail.do",{companyId:companyId},function(data){
		if(data.state==0){
			var result=data.data;
			//console.log(result)
			$(".comtop_logbox img").attr("src","../../../../"+result.logo);
			$(".company_name").html(result.name);
			$("#com_ul").html('<li>'+result.financing+'</li><li>'+result.scale+'</li><li>'+result.industry+'</li>')
			$("#com_wz").html(result.website);
			$("#com_dz").html(result.address);
			$(".company_jianjie").html(result.intro)
			$(".company_addresstext").html(result.address);
			searchByStationName(result.address,result.name,result.tel)
		}else{
			layer.msg(data.message)
		}
	},'json')
	$.get(ip+"/rrp/post/listPostJob.do",{companyId:companyId,status:1,page:1,pageSize:10},function(data){
		if(data.state==0){
			var result=data.data;
			$(".company_zhiweiBox").html("");
			//console.log(result);
			var str='';
			var myDate = new Date();
			var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy/MM/dd");
			$.each(result,function(k,v){
				var postDate=new Date(result[k].post_time).format("yyyy/MM/dd");
				var showDate=''
				if(nowDate==postDate){//当天发布
					showDate=new Date(result[k].post_time).format("hh:mm")
				}else{
					showDate=new Date(result[k].post_time).format("yy年MM月dd日")
				}
				if(window.screen.width<1024){
					str+='<div class="company_zhiweibox"><input value="'+result[k].post_id+'" class="myinput"/><div class="com_zhiwei"> ' +
					'<div>'+result[k].post_name+'<span>['+result[k].region+']</span></div> ' +
					'<ul class="down"> <li>经验'+result[k].work_exp+'</li> <li class="li2">'+result[k].degree+'</li> </ul> </div> <div> ' +
					'<div class="salary_color">'+result[k].salary+'</div><div class="down">'+showDate+' 发布</div> </div> </div>'
				}else{
					str+='<div class="company_zhiweibox"><input value="'+result[k].post_id+'" class="myinput"/><div class="com_zhiwei">'+
                    '<div>'+result[k].post_name+'<span>['+result[k].region+']</span></div><div>'+showDate+'  发布</div>'+
                    '</div><ul><li>'+result[k].salary+'</li><li>经验'+result[k].work_exp+'</li><li>'+result[k].degree+'</li></ul></div>'
				}
				
			})
			$(".company_zhiweiBox").html(str);
			$(".company_zhiweibox").click(function(){
				window.location.href="../job/job_detail.html?postId="+$(this).children("input").val();
			})
		}else{
			layer.msg(data.message)
		}
	},'json')
})