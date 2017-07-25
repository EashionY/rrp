//设置IP地址
var ip='http://192.168.0.102:8080';

function addCookie(name,value,days,path){   /**添加设置cookie**/  
    var name = escape(name);  
    var value = escape(value);
    var expires = new Date();  
    expires.setTime(expires.getTime() + days * 3600000 * 24);  
    //path=/，表示cookie能在整个网站下使用，path=/temp，表示cookie只能在temp目录下使用  
    path = path == "" ? "" : ";path=" + path;  
    //GMT(Greenwich Mean Time)是格林尼治平时，现在的标准时间，协调世界时是UTC  
    //参数days只能是数字型  
    var _expires = (typeof days) == "string" ? "" : ";expires=" + expires.toUTCString();  
    document.cookie = name + "=" + value + _expires + path;  
}  

function getCookieValue(name){  /**获取cookie的值，根据cookie的键获取值**/  
    //用处理字符串的方式查找到key对应value  
    var name = escape(name);  
    //读cookie属性，这将返回文档的所有cookie  
    var allcookies = document.cookie;         
    //查找名为name的cookie的开始位置  
    name += "=";  
    var pos = allcookies.indexOf(name);      
    //如果找到了具有该名字的cookie，那么提取并使用它的值  
    if (pos != -1){                                     //如果pos值为-1则说明搜索"version="失败  
        var start = pos + name.length;                  //cookie值开始的位置  
        var end = allcookies.indexOf(";",start);        //从cookie值开始的位置起搜索第一个";"的位置,即cookie值结尾的位置  
        if (end == -1) end = allcookies.length;        //如果end值为-1说明cookie列表里只有一个cookie  
        var value = allcookies.substring(start,end); //提取cookie的值  
        return (unescape(value));                           //对它解码        
    }else{  //搜索失败，返回空字符串  
        return "";  
    }  
}  


function deleteCookie(name,path){   /**根据cookie的键，删除cookie，其实就是设置其失效**/  
    var name = escape(name);  
    var expires = new Date(0);  
    path = path == "" ? "" : ";path=" + path;  
    document.cookie = name + "="+ ";expires=" + expires.toUTCString() + path;  
}  

//页面加载时判断有没有登录
function myonload2(zhurl,qyurl,exiturl){    
    var userNameValue = getCookieValue("userName");  
    var userIdValue = getCookieValue("userId"); 
	console.log(userNameValue);
	console.log(userIdValue);
	if(userNameValue==""){
		
	}else{ 
		var str="<li id='head_person'>"+userNameValue+"<span class='myfont myfont_xia'>&#xe60b;</span>" +
       "<ul id='head_person_ul'><li id='head_zh'>&nbsp;账号设置&nbsp;</li> " +
       "<li id='head_qy'>&nbsp;去企业版&nbsp;</li> " +
       "<li id='head_exit'>&nbsp;安全退出&nbsp;</li></ul></li>";
		$("#header_last").html(str);
		 //样式
       $("#head_person").mouseover(function(){
           $("#head_person_ul").css("display","block")
       });
       $("#head_person").mouseout(function(){
           $("#head_person_ul").css("display","none")
       });
       //个人中心跳转
       $("#head_zh").click(function(){
           window.location.href=zhurl;
       });
       $("#head_qy").click(function(){
           window.location.href=qyurl;
       });
       $("#head_exit").click(function(){
       	  deleteCookie("userName","/");
       	  window.location.href=exiturl;
       });
	}
}


