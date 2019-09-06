package com.wdx.testThreadPool;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Inhibit extends AbstractQueuedSynchronizer {
	public static void main(String[] args) {
		Inhibit inhibit= new Inhibit();
		inhibit.in();
		
		
	}
	
	public void in(){
		int i = 0;
		setState(-1);
		i = i/0;
		System.out.println("的");
	}

	
}
