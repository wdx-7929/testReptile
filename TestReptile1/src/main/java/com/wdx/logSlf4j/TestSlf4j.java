package com.wdx.logSlf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试一下日志的使用
 * 
 * @author wdx
 */
public class TestSlf4j {

	public static <K> void main(String[] args) {
		Map<String, String> map1 = new HashMap<String, String>();
		Map<String, String> map2 = new HashMap<String, String>();
		map1.put("1", "1");
		map2.put("1", "1");
		int i = 0;
		System.out.println(map1.equals(map2));
		if (map1.hashCode() != map2.hashCode() && ++i == 1) {
			System.out.println(i);
		}
	}
}
