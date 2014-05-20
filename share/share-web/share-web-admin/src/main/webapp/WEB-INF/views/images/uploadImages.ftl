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

<div class="curPosition"><span class="sys-nav"><em>当前位置：图片管理</em></span></div>
	<div class="content">
	<div style="margin: 10px 0;">
		<span id="tip" style="color: red"></span>
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
				<th data-options="field:'path',width:100,align:'center',hidden:'true'">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div style="margin-bottom:5px">
			<a href="javascript:;" id="batchRemove" class="easyui-linkbutton" iconCls="icon-remove" plain="true">批量删除图片</a>
		</div>
		<div>
			开始时间: <input id="beginTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			结束时间: <input id="endTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			 <input id="search" type="button" onclick="loadTable();" value="查询">
			<!--<a href="#" onclick="loadTable();"  class="easyui-linkbutton" iconCls="icon-search">查询</a>-->
		</div>
	</div>
	<!--图片预览层-->
	<div id="imagesWin" class="easyui-window" title="图片预览" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:300px;height:200px;padding:10px;">
		<div>
			<img id="preview" src="" />
		</div>
	</div>
	
	</div>

<script type="text/javascript">
$(document).ready(function() {

	//初始化表格
    $.mins.createDataGrid({gridId:'dg',panelTitle:'上传图片列表',singleSelect:false,onDblClickRow:'showImages(i,r)'});
	loadTable();
	
	//批量移除
	     $('#batchRemove').click(function(){
	          var ids = $.mins.getCheckBoxIds({gridId:'dg',tip:'请选择文章！',dataId:'attachmentId'});
		 	  $.mins.confirm({paramId:'attachmentId',action:'${path}/upload/deleteImagesById.mins',dataId:ids,tip:'<font color=red>您确认要删除这些图片吗?</front>'});
	     });
	
});
var currentPage = 1;
var onePageSize = 10;
function loadTable() {
		var beginTime = $('#beginTime').datebox('getValue');
		var endTime = $('#endTime').datebox('getValue');
		var currentTime = new Date().getTime();
		var queryParams = {
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
	var operation = '<a href="#" onclick="javascript:;">无</>';
	return operation;
}

function showImages(index,row){
	$('#preview').attr('src','${path}/upload/'+row.midSize);
	$('#imagesWin').window('open');
}

</script>
</body>
</html>