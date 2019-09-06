package com.wdx.spile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TestURLAndIO {
	public static void main(String[] args) throws Exception {
		captureHtml();
	}
	
	public static void captureHtml() throws Exception {
		String strURL = "https://blog.csdn.net/zgyulongfei/article/details/7909006";
		URL url = new URL(strURL);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		InputStreamReader input = new InputStreamReader(httpConn
				.getInputStream(), "utf-8");
		BufferedReader bufReader = new BufferedReader(input);
		String line = "";
		StringBuilder contentBuf = new StringBuilder();
		while ((line = bufReader.readLine()) != null) {
			contentBuf.append(line);
		}
		String buf = contentBuf.toString();
		int beginIx = buf.indexOf("查询结果[");
		int endIx = buf.indexOf("上面四项依次显示的是");
		String result = buf.substring(beginIx, endIx);
		System.out.println("captureHtml()的结果：\n" + result);
	}
	
	public void testUrlOrIO(){
		try {
			URL pageUrl = new URL("https://blog.csdn.net/coqcnbkggnscf062/article/details/79412732");
			InputStream inputStream = pageUrl.openStream();
			byte[] b = new byte[1024];
			String str = "";
			while((inputStream.read(b))>0){
				str+=new String(b);
			}
			System.out.println(str);
		} catch (MalformedURLException e) {
			System.out.println("连接网页异常！");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("IO流异常！");
			e.printStackTrace();
		}
	}
}
