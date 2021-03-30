package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_12865 {

	static int N, K;
	static int[][] items;
	static int[][] p;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		p = new int[N + 1][K + 1];
		items = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());

			items[i][0] = Integer.parseInt(st.nextToken()); // 무게
			items[i][1] = Integer.parseInt(st.nextToken()); // 가치
		}

		for (int i = 1; i <= N; i++) {
			int weight = items[i][0];
			int value = items[i][1];
			
			for (int w = 1; w <= K; w++) {
				p[i][w] = p[i - 1][w];
			}

			for (int w = weight; w <= K; w++) {
				p[i][w] = Math.max(p[i - 1][w], p[i - 1][w - weight] + value);
			}
		}
		
		System.out.println(p[N][K]);
	}
}