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
<table class="navTb" cellspacing="0" cellpadding="0"  border="0" >
	<tbody>
		<tr>
			<td>当前位置：图片管理 &gt; 上传图片</td>
		</tr>
	</tbody>
</table>
<#include "frame/top.ftl" />
<table class="contentTb" cellspacing="0" cellpadding="0"  border="0">
	<tbody>
		<tr>
		    <td width=15 background=${path}/images/frame/new_022.jpg><img src="${path}/images/frame/new_022.jpg" border=0></td>
		    <td valign=top width="100%" bgcolor=#ffffff>
				<table width="100%" id="upload">
	     <tr>
		<td width="30%" align="left" valign="middle">
			<input type="hidden" name="extname" id="extname" />
			<input type="file" name="uploadify" id="uploadify" />
		</td>
		<td width="70%" align="left" valign="middle"
			style="padding-top: 9px;">
			<input type="button" class="but_bg" style="float: left;"
				onclick="javascript:exeUpload()" value="上传" />
			<input type="button" class="but_bg" style="float: left;"
				onclick="javascript:jQuery('#uploadify').uploadifyClearQueue();"
				value="撤销" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div id="fileQueue"></div>
		</td>
	</tr>
</table>
<div id="info"></div>
</td>
<td width=15 background=${path}/images/frame/new_023.jpg><img src="${path}/images/frame/new_023.jpg" border="0"></td>
</tr>
</tbody>
</table>
<#include "frame/footer.ftl" />
<script type="text/javascript">
$(document).ready(function() {

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
			alert("上传错误！");
		},
		'onSelect':function(event,queueID,fileObj) { 
			$("#extname").val(fileObj.type);
		},
		'onComplete':function(event,queueID,fileObj,response) { 
			alert("文件:" + fileObj.name + "上传成功");
		}

	});
});

function exeUpload(){
	$('#uploadify').uploadifySettings('scriptData', {'filetype':$('#extname').val()});
	jQuery('#uploadify').uploadifyUpload();
}
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
</script>
</body>
</html>