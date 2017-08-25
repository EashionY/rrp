	$(function() {
		myonload2("pers_infor.html","../../../index.html");
		var userId=getCookieValue("userId");
		if(userId==""){//未登录，请先登录
			window.location.href="../login.html";
		}else{
			$(".nowPhone").html(getCookieValue("userPhone"))
		}
	});
    $("#account_changephone").on("click",function(){
        if(window.screen.width<1024){
            window.location.href="change_phone.html"
        }else{
            $("#acc_mask").css("display","block")
        }
    });
    $(".acc_saveBtn").on("click",function(){
    	var accList=new Array();//输入框
    	var phone=$("#acc_phone");
    	var tpyzm=$("#acc_tpyzm");
    	var dxyzm=$("#acc_dxyzm");
    	accList.push(phone);accList.push(tpyzm);accList.push(dxyzm);
    	var kongbool=kong(accList);//空判断
    	var errbool=err(accList);//错误
    //  图片验证码
        var tpbool=false;//false-错误 true-正确
        var res = verifyCode.validate($("#acc_tpyzm").val());
        if(res){tpbool=true;}else{$("#acc_tpyzm").parent().next().css("opacity",1);}
        if(kongbool==false){
            layer.msg("输入框有空！")
        }else if(errbool==false){
            layer.msg("输入框内容有错！")
        }else{
        	$.get(ip+"/rrp/user/modifyPhone.do",{userId:getCookieValue("userId"),phone:$("#acc_phone").val(),code:$("#acc_dxyzm").val()},function(data){
        		if(data.state==0){
        			layer.msg(data.message,{
                	   icon: 1,
                	   time: 2000 
                  	}, function(){
                  		deleteCookie("userPhone","/");
                  	    addCookie("userPhone",$("#acc_phone").val(),1,"/");
                  	    window.location.href="account_bind.html";
                  	}); 
        		}else{
        			layer.msg(data.message);
        		}
        	},'json')
        }
       
    });
    $(".acc_quitbtn").on("click",function(){
        $("#acc_mask").css("display","none")
    });

    function err_tips_false2(obj){
        $(obj).parent().next().css("opacity",0);
        $(obj).parent().css("border","1px #3eb39d solid");
        $(obj).parent().children().children(".acc_font").css("color","#3eb39d");
    }
    $("#acc_tpyzm").on("focus",function(){
        err_tips_false2(this)
    });
    $("#acc_phone").on("focus",function(){
        err_tips_false2(this);
    });
    $("#acc_dxyzm").on("focus",function(){
        err_tips_false2(this);
    });

    $("#acc_dxyzmbtn").on("click",function(){
    	sends.send($("#acc_phone").val(),ip+"/rrp/user/sendPhoneCode.do")
    });
    $("#acc_phone").on("blur",function(){
        var myreg = /^1[3|4|5|7|8][0-9]{9}$/;
        if(!myreg.test($(this).val())) {
            $(this).parent().next().css("opacity",1);
        }
        $(this).parent().css("border","1px #dddddd solid");
        $(this).parent().children().children(".acc_font").css("color","#dddddd")
    });
    $("#acc_dxyzm").on("blur",function(){
        $(this).parent().css("border","1px #dddddd solid");
        $(this).parent().children().children(".acc_font").css("color","#dddddd")
    });
    var verifyCode = new GVerify("v_container");
    $("#acc_tpyzm").on("blur",function(){
        var res = verifyCode.validate($("#acc_tpyzm").val());
        if(res){
            $(this).parent().css("border","1px #dddddd solid");
            $(this).parent().children().children(".acc_font").css("color","#dddddd")
        }else{
            $("#acc_tpyzm").parent().next().css("opacity",1);
        }
        $(this).parent().children().eq(0).children().css("color", "#dddddd");
        $(this).parent().css("border","1px #E3E8EE solid");
    });

    $("#account_qq").click(function(){
    	layer.msg("暂不支持")
    });
    $("#account_wx").click(function(){
    	layer.msg("暂不支持")
    });
    $("#account_sina").click(function(){
    	layer.msg("暂不支持")
    });