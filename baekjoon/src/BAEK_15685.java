import java.io.*;
import java.util.*;

public class BAEK_15685 {
	static int N;

	static boolean[][] visited = new boolean[101][101];

	static ArrayList<Integer> moveDir = new ArrayList<Integer>(); // 모든 커브 정보 모아둔다
	static int[][] dirs = { { 1, 0 }, { 0, -1 }, { -1, 0 }, { 0, 1 } }; // 커브 그리기
	static int[][] square = { { 1, 0 }, { 0, 1 }, { 1, 1 } }; // 사각형 확인

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.valueOf(bf.readLine());

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());

			drawCurve(r, c, d, g);
		}

		int cnt = 0;
		for (int r = 0; r <= 99; r++) {
			for (int c = 0; c <= 99; c++) {
				if (visited[c][r] && allDragon(r, c)) cnt++; // 4개가 다 커브면 더해줘
			}
		}

		System.out.println(cnt);
	}

	private static void drawCurve(int r, int c, int d, int g) {
		makeCurve(d, g); // 그릴 커브 만들기
		visited[c][r] = true;
		
		for (int dir : moveDir) {
			r += dirs[dir][0];
			c += dirs[dir][1];
			
			visited[c][r] = true;
		}
	}

	private static void makeCurve(int d, int g) {
		moveDir.clear(); // 초기화
		moveDir.add(d); // 0세대는 d방향
		
		for (int i = 1; i <= g; i++) { // 세대마다 움직이는 거리 저장
			
			int start = 1 << (i - 1); // 시작 좌표
			int end = 1 << i; // 끝 좌표
			int index = 1;
			
			for (int j = start; j < end; j++) {
				moveDir.add( (moveDir.get(j - index) + 1 ) % 4);
				index += 2;
			}
		}
	}

	private static boolean allDragon(int r, int c) { // 사각형 꼭지점이 전부 커브인지 확인, 범위 확인은 필요없어
		for (int[] dir : square) {
			int nr = r + dir[0];
			int nc = c + dir[1];
			
			if(!visited[nc][nr]) return false;
		}
		return true;
	}
}