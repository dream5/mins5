<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="header">
    <div class="header_resize">
      <div class="logo">
        <h1><a href="index.html"><span> Mins  </span> five </a><small></small></h1>
      </div>
       <div class="search">
        <form id="form" name="form" method="post" action="">
          <span>
	          <input name="q" type="text" onfocus="if(this.value=='搜索你感兴趣的文章...'){this.value='';}" onblur="if(this.value==''){this.value='搜索你感兴趣的文章...';}"  class="keywords" id="textfield" maxlength="50" value="搜索你感兴趣的文章..." />
	          <input name="b" type="image" src="${context}/images/search_btn.gif" class="button" />
          </span>
        </form>
      </div>
      <div class="cline"></div>
      <!-- navigate -->
      <div class="menu_nav">
      </div>
      <div class="cline"></div>
    </div>
  </div>