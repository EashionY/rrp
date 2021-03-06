function addDom(mydata){
	$.get(ip+"/rrp/resume/searchResume.do",mydata,function(data){
		 if(data.state==0){
			 if(data.data.length!=0){
				 var str="";
				 var myDate = new Date();
			     var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy");
				 $.each(data.data,function(k,v){
					 var birth=new Date(v.birth).format("yyyy");
					 var age=parseInt(nowDate)-parseInt(birth);
					 if(window.screen.width<1024){
						 str+='<div class="talent_main"><div class="talent_mainbox"><div class="talent_mainleft"> ' +
							 '<div class="resu_img"><img src="../../../../'+v.head_img+'" alt=""/></div>' +
							 '<div class="resu_imgnext"><div class="resu_name">'+v.emp_name+'</div><div class="resu_sex">' +
							 '<span class="talent_zw">'+v.job+'</span><span class="talent_xz">'+v.salary+'</span></div> ' +
							 '<div class="resu_sex resu_sex2"><span>'+v.sex+'/'+age+'岁</span><span>'+v.top_degree+'</span><span>'+v.work_exp+'</span></div></div></div> ' +
							 '<div class="talent_btnbox"><input class="myinput" value="'+v.user_id+'"><span class="to_checkBtn">查看简历</span> ' +
							 '</div></div><div class="talent_mainright">“'+v.self_evaluation+'”</div></div>';
					 }else{
						 str+='<div class="talent_main"><div class="talent_mainbox">'+
							 '<div class="talent_mainleft"><div class="resu_img"><img src="../../../../'+v.head_img+'" alt=""/></div>'+
							 '<div class="resu_imgnext"><div class="resu_name">'+v.emp_name+'</div>'+
							 '<div class="resu_sex"><span class="talent_zw">'+v.job+'</span><span class="talent_xz">'+v.salary+'</span></div>'+
							 '<div class="resu_sex"><span>'+v.sex+'/'+age+'岁</span><span>'+v.top_degree+'</span><span>'+v.work_exp+'</span></div></div></div>'+
							 '<div class="talent_mainright">“'+v.self_evaluation+'”</div></div><div class="talent_btnbox"><input class="myinput" value="'+v.user_id+'"><span class="to_checkBtn">查看简历</span></div></div>';
					 }
				 });
				 $("#talent_Box").html(str);
				 $(".to_checkBtn").click(function(){
					 window.location.href="resume/talent_resume.html?userId="+$(this).prev().val();
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
		url:ip+"/rrp/resume/searchResume.do",
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
function add_page(mydata){
	addDom(mydata);//添加节点
	var totalnum=getTotal(mydata);//总条数
	//console.log(totalnum)
	var totalPage=Math.ceil(totalnum/mydata.pageSize);//总页数(向上取整);
	 //分页
	layui.use(['laypage', 'layer'], function(){
	     var laypage = layui.laypage,layer= layui.layer;
	     laypage({
	        cont: 'appJob_pages',
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
	myonload1("com_informa/com_xinxi.html","com_informa/com_psd.html","../../index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//判断是否登录
	     window.location.href="../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		if(comStatus==3){//判断是否开通企业版(3--开通成功);
			var mydata={companyId:getCookieValue("companyId"),keyword:"",page:1,pageSize:5};
			add_page(mydata);
		}else{
			to_open(comStatus);//判断跳转页面
		}
	}
})

$(".serch_tubox").click(function(){
	if($(".serch_input").val()!=""){
		var mydata={companyId:getCookieValue("companyId"),keyword:$(".serch_input").val(),page:1,pageSize:5};
		add_page(mydata);
	}else{
		layer.msg("请先填写搜索条件")
	}
})



//    选择
    $(function(){
        $.divselect("#divselect_talent","#inputselect_talent");
    });
    