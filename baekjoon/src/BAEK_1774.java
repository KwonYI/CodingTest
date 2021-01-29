import java.io.*;
import java.util.*;

public class BAEK_1774 {
	
	static class Pos implements Comparable<Pos>{
		int to;
		int from;
		double dist;
		
		public Pos(int to, int from, double dist) {
			this.to = to;
			this.from = from;
			this.dist = dist;
		}

		public int compareTo(Pos o) {
			return Double.compare(this.dist, o.dist);
		}
	}
	
	static int N, M, cnt = 0;
	static double answer;
	static int[] p;
	static int[][] pos;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		pos = new int[N + 1][2];
		visited = new boolean[N + 1];
		p = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());

			p[i] = i;
			pos[i][0] = Integer.parseInt(st.nextToken());
			pos[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			cnt++;
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		PriorityQueue<Pos> pq = new PriorityQueue<>();
		for (int i = 1; i <= N; i++) {
			for (int j = i + 1; j <= N; j++) {
				pq.add(new Pos(i, j, distance(i, j)));
			}
		}
		
		while (!pq.isEmpty()) {
			Pos cur = pq.poll();
			
			if(union(cur.to, cur.from)) {
				answer += cur.dist;
				if(++cnt == N - 1) break;
			}
		}
		
		System.out.printf("%.2f", answer);
		
	}
	
	public static int find(int to) {
		if(to != p[to]) p[to] = find(p[to]);
		return p[to];
	}
	
	public static boolean union(int to, int from) {
		int pt = find(to);
		int pf = find(from);
		
		if(pt == pf) return false;
		
		p[pf] = pt;
		return true;
	}
	
	public static double distance(int from, int to) {
		return Math.sqrt(Math.pow(pos[from][0] - pos[to][0], 2) + Math.pow(pos[from][1] - pos[to][1], 2));
	}
}