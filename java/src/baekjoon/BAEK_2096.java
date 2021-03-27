package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_2096 {
	
	static int N;
	static int[][] maxValues, minValues;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.valueOf(bf.readLine());
		
		maxValues = new int[N + 1][3];
		minValues = new int[N + 1][3];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int r = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			
			maxValues[i][0] = Math.max(r + maxValues[i - 1][0], r + maxValues[i - 1][1]);
			maxValues[i][1] = Math.max(m + maxValues[i - 1][0], Math.max(m + maxValues[i - 1][1], m + maxValues[i - 1][2]));
			maxValues[i][2] = Math.max(l + maxValues[i - 1][1], l + maxValues[i - 1][2]);
			
			minValues[i][0] = Math.min(r + minValues[i - 1][0], r + minValues[i - 1][1]);
			minValues[i][1] = Math.min(m + minValues[i - 1][0], Math.min(m + minValues[i - 1][1], m + minValues[i - 1][2]));
			minValues[i][2] = Math.min(l + minValues[i - 1][1], l + minValues[i - 1][2]);
		}
		
		int max = Math.max(maxValues[N][0], Math.max(maxValues[N][1], maxValues[N][2]));
		int min = Math.min(minValues[N][0], Math.min(minValues[N][1], minValues[N][2]));
		
		System.out.println(max + " " + min);
		
	}
}