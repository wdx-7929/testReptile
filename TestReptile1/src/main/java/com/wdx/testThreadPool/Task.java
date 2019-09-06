package com.wdx.testThreadPool;

import java.util.concurrent.atomic.AtomicLong;

class Task implements Runnable {
	private final AtomicLong count = new AtomicLong();
	
	public void run(){
		
		System.out.println("runing_" + count.getAndIncrement());
		
	}
}
