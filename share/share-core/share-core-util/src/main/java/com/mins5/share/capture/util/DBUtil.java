package com.mins5.share.capture.util;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class DBUtil {
	
	private final static Log log = LogFactory.getLog(DBUtil.class);
	
	private static String driverName = "";
	private static String dbURL="";
	private static String userName="";
	private static String userPwd="";
	private static Connection connection = null;
	
	public void init(){
		log.info("初始化数据库参数");
		try {
			ResourceBundle resource = ResourceBundle.getBundle("dbConfig");
			driverName = resource.getString("jdbc.driver");
			dbURL = resource.getString("jdbc.url");
			userName = resource.getString("jdbc.username");
			userPwd = resource.getString("jdbc.password");
		} catch (Exception e) {
			log.error("初始化数据库参数异常:["+e.toString()+"]");
		}
	}
	
	public Connection getConnection() throws Exception{
		Connection dbConn = null;  
		log.info("----------连接数据库开始----------");
        try {  
	        Class.forName(driverName);  
	        dbConn = DriverManager.getConnection(dbURL, userName, userPwd);  
	        log.info("----------连接数据库成功!!!----------");
        } catch (Exception e) {  
        	log.error("连接数据库失败:[ "+toString()+"]");
            throw e;
      
        }  
        return dbConn;
	}
	
	/**
	 * <p>将数据插入到数据库中</p>
	 * @param articles
	 * @param con
	 */
	public void batchInsertBean(List<Article> articles ,Connection con){
		log.info("批处理数据开始...");
		long startTime = System.currentTimeMillis();
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append("insert into article_copy ");
		sqlBuffer.append("(article_title, article_content, article_url, article_from, article_author,");
		sqlBuffer.append(" status, update_time, create_time, is_original, is_valid");
		sqlBuffer.append(" ) values ( ");
		sqlBuffer.append(" ?,?,?,?,?,?,?,?,?,? )");
		try {
			con.setAutoCommit(false);
			PreparedStatement statement = con.prepareStatement(sqlBuffer.toString());
			for (int i = 0,len = articles.size(); i < len; i++) {
				Article article = articles.get(i);
				if(article==null){
					continue;
				}
				statement.setString(1, article.getArticleTitle());
				statement.setString(2, article.getArticleContent());
				statement.setString(3, article.getArticleUrl());
				statement.setString(4, article.getArticleFrom());
				statement.setString(5, article.getArticleAuthor());
				statement.setString(6, article.getStatus());
				statement.setDate(7, new Date(article.getCreateTime().getTime()));
				statement.setDate(8, new Date(article.getCreateTime().getTime()));
				statement.setString(9, "1");
				statement.setString(10,"1");
				statement.addBatch();
			}
			int [] result = statement.executeBatch();
			con.commit();
			long endTime = System.currentTimeMillis();
			log.info("插入数据库结果,共["+result.length+"]条");
			log.info("批处理数据结束,共花费["+(endTime-startTime)+"ms ]");
		} catch (Exception e) {
			log.error("执行SQL语句异常：["+e.toString()+"]");
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				log.error("数据库链接关闭异常:["+e.toString()+"]");
			}
		}   
	}
	

}
