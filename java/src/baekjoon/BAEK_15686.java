package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_15686 {
	
	static class Pos{
		int r;
		int c;
		
		public Pos(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N,M;
	static int[][] map;
	static int[] selected;
	static int total = Integer.MAX_VALUE;
	
	static ArrayList<Pos> houses = new ArrayList<Pos>();
	static ArrayList<Pos> chickens = new ArrayList<Pos>();
	static int cCnt;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 0; c < N; c++) {
				int cur = Integer.parseInt(st.nextToken());
				map[r][c] = cur;
				
				if(cur == 1) houses.add(new Pos(r, c));
				else if(cur == 2) chickens.add(new Pos(r, c));
			}
		}
		
		cCnt = chickens.size();
		
		for (int m = 1; m <= M; m++) {
			selected = new int[m];
			setChicken(0 , 0, m);
		}
		
		System.out.println(total);
		
	}
	
	private static void setChicken(int cnt, int cur, int end) {
		if(cnt == end) {
			int sum = calDistance();
			total = Math.min(total, sum);
			return;
		}
		for (int i = cur; i < cCnt; i++) {
			selected[cnt] = i;
			setChicken(cnt + 1, i + 1, end);
		}
	}

	private static int calDistance() {
		int sum = 0;
		for (Pos house : houses) {
			int sub = Integer.MAX_VALUE;
			
			int r = house.r;
			int c = house.c;
			
			for (int index : selected) {
				Pos chicken = chickens.get(index);
				
				int tr = chicken.r;
				int tc = chicken.c;
				
				sub = Math.min(sub, distance(r, c, tr, tc));
			}
			sum += sub;
		}
		return sum;
	}

	private static int distance(int r, int c, int tr, int tc) {
		return Math.abs(r - tr) + Math.abs(c - tc);
	}
}