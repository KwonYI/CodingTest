package baekjoon;
import java.io.*;
import java.util.*;

public class BAEK_4386 {
	
	static class Star implements Comparable<Star> {
		int to;
		double dist;

		public Star(int t, double dist) {
			this.to = t;
			this.dist = dist;
		}

		public int compareTo(Star o) {
			return Double.compare(this.dist, o.dist);
		}
	}

	static int N, cnt = 0;
	static double answer;
	static float[][] stars;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.valueOf(bf.readLine());

		stars = new float[N + 1][2];
		visited = new boolean[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());

			stars[i][0] = Float.parseFloat(st.nextToken());
			stars[i][1] = Float.parseFloat(st.nextToken());
		}

		PriorityQueue<Star> pq = new PriorityQueue<Star>();
		pq.add(new Star(1, 0));

		while (!pq.isEmpty()) {
			Star cur = pq.poll();

			if (visited[cur.to])	continue;
			visited[cur.to] = true;

			answer += cur.dist;
			if (++cnt == N)	break;

			for (int next = 1; next <= N; next++) {
				if (!visited[next]) {
					pq.add(new Star(next, distance(next, cur.to)));
				}
			}
		}

		System.out.printf("%.2f", answer);
	}

	public static double distance(int from, int to) {
		return Math.sqrt(Math.pow(stars[from][0] - stars[to][0], 2) + Math.pow(stars[from][1] - stars[to][1], 2));
	}
}