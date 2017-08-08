function getNum(mydata){//利用同步获取总条数
	var totalNum=0;
	$.ajax({
		type:"get",
		url:ip+"/rrp/delivery/viewNewResume.do",
		data:mydata,
		async: false,
		dataType:'json',
		success:function(data) {
			if(data.state==0){
				var result=data.data;
				if(result.length==0){
					totalNum=0;
				}else{
					totalNum=result[0].totalnum;
				}
			}
		}
	})
	return totalNum;
}

function add_page(mydata){//分页
	addDom(mydata)
	var totalnum=getNum(mydata);//总条数
	//console.log(totalnum)
	var totalPage=Math.ceil(totalnum/mydata.pageSize);//总页数(向上取整);
	//console.log(totalPage)
    //分页
	layui.use(['laypage', 'layer'], function(){
	     var laypage = layui.laypage,layer= layui.layer;
	     laypage({
	        cont: 'rene_pages',
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
	        		//console.log(mydata.page);
	        		//console.log(mydata)
	        		addDom(mydata);
	        	}
	        }
	    });
	});
}
function menu_num(){
	var mydata={companyId:getCookieValue("companyId"),deliveryStatus:0,page:1,pageSize:1}
	$("#new_num").html(getNum(mydata));
	var mydata1={companyId:getCookieValue("companyId"),deliveryStatus:1,page:1,pageSize:1}
	$("#view_num").html(getNum(mydata1));
	var mydata2={companyId:getCookieValue("companyId"),deliveryStatus:2,page:1,pageSize:1}
	$("#youyi_num").html(getNum(mydata2));
	var mydata3={companyId:getCookieValue("companyId"),deliveryStatus:3,page:1,pageSize:1}
	$("#interview_num").html(getNum(mydata3));
	var mydata4={companyId:getCookieValue("companyId"),deliveryStatus:4,page:1,pageSize:1}
	$("#nopass_num").html(getNum(mydata4));
}
$(function() {
	myonload1("../com_informa/com_xinxi.html","../com_informa/com_psd.html","../../Personal_edition/index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var mydata={companyId:getCookieValue("companyId"),deliveryStatus:0,page:1,pageSize:1}
		add_page(mydata);
		menu_num();
	}
})
function menuChange(nowId){//菜单标签切换
	$.each($(".resuNew_menudiv span"),function(k,v){
		if($(v).attr("id")==nowId){
			$(v).addClass("resuNew_menuactiv");
		}else{
			$(v).removeClass("resuNew_menuactiv");
		}
	})
}
function addDom(mydata){//添加节点
	$.get(ip+"/rrp/delivery/viewNewResume.do",mydata,function(data){
		if(data.state==0){
			$("#new_Box").html("");
			if(data.data.length!=0){
				//console.log(data.data);
				var mystr='';
				$.each(data.data,function(k,v){
					var str='<div class="resuNew_mainbox"><div class="resuNew_mainleft">'+
				    '<div class="resu_img"><img src="../../../../'+v.headImg+'" alt=""/></div><div class="resu_imgnext">'+
				    '<div class="resu_name">'+v.empName+'</div><div class="resu_sex">'+
				    '<span class="resuNew_zw">'+v.postName+'</span><span class="resuNew_xz">'+v.salary+'</span></div>'+
				    '<div class="resu_sex"><span>'+v.sex+'/'+v.age+'</span><span>'+v.topDegree+'</span><span>'+v.workExp+'</span></div></div></div>'+
				    '<div class="resuNew_mainright"><div class="resuNew_caozuo">'+
				    '<input class="myinput" value="'+v.userId+'">'+
				    '<input class="myinput" value="'+v.delivery_id+'">'+
				    '<span class="resuNew_chakbtn">查看简历</span>';
				    var str0='<span class="resuNew_delebtn">删除</span></div><div class="resuNew_time">'+new Date(v.delivery_time).format("yyyy-MM-dd")+'</div></div></div>';
				    var str1='<span class="resuNew_delebtn">删除</span></div><div class="resuNew_time">'+new Date(v.check_time).format("yyyy-MM-dd")+'</div></div></div>';
				    var str2='<span class="resuNew_delebtn">删除</span></div><div class="resuNew_time">'+new Date(v.intend_time).format("yyyy-MM-dd")+'</div></div></div>';
				    var str3='<span class="resuNew_delebtn">删除</span></div><div class="resuNew_time">'+new Date(v.invite_time).format("yyyy-MM-dd")+'</div></div></div>';
				    var str4='<div class="resuNew_time">'+new Date(v.unfit_time).format("yyyy-MM-dd")+'</div></div></div>';
					if(mydata.deliveryStatus==0){
						mystr+=str+str0;
					}else if(mydata.deliveryStatus==1){
						mystr+=str+str1
					}else if(mydata.deliveryStatus==2){
						mystr+=str+str2
					}else if(mydata.deliveryStatus==3){
						mystr+=str+str3
					}else if(mydata.deliveryStatus==4){
						mystr+=str+str4
					}
				});
				$("#new_Box").html(mystr);
				$(".resuNew_chakbtn").click(function(){//查看简历页面跳转
				    window.location.href="resume_view.html?userId="+$(this).prev().prev().val()+"&deliveryId="+$(this).prev().val()
				})
				$(".resuNew_delebtn").click(function(){//删除
					var deliveryId=$(this).prev().prev().val();
					var that=$(this);
					layer.confirm('确认删除该简历？', {icon: 3, title:'提示'}, function(index){
						$.get(ip+'/rrp/delivery/dealResume.do',{deliveryId:deliveryId,deliveryStatus:4},function(data){
							if(data.state==0){
								layer.msg(data.message,{
				                	   icon: 1,
				                	   time: 1000 
				                  	}, function(){
				                  		window.location.href="resume_new.html";
				                  	}); 
							}else{
								layer.msg(data.message)
							}
						},'json')
			    	});
					
				})
			}
		}else{
			layer.msg(data.message);
		}
	},'json')
}
$("#new_btn").click(function(){//新简历按钮
	menuChange($(this).attr("id"));//菜单切换
	var mydata={companyId:getCookieValue("companyId"),deliveryStatus:0,page:1,pageSize:1}
	add_page(mydata);
})

$("#view_btn").click(function(){//被查看按钮
	menuChange($(this).attr("id"));//菜单切换
	var mydata={companyId:getCookieValue("companyId"),deliveryStatus:1,page:1,pageSize:1}
	add_page(mydata);
})

$("#youyi_btn").click(function(){//有意向按钮
	menuChange($(this).attr("id"));//菜单切换
	var mydata={companyId:getCookieValue("companyId"),deliveryStatus:2,page:1,pageSize:1}
	add_page(mydata);
	
})
$("#inter_btn").click(function(){//邀请面试按钮
	menuChange($(this).attr("id"));//菜单切换
	var mydata={companyId:getCookieValue("companyId"),deliveryStatus:3,page:1,pageSize:1}
	add_page(mydata);
	
})
$("#nopass_btn").click(function(){//不合适按钮
	menuChange($(this).attr("id"));//菜单切换
	var mydata={companyId:getCookieValue("companyId"),deliveryStatus:4,page:1,pageSize:1}
	add_page(mydata);
})





//查看简历页面跳转
$(".resuNew_chakbtn").click(function(){
    window.location.href="resume_view.html"
})