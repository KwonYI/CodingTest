package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1937 {

	static class Pos {
		int r;
		int c;
		int cur;
		int day;

		public Pos(int r, int c, int cur, int day) {
			this.r = r;
			this.c = c;
			this.cur = cur;
			this.day = day;
		}
	}

	static int N;
	static int[][] map, p;
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };

	static Queue<Pos> q = new LinkedList<Pos>();

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());

		map = new int[N][N];
		p = new int[N + 2][N + 2];

		for (int r = 0; r < N; r++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				find(r, c);
			}
		}

		for (int[] row : p) {
			for (int ele : row) {
				System.out.print(ele + " ");
			}
			System.out.println();
		}
	}

	private static void find(int R, int C) {
		// TODO Auto-generated method stub
		
	}
}