/**
 * Created by Administrator on 2017/7/13.
 */
$("#pers").on("click",function(){
    window.location.href="pers_infor.html"
})
$("#acco").on("click",function(){
    window.location.href="account_bind.html"
})
$("#chan").on("click",function(){
    window.location.href="change_psd.html"
})

//    字数提示
function setShowLength(obj, maxlength, id) {
    var rem = maxlength - obj.value.length;
    var wid = id;
    if (rem < 0){
        rem = 0;
    }
    document.getElementById(wid).innerHTML = obj.value.length;
}