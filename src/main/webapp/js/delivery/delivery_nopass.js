function addDom(mydata){
	//加载投递简历状态
	$.get(ip+"/rrp/delivery/viewDeliveried.do",mydata,function(result){
		   if(result.state==0){
			   if(result.data.length!=0){
				   var str="";
				   $.each(result.data,function(k,v){
					   if(v.check_time==null){
						   v.check_time=v.unfit_time;
					   }
					   var str1="";
					   if(window.screen.width<1024){
						   str1='<div class="delivery_leftcontbox"><div class="delivery_leftcontmain">'+
							   '<div class="delivery_leftcontdiv1"><div class="delivery_leftcomname">'+v.companyName+'</div>' +
							   '<div class="delivery_leftdown"><div>'+v.postName+'<span class="delivery_leftadd">['+v.region+']</span></div> ' +
							   '<div class="delivery_leftsala">'+v.salary+'</div></div></div>'+
						       '<div class="delivery_leftcontdiv2"><div><div>申请：</div><span class="delivery_div2_span1">'+v.resumeName+'</span></div><div><div>不合适 </div>';
					   }else{
						   str1='<div class="delivery_leftcontbox"><div class="delivery_leftcontmain">'+
		                    '<div class="delivery_leftcontdiv1"><div class="delivery_leftcomname">'+v.companyName+'</div>'+
		                    '<div>'+v.postName+'<span class="delivery_leftadd">['+v.region+']</span></div><div class="delivery_leftsala">'+v.salary+'</div></div>'+
		                    '<div class="delivery_leftcontdiv2"><div>申请：<span class="delivery_div2_span1">'+v.resumeName+'</span></div><div><span>不合适 </span>';
					   }
	                    var str2='<span class="delivery_div2_time">'+new Date(v.unfit_time).format("yyyy-MM-dd hh:mm")+'</span>'+
	                    '<span class="myfont delivery_div2_font">&#xe681;</span></div></div><div class="delivery_leftcontdetail">'+
	                    '<div class="delivery_leftdet_div"><div class="delivery_number delivery_numberactive">1</div>'+
	                    '<div class="delivery_state">投递成功</div><div class="delivery_time">'+new Date(v.delivery_time).format("yyyy-MM-dd hh:mm")+'</div>'+
	                    '<div class="delivery_time"><span class="delivery_tip"></span>HR已收到你的简历</div></div>'+
	                    '<div class="delivery_leftdet_div delivery_leftdet_divactiv" ><div class="delivery_number">2</div>'+
	                    '<div class="delivery_state">已被查看</div><div class="delivery_time">'+new Date(v.check_time).format("yyyy-MM-dd hh:mm")+'</div>'+
	                    '<div class="delivery_time"><span class="delivery_tip"></span>HR已查看你的简历</div></div>'+
	                    '<div class="delivery_leftdet_div delivery_leftdet_divactiv"><div class="delivery_number">3</div>'+
	                    '<div class="delivery_state">不合适</div><div class="delivery_time">'+new Date(v.unfit_time).format("yyyy-MM-dd hh:mm")+'</div>'+
	                    '<div class="delivery_time"><span class="delivery_tip"></span>简历被标记为不合适</div></div>'+
	                    '<div class="delivery_leftdet_div delivery_leftdet_div_0"></div></div></div></div>';
	                    str+=(str1+str2);
				   })
				   $("#delivery_nopassList").html(str);
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
				   var str='<div class="delivery_leftcontbox"><div class="deli_tips">无记录！!</div></div>'; 
				   $("#delivery_nopassList").html(str);
			   }
		   }else{
			   layer.msg(result.message) 
		   }
	   },"json")
}

$(function() { 
	load(4);
})
