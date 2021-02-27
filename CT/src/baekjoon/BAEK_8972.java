package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_8972 {

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int R, C;
	static char[][] map;

	static Pos myAd;
	static int num = 1; // 미친 아두이노 번호
	static HashMap<Integer, Pos> crazyAd = new HashMap<>();

	static int[][] dirs = { { 1, -1 }, { 1, 0 }, { 1, 1 }, 
							{ 0, -1 }, { 0, 0 }, { 0, 1 }, 
							{ -1, -1 }, { -1, 0 }, { -1, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];

		for (int r = 0; r < R; r++) {
			String input = bf.readLine();
			for (int c = 0; c < C; c++) {
				map[r][c] = '.'; // 출력을 위한 맵 초기화
				char cur = input.charAt(c);

				if (cur == 'R') crazyAd.put(num++, new Pos(r, c));
				else if (cur == 'I') myAd = new Pos(r, c);
			}
		}

		String orders = bf.readLine();
		int X = 0;

		for (int i = 0; i < orders.length(); i++) {
			int order = orders.charAt(i) - '1'; // 5 -> dirs[4]

			// 1. 종수꺼
			int nr = myAd.r + dirs[order][0];
			int nc = myAd.c + dirs[order][1];

			// 2. 미친애 만나는지
			if (check(nr, nc)) {
				X = i + 1;
				break;
			}
			myAd = new Pos(nr, nc);

			// 3,4,5. 미친애들 움직이고 종수만나는지 확인하고 미친애들끼리 부셔
			if (moveCrazy()) {
				X = i + 1;
				break;
			}
		}

		if (X == 0) {
			map[myAd.r][myAd.c] = 'I';
			for (Pos crazy : crazyAd.values()) {
				map[crazy.r][crazy.c] = 'R';
			}

			StringBuilder sb = new StringBuilder();
			for (char[] row : map) {
				for (char ele : row) {
					sb.append(ele);
				}
				sb.append('\n');
			}

			System.out.println(sb);
		} else {
			System.out.println("kraj " + X);
		}
	}

	private static boolean check(int r, int c) {
		for (Pos crazy : crazyAd.values()) {
			if (r == crazy.r && c == crazy.c) return true;
		}
		return false;
	}

	private static boolean moveCrazy() {
		int tr = myAd.r;
		int tc = myAd.c;

		for (int num : crazyAd.keySet()) {
			Pos crazy = crazyAd.get(num);

			int r = crazy.r;
			int c = crazy.c;

			int nr = 0;
			int nc = 0;
			int minDistance = Integer.MAX_VALUE;

			for (int[] dir : dirs) {
				int subr = r + dir[0];
				int subc = c + dir[1];

				int distance = getDistance(tr, tc, subr, subc);

				if (minDistance > distance) {
					minDistance = distance;
					nr = subr;
					nc = subc;
				}
			}

			if (nr == tr && nc == tc) return true;

			crazyAd.put(num, new Pos(nr, nc));
		}

		int[][] subMap = new int[R][C];
		HashSet<Integer> deleteIndices = new HashSet<>();

		for (int num : crazyAd.keySet()) {
			Pos crazy = crazyAd.get(num);

			if (subMap[crazy.r][crazy.c] != 0) {
				deleteIndices.add(num);
				deleteIndices.add(subMap[crazy.r][crazy.c]);
			} else {
				subMap[crazy.r][crazy.c] = num;
			}
		}

		for (int index : deleteIndices) {
			crazyAd.remove(index);
		}

		return false;
	}

	private static int getDistance(int tr, int tc, int r, int c) {
		return Math.abs(tr - r) + Math.abs(tc - c);
	}
}