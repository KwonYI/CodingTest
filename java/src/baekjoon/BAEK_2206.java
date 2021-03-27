package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_2206 {

	static class Pos {
		int r;
		int c;
		int cnt;
		int smash;

		public Pos(int r, int c, int cnt, int smash) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.smash = smash;
		}
	}

	static int N, M;
	static int move = -1;
	static char[][] map;
	static boolean[][][] visited;
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		visited = new boolean[2][N][M];

		for (int r = 0; r < N; r++) {
			map[r] = bf.readLine().toCharArray();
		}

		Queue<Pos> q = new LinkedList<Pos>();
		Pos cur = new Pos(0, 0, 1, 0);
		q.add(cur);

		while (!q.isEmpty()) {
			cur = q.poll();

			int r = cur.r;
			int c = cur.c;
			int cnt = cur.cnt;
			int smash = cur.smash;
			
			if(r == N - 1 && c == M - 1) {
				move = cnt;
				break;
			}
			
			if(visited[smash][r][c]) continue;
			visited[smash][r][c] = true;
			
			for (int[] dir : dirs) {
				int nr = r + dir[0];
				int nc = c + dir[1];
				
				if(!check(nr, nc) || visited[smash][nr][nc]) continue;

				if(smash == 0) { // 벽을 부신적이 없어
					if(map[nr][nc] == '1') {
						q.add(new Pos(nr, nc, cnt + 1, 1));
					}else {
						q.add(new Pos(nr, nc, cnt + 1, smash));
					}
				}else { // 벽을 부신적이 있어
					if(map[nr][nc] == '0') {
						q.add(new Pos(nr, nc, cnt + 1, smash));
					}
				}
			}
		}
		
		System.out.println(move);
	}

	private static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r > N - 1 || c > M - 1) return false;
		return true;
	}
}