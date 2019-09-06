package com.wdx.testThreadPool;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过newThread方法快速、统一地创建线程任务，强调线程一定
 * 要有特定意义的名称，方便出错回溯
 * @author wdx
 *
 */
public class UserThreadFactory implements ThreadFactory {

	private final String namePrefix;
	private final AtomicInteger nextId = new AtomicInteger(1);
	
	//定义线程组名称，在使用jstack来排查线程问题时，非常有帮助
	UserThreadFactory(String whatFeatureOfGroup) {
		namePrefix = "UserThreadFactory's " + whatFeatureOfGroup + "-Worker-";
	}
	
	public Thread newThread(Runnable task) {
		String name = namePrefix + nextId.getAndIncrement();
		Thread thread = new Thread(null,task,name,0);
		System.out.println(thread.getName());
		return thread;
	}
	
}
