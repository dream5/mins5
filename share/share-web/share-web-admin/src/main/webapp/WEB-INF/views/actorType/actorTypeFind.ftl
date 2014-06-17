<#--引用宏 -->
<#-- 填充数据--->
<#assign title="查看"/>
<#assign qryDataList=[{"content":"对象类型名称：","name":"model.actorTypeName","type":"view","seq":"1"},
                      {"content":"是否能登录：","propertyName":"actorType.canLogin","type":"domainStatus","seq":"2","tabName":"SCY_ACTOR_TYPE","colName":"CAN_LOGIN"}, 
                      {"content":"权限对象实体：","name":"actorType.entityName","type":"view","seq":"3"},
                      {"content":"认证SQL语句：","name":"actorType.authSql","type":"view","seq":"4"}, 
                      {"content":"展示SQL语句：","name":"actorType.showSql","type":"view","seq":"5"},
                      {"content":"展示方式：","propertyName":"actorType.showType","type":"domainStatus","seq":"6","tabName":"SCY_ACTOR_TYPE","colName":"SHOW_TYPE"},
                      {"content":"创建日期：","name":"actorType.createDate","type":"date","seq":"7","format":"yyyy-MM-dd hh:mm:ss"},
                      {"content":"状态日期：","name":"actorType.stsDate","type":"date","seq":"8","format":"yyyy-MM-dd hh:mm:ss"}
                     ] 
         rowNum=2  />
         
 <#assign buttonParams=[
           {"content":"关  闭","id":"cancel","seq":"1"}
           ]
         />
<#include "/templates/commonView.ftl">
<script type="text/javascript">
   $(function(){
     $('#cancel').click(function(){
               window.close();
  
      });
  });
</script>
