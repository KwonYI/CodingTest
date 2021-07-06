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

	static int N, M, answer = 11;
	static char[][] map;
	static Ball hole;
	static final int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		
		Ball blue = null, red = null;
		for (int r = 0; r < N; r++) {
			map[r] = bf.readLine().toCharArray();
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 'B') blue = new Ball(r, c, 0);
				else if (map[r][c] == 'R') red = new Ball(r, c, 0);
				else if (map[r][c] == 'O') hole = new Ball(r, c, 0);
			}
		}
		
		play(blue, red, 0);
		
		System.out.println(answer == 11 ? -1 : answer);

	}

	public static void play(Ball blue, Ball red, int cnt) {
		if(cnt > 10 || cnt >= answer || goalIn(blue, hole)) {
			return;
		}else if(goalIn(red, hole)) {
			answer = Math.min(answer, cnt);
			return;
		}else {
			for(int d = 0; d < dirs.length; d++) {
				Ball nextB = move(blue, d);
				Ball nextR = move(red, d);
				
				if(goalIn(nextB, hole)) {
					continue;
				}else if(goalIn(nextR, nextB)) {
					if(nextR.cnt > nextB.cnt) {
						nextR.r -= dirs[d][0];
						nextR.c -= dirs[d][1];
					}else {
						nextB.r -= dirs[d][0];
						nextB.c -= dirs[d][1];
					}
				}
				
				play(nextB, nextR, cnt + 1);
			}
		}
	}
	
	public static Ball move(Ball ball, int d) {
		int cnt = 0;
		int r = ball.r, c = ball.c;
		
		while(true) {
			int nr = r + dirs[d][0];
			int nc = c + dirs[d][1];
			
			if(map[nr][nc] == '#') break;

			r = nr;
			c = nc;
			cnt++;
			
			if(map[r][c] == 'O') break;
		}
		
		return new Ball(r, c, cnt);
	}
	
	public static boolean goalIn(Ball ball, Ball target) {
		return ball.r == target.r && ball.c == target.c;
	}
}