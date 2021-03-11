package study;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class A_Prim_PQ {

	static class Node implements Comparable<Node> {
		int f;
		int value;

		public Node(int f, int value) {
			this.f = f;
			this.value = value;
		}

		public int compareTo(Node o) {
			return this.value - o.value;
		}
	}

	static int N, M;
	static long total;
	static ArrayList<Node> arr[];
	static boolean[] visited;
	static int[] minEdge;

	static PriorityQueue<Node> q;

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		minEdge = new int[N + 1];

		arr = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = new ArrayList<Node>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());

			int f = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			arr[f].add(new Node(t, value));
			arr[t].add(new Node(f, value));
		}

		int cnt = 0;
		minEdge[1] = 0;

		q = new PriorityQueue<Node>();
		q.offer(new Node(1, 0)); // 0ë¶??„° ì¶œë°œ!

		while (!q.isEmpty()) {
			Node cur = q.poll();

			int f = cur.f;

			if (visited[f])	continue;

			total += cur.value;
			visited[f] = true;


			for (Node next : arr[f]) {
				if (!visited[next.f]) {
					q.add(next);
				}
			}
			
			if (++cnt == N) break;
		}

		System.out.println(total);
	}
}