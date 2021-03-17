package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1753 {
	
	static class Node implements Comparable<Node>{
		int to;
		int price;
		
		public Node(int to, int price) {
			this.to = to;
			this.price = price;
		}

		public int compareTo(Node o) {
			return this.price - o.price;
		}
		
	}
	
	static int V, E;
	static int start;
	static int[] p;
	static ArrayList<Node> nodes[];
	static PriorityQueue<Node> pq = new PriorityQueue<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		p = new int[V + 1];
		nodes = new ArrayList[V + 1];
		
		for (int i = 1; i <= V; i++) {
			p[i] = Integer.MAX_VALUE;
			nodes[i] = new ArrayList<>();
		}
		
		start = Integer.valueOf(bf.readLine());
		
		p[start] = 0;
		pq.add(new Node(start, 0));
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			
			nodes[from].add(new Node(to, price));
		}
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			int to = cur.to;
			
			for (Node next : nodes[to]) {
				if(p[next.to] > p[to] + next.price) {
					p[next.to] = p[to] + next.price;
					pq.add(new Node(next.to, p[next.to]));
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= V; i++) {
			if(p[i] == Integer.MAX_VALUE) sb.append("INF").append('\n');
			else sb.append(p[i]).append('\n');
		}
		
		System.out.println(sb);

	}
}