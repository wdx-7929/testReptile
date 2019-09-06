package com.wdx.URLConnection;

import java.net.HttpURLConnection;
import java.net.URL;

public class OpenHttpConnection {
	public static void main(String[] args) throws Exception {
		
//		URL url = new URL("https://www.oreilly.com/");
		URL url = new URL("https://www.csdn.net/");
		HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		huc.setRequestMethod("GET");//设定连接类型
		System.out.println(huc.getResponseCode());//获取响应码
		System.out.println(huc.getResponseMessage());//获取响应码后面的文本字符串
		System.out.println(huc.usingProxy());//是否使用代理服务器 代理使用proxy类 ,Properties类
		System.out.println(huc.getHeaderField("Cookie"));
		for(int j = 1; ; j++){
			String header = huc.getHeaderField(j);
			String key = huc.getHeaderFieldKey(j);
			if (header == null || key == null) break;
			System.out.println(huc.getHeaderFieldKey(j)+" : "+header);
		}
		System.out.println();
		huc.disconnect();//关闭连接
		
	}
}
