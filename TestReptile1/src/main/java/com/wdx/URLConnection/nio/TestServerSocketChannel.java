package com.wdx.URLConnection.nio;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestServerSocketChannel {
	public static void main(String[] args) throws Exception {
		//打开通道
		ServerSocketChannel serverChannel = ServerSocketChannel.open();
		ServerSocket serverSocket = serverChannel.socket();
		//绑定端口
		serverSocket.bind(new InetSocketAddress(1028));
		//同时允许服务端也处于非阻塞模式，默认情况下accept方法会阻塞，所以在之前
		serverChannel.configureBlocking(false);
		//接受连接,如果没有入站连接，非阻塞accept会立即返回null。要确保对其进行检查。否则会返回异常
		SocketChannel clientChannel = serverChannel.accept();
		//在服务端指定客户端通道非阻塞模式，允许服务器处理多个并发连接
		clientChannel.configureBlocking(false);
		
		//创建一个Selector，允许程序迭代处理所有准备好的连接
		Selector selector = Selector.open();
		//使用每个通道的register方法向监视这个通道的选择器进行注册
		SelectionKey key = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		
		
		
	}
}
