package com.wdx.fiction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class TestMain {
	public static void main(String[] args) throws UnknownHostException, FileNotFoundException {
		InetAddress inetAddress = InetAddress.getByName("www.baidu.com");
		System.out.println(inetAddress.getHostAddress());
		System.out.println(inetAddress);
//		System.out.println(inetAddress.getCanonicalHostName());
		for (String string : args) {
			System.out.println(string);
		}
		String string = new String("");
		FileInputStream fis = new FileInputStream("pom.xml");
		System.out.println(fis instanceof Object);
		System.out.println(string instanceof Object);
		String string2 = "HZ";
		String string3 = new String("HZ");
		System.out.println(string2 == string3.intern());
		
	}
}
