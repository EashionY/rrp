/**
 * Created by Administrator on 2017/7/13.
 */
function divselect(divselectid,inputselectid) {//下拉
	var inputselect = $(inputselectid);
	$(divselectid+" cite").click(function(){
		var ul = $(divselectid+" ul");
		//console.log(ul.css("display")=="none")
		if(ul.css("display")=="none"){
			ul.css("display","inline-block")
		}else{
			ul.css("display","none")
		}
	});
	$(divselectid+" ul li a").click(function(){
		var txt = $(this).text();
		$(divselectid+" cite").children(".input_text").html(txt);
		var value = $(this).attr("selectid");
		inputselect.val(value);
		$(divselectid+" ul").css("display","none");
	});
};
//选择求职状态
$(function(){
	divselect("#udegree","#inputselect");
});
//编辑器
var myintro= UE.getEditor('myintro',{
	toolbars: [
	    ['undo', 'redo'],
	],initialFrameHeight:100
});
//返回
$(".my_back").on("click",function(){
	window.location.href="pers_infor.html"
})
$(".my_edit").on("click",function(){
    $(".my_rightmain1").css("display","none");
	$(".footer_mobile").css("display","none");
	$(".kong").css("display","none");
	$(".my_mobile").css("display","none");
	$(".nav_mobile").css("display","none");
    $(".my_rightmain2").css("display","block");
    $(".nav_mobile_mask").css("display","block");
    var userIdValue = getCookieValue("userId");
    $.get(ip+"/rrp/user/findUserInfo.do",{userId:userIdValue},function(data){
    	$("#my_headImg2").attr("src",'../../../../'+data.data.headImg)
    	$("#uname").val(data.data.nickname);
    	if(data.data.sex=='男'){
    		$("#myedit_nan").addClass("mysex")
     		$("#myedit_nv").removeClass("mysex") 
      	 }else{
      		$("#myedit_nv").addClass("mysex")
     		$("#myedit_nan").removeClass("mysex")
      	 }
    	$("#ujob").val(data.data.job);
    	$("#uhighxl").html(data.data.degree);
    	myintro.setContent(data.data.selfIntro);
    },"json")
    
});
var sex=$(".myedit_sexbox span");
$.each(sex,function(key,val){
    $(val).on("click",function(){
        var keynow=key;
        $.each(sex,function(k,v){
            if(keynow==k){
                $(v).addClass("mysex");
            }else{
                $(v).removeClass("mysex")
            }
        })
    })
});
$(".myedit_input input").on("focus",function(){
    $(this).css("border","1px #3eb39d solid")
});
$(".myedit_input input").on("blur",function(){
    $(this).css("border","1px #dddddd solid")
});
$(".myedit_input textarea").on("focus",function(){
    $(this).css("border","1px #3eb39d solid")
});
$(".myedit_input textarea").on("blur",function(){
    $(this).css("border","1px #dddddd solid")
})