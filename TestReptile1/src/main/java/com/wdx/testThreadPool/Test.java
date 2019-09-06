package com.wdx.testThreadPool;

import java.util.concurrent.atomic.AtomicLong;

public class Test {
	public static void main(String[] args) {
		AtomicLong con = new AtomicLong(20);
		System.out.println(con.getAndIncrement());
		System.out.println(con.getAndIncrement());
		System.out.println(con.getAndDecrement());
	}
}
