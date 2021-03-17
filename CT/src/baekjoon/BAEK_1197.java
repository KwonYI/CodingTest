package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_1197 {
	
	static class Node{
		int from;
		int to;
		int price;
		
		public Node(int from, int to, int price) {
			this.from = from;
			this.to = to;
			this.price = price;
		}
	}
	
	static int V, E;
	static int[] p;
	static ArrayList<Node> nodes = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		p = new int[V + 1];
		for (int i = 1; i <= V; i++) {
			p[i] = i;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			
			nodes.add(new Node(from, to, price));
		}
		
		Collections.sort(nodes, new Comparator<Node>() {
			public int compare(Node o1, Node o2) {
				return o1.price - o2.price;
			}
		});
		
		long total = 0;
		int cnt = 0;
		
		for (Node cur : nodes) {
			int from = cur.from;
			int to = cur.to;
			
			if(union(from, to)) {
				total += cur.price;
				if(++cnt == V - 1) break;
			}
		}
		
		System.out.println(total);
		
	}
	
	public static int find(int x) {
		if(x != p[x]) p[x] = find(p[x]);
		return p[x];
	}
	
	public static boolean union(int x, int y) {
		int px = find(x);
		int py = find(y);
		
		if(px == py) return false;
		
		p[py] = px;
		return true;
	}
}