package programmers;

import java.util.*;

class Programmers_합승택시요금 {
	
	class Fare implements Comparable<Fare>{
		int to;
		int fare;
		
		public Fare(int to, int fare) {
			this.to = to;
			this.fare = fare;
		}

		public int compareTo(Fare o) {
			return Integer.compare(this.fare ,o.fare);
		}
	}
	
	final int INF = Integer.MAX_VALUE;
	int[] minFareFromS, minFareToA, minFareToB;
	ArrayList<Fare> fares[];
	
	public int solution(int n, int s, int a, int b, int[][] list) {
		int answer = INF;
		
		fares = new ArrayList[n + 1];
		minFareFromS = new int[n + 1];
		minFareToA = new int[n + 1];
		minFareToB = new int[n + 1];
		
		for (int i = 1; i <= n; i++) {
			fares[i] = new ArrayList<>();
			minFareFromS[i] = minFareToA[i] = minFareToB[i] = INF;
		}
		
		for (int[] info : list) {
			int to = info[0];
			int from = info[1];
			int fare = info[2];
			
			fares[to].add(new Fare(from, fare));
			fares[from].add(new Fare(to, fare));
		}
		
		findMinRoute(n, minFareFromS, s);
		findMinRoute(n, minFareToA, a);
		findMinRoute(n, minFareToB, b);
		
		for (int i = 1; i <= n; i++) {
			if(minFareFromS[i] == INF || minFareToA[i] == INF || minFareToB[i] == INF) continue;
			answer = Math.min(answer, minFareFromS[i] + minFareToA[i] + minFareToB[i]);
		}
		
		return answer;
	}
	
	public void findMinRoute(int n, int[] minFares, int dest) {
		boolean[] visited = new boolean[n + 1];
		
		minFares[dest] = 0;
		PriorityQueue<Fare> pq = new PriorityQueue<>();
		pq.add(new Fare(dest, 0));
		
		while (!pq.isEmpty()) {
			Fare cur = pq.poll();
			
			int to = cur.to;
			
			if(visited[to]) continue;
			visited[to] = true;
			
			for (Fare fares : fares[to]) {
				if(!visited[fares.to] && minFares[fares.to] > minFares[to] + fares.fare) {
					minFares[fares.to] = minFares[to] + fares.fare;
					pq.add(new Fare(fares.to, minFares[fares.to]));
				}
			}
		}
	}
}
