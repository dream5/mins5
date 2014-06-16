/*     */ package com.mins5.pageUI.tag.rich;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ import javax.servlet.ServletResponse;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.jsp.JspException;
/*     */ import javax.servlet.jsp.PageContext;
/*     */ import javax.servlet.jsp.tagext.TagSupport;
/*     */ 
/*     */ public class VRichEditorTag extends TagSupport
/*     */ {
/*     */   private String id;
/*     */   private String name;
/*     */   private String config;
/*     */   private String width;
/*     */   private String height;
/*  17 */   private static String defaultConfig = "{toolbar:[['Bold', 'Italic','Underline', '-','NumberedList','BulletedList','-','UIColor','JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','Table'],['Styles','Format','Font','FontSize','TextColor','BGColor']],extraPlugins : 'docprops'}";
/*     */   private static String tempConfig;
/*     */   private String textAreaValue;
/*     */   private static final long serialVersionUID = 1L;
/*     */ 
/*     */   public String getConfig()
/*     */   {
/*  21 */     return this.config;
/*     */   }
/*     */ 
/*     */   public void setConfig(String config) {
/*  25 */     this.config = config;
/*     */   }
/*     */ 
/*     */   public String getTextAreaValue() {
/*  29 */     return this.textAreaValue;
/*     */   }
/*     */ 
/*     */   public void setTextAreaValue(String textAreaValue) {
/*  33 */     this.textAreaValue = textAreaValue;
/*     */   }
/*     */ 
/*     */   public static long getSerialversionuid() {
/*  37 */     return 1L;
/*     */   }
/*     */ 
/*     */   public String getName()
/*     */   {
/*  43 */     return this.name;
/*     */   }
/*     */ 
/*     */   public void setName(String name) {
/*  47 */     this.name = name;
/*     */   }
/*     */ 
/*     */   public String getId()
/*     */   {
/*  52 */     return this.id;
/*     */   }
/*     */ 
/*     */   public void setId(String id)
/*     */   {
/*  57 */     this.id = id;
/*     */   }
/*     */ 
/*     */   public int doStartTag()
/*     */     throws JspException
/*     */   {
/*  67 */     HttpServletRequest re = (HttpServletRequest)this.pageContext.getRequest();
/*  68 */     String path = re.getContextPath();
/*  69 */     this.textAreaValue = re.getParameter(this.name);
/*  70 */     ServletResponse resp = this.pageContext.getResponse();
/*     */     try
/*     */     {
/*  73 */       StringBuilder sb = new StringBuilder();
/*     */ 
/*  75 */       if ((this.config == null) || ("".equals(this.config))) {
/*  76 */         tempConfig = defaultConfig;
/*     */       }
/*     */ 
/*  79 */       if ((this.width != null) && (!"".equals(this.width)) && (this.height != null) && (!"".equals(this.height)))
/*     */       {
/*  81 */         tempConfig = defaultConfig.substring(0, defaultConfig.length() - 1) + ",width:'" + this.width + "',height:'" + this.height + "'}";
/*     */       }
/*     */ 
/*  87 */       sb.append("<script>");
/*  88 */       sb.append(" $(function(){");
/*  89 */       sb.append(" CKEDITOR.replace( '" + this.id + "'," + tempConfig + ");");
/*  90 */       sb.append(" });");
/*  91 */       sb.append(" </script>");
/*     */ 
/*  93 */       sb.append("<textarea ");
/*  94 */       if ((this.id != null) && (!"".equals(this.id))) {
/*  95 */         sb.append("id=\"" + this.id + "\"");
/*     */       }
/*  97 */       if ((this.name != null) && (!"".equals(this.name))) {
/*  98 */         sb.append("name=\"" + this.name + "\">");
/*     */       }
/* 100 */       if ((this.textAreaValue != null) && (!"".equals(this.textAreaValue))) {
/* 101 */         sb.append(this.textAreaValue);
/*     */       }
/*     */ 
/* 104 */       sb.append("</textarea>");
/* 105 */       System.out.println(sb.toString());
/*     */ 
/* 107 */       PrintWriter pw = resp.getWriter();
/* 108 */       pw.print(sb.toString());
/* 109 */       pw.flush();
/*     */     }
/*     */     catch (IOException e) {
/* 112 */       e.printStackTrace();
/*     */     }
/* 114 */     return super.doStartTag();
/*     */   }
/*     */ 
/*     */   public String getWidth() {
/* 118 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/* 122 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public String getHeight() {
/* 126 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void setHeight(String height) {
/* 130 */     this.height = height;
/*     */   }
/*     */ 
/*     */   public int doEndTag()
/*     */     throws JspException
/*     */   {
/* 136 */     return super.doEndTag();
/*     */   }
/*     */ }

/* Location:           E:\server\apache-tomcat-6.0.29_end\IDP_2\WEB-INF\lib\DAPUI-1.2.0.jar
 * Qualified Name:     com.cattsoft.dapui.tag.rich.VRichEditorTag
 * JD-Core Version:    0.6.2
 */