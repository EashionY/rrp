function addDom(mydata){
	$.get(ip+"/rrp/post/searchPostCompany.do",mydata,function(data){
		if(data.state==0){
			//console.log(data.data);
			var result=data.data;
			$(".zhiwei_Box").html("");
			if(result.length==0){
				//layer.msg("对不起，没有符合所选条件的结果！");
			}else{
				var str="";
				var myDate = new Date();
				var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy/MM/dd");//当前时间
				$.each(result,function(k,v){
					   var postDate=new Date(result[k].post_time).format("yyyy/MM/dd");
					   var showDate=''
					   if(nowDate==postDate){//当天发布
						   showDate=new Date(result[k].post_time).format("hh:mm")
					   }else{
						   showDate=new Date(result[k].post_time).format("yyyy-MM-dd")
					   }
					str+='<div class="zhiwei_box"><input class="myinput" value="'+result[k].post_id+'"/><div class="zhiwei_mainbox">'+
	                '<div class="top"><div><ul><li>'+result[k].post_name+'</li><li>['+result[k].region+']</li><li>'+showDate+'  发布</li></ul>'+
	                '<div class="salary_text">'+result[k].salary+'</div></div>'+
	                '<div class="comName">'+result[k].name+'</div><input class="myinput" value="'+result[k].company_id+'"/></div>'+
	                '<div class="bottom"><ul><li>经验'+result[k].work_exp+'</li><li>'+result[k].degree+'</li></ul><div>'+result[k].industry+'/'+result[k].financing+'</div></div></div> </div>';
				});
				$(".zhiwei_Box").html(str);
				
				$(".zhiwei_box").click(function(){
	            	window.location.href="../job/job_detail.html?postId="+$(this).children("input").val();
	            })
	            $(".comName").click(function(event){
	            	window.location.href="../company/company.html?companyId="+$(this).next().val();
	            	event.stopPropagation();//阻止事件冒泡
	            });
			}
		}else{
			layer.msg(data.message);
		}
	},'json')
}
function getTotal(mydata){//利用同步获取总条数
	var totalNum=0;
	$.ajax({
		type:"get",
		url:ip+"/rrp/post/searchPostCompany.do",
		data:mydata,
		async: false,
		dataType:'json',
		success:function(data) {
			if(data.state==0){
				var result=data.data;
				if(result.length==0){
					layer.msg("对不起，没有符合所选条件的结果！");
				}else{
					totalNum=result[0].totalnum;
				}
			}
		}
	})
	return totalNum;
}

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
			var mydata={keyword:tjArr[0],region:tjArr[1],workExp:tjArr[2],degree:tjArr[3],scale:tjArr[4],salary:tjArr[5],industry:tjArr[6],financing:tjArr[7],page:1,pageSize:3}
			add_page(mydata);
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
function add_page(mydata){
	addDom(mydata);//添加节点
	var totalnum=getTotal(mydata);//总条数
	//console.log(totalnum)
	var totalPage=Math.ceil(totalnum/mydata.pageSize);//总页数(向上取整);
    //分页
	layui.use(['laypage', 'layer'], function(){
	     var laypage = layui.laypage,layer= layui.layer;
	     laypage({
	        cont: 'appJob_pages',
	        pages: totalPage,//总页数
	        first: 1,//首页
	        last: totalPage,//末页
	        prev: '<em><</em>',
	        next: '<em>></em>',
	        groups: 3,//连续显示分页数
	        skin: '#3eb39d',
	        jump: function(obj,first){
	        	if(!first){
	        		mydata.page=obj.curr;
	        		console.log(mydata.page);
	        		console.log(mydata)
	        		addDom(mydata);
	        	}
	        }
	    });
	});
}
$(function() { 
	myonload2("../my/pers_infor.html","../index.html");
	shijian($("#app_jyul li"),$("#app_xlul li"),$("#app_gmul li"),$("#app_xzul li"),$("#app_hyul li"),$("#app_rzul li"));
	var Request = new Object(); 
	Request = GetRequest(); 
	var keyword=Request['keyword']; //获取参数
	console.log(keyword)
	if(keyword==undefined){
		var mydata={keyword:null,region:null,workExp:null,degree:null,scale:null,salary:null,industry:null,financing:null,page:1,pageSize:3}
		add_page(mydata);
	}else{
		$(".appJob_serInp").val(keyword);
		var mydata={keyword:keyword,region:null,workExp:null,degree:null,scale:null,salary:null,industry:null,financing:null,page:1,pageSize:3}
		add_page(mydata);
	}
});


$(".appJob_serch_tubox").click(function(){//搜索按钮
	var keyword=$(".appJob_serInp").val();
	var region=$("#ucity").val();
	if(region==""&&keyword==""){
		layer.msg("搜索条件为空！")
	}else{
		//console.log(region)
		//console.log(keyword);
		var mydata={keyword:keyword,region:region,workExp:null,degree:null,scale:null,salary:null,industry:null,financing:null,page:1,pageSize:3}
		add_page(mydata);
	}
})


$(".cond_ul>li").on("click",function(){//样式转换
        var liList=$(this).parent().children();
        var nowId=$(this).index();
        $.each(liList,function(key,val){
            if(key==nowId){
                $(val).addClass("cond_selected")
            }else{
                $(val).removeClass("cond_selected")
            }
        });
 })
 
$(".more_indu").click(function(){//展开全部行业
        if(parseInt($(".hangye_ul").css("height"))<100){
            $(".hangye_ul").css({
                "height": "360px"
            })
        }else{
            $(".hangye_ul").css({
                "height": "50px"
            })
        }
    });

   