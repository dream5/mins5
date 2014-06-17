<#assign dtt=JspTaglibs["http://www.cattsoft.com/tags"]> <#--引用自定义标签 --> 
<#assign path=request.contextPath>
<#--树标签   宏-->
<#macro treeDivMacro treeObjName, rootElement treeClass onClickFuc>
	<DIV id="treeDiv" class="dtree" style="width:100%; min-height:500px;height:100%;overflow-y:scroll" 
	     			  onscroll="treeDiv.scrollTop = this.scrollTop;treeDiv.scrollLeft = this.scrollLeft;">
		  <@dtt.tree treeObjName="${treeObjName!}" name="${rootElement!}" classname="${treeClass!}" clickmethodname="${onClickFuc!}"/>
	</DIV>
</#macro>