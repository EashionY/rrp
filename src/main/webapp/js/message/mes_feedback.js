function addDom(mydata){
	$.get(ip+'/rrp/delivery/viewFeedback.do',mydata,function(result){
		if(result.state==0){
			if(result.data.length!=0){
				var str="";
				$.each(result.data,function(k,v){
					var state="";
					var time="";
					if(v.delivery_status==0){
						state="投递成功";
						time=new Date(v.delivery_time).format("yyyy-MM-dd");
					}else if(v.delivery_status==1){
						state="被查看";
						time=new Date(v.check_time).format("yyyy-MM-dd");
					}else if(v.delivery_status==2){
						state="有意向";
						time=new Date(v.intend_time).format("yyyy-MM-dd");
					}else if(v.delivery_status==3){
						state="邀请面试";
						time=new Date(v.invite_time).format("yyyy-MM-dd");
					}else if(v.delivery_status==4){
						state="不合适";
						time=new Date(v.unfit_time).format("yyyy-MM-dd");
					}
					str+='<div class="message_contmainbox"><div class="mesfeed_contmain"><div class="mesfeed_contleft">'+
                         '<div class="mesfeed_imgbox"><img src="../../../../'+v.logo+'" alt=""/></div><div class="mesfeed_contdiv2">'+
                         '<div>'+v.companyName+'</div><div>'+v.postName+'</div><div>最新状态：<span class="mesfeed_span">'+state+'</span></div></div></div>'+
                         '<div class="mesfeed_contright"><div>'+time+'</div><div class="mesfeed_span">去查看</div></div></div></div>';
				});
				$("#mes_feedBox").html(str);
				$(".mesfeed_span").click(function(){
					var stateTetx=$(this).parent().prev().children().eq(1).children().eq(2).children(".mesfeed_span").html();
					if(stateTetx=='投递成功'){
						window.location.href="../delivery_box/delivery_success.html"
					}else if(stateTetx=='被查看'){
						window.location.href="../delivery_box/delivery_viewed.html"
					}else if(stateTetx=='有意向'){
						window.location.href="../delivery_box/delivery_intention.html"
					}else if(stateTetx=='邀请面试'){
						window.location.href="../delivery_box/delivery_interview.html"
					}else if(stateTetx=='不合适'){
						window.location.href="../delivery_box/delivery_nopass.html"
					}
				})
			}
		}else{
			layer.msg(data.message)
		}
	},'json')
}
function getTotal(mydata){//利用同步获取总条数
	var totalNum=0;
	$.ajax({
		type:"get",
		url:ip+"/rrp/delivery/viewFeedback.do",
		data:mydata,
		async: false,
		dataType:'json',
		success:function(data) {
			if(data.state==0){
				var result=data.data;
				if(result.length==0){
					layer.msg("对不起，没有符合所选条件的结果！");
				}else{
					totalNum=result[0].totalnum;
				}
			}
		}
	})
	return totalNum;
}
function add_page(mydata){
	addDom(mydata);//添加节点
	var totalnum=getTotal(mydata);//总条数
	var totalPage=Math.ceil(totalnum/mydata.pageSize);//总页数(向上取整);
    //分页
	layui.use(['laypage', 'layer'], function(){
	     var laypage = layui.laypage,layer= layui.layer;
	     laypage({
	        cont: 'mesfee_pages',
	        pages: totalPage,//总页数
	        first: 1,//首页
	        last: totalPage,//末页
	        prev: '<em><</em>',
	        next: '<em>></em>',
	        groups: 3,//连续显示分页数
	        skin: '#3eb39d',
	        jump: function(obj,first){
	        	if(!first){
	        		mydata.page=obj.curr;
	        		addDom(mydata);
	        	}
	        }
	    });
	});
}

$(function() { 
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
		window.location.href="../login.html";
	}else{
		$.get(ip+"/rrp/resume/findByUserId.do",{userId:userId},function(data){
			if(data.state==0){
				if(data.data.length!=0){//根据用户id查找简历id
					var resumeId=data.data.id;
					var mydata={resumeId:resumeId,page:1,pageSize:8}
					add_page(mydata);
				}else{
					layer.msg("没有对应简历");
				}
			}else{
				layer.msg(data.message)
			}
		},'json')
	}
})