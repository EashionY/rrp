$(function() { 
	myonload2("../my/pers_infor.html","../../Enterprise_edition/talent_recom.html","../index.html");
	var userId = getCookieValue("userId"); 
	if(userId==""){//未登录，请先登录
	   window.location.href="../login.html";
	}else{
	   $.get(ip+"/rrp/resume/findByUserId.do",{userId:userId},function(data){
		   if(data.state==0){
			   var resumeId=data.data.id;//根据用户id找简历id
			 //  console.log(resumeId);
			   $.get(ip+"/rrp/delivery/viewDeliveried.do",{resumeId:resumeId,deliveryStatus:0},function(result){//查询投递成功简历
				   if(result.state==0){
					   if(result.data.length!=0){
						   console.log(result.data)
						   var str="";
						   $.each(result.data,function(k,v){
							   str+='<div class="delivery_leftcontbox"><div class="delivery_leftcontmain">'+
			                   '<div class="delivery_leftcontdiv1"><div class="delivery_leftcomname">'+v.companyName+'</div>'+
			                   '<div>'+v.postName+'<span class="delivery_leftadd">['+v.region+']</span></div>'+
			                   '<div class="delivery_leftsala">'+v.salary+'</div></div>'+
			                   '<div class="delivery_leftcontdiv2">'+
			                   '<div>申请：<span class="delivery_div2_span1">'+v.resumeName+'</span></div><div><span>投递成功 </span>'+
			                   '<span class="delivery_div2_time">'+new Date(v.deliveyTime).format("yyyy-MM-dd hh:mm")+'</span><span class="myfont delivery_div2_font">&#xe681;</span></div></div>'+
			                   '<div class="delivery_leftcontdetail"><div class="delivery_leftdet_div">'+
			                   '<div class="delivery_number delivery_numberactive">1</div><div class="delivery_state">投递成功</div>'+
			                   '<div class="delivery_time">'+new Date(v.deliveyTime).format("yyyy-MM-dd hh:mm")+'</div><div class="delivery_time"><span class="delivery_tip"></span>HR已收到你的简历</div>'+
			                   '</div><div class="delivery_leftdet_div" ><div class="delivery_number">2</div>'+
			                   '<div class="delivery_state">已被查看</div></div><div class="delivery_leftdet_div">'+
			                   '<div class="delivery_number">3</div><div class="delivery_state">对我有意</div></div>'+
			                   '<div class="delivery_leftdet_div"><div class="delivery_number">4</div>'+
			                   '<div class="delivery_state">邀请面试</div></div></div></div></div>';
						   })
						   $("#delivery_succList").html(str);
						   $(".delivery_div2_font").on("click",function(){
							    var dom= $(this).parent().parent().next(".delivery_leftcontdetail");
							    if($(dom).css("display")=="none"){
							        $(this).html("&#xe786;")
							        $(dom).css("display", "flex")
							    }else{
							        $(this).html("&#xe681;")
							        $(dom).css("display", "none")
							    }
							})
					   }else{
						   //layer.msg("对不起，没有符合条件的数据！") 
					   }
				   }else{
					   layer.msg(result.message) 
				   }
			   },"json")
		   }else{
			  layer.msg(data.message)
		   }
	   },"json")
	}
})