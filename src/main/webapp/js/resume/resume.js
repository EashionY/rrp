/**
 * Created by Administrator on 2017/7/17.
 */
//    平滑滚动
$.each($(".resume_lmenu"),function(k,v){
	$(v).click(function(){
		var num=k;
		$.each($(".resume_lmenu"),function(i,m){
			if(i==num){
				$(m).addClass("resume_lmenu_activ")
			}else{
				$(m).removeClass("resume_lmenu_activ")
			}
		})
	})
})
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
		console.log(value)
		inputselect.val(value);
		$(divselectid+" ul").css("display","none");
	});
};
//console.log($(document).scrollTop())
//    日期
var myDate = new Date();
var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy-MM-dd");
laydate({
    elem: '#birthday', //目标元素。
    event: 'focus', //响应事件。
    max: nowDate	
});
laydate({
    elem: '#zizhitime1',
    event: 'focus',
    max: nowDate,
    choose: function(dates){ //选择好日期的回调
    	laydate({
    	    elem: '#zizhitime2',
    	    event: 'focus',
    	    min:dates,
    	    max: nowDate,
    	});
    }
});

laydate({
    elem: '#xiangmutime1',
    event: 'focus',
    max: nowDate,
    choose: function(dates){ //选择好日期的回调
    	laydate({
    	    elem: '#xiangmutime2',
    	    event: 'focus',
    	    min:dates,
    	    max: nowDate,
    	});
    }
});

laydate({
    elem: '#jiaoyutime1',
    event: 'focus',
    max: nowDate,
    choose: function(dates){ //选择好日期的回调
    	laydate({
    	    elem: '#jiaoyutime2',
    	    event: 'focus',
    	    min:dates,
    	    max: nowDate,
    	});
    }
});
//编辑器
var workJy= UE.getEditor('work_content',{
	toolbars: [
	    ['undo', 'redo'],
	],initialFrameHeight:200
});
var xiangmu= UE.getEditor('xiangmu_content',{
	toolbars: [
	    ['undo', 'redo'],
	],initialFrameHeight:200
});
var zeren= UE.getEditor('zeren_content',{
	toolbars: [
	    ['undo', 'redo'],
	],initialFrameHeight:200
});
var ziping= UE.getEditor('ziping_content',{
	toolbars: [
	    ['undo', 'redo'],
	],initialFrameHeight:200
});



