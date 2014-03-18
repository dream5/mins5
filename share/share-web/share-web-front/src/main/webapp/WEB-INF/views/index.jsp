<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page session="false"%>
<%@ include file="/WEB-INF/views/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>心灵鸡汤</title>
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
       <%--内容加载于异步获取 --%>
      </div>
          <div id="Pagination" class="mypage">
          </div>
      </div>
      <div class="sidebar">
      <c:if test="not empty recommendArticlesList">
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
        <c:if test="not empty randomReadList">
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
        <c:if test="not empty hotLabelList">
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
<script type="text/javascript" src="${context}/js/index.js"></script>
<script type="text/javascript" src="${context}/js/jquery.pagination.js"></script>
        <script type="text/javascript">

   
            function pageSelectCallBack(page_index, jq){
            	var currentPage = page_index+1;
            	var pageSize = 15;
            	var timestamp = Date.parse(new Date());
            	$.ajax({
         	       type: "GET",
         	       contentType : 'application/json;charset=UTF-8',
         	       url: '/index/getArticlesByPageNo.mins',
         	       data: {t:timestamp,currentPage:currentPage,pageSize:pageSize},
         	       dataType: "json",
         	       success:function(result) {
         	    	  if(result == "error"){
       		    	   $(".mainbar").html("<div class=\"article\"><span>对不起，没有查询出数据！<span></div>");
       		    	   $(".mypage").hide();
       		       }else{
       		    	   var content = "";
       		    		content +="<li><a href='http://www.mins5.com'>首页</a></li>";
       		    	   $.each(result,function(index,item){
       		    		   content +="<div class=\"article\">";
       		    		   content +="<h2>"+item.articleTitle+"</h2>";
       		    		   content +="<div class=\"cline\"></div>";
       		    		   content +="<p><span>时间:"+item.createTime+"</span>&nbsp;&nbsp;<span>来源:"+item.articleFrom+"</span>&nbsp;&nbsp;<span>作者:"+item.articleAuthor+"</span>&nbsp;&nbsp;浏览（18）</p>";
       		    		   content +="<img src=\"images/img_1.jpg\" width=\"613\" height=\"193\" alt=\""+item.articleTitle+"\" />";
       		    		   content +="<div class=\"cline\"></div>";
       		    		   content +=item.articleContent;
       		    		   content +=" <p class=\"links\"><a href=\"#\" target=\"_blank\">继续阅读</a></p>";
       		    		   content +="<div class=\"cline\"></div>";
       		    		   content +=baiduShare();
       		    			content +="</div>";
       		    	   });
       		    	   $(".menu_nav").html(content);
       		       } 
         	    	   
         	    	   
         	       },
         	       error:function(data){
         	    	  $(".mainbar").html("<div class=\"article\"><span>对不起，没有查询出数据！<span></div>");
         	    	  $(".mypage").hide();
         	    	  
         	       }
         	});
                
                return false;
            }


            // When document has loaded, initialize pagination and form
            $(document).ready(function(){
            	
            	
              
                $("#Pagination").pagination('${totalArticleCount}', {
  							 num_edge_entries: 2,//两侧显示的首尾分页的条目数
   							 num_display_entries: 10,//连续分页主体部分显示的分页条目数
    						 callback: pageSelectCallBack,//回调函数
    						 items_per_page:15,//每页显示的条目数
					});

            });
            
            
            function baiduShare(url,content){
            	var baidu="<div class=\"bdsharebuttonbox\" style=\"float:right;\">";
            	    baidu +="<a href=\""+url+"\" class=\"bds_more\" data-cmd=\"more\"></a>";
            	    baidu +="<a title=\"分享到QQ空间\" href=\""+url+"\" class=\"bds_qzone\" data-cmd=\"qzone\"></a>";
            	    baidu +="<a title=\"分享到新浪微博\" href=\""+url+"\" class=\"bds_tsina\" data-cmd=\"tsina\"></a>";
            	    baidu +="<a title=\"分享到腾讯微博\" href=\""+url+"\" class=\"bds_tqq\" data-cmd=\"tqq\"></a>";
            	    baidu +="<a title=\"分享到人人网\" href=\""+url+"\" class=\"bds_renren\" data-cmd=\"renren\"></a>";
            	    baidu +="<a title=\"分享到微信\" href=\""+url+"\" class=\"bds_weixin\" data-cmd=\"weixin\"></a>";
            	    baidu +="</div>";
            	  return baidu;  
 				
            }
            

        </script>
<script>window._bd_share_config={"common":{"bdSnsKey":{},"bdText":"","bdMini":"2","bdPic":"","bdStyle":"0","bdSize":"16"},"share":{}};with(document)0[(getElementsByTagName('head')[0]||body).appendChild(createElement('script')).src='http://bdimg.share.baidu.com/static/api/js/share.js?v=89860593.js?cdnversion='+~(-new Date()/36e5)];</script>
</body>
</html>
