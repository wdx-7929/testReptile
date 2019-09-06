package com.wdx.URLConnection.httpServer;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class SingleFileHTTPServer {

	
	private final byte[] content;
	private final byte[] header;
	private final int port;
	private final String encoding;
	
	
	public SingleFileHTTPServer(String data, String encoding,
			String mimeType, int port) throws UnsupportedEncodingException {
		this(data.getBytes(encoding), encoding, mimeType, port);
	}

	public SingleFileHTTPServer(byte[] content, String encoding, String mimeType, int port) {
		this.content = content;
		this.port = port;
		this.encoding = encoding;
		String header = "HTTP/1.0 200 OK\r\n"
				+ "Server:OneFile 2.0\r\n"
				+ "Content-length: " + this.content.length + "\r\n"
				+ "Content-type: " + mimeType+";charset=" + encoding + "\r\n\r\n";
		this.header = header.getBytes(Charset.forName("US-ASCII"));
	}
	
	public void start(){
		ExecutorService pool = Executors.newFixedThreadPool(100);
		try(ServerSocket server = new ServerSocket(this.port)){
			System.out.println("Accepting connections on port " + server.getLocalPort());
			System.out.println("Data to be sent:");
			System.out.println(new String(this.content,encoding));
			
			while(true){
				try {
					Socket connection = server.accept();
					pool.submit(new HTTPHandler(connection));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		} catch (IOException e) {
		}
	}
	
	public class HTTPHandler implements Callable<Void>{

		private final Socket connection;
		
		HTTPHandler(Socket connection) {
			this.connection = connection;
		}
		
		public Void call() throws Exception {
			try {
				OutputStream out = 
						new BufferedOutputStream(connection.getOutputStream());
				InputStream in = 
						new BufferedInputStream(connection.getInputStream());
				//只读取一行，这是我们需要的全部内容
				StringBuilder request = new StringBuilder(80);
				while(true){
					int c = in.read();
					if (c == '\r' || c == '\n' || c == -1) break;
					request.append((char)c);
				}
				//如果是HTTP/1.0或以后版本，则发送一个MIME首部
				if (request.toString().indexOf("HTTP/") != -1) {
					out.write(header);
				}
				out.write(content);
				out.flush();
			} catch (IOException e) {
				System.out.println(Level.WARNING+"  Error writing to client"+e);
			}finally {
				connection.close();
			}
			return null;
		}
	}
	public static void main(String[] args) {
		//设置要监听的端口
		int port;
		try {
			port = Integer.parseInt(args[1]);
			if (port < 1 || port > 65535) port = 80;
		} catch (Exception e) {
			port = 80;
		}
		String encoding = "UTF-8";
		if (args.length > 2) encoding = args[2];
		
		try{
			Path path = Paths.get(args[0]);
			byte[] data = Files.readAllBytes(path);
			
			String contentType = URLConnection.getFileNameMap().getContentTypeFor(args[0]);
			SingleFileHTTPServer server = new SingleFileHTTPServer(data, encoding, contentType, port);
			server.start();
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Usage: java SingleFileHTTPServer filename port encoding");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
}
