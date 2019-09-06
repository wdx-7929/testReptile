package com.wdx.spile;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestJsoup {
	public static void main(String[] args) throws IOException {
		String html = "<html><head><title>First parse</title></head>"
				  + "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
		System.out.println(doc.data());		
		System.out.println(doc);
		Document document = Jsoup.connect("http://www.yiibai.com").get();
		System.out.println(document.title());
//		System.out.println(document.data());
		Document document2 = Jsoup.connect("http://www.yiibai.com").get();
		String title = document2.title();
		System.out.println(title);
		Elements elements = document2.select("a[href]");
		Element first = document2.select("a[href]").first();
		System.out.println(first.attr("href"));
		System.out.println(first.text());
		
//		for (Element element : elements) {
//			System.out.println(element.attr("href"));
//			System.out.println(element.text());
//		}
//		String keywords = document2.select("input[id=kw]").first().attr("type");
//		System.out.println(keywords);
//		String string = document2.select("meta[name=description]").get(0).attr("content");
//		System.out.println(string);
		Document document3 = Jsoup.connect("http://www.doutula.com/")
								.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
								.header("Accept-Encoding", "gzip, deflate")
								.header("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2")
								.header("Connection", "keep-alive")
								.header("Cookie", "_cfduid=d356abd0a09f780ff90ec87cec724cb661552887699; UM_distinctid=1698f51de3720-07ac7d4d61090b8-11666e4a-1fa400-1698f51de38e; BAIDU_SSP_lcr=https://www.baidu.com/link?url=MwVOhlBCSdiLZ2-LPxNvMOiJZ3Wg_UPb-4oxV63wbe0-q40UP___iTlcXuTVdgqT&wd=&eqid=d4d15fdd001877a8000000065c8f2f90; _ga=GA1.2.152823615.1552887703; _gid=GA1.2.1322735967.1552887703")
								.header("Host", "static.doutula.com")
								.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:65.0) Gecko/20100101 Firefox/65.0")
								.timeout(2000)
								.get();
//		System.out.println(document3.html());
//		document3.text();
		byte[] b = new byte[1024];
		int len = 0;
//		FileOutputStream fos = new FileOutputStream("doutu.html");
//		FileInputStream fis = new FileInputStream("doutu.html");
//		PrintWriter pw = new PrintWriter(new FileWriter("doutu.html"));
//		pw.write(document3.html());
//		pw.close();
		Elements elements2 = document3
//						.select("img[data-original~=(?i)\\.(png|jpg?g|gif)]")
						.select("img[alt=\"嗯嗯\"]");
		for (Element element : elements2) {
			System.out.println("data-original = "+element.attr("data-original"));
			System.out.println("alt = "+element.attr("alt"));
			System.out.println("height = "+element.attr("height"));
		}
	}
}
