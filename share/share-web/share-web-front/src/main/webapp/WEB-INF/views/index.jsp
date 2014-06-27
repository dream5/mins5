<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="Window-Target" content="_top"/>
<meta name="robots" content="all"/>
<meta name="author" content="mins five"/>
<meta name="copyright" content="mins five"/>
<meta name="Description" content="全面关注国内外互联网的最前沿资讯，分享互联网创业、移动互联网、互联网金融、微信营销、网站产品运营等海量资讯。" />
<meta name="Keywords" content="互联网，移动互联网，互联网金融，微信营销，手游资讯"/>
<title>关注国内外互联网的最前沿资讯—Mins Five</title>
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
							<a href="${contex}/${article.kindPinYin}/${article.articleId}.html" target="_blank"><c:out value="${fn:substring(article.articleTitle, 0,28)}..." /></a>
						</c:when>
						<c:otherwise>
							<a href="${contex}/${article.kindPinYin}/${article.articleId}.html" target="_blank"><c:out value="${article.articleTitle}" /></a>
						</c:otherwise>
					</c:choose>
         		</h2>
         		 <div class="cline"></div>
          		<p>
	          		<span>时间:&nbsp;<fmt:formatDate value="${article.createTime}" pattern="yyyy年MM月dd日" /></span>
	          		<span style="margin-left:25px;">来源:&nbsp;${article.articleFrom}</span>
	          		<span style="margin-left:25px;">作者:&nbsp;${article.articleAuthor}</span>&nbsp;&nbsp;
          		</p>
          		<p>
          		<c:if test="${not empty article.articleUrl}">
          			 <img src="${article.articleUrl}" width="300px" height="180px" alt="${article.articleTitle}" />
          		</c:if>
		         <c:choose>
					<c:when test="${fn:length(article.articleContent) > 200}">
						${fn:substring(article.articleContent, 0,200)}...
					</c:when>
					<c:otherwise>
						${article.articleContent}"
					</c:otherwise>
				</c:choose>
				</p>
	        <%--   <p class="links"><a href="${contex}/${article.kindPinYin}/${article.articleId}.html" target="_blank">继续阅读</a></p> --%>
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
      
      <div class="rightbox">
      		 <h2><span>精彩图文</span></h2>
	      	<div class="special_text_img">
				<div class="text_img_left">
					<div class="text_img_leftImg">
						<a target="_blank" href="http://news.iresearch.cn/zt/233053.shtml">
							<img src="http://pic.iresearch.cn/news/201406/635392859620937500.jpg" width="72px" height="72px"/>
						</a>
					</div>
				</div>
				<div class="text_img_right">
					<h4>
						<a target="_blank" href="http://news.iresearch.cn/zt/233053.shtml">匿名社交来袭 谁的狂欢</a>
					</h4>
					<p>当人人被逐渐遗忘，当QQ上全是同事领导的消息，当你爸妈也开始加你微信好友的时候...</p>
				</div>
			</div>
			<div class="special_text_img">
				<div class="text_img_left">
					<div class="text_img_leftImg">
						<a target="_blank" href="http://news.iresearch.cn/zt/233053.shtml">
							<img src="http://pic.iresearch.cn/news/201406/635382693172656250.jpg" width="72px" height="72px"/>
						</a>
					</div>
				</div>
				<div class="text_img_right">
					<h4>
						<a target="_blank" href="http://news.iresearch.cn/zt/233053.shtml">匿名社交来袭 谁的狂欢</a>
					</h4>
					<p>当人人被逐渐遗忘，当QQ上全是同事领导的消息，当你爸妈也开始加你微信好友的时候...</p>
				</div>
			</div>
			<div class="special_text_img">
				<div class="text_img_left">
					<div class="text_img_leftImg">
						<a target="_blank" href="http://news.iresearch.cn/zt/233053.shtml">
							<img src="http://pic.iresearch.cn/news/201406/635386209480156250.jpg" width="72px" height="72px"/>
						</a>
					</div>
				</div>
				<div class="text_img_right">
					<h4>
						<a target="_blank" href="http://news.iresearch.cn/zt/233053.shtml">匿名社交来袭 谁的狂欢</a>
					</h4>
					<p>当人人被逐渐遗忘，当QQ上全是同事领导的消息，当你爸妈也开始加你微信好友的时候...</p>
				</div>
			</div>
      </div>
      
      
      
      
      
      
      <c:if test="${not empty  recommendArticlesList}">
        <div class="rightbox">
          <img class="paperclip" alt="paperclip" src="${context}/images/paperclip.png"/>
          <h2><span>热门推荐</span></h2>
          <div class="cline"></div>
          <ul class="sb_menu">
            <c:forEach var="recommendArticle" items="${recommendArticlesList }">
            	 <c:choose>
					<c:when test="${fn:length(recommendArticle.articleTitle) > 16}">
						<c:set var="recommendtitle" value="${fn:substring(recommendArticle.articleTitle, 0,16)}..."></c:set>	
					</c:when>
					<c:otherwise>
							<c:set var="recommendtitle" value="${recommendArticle.articleTitle}"></c:set>	
					</c:otherwise>
				</c:choose>
            	 <li>
	            	 <a href="${context}/${recommendArticle.kindPinYin}/${recommendArticle.articleId}.html" target="_blank" title="${recommendArticle.articleTitle}">${recommendtitle}</a>
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
					<c:when test="${fn:length(randomArticle.articleTitle) > 16}">
						<c:set var="randomtitle" value="${fn:substring(randomArticle.articleTitle, 0,16)}..."></c:set>	
					</c:when>
					<c:otherwise>
							<c:set var="randomtitle" value="${randomArticle.articleTitle}"></c:set>	
					</c:otherwise>
				</c:choose>
            	 <li><a href="${context}/${randomArticle.kindPinYin}/${randomArticle.articleId}.html" target="_blank" title="${randomArticle.articleTitle}">${randomtitle}</a></li>
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
			placeholder : "${context}/images/loading.gif",
			effect      : "fadeIn"
		});
		
	});
	
</script>
</body>
</html>
