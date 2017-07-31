/**
 * Created by Administrator on 2017/7/17.
 */
//    平滑滚动
$(function(){
    $("#xinxi_btn").click(function(){
        $.scrollTo('#xinxibox',300);
    });
    $("#qiuzhi_btn").click(function(){
        $.scrollTo('#yixiangbox',300);
    });
    $("#work_btn").click(function(){
        $.scrollTo('#workjlbox',300);
    });
    $("#project_btn").click(function(){
        $.scrollTo('#projectjlbox',300);
    });
    $("#pingjia_btn").click(function(){
        $.scrollTo('#pingjiabox',400);
    });
    $("#jiaoyu_btn").click(function(){
        $.scrollTo('#jiaoyubox',500);
    });
    $("#ziping_btn").click(function(){
        $.scrollTo('#zipingbox',600);
    });
});
//console.log($(document).scrollTop())
//    日期
laydate({
    elem: '#birthday', //目标元素。
    event: 'focus' //响应事件。
});
laydate({
    elem: '#zizhitime1',
    event: 'focus'
});
laydate({
    elem: '#zizhitime2',
    event: 'focus'
});
laydate({
    elem: '#xiangmutime1',
    event: 'focus'
});
laydate({
    elem: '#xiangmutime2',
    event: 'focus'
});
laydate({
    elem: '#jiaoyutime1',
    event: 'focus'
});
laydate({
    elem: '#jiaoyutime2',
    event: 'focus'
});

//    字数提示
function setShowLength(obj, maxlength, id) {
    var rem = maxlength - obj.value.length;
    var wid = id;
    if (rem < 0){
        rem = 0;
    }
    document.getElementById(wid).innerHTML = obj.value.length;
}

//    选择求职状态
$(function(){
    $.divselect("#divselect","#inputselect");
    $.divselect("#divselect_xinzi","#inputselect_xinzi");
    $.divselect("#divselect_leixing","#inputselect_leixing");
    $.divselect("#divselect_jineng","#inputselect_jineng");
    $.divselect("#divselect_xueli","#inputselect_xueli");
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
    		$("#ziping_content").val(data.data.self_evaluation)
    	}
    },'json')
});
$("#resume_quitziping").click(function(){
    mydisplay($("#ziping_mask"),$("#zipingbox"));
});
//    添加项目经验
$("#editprojectjlbtn").click(function(){
    limit(this,3,$("#project_mask"), $("#projectjlbox"));
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
       	 $("#ucity").val(data.data.emp_region)
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
$(".resume_touxiang").click(function(){
    alert("更换头像")
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
