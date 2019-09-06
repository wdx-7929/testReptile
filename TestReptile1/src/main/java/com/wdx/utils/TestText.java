package com.wdx.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TestText {
	public static void main(String[] args) throws IOException {
		
		int i = 100;
		for(int j = 0; j < 100; j++){
			i--;
		}
		System.out.println(i);
//		FileWriter fWriter1 = new FileWriter("Text.txt",true);
//		fWriter1.write("asd\n");
//		fWriter1.close();
//		FileWriter fWriter2 = new FileWriter("Text.txt",true);
//		fWriter2.write("ddd\n");
//		fWriter2.close();
	}
	
	public static void test1() throws IOException {
		Queue<String> queue = new ConcurrentLinkedQueue<String>();
		queue.add("a");
		queue.add("b");
		MyInitialize mi = new MyInitialize();
		List<Queue<StringBuffer>> queueList = mi.getQueueList();
		Queue<StringBuffer> queue2 = queueList.get(0);
		StringBuffer sb = new StringBuffer("1");
		queue2.add(sb);
		
		System.out.println(queue.remove());
		System.out.println(queue.size());
		System.out.println(queue.isEmpty());
	}
	
	public static void name() throws IOException {
		ArrayList<String> arrayList = new ArrayList<String>();
		FileReader fr = new FileReader("Text.txt");
		BufferedReader bf = new BufferedReader(fr);
		String str;
		// 按行读取字符串
//		while ((str = bf.readLine()) != null) {
//			arrayList.add(str);
//		}
		for(int i=0;i<10000;i++){
			System.out.println(bf.readLine());
		}
		bf.close();
		fr.close();
	}
}
