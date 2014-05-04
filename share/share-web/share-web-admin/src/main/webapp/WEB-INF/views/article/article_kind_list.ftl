<#include "common/common.ftl" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<script type="text/javascript">
	
	function buildTreegrid() {
		$('#dg').treegrid({
			title:"文章类别列表",
			rownumbers:true,
			singleSelect:true,
			idField: 'articleKindId',
			treeField: 'kindName',
			toolbar:'#tb'
		});
	}
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
		var currentTime = new Date().getTime();
		var queryParams = {
			'currentTime':currentTime
		};
		jQuery.ajax({
		    url: '${path}/article/searchArticleKind.mins',
		    data: queryParams,
		    type: "POST",
		    dataType: "json",
		    success: function (msg) {
		    	$('#dg').treegrid('loadData', msg);
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
	$(document).ready(function() {
		buildTreegrid();
		loadTable();
	});
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<div class="curPosition"><span class="sys-nav"></span><span>当前位置：类别管理管理</span></div>
	<div class="content">
		<table id="dg">
			<thead>
				<tr>
					<th data-options="field:'kindName',width:200,align:'left'">类别名称</th>
					<th data-options="field:'kindPinyin',width:200,align:'center'">类别拼音</th>
					<th data-options="field:'status',width:80,align:'center',formatter:formatStatus">类别状态</th>
					<th data-options="field:'createTime',width:150,align:'center'">类别生成时间</th>
					<th data-options="field:'articleKindId',width:100,align:'center',formatter:formatOperation">操作</th>
				</tr>
			</thead>
		</table>
		<div id="tb" style="padding:5px;height:auto">
			<div style="margin-bottom:5px">
				<a href="${path}/article/toArticleKindAdd.mins" class="easyui-linkbutton" iconCls="icon-add" plain="true">添加类别</a>
			</div>
		</div>
	</div>
</body>
</html>