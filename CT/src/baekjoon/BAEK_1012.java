package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1012 {
	
	static class Pos{
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int T, M, N, K;
	static int[][] map;
	static int cnt;
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.valueOf(bf.readLine());

		for (int test = 0; test < T; test++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			cnt = 0;
			
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new int[N][M];

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(bf.readLine());

				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				
				map[r][c] = 1;
			}
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(map[r][c] == 1) {
						cnt++;
						check(r, c);
					}
				}
			}
			sb.append(cnt).append('\n');
		}
		System.out.println(sb);
	}

	private static void check(int R, int C) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(R, C));
		
		while (!q.isEmpty()) {
			Pos cur = q.poll();
			
			int r = cur.r;
			int c = cur.c;
			
			if(map[r][c] != 1) continue;
			map[r][c] = 2; // 방문했다 체크
			
			for (int[] dir : dirs) {
				int nr = r + dir[0];
				int nc = c + dir[1];
				
				if(nr < 0 || nc < 0 || nr > N - 1 || nc > M - 1 || map[nr][nc] != 1) continue;
				
				q.add(new Pos(nr, nc));
			}
		}
	}
}