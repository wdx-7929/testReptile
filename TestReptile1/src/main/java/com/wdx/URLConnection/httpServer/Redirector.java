package com.wdx.URLConnection.httpServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class Redirector {
	
	private final int port;
	private final String newSite;
	
	public Redirector(String newSite, int port){
		this.port = port;
		this.newSite = newSite;
	}
	
	public void start(){
		try(ServerSocket server = new ServerSocket(port)){
			System.out.println("Redirecting connections on port "
					+ server.getLocalPort() + "	to " + newSite);
			while(true){
				try {
					Socket s = server.accept();
					RedirectThread thread = new RedirectThread(s);
					thread.start();
					
				} catch (IOException e) {
					System.err.println("Exception accepting connection");
				} catch (RuntimeException e) {
					System.err.println("Unexpected error " + e);
				}
			}
		}catch (BindException e) {
			System.err.println("Could not start server." + e);
		}catch (IOException e){
			System.err.println("Error opening server socket." + e);
		}
	}
	
	private class RedirectThread extends Thread{
	
		private final Socket connection;
		
		public RedirectThread(Socket s) {
			this.connection = s;
		}

		public void run(){
			try {
				Writer out = new BufferedWriter(
								new OutputStreamWriter(
										connection.getOutputStream(),
										"US-ASCII"));
				Reader in = new BufferedReader(
								new InputStreamReader(
									connection.getInputStream(),
									"US-ASCII"));
				//一下就是需要把流中的数据做出相应的判断
				//只读取一行，这就是需要的全部内容
				StringBuilder request = new StringBuilder(80);
				while(true){
					int c = in.read();
					if (c == '\r' || c == '\n' || c == -1) break;
					request.append((char) c);
				}
				
				String get = request.toString();
				System.out.println(get);
				String[] pieces = get.split("\\w*");
				String theFile = pieces[1];
				System.out.println(theFile);
				
				//如果是HTTP/1.0或以后的版本，则发送一个MIME首部
				if (get.indexOf("HTTP") != -1) {
					out.write("HTTP/1.0 302 FOUND\r\n");
					Date now = new Date();
					out.write("Date: " + now + "\r\n");
					out.write("Server: Redirector 1.1\r\n");
					out.write("Location: " + newSite + theFile + "\r\n");
					out.write("Content-type: text/html\r\n\r\n");
					out.flush();
				}
				
				//并不是所有浏览器都支持重定向，所以需要生成指定的HTML指出文档转移到哪里
				out.write("<HTML><HEAD><TITLE>Document moved</TITLE></HEAD>\r\n");
				out.write("<BODY><H1>Document moved</H1>\r\n");
				out.write("The document " + theFile
						+ " has moved to\r\n<A HREF=\"" + newSite + theFile + "\">"
						+ newSite + theFile
						+ "</A>.\r\n Please update your bookmarks<P>");
				out.write("</BODY></HTML>\r\n");
				out.flush();
				System.out.println("Redirected " + connection.getRemoteSocketAddress());
			} catch (IOException e) {
				System.err.println("Error talking to " + connection.getRemoteSocketAddress() + "\n" +e);
			}finally{
				try{
					connection.close();
				}catch (IOException e) {}
			}
		}
		
	}
	public static void main(String[] args) {
		int thePort;
		String theSite;
		try{
			theSite = args[0];
			System.out.println(theSite.endsWith("/"));
			if (theSite.endsWith("/")) {
				theSite = theSite.substring(0, theSite.length() - 1);
			}
		}catch (RuntimeException e) {
			System.out.println("Usage: java Redirector http://www.newsite.com/port");
			return;
		}
		try {
			thePort = Integer.parseInt(args[1]);
		} catch (RuntimeException e) {
			thePort = 80;
		}
		System.out.println(theSite + "   " + thePort);
		Redirector redirector = new Redirector(theSite, thePort);
		redirector.start();
	}

}
