
	$("#for_Btn2").on("click",function(){
		var psdList=new Array();
		var psd1=$("#reg_psd");
		var psd2=$("#reg_psd2");
		psdList.push(psd1),psdList.push(psd2)
		var kongbool=kong(psdList);
        var errbool=err(psdList);
        if(kongbool==false){
        	layer.msg("内容不全")
        }else if(errbool==false){
        	layer.msg("内容有错")
        }else{
        	$.post(ip+"/rrp/user/forgetPsd.do",{phone:$("#reg_phone").val(),newPsd:$("#reg_psd").val()},function(data){
        		if(data.state==0){
        			layer.msg(data.message,{
                		icon: 1,
                		time: 1000 
                	}, function(){
                		window.location.href="login.html";
                	});
        		}else{
        			layer.msg(data.message)
        		}
        	},'json')
        }
	})
	
	
    $("#for_dxyzmbtn").on("click",function(){//发送短信
    	sends.send($("#reg_phone").val(),ip+"/rrp/user/sendPsdCode.do")
    });
    var verifyCode2 = new GVerify("v_container2");
    $("#reg_tpyzm").on("focus",function(){
        err_tips_false(this)
    });

    $("#for_nextBtn").on("click",function(){
    	var regList=new Array();
        var reg_phone=$("#reg_phone");//1手机号
        var reg_tpyzm=$("#reg_tpyzm");//2图形验证码
        var reg_dxyzm=$("#reg_dxyzm");//3短信验证码
        regList.push(reg_phone);regList.push(reg_tpyzm);regList.push(reg_dxyzm);
//      图片验证码
        var tpbool=false;
        var res = verifyCode2.validate($("#reg_tpyzm").val());
        if(res){
            tpbool=true;
        }else{
            $("#reg_tpyzm").parent().next().css("opacity",1);
        }
        var kongbool=kong(regList);
        var errbool=err(regList);
        if(kongbool==false){
        	layer.msg("内容不全")
        }else if(errbool==false){
        	layer.msg("内容有错")
        }else{
            $.get(ip+"/rrp/user/verifyCode.do",{code:$("#reg_dxyzm").val()},function(data){
            	//console.log(data)
            	if(data.state==0){
            		$("#for_mainBox2").css("display","block")
            	    $("#for_mainBox1").css("display","none")
            	}else{
            		layer.msg(data.massage)
            	}
            },'json')
        }
    })