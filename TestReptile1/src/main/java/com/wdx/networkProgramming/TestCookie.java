package com.wdx.networkProgramming;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;


public class TestCookie {
	public static void main(String[] args) {
		CookieManager manager = new CookieManager();
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);//只接受第一方Cookie
		manager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);//接受所有Cookie
		manager.setCookiePolicy(CookiePolicy.ACCEPT_NONE);//不接受任何Cookie
		CookieHandler.setDefault(manager);
		
	}

}
