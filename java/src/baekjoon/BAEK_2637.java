package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_2637 {

	static int N, M;
	static int[] degree;
	static int[][] toys;
	static boolean[] isBasic;
	static ArrayList<Integer> arr[];

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());
		M = Integer.valueOf(bf.readLine());

		degree = new int[N + 1];
		isBasic = new boolean[N + 1];
		arr = new ArrayList[N + 1];
		toys = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int target = Integer.parseInt(st.nextToken());
			int basic = Integer.parseInt(st.nextToken());
			int cnt = Integer.parseInt(st.nextToken());

			degree[target]++;
			toys[target][basic] = cnt;
			arr[basic].add(target);
		}

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i < N; i++) {
			if (degree[i] == 0) {
				q.add(i);
				isBasic[i] = true;
			}
		}

		while (!q.isEmpty()) {
			int cur = q.poll();

			if (!isBasic[cur]) {
				for (int part = 1; part < N; part++) {
					if (!isBasic[part]) {
						int requireCnt = toys[cur][part];
						toys[cur][part] = 0;

						for (int subPart = 1; subPart < N; subPart++) {
							int cnt = toys[part][subPart];

							toys[cur][subPart] = toys[cur][subPart] + requireCnt * cnt;
						}

					}
				}
			}

			for (int next : arr[cur]) {
				if (--degree[next] == 0) {
					q.add(next);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int part = 1; part < N; part++) {
			if (isBasic[part]) {
				sb.append(part).append(' ').append(toys[N][part]).append('\n');
			}
		}

		System.out.println(sb);
	}
}