package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1987 {

	static int R, C, answer = 0;
	static char[][] map;
	static boolean[] visitedAlpha;
	static boolean[][] visitedMap;
	static final int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][];
		visitedMap = new boolean[R][C];
		visitedAlpha = new boolean['Z' - 'A' + 1];

		for (int r = 0; r < R; r++) {
			map[r] = bf.readLine().toCharArray();
		}

		findRoute(0, 0, 1);

		System.out.println(answer);

	}

	private static void findRoute(int r, int c, int cnt) {
		int index = map[r][c] - 'A';
		visitedMap[r][c] = visitedAlpha[index] = true;
		answer = Math.max(answer, cnt);
		
		for (int[] dir : dirs) {
			int nr = r + dir[0];
			int nc = c + dir[1];

			if (!isIn(nr, nc) || visitedAlpha[map[nr][nc] - 'A'] || visitedMap[nr][nc]) continue;

			findRoute(nr, nc, cnt + 1);
		}
		
		visitedMap[r][c] = visitedAlpha[index] = false;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C; 
	}
}
