package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1916 {
	
	static class Bus implements Comparable<Bus>{
		int t;
		int price;
		
		public Bus(int t, int price) {
			this.t = t;
			this.price = price;
		}
		
		public int compareTo(Bus o) {
			return this.price - o.price;
		}
	}
	
	static int N, M;
	static int[] p;
	static boolean[] visited;
	static ArrayList<Bus> buses[];
	
	static int start, end;
	
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.valueOf(bf.readLine());
		M = Integer.valueOf(bf.readLine());
		
		p = new int[N + 1];
		visited = new boolean[N + 1];
		buses = new ArrayList[N + 1];
		
		for (int i = 1; i <= N; i++) {
			buses[i] = new ArrayList<Bus>();
			p[i] = INF;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int p = Integer.parseInt(st.nextToken());
			
			buses[f].add(new Bus(t , p));
		}
		
		st = new StringTokenizer(bf.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		p[start] = 0;
		PriorityQueue<Bus> pq = new PriorityQueue<Bus>();
		pq.add(new Bus(start, 0));
		
		while (!pq.isEmpty()) {
			Bus cur = pq.poll();
			
			int t = cur.t;
			int price = cur.price;
			
			if(visited[t]) continue;
			visited[t] = true;
			
			for (Bus bus : buses[t]) {
				if(bus.price + p[t] < p[bus.t]) {
					p[bus.t] = bus.price + p[t];
					pq.add(new Bus(bus.t, p[bus.t]));
				}
			}
		}
		System.out.println(p[end]);
	}
}