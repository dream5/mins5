$(function() {
	
	//init menu
	var timestamp = Date.parse(new Date());
	var currentUrl = document.URL;
	$.ajax({
	       type: "GET",
	       contentType : 'application/json;charset=UTF-8',
	       url: '/index/initNav.mins',
	       data: {t:timestamp},
	       dataType: "json",
	       success:function(result) {
		       if(result == "error"){
		    	   $(".menu_nav").html("<ul><li><a href='http://www.mins5.com'>首页</a></li></ul>");
		       }else{
		    	   var navHtml = "<ul>";
	    		   navHtml +="<li><a href='http://www.mins5.com'>首页</a></li>";
		    	   $.each(result,function(index,item){
		    		   navHtml +="<li><a href='"+currentUrl+item.kindPinyin+"/'>"+item.kindName+"</a></li>";
		    	   });
		    	   navHtml +="</ul>";
		    	   $(".menu_nav").html(navHtml);
		       } 
	       },
	       error:function(data){
	    	   $(".menu_nav").html("<ul><li><a href='http://www.mins5.com'>首页</a></li></ul>");
	       }
	});
	

});


$('.keywords').blur(function() {
	if ($(this).val().length == 0) {
		$(this).val("搜索你感兴趣的文章...");
		$(this).css("color", "#a5a4a4");
	}
});
$('.keywords').focus(function() {
	if ($(this).val() == "搜索你感兴趣的文章...") {
		$(this).val("");
		$(this).css("color", "#000");
	}
}); 

//search
$(".sbtn").click(function(){
	   var k  = $('.keywords').val();
		if(k=='搜索你感兴趣的文章...'||k==null ||k==''){
			alert("请输入搜索关键字...");
			return;
		}
		$("#sform").submit();
});

/**
 * 解析时间对象成字符串
 * @param data
 * @returns {String}
 */
function genStrDateTimeAll(data){
	if(data == null){
		return "";
	}else{
		var c = new Date(); 
		c.setTime(data.time);
		var mon = c.getMonth()+1;
		if(mon<10){
			mon = "0" + mon;
		}
		var dat = c.getDate();
		if(dat<10){
			dat = "0" + dat;
		}
		return c.getFullYear()+"-"+mon+"-"+dat+" "+c.getHours()+":"+c.getMinutes()+":"+c.getSeconds();
	}
}