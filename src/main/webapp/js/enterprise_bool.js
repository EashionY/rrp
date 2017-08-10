function to_open(comStatus){
	if(comStatus==0||comStatus=="undefined"){//邮箱未验证
		layer.msg("还未开通企业版，先前往开通",{
    	    icon: 7,
    		time: 3000 
      	}, function(){
      		window.location.href="../Personal_edition/enterprise/enterprise1.html";
      	}); 
	}else if(comStatus==1){//邮箱已验证
		console.log("2")
		window.location.href="../Personal_edition/enterprise/enterprise1.html";
	}else if(comStatus==2){//公司资料审核中
		console.log("3")
		window.location.href="../Personal_edition/enterprise/enterprise1.html";
	}else if(comStatus==-1){//审核未通过
		console.log("4")
		window.location.href="../Personal_edition/enterprise/enterprise1.html";
	}
}
function to_open_inner(comStatus){
	if(comStatus==0||comStatus=="undefined"){//邮箱未验证
		layer.msg("还未开通企业版，先前往开通",{
    	    icon: 7,
    		time: 3000 
      	}, function(){
      		window.location.href="../../Personal_edition/enterprise/enterprise1.html";
      	}); 
	}else if(comStatus==1){//邮箱已验证
		console.log("2")
		window.location.href="../../Personal_edition/enterprise/enterprise1.html";
	}else if(comStatus==2){//公司资料审核中
		console.log("3")
		window.location.href="../../Personal_edition/enterprise/enterprise1.html";
	}else if(comStatus==-1){//审核未通过
		console.log("4")
		window.location.href="../../Personal_edition/enterprise/enterprise1.html";
	}
}