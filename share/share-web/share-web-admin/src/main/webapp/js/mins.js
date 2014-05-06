/**
 * <p>项目通用组件</p>
 * @author zhanglin
 * @date 2014-05-06 18:50
 * @param JQuery
 */
(function($) {
	$.fn.mins = function(options) {
		alert(1);
		var defaults = {
			"id" : "dbGrid"
		};
		var opts = $.extend(defaults, options);

	};

	//通用异步方法，用于删除记录，修改状态
	$.fn.mins.confirm = function(options) {
		
		var defaults = {
				"id" : "dbGrid"
		};
		alert(1);
		var opts = $.extend(defaults, options); 
		this.each(function(){
			alert(2+opts.id);
		});
		alert(3);
		return;
		$.messager.confirm('确认提示', tip, function(r) {
			if (!r) {
				return;
			}
			var queryParams = {
				paramName : id
			};
			jQuery.ajax({
				url : url,
				data : queryParams,
				type : "POST",
				dataType : "text",
				success : function(msg) {
					$.messager.alert('确认提示', msg);
					loadTable();
				},
				error : function() {
					$.messager.alert('确认提示', '操作失败！');
				}
			});
		});
	};
	
	//格式化状态
	$.fn.mins.formatSts= function(opts){
		if (value == 0) {
			return '<span style="color:red;">否</span>';
		} else {
			return '<span style="color:green;">是</span>';
		}
	};
	

})(jQuery);
