package baekjoon;
import java.io.*;
import java.util.*;

public class BAEK_2468 {

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int safeZoneCnt = 1; // 잠기는 지역 없다

	static int maxWater = Integer.MIN_VALUE; // 입력값 중 가장 큰 값
	static Queue<Pos> q = new LinkedList<Pos>(); // 안전영역 탐색 위한 큐
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 사방탐색

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.valueOf(bf.readLine());

		map = new int[N][N];
		visited = new boolean[N][N];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 0; c < N; c++) {
				int cur = Integer.parseInt(st.nextToken());
				map[r][c] = cur;

				maxWater = Math.max(maxWater, cur);
			}
		}

		for (int water = 1; water < maxWater; water++) {
			sink(water); // 잠기는 부분 체크

			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (!visited[r][c]) {
						checkSafeZone(r, c);
						cnt++;
					}
				}
			}

			safeZoneCnt = Math.max(cnt, safeZoneCnt);
			init(); // 초기화
		}
		
		System.out.println(safeZoneCnt);
	}
	
	private static void sink(int water) {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if (map[r][c] <= water)
					visited[r][c] = true;
			}
		}
	}

	private static void init() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				visited[r][c] = false;
			}
		}
	}
	
	private static void checkSafeZone(int R, int C) {
		q.clear();
		q.add(new Pos(R, C));

		while (!q.isEmpty()) {
			Pos cur = q.poll();

			int r = cur.r;
			int c = cur.c;

			if (visited[r][c]) continue;
			visited[r][c] = true;

			for (int[] dir : dirs) {
				int nr = r + dir[0];
				int nc = c + dir[1];

				if (!check(nr, nc) || visited[nr][nc]) continue;
				
				q.add(new Pos(nr, nc));
			}
		}
	}
	
	private static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r > N - 1 || c > N - 1) return false;
		return true;
	}
}