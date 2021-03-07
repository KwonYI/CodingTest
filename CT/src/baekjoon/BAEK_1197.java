package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1197 {
	
	static class Node implements Comparable<Node>{
		int t;
		int f;
		int price;
		
		public Node(int t, int f, int price) {
			this.t = t;
			this.f = f;
			this.price = price;
		}
		
		public int compareTo(Node o) {
			return this.price - o.price;
		}
	}
	
	static int N, M;
	
	static int[] p;
	static int total;
	static PriorityQueue<Node> arr = new PriorityQueue<Node>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		p = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			p[i] = i;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int t = Integer.parseInt(st.nextToken());
			int f = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			
			arr.add(new Node(f, t, price));
		}
		
		int cnt = 0;
		while (!arr.isEmpty()) {
			Node cur = arr.poll();
		
			int t = cur.t;
			int f = cur.f;
			
			if(union(t, f)) {
				total += cur.price;
				if(++cnt == N - 1) break;
			}
		}
		
		System.out.println(total);
	}
	
	public static int find(int t) {
		if(t != p[t]) p[t] = find(p[t]);
		return p[t];
	}
	
	public static boolean union(int t, int f) {
		int tp = find(t);
		int fp = find(f);
		if(tp == fp) return false;
		
		p[fp] = tp;
		return true;
	}
}