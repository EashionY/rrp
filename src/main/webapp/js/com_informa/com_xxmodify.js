var boollogo=false;

var jianjie1= UE.getEditor('com_jianjie1',{
	toolbars: [
	    ['undo', 'redo'],
	],initialFrameHeight:200,initialFrameWidth:542
});
var xinxi1= UE.getEditor('com_xinxi1',{
	toolbars: [
	    ['undo', 'redo'],
	],initialFrameHeight:200,initialFrameWidth:542
});

$(function() {
	myonload1("com_xinxi.html","com_psd.html","../../Personal_edition/index.html");
	var userId=getCookieValue("userId");
	if(userId==""){//未登录，请先登录
	    window.location.href="../../Personal_edition/login.html";
	}else{
		var comStatus=getStatus();
		if(comStatus==3){//判断是否开通企业版
			$.get(ip+"/rrp/company/findCompanyInfo.do",{email:getCookieValue("email")},function(data){
				 if(data.state==0){
					//logo处理
						var options =
				        {
				            thumbBox: '.thumbBox',
				            spinner: '.spinner',
				            imgSrc: '../../../../'+data.data.logo
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
				            	boollogo=true;
				            	$(".comInfo_logo").html('<img id="com_logo" style="position:relative;z-index:800" src="'+imgMain+'">')
				            	$("#img_mask").css("display","none")
				            }
				        })
					 
					 
					 $(".comInfo_logo").html('<img id="com_logo" style="position:relative;z-index:800" src="../../../../'+data.data.logo+'">');
					 $("#com_moName").val(data.data.name);
					 $("#com_moPhone").val(data.data.tel);
					 $("#com_moWz").val(data.data.website);
					 $("#license_img").attr("src",'../../../../'+data.data.license)
					 var address=data.data.address;
					 $("#com_moDq").val(address.slice(0,7))//地区
					 $("#com_moDz").val(address.slice(7,address.length))//地址
					 $("#com_moLy").val(data.data.industry);
					 $("#com_moGm").html(data.data.scale);
					 //console.log($("#com_logo").attr("src"))
					 var rz=data.data.financing;
					 var reDom=$(".rz");
					 for(var i=0;i<reDom.length;i++){
						 if(rz==$(reDom[i]).html()){
							 $.each(reDom,function(k,v){
								 if(k==i){
									$(reDom[k]).addClass("rz comInfo_rzactiv");
								 }else{
									$(reDom[k]).removeClass("rz comInfo_rzactiv");
									$(reDom[k]).addClass("rz");
								 }
							 })
						 }
					 }
					 jianjie1.addListener("ready", function () { 
							jianjie1.setContent(data.data.intro);
						});
					 xinxi1.addListener("ready", function () { 
						 xinxi1.setContent(data.data.info);
					 });
				 }else{
					 layer.msg(data.message)
				 }
			 },'json');
		}else{
			to_open_inner(comStatus);//判断跳转页面
		}
	}
})
$("#com_commit").click(function(){
	 layer.confirm("修改资料后，须重新进行审核，是否继续？", {icon: 3, title:'提示'},function(index){
		 $.get(ip+'/rrp/company/findCompanyInfo.do',{email:getCookieValue("email")},function(data){
				if(data.state==0){
					var com_id=data.data.id;
					var form=document.getElementById("myform");
					var formData = new FormData(form);
					formData.append("id",com_id);
					formData.append("name",$("#com_moName").val());
					if(boollogo==true){
						formData.append("logo",$("#com_logo").attr("src"));
					}
					formData.append("address",$("#com_moDq").val()+$("#com_moDz").val());
					formData.append("industry",$("#com_moLy").val());
					formData.append("website",$("#com_moWz").val());
					formData.append("scale",$("#com_moGm").html());
					formData.append("financing",$(".comInfo_rzactiv").html());
					formData.append("intro",jianjie1.getContent());
					formData.append("info",xinxi1.getContent());
					formData.append("tel",$("#com_moPhone").val());
					$.ajax({
						type:"post",
						url:ip+'/rrp/company/addCompanyInfo.do',
						data:formData,
						contentType: false,
		                processData: false,
						success:function(result){
							console.log(result)
							if(result.state==0){
								layer.msg("信息修改成功",{
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
	 })
     
})
$(".comInfo_logo").click(function(){
	$(".mymask").css("display","block")
})
 $("#imgbtn_quit").click(function(){
	$("#img_mask").css("display","none")
})
$("#lice_check").click(function() {
	$(this).css("display","none");
	$("#license_img").css("display","block")
});
$("#license_img").click(function() {
	$(this).css("display","none");
	$("#lice_check").css("display","inline-block")
})

