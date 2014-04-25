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
  
  <div id="banners" class="ui-banner">
	<ul class="ui-banner-slides">
		<li><a href="#"><img src="/images/HeartHealthOmega.jpg" alt="NEW! Get Heart Smart With Essential Omega III" title="NEW! Get Heart Smart With Essential Omega III"/></a></li>
		<li><a href="#"><img src="/images/CellLabs.jpg" alt="NEW! Support Your Skin on a Cellular Level" title="NEW! Support Your Skin on a Cellular Level"/></a></li>
		<li><a href="#"><img src="/images/RoyalSpa.jpg" alt="NEW! Indulge in Luxury With Royal Spa" title="NEW! Indulge in Luxury With Royal Spa"/></a></li>
		<li><a href="#"><img src="/images/Cashback.jpg" alt="NEW! Get paid to shop with Cashback" title="NEW! Get paid to shop with Cashback"/></a></li>
		<li><a href="#"><img src="/images/StealthShield.jpg" alt="NEW! Radiation Protection at its Best" title="NEW! Radiation Protection at its Best"/></a></li>
		<li><a href="#"><img src="/images/IsotonixEducate.jpg" alt="Unleash the Power of Isotonix Today" title="Unleash the Power of Isotonix Today"/></a></li>
		<li><a href="#"><img src="/images/MotivesSimple.jpg" alt="Motives is Changing the Face of UK Beauty" title="Motives is Changing the Face of UK Beauty"/></a></li>
		<li><a href="#"><img src="/images/UltimateAloeKwStb.jpg" alt="Get a Taste of Spring" title="Get a Taste of Spring"/></a></li>
		<li><a href="#"><img src="/images/OPC3AllBetter.jpg" alt="Try Isotonix OPC-3 Today for Better Health" title="Try Isotonix OPC-3 Today for Better Health"/></a></li>
		<li><a href="#"><img src="/images/RoyalSpaRoyalHair.jpg" alt="Feel like a princess with Royal Spa" title="Feel like a princess with Royal Spa"/></a></li>
		<li><a href="#"><img src="/images/JubileeRoyalPartyAcai.jpg" alt="Stay energised with Isotonix for the Queen's Jubilee" title="Stay energised with Isotonix for the Queen's Jubilee"/></a></li>
		<li><a href="#"><img src="/images/WorldStores.gif" alt="WorldStores - Top Brands Delivered Next Day" title="WorldStores - Top Brands Delivered Next Day"/></a></li>
	</ul><!--ui-banner-slides end-->
	<ul class="ui-banner-slogans">
		<li>新！让心智能必要的欧米茄三</li>
		<li>新！支持你的皮肤细胞水平上</li>
		<li>新！御温泉尽情享受豪华</li>
		<li>新！得到报酬购物现金回赠</li>
		<li>新！在其最好的辐射防护</li>
		<li>今天释放等渗电力</li>
		<li>动机的改变，面对英国美容</li>
		<li>得到了春天的味道</li>
		<li>尝试等渗OPC-3今天更健康</li>
		<li>与皇家温泉公主的感觉</li>
		<li>保持精力充沛与等渗女王的银禧</li>
		<li>WorldStores - 顶级品牌提供翌日</li>
	</ul><!--ui-banner-slogans end-->
</div>
  
  
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
	          		<span>时间:&nbsp;${article.createTime}</span>
	          		<span style="margin-left:25px;">来源:&nbsp;${article.articleFrom}</span>
	          		<span style="margin-left:25px;">作者:&nbsp;${article.articleAuthor}</span>&nbsp;&nbsp;
	          		浏览（18）
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
			  </br>	
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
<script src="${context}/js/jquery-ui-1.8.6.core.widget.js"></script>
<script src="${context}/js/jqueryui.bannerize.js"></script>
<script type="text/javascript">
	$(function(){
		$('#banners').bannerize({
			shuffle: 1,
			interval: "5"
		});
	});
</script>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>
