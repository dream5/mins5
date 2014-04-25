$(function() {
	$.fn.toTop = function(options) {
		var defaults = {			
			showHeight : 150,
			speed : 1000
		};
		var options = $.extend(defaults,options);
		$("body").prepend("<div id='totop'><a></a></div>");
		var $toTop = $(this);
		var $top = $("#totop");
		var $ta = $("#totop a");
		$toTop.scroll(function(){
			var scrolltop=$(this).scrollTop();		
			if(scrolltop>=options.showHeight){				
				$top.show();
			}
			else{
				$top.hide();
			}
		});	
		$ta.hover(function(){ 		
			$(this).addClass("cur");	
		},function(){			
			$(this).removeClass("cur");		
		});	
		$top.click(function(){
			$("html,body").animate({scrollTop: 0}, options.speed);	
		});
	};
	
	
	var menu = $('#menu'),
	pos = menu.offset();
	$(window).scroll(function(){
		if($(this).scrollTop() > pos.top+menu.height() && menu.hasClass('default')){
			menu.fadeOut('fast', function(){
				$(this).removeClass('default').addClass('fixed').fadeIn('fast');
			});
		} else if($(this).scrollTop() <= pos.top && menu.hasClass('fixed')){
			menu.fadeOut('fast', function(){
				$(this).removeClass('fixed').addClass('default').fadeIn('fast');
			});
		}
	});
	
	
	$(window).toTop({
		showHeight : 100,//设置滚动高度时显示
		speed : 500 //返回顶部的速度以毫秒为单位
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
