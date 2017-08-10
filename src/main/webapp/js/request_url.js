function GetRequest() { 
	var url = window.location.href;
	//console.log(url);

	var obj = {};
	var reg = /\?/;
	if(url.match(reg)) {
	    //判断传入参数，以问号截取，问号后是参数
	    var chars = url.split('?')[1];

	    //再截&号
	    var arr = chars.split('&');

	    //获得截取后的数组为键值对字符串
	    for (var i = 0; i < arr.length; i++) {

	        //保守一点确定看是否为 name=value形式
	        var num = arr[i].indexOf("=");

	        if (num > 0) {
	            //拼接字符串
	            var name = arr[i].substring(0, num);
	            var value = arr[i].substr(num + 1);

	            //拼接对象，并转码
	            obj[decodeURIComponent(name)] = decodeURIComponent(value);
	        }
	    }
	}
	//console.log(obj);
	return obj; 
} 
