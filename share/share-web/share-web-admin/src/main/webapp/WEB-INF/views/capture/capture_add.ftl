<#include "common/common.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<script type="text/javascript">
	function submitForm() {
		$('#captureForm').form('submit', {
			onSubmit : function() {
				beginMark();
				var isValid = $(this).form('validate');
				if (isValid) {
					clearForm();
				}
				return;
			},
			success : function(data) {
				endMark();
				clearForm();
				$("#tip").html(data);
			}
		});
	}
	function clearForm() {
		$('#captureForm').form('clear');
	}

	$(document).ready(function() {
		//initArticleLabel();
		//initArticleKind();
	});
</script>
<script type="text/javascript">
	var currentPage = 1;
	var onePageSize = 10;
	function buildDataGrid() {
		$('#dg').datagrid({
			title:"抓取文章列表",
			rownumbers:true,
			singleSelect:true,
			pagination:true,
			pageList:[10, 20, 30, 40, 50],
			pageSize:10,
			toolbar:'#tb',
			onDblClickRow :function(rowIndex,rowData){
  				  articleDetail(rowData.articleId);
 			 } 
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
		    url: '${path}/article/searchCaptureArticle.mins',
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
		    url: '${path}/article/captureArticleDetail.mins',
		    data: queryParams,
		    type: "POST",
		    dataType: "json",
		    success: function (msg) {
		    	$('#detail_articleTitle').text(msg.articleTitle);
		    	$('#detail_articleUrl').attr("src",msg.articleUrl);
		    	$('#detail_articleFrom').html(msg.articleFrom);
		    	$('#detail_articleAuthor').text(msg.articleAuthor);
		    	$('#detail_isOriginal').text(msg.isOriginal);
		    	$('#detail_articleContent').html(msg.articleContent);
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
		 $("textarea").keydown(function(event) {
	        if (event.keyCode == "13") {
		        e = $(this).val();
				$(this).val(e + ';');
				           
	        }
	    });
	});
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<div class="curPosition"><span class="sys-nav"><em>当前位置：文章抓取</em></span></div>
	<div class="content">
		<div style="margin: 10px 0;">
			<span id="tip" style="color: red"></span>
		</div>
		<div class="easyui-panel" title="抓取URL" data-options="iconCls:'icon-add'">
			<div style="padding: 10px 0 10px 60px">
				<form id="captureForm" method="post" action="${path}/capture/beginCapture.mins">
					<table>
						<tr>
							<td>URL:</td>
							<td><textarea class="easyui-validatebox textarea-article"
									name="captureUrl" data-options="required:true" style="width: 600px;height: 100px;"></textarea>
							</td>
							<td>
								<a href="javascript:void(0)" class="easyui-linkbutton" onclick="submitForm()">开始抓取</a>
							</td>
							<td><font color="red">（注：每个URL请已分号";"结尾！！！）</font></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		
		<div class="dbGrid">
			<table id="dg" class="gridHead">
		<thead>
			<tr>
				<th data-options="field:'articleTitle',width:400,align:'left'">标题<font color="red">（双击记录，可预览文章！）</font></th>
				<th data-options="field:'articleFrom',width:120,align:'center'">来源</th>
				<th data-options="field:'articleAuthor',width:120,align:'center'">作者</th>
				<th data-options="field:'createTime',width:150,align:'center'">创建时间</th>
				<th data-options="field:'articleId',width:300,align:'center',formatter:formatOperation">操作</th>
			</tr>
		</thead>
	</table>
	<div id="tb" style="padding:5px;height:auto">
		<div>
			文章标题: <input id="articleTitle" type="text" style="width:200px">
			开始时间: <input id="beginTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			结束时间: <input id="endTime" class="easyui-datebox" style="width:100px" data-options="editable:false">
			<a href="#" onclick="loadTable();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
		</div>
	</div>
		
		</div>
		<!--预览层-->
	<div id="articleDetail" class="easyui-window" title="查看文章" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:800px;height:500px;padding:10px;">
		<div style="padding: 10px 0 10px 60px">
		
			<div class="article">
	          <h2 id="detail_articleTitle"></h2>
	          <div class="cline"></div>
	          <p>
		          <span>时间:&nbsp;</span>
		          <span style="margin-left:25px;" >来源:&nbsp;<em id="detail_articleFrom"></em></span>
		          <span style="margin-left:25px;">作者:&nbsp;<em id="detail_articleAuthor"></em></span>&nbsp;&nbsp;
		                             浏览（18）
	          	</p>
	         	 <img id="detail_articleUrl" src="#" width="613" height="193" alt="" />
	          <div class="cline"></div>
	         	<div id="detail_articleContent"></div>
	          <p class="links" id="detail_articleLabel"></p>
	         </div>
		</div>
	</div>
	</div>
</body>
</html>