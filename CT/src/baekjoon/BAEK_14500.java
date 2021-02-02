package baekjoon;
import java.io.*;
import java.util.*;

public class BAEK_14500 {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int total;

	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };
	static int[][][] special = { 
			{ { 1, 0 }, { 0, 1 }, { 0, -1 } } ,
			{ { 1, 0 }, { -1, 0 }, { 0, -1 } } ,
			{ { -1, 0 }, { 0, 1 }, { 0, -1 } } ,
			{ { 1, 0 }, { -1, 0 }, { 0, 1 } }
	};

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				visited[r][c] = true;
				find(r, c, 1, map[r][c]);
				visited[r][c] = false;
				total = Math.max(total, plus(r, c));
			}
		}
		
		System.out.println(total);
	}

	private static int plus(int r, int c) {
		int sum = 0, sub;
		int nr, nc;
		for (int d = 0; d < 4; d++) {
			sub = map[r][c];
			for (int[] dir : special[d]) {
				nr = r + dir[0];
				nc = c + dir[1];
				
				if(!check(nr, nc)) {
					sub = -1;
					break;
				}
				
				sub += map[nr][nc];
			}
			
			sum = Math.max(sum, sub);
		}
		return sum;
	}

	private static void find(int r, int c, int cnt, int sum) {
		if(cnt == 4) {
			total = Math.max(total, sum);
			return;
		}
		for (int[] dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];
			
			if(check(nr, nc) && !visited[nr][nc]) {
				visited[nr][nc] = true;
				find(nr, nc, cnt + 1, sum + map[nr][nc]);
				visited[nr][nc] = false;
			}
		}
	}

	private static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r > N - 1 || c > M - 1) return false;
		return true;
	}
}