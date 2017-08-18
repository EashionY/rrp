
	$(".for_Btn2").on("click",function(){
		var psdList=new Array();
        var form=$(this).parent().parent();
		var psd1=form.children().eq(0).children().eq(1);
		var psd2=form.children().eq(2).children().eq(1);
		psdList.push(psd1),psdList.push(psd2);
        var phone="";
        if(window.screen.width<1024){
            phone=form.parent().parent().prev().children().children().eq(1).children().eq(0).children().eq(2);
        }else{
            phone=form.parent().parent().parent().prev().children().eq(1).children(".log_formBox").children().children().eq(0).children().eq(2);
        }
		var kongbool=kong(psdList);
        var errbool=err(psdList);
        if(kongbool==false){
        	layer.msg("内容不全")
        }else if(errbool==false){
        	layer.msg("内容有错")
        }else{
        	$.post(ip+"/rrp/user/forgetPsd.do",{phone:phone.val(),newPsd:psd1.val()},function(data){
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
	
	
    $(".for_dxyzmbtn").on("click",function(){//发送短信
        var phone=$(this).parent().parent().parent().children().eq(0).children().eq(2).val();
    	sends.send(phone,ip+"/rrp/user/sendPsdCode.do")
    });

    var verifyCode2 ="";
    if(window.screen.width<1024){
        verifyCode2= new GVerify("f_container2");//图片验证码-手机
    }else{
        verifyCode2 = new GVerify("f_container");//图片验证码
    }

//    下一步
    $(".for_nextBtn").on("click",function(){
    	var regList=new Array();
        var form=$(this).parent().parent();
        var reg_phone=form.children().eq(0).children().eq(2);//1手机号
        var reg_tpyzm=form.children().eq(2).children().eq(1);//2图形验证码
        var reg_dxyzm=form.children().eq(4).children().eq(1);//3短信验证码
        regList.push(reg_phone);regList.push(reg_tpyzm);regList.push(reg_dxyzm);
//      图片验证码
        var tpbool=false;
        var res = verifyCode2.validate(reg_tpyzm.val());
        if(res){
            tpbool=true;
        }else{
            reg_tpyzm.parent().next().css("opacity",1);
        }
        var kongbool=kong(regList);
        var errbool=err(regList);
        if(kongbool==false){
        	layer.msg("内容不全")
        }else if(errbool==false){
        	layer.msg("内容有错")
        }else{
            $.get(ip+"/rrp/user/verifyCode.do",{code:reg_dxyzm.val()},function(data){
            	//console.log(data)
            	if(data.state==0){
                    if(window.screen.width<1024){
                        $("#for_mainBox2_mobile").css("display","block");
                        $("#for_mainBox1_mobile").css("display","none");
                    }else{
                        $("#for_mainBox2").css("display","block");
                        $("#for_mainBox1").css("display","none");
                    }
            	}else{
            		layer.msg(data.massage)
            	}
            },'json')
        }
    });
