package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_2589 {

	static class Pos {
		int r;
		int c;
		int cnt;

		public Pos(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	static int answer = Integer.MIN_VALUE;
	static Queue<Pos> q = new LinkedList<Pos>();
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			map[r] = bf.readLine().toCharArray();
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 'L' && !visited[r][c]) {
					visited[r][c] = true;
					answer = Math.max(find(r, c), answer);
				}
			}
		}
		
		System.out.println(answer);
	}

	private static int find(int R, int C) {
		boolean[][] visited = new boolean[N][M];
		
		q.add(new Pos(R, C, 0));
		int cnt = 0;
		Pos cur;

		while (!q.isEmpty()) {
			cur = q.poll();

			int r = cur.r;
			int c = cur.c;
			cnt = cur.cnt;

			if (visited[r][c]) continue;
			visited[r][c] = true;

			for (int[] dir : dirs) {
				int nr = r + dir[0];
				int nc = c + dir[1];
				
				if(!check(nr, nc) || map[nr][nc] != 'L' || visited[nr][nc]) continue;
				
				q.add(new Pos(nr, nc, cnt + 1));
			}
		}

		return cnt;
	}

	private static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r > N - 1 || c > M - 1) return false;
		return true;
	}
}