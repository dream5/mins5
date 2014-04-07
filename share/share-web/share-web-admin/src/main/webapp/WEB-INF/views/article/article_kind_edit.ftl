<#include "common/common.ftl" />
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<script type="text/javascript">
	function submitForm() {
		$('#articleKindFormId').form('submit', {
			//	url:...,
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (isValid) {
				//	clearForm();
				}
				return isValid;
			},
			success : function(data) {
				clearForm();
				$("#tip").html(data);
				initArticleKindTree();
			}
		});
	}
	function clearForm() {
		$('#articleKindFormId')[0].reset();
		$('#status').combobox('setValue', '0');
	//	$('#parentKindId').combotree('setValue', '0');
	}
	function initArticleKindTree() {
		$('#parentKindId').combotree({
			url: '${path}/article/getArticleKind.mins'
	    });
	}
	function init() {
		var status = ${articleKind.status};
		$('#status').combobox('setValue', status);
	}
	$(document).ready(function() {
		init();
	//	initArticleKindTree();
	});
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<h2>修改文章类别</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>输入文章类别信息然后点击提交按钮</div>
	</div>
	<div style="margin: 10px 0;">
		<span id="tip" style="color: red"></span>
	</div>
	<div class="easyui-panel" title="修改文章类别" style="width: 800px">
		<div style="padding: 10px 0 10px 60px">
			<form id="articleKindFormId" method="post"
				action="${path}/article/articleKindEdit.mins">
				<input name="articleKindId" type="hidden" value="${articleKind.articleKindId }">
				<table>
					<tr>
						<td>类别名称:</td>
						<td><input class="easyui-validatebox input-long" type="text"
							name="kindName" data-options="required:true" value="${articleKind.kindName }"></input></td>
					</tr>
					<tr>
						<td>类别拼音:</td>
						<td><input class="easyui-validatebox input-long" type="text"
							name="kindPinyin" data-options="required:true" value="${articleKind.kindPinyin }"></input></td>
					</tr>
					<tr>
	    				<td>类别状态:</td>
		    			<td>
		    				<select class="easyui-combobox" id="status" name="status">
		    					<option value="0">无效</option>
		    					<option value="1">有效</option>
		    				</select>
	    				</td>
	    			</tr>
	    			<!-- 
	    			<tr>
	    				<td>所属类别:</td>
		    			<td>
		    				<input id="parentKindId" name="parentKindId" class="easyui-combotree" style="width:200px;">
	    				</td>
	    			</tr>
	    			 -->
				</table>
			</form>
		</div>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitForm()">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="clearForm()">重置</a>
		</div>
	</div>
</body>
</html>