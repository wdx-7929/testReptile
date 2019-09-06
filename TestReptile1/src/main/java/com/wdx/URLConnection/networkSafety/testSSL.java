package com.wdx.URLConnection.networkSafety;

import java.io.IOException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class testSSL {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, UnknownHostException, IOException {
		
		SocketFactory socketFactory = SSLSocketFactory.getDefault();
		SSLSocket socket = (SSLSocket) socketFactory.createSocket("", 0);
		String[] cipherSuites = socket.getSupportedCipherSuites();
		
		socket.setEnabledCipherSuites(cipherSuites);
		SSLSession sslSession = socket.getSession();
		
		//为避免socket创建会话，向以下方法传入false
		socket.setEnableSessionCreation(false);
		
		ByteBuffer bb = ByteBuffer.allocate(100);
		bb.flip();
		
	}
	
}
