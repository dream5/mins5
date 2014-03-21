package com.mins5.share.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.ser.impl.SimpleFilterProvider;

/**
 * @author zhoutian
 * @since 2014-2-28
 */
public abstract class JsonUtils {

	private static ObjectMapper objectMapper = new ObjectMapper();
	
	static {
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        objectMapper.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
//        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,    false);
//        objectMapper.setFilters(new SimpleFilterProvider().setFailOnUnknownId(false));
	}

	public static String writeValue(Object obj) {
		if (obj == null) {
			return null;
		}
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (Exception ex) {

		}
		return null;
	}

	public static <E> E readValue(String json, Class<E> clazz) {
		if (json == null) {
			return null;
		}
		try {
			return objectMapper.readValue(json, clazz);
		} catch (Exception e) {

		}
		return null;
	}

	public static void write(String str, HttpServletResponse response) {
		PrintWriter out = null;
		try {
			response.setContentType("text/javascript;charset=UTF-8");
			out = response.getWriter();
			out.write(str);
			out.flush();
			out.close();
		} catch (IOException e) {

		} finally {
			if (out != null) {
				out.close();
			}
		}
	}

}
