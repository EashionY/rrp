/**
 * Created by Administrator on 2017/7/7.
 */
//验证码
var sends = {
    checked:1,
    send:function(){
        var numbers = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        var val = $('#reg_phone').val(); //获取输入手机号码
        if(!numbers.test(val) || val.length ==0){
            $("#reg_dxyzmbtn").parent().next().css("opacity",1);
            $("#reg_dxyzmbtn").parent().next().html("*请确认手机号码正确");
            return false;
        }
        if(numbers.test(val) && $('.div-phone a').attr('class') == 'send1'){
            console.log("发送短信验证码");
            $("#reg_dxyzmbtn").parent().next().css("opacity",0);
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
var sends2 = {
    checked:1,
    send:function(){
        var numbers = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
        var val = $('#acc_phone').val(); //获取输入手机号码
        if(!numbers.test(val) || val.length ==0){
            $("#acc_dxyzmbtn").parent().next().css("opacity",1);
            $("#acc_dxyzmbtn").parent().next().html("*请确认手机号码正确");
            return false;
        }
        if(numbers.test(val) && $('.acc_maskinputbox a').attr('class') == 'send1'){
            console.log("发送短信验证码");
            $("#acc_dxyzmbtn").parent().next().css("opacity",0);
            var time = 60;
            $('.acc_maskinputbox .accerr_tips').remove();
            function timeCountDown(){
                if(time==0){
                    clearInterval(timer);
                    $('.acc_maskinputbox a').removeClass('send0').html("发送验证码");
                    sends.checked = 1;
                    return true;
                }
                $('.acc_maskinputbox a').html(time+"S后重发");
                time--;
                return false;
                sends.checked = 0;
            }
            $('.acc_maskinputbox a').addClass('send0');
            timeCountDown();
            var timer = setInterval(timeCountDown,1000);
        }
    }
};