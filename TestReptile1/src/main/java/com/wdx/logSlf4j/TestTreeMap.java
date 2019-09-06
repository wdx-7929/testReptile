package com.wdx.logSlf4j;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntBinaryOperator;

public class TestTreeMap {
	public static void main(String[] args) {
		
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("a", "1");
		treeMap.put("c", "3");
		treeMap.put("b", "2");
		Set<Entry<String,String>> entrySet = treeMap.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry.getKey()+"     "+entry.getValue());
		}
		HashMap<String, String> hashMap = new HashMap<String, String>();
		
		int i = 1;
		float f = 0.75f;
		System.out.println((i<<4)*f*f);
		int   MAX_VALUE = 0x7fffffff;
		System.out.println(MAX_VALUE);
		Object key = "2";
		int h = 160000;
		System.out.println((h>>>16));
		System.out.println(key.hashCode());
		h = (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
		System.out.println(h);
		
		AtomicInteger ai = new AtomicInteger(i);
		IntBinaryOperator accumulatorFunction = new TestTreeMap().new MyIntBinaryOperator();
		int accumulateAndGet = ai.accumulateAndGet(5, accumulatorFunction);
		System.out.println(accumulateAndGet);
		int addAndGet = ai.addAndGet(6);
		System.out.println(ai.get());
	}
	
	
	class MyIntBinaryOperator implements IntBinaryOperator{

		public int applyAsInt(int left, int right) {
			System.out.println("left:"+left+"   right:"+right);
			return left+right;
		}
		
	}
	
}
