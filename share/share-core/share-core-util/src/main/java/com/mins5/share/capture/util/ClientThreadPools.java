package com.mins5.share.capture.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * <p>客户端线程池</p>
 * @author zhanglin
 * 2014年4月12日 下午6:43:44
 */
public class ClientThreadPools {
	
	private final static int MAX_POOL_SIZE = 100;
	
	/**
	 * <p>创建客户端</p>
	 * @return 客户端线程
	 */
	public static  CloseableHttpClient getClientFromPools(){
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(MAX_POOL_SIZE);
        return HttpClients.custom().setConnectionManager(cm).build();
	}

}
