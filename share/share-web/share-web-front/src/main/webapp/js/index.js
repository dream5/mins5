/**
 *@author zhangl
 *@since 20140312
 */
$(function (){
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