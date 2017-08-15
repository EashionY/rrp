function addDom(mydata){
	$.get(ip+"/rrp/post/searchPostCompany.do",mydata,function(data){
		if(data.state==0){
			var str="";
			var myDate = new Date();
			var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy/MM/dd");//当前时间
			$.each(data.data,function(k,v){
				var postDate=new Date(v.post_time).format("yyyy/MM/dd");
				   var showDate=''
				   if(nowDate==postDate){//当天发布
					   showDate=new Date(v.post_time).format("hh:mm")
				   }else{
					   showDate=new Date(v.post_time).format("yyyy-MM-dd")
				   }
				str+='<div class="zhiwei_box"><input class="myinput" value="'+v.post_id+'"/><div class="zhiwei_mainbox zhiwei_mainbox2">'+
                	 '<div class="top"><div><ul><li>'+v.post_name+'</li>'+
                     '<li>['+v.region+']</li><li class="recomposi_time">'+showDate+'  发布</li></ul>'+
                     '<div class="salary_text">'+v.salary+'</div></div>'+
                     '<div class="comName">'+v.name+'</div></div>'+
                     '<div class="bottom"><ul><li>'+v.work_exp+'</li><li>'+v.degree+'</li></ul>'+
                     '<div>'+v.industry+'/'+v.financing+'</div></div></div> </div>';
			});
			$(".recomposi_box").html(str);
			$(".zhiwei_box").click(function(){
				window.location.href="../job/job_detail.html?postId="+$(this).children("input").val();
			})
		}else{
			layer.msg(data.message)
		}
	},"json")
}
function getTotal(mydata){//利用同步获取总条数
	var totalNum=0;
	$.ajax({
		type:"get",
		url:ip+"/rrp/post/searchPostCompany.do",
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
	        		//console.log(mydata.page);
	        		//console.log(mydata)
	        		addDom(mydata);
	        	}
	        }
	    });
	});
}
$(function() { 
	myonload2("../my/pers_infor.html","../../../index.html");
	var Request = new Object(); 
	Request = GetRequest(); 
	var keyword=Request['keyword']; //获取参数
	console.log(keyword);
	var mydata={keyword:keyword,page:1,pageSize:2};
	add_page(mydata)
});