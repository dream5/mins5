<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="index.html"><span> Mins  </span> five </a><small></small></h1>
      </div>
       <div class="search">
        <form id="form" name="form" method="post" action="${context}/s/s.mins">
          <span>
          <input name="k" type="text" class="keywords" id="sContent" maxlength="50" value="搜索你感兴趣的文章..." />
          <input name="searchbtn" type="image" src="${context}/images/search_btn.gif" class="sbtn" />
          </span>
        </form>
      </div>
      <div class="cline"></div>
      <div class="menu_nav">
      </div>
      <div class="cline"></div>
	  <!--面包屑-->
		<div class="page-position">
			当前位置：<a target="_parent" href="#">5分钟</a>&nbsp;&gt;&nbsp;<a target="_parent" href="#">心灵鸡汤</a>&nbsp;&gt;&nbsp;分类列表
		</div>
    </div>
 </div>