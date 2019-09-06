package com.wdx.spile;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.auth.CredentialsProvider;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

/**
 * commons.HttpClient项目已经停止开发已被apache的项目替代
 * @author wdx
 *
 */
public class TestHttpClient {
	
	public static void main(String[] args) throws HttpException, IOException {
		HttpClientProxy();
	}
	
	//代理 方式
	public static void HttpClientProxy() throws HttpException, IOException{
		HttpClient httpClient = new HttpClient();
		//设置代理服务器的IP地址和端口
		httpClient.getHostConfiguration().setProxy("192.168.0.1", 9527);
		//告诉httpClient，使用抢先认证，否账你会收到“你没有资格”的恶果
		httpClient.getParams().setAuthenticationPreemptive(true);
		//MyProxyCredentialsProvider返回代理credential(username.password)
		httpClient.getParams().setParameter(CredentialsProvider.PROVIDER,
					new MyProxyCredentialsProvider());
		//设置代理服务器的用户名和密码
		httpClient.getState().setProxyCredentials(new AuthScope("192.168.0.1",
							AuthScope.ANY_PORT,AuthScope.ANY_REALM),
						new UsernamePasswordCredentials("username","password"));
		HttpClientPost(httpClient);
	}
	
	public static void HttpClientPost(HttpClient httpClient) throws HttpException, IOException{
//		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod("https://blog.csdn.net/lzj0470/article/details/84151104");
		//请求接口发送参数
		NameValuePair[] postDate = new NameValuePair[2];
		postDate[0] = new NameValuePair("武器", "1000+");
		postDate[0] = new NameValuePair("防具", "200");
		
		int method = httpClient.executeMethod(postMethod);
		System.out.println(method);
		postMethod.releaseConnection();
	}
	
	public static void HttpClientGet(HttpClient httpClient) throws HttpException, IOException {
		//创建一个客户端，类似于打开一个浏览器
//		HttpClient httpClient = new HttpClient();
		//创建一个get方法，类似于在浏览器地址栏输入一个地址
		GetMethod getMethod = new GetMethod("https://blog.csdn.net/lzj0470/article/details/84151104");
		//回车获得响应状态码
		int executeMethod = httpClient.executeMethod(getMethod);
		//查看命中情况，可以获得的东西很多，比如head、cookies等
		System.out.println("response="+getMethod.getResponseBodyAsString().substring(10,20));
		//释放
		getMethod.releaseConnection();
	}
	
}
