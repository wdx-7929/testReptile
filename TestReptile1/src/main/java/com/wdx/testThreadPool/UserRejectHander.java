package com.wdx.testThreadPool;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 当执行被阻塞时使用的处理程序，因为达到了线程边界和队列容量
 * @author wdx
 */
public class UserRejectHander implements RejectedExecutionHandler {

	public void rejectedExecution(Runnable task, ThreadPoolExecutor executor) {
		System.out.println("task rejectted. " + executor.toString());
	}
}
