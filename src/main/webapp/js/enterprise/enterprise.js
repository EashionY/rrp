/**
 * Created by Administrator on 2017/7/12.
 */
//样式
$(".enterp_contentbox3_input input").on("focus",function(){
    $(this).parent().css({
        "border": "1px #3EB49E solid"
    })
});
$(".enterp_contentbox3_input input").on("blur",function(){
    $(this).parent().css({
        "border": "1px #E3E8EE solid"
    })
})

$(".enterp_inputDiv input").on("focus",function(){
    $(this).parent().css({
        "border": "1px #3EB49E solid"
    })
    $(this).siblings(".enterp_tubiao").children(".enterp_font").css({
        "color": "#3EB49E"
    })
});
$(".enterp_inputDiv input").on("blur",function(){
    $(this).parent().css({
        "border": "1px #E3E8EE solid"
    })
    $(this).siblings(".enterp_tubiao").children(".enterp_font").css({
        "color": "#dddddd"
    })
})