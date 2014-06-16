/*     */ package com.mins5.pageUI.pub.base;
/*     */ 
/*     */ public abstract class WebBaseTag extends BaseComTag
/*     */ {
/*     */   protected String width;
/*     */   protected String height;
/*     */ 
/*     */   protected String buildOption(String name, String value)
/*     */   {
/*  29 */     return name + ":'" + value + "',";
/*     */   }
/*     */ 
/*     */   protected String buildOption(String name, int value)
/*     */   {
/*  40 */     return name + ":" + value + ",";
/*     */   }
/*     */ 
/*     */   protected String buildOption(String name, boolean value)
/*     */   {
/*  51 */     return name + ":" + value + ",";
/*     */   }
/*     */ 
/*     */   protected String buildFuncOption(String name, String func) {
/*  55 */     return name + ":" + func + ",";
/*     */   }
/*     */ 
/*     */   protected String buildVarOption(String name, String var) {
/*  59 */     return name + ":" + var + ",";
/*     */   }
/*     */ 
/*     */   protected String genEventProxyFunc(String func)
/*     */   {
/*  70 */     if (func.indexOf("javascript:") == 0)
/*  71 */       return String.format("function() {%s}", new Object[] { func.substring("javascript:".length()) });
/*  72 */     if ((func.indexOf("(") >= 0) && (func.indexOf(")") >= 0))
/*     */     {
/*  74 */       String funcName = func.substring(0, func.indexOf("(")).trim();
/*  75 */       String arg = func.substring(func.indexOf("(") + 1, func.indexOf(")")).trim();
/*  76 */       StringBuffer sb = new StringBuffer();
/*  77 */       sb.append("function(){");
/*  78 */       sb.append("\t\tvar newArgs = Array.prototype.slice.call(arguments, 0);");
/*  79 */       if (!"".equals(arg))
/*  80 */         sb.append("\t\tnewArgs.unshift(" + arg + ");");
/*  81 */       sb.append("\t\t" + funcName + ".apply(this, newArgs);");
/*  82 */       sb.append("}");
/*  83 */       return sb.toString();
/*     */     }
/*     */ 
/*  87 */     return func;
/*     */   }
/*     */ 
/*     */   public String getWidth()
/*     */   {
/*  92 */     return this.width;
/*     */   }
/*     */ 
/*     */   public void setWidth(String width) {
/*  96 */     this.width = width;
/*     */   }
/*     */ 
/*     */   public String getHeight() {
/* 100 */     return this.height;
/*     */   }
/*     */ 
/*     */   public void setHeight(String height) {
/* 104 */     this.height = height;
/*     */   }
/*     */ }

/* Location:           E:\server\apache-tomcat-6.0.29_end\IDP_2\WEB-INF\lib\DAPUI-1.2.0.jar
 * Qualified Name:     com.cattsoft.dapui.pub.base.WebBaseTag
 * JD-Core Version:    0.6.2
 */