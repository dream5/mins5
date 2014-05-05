package com.mins5.share.capture.util;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
/**
 * <p> 抓取文章</p>
 * @author zhanglin
 * @date 20140412 17:44
 */
public class Capture {

	private final static Log log = LogFactory.getLog(Capture.class);

	public static void main(String[] args) throws Exception {
		Capture capture = new Capture();
		capture.beginCapture();
	}

	/**
	 * <p>
	 * 根据URL获取其HTML
	 * </p>
	 * 
	 * @param url
	 *            请求URL
	 */
	private void beginCapture() throws Exception {
		log.info("抓取开始...");
		long startTime = System.currentTimeMillis();
		CloseableHttpClient httpclient = ClientThreadPools.getClientFromPools();
		try {
			/*	List<String> url = FileUtil.getUrlFromConfigFile();
			if (url != null && url.size() > 0) {
				CaptureThreads[] threads = new CaptureThreads[url.size()];
				for (int i = 0, len = threads.length; i < len; i++) {
					HttpGet httpGet = new HttpGet(url.get(i));
					threads[i] = new CaptureThreads(httpclient, httpGet, i + 1);
				}
				for (int j = 0, len = threads.length; j < len; j++) {
					threads[j].start();
				}
				for (int j = 0, len = threads.length; j < len; j++) {
					threads[j].join();
				}
				log.info("总共抓取实例数量：[" + CaptureThreads.articles.size() + "]");
				DBUtil dbUtil = new DBUtil();
				dbUtil.init();
				dbUtil.batchInsertBean(CaptureThreads.articles,
						dbUtil.getConnection());
			}
*/
		} catch (Exception e) {
			log.error("根据URL获取其HTML异常：[" + e.toString() + "]");
		} finally {
			//if (httpclient != null) {
				// httpclient.close();
			//}
		}
		long endTime = System.currentTimeMillis();
		log.info("抓取保存结束共花费" + (endTime - startTime) + "ms");
	}

}
