package com.wdx.testThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ZDUtils3 {
	private final static Logger auditLogger = Logger.getLogger("requests");
	
	public static void main(String[] args) {
		/*
		 * corePoolSize			常驻核心线程数
		 * maximumPoolSize		同时执行最大线程数
		 * keepAliveTime		线程空闲时间
		 * unit					keepAliveTime参数的时间单位
		 * workQueue			缓存队列，用于执行前保存的队列
		 * threadFactory		执行程序创建新线程时使用的工厂
		 * handler				当执行被阻塞时使用的处理程序，因为达到了线程边界和队列容量
		 */
		
		BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(2);
		
		UserThreadFactory f1 = new UserThreadFactory("1号机器");
		UserThreadFactory f2 = new UserThreadFactory("2号机器");
		
		UserRejectHander handler = new UserRejectHander();
		
		ThreadPoolExecutor threadPoolFirst =
				new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, workQueue, f1, handler);
		ThreadPoolExecutor threadPoolSecond =
				new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, workQueue, f2, handler);
		
		Executors.newFixedThreadPool(12);
		
		//创建400个任务线程
		Runnable task = new Task();
		for (int i = 0; i < 200; i++) {
			threadPoolFirst.execute(task);
			threadPoolSecond.execute(task);
		}
	}
}
