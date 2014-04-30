package com.mins5.share.attachment;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <p>上传工具类</p>
 * @author zhanglin
 * @since 20140430 17:15
 */
public class UploadUtil {
	
	private static final Log log  = LogFactory.getLog(UploadUtil.class);
	
	
	/**
	 * 单独文件上传操作
	 * @param request
	 * @param basePath 指定文件存放路径
	 * @param serverPath 指定服务器存放文件路径
	 * @return
	 */
	public static Map singleFileUpload(HttpServletRequest request) {
		
		log.info("单独文件上传开始...");
		long startTime = System.currentTimeMillis();
		String fileType = request.getParameter("filetype");
		String proVal = request.getParameter("proVal");
		if (fileType==null||fileType.trim().length()==0) {
			fileType = ".unknown";
		}else{
			fileType = fileType.toLowerCase();
		}
		String newFileName = String.valueOf(System.currentTimeMillis())+fileType;
		//System.out.println("[" + fileType + "]...... Upload time:" + today+ " （IP:" + request.getRemoteAddr().toString() + "）" + proVal);

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(8192);
		 //获取保存文件的最终路径
		String saveFilePath = null;
		if (saveFilePath == null) {
			return null;
		}
		factory.setRepository(new File(saveFilePath));
		ServletFileUpload upload = new ServletFileUpload(factory);
		List uploadlist = null;
		try {
			uploadlist = upload.parseRequest(request);
		} catch (FileUploadException e) {
			log.error("上传文件异常：["+e.toString()+"]");
			factory = null;
			upload = null;
			uploadlist = null;
			 //如果是上传出现异常，删除文件
			//FileUtil.del(saveFilePath);
			return null;
		}
		String oldFileName = "", actualFileSize = "";
		try {
			for (int i = 0; i < uploadlist.size(); i++) {
				FileItem item = (FileItem) uploadlist.get(i);
				if (!item.isFormField()) { //处理文件上传域 忽略其他不是文件域的所有表单信息
					try {
						long start = System.currentTimeMillis();
						actualFileSize = String.valueOf(item.getSize()); //获取文件大小
						String fullPath = item.getName(); //取到客户端完整 路径+文件名
						oldFileName = FilenameUtils.getName(fullPath);// 取到文件名
						item.write(new File(saveFilePath + newFileName));// 保存文件路径

						long end = System.currentTimeMillis();
						/*System.out.println("  save file："
								+ newFileName
								+ "(size："
								+ FileUtil.getAllFilesSizeWithUnit(String
										.valueOf(actualFileSize)) + " ，used："
								+ (end - start) + " ms)");*/

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

		/*
		 * String[] rtnVal = new String[5]; rtnVal[0] = oldFileName; 旧文件名
		 * rtnVal[1] = fileType; 带点的扩展名，如 .wmv rtnVal[2] = newFileName;
		 * 不带扩展名重命名的文件 rtnVal[3] = actualFileSize; 文件大小 rtnVal[4] =
		 * saveFilePath; 保存文件的绝对路径
		 */
		Map rtnMap = new HashMap();
		rtnMap.put("newName", newFileName);
		rtnMap.put("oldName", oldFileName);
		return rtnMap;
	}
	
	

}
