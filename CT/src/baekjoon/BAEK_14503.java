package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_14503 {

	static class Robot {
		int r;
		int c;
		int d; // 현재 방향

		public Robot(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}

	static int N, M;
	static int[][] map;
	static int cnt = 0;
	static int[][] dirs = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } }; // 북,동,남,서

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];

		st = new StringTokenizer(bf.readLine());
		
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		Robot cur = new Robot(R, C, D);

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		Queue<Robot> q = new PriorityQueue<Robot>();
		q.add(cur);
		
		while (!q.isEmpty()) {
			cur = q.poll();
			
			int r = cur.r;
			int c = cur.c;
			int d = cur.d;

			// 1. 청소
			if(map[r][c] == 0) { 
				cnt++;
				map[r][c] = 2;
			}
			
			boolean flag = true; // 갈 수 있는 곳 있나
			int nr, nc;
			
			// 2. 왼쪽부터 돌면서 탐색
			for (int loop = 0; loop < 4; loop++) {
				d = d - 1 < 0 ? 3 : d - 1;
				
				nr = r + dirs[d][0];
				nc = c + dirs[d][1];
				
				if(check(nr, nc) && map[nr][nc] == 0) {
					q.add(new Robot(nr, nc, d)); // 청소하러가
					flag = false;
					break;
				}
			}
			
			if(flag) { // 갈 수 있는곳이 없다 -> 후진
				nr = r - dirs[d][0];
				nc = c - dirs[d][1];
				
				if(!check(nr, nc)) continue;
				
				if(map[nr][nc] != 1) q.add(new Robot(nr, nc, d));
				else break;
			}
		}
		
		System.out.println(cnt);

	}
	
	private static boolean check(int r, int c) {
		if(r < 0 || c < 0 || r > N - 1 || c > M - 1) return false;
		return true;
	}

}