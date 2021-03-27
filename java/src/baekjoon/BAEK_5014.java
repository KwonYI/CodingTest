package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_5014 {
	
	static class Elevator{
		int cur;
		int cnt;
		
		public Elevator(int cur, int cnt) {
			this.cur = cur;
			this.cnt = cnt;
		}
	}
	
	static int F, S, G, U, D;
	static boolean[] visited;
	static int answer = - 1;
			
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		visited = new boolean[F + 1];
		
		Queue<Elevator> q = new LinkedList<Elevator>();
		q.add(new Elevator(S, 0));
		
		Elevator e;
		while (!q.isEmpty()) {
			e = q.poll();
			
			int cur = e.cur;
			int cnt = e.cnt;
			
			if(cur == G) {
				answer = cnt;
				break;
			}
			
			if(visited[cur]) continue;
			visited[cur] = true;
			
			if(cur + U <= F && !visited[cur + U]) q.add(new Elevator(cur + U, cnt + 1));
			if(cur - D >= 1 && !visited[cur - D]) q.add(new Elevator(cur - D, cnt + 1));
		}
		
		if(answer == -1) System.out.println("use the stairs");
		else System.out.println(answer);
	}
}