function posMa_addDom(mydata){
	$.get(ip+'/rrp/post/listPostJob.do',mydata,function(data){
		if(data.state==0){
			var result=data.data;
			$(".poma_divdown").html("");
			if(result.length!=0){
				var str="";
				var myDate = new Date();
				var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy/MM/dd");
				$.each(result,function(k,v){
					 var postDate=new Date(v.post_time).format("yyyy/MM/dd");
					 var showDate=''
					 if(nowDate==postDate){//当天发布
						 showDate=new Date(v.post_time).format("hh:mm")
					 }else{
						 showDate=new Date(v.post_time).format("yyyy/MM/dd")
					 }
					 var str1='<div class="poma_contbox"><div><div class="poma_left_div1">';
					 var str2='<span class="poma_zwstate">已关闭</span>';
					 var str3='<span class="poma_zwname">'+v.post_name+'</span>'+
	                    '<span class="poma_fbtime">'+showDate+' 发布</span></div>'+
	                    '<div class="poma_left_div2">'+
	                    ' <span class="poma_salary">'+v.salary+'</span>'+
	                    ' <span class="poma_jingyan">经验 '+v.work_exp+'</span>'+
	                    ' <span class="poma_xueli">'+v.degree+'</span></div></div>';
					 var str4='<div class="poma_right"><input class="myinput" value="'+v.post_id+'"/><span class="poma_edit">编辑</span><span class="c_o_btn close_btn">关闭</span><span class="delete_btn">删除</span></div></div></div>';
					 var str5='<div class="poma_right"><input class="myinput" value="'+v.post_id+'"/><span class="poma_edit">编辑</span><span class="c_o_btn open_btn">开启</span><span class="delete_btn">删除</span></div></div></div>';
					if(v.post_status==0){
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
				});
				$(".delete_btn").click(function(){//TO-DO
					var id=$(this).parent().children().eq(0).val();
					var that=this;
					layer.confirm("是否确认删除该条职位信息？",{icon:3,title:"提示"},function(index){
						$.get(ip+"/rrp/post/deleteJob.do",{postId:id},function(data){
							if(data.state==0){
								layer.msg(data.message);
								$(that).parent().parent().remove();
							}else{
								layer.msg(data.message)
							}
						},'json');
						layer.close(index)
					})
				})
				
			}else{
				layer.msg("没有已经发布的职位");
			}
		}else{
			layer.msg(data.message);
		}
	},'json')
}

function posMa_getTotal(mydata){//利用同步获取总条数
	var totalNum=0;
	$.ajax({
		type:"get",
		url:ip+"/rrp/post/listPostJob.do",
		data:mydata,
		async: false,
		dataType:'json',
		success:function(data) {
			//console.log(data)
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
function posMaAdd_page(mydata){
	posMa_addDom(mydata);//添加节点
	var totalnum=posMa_getTotal(mydata);//总条数
	//console.log(totalnum)
	var totalPage=Math.ceil(totalnum/mydata.pageSize);//总页数(向上取整);
	 //分页
	layui.use(['laypage', 'layer'], function(){
	     var laypage = layui.laypage,layer= layui.layer;
	     laypage({
	        cont: 'poma_pages',
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
	        		posMa_addDom(mydata);
	        	}
	        }
	    });
	});
	
}

$(function() {
	myonload1("../com_informa/com_xinxi.html","../com_informa/com_psd.html","../../../index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		if(comStatus==3){//判断是否开通企业版
			var mydata={companyId:getCookieValue("companyId"),page:1,pageSize:8};
			posMaAdd_page(mydata);
		}else{
			to_open_inner(comStatus);//判断跳转页面
		}
	}
})

$("#divselect ul li a").click(function(){//条件查询
	var mystatus=$(this).attr("selectid");
	if(mystatus==-1){
		var mydata={companyId:getCookieValue("companyId"),page:1,pageSize:8};
		posMaAdd_page(mydata);
	}else{
		var mydata={companyId:getCookieValue("companyId"),status:mystatus,page:1,pageSize:8};
		posMaAdd_page(mydata);
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
   