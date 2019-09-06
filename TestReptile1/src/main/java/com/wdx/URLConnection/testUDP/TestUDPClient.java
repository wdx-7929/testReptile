package com.wdx.URLConnection.testUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class TestUDPClient {
	public static void main(String[] args) throws Exception{
		
		DatagramSocket socket = new DatagramSocket(0);
		socket.setSoTimeout(10000);
		InetAddress address = InetAddress.getByName("127.0.0.1");
		DatagramPacket request = new DatagramPacket(new byte[1], 1, address, 1030);
		byte[] data = new byte[1024];
		DatagramPacket response = new DatagramPacket(data, data.length);
		socket.send(request);
		socket.receive(response);
		String daytime = new String(response.getData(), 0, response.getLength(), "US-ASCII");
		System.out.println(daytime);
		
		
		
	}
}