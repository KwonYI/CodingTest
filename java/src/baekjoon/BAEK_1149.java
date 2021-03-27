package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1149 {

	static int N;
	static int[][] colors;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());

		colors = new int[N][3]; // R, G, B
		
		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			colors[r][0] = Integer.parseInt(st.nextToken());
			colors[r][1] = Integer.parseInt(st.nextToken());
			colors[r][2] = Integer.parseInt(st.nextToken());
			
		}
		
		for (int r = 1; r < N; r++) {
			colors[r][0] = Math.min(colors[r][0] + colors[r - 1][1], colors[r][0] + colors[r - 1][2]);
			colors[r][1] = Math.min(colors[r][1] + colors[r - 1][0], colors[r][1] + colors[r - 1][2]);
			colors[r][2] = Math.min(colors[r][2] + colors[r - 1][0], colors[r][2] + colors[r - 1][1]);
		}
		
		Arrays.sort(colors[N-1]);
		
		System.out.println(colors[N-1][0]);
	}
}