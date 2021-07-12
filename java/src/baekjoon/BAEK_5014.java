package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_5014 {

	static class Ele {
		int cur;
		int cnt;

		public Ele(int cur, int cnt) {
			this.cur = cur;
			this.cnt = cnt;
		}
	}

	static int F, S, G, U, D;
	static int answer = -1;
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		F = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		G = Integer.parseInt(st.nextToken());
		U = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		visited = new boolean[F + 1];
		Queue<Ele> q = new LinkedList<Ele>();
		q.add(new Ele(S, 0));

		while (!q.isEmpty()) {
			Ele e = q.poll();

			int cur = e.cur;
			int cnt = e.cnt;

			if (cur == G) {
				answer = cnt;
				break;
			}

			if (visited[cur])
				continue;
			visited[cur] = true;

			if (cur + U <= G) q.add(new Ele(cur + U, cnt + 1));
			if (cur >= D) q.add(new Ele(cur - D, cnt + 1));
		}

		System.out.println(answer == -1 ? "use the stairs" : answer);
	}
}