$(function() {
	myonload1("com_xinxi.html","com_psd.html","../../Personal_edition/index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		$("#com_name").val(getCookieValue("companyName"));
		//logo处理
		var options =
        {
            thumbBox: '.thumbBox',
            spinner: '.spinner',
            imgSrc: ''
        };
        var cropper = $('.imageBox').cropbox(options);
        $('#upload-file').on('change', function(){
            var reader = new FileReader();
            reader.onload = function(e) {
                options.imgSrc = e.target.result;
                cropper = $('.imageBox').cropbox(options);
            }
            reader.readAsDataURL(this.files[0]);
           // this.files = [];
        })
        var imgMain
        $('#btnCrop').on('click', function(){
            imgMain= cropper.getDataURL();
            $('#imgDiv').html('');
            $('#imgDiv').append('<img src="'+imgMain+'" align="absmiddle" style="width:180px;box-shadow:0px 0px 12px #7E7E7E;" ><p>180px*180px</p>');
        })
        $('#btnZoomIn').on('click', function(){
            cropper.zoomIn();
        })
        $('#btnZoomOut').on('click', function(){
            cropper.zoomOut();
        })
        $("#imgbtn").click(function(){
        	if(imgMain==undefined){
                layer.msg("没有剪切图片")
            }else{
            	$(".comInfo_logo").html('<img id="com_logo" style="position:relative;z-index:800" src="'+imgMain+'">')
            	$("#img_mask").css("display","none")
            }
        })
	}
})
$("#commit_btn").click(function(){
	var list=new Array();
	list.push($("#com_name").val());//名字
	list.push($("#com_logo").attr("src"));//logo
	list.push($("#com_phone").val());//电话
	list.push($("#com_address").val());//地区
	list.push($("#com_address2").val());//详细地址
	list.push($("#com_industry").val());//行业
	list.push($("#com_scale").html());//规模
	list.push($(".comInfo_rzactiv").html());//融资
	list.push($("#work_content").val());//简介
	list.push($("#work_content2").val());//公司信息
	console.log($("#com_address").val()+$("#com_address2").val())
	var bool=false;
	$.each(list,function(k,v){
		if(list[k]==""||list[k]==undefined){
			layer.msg("内容不全")
			bool=false;
			return false;
		}else{
			bool=true;
		}
	})
	if(bool==true){
		$.get(ip+'/rrp/company/findCompanyInfo.do',{email:getCookieValue("email")},function(data){
			if(data.state==0){
				var com_id=data.data.id;
				$.ajax({
					type:"post",
					url:ip+'/rrp/company/addCompanyInfo.do',
					data:{id:com_id,name:$("#com_name").val(),logo:$("#com_logo").attr("src"),address:$("#com_address").val()+$("#com_address2").val(),industry:$("#com_industry").val(),website:$("#com_website").val(),scale:$("#com_scale").html(),financing:$(".comInfo_rzactiv").html(),intro:$("#work_content").val(),info:$("#work_content2").val(),tel:$("#com_phone").val()},
					contentType:'application/x-www-form-urlencoded; charset=UTF-8',
					success:function(result){
						console.log(result);
						if(result.state==0){
							addCookie("companyId",result.data.id,1,"/"); 
							layer.msg("信息完善成功",{
			              		  icon: 1,
			              		  time: 1000 
			                	}, function(){
			                	  window.location.href="com_xinxi.html";
			                	}); 
						}else{
							layer.msg(result.message)
						}
				
					}
				})
			}else{layer.msg(data.message)}
		},'json')
	}
	
	
	
	
})
			        
$(".comInfo_logo").click(function(){
     $("#img_mask").css("display","block")
})
 $("#imgbtn_quit").click(function(){
	$("#img_mask").css("display","none")
})