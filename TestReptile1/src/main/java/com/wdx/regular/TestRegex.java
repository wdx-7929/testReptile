package com.wdx.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 测试正则表达式
 * 
 * @author wdx
 *
 */
public class TestRegex {
	public static void main(String[] args) {
		testPatternAndMatcher();
		
	}
	
	public static void testPatternAndMatcher(){
		Pattern pattern = Pattern.compile("^java.*");//设定正则
		Matcher matcher = pattern.matcher("java是语言");
		boolean b = matcher.matches();
		System.out.println(b);
		String string = matcher.group();
		System.out.println(string);
	}
	
}
