package com.wdx.utils;

import java.io.FileWriter;
import java.io.IOException;

public class ZDUtil2 {
	
	
	public static void main(String[] args) throws IOException {
		
		String[] str = {
				"1","2","3","4","5","6","7","8","9","0",
				"q","w","e","r","t","y","u","i","o","p",
				"a","s","d","f","g","h","j","k","l","z",
				"x","c","v","b","n","m","Q","W","E","R",
				"T","Y","U","I","O","P","A","S","D","F",
				"G","H","J","K","L","Z","X","C","V","B","N","M"};
		
		FileWriter fw = new FileWriter("Text2.txt");
		for(int i = 0;i<62;i++){
			for(int q = 0;q<62;q++){
				fw.write(str[i]+str[q]+"\n");
				for(int w = 0;w<62;w++){
					fw.write(str[i]+str[q]+str[w]+"\n");
					for(int e = 0;e<62;e++){
						fw.write(str[i]+str[q]+str[w]+str[e]+"\n");
						for(int r = 0;r<62;r++){
							fw.write(str[i]+str[q]+str[w]+str[e]+str[r]+"\n");
							for(int t = 0;t<62;t++){
								fw.write(str[i]+str[q]+str[w]+str[e]+str[r]+str[t]+"\n");
							}
						}
					}
				}
			}
		}
		fw.close();
		
		
	}
}
