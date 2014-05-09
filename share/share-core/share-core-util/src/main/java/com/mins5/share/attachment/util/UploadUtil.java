package com.mins5.share.attachment.util;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>
 * 上传工具类
 * </p>
 * 
 * @author zhanglin
 * @since 20140430 17:15
 */
public class UploadUtil {

	private static final Log log = LogFactory.getLog(UploadUtil.class);

	/**
	 * 单独文件上传操作
	 * 
	 * @param request
	 * @param basePath 指定文件存放路径
	 * @param serverPath 指定服务器存放文件路径
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map singleFileUpload(HttpServletRequest request) {

		log.info("单独文件上传开始...");
		String fileType = request.getParameter("filetype");
		if (fileType == null || fileType.trim().length() == 0) {
			fileType = ".unknown";
		} else {
			fileType = fileType.toLowerCase();
		}
		String newFileName = String.valueOf(System.currentTimeMillis()) + fileType;
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(8192);
		// 获取保存文件的最终路径
		String saveFilePath = request.getSession().getServletContext().getRealPath("/") + "upload" + File.separator;
		factory.setRepository(new File(saveFilePath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		List uploadlist = null;
		try {
			uploadlist = upload.parseRequest(request);
		} catch (FileUploadException e) {
			log.error("上传文件异常：[" + e.toString() + "]");
			factory = null;
			upload = null;
			uploadlist = null;
			// 如果是上传出现异常，删除文件
			// FileUtil.del(saveFilePath);
			return null;
		}
		String oldFileName = "", actualFileSize = "";
		try {
			for (int i = 0; i < uploadlist.size(); i++) {
				FileItem item = (FileItem) uploadlist.get(i);
				if (!item.isFormField()) { // 处理文件上传域 忽略其他不是文件域的所有表单信息
					try {
						actualFileSize = String.valueOf(item.getSize()); // 获取文件大小
						item.write(new File(saveFilePath + newFileName));// 保存文件路径
					} catch (Exception e) {
						e.printStackTrace();
						throw e;
					}
					item.delete();
					break;
				}
				item.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
			actualFileSize = null;
		} finally {
			factory = null;
			upload = null;
			uploadlist = null;
		}

		String fullName = saveFilePath + newFileName;
		log.info("生成文件的完整路径：" + fullName);
		// 生成不同尺寸的图片
		ImageUtils.scale2(fullName, saveFilePath + "_730x260.png", 260, 730, true);// 测试OK
		ImageUtils.scale2(fullName, saveFilePath + "_613x193.png", 193, 613, true);
		ImageUtils.scale2(fullName, saveFilePath + "_212x212.png", 212, 212, true);

		/*
		 * String[] rtnVal = new String[5]; rtnVal[0] = oldFileName; 旧文件名
		 * rtnVal[1] = fileType; 带点的扩展名，如 .wmv rtnVal[2] = newFileName;
		 * 不带扩展名重命名的文件 rtnVal[3] = actualFileSize; 文件大小 rtnVal[4] =
		 * saveFilePath; 保存文件的绝对路径
		 */
		Map rtnMap = new HashMap();
		rtnMap.put("newName", newFileName);
		// rtnMap.put("oldName", oldFileName);
		return rtnMap;
	}

	public static void main(String[] args) {
		System.out.println();
	}

}
