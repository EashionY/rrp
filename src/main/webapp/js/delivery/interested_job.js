 $(function(){

        /*初始化*/
        var counter = 1; /*计数器*/
        var pageStart = 1; /*offset*/
        var pageSize = 5; /*size*/

        /*首次加载*/
        getData(pageStart, pageSize);//开始页，一页条数

        /*监听加载更多*/
        $(document).on('click', '.delivery_morezhiwei', function(){
            pageStart = pageStart+1;
            getData(pageStart, pageSize);
        });
});  
 
 function getData(offset,size){
     $.ajax({
         type: 'get',
         url: ip+'/rrp/post/interestJob.do',
         dataType: 'json',
         data:{userId:getCookieValue("userId"),page:offset,pageSize:size},
         success: function(reponse){
             //console.log(reponse);
             var data = reponse.data;
             var sum = reponse.data.length;
             
             if(sum==0){//如果感兴趣的搜索为0，则搜索最近发布的职位
            	 $.ajax({
                     type: 'get',
                     url: ip+'/rrp/post/latestPostJob.do',
                     dataType: 'json',
                     data:{page:offset,pageSize:5},
                     async: false,
                     success: function(reponse2){
                         data = reponse2.data;
                         sum = reponse2.data.length;
                     }
            	 })
             }
             
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
            
             /*使用循环*/
             $.each(data,function(i,v){
            	 result+='<div class="delivery_xsmain"><input class="myinput" value="'+v.post_id+'"><div class="delivery_xs_zhiwei">'+v.post_name+'</div>'+
				   	'<div class="delivery_xs_salary">'+v.salary+'</div><div class="delivery_xs_comname">'+v.name+'</div></div>';
             })

             $('.interest_box').append(result);
             $('.delivery_xsmain').click(function(){
            	 window.location.href="../job/job_detail.html?postId="+$(this).children().eq(0).val();
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