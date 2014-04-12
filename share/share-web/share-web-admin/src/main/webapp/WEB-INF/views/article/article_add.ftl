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
		$('#articleFormId').form('clear');
		$('#isOriginal').combobox('setValue', '1');
	}
	function initArticleLabel() {
		$('#articleLabel').combogrid({
			url: '${path}/article/searchArticleLabel.mins?currentPage=0&onePageSize=1000'
	    });
	}
	function formatLabelStatus(val,row) {
		if (val == 0) {
			return '<span style="color:red;">未启用</span>';
		} else {
			return '<span style="color:green;">启用</span>';
		}
	}
	function initArticleKind() {
		$('#articleKind').combotree({
			url: '${path}/article/getArticleKind.mins'
	    });
	}
	$(document).ready(function() {
		initArticleLabel();
		initArticleKind();
	});
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<h2>添加文章</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>输入文章信息然后点击提交按钮</div>
	</div>
	<div style="margin: 10px 0;">
		<span id="tip" style="color: red"></span>
	</div>
	<div class="easyui-panel" title="添加文章" style="width: 1000px">
		<div style="padding: 10px 0 10px 60px">
			<form id="articleFormId" method="post"
				action="${path}/article/articleAdd.mins">
				<table>
					<tr>
						<td>标题:</td>
						<td><input class="easyui-validatebox input-long" type="text"
							name="articleTitle" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>来源url:</td>
						<td><input class="easyui-validatebox input-long" type="text"
							name="articleUrl"></input></td>
					</tr>
					<tr>
						<td>文章来源描述:</td>
						<td><input class="easyui-validatebox input-long" type="text"
							name="articleFrom"></input></td>
					</tr>
					<tr>
						<td>文章作者:</td>
						<td><input class="easyui-validatebox input-short" type="text"
							name="articleAuthor" data-options="required:true"></input></td>
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
						<td>文章标签:</td>
						<td>
							<select id="articleLabel" name="articleLabel" class="easyui-combogrid" style="width:200px" data-options="
									panelWidth: 350,
									multiple: true,
									idField: 'labelId',
									textField: 'labelName',
									columns: [[
										{field:'labelId',checkbox:true},
										{field:'labelName',title:'标签名称',width:100},
										{field:'status',title:'标签状态',width:80,align:'center',formatter:formatLabelStatus},
										{field:'createTime',title:'标签生成时间',width:150,align:'center'},
									]],
									fitColumns: true,
									editable:false,
									required:true
								">
							</select>
						</td>
					</tr>
					<tr>
						<td>文章类别:</td>
						<td>
							<input id="articleKind" name="articleKind" class="easyui-combotree" data-options="editable:false, required:true" multiple style="width:200px;">
						</td>
					</tr>
					<tr>
						<td>内容:</td>
						<td><textarea class="easyui-validatebox textarea-article"
								name="articleContent" data-options="required:true" style="width: 800px;height: 500px;"></textarea></td>
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