package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_13460 {

	static class Ball {
		int r;
		int c;
		int cnt;

		public Ball(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}

	static int N, M;
	static char[][] map;
	static Ball hole;
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static int answer = 11;

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];

		Ball red = null, blue = null;
		for (int r = 0; r < N; r++) {
			map[r] = bf.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 'B') {
					blue = new Ball(r, c, 0);
					map[r][c] = '.';
				} else if (map[r][c] == 'R') {
					red = new Ball(r, c, 0);
					map[r][c] = '.';
				} else if (map[r][c] == 'O') {
					hole = new Ball(r, c, 0);
				}
			}
		}

		// 현재 이동 방향으로 연속으로 가지않게 만든다
		play(red, blue, 0, 0);

		if (answer == 11) {
			answer = -1;
		}

		System.out.println(answer);
	}

	private static void play(Ball red, Ball blue, int prevD, int round) {
		Ball curRed = red, curBlue = blue;
		// 10번 이상 시도했거나 파란공이 구멍에 들어갔다
		if (round > 10 || isSamePos(blue, hole)) {
			return;
		}

		// 10번 이하면서 파란공은 구멍에 안들어가고 빨간공은 구멍에 들어갔다
		if (isSamePos(red, hole)) {
			answer = Math.min(answer, round);
			return;
		}

		// 빨간공과 파란공의 위치가 같다 -> 더 많이 이동한 공을 반대방향으로 하나 이동시킨다
		if (isSamePos(red, blue)) {
			if (red.cnt > blue.cnt) {
				int nr = red.r - dirs[prevD][0];
				int nc = red.c - dirs[prevD][1];
				
				curRed = new Ball(nr, nc, 0);
			} else if(red.cnt < blue.cnt){
				int nr = blue.r - dirs[prevD][0];
				int nc = blue.c - dirs[prevD][1];
				
				curBlue = new Ball(nr, nc, 0);
			}else {
				System.out.println("나올리 없지");
			}
		}

		for (int d = 0; d < 4; d++) {
			// 이전에 움직인 방향과 같으면 못가게한다
			if (prevD == d) {
				continue;
			}

			// 왜 그냥 red, blue로 하면 안되는거지...???
			Ball newRed = moveBall(curRed, d);
			Ball newBlue = moveBall(curBlue, d);

			play(newRed, newBlue, d, round + 1);
		}
	}

	private static Ball moveBall(Ball cur, int d) {
		int cnt = 0;
		int r = cur.r;
		int c = cur.c;

		while (true) {
			// 움직일 위치 계산
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];

			// 갈 수 없으면 종료
			if (map[nr][nc] == '#') {
				break;
			}

			// 움직이고 움직인 횟수+
			r = nr;
			c = nc;
			cnt++;

			// 구멍에 들어가면 종료
			if (map[r][c] == 'O') {
				break;
			}
		}

		return new Ball(r, c, cnt);
	}

	public static boolean isSamePos(Ball o1, Ball o2) {
		return o1.r == o2.r && o1.c == o2.c;
	}
}