/**
 * Created by Administrator on 2017/7/12.
 */
function getTotal(mydata){//利用同步获取总条数
	var totalNum=0;
	$.ajax({
		type:"get",
		url:ip+"/rrp/delivery/viewDeliveried.do",
		data:mydata,
		async: false,
		dataType:'json',
		success:function(data) {
			if(data.state==0){
				var result=data.data;
				if(result.length==0){
					//layer.msg("对不起，没有符合所选条件的结果！");
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
	        cont: 'deli_pages',
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
	        		console.log(mydata.page);
	        		console.log(mydata)
	        		addDom(mydata);
	        	}
	        }
	    });
	});
}
function load(state){
	//console.log(state)
	myonload2("../my/pers_infor.html","../../../index.html");
	var userId = getCookieValue("userId"); 
	if(userId==""){//未登录，请先登录
	   window.location.href="../login.html";
	}else{
		 $.get(ip+"/rrp/resume/findByUserId.do",{userId:userId},function(data){
			   if(data.state==0){
				   var resumeId=data.data.id;//根据用户id找简历id
				   var mydata={resumeId:resumeId,deliveryStatus:state,page:1,pageSize:2};
				   add_page(mydata)
			   }else{
				   layer.msg(data.message)
			   }
		 })
	   
	}
}



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
$(".delivery_menu1").on("click",function(){
    window.location.href="delivery_success.html";
})
$(".delivery_menu2").on("click",function(){
    window.location.href="delivery_viewed.html";
})
$(".delivery_menu3").on("click",function(){
    window.location.href="delivery_intention.html";
})
$(".delivery_menu4").on("click",function(){
    window.location.href="delivery_interview.html";
})
$(".delivery_menu5").on("click",function(){
    window.location.href="delivery_nopass.html";
})