<#include "common/common.ftl" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<#include "common/common_js.ftl" />
<link rel="stylesheet" type="text/css" href="${path}/js/uploadify/uploadSend.css">
<script type="text/javascript" src="${path}/js/uploadify/swfobject.js"></script>
<script type="text/javascript" src="${path}/js/uploadify/jquery.uploadify.v2.1.4.js"></script>
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
	<div class="curPosition"><span class="sys-nav"><em>当前位置：新增文章</em></span><span style="float:right;"><a href="javascript:history.go(-1);">返回上一步</a></span></div>
	<div class="content">
	<div style="margin: 10px 0;">
		<span id="tip" style="color: red"></span>
	</div>
	<div class="easyui-panel" title="添加文章" data-options="iconCls:'icon-add'">
		<div style="padding: 10px 0 10px 60px">
			<form id="articleFormId" method="post"
				action="${path}/article/articleAdd.mins">
				<input type="hidden" name="attachmentId" value="" id="attachmentIds" />
				<table>
					<tr>
						<td>标题:</td>
						<td><input class="easyui-validatebox input-long" type="text" 
							name="articleTitle" data-options="required:true"></input></td>
					</tr>
					<tr>
						<td>文章顶部导图地址:</td>
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
						<td>
							<textarea id="editor_id" name="articleContent" style="width: 800px;height: 500px;"></textarea>
						</td>
					</tr>
					<tr>
						<td>上传相关图片:</td>
						<td>
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
			var json = $.parseJSON(response);
			$('#attachmentIds').val(json.attachmentId);
			$.messager.alert('操作提示','文件:' + fileObj.name + '上传成功!');
		}

	});

	
});

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