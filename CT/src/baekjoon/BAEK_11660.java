package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_11660 {

	static int N, M;
	static int[][] p;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		p = new int[N + 1][N + 2];

		for (int r = 1; r <= N; r++) {
			String[] input = bf.readLine().split(" ");
			int sum = 0;
			for (int c = N; c >= 1; c--) {
				sum += Integer.valueOf(input[c - 1]);
				p[r][c] = sum;
			}
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			sb.append(area(x1, y1, x2, y2)).append('\n');
		}
		
		System.out.println(sb);
	}

	private static int area(int x1, int y1, int x2, int y2) {
		int sum = 0;
		for (int r = x1; r <= x2; r++) {
			sum += p[r][y1];
			sum -= p[r][y2 + 1];
		}
		return sum;
	}
}