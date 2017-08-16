/**
 * Created by Administrator on 2017/7/7.
 */
//验证码
//忘记密码接口
var sends= {
	    checked:1,
	    send:function(phone,url){
	        var numbers = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
	        var val =phone; //获取输入手机号码
	        if(!numbers.test(val) || val.length ==0){
	            $(".send1").parent().next().css("opacity",1);
	            $(".send1").parent().next().html("*请确认手机号码正确");
	            return false;
	        }
	        if(numbers.test(val) && $('.div-phone a').attr('class') == 'send1'){
	            $.get(url,{phone:val},function(data){
	            	layer.msg(data.message)
	            },'json');
	            $(".send1").parent().next().css("opacity",0);
	            var time = 60;
	            $('.div-phone .error').remove();
	            function timeCountDown(){
	                if(time==0){
	                    clearInterval(timer);
	                    $('.div-phone a').removeClass('send0').html("发送验证码");
	                    sends.checked = 1;
	                    return true;
	                }
	                $('.div-phone a').html(time+"S后重发");
	                time--;
	                return false;
	                sends.checked = 0;
	            }
	            $('.div-phone a').addClass('send0');
	            timeCountDown();
	            var timer = setInterval(timeCountDown,1000);
	        }
	    }
	};