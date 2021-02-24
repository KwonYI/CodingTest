package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_4485 {

	static class Pos implements Comparable<Pos> {
		int r;
		int c;
		int coin;

		public Pos(int r, int c, int coin) {
			this.r = r;
			this.c = c;
			this.coin = coin;
		}

		public int compareTo(Pos o) {
			return this.coin - o.coin;
		}
	}

	static int N, testCase = 0;
	static int[][] p; // 최소 비용 저장
	static int[][] map; // 잃는 코인 저장

	static StringBuilder sb = new StringBuilder();
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());
		do {
			testCase++;

			p = new int[N][N];
			map = new int[N][N];

			for (int r = 0; r < N; r++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int c = 0; c < N; c++) {
					map[r][c] = Integer.parseInt(st.nextToken());
					p[r][c] = Integer.MAX_VALUE;
				}
			}

			solve(testCase);

			N = Integer.valueOf(bf.readLine());
		} while (N != 0);

		System.out.println(sb);
	}

	private static void solve(int testCase) {
		PriorityQueue<Pos> pq = new PriorityQueue<Pos>();
		
		p[0][0] = map[0][0];
		pq.add(new Pos(0, 0, p[0][0]));
		
		while (!pq.isEmpty()) {
			Pos pos = pq.poll();

			int r = pos.r;
			int c = pos.c;
			int coin = pos.coin;

			for (int[] dir : dirs) {
				int nr = r + dir[0];
				int nc = c + dir[1];

				if (nr < 0 || nc < 0 || nr > N - 1 || nc > N - 1) continue;

				if (p[nr][nc] > coin + map[nr][nc]) {
					p[nr][nc] = coin + map[nr][nc];
					pq.add(new Pos(nr, nc, coin + map[nr][nc]));
				}
			}
		}

		sb.append("Problem ").append(testCase).append(": ").append(p[N - 1][N - 1]).append('\n');
	}
}