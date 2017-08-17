 $(function(){

        /*初始化*/
        var counter = 1; /*计数器*/
        var pageStart = 1; /*offset*/
        var pageSize = 5; /*size*/

        /*首次加载*/
        getData(pageStart, pageSize);//开始页，一页条数

        /*监听加载更多*/
        $(document).on('click', '.zhiwei_more1', function(){
            pageStart = pageStart+1;
            getData(pageStart, pageSize);
        });
    });    

function getData(offset,size){
        $.ajax({
            type: 'GET',
            url: ip+'/rrp/post/latestPostJob.do',
            dataType: 'json',
            data:{page:offset,pageSize:size},
            success: function(reponse){
                //console.log(reponse);
                var data = reponse.data;
                var sum = reponse.data.length;
                var result = '';
                /****业务逻辑块：实现拼接html内容并append到页面*********/
                // console.log(offset , size, sum);
                //console.log(sum);
                /*如果剩下的记录数不够分页，就让分页数取剩下的记录数
                 * 例如分页数是5，只剩2条，则只取2条
                 *
                 * 实际MySQL查询时不写这个不会有问题
                 */
                if(sum - offset < size ){
                    size = sum - offset;
                } 
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
                    result +='<div class="zhiwei_box"><input class="myinput" value="'+data[i].post_id+'"/><div class="zhiwei_mainbox"><div class="top">'+
                    '<div><ul><li>'+data[i].post_name+'</li><li>['+data[i].region+']</li><li>'+showDate+' 发布</li></ul>'+
                    '<div class="salary_text">'+data[i].salary+'</div></div><div class="comName">'+data[i].name+'</div><input class="myinput" value="'+data[i].company_id+'"/>'+
                    '</div><div class="bottom"><ul><li>'+data[i].work_exp+'</li><li>'+data[i].degree+'</li></ul>'+
                    '<div>'+data[i].industry+'/'+data[i].financing+'</div></div></div></div>';
                })

                $('#zhiwei_main').append(result);
                
                $(".zhiwei_box").click(function(){
                	window.location.href="pages/Personal_edition/job/job_detail.html?postId="+$(this).children("input").val();
                })
                $(".comName").click(function(event){
                	window.location.href="pages/Personal_edition/company/company.html?companyId="+$(this).next().val();
                	event.stopPropagation();//阻止事件冒泡
                })

                /*******************************************/

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

//搜索
$(".serch_tubox").click(function(){
	if($(".serch_input").val().trim()!=""){
		$(".zhiwei_more1").css("display","none")
		$(".zhiwei_more2").css("display","block");
		/*初始化*/
        var counter = 1; /*计数器*/
        var pageStart = 1; /*offset*/
        var pageSize = 5; /*size*/
        
        var key=$(".serch_input").val().trim();
        /*首次加载*/
        $('#zhiwei_main').html("");
        getData2(key,pageStart, pageSize);//开始页，一页条数

        /*监听加载更多*/
        $(document).on('click', '.zhiwei_more2', function(){
            pageStart = pageStart+1;
            getData2(key,pageStart, pageSize);
        });
		
	}else{
		layer.msg("请先填写搜索条件")
	}
})

function getData2(key,offset,size){//搜索后更多
        $.ajax({
            type: 'GET',
            url: ip+"/rrp/post/searchPostCompany.do",
            dataType: 'json',
            data:{keyword:key,page:offset,pageSize:size},
            success: function(reponse){
                //console.log(reponse);
                var data = reponse.data;
                var sum = reponse.data.length;
                var result = '';
                /****业务逻辑块：实现拼接html内容并append到页面*********/
                // console.log(offset , size, sum);
                //console.log(sum);
                /*如果剩下的记录数不够分页，就让分页数取剩下的记录数
                 * 例如分页数是5，只剩2条，则只取2条
                 *
                 * 实际MySQL查询时不写这个不会有问题
                 */
                if(sum - offset < size ){
                    size = sum - offset;
                } 
                var myDate = new Date();
				var nowDate=new Date(myDate.toLocaleDateString()).format("yyyy/MM/dd");
                /*使用循环*/
                $.each(data,function(i,v){
                	   var postDate=new Date(data[i].post_time).format("yyyy/MM/dd");
					   var showDate=''
					   if(nowDate==postDate){//当天发布
						   showDate=new Date(data[i].post_time).format("hh:mm")
					   }else{
						   showDate=new Date(data[i].post_time).format("yy年MM月dd日")
					   }
                    result +='<div class="zhiwei_box"><input class="myinput" value="'+data[i].post_id+'"/><div class="zhiwei_mainbox"><div class="top">'+
                    '<div><ul><li>'+data[i].post_name+'</li><li>['+data[i].region+']</li><li>'+showDate+' 发布</li></ul>'+
                    '<div class="salary_text">'+data[i].salary+'</div></div><div class="comName">'+data[i].name+'</div><input class="myinput" value="'+data[i].company_id+'"/>'+
                    '</div><div class="bottom"><ul><li>'+data[i].work_exp+'</li><li>'+data[i].degree+'</li></ul>'+
                    '<div>'+data[i].industry+'/'+data[i].financing+'</div></div></div></div>';
                })

                $('#zhiwei_main').append(result);
                
                $(".zhiwei_box").click(function(){
                	window.location.href="pages/Personal_edition/job/job_detail.html?postId="+$(this).children("input").val();
                })
                $(".comName").click(function(event){
                	window.location.href="pages/Personal_edition/company/company.html?companyId="+$(this).next().val();
                	event.stopPropagation();//阻止事件冒泡
                })
                /*******************************************/

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
