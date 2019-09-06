package com.wdx.testEunm;

public enum test1 {
	
	E1("asd"),E2("123");
	
	private String str;
	
	private test1(String str) {
		this.str = str;
	}
	public String getStr() {
		return str;
	}
}
