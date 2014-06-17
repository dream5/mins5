<!--引用宏 -->
<#include "/templates/head.ftl"><#--模板head部分  -->
<#--填充自定义标签数据 -->
<#assign  pagingResultName="pagingResult" 
          frmName="frm"
          showDivName="operatDiv"
          url="${path}/security/actorType_list.action"
/>
<#--填充需要展示结果集 -->
<#assign  showListResult=[{"title":"对象类型名称","name":"actorTypeName","id":"actorTypeId"},
						  {"title":"是否能登录","propertyName":"canLogin","type":"domainStatus","tabName":"SCY_ACTOR_TYPE","colName":"CAN_LOGIN"},
	                      {"title":"权限对象实体","name":"entityName"},
	                      {"title":"认证SQL语句","name":"authSql"},
	                      {"title":"展示方式","propertyName":"showType","type":"domainStatus","tabName":"SCY_ACTOR_TYPE","colName":"SHOW_TYPE"},
	                      {"title":"创建日期","name":"createDate","type":"date","format":"yyyy-MM-dd hh:mm:ss"}
                         ],
          columnHeadName="操作", 
          oprateLink=[{"title":"查看","methodname":"toView","param":"actorTypeId"},
	                 {"title":"修改","methodname":"toEdit","param":"actorTypeId"},
	                 {"title":"删除","methodname":"toDelete","param":"actorTypeId"}]
          
/>

<#--导入模板 -->
<#include "/templates/commonList.ftl">

<script  language="javascript" type="type/javascript">
function toView(actorId,t){
	 var winParam = 'dialogHeight: 660px; dialogWidth: 880px; center: yes; help: no;status:yes;title:no;scroll:yes;resizable:yes';
	 var url = "${path}/security/actorType_find.action?actorTypeId="+actorId;
     window.showModalDialog(url,window,winParam);
     
}

//修改
 function toEdit(actorId,v2){
          var url = "${path}/security/actorType_update.action?actorTypeId="+actorId
          var retVal = window.showModalDialog(url,window,
					"dialogHeight: 500px; dialogWidth: 750px; center: yes; help: no;status:yes;title:no;scroll:yes");
		   if(retVal == 'y'){
	       	window.location.href='${path}/security/actorType_init.action';
	       	}   
  }
  
    //删除
  function toDelete(v1,v2){
  			if(!confirm("确定要删除吗？")){
				return;
		    }
             $.ajax({ 
		                'url' : '${path}/security/actorType_delete.action',
		                'type' : 'post',
		                'data' : {
		                    	 'actorTypeId':v1
	                             },
	                	'success' : function(data, statusText) {
	                	              if(data>0){
	                	               alert('删除成功！');
	                	              }
		                              $('#qryButton').click();
		                            },
		                'error' : function(xhr, e1, e2) {

	                              	}
	                   });        
  
  }

</script>