package com.wdx.test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;


public class CoverageSampleMethods {
	public Boolean testMethod(int a , int b , int c) { 
		boolean result = false ; 
		if (a == 1 && b == 2 || c == 3) {
			result = true;
		}
	return result;
	}
	
	@Test
	@DisplayName("line coverage sample test")
	public void testLineCoverageSample1(){
		CoverageSampleMethods coverageSampleMethods = 
				new CoverageSampleMethods();
		Assertions.assertFalse(coverageSampleMethods.testMethod(1, 2, 0));
		Assertions.assertTrue(coverageSampleMethods.testMethod(1,2,0));
		System.out.println("asd");
	}
	
	@Test
	@DisplayName("line coverage sample test")
	public void testLineCoverageSample2(){
		CoverageSampleMethods coverageSampleMethods = 
				new CoverageSampleMethods();
	}
	
	@Test
	public void testTest(){
		System.out.println("   asd");
	}
	
}
