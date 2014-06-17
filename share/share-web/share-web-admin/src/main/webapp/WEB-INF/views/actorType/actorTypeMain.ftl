<#-- Main模板参数定义--->
<#assign titleContent="权限作用对象列表"/>
<#assign qryDataList=[{"content":"对象类型名称：","name":"actorType.actorTypeName","type":"text","seq":"1"},
                      {"content":"权限对象实体：","name":"actorType.entityName","type":"text","seq":"2"},
                      {"content":"认证SQL语句：","name":"actorType.authSql","type":"text","seq":"3"},
                      {"content":"展示方式：","name":"actorType.showType","type":"selectDomain","seq":"4","tabName":"SCY_ACTOR_TYPE","colName":"SHOW_TYPE"}]
                      
         buttonParams=[{"content":"查&nbsp;&nbsp;询","id":"qryButton","seq":"1"},
         			   {"content":"重&nbsp;&nbsp;置","id":"resetButton","seq":"2"},
                       {"content":"新&nbsp;&nbsp;增","id":"addButton","seq":"3"}]
         rowNum=2  />
<#-- Main模板引入-->
<#include "/templates/commonMain.ftl"> 
<#-- JS模板引入-->
<@commJsMacro qryUrl="${path}/security/actorType_list.action"  qryBtton="qryButton"/>

<script lanugage="javascript">
$('select[name="actorType.showType"] option:first').before("<option value=''>--请选择--</option>");
$('select[name="actorType.showType"]').val('');

//
$('#resetButton').bind('click',function(){
		$("input[name='actorType.actorTypeName']").val('');
	     $("input[name='actorType.entityName']").val('');
	       $("input[name='actorType.authSql']").val('');
	         $("select[name='actorType.showType']").val('');
});

//添加
 $(function(){
     $('#addButton').click(function(){
                 var url = "${path}/security/actorType_add.action";
                 var retVal = window.showModalDialog(url,window,
					"dialogHeight: 500px; dialogWidth: 750px; center: yes; help: no;status:yes;title:no;scroll:yes");	           
					
	       if(retVal == 'y'){
	       	window.location.href='${path}/security/actorType_init.action';
	       	}        
    });
 })

</script>