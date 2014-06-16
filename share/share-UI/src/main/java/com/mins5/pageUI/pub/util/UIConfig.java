/*    */ package com.mins5.pageUI.pub.util;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Properties;
/*    */ 
/*    */ public class UIConfig
/*    */ {
/* 14 */   private static Properties prop = null;
/*    */ 
/* 16 */   private static Properties getConfig() { if (prop == null) {
/* 17 */       prop = new Properties();
/*    */       try {
/* 19 */         prop.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("ui-config.properties"));
/*    */       }
/*    */       catch (IOException e) {
/* 22 */         e.printStackTrace();
/*    */       }
/*    */     }
/* 25 */     return prop; }
/*    */ 
/*    */   public static String getVersionType()
/*    */   {
/* 29 */     return getConfig().getProperty("version_type");
/*    */   }
/*    */ 
/*    */   public static String getRootPath() {
/* 33 */     return getConfig().getProperty("root_path");
/*    */   }
/*    */ 
/*    */   public static String getThemeStyle() {
/* 37 */     return getConfig().getProperty("theme");
/*    */   }
/*    */ 
/*    */   public static String getGridRowNum() {
/* 41 */     return getConfig().getProperty("grid.rownum");
/*    */   }
/*    */ 
/*    */   public static Properties getAll() {
/* 45 */     return getConfig();
/*    */   }
/*    */ 
/*    */   public static void main(String[] args)
/*    */   {
/* 52 */     System.out.println(getThemeStyle());
/* 53 */     System.out.println(getGridRowNum());
/*    */   }
/*    */ }

/* Location:           E:\server\apache-tomcat-6.0.29_end\IDP_2\WEB-INF\lib\DAPUI-1.2.0.jar
 * Qualified Name:     com.cattsoft.dapui.pub.util.UIConfig
 * JD-Core Version:    0.6.2
 */