package com.mins5.share.web.constant;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigurableConstants {
	private static final Logger logger = Logger.getLogger(ConfigurableConstants.class);

	protected static Properties p = new Properties();

	protected static void init(String propertyFileName) {
		InputStream in = null;
		try {
			in = ConfigurableConstants.class.getResourceAsStream(propertyFileName);
			if (in != null)
				p.load(in);
		} catch (IOException e) {
			logger.error("load " + propertyFileName + " into Contants error");
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error("载入系统配置文件时出错，文件路径：" + propertyFileName);
				}
			}
		}
	}

	protected static int getProperty(String key, int defaultValue) {
		int result = defaultValue;
		try {
			result = Integer.parseInt(p.getProperty(key, String.valueOf(defaultValue)));
		} catch (Exception e) {
			return defaultValue;
		}
		return result;
	}
}
