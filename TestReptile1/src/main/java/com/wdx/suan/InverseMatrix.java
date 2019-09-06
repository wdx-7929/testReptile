package com.wdx.suan;

import java.util.Collection;
import java.util.Collections;

public class InverseMatrix {
	static int arr[][] = {	{0,  1,  2,  3,  4}, 
							{13, 14, 15, 16, 5}, 
							{12, 19, 18, 17, 6}, 
							{11, 10, 9,  8,  7}};
	static int up = 1;
	static int down = 3;
	static int left = 0;
	static int right = 4;
	static String direction = "right";
	static int xDirection = 0;//左右方向
	static int yDirection = 0;//上下方向
	public static void main(String[] args) {
		fill();
	}
	
	public static void fill() {
		
		for (int i = 0; i < 20; i++) {
			System.out.println(arr[yDirection][xDirection]);
			System.out.println(judgeDi());
			
		}
	}
	public static String judgeDi() {
		String dirString = null;
		switch (direction) {
			case "up":
				if (yDirection == up) {
					direction = "right";
					up++;
					dirString = "up";
					xDirection++;
					break;
				}
				yDirection--;
				dirString = "up";
				break;
			case "down":
				if (yDirection == down) {
					direction = "left";
					down--;
					dirString = "down";
					xDirection--;
					break;
				}
				yDirection++;
				dirString = "down";
				break;
			case "left":
				if (xDirection == left) {
					direction = "up";
					left++;
					dirString = "left";
					yDirection--;
					break;
				}
				xDirection--;
				dirString = "left";
				break;
			case "right":
				if (xDirection == right) {
					direction = "down";
					right--;
					dirString = "right";
					yDirection++;
					break;
				}
				xDirection++;
				dirString = "right";
				break;
		}
		
		return dirString;
	}
	
}
