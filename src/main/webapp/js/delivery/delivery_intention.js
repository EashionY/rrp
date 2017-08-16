function addDom(mydata){
	//加载投递简历状态
	$.get(ip+"/rrp/delivery/viewDeliveried.do",mydata,function(result){
		   if(result.state==0){
			   if(result.data.length!=0){
				   var str="";
				   $.each(result.data,function(k,v){
					   if(v.check_time==null){
						   v.check_time=v.intend_time;
					   }
					   str+='<div class="delivery_leftcontbox"><div class="delivery_leftcontmain"><div class="delivery_leftcontdiv1">'+
	                        '<div class="delivery_leftcomname">'+v.companyName+'</div><div>'+v.postName+'<span class="delivery_leftadd">['+v.region+']</span></div><div class="delivery_leftsala">'+v.salary+'</div></div>'+
	                        '<div class="delivery_leftcontdiv2"><div>申请：<span class="delivery_div2_span1">'+v.resumeName+'</span></div><div>'+
	                        '<span>对我有意 </span><span class="delivery_div2_time">'+new Date(v.intend_time).format("yyyy-MM-dd hh:mm")+'</span><span class="myfont delivery_div2_font">&#xe681;</span></div></div>'+
	                        '<div class="delivery_leftcontdetail"><div class="delivery_leftdet_div"> <div class="delivery_number delivery_numberactive">1</div>'+
	                        '<div class="delivery_state">投递成功</div><div class="delivery_time">'+new Date(v.delivey_time).format("yyyy-MM-dd hh:mm")+'</div>'+
	                        '<div class="delivery_time"><span class="delivery_tip"></span>HR已收到你的简历</div></div>'+
	                        '<div class="delivery_leftdet_div delivery_leftdet_divactiv" ><div class="delivery_number">2</div>'+
	                        '<div class="delivery_state">已被查看</div><div class="delivery_time">'+new Date(v.check_time).format("yyyy-MM-dd hh:mm")+'</div>'+
	                        '<div class="delivery_time"><span class="delivery_tip"></span>HR已查看你的简历</div></div>'+
	                        '<div class="delivery_leftdet_div delivery_leftdet_divactiv"><div class="delivery_number">3</div>'+
	                        '<div class="delivery_state">对我有意</div><div class="delivery_time">'+new Date(v.intend_time).format("yyyy-MM-dd hh:mm")+'</div>'+
	                        '<div class="delivery_time"><span class="delivery_tip"></span>你的简历通过初选</div></div>'+
	                        '<div class="delivery_leftdet_div"><div class="delivery_number">4</div><div class="delivery_state">邀请面试</div></div></div></div></div>';
				   })
				   $("#delivery_inteList").html(str);
				   
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
}

$(function() { 
	load(2);
})
