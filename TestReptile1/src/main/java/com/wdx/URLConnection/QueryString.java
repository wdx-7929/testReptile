package com.wdx.URLConnection;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 它使用URLEncoder对一个Java对象中连续的名-值对编码，
 * 这个对象将用来向服务器发送数据。为了增加名-值对，需要调用add()方法，
 * 它接受两个字符串作为参数，并进行编码。getQuery()方法返回编码后名-值对
 * 的累积列表。
 * @author wdx
 */
public class QueryString {
	
	private StringBuilder query = new StringBuilder();
	
	public QueryString() {
		super();
	}
	
	public synchronized void add(String name, String value) {
		query.append('&');
		encode(name,value);
	}

	private synchronized void encode(String name, String value) {
		try {
			query.append(URLEncoder.encode(name,"UTF-8"));
			query.append('=');
			query.append(URLEncoder.encode(value,"UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Broken VM does not support UTF-8");
		}
	}
	
	public synchronized String getQuery(){
		return query.toString();
	}
	
	public String toString(){
		return getQuery();
	}
	public static void main(String[] args) {
		QueryString qs = new QueryString();
		qs.add("h1", "en");
		System.out.println(qs);
	}
}
