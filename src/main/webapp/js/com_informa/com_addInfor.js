$(function() {
	myonload2("../../Personal_edition/my/pers_infor.html","../../Personal_edition/index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	     window.location.href="../../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		console.log(comStatus)
		if(comStatus!=0 && comStatus!=2){//判断是否开通企业版
			if(getCookieValue("companyName")!="undefined"){
				$("#com_name").val(getCookieValue("companyName"));
			}			
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
		}else if(comStatus==2){//审核中
			window.location.href="../../Personal_edition/enterprise/enterprise_status.html"
		}else{//未验证邮箱
			window.location.href="../../Personal_edition/enterprise/enterprise1.html"
		}
	}
})
$("#commit_btn").click(function(){
	
	var list=new Array();
	list.push($("#com_name").val());//名字
	list.push($("#com_logo").attr("src"));//logo
	list.push($("#com_zhizhao").val());//营业执照
	list.push($("#com_phone").val());//电话
	list.push($("#com_address").val());//地区
	list.push($("#com_address2").val());//详细地址
	list.push($("#com_industry").val());//行业
	list.push($("#com_scale").html());//规模
	list.push($(".comInfo_rzactiv").html());//融资
	list.push(jianjie.getContent());//简介
	list.push(xinxi.getContent());//公司信息
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
				var form=document.getElementById("myform");
				var formData = new FormData(form);
				formData.append("id",com_id);
				formData.append("name",$("#com_name").val());
				formData.append("logo",$("#com_logo").attr("src"));
				formData.append("address",$("#com_address").val()+$("#com_address2").val());
				formData.append("industry",$("#com_industry").val());
				formData.append("website",$("#com_website").val());
				formData.append("scale",$("#com_scale").html());
				formData.append("financing",$(".comInfo_rzactiv").html());
				formData.append("intro",jianjie.getContent());
				formData.append("info",xinxi.getContent());
				formData.append("tel",$("#com_phone").val());
				$.ajax({
					type:"post",
					url:ip+'/rrp/company/addCompanyInfo.do',
					data:formData,
					contentType: false,
	                processData: false,
					success:function(result){
						console.log(result);
						if(result.state==0){
							addCookie("companyId",result.data.id,1,"/"); 
							addCookie("companyName",result.data.name,1,"/");
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