//    选择求职状态
$(function(){
	divselect("#divselect","#inputselect");
	divselect("#divselect_xinzi","#inputselect_xinzi")
	divselect("#divselect_leixing","#inputselect_leixing")
	divselect("#divselect_jineng","#inputselect_jineng")
	divselect("#divselect_xueli","#inputselect_xueli");
	divselect("#divselect_highxl","#inputselect_highxl");
});
//隐藏-显示
function mydisplay(mask,box){
    $(mask).css("display","none");
    $(box).css("display","block")
}
//最多录入限制
function limit(obj,mynum,mask,box){
    var num=$(obj).parent().siblings(".resume_workbox").length;
    if(num<mynum){
        mydisplay(box,mask);
    }else{
        layer.msg("最多"+mynum+"个！")
    }
}
//    技能评价
$("#editpingjiabtn").click(function(){
    mydisplay($("#pingjiabox"),$("#pingjia_mask"));
    
});
$("#resume_quitpingjia").click(function(){
    mydisplay($("#pingjia_mask"),$("#pingjiabox"));
    $("#resume_modipingjia").css("display","none");
 	$("#resume_savepingjia").css("display","inline-block");
 	$("#skillName").val("");
	$("#skillcd").html("");
});
//    教育经历
$("#editjiaoyubtn").click(function(){
    limit(this,2,$("#jiaoyu_mask"), $("#jiaoyubox"))
});
$("#resume_quitjiaoyu").click(function(){
    mydisplay($("#jiaoyu_mask"),$("#jiaoyubox"));
    $("#resume_modijiaoyu").css("display","none");
	$("#resume_savejiaoyu").css("display","inline-block");
	$("#school").val("");
	$("#major").val("");
	$("#jiaoyutime1").val("")
    $("#jiaoyutime2").val("");
	$("#education").val("");
});
//    自我描述
$("#editzipingbtn").click(function(){
    mydisplay($("#zipingbox"),$("#ziping_mask"));
    var userId = getCookieValue("userId"); 
    $.post(ip+"/rrp/resume/findByUserId.do",{userId:userId},function(data){
    	if(data.data!=null){
    		ziping.setContent(data.data.self_evaluation);
    	}
    },'json')
});
$("#resume_quitziping").click(function(){
    mydisplay($("#ziping_mask"),$("#zipingbox"));
});
//    添加项目经验
$("#editprojectjlbtn").click(function(){
    limit(this,3,$("#project_mask"), $("#projectjlbox"));
    xiangmu.setContent("");
    zeren.setContent("");
});
$("#resume_quitproject").click(function(){
    mydisplay($("#project_mask"),$("#projectjlbox"));
    $("#resume_modiproject").css("display","none");
	$("#resume_saveproject").css("display","inline-block");
    $("#projectName").val("");
    $("#xiangmutime1").val("");
    $("#xiangmutime2").val("");
    $("#xiangmu_content").val("");
    $("#zeren_content").val("");
});
//    添加工作经历
$("#editworkjlbtn").click(function(){
    limit(this,3,$("#workjl_mask"), $("#workjlbox"));
    workJy.setContent("");
});
$("#resume_quitwork").click(function(){
    mydisplay($("#workjl_mask"),$("#workjlbox"));
    $("#re_company").val("");
	$("#re_work").val("");
	$("#zizhitime1").val("");
	$("#zizhitime2").val("");
	$("#work_content").val("");
    $("#resume_modiwork").css("display","none")
	$("#resume_savework").css("display","inline-block");
});
//    修改求职意向
$("#edityxbtn").click(function(){
    mydisplay($("#yixiangbox"),$("#yixiang_mask"));
    var userId = getCookieValue("userId"); 
    $.post(ip+"/rrp/resume/findByUserId.do",{userId:userId},function(data){
    	if(data.data!=null){
    		$("#qwsalary").html(data.data.salary);
    		$("#qwjob").val(data.data.job);
    		$("#workType").html(data.data.work_type)
    		$("#workArea").val(data.data.work_area)
    	}    	
    },'json')
});
$("#resume_quityx").click(function(){
    mydisplay($("#yixiang_mask"),$("#yixiangbox"));
});
//    修改基本信息
$("#editxxbtn").click(function(){
    mydisplay($("#xinxibox"),$("#xinxi_mask"));
    var userId = getCookieValue("userId"); 
    $.post(ip+"/rrp/resume/findByUserId.do",{userId:userId},function(data){
    	if(data.data!=null){
    	 $("#uname").val(data.data.emp_name);
       	 if(data.data.sex=='女'){
       		 $("#resume_nv").addClass("resume_mysex")
       		  $("#resume_nan").removeClass("resume_mysex")
       	 }else if(data.data.sex=='男'){
       		 $("#resume_nan").addClass("resume_mysex")
       		 $("#resume_nv").removeClass("resume_mysex") 
       	 }
       	 $("#birthday").val(data.data.birth);
       	 $("#startwork").val(data.data.work_exp);
       	 $("#ustate").html(data.data.status);
       	 $("#uphone").val(data.data.phone);
       	 $("#uemail").val(data.data.email);
       	 $("#ucity").val(data.data.emp_region);
       	 $("#uhighxl").html(data.data.top_degree);
    	}
    },'json');
    
});
$("#resume_quitxinxi").click(function(){
    $("#xinxi_mask").css("display","none");
    $("#xinxibox").css("display","flex")
});
//    修改简历名称
$("#resume_left_editbtn").click(function(){
    mydisplay($(".resume_left_name"),$(".resume_left_mask"));
    var old=$(".resume_lname").html();
    $(".resume_left_mask input").val(old)
});
$("#resume_left_mask_quit").click(function(){
    $(".resume_left_name").css("display","flex");
    $(".resume_left_mask ").css("display","none")
});
//   修改头像
$(".resume_touxiang").mouseover(function(){
    $(this).addClass("resedit_touxiang");
});
$(".resume_touxiang").mouseout(function(){
    $(this).removeClass("resedit_touxiang");
});

//   选择性别
var sex=$(".resume_spanbox span");
$.each(sex,function(key,val){
    $(val).click(function(){
        var keynow=key;
        $.each(sex,function(k,v){
            if(keynow==k){
                $(v).addClass("resume_mysex");
            }else{
                $(v).removeClass("resume_mysex")
            }
        })
    })
});
