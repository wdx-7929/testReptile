package com.wdx.utils;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLockAndCondition {
	
	private static Lock lock = new ReentrantLock();
	static Condition conditionA = lock.newCondition();
	static Condition conditionC = lock.newCondition();
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new TestLockAndCondition().new ThreadA(),"线程3").start();
		new Thread(new TestLockAndCondition().new ThreadB(),"线程1").start();
//		new Thread(new TestLockAndCondition().new ThreadC(),"线程2").start();
		
	}
	
	class ThreadA implements Runnable{

		public void run() {
			lock.lock();
			System.out.println("ThreadA线程 我输出了。。。");
			try {
				conditionA.await();
				System.out.println("ThreadA唤醒了");
				Thread.sleep(3000);
				System.out.println("3秒");
			} catch (InterruptedException e) {
				System.err.println("conditionA出错");
			}
			lock.unlock();
		}
	}
	class ThreadB implements Runnable{

		public void run() {
			lock.lock();
			System.out.println("ThreadB线程 我输出了。。。");
			try {
				Thread.sleep(1000);
				System.out.println("1秒");
				conditionA.signal();
				Thread.sleep(5000);
				System.out.println("5秒");
			} catch (InterruptedException e) {
			}
			lock.unlock();
		}
	}
	class ThreadC implements Runnable{

		public void run() {
			lock.lock();
			System.out.println("ThreadC线程 我输出了。。。");
			try {
				Thread.sleep(2000);
				conditionC.await();
			} catch (InterruptedException e) {
			}
			System.out.println("ThreadC唤醒了");
			lock.unlock();
		}
	}
	
	
}
