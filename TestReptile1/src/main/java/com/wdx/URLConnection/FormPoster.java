package com.wdx.URLConnection;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class FormPoster {
	
	private URL url;
	private QueryString query = new QueryString();
	
	public FormPoster(URL url){
		if (!url.getProtocol().toLowerCase().startsWith("http")) {
			throw new IllegalArgumentException(
					"Posting only works for http URLs");
		}
		this.url = url;
	}
	
	public void add(String name, String value){
		query.add(name, value);
	}
	
	public URL getURL() {
		return this.url;
	}
	
	public InputStream post() throws IOException{
		//打开连接，准备POST
		URLConnection uc = url.openConnection();
		
		uc.setDoOutput(true);
		try(OutputStreamWriter out = 
				new OutputStreamWriter(uc.getOutputStream(), "UTF-8")){
			//POST行、Content-type首部和Content-length首部
			//由URLConnection发送
			//我们只需要发送数据
			out.write(query.toString());
			out.write("\r\n");
			out.flush();
		}
		return uc.getInputStream();
	}
	
	public static void main(String[] args) {
		URL url = null;
		try {
			url = new URL("http://www.cafeaulait.org/books/jnp4/postquery.phtml");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		FormPoster poster = new FormPoster(url);
		poster.add("name", "");
		poster.add("email", "");
		try(InputStream in = poster.post()) {
			//读取响应
			Reader r = new InputStreamReader(in);
			int c;
			while((c = r.read()) != -1){
				System.out.println((char)c);
			}
			System.out.println();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
