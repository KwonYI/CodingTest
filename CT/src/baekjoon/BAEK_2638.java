package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_2638 {

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M;
	static int[][] cheese;
	static int time;
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		cheese = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 0; c < M; c++) {
				cheese[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		while(true) {
			find();
			if(melt() == 0) break;
			time++;
		}
		System.out.println(time);
	}
	
	public static void find() {
		Queue<Pos> q = new LinkedList<Pos>();
		q.add(new Pos(0, 0));
		int nr, nc;

		while (!q.isEmpty()) {
			Pos cur = q.poll();

			int r = cur.r;
			int c = cur.c;

			if (cheese[r][c] == -1)	continue;
			cheese[r][c] = -1;

			for (int[] dir : dirs) {
				nr = r + dir[0];
				nc = c + dir[1];
				
				if(!check(nr, nc)) continue;
				
				if(cheese[nr][nc] > 0) cheese[nr][nc]++;
				else {
					q.add(new Pos(nr, nc));
				}

			}
		}
	}
	
	public static int melt() {
		int sum = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				int cur = cheese[r][c];
				
				if(cur == - 1 || cur == 0) cheese[r][c] = 0;
				else if(cur > 2) {
					sum++;
					cheese[r][c] = 0;
				}else {
					cheese[r][c] = 1;
				}
				
			}
		}
		return sum;
	}

	public static boolean check(int r, int c) {
		if (r > N - 1 || c > M - 1 || r < 0 || c < 0) return false;
		return true;
	}
}