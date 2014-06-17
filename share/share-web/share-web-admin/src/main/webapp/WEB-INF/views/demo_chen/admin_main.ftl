<#-- Main模板参数定义--->
<#assign titleContent="管理员管理"/>
<#assign qryDataList=[{"content":"对象类型名称：","name":"userName","type":"text","seq":"1"},
                      {"content":"状态：","name":"status","type":"selectDomain","seq":"2","tabName":"ADMIN","colName":"STATUS"}]
                      
         buttonParams=[{"content":"查&nbsp;&nbsp;询","id":"qryButton","seq":"1"},
         			   {"content":"重&nbsp;&nbsp;置","id":"resetButton","seq":"2"},
                       {"content":"新&nbsp;&nbsp;增","id":"addButton","seq":"3"}]
         rowNum=2  />
<#-- Main模板引入-->
<#include "common/commonMain.ftl" />

