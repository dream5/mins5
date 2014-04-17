<%--
	<p>分页标签</p>
	@author zhanglin
	@date 20140416
--%> 
<%@ tag pageEncoding="UTF-8"%>
<%@ tag import="com.mins5.share.common.service.ReturnPageData" %>
<%@ tag import="javax.servlet.jsp.PageContext"%>

<!-- 输入参数 -->
<%@ attribute name="currPage" type="java.lang.Integer" required="true" description="当前页号"%>
<%@ attribute name="pageSize" type="java.lang.Integer" required="true" description="每页显示的记录数"%>
<%@ attribute name="totalResults" type="java.lang.Integer" required="true" description="总记录数"%>
<%@ attribute name="link" type="java.lang.String" required="true" description="跳转的URL"%>
<!-- 输出参数 -->
<%@ attribute name="var" type="java.lang.String" required="true"
	description="结果返回分页字符串"%>
	
<%
	Integer currPage = (Integer) jspContext.getAttribute("currPage");
	Integer pageSize = (Integer) jspContext.getAttribute("pageSize");
	Integer totalResults = (Integer) jspContext.getAttribute("totalResults");
	String link = (String) jspContext.getAttribute("link");
	/* System.out.println("currPage="+currPage);
	System.out.println("pageSize="+pageSize);
	System.out.println("totalResults="+totalResults); */
	String strVar = (String) jspContext.getAttribute("var");
	
	ReturnPageData pageSupport = new ReturnPageData(currPage,pageSize);
	pageSupport.setTotalResults(totalResults);
	
	
	//定义分页输出字符串
	StringBuffer pageOut = new StringBuffer();
	pageOut.append("<p class=\"Paginator content\">");
	pageOut.append("<a href=\"").append(link+1+".html").append("\" title='首页'>|&laquo;</a>");
	//如果有上一页
	if (pageSupport.hasPreviousPage()){
			pageOut.append("<a class=\"Prev content\" href=\"").append(link+pageSupport.getPrev()+".html").append("\">&laquo;上一页</a>");
	}else{
			pageOut.append("<span class=\"Prev content\">&laquo;上一页</span>");
	}
	//添加本页前面的页
	for(Object o:pageSupport.getPrevPages()){   
	       pageOut.append("<a href=\"").append(link+o+".html").append("\">").append(o).append("</a>");   
	} 		
	    
	//当前页
	pageOut.append("<span class=\"thisPage content\">").append(pageSupport.getCurrentPage()).append("</span>");
		
	//添加当前页后面的页
	for(Object o:pageSupport.getNextPages()){   
		 	pageOut.append("<a href=\"").append(link+o+".html").append("\">").append(o).append("</a>");
	} 
	//如果有下一页
	if (pageSupport.hasNextPage()) {
	    	pageOut.append("<a class=\"Next content\" href=\"").append(link+pageSupport.getNext()+".html").append("\">下一页&raquo;</a>");
	} else{
	    	pageOut.append("<span class=\"Next content\">下一页&raquo;</span>");
	}
	
	pageOut.append("<a href=\"").append(link+pageSupport.getTotalPages()+".html").append("\" title='末页'>&raquo;|</a></p>");

	
	jspContext.setAttribute(strVar, pageOut.toString(), PageContext.REQUEST_SCOPE);



%>	