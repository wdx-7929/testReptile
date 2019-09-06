package com.wdx.logSlf4j;

import java.util.concurrent.Callable;

public class TestCallable {
	
	public static void main(String[] args) {
		
		new Thread().start();
	}
	
	private void testSy(){
		synchronized (this) {
			
		}
	}
	
	class MyCallable<V> implements Callable<V>{

		public V call() throws Exception {
			
			System.out.println("aaa");
			
			return null;
		}
		
	}
}
