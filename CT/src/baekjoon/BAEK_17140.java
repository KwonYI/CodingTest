package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_17140 {

	static class Element implements Comparable<Element> {
		int num;
		int cnt;

		public Element(int num, int cnt) {
			this.num = num;
			this.cnt = cnt;
		}

		public int compareTo(Element o) {
			if (o.cnt == this.cnt) return this.num - o.num;
			else return this.cnt - o.cnt;
		}
	}

	static int r, c, k;
	static int[][] A = new int[101][101];
	static int maxR = 3, maxC = 3; // 가장 긴 행과 열의 길이

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		for (int R = 1; R <= 3; R++) {
			st = new StringTokenizer(bf.readLine());
			for (int C = 1; C <= 3; C++) {
				A[R][C] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0;
		
		while(A[r][c] != k) {
			if(maxR >= maxC) {
				action(true, maxR, maxC);
			}else {
				action(false, maxR, maxC);
			}
			
			if(++time > 100) break;
		}
		
		if(time > 100) System.out.println(-1);
		else System.out.println(time);

	}

	private static void action(boolean flag, int R, int C) { // true면 R, false면 C
		if(flag) {
			for (int r = 1; r <= R; r++) {
				HashMap<Integer, Integer> map = new HashMap<>();
				for (int c = 1; c <= C; c++) {
					if(A[r][c] == 0) continue;
					map.put(A[r][c], map.getOrDefault(A[r][c], 0) + 1);
					A[r][c] = 0;
				}
				
				PriorityQueue<Element> pq = new PriorityQueue<>();
				for (Integer key : map.keySet()) {
					pq.add(new Element(key, map.get(key)));
				}
				
				maxC = Math.max(maxC, map.size() * 2);
				
				int c = 1;
				while(!pq.isEmpty()) {
					Element cur = pq.poll();
					A[r][c++] = cur.num;
					A[r][c++] = cur.cnt;
					
					if(c > 100) {
						maxC = 100;
						break;
					}
				}
			}
			
		}else {
			for (int c = 1; c <= C; c++) {
				HashMap<Integer, Integer> map = new HashMap<>();
				for (int r = 1; r <= R; r++) {
					if(A[r][c] == 0) continue;
					map.put(A[r][c], map.getOrDefault(A[r][c], 0) + 1);
					A[r][c] = 0;
				}
				
				PriorityQueue<Element> pq = new PriorityQueue<>();
				for (Integer key : map.keySet()) {
					pq.add(new Element(key, map.get(key)));
				}
				
				maxR = Math.max(maxR, map.size() * 2);
				
				int r = 1;
				while(!pq.isEmpty()) {
					Element cur = pq.poll();
					A[r++][c] = cur.num;
					A[r++][c] = cur.cnt;
					
					if(r > 100) {
						maxR = 100;
						break;
					}
				}
			}
		}
	}
}