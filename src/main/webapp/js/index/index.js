function findmax(max2,data) {
	$.each(data,function(key,val){
		if(parseInt(data[key].parentNo)>max2){
			max2=parseInt(data[key].parentNo)
		}
	})
	return max2;
}
function jobs(arrName,data,grandNo,leftMenu){
    	$.each(data,function(key,val){
    		if(parseInt(data[key].grandNo)==parseInt(grandNo)){
    			arrName.push(data[key])
    		}
    	})
    	leftMenu.push(arrName);
}

function parent(arrName,data,grandNo,parentMenu){
	$.each(data,function(key,val){
		if(parentMenu(data[key].grandNo)==parseInt(grandNo)){
			arrName.push(data[key])
		}
	})
	leftMenu.push(arrName);
}
$(function() { 
	myonload2("pages/Personal_edition/my/pers_infor.html","index.html");
	$.ajax({
        type: "get",
        url: ip+"/rrp/job/listAllJobs.do",//左侧菜单
        data: {},
        dataType: "json",
        success: function(data){
            if(data.state==0){
            	//========左侧菜单栏===========
            	var max=parseInt(data.data[0].grandNo);
            	$.each(data.data,function(key,val){
            		if(parseInt(data.data[key].grandNo)>max){
            			max=parseInt(data.data[key].grandNo)
            		}
            	})
            	//console.log(max)
            	var leftMenu=new Array();
            	for(var i=1;i<=max;i++){
            		var str='arr'+i;
					var str=new Array();
                	jobs(str,data.data,i.toString(),leftMenu);
            	}
            	//console.log(leftMenu);
            	//处理一级菜单
            	for(var j=0;j<leftMenu.length;j++){
            		var a=[];
            		a.push(leftMenu[j][0].grandName.split("|"))
            		var grandstr="";
            		for(var k=0;k<a.length;k++){
            			for(var m=0;m<a[k].length;m++){
            				grandstr=grandstr+"<li>"+a[k][m]+"</li>";
            			}
            		}
            		//处理热门
            		var rmNum=leftMenu[j][0].jobName.length+leftMenu[j][1].jobName.length+leftMenu[j][2].jobName.length
            		var remenstr="";
            		if(rmNum>15){
            			remenstr= '<li>'+leftMenu[j][0].jobName+'</li> '+
                        '<li>'+leftMenu[j][1].jobName+'</li>'
            		}else{
            			remenstr= '<li>'+leftMenu[j][0].jobName+'</li> '+
                        '<li>'+leftMenu[j][1].jobName+'</li> <li>'+leftMenu[j][2].jobName+'</li>'
            		}
            		 var str='<div class="left_menubox"> <div class="menu_div"><ul class="menu_div_ul">' +
                     ''+grandstr+'</ul><div class="left_more">' +
                     '<span class="myfont">&#xe68d;</span></div> </div> ' +
                     '<ul class="menu_ul">'+remenstr+'</ul>'+
                     '<div class="menu_detailBox" id="menu_detailBox'+j+'"></div></div>';
            		 $(".index_left").append(str);
            	}
            	//处理二级菜单
            	var parent1=new Array();
            	$.each(leftMenu,function(key,val){
            		$.each(leftMenu[key],function(k,v){
                		if($.inArray(leftMenu[key][k].parentName,parent1)<0){
                			parent1.push(leftMenu[key][k].parentName);
        			    }
                	})
            	})
            	//console.log(parent1);
            	var maxarr=new Array();
            	for(var m=0;m<leftMenu.length;m++){
            		var max='max'+m;
            		var max=parseInt(leftMenu[m][0].parentNo);
            		max=findmax(max,leftMenu[m])
            		maxarr.push(max)
            	}
            	//console.log(maxarr);
            	var start=0;
        		var end=maxarr[0];
        		var parentarr=new Array();//二级菜单
            	for(var x=0;x<maxarr.length;x++){
            		//console.log(parent1.slice(start,end));
            		parentarr.push(parent1.slice(start,end))
            		var tem=end;
            		start=tem;
            		end=start+maxarr[x+1];
            	}
            	//console.log(parentarr);
            	$.each(parentarr,function(key,val){
            		$.each(parentarr[key],function(k,v){
            			var pararr='<div class="deta_mainbox"> <div class="deta_contbox"> <div class="deta_left">'+parentarr[key][k]+'</div> ' +
                        '<div class="deta_right" id="deta_right'+k+'"><ul><ul></div> </div> </div>'
            			$("#menu_detailBox"+key).append(pararr);
            		})
            	})
            	//处理三级菜单
            	function addjob3(leftMenu,num,panum){
            		$.each(leftMenu[num],function(k,v){
            			var jobNo=leftMenu[num][k].jobNo.slice(0,4)
            			var jobarr='<li>'+leftMenu[num][k].jobName+'</li>';
            			for(var m=0;m<panum;m++){
            				if(parseInt(jobNo)==parseInt((num+1)+'0'+(m+1))){//三级菜单判断编号(必须二级编号<10)
            	        		$("#menu_detailBox"+num+" #deta_right"+m+" ul").append(jobarr)
            	    		}	
            			}
            			
            		})
            	}
            	for(var j=0;j<parentarr.length;j++){
            		addjob3(leftMenu,j,parentarr[j].length);
            	}
            	//========左侧菜单栏结束===========
            	$(".left_menubox li").click(function(){//点击菜单栏搜索职位
            		window.location.href="pages/Personal_edition/job/apply_job.html?keyword="+$(this).html();
            	})
            }else{
            	layer.msg(data.message);
            }
            $(".left_menubox").on("mouseover",function(){
                $(this).children(".menu_detailBox").css("display","block")
            });
            $(".left_menubox").on("mouseout",function(){
                $(this).children(".menu_detailBox").css("display","none")
            });
        }
    });
});
	

var comNameList=$(".comName");
$.each(comNameList,function(key,val){
    $(val).on("click",function(){
        console.log(key);
        window.location.href="pages/Personal_edition/company/company.html";
    })
})

$(".comlogo_Box>ul>li").on("mouseover",function(){
    $(this).children(".com_name").css("bottom", "0px")
});
$(".comlogo_Box>ul>li").on("mouseout",function(){
    $(this).children(".com_name").css("bottom", "-30px")
});
//热门职位
$(function(){
	$.get(ip+"/rrp/post/popularJob.do",{},function(data){
		if(data.state==0){
			var list=data.data;
			var str="";
			$.each(list,function(k,v){//显示全部
				str+='<li>'+list[k].post_name+'</li>'
			})
			$(".remen_ul").html(str)
			
			
			
			var li=$(".remen_ul li");//判断第几个超出范围
			var length=30;
			var num=0;
			$.each(li,function(k,v){
				length+=parseInt($(li[k]).css("width"))+20
				if(length>673){
					num=k;
					return false;
				}else{
					num=10;
				}
			})
		//	console.log(num)
			$.each(li,function(k,v){//删除超出的节点
				if(k>=num){
					$(".remen_ul li:eq("+k+")").remove();
					
				}
			})
			
			
		}else{
			layer.msg(data.message)
		}
	},'json')
})


