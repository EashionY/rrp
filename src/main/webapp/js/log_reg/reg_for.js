//    输入框验证
function err_tips_false(obj){
    $(obj).parent().next().css("opacity",0)
}
var reg_focus=$(".reg_focus");
$.each(reg_focus,function(key,val){
    $(val).on("focus",function(){
        err_tips_false(this)
    });
});

$(".reg_phone").on("blur",function(){
	var myreg = /^1[3|4|5|7|8][0-9]{9}$/;
    if(!myreg.test($(this).val())) {
        $(this).parent().next().css("opacity",1);
    }
});
$(".reg_psd").on("blur",function(){
	
    var psd=$(this).val();
    console.log(psd)
    var num=0;
    var zm=false,sz=false,fh=false;
    if(psd.search(/[A-Za-z]/)!=-1) {num+=1;}else{zm=true}
    if(psd.search(/[0-9]/)!=-1) {num+=1;}else{sz=true}
    if(psd.search(/[-+_!@#$%^&*().]/)!=-1) {num+=1;}else{fh=true}
    if(psd.length>=6 && psd.length<=16){
        if(num<2){
            $(this).parent().next().css("opacity",1);
            if(zm==true){
                $(this).parent().next().html("*至少有一个字母")}
            if(sz==true){
                $(this).parent().next().html("*至少有一个数字")}
            if(fh==true){
                $(this).parent().next().html("*至少有一个符号")}
        }
    }else{
        $(this).parent().next().css("opacity",1);
    }
});
$(".reg_psd2").on("blur",function(){
    var psd1=$(this).parent().prev().prev().children().eq(1).val();
    var psd2=$(this).val();
    if(psd2!=psd1){
        $(this).parent().next().css("opacity",1);
    }
});




//阅读声明-样式
$(".reg_tiptu").on("click",function(){
    if($(this).children().css("color")=="rgb(51, 51, 51)"){
        $(this).children().css("color","#3EB49E")
    }else{
        $(this).children().css("color","rgb(51, 51, 51)")
    }
})
//空 --判断方法
	function kong(arr){
		var kongbool=false;
        $.each(arr,function(key,val){
            if(val.val()==""){
                kongbool=false;
                return false;
            }else{
                kongbool=true;
            }
        });
		return kongbool;
	}
//错误--判断方法
	function err(arr){
		var errbool=false;//错误
        $.each(arr,function(key,val){
            if(val.parent().next().css("opacity")==1){
                errbool=false;
                return false;
            }else{
                errbool=true;
            }
        });
		return errbool;
	}

//样式
$(".log_inputDiv input").on("focus",function(){
    $(this).parent().css("border","1px #3EB49E solid");
    $(this).parent().children(".log_tubiao").css("color", "#3EB49E")
});
$(".log_inputDiv input").on("blur",function(){
    $(this).parent().css("border","1px #E3E8EE solid");
    $(this).parent().children(".log_tubiao").css("color", "#dddddd")
});
