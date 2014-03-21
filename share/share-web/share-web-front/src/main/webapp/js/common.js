$(function() {
	$("#goTop").hide();
	//当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失
	$(function() {
		$(window).scroll(function() {
			if ($(window).scrollTop() > 100) {
				$("#goTop").fadeIn();
			} else {
				$("#goTop").fadeOut();
			}
		});
		//当点击跳转链接后，回到页面顶部位置
		$("#goTop").click(function() {
			$('body,html').animate({
				scrollTop : 0
			}, 500);
			return false;
		});
	});
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