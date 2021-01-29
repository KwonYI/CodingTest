package baekjoon;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BAEK_15683 {

	static class CCTV {
		int r;
		int c;
		int t; // CCTV 타입

		public CCTV(int r, int c, int t) {
			this.r = r;
			this.c = c;
			this.t = t;
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] initmap;

	static int blindSpotCnt = Integer.MAX_VALUE;
	
	static int cctvCnt;
	static ArrayList<CCTV> cctvs = new ArrayList<CCTV>();

	static int[] selectedDir; // 조합

	static int[][][] types = { { { 0 }, { 1 }, { 2 }, { 3 } },  // 감시가능한 방향의 모든 경우의 수
								{ { 0, 1 }, { 2, 3 } },
								{ { 0, 2 }, { 2, 1 }, { 1, 3 }, { 3, 0 } }, 
								{ { 0, 1, 2 }, { 0, 1, 3 }, { 0, 2, 3 }, { 1, 2, 3 } },
								{ { 0, 1, 2, 3} } };
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		initmap = new int[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 0; c < M; c++) {
				int cur = Integer.parseInt(st.nextToken());

				initmap[r][c] = cur;
				map[r][c] = cur;

				if (cur >= 1 && cur <= 5) cctvs.add(new CCTV(r, c, cur));
			}
		}
		
		cctvCnt = cctvs.size();
		selectedDir = new int[cctvCnt];
		
		solve(0);

		System.out.println(blindSpotCnt);
	}

	private static void solve(int cnt) {
		if(cnt == cctvCnt) { // 조합 뽑으면 돌려
			CCTV cur = null;
			
			for (int index = 0; index < cnt; index++) {
				int dirType = selectedDir[index];
				cur = cctvs.get(index);
				watch(cur, dirType);
			}
			
			int curCnt = findBlindSpot();
			
			blindSpotCnt = Math.min(curCnt, blindSpotCnt);
			copyMap();
			
			return;
		}
		int curType = cctvs.get(cnt).t;
		
		if(curType == 1) {
			for (int i = 0; i < 4; i++) {
				selectedDir[cnt] = i;
				solve(cnt + 1);
			}
		}else if(curType == 2) {
			for (int i = 0; i < 2; i++) {
				selectedDir[cnt] = i;
				solve(cnt + 1);
			}
		}else if(curType == 3) {
			for (int i = 0; i < 4; i++) {
				selectedDir[cnt] = i;
				solve(cnt + 1);
			}
		}else if(curType == 4) {
			for (int i = 0; i < 4; i++) {
				selectedDir[cnt] = i;
				solve(cnt + 1);
			}
		}else {
			selectedDir[cnt] = 0;
			solve(cnt + 1);
		}
	}

	public static void copyMap() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = initmap[r][c];
			}
		}
	}

	public static boolean check(int r, int c) {
		if (r < 0 || c < 0 || r > N - 1 || c > M - 1) return false;
		return true;
	}

	public static void watch(CCTV cctv, int dirType) {
		int r = cctv.r;
		int c = cctv.c;
		int t = cctv.t - 1;

		for (int type : types[t][dirType]) {
			int nr = r, nc = c;

			while(true) {
				nr += dirs[type][0];
				nc += dirs[type][1];
				
				if(check(nr, nc) && map[nr][nc] != 6) map[nr][nc] = '#';
				else break;
			}
		}
	}

	public static int findBlindSpot() {
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0)
					cnt++;
			}
		}
		return cnt;
	}
}