/**
 * <p>项目通用组件</p>
 * @author zhanglin
 * @date 2014-05-06 18:50
 * @param JQuery
 * 说明：
 * 1.生成GRID
 * $.mins.createDataGrid({gridId：‘dg’,panelTitle:'文章列表'});
 * 2.GRID加载数据
 * var fields = ['artileTitle','artileSts'];//查询参数名称
 * var values = ['苹果'，'1'];//查询参数对应的值
 * $.mins.loadGridData(fields,values,{url:'xxxxxxxxx'});
 * 3.删除，审核确认
 * $.mins.confirm({paramId:'articleId',action:'${path}/article/articleDelete.mins',dataId:数据ID,tip:'您确认删除此文章吗?'});
 * 4.格式化状态
 * $.mins.formatSts(value);
 * 5.获取复选框ID字符串（注：现在行ID字段，只能是id）
 * $.mins.getCheckBoxIds({gridId:'dg',tip:'请选择文章！'});
 * 
 */
var currentPage = 1;
var onePageSize = 10;
(function($) {
	var currentTime = new Date().getTime();
	$.mins = {
		init : function() {},
		createDataGrid:function(options){
			var defaults = {
				"gridId" : "dg",//Grid对应ID
				"toolbarId" : "tb",//搜索工具条对应ID
				"panelTitle" : "列表",//panel栏名称
				"onDblClickRow" : function(){},//双击函数
				"currentPage":1,
				"onePageSize":10,
				"singleSelect":true,
				"loadGrid":function(){}
			};
			var opts = $.extend(defaults, options);
			
			$(this).each(function() {
				
				$('#'+opts.gridId).datagrid({
						title:opts.panelTitle,
						rownumbers:true,
						singleSelect:opts.singleSelect,
						pagination:true,
						pageList:[10, 20, 30, 40, 50],
						pageSize:10,
				 		toolbar:'#'+opts.toolbarId,
						onDblClickRow :function(i,r){
			  				 eval(opts.onDblClickRow);
			 			 } 
					});
				$('#'+opts.gridId).datagrid('getPager').pagination({
					    
						onSelectPage:function(pageNum, pageSize){
						 currentPage = pageNum;
						 onePageSize = pageSize;
						 eval(opts.loadGrid);
					}
				});
				
			});
			
			
		},
		loadGridData:function(fields,values,options){
			
			var defaults = {
				"gridId" : "dg",//Grid对应ID
				"url" : "",//请求URL
			};
			
			var opts = $.extend(defaults, options);
			
			$(this).each(function() {
				if(fields.length = values.length){
				var s="({";
				
				for(var i=0;i<fields.length;i++){
					if(i!=(fields.length-1)){
						s+=fields[i]+":'"+values[i]+"',";
					}else if(i==(fields.length-1)){
						s+=fields[i]+":'"+values[i]+"',";
						//追加分页参数到最后
						s+="currentPage:"+currentPage+",";
						s+="onePageSize:"+onePageSize+",";
						s+="currentTime:"+currentTime+"})";
					}
				}
				var p=eval(s);
				$.ajax({
				    url: opts.url,
				    data: p,
				    type: "POST",
				    dataType: "json",
				    success: function (msg) {
				    	$('#'+opts.gridId).datagrid('loadData', msg);
				    }
				});
				
			}else{
				$.messager.alert('提示', '参数错误！');
			}
				
			});
			
		},
		getCheckBoxIds:function(options){//得到复选框ID字符串id1,id2,id3
			var defaults = {
					"gridId" : "dg",//Grid对应ID
					"tip" : "请选择记录！",
					"dataId":""
				};
				var opts = $.extend(defaults, options);
				var ids= "";
				$(this).each(function() {
					var checkedItems = $('#'+opts.gridId).datagrid('getChecked');
					var temp = [];
		            $.each(checkedItems, function(index, item){
		            		
		            	for(var attr in item)
		            	{
		            		if(attr.toString()==opts.dataId.toString()){
		            			temp.push(item[attr]);
		            		}
		            	}
					}); 
					if(temp.length==0){
						$.messager.alert('确认提示', opts.tip);
						return;
					}        
			 	    ids = temp.join(",");
				});
				return ids;
		},
		confirm : function(options) {
			var defaults = {
				"paramId" : "id",//action中参数名
				"dataId" : "",
				"tip" : "你确认要删除此项记录吗？",
				"action" : ""
			};
			var opts = $.extend(defaults, options);
			$(this).each(function() {

				$.messager.confirm('确认提示', opts.tip, function(r) {
					if (!r) {
						return;
					}
					var queryParams = {};
					queryParams[opts.paramId] = opts.dataId;

						$.ajax({
							url : opts.action,
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

			});
		},
		formatSts : function(v,args) {//args,为1或0值代表的中文意思，如['未发布','已发布'];
			if (v == 'WAITING_CHECK') {
				return '<span style="color:red;">'+args[0]+'</span>';
			} else {
				return '<span style="color:green;">'+args[1]+'</span>';
			}
		},//v为值，r为行对象，args操作对象args
		formatOperation:function(v,row,args){
			 $.each(args, function(i, item) {
            	alert(item.name);
       	 });
		},
		mark:function(){
			$.messager.progress({title:'请稍后',msg:'正在处理中...'}); 
		},
		unmark:function(){
			$.messager.progress('close');
		}

	};
})(jQuery);
