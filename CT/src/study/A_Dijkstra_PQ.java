package study;

import java.io.*;
import java.util.*;

public class A_Dijkstra_PQ {

	static class Node implements Comparable<Node> {
		int f;
		int value;

		public Node(int vertex, int cost) {
			this.f = vertex;
			this.value = cost;
		}

		public int compareTo(Node o) {
			return this.value - o.value;
		}
	}
	
	static int V, E, K;
	static int[] d;
	static ArrayList<Node> edges[];
	static PriorityQueue<Node> pq;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());

		edges = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++) { // V+1 ê°?
			edges[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			
			edges[f].add(new Node(t, value));
			edges[t].add(new Node(f, value));
		}

		d = new int[V + 1];
		Arrays.fill(d, Integer.MAX_VALUE);

		pq = new PriorityQueue<Node>();
		visited = new boolean[V + 1];

		pq.add(new Node(1, 0)); // 1?—?„œ ì¶œë°œ
		d[1] = 0;

		while (!pq.isEmpty()) {
			Node cur = pq.poll();

			int f = cur.f;

			for (Node next : edges[cur.f]) {
				if (d[next.f] > d[f] + next.value) {
					d[next.f] = d[f] + next.value;

					pq.add(new Node(next.f, d[next.f]));
				}
			}
		}
		
		System.out.println(d[V]);
	}
}