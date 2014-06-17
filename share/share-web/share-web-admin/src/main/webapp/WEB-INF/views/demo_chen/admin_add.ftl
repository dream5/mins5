<#include "common/common.ftl" />
<script type="text/javascript" src="${path}/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript">
	function submitForm() {
		$('#adminFormId').form('submit', {
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
	}
</script>
<title>Mins5后台管理</title>
</head>
<body>
	<div class="curPosition"><span class="sys-nav"><em>当前位置：新增管理员</em></span></div>
	<div class="content">
	<div style="margin: 10px 0;">
		<span id="tip" style="color: red"></span>
	</div>
	<div class="easyui-panel" title="juery" data-options="iconCls:'icon-add'" width="600px">
		<div style="padding: 10px 0 10px 60px">
			<form id="adminFormId" method="post"
				action="${path}/admin/adminAdd.mins">
				<table>
						<tr>
							<td align="right">
								用户名:
							</td>
							<td>
								<input type="text" name="userName"  class="easyui-validatebox input-short"  data-options="required:true">
							</td>
						</tr>
						<tr>
							<td align="right">
								密&nbsp;&nbsp;码:
							</td>
							<td>
								<input type="password" name="password"  class="easyui-validatebox input-short"  data-options="required:true">
							</td>
						</tr>
						<tr>
							<td align="right">
								昵 &nbsp;&nbsp;称:
							</td>
							<td>
								<input type="text" name="nickName"  class="easyui-validatebox input-short" data-options="required:true">
							</td>
						</tr>
						<tr>
							<td &nbsp;>
								真实姓名:
							</td>
							<td>
								<input type="text" name="realName"  class="easyui-validatebox input-short"  data-options="required:true">
							</td>
						</tr>
						<!--<tr>
							<td>
								创建时间:
							</td>
							<td>
								<input class="easyui-validatebox input-short"  data-options="required:true" type="text" name="createTime" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
							</td>
						</tr>-->
				</table>
			</form>
		</div>
		<div style="align: left; padding: 5px">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" class="easyui-linkbutton"
				onclick="submitForm()">提交</a> <a href="javascript:void(0)"
				class="easyui-linkbutton" onclick="clearForm()">重置</a>
		</div>
	</div>
	</div>
</body>
</html>