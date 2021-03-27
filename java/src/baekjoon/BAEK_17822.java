package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_17822 {

	static int N, M, T;

	static Deque<Integer>[] rotatePlate; // 회전하는 원판
	static int[][] checkPlate;			 // 인접한 숫자 확인
	static boolean[][] deletePlate;		 // 제거할 숫자 확인

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());

		checkPlate = new int[N][M];
		deletePlate = new boolean[N][M];
		rotatePlate = new Deque[N];
		for (int i = 0; i < N; i++) {
			rotatePlate[i] = new LinkedList<Integer>();
		}

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 0; c < M; c++) {
				rotatePlate[r].add(Integer.parseInt(st.nextToken()));
			}
		}

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(bf.readLine());

			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			rotate(x, d, k);
			check();
		}
		
		int answer = 0;
		for (int[] row : checkPlate) {
			for (int ele : row) {
				answer += ele;
			}
		}
		System.out.println(answer);
	}

	private static void rotate(int x, int d, int k) {
		for (int r = 0; r < N; r++) {
			if ((r + 1) % x == 0) {
				Queue<Integer> sub = new LinkedList<Integer>();
				if (d == 0) {
					for (int c = 0; c < k; c++) {
						sub.add(rotatePlate[r].pollLast());
					}
					while (!sub.isEmpty()) {
						rotatePlate[r].addFirst(sub.poll());
					}
				} else {
					for (int c = 0; c < k; c++) {
						sub.add(rotatePlate[r].pollFirst());
					}
					while (!sub.isEmpty()) {
						rotatePlate[r].addLast(sub.poll());
					}
				}
			}
		}
	}

	private static void check() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				checkPlate[r][c] = rotatePlate[r].poll();
				deletePlate[r][c] = false;
			}
		}

		if (canDelete()) {
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(deletePlate[r][c]) checkPlate[r][c] = 0;
				}
			}
		} else {
			float sum = 0;
			int cnt = 0;
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(checkPlate[r][c] != 0) {
						sum += checkPlate[r][c];
						cnt++;
					}
				}
			}
			
			sum /= cnt;
			
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					if(checkPlate[r][c] == 0) continue;
					
					if(checkPlate[r][c] > sum) checkPlate[r][c]--;
					else if(checkPlate[r][c] < sum) checkPlate[r][c]++;
				}
			}
		}
		
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				rotatePlate[r].add(checkPlate[r][c]);
			}
		}
	}

	private static boolean canDelete() {
		boolean flag = false;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				int cur = checkPlate[r][c];
				
				if(cur == 0) continue;

				if(r - 1 >= 0 && checkPlate[r-1][c] == cur) {
					deletePlate[r - 1][c] = true;
					deletePlate[r][c] = true;
					flag = true;
				}
				
				if(r + 1 < N && checkPlate[r+1][c] == cur) {
					deletePlate[r + 1][c] = true;
					deletePlate[r][c] = true;
					flag = true;
				}
				
				int right = c == M - 1 ? 0 : c + 1; 
				if(cur == checkPlate[r][right]) {
					deletePlate[r][right] = true;
					deletePlate[r][right] = true;
					flag = true;
				}
				int left = c == 0 ? M - 1 : c - 1;
				if(cur == checkPlate[r][left]) {
					deletePlate[r][left] = true;
					deletePlate[r][left] = true;
					flag = true;
				}
			}
		}
		
		return flag;
	}
}