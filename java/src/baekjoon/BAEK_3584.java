package baekjoon;

import java.io.*;
import java.util.*;

public class BAEK_3584 {

	static int T, N;
	static int A, B;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		T = Integer.valueOf(bf.readLine());

		for (int testCase = 0; testCase < T; testCase++) {
			N = Integer.valueOf(bf.readLine());

			int[] nodes = new int[N + 1];

			for (int i = 0; i < N - 1; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());

				int u = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());

				nodes[d] = u;
			}

			StringTokenizer st = new StringTokenizer(bf.readLine());

			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			Queue<Integer> q = new LinkedList<Integer>();
			q.add(A);
			boolean[] visited = new boolean[N + 1];

			while (!q.isEmpty()) {
				int cur = q.poll();

				if (visited[cur]) continue;
				visited[cur] = true;

				q.add(nodes[cur]);
			}

			q.add(B);
			while (!q.isEmpty()) {
				int cur = q.poll();

				if (visited[cur]) {
					sb.append(cur).append('\n');
					break;
				} else {
					visited[cur] = true;
				}

				q.add(nodes[cur]);

			}

		}
		
		System.out.println(sb);
	}
}