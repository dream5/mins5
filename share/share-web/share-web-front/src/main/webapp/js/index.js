/**
 *@author zhangl
 *@since 20140312
 */
$(function (){
	var timestamp = Date.parse(new Date());

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
		    		   navHtml +="<li><a href='#'>"+item.kindName+"</a></li>";
		    	   });
		    	   navHtml +="</ul>";
		    	   $(".menu_nav").html(navHtml);
		       } 
	       },
	       error:function(data){
	    	   $(".menu_nav").html("<ul><li><a href='http://www.mins5.com'>首页</a></li></ul>");
	       }
	});
	
	

	
	
	/*$.ajax({
	       type: "GET",
	       contentType : 'application/json;charset=UTF-8',
	       url: '/index/randomRead.mins',
	       data: {t:timestamp,a:6},
	       dataType: "json",
	       success:function(result) {
		       if(result == "error"){
		    	   $("#read").hide();
		       }else{
		    	   var readHtml ="";
		    	   $.each(result,function(index,item){
		    		   readHtml +="<li><a href='#' title='"+item.articleTitle+"'>"+item.articleTitle+"</a></li>";
		    	   });
		    	   $(".ex_menu").html(readHtml);
		       } 
	       },
	       error:function(data){
	    	   $("#read").hide();
	       }
	});
	
	
	$.ajax({
	       type: "GET",
	       contentType : 'application/json;charset=UTF-8',
	       url: '/index/hotLabel.mins',
	       data: {t:timestamp,a:10},
	       dataType: "json",
	       success:function(result) {
		       if(result == "error"){
		    	   $("#hotLabel").hide();
		       }else{
		    	   var labelHtml ="";
		    	   $.each(result,function(index,item){
		    		   labelHtml +="<a href='#' target='_blank' title='"+item.labelName+"'>"+item.labelName+"</a>";
		    	   });
		    	   $(".taglist").html(labelHtml);
		       } 
	       },
	       error:function(data){
	    	   $("#hotLabel").hide();
	       }
	});*/

});