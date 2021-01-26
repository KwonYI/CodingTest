import java.io.*;
import java.util.*;

public class BAEK_19238 {

	static class Pos implements Comparable<Pos> {
		int r;
		int c;
		int dis;

		public Pos(int r, int c, int dis) {
			this.r = r;
			this.c = c;
			this.dis = dis;
		}

		public int compareTo(Pos o) { // 젤 가깝고 젤 위면서 젤 왼쪽
			if (this.dis == o.dis) {
				if (this.r == o.r) return this.c - o.c;
				else return this.r - o.r;
			}
			return this.dis - o.dis;
		}
	}

	static int N, M, fuel;
	static int[][] map;
	static boolean[][] visited;
	static HashMap<Integer, Pos> passengers = new HashMap<Integer, Pos>();
	
	static int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());

		map = new int[N + 1][N + 1];
		visited = new boolean[N + 1][N + 1];

		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(bf.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		PriorityQueue<Pos> emptyTaxi = new PriorityQueue<Pos>(); // 빈 택시
		st = new StringTokenizer(bf.readLine());

		int tr = Integer.parseInt(st.nextToken());
		int tc = Integer.parseInt(st.nextToken());

		emptyTaxi.add(new Pos(tr, tc, 0));

		for (int i = 2; i < M + 2; i++) {
			st = new StringTokenizer(bf.readLine());

			int pr = Integer.parseInt(st.nextToken());
			int pc = Integer.parseInt(st.nextToken());

			map[pr][pc] = i;

			int ar = Integer.parseInt(st.nextToken());
			int ac = Integer.parseInt(st.nextToken());

			passengers.put(i, new Pos(ar, ac, 0));
		}

		int passengerNum = 0; // 승객 번호
		Pos taxi = null; // 택시 위치
		Pos destination = null; // 도착지 위치
		Queue<Pos> inTaxi = new LinkedList<Pos>(); // 승객이 탄 택시
		
		ex : while (true) {
			// 1. 승객 찾기
			while(!emptyTaxi.isEmpty()) {
				taxi = emptyTaxi.poll();
				
				int r = taxi.r;
				int c = taxi.c;
				int dis = taxi.dis;

				if(dis > fuel) break ex; // 기름 부족하면 그만 가
				
				if(map[r][c] > 1) {
					fuel -= dis;
					
					inTaxi.add(new Pos(r, c, 0)); // 승객 태워
					passengerNum = map[r][c]; // 맵에서 지워주기위해 알아둔다
					destination = passengers.get(passengerNum); // 없으면 null
					map[r][c] = 0; // 승객위치 비워주기
					
					break;
				}
				
				if(visited[r][c]) continue;
				visited[r][c] = true;
				
				for (int[] dir : dirs) {
					int nr = r + dir[0];
					int nc = c + dir[1];
					
					if(!check(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;
					
					emptyTaxi.add(new Pos(nr, nc, dis + 1));
				}
			}
			
			if(destination == null) break; // 승객을 못 찾았으면 나가
			init(); // 방문배열 초기화
			emptyTaxi.clear(); // 승객찾을 택시큐 비워주기
			
			// 2. 찾은 승객에서 도착지 가기
			while (!inTaxi.isEmpty()) {
				taxi = inTaxi.poll();
				
				int r = taxi.r;
				int c = taxi.c;
				int dis = taxi.dis;
				
				if(dis > fuel) break ex; // 기름 부족하면 그만 가
				
				if(r == destination.r && c == destination.c) {
					fuel -= dis;
					
					fuel += dis*2; // 연료 채워
					passengers.remove(passengerNum); // 승객 내렸어
					emptyTaxi.add(new Pos(r, c, 0)); // 그 위치에서 다시 승객 찾아
					M--; // 승객수 줄여
					if(M == 0) break ex; // 다 내렸으면 나가
					
					break;
				}
				
				if(visited[r][c]) continue;
				visited[r][c] = true;
				
				for (int[] dir : dirs) {
					int nr = r + dir[0];
					int nc = c + dir[1];
					
					if(!check(nr, nc) || visited[nr][nc] || map[nr][nc] == 1) continue;
					
					inTaxi.add(new Pos(nr, nc, dis + 1));
				}
				
			}
			
			init(); // 방문배열 초기화
			inTaxi.clear(); // 도착지 찾을 큐 비워주기
			destination = null; // 도착지 위치 초기화
		}
		
		if(M != 0) fuel = -1;
		
		System.out.println(fuel);
	}
	
	private static void init() {
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				visited[r][c] = false;
			}
		}
	}

	private static boolean check(int r, int c) {
		if(r > N || c > N || r < 1 || c < 1 ) return false;
		return true;
	}
}
