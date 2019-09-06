package com.wdx.URLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;

public class whois {

	private final static int DEFAULT_PORT = 43;
	private final static String DEFAULT_HOST = "whois.internic.net";
	
	private int port;
	private InetAddress host;
	
	public whois(int port, InetAddress host) {
		this.port = port;
		this.host = host;
	}
	public whois(InetAddress host) {
		this(DEFAULT_PORT,host);
	}
	public whois(String hostName,int port) throws UnknownHostException {
		this(port, InetAddress.getByName(hostName));
	}
	public whois(String hostName) throws UnknownHostException {
		this(DEFAULT_PORT, InetAddress.getByName(hostName));
	}
	
	public whois() throws UnknownHostException {
		this(DEFAULT_HOST, DEFAULT_PORT);
	}
	
	//搜索条目
	public enum SearchFor{
		ANY("Any"),NETWORK("Network"),PERSON("Person"),HOST("Host"),
		DOMAIN("Domain"),ORGANIZATION("Organization"),GROUP("Group"),
		GATEWAY("Gateway"),ASN("Asn");
		private String label;
		private SearchFor(String label){
			this.label = label;
		}
	}
	//搜索类别
	public enum SearchIn{
		ALL(""),NAME("Name"),MAILBOX("Mailbox"),HANDLE("!");
		private String label;
		private SearchIn(String label){
			this.label = label;
		}
	}
	
	/**
	 * 
	 * @param target		要搜索的字符串
	 * @param category		搜索的记录类型
	 * @param group			搜索的数据库
	 * @param exactMatch	是否完全匹配
	 * @return
	 * @throws IOException 
	 */
	public String lookUpNames(String target, SearchFor category,
			SearchIn group, boolean exactMatch) throws IOException{
		
		String suffix = "";
		if (!exactMatch) suffix = ".";
		
		String prefix = category.label + " " + group.label;
		String query = prefix + target + suffix;
		
		Socket socket = new Socket();
		try {
			SocketAddress address = new InetSocketAddress(host, port);
			socket.connect(address);
			OutputStreamWriter out = 
					new OutputStreamWriter(socket.getOutputStream(), "ASCII");
			BufferedReader in = 
					new BufferedReader(new InputStreamReader(socket.getInputStream(), "ASCII"));
			out.write(query + "\r\n");
			out.flush();
			
			StringBuffer response = new StringBuffer();
			String theLine = null;
			while((theLine = in.readLine()) != null){
				response.append(theLine);
				response.append("\r\n");
			}
			return response.toString();
		} finally {
			socket.close();
		}
	}
	
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public InetAddress getHost() {
		return host;
	}
	public void setHost(InetAddress host) {
		this.host = host;
	}
}
