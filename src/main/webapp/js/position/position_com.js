function ld_caozuo(){
	$("#po_ldBox span").mouseover(function(){//移入
		$(this).children(".ld_dele").css("display","block")
	})
	$("#po_ldBox span").mouseout(function(){//移出
		$(this).children(".ld_dele").css("display","none")
	});
	$(".ld_dele").click(function(){//删除亮点
		$(this).parent().remove();
	});
}
//字数提示
function setShowLength(obj, maxlength, id) {
    var rem = maxlength - obj.value.length;
    var wid = id;
    if (rem < 0){
        rem = 0;
    }
    document.getElementById(wid).innerHTML = obj.value.length;
}
//工作性质
$(".posiEdi_inputdiv_xz span").click(function(){
	var list=$(".posiEdi_inputdiv_xz span");
	var text=$(this).html();
	$.each(list,function(k,v){
		if($(v).html()==text){
			$(v).addClass("xz_active");
		}else{
			$(v).removeClass("xz_active");
		}
	})
})



$("#po_addld").click(function(){//添加职位亮点
	layer.prompt({
		title:'添加职位亮点'
	},function(value, index, elem){
		$("#po_ldBox").append('<span><span class="ld_str">'+value+'</span><span class="ld_dele">X</span></span>');
		layer.close(index);
		ld_caozuo();
	});
})