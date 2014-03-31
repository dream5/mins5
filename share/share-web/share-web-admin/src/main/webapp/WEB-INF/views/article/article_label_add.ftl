<#include "common/common.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<script type="text/javascript">
	function submitForm() {
		$('#articleLabelFormId').form('submit', {
			//	url:...,
			onSubmit : function() {
				var isValid = $(this).form('validate');
				if (isValid) {
			//		clearForm();
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
		$('#articleLabelFormId').form('clear');
		$('#status').combobox('setValue', '0');
	}
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<h2>添加文章标签</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>输入文章标签信息然后点击提交按钮</div>
	</div>
	<div style="margin: 10px 0;">
		<span id="tip" style="color: red"></span>
	</div>
	<div class="easyui-panel" title="添加文章标签" style="width: 800px">
		<div style="padding: 10px 0 10px 60px">
			<form id="articleLabelFormId" method="post"
				action="${path}/article/articleLabelAdd.mins">
				<table>
					<tr>
						<td>标签名称:</td>
						<td><input class="easyui-validatebox input-long" type="text"
							name="labelName" data-options="required:true"></input></td>
					</tr>
					<tr>
	    				<td>标签状态:</td>
		    			<td>
		    				<select class="easyui-combobox" id="status" name="status">
		    					<option value="0">未启用</option>
		    					<option value="1">启用</option>
		    				</select>
	    				</td>
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
</body>
</html>