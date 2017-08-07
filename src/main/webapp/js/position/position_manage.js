function posMa_addDom(mydata){
	$.get(ip+'/rrp/post/listPostJob.do',mydata,function(data){
		if(data.state==0){
			var result=data.data;
			console.log(data)
			$(".poma_divdown").html("");
			if(result.length!=0){
				var str="";
				var myDate = new Date();
				var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy/MM/dd");
				$.each(result,function(k,v){
					 var postDate=new Date(v.postTime).format("yyyy/MM/dd");
					 var showDate=''
					 if(nowDate==postDate){//当天发布
						 showDate=new Date(v.postTime).format("hh:mm")
					 }else{
						 showDate=new Date(v.postTime).format("yyyy/MM/dd")
					 }
					 var str1='<div class="poma_contbox"><div><div class="poma_left_div1">';
					 var str2='<span class="poma_zwstate">已关闭</span>';
					 var str3='<span class="poma_zwname">'+v.postName+'</span>'+
	                    '<span class="poma_fbtime">'+showDate+' 发布</span></div>'+
	                    '<div class="poma_left_div2">'+
	                    ' <span class="poma_salary">'+v.salary+'</span>'+
	                    ' <span class="poma_jingyan">经验'+v.workExp+'</span>'+
	                    ' <span class="poma_xueli">'+v.degree+'</span></div></div>';
					 var str4='<div class="poma_right"><input class="myinput" value="'+v.postId+'"/><span class="poma_edit">编辑</span><span class="c_o_btn close_btn">关闭</span></div></div>';
					 var str5='<div class="poma_right"><input class="myinput" value="'+v.postId+'"/><span class="poma_edit">编辑</span><span class="c_o_btn open_btn">开启</span></div></div>';
					if(v.postStatus==0){
						str+=str1+str2+str3+str5;
					}else{
						str+=str1+str3+str4;
					}
				})
				$(".poma_divdown").html(str);
				$(".poma_edit").click(function(){//编辑职位按钮
			        window.location.href="position_edit.html?postId="+$(this).prev().val();
			    });
				$(".c_o_btn").click(function(){
					var that=$(this);
					if($(this).attr("class")=="c_o_btn close_btn"){//关闭方法
						$.get(ip+"/rrp/post/closeJob.do",{id:$(this).prev().prev().val()},function(data){
							if(data.state==0){
								that.removeClass("close_btn").addClass("open_btn") 
								that.html("开启");
								that.parent().prev().children().eq(0).children(".poma_zwname").before('<span class="poma_zwstate">已关闭</span>');
								layer.msg(data.message)
							}else{
								layer.msg(data.message)
							}
						},'json')
					}else{//开启方法
						$.get(ip+"/rrp/post/openJob.do",{id:$(this).prev().prev().val()},function(data){
							if(data.state==0){
								that.parent().prev().children().eq(0).children(".poma_zwstate").remove();
								that.html("关闭");
								that.removeClass("open_btn").addClass("close_btn") 
								layer.msg(data.message)
							}else{
								layer.msg(data.message)
							}
						},'json')
					}
				})
				
			}else{
				layer.msg("没有已经发布的职位");
			}
		}else{
			layer.msg(data.message);
		}
	},'json')
}

$(function() {
	myonload1("../com_informa/com_xinxi.html","../com_informa/com_psd.html","../../Personal_edition/index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		 var mydata={companyId:getCookieValue("companyId"),page:1,pageSize:3};
		 posMa_addDom(mydata);
	}
})

$("#divselect ul li a").click(function(){//条件查询
	var mystatus=$(this).attr("selectid");
	if(mystatus==-1){
		console.log("1")
		var mydata={companyId:getCookieValue("companyId"),page:1,pageSize:3};
		posMa_addDom(mydata);
	}else{
		console.log("2")
		var mydata={companyId:getCookieValue("companyId"),status:mystatus,page:1,pageSize:3};
		posMa_addDom(mydata);
	}
})



	


//   下拉 选择
    $(function(){
        $.divselect("#divselect","#inputselect");
    });
    //页面跳转
    $("#poma_fabubtn").click(function(){
        window.location.href="position_release.html"
    })
   