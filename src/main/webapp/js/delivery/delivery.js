/**
 * Created by Administrator on 2017/7/12.
 */
$(".delivery_div2_font").on("click",function(){
    var dom= $(this).parent().parent().next(".delivery_leftcontdetail");
    if($(dom).css("display")=="none"){
        $(this).html("&#xe786;")
        $(dom).css("display", "flex")
    }else{
        $(this).html("&#xe681;")
        $(dom).css("display", "none")
    }
})
$(".delivery_menu1").on("click",function(){
    window.location.href="delivery_success.html";
})
$(".delivery_menu2").on("click",function(){
    window.location.href="delivery_viewed.html";
})
$(".delivery_menu3").on("click",function(){
    window.location.href="delivery_intention.html";
})
$(".delivery_menu4").on("click",function(){
    window.location.href="delivery_interview.html";
})
$(".delivery_menu5").on("click",function(){
    window.location.href="delivery_nopass.html";
})