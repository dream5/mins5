<#include "common/common.ftl" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<script type="text/javascript">
	var currentPage = 1;
	var onePageSize = 10;
	function buildDataGrid() {
		$('#dg').datagrid({
			title:"文章列表",
			rownumbers:true,
			singleSelect:true,
			pagination:true,
			pageList:[10, 20, 30, 40, 50],
			pageSize:10,
			toolbar:'#tb'
		});
		$('#dg').datagrid('getPager').pagination({
			onSelectPage:function(pageNum, pageSize){
				currentPage = pageNum;
				onePageSize = pageSize;
				loadTable();
			}
		});
	}
	function formatIsOriginal(val,row) {
		if (val == 0) {
			return '<span style="color:red;">否</span>';
		} else {
			return '<span style="color:green;">是</span>';
		}
	}
	function formatOperation(val,row) {
		var operation = '<a href="#" onclick="articleDetail('+val+')">查看</>';
		operation += '&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;';
		operation += '<a href="${path}/article/toArticleEdit.mins?articleId='+val+'">修改</>';
		operation += '&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;';
		operation += '<a href="#" onclick="deleteConfirm('+val+')">删除</>';
		operation += '&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;';
		operation += '<a href="#" onclick="articleDetail('+val+')">审核</>';
		operation += '&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;';
		operation += '<a href="#" onclick="publishedArticle('+val+')">发布</>';
		operation += '&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;';
		operation += '<a href="#" onclick="removedArticle('+val+')">下架</>';
		return operation;
	}
	function loadTable() {
		var articleTitle = $('#articleTitle').val();
		var beginTime = $('#beginTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		var status = $('#status').combobox('getValue');
		if(status == -1) {
			status = null;
		}
		var isOriginal = $('#isOriginal').combobox('getValue');
		if(isOriginal == -1) {
			isOriginal = null;
		}
		var currentTime = new Date().getTime();
		var queryParams = {
			'articleTitle':articleTitle,
			'beginTime':beginTime,
			'endTime':endTime,
			'status':status,
			'isOriginal':isOriginal,
			'currentPage':currentPage,
			'onePageSize':onePageSize,
			'currentTime':currentTime
		};
		jQuery.ajax({
		    url: '${path}/article/searchArticle.mins',
		    data: queryParams,
		    type: "POST",
		    dataType: "json",
		    success: function (msg) {
		    	$('#dg').datagrid('loadData', msg);
		    }
		});
	};
	function articleDetail(articleId){
		var queryParams = {"articleId":articleId};
		jQuery.ajax({
		    url: '${path}/article/articleDetail.mins',
		    data: queryParams,
		    type: "POST",
		    dataType: "json",
		    success: function (msg) {
		    	$('#detail_articleTitle').text(msg.articleTitle);
		    	$('#detail_articleUrl').text(msg.articleUrl);
		    	$('#detail_articleFrom').text(msg.articleFrom);
		    	$('#detail_articleAuthor').text(msg.articleAuthor);
		    	$('#detail_isOriginal').text(msg.isOriginal);
		  //  	$('#detail_articleLabel').text(msg.articleTitle);
		   // 	$('#detail_articleKind').text(msg.articleTitle);
		    	$('#detail_articleContent').text(msg.articleContent);
		    	$('#articleDetail').window('open');
		    },
		    error: function() {
		    	$.messager.alert('操作提示', '系统繁忙！');
		    }
		});
	}
	function deleteConfirm(articleId){
		$.messager.confirm('删除提示', '您确认删除此文章吗?', function(r){
			if(!r) {
				return;
			}
			var queryParams = {"articleId":articleId};
			jQuery.ajax({
			    url: '${path}/article/articleDelete.mins',
			    data: queryParams,
			    type: "POST",
			    dataType: "text",
			    success: function (msg) {
			    	$.messager.alert('删除提示', msg);
			    	loadTable();
			    },
			    error: function() {
			    	$.messager.alert('删除提示', '删除失败！');
			    }
			});
		});
	}
	function publishedArticle(articleId){
		$.messager.confirm('删除提示', '您确认发布此文章吗?', function(r){
			if(!r) {
				return;
			}
			var queryParams = {"articleId":articleId};
			jQuery.ajax({
			    url: '${path}/article/publishedArticle.mins',
			    data: queryParams,
			    type: "POST",
			    dataType: "text",
			    success: function (msg) {
			    	$.messager.alert('操作提示', msg);
			    	loadTable();
			    },
			    error: function() {
			    	$.messager.alert('操作提示', '发布失败！');
			    }
			});
		});
	}
	function removedArticle(articleId){
		$.messager.confirm('删除提示', '您确认下架此文章吗?', function(r){
			if(!r) {
				return;
			}
			var queryParams = {"articleId":articleId};
			jQuery.ajax({
			    url: '${path}/article/removedArticle.mins',
			    data: queryParams,
			    type: "POST",
			    dataType: "text",
			    success: function (msg) {
			    	$.messager.alert('操作提示', msg);
			    	loadTable();
			    },
			    error: function() {
			    	$.messager.alert('操作提示', '发布失败！');
			    }
			});
		});
	}
	$(document).ready(function() {
		buildDataGrid();
		loadTable();
	});
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<div style="margin:10px 0;"></div>
	<table id="dg" style="width:1000px;">
		<thead>
			<tr>
				<th data-options="field:'articleTitle',width:300,align:'left'">标题</th>
				<th data-options="field:'status',width:80,align:'center'">文章状态</th>
				<th data-options="field:'isOriginal',width:80,align:'center',formatter:formatIsOriginal">是否原创</th>
				<th data-options="field:'createTime',width:150,align:'center'">创建时间</th>
				<th data-options="field:'articleId',width:260,align:'center',formatter:formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="${path}/article/toArticleAdd.mins" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加文章</a>
		</div>
		<div>
			文章标题: <input id="articleTitle" type="text" style="width:100px">
			开始时间: <input id="beginTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			结束时间: <input id="endTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			文章状态: 
			<select id="status" class="easyui-combobox" style="width:60px" data-options="editable:false,panelHeight:'auto'">
				<option value="-1">全部</option>
				<#list articleStatusArray as status>
				<option value="${status.getCode() }">${status.getName() }</option>
				</#list>
			</select>
			是否原创: 
			<select id="isOriginal" class="easyui-combobox" style="width:60px" data-options="editable:false,panelHeight:'auto'">
				<option value="-1">全部</option>
				<option value="0">否</option>
				<option value="1">是</option>
			</select>
			<a href="#" onclick="loadTable();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
	<div id="articleDetail" class="easyui-window" title="查看文章" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:800px;height:500px;padding:10px;">
		<div style="padding: 10px 0 10px 60px">
			<table>
				<tr>
					<td>标题:</td>
					<td id="detail_articleTitle"></td>
				</tr>
				<tr>
					<td>来源url:</td>
					<td id="detail_articleUrl"></td>
				</tr>
				<tr>
					<td>文章来源描述:</td>
					<td id="detail_articleFrom"></td>
				</tr>
				<tr>
					<td>文章作者:</td>
					<td id="detail_articleAuthor"></td>
				</tr>
				<tr>
					<td>是否原创:</td>
					<td id="detail_isOriginal"></td>
				</tr>
				<tr>
					<td>文章标签:</td>
					<td id="detail_articleLabel"></td>
				</tr>
				<tr>
					<td>文章类别:</td>
					<td id="detail_articleKind"></td>
				</tr>
				<tr>
					<td>内容:</td>
					<td id="detail_articleContent"></td>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>