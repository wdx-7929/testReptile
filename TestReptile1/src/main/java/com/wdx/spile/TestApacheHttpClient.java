package com.wdx.spile;

import java.io.IOException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TestApacheHttpClient {
	public static void main(String[] args) throws ClientProtocolException, IOException {
//		httpGet();
		testZhenZe();
		
		
	}
	
	public static void testZhenZe(){
		String string = "200HttpResponseProxy{HTTP/1.1 200 OK [Server: nginx"
				+ "/1.15.6, Date: Sun, 17 Mar 2019 08:30:52 GMT, Content-Type: "
				+ "text/html; charset=utf-8, Transfer-Encoding: chunked, Connectio"
				+ "n: keep-alive, X-Frame-Options: SAMEORIGIN, X-XSS-Protection: 1;"
				+ " mode=block, X-Content-Type-Options: nosniff, ETag: W/\"528"
				+ "a1c31d70877437061c91df513e39c\", Cache-Control: "
				+ "max-age=0, private, must-revalidate, Set-Cookie: _tui"
				+ "cool_session=BAh7B0kiD3Nlc3Npb25faWQGOgZFVEkiJTRhNDM5NWJhN2Y2YzA0YjZ"
				+ "hNWJlM2Y2ZGQ0MmNhNmQwBjsAVEkiEF9jc3JmX3Rva2VuBjsARkkiMURDL242M3NOREx"
				+ "pRnF6dytDNmc5K1ZPQ05OMzVMNGpPS2RxNUlmTFlNVzg9BjsARg%3D%3D--dfadf868"
				+ "d42f65104d0f71cf8ad445af97d8619c; domain=.tuicool.com; path=/; expir"
				+ "es=Fri, 06 Nov 2020 08:30:52 -0000; HttpOnly, X-Request-Id: 83525"
				+ "c70-d8f6-4691-8c75-efe69097bf95, X-Runtime: 0.032294]}";
		
		int lastIndexOf = string.lastIndexOf("[Http(.*){HT]");
		System.out.println(lastIndexOf);
		String[] split = string.split("['Http']");
		System.out.println(split.length);
//		List<String> asList = Arrays.asList(split);
//		Stream<String> stream = asList.stream();
//		asList.stream().filter(str  -> !str.isEmpty()).forEach(System.out::println);
		
		String pattern = "H.*p";
		Pattern compile = Pattern.compile(pattern);
		Matcher matcher = compile.matcher(string);
		if (matcher.find()) {
			String group = matcher.group();
			System.out.println(group);
			int start = matcher.start();
			System.out.println(start);
			int end = matcher.end();
			System.out.println(end);
			System.out.println(matcher.group(1));
		}
		
	}
	
	
 	public static void httpGet() throws ClientProtocolException, IOException{
		
		//创建httpClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建httpGet实例
        HttpGet httpGet = new HttpGet("http://www.tuicool.com");
        //设置代理IP，设置连接超时时间 、 设置 请求读取数据的超时时间 、 设
        //置从connect Manager获取Connection超时时间、58.60.255.82 8118
//        HttpHost proxy = new HttpHost("192.168.42.90",8888);
        RequestConfig requestConfig = RequestConfig.custom()
//                .setProxy(proxy)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000)
                .setConnectionRequestTimeout(3000)
                .build();
        httpGet.setConfig(requestConfig);
        //设置请求头消息
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.94 Safari/537.36");
        CloseableHttpResponse response = httpClient.execute(httpGet);

        if (response != null){
            HttpEntity entity = response.getEntity();  //获取返回实体
            Locale locale = response.getLocale();
            StatusLine statusLine = response.getStatusLine();
            System.out.println(statusLine.getStatusCode());
            System.out.println(response);
            if (entity != null){
//                System.out.println("网页内容为:"+ EntityUtils.toString(entity,"utf-8"));
            }
        }
        if (response != null){
            response.close();
        }
        if (httpClient != null){
            httpClient.close();
        }
		
	}
	
	
}
