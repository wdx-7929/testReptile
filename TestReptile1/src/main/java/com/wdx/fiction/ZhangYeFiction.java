package com.wdx.fiction;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ZhangYeFiction {
	public static void main(String[] args) throws IOException {
		getUrlToFile();
		
		
		
	}
	
	static void getUrl() throws IOException{
		Document document = Jsoup.connect("https://www.xiaoshuo2016.com/426492/124371197.html")
//		Document document = Jsoup.connect("https://www.xiaoshuo2016.com/426492/125782497.html")
				.get();
		byte[] b = new byte[1024];
		int len = 0;
//		PrintWriter pw = new PrintWriter(new FileWriter("fiction/1.txt"));
//		pw.write(document.html());
		System.out.println("2");
		Elements select = document.select("div[class=\"articleDiv\"]");
		Elements elements = document.select("div[class=\"pagination\"]")
									.select("a[href]");
		System.out.println(select.text());
		String html = select.html();
		String replaceAll = html.replaceAll("&nbsp;", "")
				.replaceAll("<br>", "\\\n")
				.replaceAll(" 《完本小说网》网址：<a href=\"http://www.txt2016.com\" target=\"_blank\">www.txt2016.com</a> 书友超喜欢的【全本】书籍站，手机可直接下载txt</p>", "")
				.replaceAll("<p>看完结好书上【完本神站】地址：<a href=\"http://www.wanbentxt.com\" target=\"_blank\">www.wanbentxt.com</a> 免去追书的痛！", "");
//		System.out.println(replaceAll);
		String attr = elements.get(2).attr("href");
		System.out.println(attr);
		System.out.println(attr.endsWith(".html"));
		
		
	}
	static void getUrlToFile() throws IOException{
		int i = 1;
		String attr = "426492/124371197.html";
		String url1 = "https://www.xiaoshuo2016.com/BH";
		do {
			String url = url1.replace("BH", attr);
			Document document = Jsoup.connect(url).get();
			byte[] b = new byte[1024];
			int len = 0;
			PrintWriter pw = new PrintWriter(new FileWriter("fiction/"+i+".txt"));
			Elements select = document.select("div[class=\"articleDiv\"]");
			Elements elements = document.select("div[class=\"pagination\"]")
										.select("a[href]");
			String html = select.html();
			String replaceAll = html.replaceAll("&nbsp;", "")
					.replaceAll("<br>", "\\\n")
					.replaceAll(" 《完本小说网》网址：<a href=\"http://www.txt2016.com\" target=\"_blank\">www.txt2016.com</a> 书友超喜欢的【全本】书籍站，手机可直接下载txt</p>", "")
					.replaceAll("<p>看完结好书上【完本神站】地址：<a href=\"http://www.wanbentxt.com\" target=\"_blank\">www.wanbentxt.com</a> 免去追书的痛！", "");
			
			pw.write(replaceAll);
			
			i++;
			pw.close();
			attr = elements.get(2).attr("href");
			
		} while (attr.endsWith(".html"));
	}
}
