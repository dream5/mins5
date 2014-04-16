<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>互联网资讯</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${context}/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="login">
</div>
<div class="main">	
  <%--top --%> 
  <%@ include file="/WEB-INF/views/frame/top.jsp" %>
  <div class="cline"></div>
  <%--content --%>
  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
      	 <c:if test="${not empty articlesList}">
      	  <c:forEach var="article" items="${articlesList }">
      	 	<div class="article">
         		 <h2>
         		 	<c:choose>
						<c:when test="${fn:length(article.articleTitle) > 20}">
							<c:out value="${fn:substring(article.articleTitle, 0,20)}..." />
						</c:when>
						<c:otherwise>
							<c:out value="${article.articleTitle}" />
						</c:otherwise>
					</c:choose>
         		</h2>
         		 <div class="cline"></div>
          		<p>
	          		<span>时间:${article.createTime}</span>&nbsp;&nbsp;<span>来源:${article.articleFrom}</span>
	          		&nbsp;&nbsp;<span>作者:${article.articleAuthor}</span>&nbsp;&nbsp;浏览（18）
          		</p>
          		<c:if test="${not empty article.articleTitle}">
          			 <img src="${article.articleUrl}" width="613" height="193" alt="${article.articleTitle}" />
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
	          <p class="links"><a href="${contex}/${article.kindPinYin}/${article.articleId}.html" target="_blank">继续阅读</a></p>
			  <!-- baidu share begin-->
				  <div class="bdsharebuttonbox" style="float:right;">
					<a href="#" class="bds_more" data-cmd="more"></a>
					<a title="分享到QQ空间" href="#" class="bds_qzone" data-cmd="qzone"></a>
					<a title="分享到新浪微博" href="#" class="bds_tsina" data-cmd="tsina"></a>
					<a title="分享到腾讯微博" href="#" class="bds_tqq" data-cmd="tqq"></a>
					<a title="分享到人人网" href="#" class="bds_renren" data-cmd="renren"></a>
					<a title="分享到微信" href="#" class="bds_weixin" data-cmd="weixin"></a>
				 </div>
			 <!-- baidu share end-->
	        </div>
        	</c:forEach>
      	 </c:if>
      	 <c:if test="${ empty articlesList}">
      	 	<div class="article"><span>对不起，没有查询出数据！<span></div>
      	 </c:if>
      
       <%--分页标签开始 --%>
       <pageUtil:pageHandler currPage="${currentPage}" var="pageTag" pageSize="${pageSize}" totalResults="${totalArticleCount}"></pageUtil:pageHandler>
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
            	 <li><a href="#" title="${recommendArticle.articleTitle}">${recommendArticle.articleTitle}</a></li>
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
            	 <li><a href="#" title="${randomArticle.articleTitle}">${randomArticle.articleTitle}</a></li>
            </c:forEach>
          </ul>
        </div>
        </c:if>
        <div class="rightbox" style="background-image:url(${context}/images/baidu.jpg);height:250px;">
        </div>
        <c:if test="${not empty hotLabelList}">
		<div class="rightbox">
		  	 <h2><span>热门标签</span></h2>
          	 <div class="cline"></div>
	          	<div class="taglist">
	          		<c:forEach var="label" items="${hotLabelList }">
            	 		<a href="#" target="_blank" title="${label.labelName}">${label.labelName}</a>
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
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>
