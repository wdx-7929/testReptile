package com.wdx.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

class MyInitialize {
	
	private final static String[] str = {
			"1","2","3","4","5","6","7","8","9","0",
			"q","w","e","r","t","y","u","i","o","p",
			"a","s","d","f","g","h","j","k","l","z",
			"x","c","v","b","n","m","Q","W","E","R",
			"T","Y","U","I","O","P","A","S","D","F",
			"G","H","J","K","L","Z","X","C","V","B","N","M"};
	static int count = 0;
	
	private List<Queue<StringBuffer>> queueList = new ArrayList<Queue<StringBuffer>>();
	private Queue<String> qBuffers = new ConcurrentLinkedQueue<String>();
	private FileWriter fw;
	
	public MyInitialize() throws IOException {
		for(int i = 0;i < 6; i++){
			queueList.add(new ConcurrentLinkedQueue<StringBuffer>());
		}
	}
	public List<Queue<StringBuffer>> getQueueList() {
		return queueList;
	}

	public void setQueueList(List<Queue<StringBuffer>> queueList) {
		this.queueList = queueList;
	}

	public  FileWriter getfw() throws IOException {
		fw = new FileWriter("Text.txt",true);
		return fw;
	}
	public  void setfw(FileWriter fw) {
		this.fw = fw;
	}
	public static String[] getStr() {
		return str;
	}
	
	public Queue<String> getqBuffers() {
		return qBuffers;
	}
	public void setqBuffers(Queue<String> qBuffers) {
		this.qBuffers = qBuffers;
	}
	public void close() throws IOException{
		this.fw.flush();
		this.fw.close();
	}
	
	

}
