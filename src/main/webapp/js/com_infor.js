/**
 * Created by Administrator on 2017/7/19.
 */
//    融资
var comrz=$(".comInfo_rongzi span");
$.each(comrz,function(key,val){
    $(val).click(function(){
        var activekey=key;
        $.each(comrz,function(k,v){
            if(k==activekey){
                $(v).addClass("comInfo_rzactiv")
            }else{
                $(v).removeClass("comInfo_rzactiv")
            }
        })
    })
});
//    选择求职状态
$(function(){
    $.divselect("#divselect","#inputselect");
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
//页面跳转
$("#com_pers").click(function(){
    window.location.href="com_xinxi.html"
})
$("#com_chan").click(function(){
    window.location.href="com_psd.html"
});
$("#comxx_eidt").click(function(){
    window.location.href="com_manage.html"
})
$("#com_exit").click(function(){
    window.location.href="com_xinxi.html"
})