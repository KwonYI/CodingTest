package baekjoon;
import java.io.*;
import java.util.*;

public class BAEK_1238 {

	static class Route implements Comparable<Route>{
		int to;
		int price;

		public Route(int to, int price) {
			this.to = to;
			this.price = price;
		}

		public int compareTo(Route o) {
			return this.price - o.price;
		}
	}

	static int N, M, X;
	static int[][] minPrices;
	static boolean[][] visited;
	static ArrayList<Route>[][] routes;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		minPrices = new int[2][N + 1];
		visited = new boolean[2][N + 1];
		routes = new ArrayList[2][N + 1];
		
		for (int i = 1; i <= N; i++) {
			routes[0][i] = new ArrayList<Route>();
			routes[1][i] = new ArrayList<Route>();
			minPrices[0][i] = minPrices[1][i] = Integer.MAX_VALUE;
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int to = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			int price = Integer.parseInt(st.nextToken());
			
			routes[0][to].add(new Route(from, price));
			routes[1][from].add(new Route(to, price));
		}
		
		findMinRoute(0, X);
		findMinRoute(1, X);
		
		int answer = 0;
		for (int i = 1; i <= N; i++) {
			answer = Math.max(answer, minPrices[0][i] + minPrices[1][i]);
		}
		
		System.out.println(answer);
	}

	private static void findMinRoute(int i, int start) {
		PriorityQueue<Route> pq = new PriorityQueue<Route>();
		minPrices[i][start] = 0;
		pq.add(new Route(start, 0));
		
		while (!pq.isEmpty()) {
			Route cur = pq.poll();
			
			int to = cur.to;
			
			if(visited[i][to]) continue;
			visited[i][to] = true;
			
			for (Route next : routes[i][to]) {
				if(!visited[i][next.to] && minPrices[i][next.to] > minPrices[i][to] + next.price) {
					minPrices[i][next.to] = minPrices[i][to] + next.price;
					pq.add(new Route(next.to, minPrices[i][next.to]));
				}
			}
		}
	}
}