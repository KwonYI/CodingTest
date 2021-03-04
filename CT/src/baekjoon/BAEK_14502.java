package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_14502 {

	static class Pos {
		int r;
		int c;

		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M;
	
	static int[] selected;
	static int K;
    
	static int[][] initmap;
	static int[][] map;
	static boolean[][] visited;
	
	static int safeArea;
	
	static ArrayList<Pos> virus = new ArrayList<Pos>();
	static ArrayList<Pos> walls = new ArrayList<Pos>();

	static int[][] dirs = { { 1, 0 }, { -1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		initmap = new int[N][M];
		visited = new boolean[N][M];

		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 0; c < M; c++) {
				int cur = Integer.parseInt(st.nextToken());
				initmap[r][c] = map[r][c] = cur;
				if(cur == 2) virus.add(new Pos(r, c));
				else if(cur == 0) walls.add(new Pos(r, c));
			}
		}
		
		K = walls.size();
		selected = new int[K];
		int cnt = 0;
		while(cnt++ < 3) selected[K - cnt] = 1;

		do {
			buildWall();
			spread();			
			safeArea = Math.max(safeArea, findSafeArea());
			init();
		} while (nextComb(selected));
		
		System.out.println(safeArea);

	}
	
	public static void buildWall() {
		for (int i = 0; i < K; i++) {
			if(selected[i] == 1) {
				Pos cur = walls.get(i);
				
				map[cur.r][cur.c] = 1;
			}
		}
	}
	
	public static void init() {
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				map[r][c] = initmap[r][c];
				visited[r][c] = false;
			}
		}
	}
	
	public static void spread() {
		Queue<Pos> q = new LinkedList<Pos>();
		for (Pos pos : virus) { 
			q.add(pos);
		}
		
		while(!q.isEmpty()) {
			Pos cur = q.poll();
			
			int r = cur.r;
			int c = cur.c;
			
			for (int[] dir : dirs) {
				int nr = r + dir[0];
				int nc = c + dir[1];
				
				if(nr < 0 || nc < 0 || nr > N - 1 || nc > M - 1 || visited[nr][nc]) continue;
				
				if(map[nr][nc] == 0) {
					map[nr][nc] = 2;
					q.add(new Pos(nr, nc));
					visited[nr][nc] = true;
				}
			}
		}
	}
	
	private static boolean nextComb(int[] arr) {
		int i = K - 1;
		while(i > 0 && arr[i - 1] >= arr[i]) --i;
		
		if(i == 0) return false;
		
		int j = K - 1;
		while(arr[i - 1] >= arr[j]) --j;
		
		swap(arr, i - 1, j);
		
		int k = K - 1;
		while(k > i) swap(arr, k--, i++);
		
		return true;
	}
	
	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	public static int findSafeArea() {
		int cnt = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if(map[r][c] == 0) cnt++;
			}
		}
		return cnt;
	}
}