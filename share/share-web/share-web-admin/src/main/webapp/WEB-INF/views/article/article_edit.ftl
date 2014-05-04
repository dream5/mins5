<#include "common/common.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<script type="text/javascript">
	function submitForm() {
		$('#articleFormId').form('submit', {
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
			}
		});
	}
	function clearForm() {
		$('#articleFormId')[0].reset();
		init();
	}
	function init() {
		var isOriginal = ${article.isOriginal};
		$('#isOriginal').combobox('setValue', isOriginal);
	}
	$(document).ready(function() {
		init();
	});
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<div class="curPosition"><span class="sys-nav"></span><span>当前位置：修改文章</span></div>
	<div class="content">
	<div style="margin: 10px 0;">
		<span id="tip" style="color: red"></span>
	</div>
	<div class="easyui-panel" title="修改文章" data-options="iconCls:'icon-edit'">
		<div style="padding: 10px 0 10px 60px">
			<form id="articleFormId" method="post"
				action="${path}/article/articleEdit.mins">
				<table>
					<tr>
						<td>标题:</td>
						<td><input class="easyui-validatebox input-long" type="text" width="240px"
							name="articleTitle" data-options="required:true" value="${article.articleTitle }"></input></td>
					</tr>
					<tr>
						<td>来源url:</td>
						<td><input class="easyui-validatebox input-long" type="text"
							name="articleUrl" value='${article.articleUrl }'></input></td>
					</tr>
					<tr>
						<td>文章来源描述:</td>
						<td><input class="easyui-validatebox input-long" type="text"
							name="articleFrom" value='${article.articleFrom }'></input></td>
					</tr>
					<tr>
						<td>文章作者:</td>
						<td><input class="easyui-validatebox input-short" type="text"
							name="articleAuthor" data-options="required:true" value="${article.articleAuthor }"></input></td>
					</tr>
					<tr>
						<td>是否原创:</td>
						<td><select id="isOriginal" class="easyui-combobox"
							style="height: 1" name="isOriginal"
							data-options="required:true,editable:false,panelHeight:'auto'">
								<option value="0">否</option>
								<option value="1" selected="selected">是</option>
						</select></td>
					</tr>
					<tr>
						<td>内容:</td>
						<td><textarea class="easyui-validatebox textarea-article"
								name="articleContent" data-options="required:true" style="width: 800px;height: 500px;">${article.articleContent }</textarea></td>
					</tr>
				</table>
			</form>
		</div>
		<div style="text-align: center; padding: 5px">
			<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitForm()">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="clearForm()">重置</a>
		</div>
	</div>
	</div>
</body>
</html>