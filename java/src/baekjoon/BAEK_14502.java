package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_14502 {

	static int N, M, answer;
	static char[][] map;
	static int[][] dir = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };

	static boolean[] alpha = new boolean['Z' - 'A' + 1];

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];

		for (int r = 0; r < N; r++) {
			map[r] = bf.readLine().toCharArray();
		}

		alpha[map[0][0] - 'A'] = true;
		answer = 1;

		Go(0, 0, answer);

		System.out.println(answer);
	}

	public static void Go(int r, int c, int move) {
		answer = answer < move ? move : answer;

		int nr, nc;
		for (int[] d : dir) {
			nr = r + d[0];
			nc = c + d[1];
			if (!check(nr, nc) || alpha[map[nr][nc] - 'A'])
				continue;
			alpha[map[nr][nc] - 'A'] = true;
			Go(nr, nc, move + 1);
			alpha[map[nr][nc] - 'A'] = false;
		}
		return;
	}

	public static boolean check(int nr, int nc) {
		if (nr < 0 || nc < 0 || nr > N - 1 || nc > M - 1)
			return false;
		return true;
	}
}
