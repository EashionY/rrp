function getMore(url,word,obj,size){//url,keyword,点击按钮，每页条数
	/*初始化*/
    var counter = 1; /*计数器*/
    var pageStart = 1; /*offset*/
    var pageSize = size; /*size*/
    /*首次加载*/
    if(word!=""){
    	$('#zhiwei_main').html("");
    	$('#zhiwei_main_mobile').html("");
    }
    var mydata1={page:pageStart,pageSize:pageSize};
    getData(url,word,mydata1);//开始页，一页条数
    /*监听加载更多*/
    $(obj).click(function(){
    	pageStart = pageStart+1;
        var mydata={page:pageStart,pageSize:pageSize};
        getData(url,word,mydata);
    })
}
function getData(url,word,mydata){
	    if(word!=""){
	    	mydata= $.extend({keyword:word},mydata);
	    }
        $.ajax({
            type: 'GET',
            url: url,
            dataType: 'json',
            data:mydata,
            success: function(reponse){
                var data = reponse.data;
                var sum = reponse.data.length;
                if(sum - mydata.page < mydata.pageSize){
                	mydata.pageSize = sum - mydata.page;
                } 
                addDom(data);//加载节点
                /*已经加载所有*/
                if (sum<=0){
                   layer.msg("已加载完，没有更多")
                }
            },
            error: function(xhr, type){
                layer.msg('Ajax error!');
            }
        });
    }
//添加首页职位列表节点
function addDom(data){
	var result = '';
	var myDate = new Date();
	var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy/MM/dd");
    /*使用循环*/
    $.each(data,function(i,v){
    	   var postDate=new Date(data[i].post_time).format("yyyy/MM/dd");
		   var showDate=''
		   if(nowDate==postDate){//当天发布
			   showDate=new Date(data[i].post_time).format("hh:mm")
		   }else{
			   showDate=new Date(data[i].post_time).format("yyyy/MM/dd")
		   }
		 if(window.screen.width<1024){
			 result +='<div class="zhiwei_box"><input class="myinput" value="'+data[i].post_id+'"/><div class="zhiwei_mainbox">'+
                '<div class="top"><div class="zhiwei_top_left">'+
                '<ul class="ul1"><li>'+data[i].post_name+'</li><li>['+data[i].region+']</li></ul>'+
                '<ul class="ul2"><li>'+data[i].work_exp+'</li><li>'+data[i].degree+'</li></ul></div>'+
                '<div class="zhiwei_top_right"><div class="salary_text">'+data[i].salary+'</div>'+
                '<div class="time_text">'+showDate+' 发布</div></div></div>'+
                '<div class="bottom"><div class="comName">'+data[i].name+'</div><input class="myinput" value="'+data[i].company_id+'"/>'+
                '<div>'+data[i].industry+'/'+data[i].financing+'</div></div></div></div>';
         }else{
        	 result +='<div class="zhiwei_box"><input class="myinput" value="'+data[i].post_id+'"/><div class="zhiwei_mainbox"><div class="top">'+
                '<div><ul><li>'+data[i].post_name+'</li><li>['+data[i].region+']</li><li>'+showDate+' 发布</li></ul>'+
                '<div class="salary_text">'+data[i].salary+'</div></div><div class="comName">'+data[i].name+'</div><input class="myinput" value="'+data[i].company_id+'"/>'+
                '</div><div class="bottom"><ul><li>'+data[i].work_exp+'</li><li>'+data[i].degree+'</li></ul>'+
                '<div>'+data[i].industry+'/'+data[i].financing+'</div></div></div></div>';
         }
    })
    if(window.screen.width<1024){
    	$('#zhiwei_main_mobile').append(result);
    }else{
    	$('#zhiwei_main').append(result);
    }
    
    $(".zhiwei_box").click(function(){
    	window.location.href="pages/Personal_edition/job/job_detail.html?postId="+$(this).children("input").val();
    })
    $(".comName").click(function(event){
    	window.location.href="pages/Personal_edition/company/company.html?companyId="+$(this).next().val();
    	event.stopPropagation();//阻止事件冒泡
    })

}

//加载时
$(function(){
	var url=ip+'/rrp/post/latestPostJob.do';
	var word="";
	var obj=$('.zhiwei_more1')
	if(window.screen.width<1024){
		getMore(url,word,obj,3);
	}else{
	    getMore(url,word,obj,5);
	}
}); 
//搜索
$(".serch_tubox").click(function(){
	if($(".serch_input").val().trim()!=""){
		var url=ip+"/rrp/post/searchPostCompany.do";
		var word=$(".serch_input").val().trim();
		var obj=$('.zhiwei_more2')
		if(window.screen.width<1024){
			getMore(url,word,obj,10);
		}else{
			$(".zhiwei_more1").css("display","none")
			$(".zhiwei_more2").css("display","block");
			getMore(url,word,obj,5);
		}
	}else{
		layer.msg("请先填写搜索条件")
	}
})



