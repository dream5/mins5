<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>G12E 文件上传</title>
<link rel="stylesheet" type="text/css" href="/expupload/uploadify.css"/>
<script type="text/javascript" src="/expupload/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="/expupload/swfobject.js"></script>
<script type="text/javascript" src="/expupload/jquery.uploadify.v2.1.4.js"></script>
<title>upload上传文件</title>
<style>
.descspan {color:#f00; font-size:13px; }
</style>
<script type="text/javascript">
$(document).ready(function() {
	var type = '${param.type}';
	var desc = '';
	var pic = '*.gif;*.jpg;*.jpeg;*.bmp;*.png';
	var doc = '*.doc;*.docx;*.xls;*.xlsx;*.pdf';
	if(type != '' && type =='doc'){
		desc=doc; 
	}else{
		desc=pic;
	}
	$("#uploadify").uploadify({
		'uploader': '/expupload/uploadify.swf?var='+(new Date()).getTime(),
		'cancelImg': '/expupload/cancel.png',
		'script': '/expupload/multiUpload.jsp',	
		'cancelImg' : '/expupload/cancel.png',            //取消按钮。设定图片路径
		'method': 'get',
		'scriptData' : {'proVal':$('#proVal').val()},
		'fileExt' : '*.gif;*.jpg;*.jpeg;*.bmp;*.png;*.doc;*.docx;*.xls;*.xlsx;*.pdf;*.avi;*.swf;*.flv;*.wmv;*.rar;*.zip;*.mpg;*.mkv;*.txt',              //支持的格式，启用本项时需同时声明fileDesc
		'fileDesc' : '文件格式(.doc, .docx, .xls, .xlsx, .pdf, .avi, .swf, .flv, .wmv, .rar, .zip, .mpg, .mkv,.gif, .jpg, .jpeg, .bmp, .png)',     //出现在上传对话框中的文件类型描述。与fileExt需同时使用
		//'folder': 'files',
		'queueID': 'fileQueue',
		'multi': true,
		'auto':false,
		'sizeLimit': 50*1024*1024,
		//'displayData': 'speed',
		'queueSizeLimit': 10,                                //可以一次选定几个文件
		'simUploadLimit': 1,                                // 一次可传几个文件
		'removeCompleted': false,
		'buttonImg':'/expupload/liulan_bg.gif',
		'onError':function(event,queueID,fileObj,errorObj) { 
			//alert("文件上传异常"+fileObj);
			//alert("文件上传异常"+errorObj.info);
			alert("上传失败！请按以下说明重新上传。\r\n"
					+"上传的所有文件不能超过20M，支持的格式有.gif;*.jpg;*.jpeg;*.bmp;\r\n"
					+"*.png;*.doc;*.docx;;*.pdf;*.rar;*.zip;"+
					+"如需上传其它格式的文件，\r\n可压缩为.rar或.zip格式后上传。");
		},
		'onSelect':function(event,queueID,fileObj) { 
			 
			//$("#fileQueue").css("border","1px solid #E5E5E5");
		},
		'onClearQueue' : function(event) { 
		},
		'onComplete':function(event,queueID,fileObj,response) { 
			$(window.parent.document).find("#${param.dom}").append(trim(response));
		},
		'onAllComplete':function(event,data) {  
            //当所有文件上传完成后的操作  
			$(window.parent.document).find('#fileForm').submit();
		}
		

	});
});

function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

//上传 
function upload(){
	jQuery('#uploadify').uploadifyUpload() ;
}
//取消上传  
function cancelUpload(){
	jQuery('#uploadify').uploadifyClearQueue();
}

</script>
</head>
<body>
<table width="100%" id="upload">
	<tr>
		<td width="120px" align="left" valign="middle">
			<input type="hidden" name="extname" id="extname" />
			<input type="file" name="uploadify" id="uploadify" />
			<input type="hidden" name="proVal" id="proVal" value="${param.proVal }" />
		</td>
		<td align="left" valign="middle"
			style="padding-top: 9px;">
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<div id="fileQueue"></div>
		</td>
	</tr>
	 
	<tr>
		<td colspan="2">
		<div  class="descspan">
			<br />
		提示: 上传的所有文件不能超过20M,支持的格式有: .gif;*.jpg;*.jpeg;*.bmp;	<br />
		*.png;*.doc;*.docx;*.pdf;*.rar;*.zip;。
		如需上传其它格式的文件，<br />
		可压缩为.rar或.zip格式后上传。
		</div>
		
	 
		</td>
	</tr>
 
</table>
</body>
 
</html> 