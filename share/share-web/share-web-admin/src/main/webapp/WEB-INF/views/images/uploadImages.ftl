<#include "common/common.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<link rel="stylesheet" type="text/css" href="${path}/js/uploadify/uploadSend.css">
<script type="text/javascript" src="${path}/js/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${path}/js/uploadify/jquery.uploadify.v2.1.4.js"></script>
<title>Mins5后台管理</title>
</head>
<body>

<div class="curPosition"><span class="sys-nav"><em>当前位置：图片上传</em></span></div>
	<div class="content">
	<div style="margin: 10px 0;">
		<span id="tip" style="color: red"></span>
	</div>
	<div class="easyui-panel" title="图片上传" data-options="iconCls:'icon-add'">
		<div style="padding: 10px 0 10px 60px">
			<form id="articleFormId" method="post" action="">
			   <input type="hidden" name="extname" id="extname" />
			   <input type="file" name="uploadify" id="uploadify" />
				<table id="upload">
					<tr>
						<td colspan="2">
						<input type="button" class="but_bg" style="float: left;" onclick="javascript:exeUpload()" value="上传" />
						<input type="button" class="but_bg" style="float: left;" onclick="javascript:jQuery('#uploadify').uploadifyClearQueue();"
				value="撤销" /></td>
					</tr>
					<tr>
						<td colspan="2">
								<div id="fileQueue"></div>
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	
	
	<div class="dbGrid">
			<table id="dg" class="gridHead">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'articleId',width:80,align:'left'">文章ID</th>
				<th data-options="field:'attachmentOldName',width:150,align:'left'">上传前名称</th>
				<th data-options="field:'attachmentName',width:200,align:'left'">新名称</th>
				<th data-options="field:'attachmentType',width:80,align:'center'">后缀名称</th>
				<th data-options="field:'createDate',width:120,align:'center'">上传时间</th>
				<th data-options="field:'large',width:200,align:'center'">大尺寸图名称</th>
				<th data-options="field:'midSize',width:200,align:'center'">中尺寸图名称</th>
				<th data-options="field:'small',width:200,align:'center'">小尺寸图名称</th>
				<th data-options="field:'attachmenSts',width:100,align:'center', formatter:formatSts">状态</th>
				<th data-options="field:'attachmentId',width:100,align:'center',formatter:formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" id="batchPublish" class="easyui-linkbutton" iconCls="icon-add" plain="true">批量发布文章</a>
			<a href="javascript:;" id="batchRemove" class="easyui-linkbutton" iconCls="icon-remove" plain="true">批量清空已发布文章</a>
		</div>
		<div>
			文章标题: <input id="articleTitle" type="text" style="width:200px">
			开始时间: <input id="beginTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			结束时间: <input id="endTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			 <input id="search" type="button" onclick="loadTable();" value="查询">
			<!--<a href="#" onclick="loadTable();"  class="easyui-linkbutton" iconCls="icon-search">查询</a>-->
		</div>
	</div>
	</div>

<script type="text/javascript">
$(document).ready(function() {

	//上传图片
	var desc = '*.gif;*.jpg;*.jpeg;*.bmp;*.png';
	var fileSize=3*1024*1024;
	$("#uploadify").uploadify({
		'uploader': '${path}/js/uploadify/uploadify.swf?t='+(new Date()).getTime(),
		'cancelImg': '${path}/js/uploadify/cancel.png',
		'script': '${path}/upload/beginUpload.mins',	
		'method': 'get',
		'fileDesc': '请选择要上传的文件',
		'fileExt': desc,
		//'folder': 'files',
		'queueID': 'fileQueue',
		'multi': false,
		'auto':false,
		'sizeLimit': fileSize,
		'displayData': 'speed',
		'wmode': 'transparent',
		'buttonText': 'BROWSE',
		'buttonImg':'${path}/js/uploadify/liulan_bg.gif',
		'onError':function(event,queueID,fileObj,errorObj) { 
			$.messager.alert('操作提示', '上传错误！');
		},
		'onSelect':function(event,queueID,fileObj) { 
			$("#extname").val(fileObj.type);
		},
		'onComplete':function(event,queueID,fileObj,response) { 
			$.mins.unmark();
			$.messager.alert('操作提示','文件:' + fileObj.name + '上传成功!');
			loadTable();
		}

	});
	
	
	//初始化表格
    $.mins.createDataGrid({gridId:'dg',panelTitle:'上传图片列表'});
	loadTable();
	
});
var currentPage = 1;
var onePageSize = 10;
function loadTable() {
		var articleTitle = $('#articleTitle').val();
		var beginTime = $('#beginTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		var currentTime = new Date().getTime();
		var queryParams = {
			'articleTitle':articleTitle,
			'beginTime':beginTime,
			'endTime':endTime,
			'currentPage':currentPage,
			'onePageSize':onePageSize,
			'currentTime':currentTime
		};
		jQuery.ajax({
		    url: '${path}/upload/searchUploadImages.mins',
		    data: queryParams,
		    type: "POST",
		    dataType: "json",
		    success: function (msg) {
		    	$('#dg').datagrid('loadData', msg);
		    }
		});
	}

function formatSts(val,row) {
	    var stsValue=['未发布','已发布'];
		return $.mins.formatSts(val,stsValue);
}

function formatOperation(val,row) {
	var operation = '<a href="#" onclick="articleDetail('+val+')">删除</>';
	return operation;
}

function exeUpload(){
	$.mins.mark();
	$('#uploadify').uploadifySettings('scriptData', {'filetype':$('#extname').val()});
	jQuery('#uploadify').uploadifyUpload();
}
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
</script>
</body>
</html>