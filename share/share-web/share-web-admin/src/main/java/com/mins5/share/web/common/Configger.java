package com.mins5.share.web.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 读取配置文件. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2011-10-10 下午12:31:14
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: jixs@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public class Configger {
	
	public static final String PROPERTIES_NAME = "/conf.properties";
	private static InputStream in  = null ;
	private static Properties properties = null;
	
	static {
		try {
			properties = new Properties();
			in = Configger.class.getResourceAsStream(PROPERTIES_NAME); 
	        properties.load(in);
        }
        catch (IOException e) {
	        e.printStackTrace();
        } finally {
        	try {
        		in.close() ;
        	} catch(Exception e) {
        	}
        }
	}
	private Configger() {
	}


	public static String getString(String key) {
		return properties.getProperty(key);
	}

	public static String getString(String key, String def) {
		String value = getString(key);
		return value != null ? value : def;
	}

	public static void main(String[] args) {
		System.out.println(getString("main.template.name", "asd"));
	}

	public static String readFile(String file) {
		return readFile(new File(file));
	}

	public static String readFile(File file) {
		try {
			return readFile(new FileReader(file));
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String readFile(FileReader fileReader) {
		String result = "";
		BufferedReader br = null;
		try {
			br = new BufferedReader(fileReader);
			StringBuffer sb = new StringBuffer("");
			String line = null;
			while ((line = br.readLine()) != null) {
				sb.append(line).append("\n");
			}

			result = sb.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return result;
	}

}
