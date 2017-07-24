/**
 * Created by Administrator on 2017/7/13.
 */
$(".my_edit").on("click",function(){
    $(".my_rightmain1").css("display","none");
    $(".my_rightmain2").css("display","block")
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