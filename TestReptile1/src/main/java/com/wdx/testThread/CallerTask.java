package com.wdx.testThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallerTask implements Callable<Integer> {

	public Integer call() throws Exception {
		System.out.println("线程执行了");
		return 1;
	}

	public static void main(String[] args) {
		
		CallerTask callerTask = new CallerTask();
		FutureTask<Integer> futureTask = new FutureTask<>(callerTask);
		new Thread(futureTask).start();
		try {
			Integer integer = futureTask.get();
			System.out.println(integer);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
