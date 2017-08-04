function find(obj){
	var name='';
	$.each(obj,function(k,v){
		if(v.className=='cond_selected'){
			name=$(v).html()
		}
	})
	return name;
}
function xunhuan(tjArr,obj,num){
	$.each(obj,function(k,v){
		$(v).click(function(){
			if($(".appJob_serInp").val()!=""){
				tjArr.splice(0,1,$(".appJob_serInp").val())
			}else{
				tjArr.splice(0,1,null)
			};
			if($("#ucity").val()!=""){
				tjArr.splice(1,1,$("#ucity").val())
			}else{
				tjArr.splice(1,1,null)
			};
			var tj=find(obj);
			if(tj=="不限"){
				tjArr.splice(num,1,null);//替换元素
			}else{
				tjArr.splice(num,1,tj);//替换元素
			}
			console.log(tjArr);
			$.get(ip+"/rrp/post/searchPostCompany.do",{keyword:tjArr[0],region:tjArr[1],workExp:tjArr[2],degree:tjArr[3],scale:tjArr[4],salary:tjArr[5],industry:tjArr[6],financing:tjArr[7],page:1,pageSize:8},function(data){
				if(data.state==0){
					console.log(data.data);
					var result=data.data;
					$(".zhiwei_Box").html("");
					var str="";
					$.each(result,function(){
						str+='<div class="zhiwei_box"><div class="zhiwei_mainbox">'+
                        '<div class="top"><div><ul><li>ios开发工程师</li><li>[成都]</li><li>14:28发布</li></ul>'+
                        '<div class="salary_text">10K-20K</div></div>'+
                        '<div class="comName">凯利特网络</div></div>'+
                        '<div class="bottom"><ul><li>经验1-3年</li><li>本科</li></ul><div>电子商务/初创型(未融资)</div></div></div> </div>';
					});
					$(".zhiwei_Box").html(str);
				}else{
					layer.msg(data.message);
				}
			},'json')
		})
	})
}
function shijian(obj1,obj2,obj3,obj4,obj5,obj6){
	var tjArr=[null,null,null,null,null,null,null,null];
	xunhuan(tjArr,obj1,2);
	xunhuan(tjArr,obj2,3);
	xunhuan(tjArr,obj3,4);
	xunhuan(tjArr,obj4,5);
	xunhuan(tjArr,obj5,6);
	xunhuan(tjArr,obj6,7);
}

$(function() { 
	myonload2("../my/pers_infor.html","../../Enterprise_edition/talent_recom.html","../index.html");
	shijian($("#app_jyul li"),$("#app_xlul li"),$("#app_gmul li"),$("#app_xzul li"),$("#app_hyul li"),$("#app_rzul li"));
	
});
$(".appJob_serch_tubox").click(function(){
	var keyword=$(".appJob_serInp").val();
	var region=$("#ucity").val();
	if(region==""&&keyword==""){
		layer.msg("搜索条件为空！")
	}else{
		console.log(region)
		console.log(keyword)
	}
})