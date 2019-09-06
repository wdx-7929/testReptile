package com.wdx.URLConnection.testUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class TestUDPServer {
	public static void main(String[] args) throws Exception {
		
		DatagramSocket socket = new DatagramSocket(1030);
		//接受数据包
		DatagramPacket request = new DatagramPacket(new byte[1024], 0, 1024);
		socket.receive(request);
		
		//响应数据包， 主机和端口就是入站的主机和端口
		String daytime = new Date().toString() + "\r\n";
		byte[] data = daytime.getBytes("US-ASCII");
		InetAddress address = request.getAddress();
		int port = request.getPort();
		DatagramPacket response = new DatagramPacket(data, data.length, address, port);
		socket.send(response);
		
		
		
		
	}
}
