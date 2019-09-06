package com.wdx.utils;

public class TestGenericity<String,abc extends T, T> {
	
	public void print(abc a, String s) {
		System.out.println(a + "   " + s);
	}
	
	
}
