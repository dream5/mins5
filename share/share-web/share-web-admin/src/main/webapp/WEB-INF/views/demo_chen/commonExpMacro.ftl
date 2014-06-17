<#--zhaoshoulai@cattsoft.com-->
<#include "/templates/head.ftl">
<#assign path=request.contextPath>
<#--LEGEND-->
<#macro legend img imageStyle title>
	<legend align="left">
		<#if img??>
			<#if img?length gt 0>
			<img src="${path}/images/${img}" style="${imageStyle!}" />
			</#if>
		</#if>
		<#if title??>
			<strong>${title!}</strong>
		</#if>
	</legend>
</#macro>

<#--VIEW--List<T>-->
<#--说明：-->
<#--目前仅支持一个或者0个columnHead-->
<#--columnHead中方法只支持一个参数-->
<#macro showList coltitles resultList oprateLinks columnHead>
    <#-- 标题table的头 -->
    <#if coltitles?exists>
      <tr >
	     <#list coltitles as itemTitle >
	        <th align="center" >${(itemTitle.title)!}</th>
	     </#list>
	     <#if columnHead??>
	     	<#if oprateLinks?length gt 0>
	     		<th align="center" >${columnHead!}</th>
	     	</#if>
	     </#if>
	   </tr>
    </#if>
     <#-- 数据展示  -->
     <#if resultList??>
	 <@s.iterator value="${resultList}" status="objStatu">
      <tr>
         <#list coltitles as itemTitle >
     		<td  nowrap="nowrap" align="left" style="${itemTitle.css!}">
     			<#-- 引入类型 start-->
		        <#if itemTitle.type?exists>
		        	<#-- 标签引入 domainStatus-->
			        <#if itemTitle.type =="domainStatus">
			           <@domainStatus  name=itemTitle.propertyName! tabName=itemTitle.tabName! colName=itemTitle.colName!/>
			        </#if>
			        <#-- 标签引入 dataCacheStatus-->
			        <#if itemTitle.type =="dataCacheStatus">
			        	<@dataCacheStatus  name="${itemTitle.name!}" cacheName="${itemTitle.cacheName}"/> 
			        </#if>
			       	<#-- 日期格式-->
			        <#if itemTitle.type =="date">
			        	<@s.date name="${itemTitle.name!}" format="${itemTitle.format!}" />
			        </#if>
			    <#-- 引入类型 end-->
			    <#else>
			    <#--无类型 直接输出-->
			    	<@s.property value="${itemTitle.name!}"/>
		        </#if>
     		</td>
	     </#list>
	     <#if oprateLinks??>
	     	<#if oprateLinks?size gt 0>
			     <td width="100">
				     <#list oprateLinks as oprateLink>
					  	 	<a href="javascript:${oprateLink.methodname!}('${objStatu.index}');">${oprateLink.title!}</a>
					 </#list>
			     </td>
			</#if>
		 </#if>
	  </tr>
	  </@s.iterator>
   </#if>
</#macro>

<#--EDIT--ADD--LIST<T>-->
<#macro editList coltitles resultList oprateLinks columnHead >
     <#-- 标题table的头 -->
	    <#if coltitles?exists>
	      <tr>
		     <#list coltitles as itemTitle >
	     		<#if itemTitle.type != "hidden">
	        		<th>${(itemTitle.title)!}</th>
	        	</#if>
		     </#list>
		     <#if columnHead??><th>${columnHead}</th></#if>
		   </tr>
	    </#if>
     <#-- 数据展示  -->
     <#if resultList??>
    	 <@s.iterator value="${resultList}" status="objStatu">
          <tr id="tr${objStatu.index}">
             <#list coltitles as itemTitle >
             <#if itemTitle.type != "hidden">	
         		<td nowrap="nowrap" align="left" style="${itemTitle.css!}">
		        	<#-- 标签引入 domainStatus-->
			        <#if itemTitle.type =="selectDomain">
			        	<@domainOption name="params[${objStatu.index}].${itemTitle.name!}" tabName=itemTitle.tabName! colName=itemTitle.colName! />
                        <#if (element.required)??>
                          <span class="required" />
                        </#if>
			        </#if>
			       	<#-- 日期格式-->
			        <#if itemTitle.type =="date">
			        	<@s.date name="params[${objStatu.index}].${itemTitle.name!}" format="${itemTitle.format!}" id="${itemTitle.id!}" />
			        </#if>
			        
			    	<#if itemTitle.type =="text">
				    	<#--无类型 按照文本框输出-->
                        <@s.textfield id="${itemTitle.id!}"  name="params[${objStatu.index}].${itemTitle.name!}" cssStyle=itemTitle.cssStyle! disabled=itemTitle.disabled! readonly=itemTitle.readonly!/>
                        <#if (itemTitle.img)??>
                          ${element.img}
                        </#if>
                        <#if (itemTitle.required)??>
                              <span class="required" ></span>
                        </#if>
                        <#if (itemTitle.oneTR)??>
                            <#assign y=1>
                        </#if>
                    </#if>
         		</td>
         		<#else>	          
                    <@s.hidden  name="params[${objStatu.index}].${itemTitle.name!}" />
                </#if>
		     </#list>
		     <#if oprateLinks??>
		     <td width="100">
			     <#list oprateLinks as oprateLink>
				  	 	<a href="javascript:${oprateLink.methodname!}('${objStatu.index}');">${oprateLink.title!}</a>
				 </#list>
		     </td>
			 </#if>
		  </tr> 
		  </@s.iterator>
       </#if>
</#macro>


<#--JobparamList专用 type:view,text,hidden-->
<#macro jobparamList coltitles resultList namePrefix >
      <tr>
	     <#list coltitles as itemTitle >
     		<#if itemTitle.type != "hidden"><th style="${itemTitle.css!}">${(itemTitle.title)!}</th></#if>
	     </#list>
	  </tr>
     <#-- 数据展示  -->
     <#if resultList??>
    	 <@s.iterator value="${resultList}" status="objStatu">
          <tr>
             <#list coltitles as itemTitle >
             <#if itemTitle.type != "hidden">
         		<td nowrap="nowrap" align="left">
			    	<#if itemTitle.type =="text">
                        <@s.textfield id="${itemTitle.id!}"  name="${namePrefix}[${objStatu.index}].${itemTitle.name!}" cssStyle=itemTitle.cssStyle!/>
                        <#if (itemTitle.required)??>
                              <span class="required" ></span>
                        </#if>
                    <#elseif itemTitle.type =="view">
                    	<@s.property value="${itemTitle.name!}"/>
                    </#if>
         		</td>
         		<#else>	          
                    <input type="hidden"  name="${namePrefix}[${objStatu.index}].${itemTitle.name!}" value="<@s.property value='${itemTitle.name!}'/>" />
                </#if>
		     </#list>
		  </tr>
		  </@s.iterator>
       </#if>
</#macro>



