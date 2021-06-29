package programmers;


import java.util.*;

class Programmers_배달 {

	class Road implements Comparable<Road> {
		int to;
		int time;

		public Road(int to, int time) {
			this.to = to;
			this.time = time;
		}

		public int compareTo(Road o) {
			return this.time - o.time;
		}
	}

	int[] p;
	ArrayList<Road> roads[];
	PriorityQueue<Road> pq = new PriorityQueue<>();

	public int solution(int N, int[][] road, int K) {
		p = new int[N + 1];
		roads = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			roads[i] = new ArrayList<>();
			p[i] = Integer.MAX_VALUE;
		}

		for (int[] r : road) {
			int f = r[0];
			int t = r[1];
			int time = r[2];

			roads[f].add(new Road(t, time));
			roads[t].add(new Road(f, time));
		}

		p[1] = 0;
		pq.add(new Road(1, 0));

		while (!pq.isEmpty()) {
			Road info = pq.poll();

			int cur = info.to;
			int time = info.time;

			for (Road next : roads[cur]) {
				if (p[next.to] > p[cur] + next.time && p[cur] + next.time <= K) {
					p[next.to] = p[cur] + next.time;
					pq.add(new Road(next.to, p[next.to]));
				}
			}
		}

		int answer = 0;
		for (int i = 1; i <= N; i++) {
			if (p[i] <= K) {
				answer += 1;
			}
		}

		return answer;
	}
}