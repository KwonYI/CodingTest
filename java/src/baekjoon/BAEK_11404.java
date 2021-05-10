package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_11404 {

	static int N, M;
	static int[][] map;

	static final int INF = 100000001;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());

		map = new int[N][N];

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				map[r][c] = INF;
			}
		}

		for (int i = 0; i < N; i++) {
			map[i][i] = 0;
		}

		M = Integer.valueOf(bf.readLine());

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int f = Integer.parseInt(st.nextToken()) - 1;
			int t = Integer.parseInt(st.nextToken()) - 1;
			int price = Integer.parseInt(st.nextToken());

			map[f][t] = Math.min(price, map[f][t]);
		}

		for (int k = 0; k < N; k++) { // °ÅÃÄ°¥ ³ð
			for (int i = 0; i < N; i++) { // Ãâ¹ß
				for (int j = 0; j < N; j++) { // µµÂø
					if (map[i][k] + map[k][j] < map[i][j]) {
						map[i][j] = map[i][k] + map[k][j];
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int[] row : map) {
			for (int ele : row) {
				if(ele == INF) sb.append(0).append(' ');
				else sb.append(ele).append(' ');
			}
			sb.append('\n');
		}
		
		System.out.println(sb);
	}
}