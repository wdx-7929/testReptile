package com.wdx.utils.algorithm;

public class Packing {
	/*
	 * 	动态规划求解01背包问题
	 * c=11
	 * item:	1	2	3	4	5
	 * value:	1	6	18	22	28
	 * weight:	1	2	5	6	7
	 * 	定义子问题：在前i个物品里面挑选总重量不大于W（W<=C）的物品，记最优值为M(i, W)，则对于第i个物品来说就有两种情况：
	 *	①Wi>W，装不下了，则M(i, W)=M(i-1, W)；
	 *	②Wi<=W，那么就要选择装不装，则M(i, W)=max{M(i-1, W) , M(i-1, W-Wi)+Vi}
	 *	 M(i-1, W-Wi)即为第i个物品装入之前的最优值，结果是装了第i个物品。
	 */
	public static void main(String[] args) {
		int capacity=11; //背包容量
		int item_num=5;	//物品数量
		int[][] M = new int[item_num+1][capacity+1];
		int[] w={0,1,2,5,6,7};
		int[] v={0,1,6,18,22,28};
		int i,j;//初始化
		for(i=0;i<=capacity;i++){
			M[0][i]=0;
		}
		for(j=0;j<=item_num;j++){
			M[j][0]=0;
		}
		
		for(i=1;i<=item_num;i++){
			for(j=1;j<=capacity;j++){
				if(w[i]>j){
					M[i][j]=M[i-1][j];
				}
				else{
					M[i][j]=Math.max(M[i-1][j], M[i-1][j-w[i]]+v[i]);
				}
			}
		}
		for(i=0;i<=item_num;i++){
			for(j=0;j<=capacity;j++){
				System.out.print(M[i][j]+ "\t");
			}
			System.out.println("");
		}
		
	}
}
