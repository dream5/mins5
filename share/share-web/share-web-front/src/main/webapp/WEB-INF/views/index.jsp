<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>互联网资讯</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
<link href="${context}/favicon.ico" mce_href="${context}/favicon.ico" rel="bookmark" type="image/x-icon" /> 
<link href="${context}/favicon.ico" mce_href="${context}/favicon.ico" rel="icon" type="image/x-icon" /> 
<link href="${context}/favicon.ico" mce_href="${context}/favicon.ico" rel="shortcut icon" type="image/x-icon" /> 
 
</head>
<body>
<div class="login">
</div>
<div class="main">	
  <%--top --%> 
  <%@ include file="/WEB-INF/views/frame/top.jsp" %>
  <div class="cline"></div>
  
  <c:if test="${not empty bannerList}">
  <div id="banners" class="ui-banner">
	<ul class="ui-banner-slides">
	<c:forEach var="banner" items="${bannerList }">
		<li>
			<a href="${contex}/${banner.kindPinYin}/${banner.articleId}.html" target="_blank">
			   <c:if test="${not empty banner.large}">
				<img src="${context}/upload/${banner.large}" alt="${banner.articleTitle}" title="${banner.articleTitle}"/>
				</c:if>
			</a>
		</li>
	</c:forEach>
	</ul>
	<ul class="ui-banner-slogans">
		<c:forEach var="banner_title" items="${bannerList }">
		<li>
			<c:choose>
				<c:when test="${fn:length(banner_title.articleTitle) > 28}">
						<c:out value="${fn:substring(banner_title.articleTitle, 0,28)}..." />
				</c:when>
				<c:otherwise>
						<c:out value="${banner_title.articleTitle}" />
				</c:otherwise>
			</c:choose>
		</li>
		</c:forEach>
	</ul>
	</div>
	</c:if>
  
  <%--content --%>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
      	 <c:if test="${not empty articlesList}">
      	  <c:forEach var="article" items="${articlesList }">
      	 	<div class="article">
         		 <h2>
         		 	<c:choose>
						<c:when test="${fn:length(article.articleTitle) > 28}">
							<c:out value="${fn:substring(article.articleTitle, 0,28)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${article.articleTitle}" />
						</c:otherwise>
					</c:choose>
         		</h2>
         		 <div class="cline"></div>
          		<p>
	          		<span>时间:&nbsp;<fmt:formatDate value="${article.createTime}" pattern="yyyy年MM月dd日" /></span>
	          		<span style="margin-left:25px;">来源:&nbsp;${article.articleFrom}</span>
	          		<span style="margin-left:25px;">作者:&nbsp;${article.articleAuthor}</span>&nbsp;&nbsp;
          		</p>
          		<c:if test="${not empty article.articleTitle}">
          			 <img src="${article.articleUrl}" width="613px" height="193px" alt="${article.articleTitle}" />
          		</c:if>
         		<div class="cline"></div>
		         <c:choose>
					<c:when test="${fn:length(article.articleContent) > 200}">
						${fn:substring(article.articleContent, 0,200)}...
					</c:when>
					<c:otherwise>
						${article.articleContent}"
					</c:otherwise>
				</c:choose>
			  </br>	
	          <p class="links"><a href="${contex}/${article.kindPinYin}/${article.articleId}.html" target="_blank">继续阅读</a></p>
	        </div>
        	</c:forEach>
      	 </c:if>
      	 <c:if test="${ empty articlesList}">
      	 	<div class="article"><span>对不起，没有查询出数据！</span></div>
      	 </c:if>
      
       <%--分页标签开始 --%>
       <pageUtil:pageHandler currPage="${currentPage}" link="/" var="pageTag" pageSize="${pageSize}" totalResults="${totalArticleCount}"></pageUtil:pageHandler>
       ${pageTag}
       <%--分页标签结束 --%>
      </div>
      <div class="sidebar">
      <c:if test="${not empty  recommendArticlesList}">
        <div class="rightbox">
          <img class="paperclip" alt="paperclip" src="${context}/images/paperclip.png"/>
          <h2><span>热门推荐</span></h2>
          <div class="cline"></div>
          <ul class="sb_menu">
            <c:forEach var="recommendArticle" items="${recommendArticlesList }">
            	 <c:choose>
					<c:when test="${fn:length(recommendArticle.articleTitle) > 30}">
						<c:set var="recommendtitle" value="${fn:substring(recommendArticle.articleTitle, 0,30)}..."></c:set>	
					</c:when>
					<c:otherwise>
							<c:set var="recommendtitle" value="${recommendArticle.articleTitle}"></c:set>	
					</c:otherwise>
				</c:choose>
            	 <li>
	            	 <a href="${context}/${recommendArticle.kindPinYin}/${recommendArticle.articleId}.html" target="_blank" title="${recommendtitle}">${recommendtitle}</a>
            	 </li>
            </c:forEach>
          </ul>
        </div>
        </c:if>
        <c:if test="${not empty randomReadList}">
        <div class="rightbox">
           <img class="paperclip" alt="paperclip" src="${context}/images/paperclip.png"/>	
          <h2><span>随机阅读</span></h2>
          <div class="cline"></div>
          <ul class="ex_menu">
            <c:forEach var="randomArticle" items="${randomReadList }">
             <c:choose>
					<c:when test="${fn:length(randomArticle.articleTitle) > 30}">
						<c:set var="randomtitle" value="${fn:substring(randomArticle.articleTitle, 0,30)}..."></c:set>	
					</c:when>
					<c:otherwise>
							<c:set var="randomtitle" value="${randomArticle.articleTitle}"></c:set>	
					</c:otherwise>
				</c:choose>
            	 <li><a href="${context}/${randomArticle.kindPinYin}/${randomArticle.articleId}.html" target="_blank" title="${randomtitle}">${randomtitle}</a></li>
            </c:forEach>
          </ul>
        </div>
        </c:if>
        <%--广告层暂时保留 
        <div class="rightbox" style="background-image:url(${context}/images/baidu.jpg);height:250px;">
        </div>
        --%>
        <c:if test="${not empty hotLabelList}">
		<div class="rightbox">
		  	 <h2><span>热门标签</span></h2>
          	 <div class="cline"></div>
	          	<div class="taglist">
	          		<c:forEach var="label" items="${hotLabelList }">
            	 		<a href="${context}/label/${label.labelId}.html" target="_blank" title="${label.labelName}">${label.labelName}</a>
                    </c:forEach>
				</div>
	    </div>
	      </c:if>
      </div>
      <div class="cline"></div>
    </div>
  </div>
  	<%--footer --%> 
     <%@ include file="/WEB-INF/views/frame/footer.jsp" %>
     
</div>
<script type="text/javascript" src="${context}/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${context}/js/common.js"></script>
<script type="text/javascript" src="${context}/js/jquery.LoadImage.js"></script>
<script src="${context}/js/jquery-ui-1.8.6.core.widget.js"></script>
<script src="${context}/js/jqueryui.bannerize.js"></script>
<script type="text/javascript">
	$(function(){
		$('#banners').bannerize({
			shuffle: 1,
			interval: "5"
		});
		
		$(".main img").lazyload({
			effect      : "fadeIn"
		});
		
	});
	
</script>
</body>
</html>
