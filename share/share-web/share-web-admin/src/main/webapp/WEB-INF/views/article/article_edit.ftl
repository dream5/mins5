<#include "common/common.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<script type="text/javascript" src="${path}/js/kindeditor/kindeditor.js"></script>
<script type="text/javascript" src="${path}/js/kindeditor/lang/zh_CN.js"></script>
<script type="text/javascript">
	function submitForm() {
		
		// 取得HTML内容
		var articleContent = editor.html();
		articleContent = $.trim(articleContent);
		if(articleContent == "") {
			$.messager.alert('提示','文章内容不能为空!','warning');
			return;
		}
		
		// 同步数据后可以直接取得textarea的value
		window.editor.sync();
	
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
				// clearForm();
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
		
		KindEditor.ready(function(K) {
        	window.editor = K.create('#editor_id', {
    			items : [
					'source', '|', 'undo', 'redo', '|', 'preview', /*'print', 'template', 'code',*/ 'cut', 'copy', 'paste',
					'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
					'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
					'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
					'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
					'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', /*'image', 'multiimage',*/
					/*'flash', 'media',*/ 'insertfile', 'table', 'hr', /*'emoticons', 'baidumap', 'pagebreak',*/
					'anchor', 'link', 'unlink'/*, '|', 'about'*/
				]
			});
        });
	});
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<div class="curPosition"><span class="sys-nav"><em>当前位置：修改文章</em></span><span style="float:right;"><a href="javascript:history.go(-1);">返回上一步</a></span></div>
	<div class="content">
	<div style="margin: 10px 0;">
		<span id="tip" style="color: red"></span>
	</div>
	<div class="easyui-panel" title="修改文章" data-options="iconCls:'icon-edit'">
		<div style="padding: 10px 0 10px 60px">
			<form id="articleFormId" method="post" action="${path}/article/articleEdit.mins">
			<input type="hidden" name="articleId" value="${article.articleId }" />
				<table>
					<tr>
						<td>标题:</td>
						<td><input class="easyui-validatebox input-long" type="text" width="240px"
							name="articleTitle" data-options="required:true" value="${article.articleTitle }"></input></td>
					</tr>
					<tr>
						<td>文章顶部导图地址:</td>
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
						<td>
							<textarea id="editor_id" name="articleContent" style="width: 800px;height: 500px;">${article.articleContent }</textarea>
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
	</div>
</body>
</html>