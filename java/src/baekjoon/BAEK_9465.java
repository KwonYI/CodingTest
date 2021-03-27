package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_9465 {

	static int T, N;
	static int answer;
	static int[][] arr;
	static int[][] p;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.valueOf(bf.readLine());

		for (int t = 0; t < T; t++) {
			N = Integer.valueOf(bf.readLine());

			arr = new int[2][N + 1];
			p = new int[2][N + 1];
			
			for (int r = 0; r < 2; r++) {
				st = new StringTokenizer(bf.readLine());
				for (int c = 1; c <= N; c++) {
					arr[r][c] = Integer.parseInt(st.nextToken());
				}
			}
			
			p[0][1] = arr[0][1];
			p[1][1] = arr[1][1];
			
			for (int c = 2; c <= N; c++) {
				p[0][c] = Math.max(arr[0][c] + p[1][c - 1], p[0][c - 1]);
				p[1][c] = Math.max(arr[1][c] + p[0][c - 1], p[1][c - 1]);
			}
			
			sb.append(Math.max(p[0][N], p[1][N])).append('\n');
		}

		System.out.println(sb);
	}
}