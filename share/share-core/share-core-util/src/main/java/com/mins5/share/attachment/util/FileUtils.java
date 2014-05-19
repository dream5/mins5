package com.mins5.share.attachment.util;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 文件工具类
 * 
 * @author zhanglin
 * @since 2014年5月19日
 */
public class FileUtils {

	private static final Log log = LogFactory.getLog(FileUtils.class);

	public static String UPLOADPATH = "";

	/**
	 * 得到文件上传路径
	 * 
	 * @author zhanglin
	 * @since 2014年5月19日
	 */
	public static String getUploadPath(HttpServletRequest request) {
		UPLOADPATH = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator;
		return UPLOADPATH;
	}

	/**
	 * 删除文件
	 * 
	 * @author zhanglin
	 * @since 2014年5月19日
	 */
	public static void removeFile(String fullPath) throws Exception {
		log.info("删除图片路径：" + fullPath);
		try {
			File file = new File(fullPath);
			if (file.isFile()) {
				file.delete();
			}
			log.info("删除图片OK！");
		} catch (Exception e) {
			throw e;
		}
	}
}
