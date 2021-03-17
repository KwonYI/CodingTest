package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_2583 {
	
	static class Pos{
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int M, N, K;
	static boolean[][] map, visited;
	static ArrayList<Integer> areas = new ArrayList<>();
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new boolean[M][N];
		visited = new boolean[M][N];

		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(bf.readLine());

			int lr = Integer.parseInt(st.nextToken());
			int lc = Integer.parseInt(st.nextToken());
			int rr = Integer.parseInt(st.nextToken());
			int rc = Integer.parseInt(st.nextToken());
			
			int row = rc - lc;
			int col = rr - lr;
			
			for (int r = M - rc; r < M - rc + row; r++) {
				for (int c = lr; c < lr + col; c++) {
					map[r][c] = true;
				}
			}
		}
		
		int cnt = 0;		
		for (int r = 0; r < M; r++) {
			for (int c = 0; c < N; c++) {
				if(!map[r][c] && !visited[r][c]) {
					cnt++;
					findArea(r, c);
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(cnt).append('\n');
		
		Collections.sort(areas);
		for (int area : areas) {
			sb.append(area).append(' ');
		}
		
		System.out.println(sb);

	}

	private static void findArea(int R, int C) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(R, C));
		int size = 0;
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			int r = cur.r;
			int c = cur.c;
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			
			size++;
			
			for (int[] dir : dirs) {
				int nr = r + dir[0];
				int nc = c + dir[1];
				
				if(nr < 0 || nc < 0 || nr > M - 1 || nc > N - 1 || map[nr][nc] || visited[nr][nc]) continue;
				
				q.add(new Pos(nr, nc));
			}
		}
		
		areas.add(size);
	}
}