package com.mins5.share.web.controller.images;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mins5.share.attachment.util.UploadUtil;
import com.mins5.share.util.JsonUtils;

/**
 * <p>图片控制器</p>
 * @author zhanglin
 * 2014年5月2日 下午7:21:59
 */
@Controller
@Scope("prototype")
@RequestMapping(value = "/upload")
public class ImagesController {

	private static final Log log = LogFactory.getLog(ImagesController.class);
	
	@RequestMapping(value = "/addImagesInit")
	public String addInit(){
		return "images/uploadImages";
	}
	
	@RequestMapping(value = "/beginUpload")
	public void BeginUpload(HttpServletRequest request,HttpServletResponse response){
		Map alist=UploadUtil.singleFileUpload(request);
		JsonUtils.write(alist, response);
	}
	
}
