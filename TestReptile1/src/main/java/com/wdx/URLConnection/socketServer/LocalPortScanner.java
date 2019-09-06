package com.wdx.URLConnection.socketServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;

public class LocalPortScanner {
	public static void main(String[] args) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(1024);
		System.out.println(serverSocket.getReuseAddress());
		InetSocketAddress address = new InetSocketAddress(0);
		serverSocket.bind(address);
		System.out.println(serverSocket.getInetAddress());
		System.out.println(serverSocket.getLocalPort());
		
	}
	
	public static void setSocketOption() throws IOException{
		
		ServerSocket socket = new ServerSocket();
		//设置Socket选项
		SocketAddress http = new InetSocketAddress(80);
		//将ServerSocket绑定到特定的地址(IP地址和端口号)。
		//如果地址为空，那么系统将选择一个临时端口和一个有效的本地地址来绑定套接字。
		socket.bind(http);
	}
	
	public static void testBindPort(){
		for(int i = 0; i < 65535; i++){
			try {
				ServerSocket serverSocket = new ServerSocket(i);
			} catch (Exception e) {
				System.out.println("There is a server on port " + i + ".");
			}
		}
	}
}
