package com.wdx.URLConnection;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketAddress;

public class OpenSocket {
	public static void main(String[] args) throws IOException {
		SocketAddress sa = new InetSocketAddress("", 80);
		Proxy proxy = new Proxy(Proxy.Type.SOCKS, sa);
		Socket socket = new Socket(proxy);
		InetSocketAddress socketAddress = new InetSocketAddress("", 1080);
		socket.connect(socketAddress);
		
		
	}
}
