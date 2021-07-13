package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_5547 {

	static class Pos {
		int h;
		int w;

		public Pos(int h, int w) {
			this.h = h;
			this.w = w;
		}
	}

	static int W, H, total;
	static int[][] map;
	static boolean[][] visited;

	static final int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static final int[][][] extraDirs = { { { -1, -1 }, { 1, -1 } }, { { -1, 1 }, { 1, 1 } } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		W = Integer.parseInt(st.nextToken()); // 8
		H = Integer.parseInt(st.nextToken()); // 4

		total = 0;
		map = new int[H + 2][W + 2];
		visited = new boolean[H + 2][W + 2];

		for (int h = 1; h <= H; h++) {
			st = new StringTokenizer(bf.readLine());
			for (int w = 1; w <= W; w++) {
				map[h][w] = Integer.parseInt(st.nextToken());
			}
		}

		find(0, 0);

		System.out.println(total);
	}

	private static void find(int hh, int ww) {
		Queue<Pos> q = new LinkedList<>();
		q.add(new Pos(hh, ww));

		while (!q.isEmpty()) {
			Pos pos = q.poll();

			int h = pos.h;
			int w = pos.w;

			if (visited[h][w]) continue;
			visited[h][w] = true;

			for (int[] dir : dirs) {
				int nh = h + dir[0];
				int nw = w + dir[1];

				if (isOut(nh, nw) || visited[nh][nw]) continue;

				if (map[nh][nw] == 1) total++;
				else q.add(new Pos(nh, nw));
			}

			for (int[] dir : extraDirs[h % 2]) {
				int nh = h + dir[0];
				int nw = w + dir[1];

				if (isOut(nh, nw) || visited[nh][nw]) continue;

				if (map[nh][nw] == 1) total++;
				else  q.add(new Pos(nh, nw));
			}
		}
	}

	private static boolean isOut(int h, int w) {
		return h < 0 || w < 0 || h > H + 1 || w > W + 1;
	}
}