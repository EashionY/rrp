jQuery.divselect = function(divselectid,inputselectid) {
	var inputselect = $(inputselectid);
	$(divselectid+" cite").click(function(){

		var ul = $(divselectid+" ul");

		if(ul.css("display")=="none"){
			//ul.slideDown("fast");
			ul.css("display","block")
		}else{
			ul.css("display","none")
			//ul.slideUp("fast");
		}
	});
	$(divselectid+" ul li a").click(function(){
		var txt = $(this).text();
		$(divselectid+" cite").children(".input_text").html(txt);
		var value = $(this).attr("selectid");
		console.log(value)
		inputselect.val(value);
		$(divselectid+" ul").css("display","none");
	});
	$(document).click(function(){
		$(divselectid+" ul").css("display","none");
	});
};