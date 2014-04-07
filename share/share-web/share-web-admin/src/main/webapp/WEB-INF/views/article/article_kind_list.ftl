<#include "common/common.ftl" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<script type="text/javascript">
	var currentPage = 1;
	var onePageSize = 10;
	$(document).ready(function() {
		$('#dg').datagrid({
			title:"文章类别列表",
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
		loadTable();
	});
	function formatStatus(val,row) {
		if (val == 0) {
			return '<span style="color:red;">无效</span>';
		} else {
			return '<span style="color:green;">有效</span>';
		}
	}
	function formatOperation(val,row) {
		var operation = '<a href="${path}/article/toArticleKindEdit.mins?articleKindId='+val+'">修改</>';
		operation += '&nbsp;&nbsp;&nbsp;';
		operation += '<a href="#" onclick="deleteConfirm('+val+')">删除</>';
		return operation;
	}
	function loadTable() {
		var kindName = $('#kindName').val();
		var beginTime = $('#beginTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		var status = $('#status').combobox('getValue');
		if(status == -1) {
			status = null;
		}
		var currentTime = new Date().getTime();
		var queryParams = {
			'kindName':kindName,
			'beginTime':beginTime,
			'endTime':endTime,
			'status':status,
			'currentPage':currentPage,
			'onePageSize':onePageSize,
			'currentTime':currentTime
		};
		jQuery.ajax({
		    url: '${path}/article/searchArticleKind.mins',
		    data: queryParams,
		    type: "POST",
		    dataType: "json",
		    success: function (msg) {
		    	$('#dg').datagrid('loadData', msg);
		    }
		});
	};
	function deleteConfirm(articleKindId){
		$.messager.confirm('删除提示', '您确认删除此类别吗?', function(r){
			if(!r) {
				return;
			}
			var queryParams = {"articleKindId":articleKindId};
			jQuery.ajax({
			    url: '${path}/article/articleKindDelete.mins',
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
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<div style="margin:10px 0;"></div>
	<table id="dg" style="width:700px;">
		<thead>
			<tr>
				<th data-options="field:'kindName',width:100,align:'center'">标签名称</th>
				<th data-options="field:'status',width:80,align:'center',formatter:formatStatus">标签状态</th>
				<th data-options="field:'createTime',width:150,align:'center'">标签生成时间</th>
				<th data-options="field:'articleKindId',width:100,align:'center',formatter:formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="${path}/article/toArticleKindAdd.mins" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加类别</a>
		</div>
		<div>
			类别名称: <input id="kindName" type="text" style="width:100px">
			开始时间: <input id="beginTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			结束时间: <input id="endTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			类别状态: 
			<select id="status" class="easyui-combobox" style="width:60px" data-options="editable:false,panelHeight:'auto'">
				<option value="-1">全部</option>
				<option value="0">无效</option>
				<option value="1">有效</option>
			</select>
			<a href="#" onclick="loadTable();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
</body>
</html>