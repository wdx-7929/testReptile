package com.wdx.URLConnection;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.CacheResponse;
import java.net.URL;
import java.net.URLConnection;
import java.security.Permission;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OpenURLConnection {
	public static void main(String[] args) throws Exception {
		
		try {
			URL url = new URL("http://www.baidu.com");
			URLConnection urlConnection = url.openConnection();
//			Permission permission = urlConnection.getPermission();
//			System.out.println(permission.toString());
			System.out.println(urlConnection.getURL());
			urlConnection.setUseCaches(false);//禁用缓存，确保总会获取最新版本
			urlConnection.setDoOutput(true);//用于写入为true，否则为false
			Map<String, List<String>> requestProperties = urlConnection.getRequestProperties();
			Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
			urlConnection.setRequestProperty("Cookie", "1111");
			urlConnection.addRequestProperty("header", "2222");
			System.out.println("map长度:"+requestProperties.size());
			for (Entry<String, List<String>> string : requestProperties.entrySet()) {
				System.out.println(string.getKey());
				System.out.println(string.getValue().get(0));
			}
			OutputStream outputStream = urlConnection.getOutputStream();
			InputStream inputStream = urlConnection.getInputStream();
			inputStream.close();
			outputStream.close();
			
			//返回响应主体的MIME内容类型
			String type = urlConnection.getContentType();
			System.out.println(type);
			//返回你内容中有多少字节，如果没有Content-length首部就返回-1
			int contentLength = urlConnection.getContentLength();
			System.out.println(contentLength);
			//返回内容是如何编码的。如果发送的内容没有编码，返回值为NULL
			String contentEncoding = urlConnection.getContentEncoding();
			System.out.println(contentEncoding);
			//返回指定首部字段的值，首部名不区分大小写，也不包含结束冒号。
			String headerField = urlConnection.getHeaderField("Connection");
			System.out.println(headerField);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void testToFromString(){
		
		
		
		
		
	}
	
}
