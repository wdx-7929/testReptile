package com.wdx.utils.algorithm;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class Map {
	/*
	 * 	贪心算法求解图  最短路径
	 * 	假定初始位置为v3 (有向图->  无权重值)
	 * 		 v1 v2 v3 v4 v5 v6 v7
	 * 	  v1 0	1  0  1  0  0  0
	 * 	  v2 0  0  0  1  1  0  0
	 *    v3 1  0  0  0  0  1  0
	 *    v4 0  0  1  1  1  1  1
	 *    v5 0  0  0  0  0  0  1
	 *    v6 0  0  0  0  0  0  0
	 *    v7 0  0  0  0  0  1  0
	 */
	private static int map[][] = {{0,1,0,1,0,0,0},
				           {0,0,0,1,1,0,0},
				           {1,0,0,0,0,1,0},
				           {0,0,1,1,1,1,1},
				           {0,0,0,0,0,0,1},
				           {0,0,0,0,0,0,0},
				           {0,0,0,0,0,1,0}};
	/*
	 * 用于记录图的遍历以及最短路径的上一个顶点
	 * 当变成有权重值的时候数组可以采用字符串形式,第二行可以采用字符串存储父亲节点值
	 */
//	private static String recordMap[][] = {};
	private static int recordMap[][] = {{Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE},
										{0,0,0,0,0,0,0}};
	public static void main(String[] args) {
		//初始设置顶点
		recordMap[0][2] = 0;
		//设置一个中间队列存储
		Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
		queue.add(2);
		while (!queue.isEmpty()) {
			Integer poll = queue.poll();
			for(int i = 0; i < 7; i++) {
				if (map[poll][i]==1 && recordMap[0][i] > recordMap[0][poll]+1) {
					recordMap[0][i] = recordMap[0][poll]+1;
					queue.add(i);
					recordMap[1][i] = poll;
				}
			}
		}
		System.out.println(recordMap.length);
		for (int i = 0; i < 7; i++) {
			if (recordMap[0][i] == 2) {
				traverse(i);
			}
			System.out.println();
		}
	}
	public static void traverse(int i) {
		if (i != 2) {
			traverse(recordMap[1][i]);
			System.out.print(" -> ");
			System.out.print(i);
		}else {
			System.out.print(i);
		}
	}
}
