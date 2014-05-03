<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>G12E 文件上传</title>
<link rel="stylesheet" href="uploadSend.css" type="text/css" />
<style>
.descspan {color:#f00; font-size:13px; }
</style>
<script type="text/javascript" src="jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="swfobject.js"></script>
<script type="text/javascript" src="jquery.uploadify.v2.1.4.js"></script>
<script type="text/javascript" src="${_currConText}/js/mask.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var type = '${param.type}';
	var desc = '';
	var pic = '*.gif;*.jpg;*.jpeg;*.bmp;*.png';
	var doc = '*.doc;*.docx;*.xls;*.xlsx;*.ppt;*.pptx;*.pdf;*.txt;*.rar;*.zip';
	var fileSize=0;
	if(type != '' && type =='doc'){
		desc=doc;
		fileSize=200*1024*1024;
	}else{
		desc=pic;
		fileSize=3*1024*1024;
	}
	$("#uploadify").uploadify({
		'uploader': 'uploadify.swf?var='+(new Date()).getTime(),
		'cancelImg': 'cancel.png',
		'script': 'upload.jsp',	
		'method': 'get',
		'fileDesc': '请选择要上传的文件',
		'fileExt': desc,
		//'folder': 'files',
		'queueID': 'fileQueue',
		'multi': false,
		'auto':false,
		'sizeLimit': fileSize,
		//'displayData': 'speed',
		'buttonText': 'BROWSE',
		'buttonImg':'liulan_bg.gif',
		'onError':function(event,queueID,fileObj,errorObj) { 
			alert("上传的所有文件不能超过200M，支持的格式有.doc,.docx,.xls,.xlsx,.ppt,.pptx,.pdf。\r\n如需上传其它格式的文件，可压缩为.rar或.zip格式后上传。");
		},
		'onSelect':function(event,queueID,fileObj) { 
			$("#extname").val(fileObj.type);
		},
		'onComplete':function(event,queueID,fileObj,response) { 
			//document.upload.filesize.value=fileObj.size;
			//alert(queueID);
			//alert(fileObj.size);
			//alert(trim(response));
			//alert($(window.parent.document).find("#${param.dom}"));
			var jsonObj=eval("("+trim(response)+")");
			$(window.parent.document).find("#${param.dom}").val(jsonObj[0].newName);
			if("${param.oldDom }"!=null&&"${param.oldDom }"!=""&&"${param.oldDom }"!="undefind"){
				$(window.parent.document).find("#${param.oldDom}").val(jsonObj[0].oldName);
				$(window.parent.document).find("#${param.oldDom}Show").html(jsonObj[0].oldName);
			}
			//window.parent.$.colorbox.close();
			//closeLayer();
			
			window.parent.closeLayer();
			
		}

	});
});

function exeUpload(){
	$('#uploadify').uploadifySettings('scriptData', {'filetype':$('#extname').val(),'proVal': $('#proVal').val()});
	jQuery('#uploadify').uploadifyUpload();
}
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
</script>
</head>

<body>
<table width="100%" id="upload">
	<tr>
		<td colspan="2">
			<div id="fileQueue"></div>
		</td>
	</tr>
	<tr>
		<td width="30%" align="left" valign="middle">
			<input type="hidden" name="extname" id="extname" />
			<input type="file" name="uploadify" id="uploadify" />
			<input type="hidden" name="proVal" id="proVal" value="${param.proVal}" />
		</td>
		<td width="70%" align="left" valign="middle"
			style="padding-top: 9px;">
			<input type="button" class="but_bg" style="float: left;"
				onclick="javascript:exeUpload()" value="上传" />
			<input type="button" class="but_bg" style="float: left;"
				onclick="javascript:jQuery('#uploadify').uploadifyClearQueue()"
				value="撤销" />
		</td>
	</tr>
	<%if("WORK_PATH".equals(request.getParameter("proVal"))){ %>
	<tr>
		<td colspan="2">
	<span class="descspan">上传的所有文件不能超过200M，支持的格式有.doc,.docx,.xls,.xlsx,.ppt,.pptx,.pdf。</span>
	<span class="descspan">如需上传其它格式的文件，可压缩为.rar或.zip格式后上传。</span>
		</td>
	</tr>
	<%} %>
</table>
<div id="info"></div>
</body>
</html>
